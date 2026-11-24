# Selenium + Java + TestNG + Maven + UI + Automation

A scalable Selenium WebDriver automation framework developed using Java, TestNG, and Maven. The framework follows Page Object Model design principles and demonstrates reusable components, data-driven testing, test execution management, reporting, and failure handling.

## Technology stack

- Java 21
- Maven
- Selenium WebDriver 4.21.0
- TestNG 7.9.0
- Jackson for JSON test data
- ExtentReports for HTML execution reports
- Chrome browser

## Automation Framework structure

```text
regressionSuite/
├── pom.xml
├── regressionSuite.xml
└── src/
    ├── main/
    │   └── java/FTH/
    │       ├── Abstractcomponent/     # Shared browser and data helper methods
    │       ├── PageObject/            # Page objects and page-level actions
    │       ├── Util/                  # CSV, reporting, and retry utilities/listeners
    │       └── resource/
    │           └── GlobalData.properties # Application URLs
    └── test/
        └── java/FTH/
            ├── BaseComponent/         # Shared test setup and WebDriver initialization
            ├── Data/                  # JSON test data
            └── Test/                  # TestNG test classes


### Main files

- `pom.xml` declares project dependencies and Java 21 compiler settings.
- `regressionSuite.xml` selects `FTH.Test.TestRegistration` and registers the retry and ExtentReports listeners.
- `src/main/java/FTH/resource/GlobalData.properties` holds the application and registration URLs.
- `src/main/java/FTH/PageObject/Registration.java` contains page locators and registration actions.
- `src/test/java/FTH/BaseComponent/BaseClass.java` starts Chrome, loads the configured URL, and shares the WebDriver with TestNG listeners.
- `src/test/java/FTH/Test/TestRegistration.java` contains the registration page and user registration tests.
- `src/test/java/FTH/Data/RegisterData.json` contains the registration test data.

## Prerequisites

1. Install a Java 21 JDK and configure `JAVA_HOME` and `PATH`.
2. Install Maven 3.x and verify that `mvn` is available on `PATH`.
3. Install Google Chrome.
4. Ensure the machine can access `https://demo.nopcommerce.com/`.
5. Use a unique email address for each successful registration run. The test submits data to a live public demo application, not a local test server.

Selenium 4.21.0 uses Selenium Manager to resolve the compatible Chrome driver when needed. If driver setup fails, check your Selenium Manager network access, Chrome installation, and browser compatibility.

## Before running

Review `src/main/java/FTH/resource/GlobalData.properties` and set `url` and `regUrl` to the intended test environment. The current values point to the public nopCommerce demo site.

Update `src/test/java/FTH/Data/RegisterData.json` with test-owned data before running the registration flow. In particular:

- Use an email address controlled by your test team and not already registered on the target environment.
- Do not commit real personal information, production credentials, or other secrets to GitHub.
- Keep the JSON property names expected by the test: `gender`, `firstName`, `lastName`, `email`, `company`, `password`, and `confirmPassword`.

The test data currently in the repository includes a personal-looking name and email address. Replace it with approved test data before publishing this repository.

## Setup

1. Clone the repository and open a terminal in its root directory:

   ```bash
   git clone <repository-url>
   cd regressionSuite
   ```

2. Confirm Java and Maven are available:

   ```bash
   java -version
   mvn -version
   ```

   Java should report version 21.

3. Configure the application URLs and registration data as described above.

4. Run the TestNG suite:

   ```bash
   mvn clean test -Dsurefire.suiteXmlFiles=regressionSuite.xml
   ```

   Maven downloads the project dependencies on the first run, compiles the code, and launches the configured suite in Chrome.

To run tests using Maven's normal test discovery instead of the explicit TestNG suite file, run:

```bash
mvn clean test
```

## Execution output

- TestNG/Surefire results are written under `target/surefire-reports/`.
- The ExtentReports listener writes a timestamped HTML report under `test-output/`.
- Screenshots captured for failed tests are written under `test-output/screenshots/`.

These are generated artifacts and should not be committed unless the project intentionally adopts a policy for storing test reports.

The suite configures a retry listener that retries failed tests up to three times. Retries do not replace investigation of the original failure; check the report and application state when a test remains failing.
