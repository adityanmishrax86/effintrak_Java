#!/bin/bash

# effinTrak Development Server Startup Script
# This script starts both the backend (Spring Boot) and frontend (Next.js) servers

set -e

# Colors for terminal output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Configuration
BACKEND_PORT=8080
FRONTEND_PORT=3000
BACKEND_PID_FILE="/tmp/effintrak-backend.pid"
FRONTEND_PID_FILE="/tmp/effintrak-frontend.pid"

# Function to print messages
print_header() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}"
}

print_success() {
    echo -e "${GREEN}✓ $1${NC}"
}

print_error() {
    echo -e "${RED}✗ $1${NC}"
}

print_info() {
    echo -e "${YELLOW}ℹ $1${NC}"
}

# Cleanup function
cleanup() {
    echo ""
    print_header "Shutting down servers..."
    
    if [ -f "$BACKEND_PID_FILE" ]; then
        BACKEND_PID=$(cat "$BACKEND_PID_FILE")
        if kill -0 "$BACKEND_PID" 2>/dev/null; then
            print_info "Stopping backend (PID: $BACKEND_PID)..."
            kill "$BACKEND_PID" 2>/dev/null || true
            sleep 2
            kill -9 "$BACKEND_PID" 2>/dev/null || true
        fi
        rm -f "$BACKEND_PID_FILE"
    fi
    
    if [ -f "$FRONTEND_PID_FILE" ]; then
        FRONTEND_PID=$(cat "$FRONTEND_PID_FILE")
        if kill -0 "$FRONTEND_PID" 2>/dev/null; then
            print_info "Stopping frontend (PID: $FRONTEND_PID)..."
            kill "$FRONTEND_PID" 2>/dev/null || true
            sleep 2
            kill -9 "$FRONTEND_PID" 2>/dev/null || true
        fi
        rm -f "$FRONTEND_PID_FILE"
    fi
    
    print_success "Servers stopped"
    exit 0
}

# Set up trap for cleanup on script exit
trap cleanup EXIT INT TERM

# Kill any processes on required ports
print_header "Checking ports..."

for PORT in $BACKEND_PORT $FRONTEND_PORT; do
    if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null 2>&1; then
        PIDS=$(lsof -Pi :$PORT -sTCP:LISTEN -t)
        print_info "Found process(es) on port $PORT: $PIDS"
        print_info "Killing process(es) on port $PORT..."
        kill -9 $PIDS 2>/dev/null || true
        sleep 1
    fi
done

print_success "Ports cleared"

# Get the directory where this script is located
SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$SCRIPT_DIR"

print_header "Starting effinTrak Development Environment"

# Start Backend
print_info "Starting backend server on port $BACKEND_PORT..."
if command -v mvn &> /dev/null; then
    # Maven is installed, run with mvn
    mvn spring-boot:run > /tmp/effintrak-backend.log 2>&1 &
    BACKEND_PID=$!
elif [ -f "./mvnw" ]; then
    # Maven wrapper available
    ./mvnw spring-boot:run > /tmp/effintrak-backend.log 2>&1 &
    BACKEND_PID=$!
else
    print_error "Maven not found! Please install Maven or ensure mvnw exists."
    exit 1
fi

echo $BACKEND_PID > "$BACKEND_PID_FILE"
print_success "Backend process started (PID: $BACKEND_PID)"
print_info "Backend logs: tail -f /tmp/effintrak-backend.log"

# Wait for backend to start
print_info "Waiting for backend to start (up to 30 seconds)..."
BACKEND_READY=0
for i in {1..30}; do
    if curl -s http://localhost:$BACKEND_PORT/actuator/health > /dev/null 2>&1; then
        BACKEND_READY=1
        break
    fi
    echo -n "."
    sleep 1
done
echo ""

if [ $BACKEND_READY -eq 1 ]; then
    print_success "Backend is ready!"
else
    print_error "Backend failed to start. Check logs at /tmp/effintrak-backend.log"
    exit 1
fi

# Start Frontend
print_info "Starting frontend server on port $FRONTEND_PORT..."
if [ -d "./frontend" ]; then
    cd frontend
    
    if [ ! -d "node_modules" ]; then
        print_info "Installing frontend dependencies..."
        npm install
    fi
    
    npm run dev > /tmp/effintrak-frontend.log 2>&1 &
    FRONTEND_PID=$!
    cd ..
else
    print_error "Frontend directory not found!"
    exit 1
fi

echo $FRONTEND_PID > "$FRONTEND_PID_FILE"
print_success "Frontend process started (PID: $FRONTEND_PID)"
print_info "Frontend logs: tail -f /tmp/effintrak-frontend.log"

# Wait for frontend to start
print_info "Waiting for frontend to start (up to 30 seconds)..."
FRONTEND_READY=0
for i in {1..30}; do
    if curl -s http://localhost:$FRONTEND_PORT > /dev/null 2>&1; then
        FRONTEND_READY=1
        break
    fi
    echo -n "."
    sleep 1
done
echo ""

if [ $FRONTEND_READY -eq 1 ]; then
    print_success "Frontend is ready!"
else
    print_error "Frontend failed to start. Check logs at /tmp/effintrak-frontend.log"
    sleep 5
fi

# Print summary
print_header "effinTrak Services Running"
print_success "Backend running at http://localhost:$BACKEND_PORT"
print_success "Frontend running at http://localhost:$FRONTEND_PORT"
print_info "API Documentation: http://localhost:$BACKEND_PORT/v3/api-docs"
print_info "Backend logs: tail -f /tmp/effintrak-backend.log"
print_info "Frontend logs: tail -f /tmp/effintrak-frontend.log"
print_info "Press Ctrl+C to stop all servers"

# Keep the script running
while true; do
    # Check if backend is still running
    if [ -f "$BACKEND_PID_FILE" ]; then
        BACKEND_PID=$(cat "$BACKEND_PID_FILE")
        if ! kill -0 "$BACKEND_PID" 2>/dev/null; then
            print_error "Backend process died (PID: $BACKEND_PID)"
            rm -f "$BACKEND_PID_FILE"
        fi
    fi
    
    # Check if frontend is still running
    if [ -f "$FRONTEND_PID_FILE" ]; then
        FRONTEND_PID=$(cat "$FRONTEND_PID_FILE")
        if ! kill -0 "$FRONTEND_PID" 2>/dev/null; then
            print_error "Frontend process died (PID: $FRONTEND_PID)"
            rm -f "$FRONTEND_PID_FILE"
        fi
    fi
    
    sleep 5
done
