#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

print_header() {
  echo -e "${BLUE}========================================${NC}"
  echo -e "${BLUE}$1${NC}"
  echo -e "${BLUE}========================================${NC}"
}

print_success() { echo -e "${GREEN}✓ $1${NC}"; }
print_info() { echo -e "${YELLOW}ℹ $1${NC}"; }
print_error() { echo -e "${RED}✗ $1${NC}"; }

require_cmd() {
  if ! command -v "$1" >/dev/null 2>&1; then
    print_error "Missing required command: $1"
    exit 1
  fi
}

print_header "effinTrak Online Deployment"

require_cmd docker

if ! docker compose version >/dev/null 2>&1; then
  print_error "Docker Compose plugin is required. Install/enable 'docker compose'."
  exit 1
fi

if [[ -z "${NVIDIA_API_KEY:-}" ]]; then
  print_error "NVIDIA_API_KEY is not set."
  echo "Set it and rerun:"
  echo "  export NVIDIA_API_KEY='your_nvidia_api_key'"
  exit 1
fi

PUBLIC_HOST="${PUBLIC_HOST:-}"
if [[ -z "$PUBLIC_HOST" ]]; then
  if command -v hostname >/dev/null 2>&1; then
    PUBLIC_HOST="$(hostname -I 2>/dev/null | awk '{print $1}')"
  fi
fi
if [[ -z "$PUBLIC_HOST" ]]; then
  PUBLIC_HOST="localhost"
fi

export NEXT_PUBLIC_API_BASE_URL="${NEXT_PUBLIC_API_BASE_URL:-http://${PUBLIC_HOST}:8080/api}"
export NEXT_BASE_PATH="${NEXT_BASE_PATH:-}"

print_info "Using API base URL for frontend build: ${NEXT_PUBLIC_API_BASE_URL}"
print_info "Stopping previous containers (if any)..."
docker compose down || true

print_info "Building and starting backend + frontend + postgres..."
docker compose up -d --build

print_info "Waiting for backend readiness..."
for _ in {1..60}; do
  if curl -fsS "http://localhost:8080/actuator/health/readiness" >/dev/null 2>&1; then
    print_success "Backend is ready."
    break
  fi
  sleep 2
done

print_info "Waiting for frontend readiness..."
for _ in {1..60}; do
  if curl -fsS "http://localhost:3000" >/dev/null 2>&1; then
    print_success "Frontend is ready."
    break
  fi
  sleep 2
done

print_header "Deployment Complete"
print_success "Frontend: http://${PUBLIC_HOST}:3000"
print_success "Backend:  http://${PUBLIC_HOST}:8080"
print_info "Backend health: http://${PUBLIC_HOST}:8080/actuator/health"
print_info "View logs: docker compose logs -f app frontend"

