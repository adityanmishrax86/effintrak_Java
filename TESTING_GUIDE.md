# Testing Guide - Running Tests from Scratch

This guide provides step-by-step instructions for setting up and running tests for the EffinTrak backend application.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Initial Setup](#initial-setup)
3. [Running Tests](#running-tests)
4. [Test Types](#test-types)
5. [IDE Setup](#ide-setup)
6. [Troubleshooting](#troubleshooting)
7. [Best Practices](#best-practices)

---

## Prerequisites

### 1. Java Development Kit (JDK)

**Required Version:** JDK 21 or higher

**Check Installation:**
```bash
java -version
```

**Expected Output:**
```
openjdk version "21.0.x" 2024-xx-xx
OpenJDK Runtime Environment (build 21.0.x+xx-xx)
OpenJDK 64-Bit Server VM (build 21.0.x+xx-xx, mixed mode, sharing)
```

**Installation:**
- **Windows:** Download from [Oracle](https://www.oracle.com/java/technologies/downloads/#java21) or use [Adoptium](https://adoptium.net/)
- **macOS:** `brew install openjdk@21`
- **Linux:** `sudo apt-get install openjdk-21-jdk` (Ubuntu/Debian)

### 2. Maven

**Required Version:** Maven 3.6+ (or use Maven Wrapper included in project)

**Check Installation:**
```bash
mvn -version
```

**Expected Output:**
```
Apache Maven 3.9.x
Maven home: /path/to/maven
Java version: 21.0.x
```

**Installation:**
- **Windows:** Download from [Apache Maven](https://maven.apache.org/download.cgi)
- **macOS:** `brew install maven`
- **Linux:** `sudo apt-get install maven`

**Note:** The project includes Maven Wrapper (`mvnw`/`mvnw.cmd`), so Maven installation is optional.

### 3. Docker Desktop

**Required for:** Full integration tests (Testcontainers)

**Check Installation:**
```bash
docker --version
docker-compose --version
```

**Expected Output:**
```
Docker version 24.0.x, build xxxxx
docker-compose version 2.20.x
```

**Installation:**
- **Windows/macOS:** Download [Docker Desktop](https://www.docker.com/products/docker-desktop/)
- **Linux:** Follow [Docker Engine installation guide](https://docs.docker.com/engine/install/)

**Verify Docker is Running:**
```bash
docker ps
```

**Expected Output:**
```
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
```

If you see an error, start Docker Desktop.

### 4. Git (Optional but Recommended)

**Check Installation:**
```bash
git --version
```

**Installation:**
- **Windows:** Download from [Git for Windows](https://git-scm.com/download/win)
- **macOS:** `brew install git`
- **Linux:** `sudo apt-get install git`

---

## Initial Setup

### Step 1: Clone or Navigate to Project

If you have the project in a repository:
```bash
git clone <repository-url>
cd effintrak_Java
```

Or navigate to your project directory:
```bash
cd /path/to/effintrak_Java
```

### Step 2: Verify Project Structure

Ensure you have the following structure:
```
effintrak_Java/
├── pom.xml
├── mvnw (or mvnw.cmd on Windows)
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       └── resources/
│           └── application-test.properties
└── ...
```

### Step 3: Verify Maven Wrapper (Optional but Recommended)

**Windows:**
```cmd
mvnw.cmd --version
```

**Linux/macOS:**
```bash
chmod +x mvnw
./mvnw --version
```

### Step 4: Download Dependencies

**Windows:**
```cmd
mvnw.cmd dependency:resolve
```

**Linux/macOS:**
```bash
./mvnw dependency:resolve
```

This downloads all Maven dependencies without running tests.

### Step 5: Verify Docker is Running

**Start Docker Desktop:**
- Windows: Launch Docker Desktop from Start Menu
- macOS: Launch Docker Desktop from Applications
- Linux: `sudo systemctl start docker`

**Verify:**
```bash
docker run hello-world
```

**Expected Output:**
```
Hello from Docker!
This message shows that your installation appears to be working correctly.
```

---

## Running Tests

### Method 1: Using Maven Wrapper (Recommended)

#### Run All Tests

**Windows:**
```cmd
mvnw.cmd clean test
```

**Linux/macOS:**
```bash
./mvnw clean test
```

This will:
1. Clean previous build artifacts
2. Compile source and test code
3. Run all unit tests
4. Run all integration tests
5. Generate test reports

**Expected Output:**
```
[INFO] Scanning for projects...
[INFO] 
[INFO] Building effinTrak 0.0.1-SNAPSHOT
[INFO] 
[INFO] --- maven-clean-plugin:3.2.0:clean (default-clean) @ effinTrak ---
[INFO] --- maven-resources-plugin:3.3.1:resources (default-resources) @ effinTrak ---
[INFO] --- maven-compiler-plugin:3.11.0:compile (default-compile) @ effinTrak ---
[INFO] --- maven-resources-plugin:3.3.1:testResources (default-testResources) @ effinTrak ---
[INFO] --- maven-compiler-plugin:3.11.0:testCompile (default-testCompile) @ effinTrak ---
[INFO] --- maven-surefire-plugin:3.1.2:test (default-test) @ effinTrak ---
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
...
[INFO] Tests run: 50, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

#### Run Tests Without Clean

**Windows:**
```cmd
mvnw.cmd test
```

**Linux/macOS:**
```bash
./mvnw test
```

### Method 2: Using Maven Directly

If Maven is installed on your system:

```bash
mvn clean test
```

### Method 3: Run Specific Test Categories

#### Unit Tests Only (Fast - No Docker Required)

**Windows:**
```cmd
mvnw.cmd test -Dtest=*ServiceTest
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=*ServiceTest
```

**Tests Included:**
- `UserServiceTest`
- `IncomeServiceTest`
- `ExpenseServiceTest`
- `BankAccountServiceTest`
- `CategoryServiceTest`

#### Controller Integration Tests (Fast - Mocked)

**Windows:**
```cmd
mvnw.cmd test -Dtest=*ControllerIntegrationTest
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=*ControllerIntegrationTest
```

**Tests Included:**
- `UserControllerIntegrationTest`
- `IncomeControllerIntegrationTest`
- `ExpenseControllerIntegrationTest`
- All other controller integration tests

#### Full Integration Tests (Requires Docker)

**Windows:**
```cmd
mvnw.cmd test -Dtest=*FlowIntegrationTest
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=*FlowIntegrationTest
```

**Tests Included:**
- `UserFlowIntegrationTest`
- `TransactionFlowIntegrationTest`
- `ReportingFlowIntegrationTest`

### Method 4: Run Specific Test Classes

#### Single Test Class

**Windows:**
```cmd
mvnw.cmd test -Dtest=UserServiceTest
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=UserServiceTest
```

#### Multiple Test Classes

**Windows:**
```cmd
mvnw.cmd test -Dtest=UserServiceTest,IncomeServiceTest
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=UserServiceTest,IncomeServiceTest
```

#### Test Class with Pattern

**Windows:**
```cmd
mvnw.cmd test -Dtest=*User*Test
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=*User*Test
```

### Method 5: Run Specific Test Methods

**Windows:**
```cmd
mvnw.cmd test -Dtest=UserServiceTest#registerUser_WhenUserDoesNotExist_ShouldSaveUser
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=UserServiceTest#registerUser_WhenUserDoesNotExist_ShouldSaveUser
```

### Method 6: Run Tests in a Package

**Windows:**
```cmd
mvnw.cmd test -Dtest=com.azaxxc.effintrakj.effinTrak.users.service.*Test
```

**Linux/macOS:**
```bash
./mvnw test -Dtest=com.azaxxc.effintrakj.effinTrak.users.service.*Test
```

---

## Test Types

### 1. Unit Tests

**Location:** `src/test/java/.../service/*Test.java`

**Characteristics:**
- Fast execution (milliseconds)
- No external dependencies
- Uses mocks (Mockito)
- No Docker required

**Examples:**
- `UserServiceTest.java`
- `IncomeServiceTest.java`
- `ExpenseServiceTest.java`
- `BankAccountServiceTest.java`
- `CategoryServiceTest.java`

**Run Command:**
```bash
./mvnw test -Dtest=*ServiceTest
```

**Expected Duration:** 5-10 seconds

### 2. Controller Integration Tests

**Location:** `src/test/java/.../controller/*IntegrationTest.java`

**Characteristics:**
- Fast execution (seconds)
- Uses `@WebMvcTest` (Spring Mock MVC)
- Mocks services
- No Docker required

**Examples:**
- `UserControllerIntegrationTest.java`
- `IncomeControllerIntegrationTest.java`
- `ExpenseControllerIntegrationTest.java`
- All 17 controller integration tests

**Run Command:**
```bash
./mvnw test -Dtest=*ControllerIntegrationTest
```

**Expected Duration:** 10-20 seconds

### 3. Full Integration Tests

**Location:** `src/test/java/.../integration/*IntegrationTest.java`

**Characteristics:**
- Slower execution (30-60 seconds)
- Uses `@SpringBootTest` with Testcontainers
- Real PostgreSQL database in Docker
- Tests complete flows

**Examples:**
- `UserFlowIntegrationTest.java`
- `TransactionFlowIntegrationTest.java`
- `ReportingFlowIntegrationTest.java`

**Run Command:**
```bash
./mvnw test -Dtest=*FlowIntegrationTest
```

**Expected Duration:** 30-60 seconds (first run may be slower due to Docker image download)

**Requirements:**
- Docker Desktop must be running
- Sufficient disk space for PostgreSQL Docker image (~200MB)

---

## IDE Setup

### IntelliJ IDEA

#### 1. Open Project

1. File → Open
2. Select `effintrak_Java` directory
3. Wait for Maven import to complete

#### 2. Configure JDK

1. File → Project Structure → Project
2. Set Project SDK to JDK 21
3. Set Project language level to 21

#### 3. Run Tests

**Run All Tests:**
- Right-click on `src/test/java` → Run 'All Tests'

**Run Test Class:**
- Right-click on test class → Run 'TestClassName'

**Run Test Method:**
- Click green arrow next to test method
- Or press `Ctrl+Shift+F10` (Windows/Linux) / `Cmd+Shift+R` (Mac)

**Run with Coverage:**
- Right-click → Run 'TestClassName' with Coverage
- View coverage report: Run → Show Coverage Data

#### 4. Configure Test Runner

1. Run → Edit Configurations
2. Add JUnit configuration
3. Test kind: All in package/class/method
4. Working directory: `$MODULE_DIR$`

### Eclipse

#### 1. Import Project

1. File → Import → Maven → Existing Maven Projects
2. Select `effintrak_Java` directory
3. Finish

#### 2. Configure JDK

1. Project → Properties → Java Build Path
2. Libraries → Modulepath → Add Library → JRE System Library
3. Select JDK 21

#### 3. Run Tests

- Right-click on test file → Run As → JUnit Test
- Or use JUnit view: Window → Show View → JUnit

### VS Code

#### 1. Install Extensions

- Java Extension Pack
- Test Runner for Java
- Spring Boot Extension Pack

#### 2. Open Project

1. File → Open Folder
2. Select `effintrak_Java` directory

#### 3. Run Tests

- Click "Run Test" above test method/class
- Or use Command Palette: `Java: Run Tests`
- View test results in Test Explorer

---

## Troubleshooting

### Issue 1: Docker Not Running

**Error:**
```
Could not find a valid Docker environment. Please see logs and check configuration
```

**Solution:**
1. Start Docker Desktop
2. Verify: `docker ps`
3. Wait for Docker to fully start (may take 30-60 seconds)
4. Re-run tests

### Issue 2: Port Already in Use

**Error:**
```
Port 5432 is already in use
```

**Solution:**
**Option A:** Stop local PostgreSQL
```bash
# Windows
net stop postgresql-x64-15

# Linux
sudo systemctl stop postgresql

# macOS
brew services stop postgresql
```

**Option B:** Change test configuration
Edit `src/test/resources/application-test.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/test_effintrak
```

### Issue 3: Out of Memory

**Error:**
```
java.lang.OutOfMemoryError: Java heap space
```

**Solution:**
**Windows:**
```cmd
set MAVEN_OPTS=-Xmx2048m -Xms1024m
mvnw.cmd test
```

**Linux/macOS:**
```bash
export MAVEN_OPTS="-Xmx2048m -Xms1024m"
./mvnw test
```

### Issue 4: Testcontainers Image Download Fails

**Error:**
```
Failed to pull image: postgres:15-alpine
```

**Solution:**
1. Check internet connection
2. Verify Docker can pull images:
   ```bash
   docker pull postgres:15-alpine
   ```
3. If behind proxy, configure Docker proxy settings
4. Re-run tests

### Issue 5: Maven Wrapper Permission Denied (Linux/macOS)

**Error:**
```
Permission denied: ./mvnw
```

**Solution:**
```bash
chmod +x mvnw
./mvnw test
```

### Issue 6: Tests Fail with "Class not found"

**Error:**
```
java.lang.ClassNotFoundException
```

**Solution:**
1. Clean and rebuild:
   ```bash
   ./mvnw clean compile test-compile
   ```
2. Verify dependencies:
   ```bash
   ./mvnw dependency:resolve
   ```

### Issue 7: Spring Context Fails to Load

**Error:**
```
Failed to load ApplicationContext
```

**Solution:**
1. Check `application-test.properties` exists
2. Verify test profile is active
3. Check for missing dependencies in `pom.xml`
4. Review full stack trace for specific error

### Issue 8: JWT Token Generation Fails in Tests

**Error:**
```
JWT token generation failed
```

**Solution:**
- This is usually a configuration issue
- Check `SecurityTestUtils.java` for proper JWT setup
- Verify `JWTUtil` is properly configured

---

## Best Practices

### 1. Run Tests Before Committing

```bash
# Quick check (unit tests only)
./mvnw test -Dtest=*ServiceTest

# Full check before commit
./mvnw clean test
```

### 2. Run Tests in CI/CD Pipeline

Create a `.github/workflows/test.yml` or similar:

```yaml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - name: Start Docker
        run: |
          sudo systemctl start docker
      - name: Run Tests
        run: ./mvnw clean test
```

### 3. Test Execution Order

Run tests in this order for faster feedback:

1. **Unit Tests First** (fastest, no dependencies)
   ```bash
   ./mvnw test -Dtest=*ServiceTest
   ```

2. **Controller Tests** (fast, mocked)
   ```bash
   ./mvnw test -Dtest=*ControllerIntegrationTest
   ```

3. **Full Integration Tests** (slower, requires Docker)
   ```bash
   ./mvnw test -Dtest=*FlowIntegrationTest
   ```

### 4. Parallel Test Execution

Maven Surefire runs tests in parallel by default. To control:

```bash
# Run tests sequentially
./mvnw test -DforkCount=1

# Run with specific thread count
./mvnw test -DforkCount=2 -DreuseForks=true
```

### 5. Test Reports

View test reports after execution:

**Location:** `target/surefire-reports/`

**View HTML Reports:**
```bash
# Generate report
./mvnw surefire-report:report

# Open in browser (Linux/macOS)
open target/site/surefire-report.html

# Windows
start target/site/surefire-report.html
```

### 6. Continuous Testing

Use IDE features for continuous testing:
- IntelliJ: Enable "Toggle Auto-Test" (lightning bolt icon)
- VS Code: Enable "Java: Test Runner" auto-run

---

## Test Execution Examples

### Example 1: Quick Development Cycle

```bash
# 1. Make code changes
# 2. Run only affected unit tests
./mvnw test -Dtest=UserServiceTest

# 3. If unit tests pass, run controller tests
./mvnw test -Dtest=UserControllerIntegrationTest

# 4. Before commit, run full suite
./mvnw clean test
```

### Example 2: Debugging a Failing Test

```bash
# Run specific failing test with verbose output
./mvnw test -Dtest=UserServiceTest#registerUser_WhenUserAlreadyExists_ShouldThrowException -X

# Or run with debug mode
./mvnw test -Dtest=UserServiceTest -Dmaven.surefire.debug
```

### Example 3: Pre-Commit Checklist

```bash
# 1. Clean build
./mvnw clean

# 2. Compile
./mvnw compile

# 3. Run all tests
./mvnw test

# 4. Check test results
cat target/surefire-reports/*.txt
```

### Example 4: CI/CD Pipeline

```bash
#!/bin/bash
set -e  # Exit on error

echo "Starting test execution..."

# Start Docker if not running
if ! docker ps > /dev/null 2>&1; then
    echo "Starting Docker..."
    sudo systemctl start docker
    sleep 10
fi

# Run tests
echo "Running tests..."
./mvnw clean test

# Check results
if [ $? -eq 0 ]; then
    echo "All tests passed!"
    exit 0
else
    echo "Tests failed!"
    exit 1
fi
```

---

## Test Coverage

### Generate Coverage Report

**Using JaCoCo (if configured):**

```bash
./mvnw clean test jacoco:report
```

**View Report:**
- Location: `target/site/jacoco/index.html`
- Open in browser to see coverage metrics

### Coverage Goals

- **Unit Tests:** Aim for 70%+ coverage on service layer
- **Integration Tests:** Cover all API endpoints
- **Full Integration Tests:** Cover critical user flows

---

## Quick Reference

### Common Commands

| Command | Description |
|---------|-------------|
| `./mvnw clean test` | Clean and run all tests |
| `./mvnw test` | Run all tests |
| `./mvnw test -Dtest=*ServiceTest` | Unit tests only |
| `./mvnw test -Dtest=*IntegrationTest` | Integration tests |
| `./mvnw test -Dtest=UserServiceTest` | Specific test class |
| `./mvnw test -DskipTests` | Skip tests (build only) |
| `./mvnw test -Dtest=*Test -DfailIfNoTests=false` | Run tests, don't fail if none found |

### Test File Locations

| Test Type | Location Pattern |
|-----------|-----------------|
| Unit Tests | `src/test/java/.../service/*Test.java` |
| Controller Tests | `src/test/java/.../controller/*IntegrationTest.java` |
| Integration Tests | `src/test/java/.../integration/*IntegrationTest.java` |

### Expected Test Counts

- **Unit Tests:** ~5-10 test classes
- **Controller Tests:** ~17 test classes (one per controller)
- **Integration Tests:** ~3 test classes
- **Total Test Methods:** ~100+ test methods

---

## Additional Resources

- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Testcontainers Documentation](https://www.testcontainers.org/)
- [Spring Boot Testing](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

---

## Getting Help

If you encounter issues:

1. **Check Logs:** Review test output and stack traces
2. **Verify Prerequisites:** Ensure JDK 21, Maven, and Docker are installed
3. **Check Docker:** Ensure Docker Desktop is running
4. **Review Configuration:** Check `application-test.properties`
5. **Clean Build:** Try `./mvnw clean test`

For persistent issues, check:
- Test logs in `target/surefire-reports/`
- Docker logs: `docker ps` and `docker logs <container-id>`
- Maven logs: Add `-X` flag for debug output

---

## Summary

This guide covers:
- ✅ Prerequisites and setup
- ✅ Multiple ways to run tests
- ✅ Different test types and their requirements
- ✅ IDE configuration
- ✅ Troubleshooting common issues
- ✅ Best practices

**Quick Start:**
1. Install JDK 21, Maven, and Docker
2. Start Docker Desktop
3. Run: `./mvnw clean test`
4. View results in console or `target/surefire-reports/`

Happy Testing! 🧪

