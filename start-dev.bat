@echo off
REM effinTrak Development Server Startup Script for Windows
REM This script starts both the backend (Spring Boot) and frontend (Next.js) servers

setlocal enabledelayedexpansion

set BACKEND_PORT=8080
set FRONTEND_PORT=3000

echo.
echo ========================================
echo Starting effinTrak Development Environment
echo ========================================
echo.

REM Check if ports are in use and warn user
echo Checking ports...
netstat -ano | findstr :%BACKEND_PORT% >nul && (
    echo WARNING: Port %BACKEND_PORT% is already in use. Please close other applications or change the port.
    echo.
)
netstat -ano | findstr :%FRONTEND_PORT% >nul && (
    echo WARNING: Port %FRONTEND_PORT% is already in use. Please close other applications or change the port.
    echo.
)

REM Get the directory where this script is located
cd /d "%~dp0"

REM Start Backend
echo Starting backend server on port %BACKEND_PORT%...
if exist mvnw.cmd (
    start "effinTrak Backend" cmd /k "mvnw.cmd spring-boot:run"
) else if exist mvnw (
    start "effinTrak Backend" cmd /k "./mvnw spring-boot:run"
) else (
    echo Maven not found! Please install Maven.
    exit /b 1
)

REM Wait for backend to start
echo Waiting for backend to start (up to 30 seconds)...
timeout /t 5 /nobreak

setlocal
set RETRY=0
:BACKEND_CHECK
set /a RETRY=%RETRY%+1
if %RETRY% gtr 30 (
    echo Backend failed to start. Check the backend window for errors.
    goto SKIP_FRONTEND_CHECK
)
curl -s http://localhost:%BACKEND_PORT%/actuator/health >nul 2>&1
if errorlevel 1 (
    timeout /t 1 /nobreak
    goto BACKEND_CHECK
)
echo Backend is ready!

:SKIP_FRONTEND_CHECK

REM Start Frontend
echo Starting frontend server on port %FRONTEND_PORT%...
if exist frontend (
    cd frontend
    if not exist node_modules (
        echo Installing frontend dependencies...
        call npm install
    )
    start "effinTrak Frontend" cmd /k "npm run dev"
    cd ..
) else (
    echo Frontend directory not found!
    exit /b 1
)

REM Print summary
echo.
echo ========================================
echo effinTrak Services Running
echo ========================================
echo.
echo Backend URL: http://localhost:%BACKEND_PORT%
echo Frontend URL: http://localhost:%FRONTEND_PORT%
echo.
echo API Documentation: http://localhost:%BACKEND_PORT%/v3/api-docs
echo.
echo Backend window: "effinTrak Backend"
echo Frontend window: "effinTrak Frontend"
echo.
echo Close either window to stop that service, or close both to stop all services.
echo.

endlocal
