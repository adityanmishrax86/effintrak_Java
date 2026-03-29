# effinTrak Development Startup Guide

This guide explains how to quickly start the entire effinTrak development environment (backend + frontend) with a single command.

## Quick Start

### macOS / Linux

```bash
./start-dev.sh
```

This will:
- Start the **Backend** (Spring Boot) on `http://localhost:8080`
- Start the **Frontend** (Next.js) on `http://localhost:3000`
- Monitor both processes and restart if they crash
- Display useful URLs and log locations

### Windows

Double-click the `start-dev.bat` file or run:

```cmd
start-dev.bat
```

This will open two command windows:
- One for the **Backend** (Spring Boot)
- One for the **Frontend** (Next.js)

## Service URLs

Once both services are running:

| Service | URL |
|---------|-----|
| **Frontend** | http://localhost:3000 |
| **Backend API** | http://localhost:8080 |
| **API Documentation** | http://localhost:8080/v3/api-docs |
| **Backend Health Check** | http://localhost:8080/actuator/health |

## Accessing the Application

1. Open your browser and go to **http://localhost:3000**
2. You'll be redirected to the login page
3. Default test credentials (if configured):
   - Check your database or backend logs for test user credentials
   - Or create a new account via the register page

## Configuration

### Frontend Environment Variables

The frontend requires the `NEXT_PUBLIC_API_BASE_URL` environment variable to know where the backend API is located.

**For Local Development:**
- A `.env.local` file has been created automatically with the correct settings
- Backend URL: `http://localhost:8080/api`
- No additional configuration needed!

**If you need to change the backend URL:**
1. Edit `frontend/.env.local`
2. Change `NEXT_PUBLIC_API_BASE_URL` to your backend URL
3. Restart the frontend development server (Ctrl+C and run `./start-dev.sh` again)

**Example `.env.local`:**
```
NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/api
```

### Accessing the Application

### Dashboard Pages (13 total)
- ✅ **Dashboard** - Overview with transactions
- ✅ **Expenses** - Track expenses with categories
- ✅ **Incomes** - Track income sources
- ✅ **Transfers** - Internal money transfers
- ✅ **Accounts** - Bank account management
- ✅ **Chat** - AI-powered financial assistant
- ✅ **Budgets** - Budget creation and tracking with edit functionality
- ✅ **Savings** - Savings goals with progress tracking and edit functionality
- ✅ **Subscriptions** - Recurring subscription tracking with edit functionality
- ✅ **Credits** - Credit card bill tracking with edit functionality
- ✅ **Recurring** - Automated recurring transactions with edit functionality
- ✅ **Reports** - Analytics dashboard with monthly analysis and trends
- ✅ **Settings** - Account settings and logout

### Features

#### Full CRUD Operations
- Create, Read, Update, Delete for all financial data
- Inline edit functionality for quick updates
- Delete confirmations to prevent accidents

#### Analytics & Reports
- Monthly financial summaries
- Top spending categories
- 12-month trends
- Key financial insights
- Budget vs. actual comparisons

#### Authentication
- User registration and login
- JWT token-based authentication
- Automatic token refresh
- Secure logout

#### User Interface
- Responsive design for mobile and desktop
- Dark/light theme ready
- Loading skeletons for better UX
- Real-time form validation
- Progress indicators

## Stopping the Services

### macOS / Linux
Press `Ctrl+C` in the terminal where `start-dev.sh` is running. The script will automatically:
- Stop both the backend and frontend
- Clean up temporary files
- Display shutdown confirmation

### Windows
- Close the **Backend** window to stop the Spring Boot server
- Close the **Frontend** window to stop the Next.js server
- Or close both windows to stop everything

## Troubleshooting

### Port Already in Use

If you see a "Address already in use" error:

**macOS / Linux:**
```bash
# Kill process on port 8080 (backend)
lsof -ti:8080 | xargs kill -9

# Kill process on port 3000 (frontend)
lsof -ti:3000 | xargs kill -9
```

**Windows:**
```cmd
# Kill process on port 8080
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Kill process on port 3000
netstat -ano | findstr :3000
taskkill /PID <PID> /F
```

### Backend Not Starting

Check the logs:
```bash
tail -f /tmp/effintrak-backend.log
```

Make sure:
- Java is installed: `java -version`
- Maven is installed: `mvn -version`
- Database is running and accessible
- Port 8080 is not in use

### Frontend Not Starting

The script automatically installs dependencies via `npm install` on first run. If you're still seeing errors:

```bash
cd frontend
npm install
npm run dev
```

### Services Keep Dying

Check the log files:
```bash
# Backend logs
tail -f /tmp/effintrak-backend.log

# Frontend logs
tail -f /tmp/effintrak-frontend.log
```

## Manual Startup (Alternative)

If the startup script doesn't work, you can start the services manually:

### Terminal 1 - Backend
```bash
./mvnw spring-boot:run
```

### Terminal 2 - Frontend
```bash
cd frontend
npm run dev
```

## Configuration

### Changing Ports

Edit `start-dev.sh` (or `start-dev.bat` for Windows) and modify:
```bash
BACKEND_PORT=8080    # Change to your preferred port
FRONTEND_PORT=3000   # Change to your preferred port
```

### Environment Variables

Check `.env` files in the frontend and backend directories for configuration:
- Backend: `src/main/resources/application.properties`
- Frontend: `frontend/.env.local` (if exists)

## Development Workflow

1. **Start the environment:** `./start-dev.sh`
2. **Open frontend:** http://localhost:3000
3. **Make changes** to code
4. **Auto-reload:** 
   - Backend: Auto-reloads on file changes (Spring Dev Tools)
   - Frontend: Auto-reloads on file changes (Next.js Fast Refresh)
5. **Refresh browser** to see frontend changes
6. **Stop with:** `Ctrl+C` (macOS/Linux) or close windows (Windows)

## Next Steps

After starting the application:

1. **Register a new user** at http://localhost:3000/register
2. **Complete the setup wizard** to configure your financial profile
3. **Start tracking** expenses, incomes, and budgets
4. **Try the AI Chat** feature for financial insights
5. **Check Reports** for analytics and trends

## API Development

For backend API development:
- Swagger/OpenAPI docs: http://localhost:8080/swagger-ui.html
- API spec: http://localhost:8080/v3/api-docs
- API spec (YAML): http://localhost:8080/v3/api-docs.yaml

## Frontend Development

For frontend development:
- Hot reload enabled
- TypeScript for type safety
- ESLint for code quality
- Tailwind CSS for styling

## Support

If you encounter issues:
1. Check terminal output and logs
2. Verify ports are available
3. Ensure all dependencies are installed
4. Check GitHub issues or ask for help

---

**Happy developing! 🚀**
