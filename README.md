# Selenium Data-Driven Test Automation Framework

A Java and Selenium WebDriver framework for browser-based UI testing. It uses TestNG for test execution and supports external test data from Excel and JSON, with application configuration read from a Java properties file. The current example tests exercise the login and PIM (employee management) workflows on the OrangeHRM demo site.

> **Demo environment:** The configured application is a public demo, not a private or production test environment. Demo data and availability can change. Use only approved test accounts and non-sensitive data.

## Features

- Selenium WebDriver browser automation with Chrome.
- TestNG test execution, suite configuration, and `@DataProvider`.
- Excel-backed login cases, with one test invocation per data row.
- JSON-backed employee details for the PIM example.
- Properties-based application URL configuration.
- Page Object Model classes for login and PIM interactions.
- Shared browser setup, waits, and reusable UI helpers.
- TestNG listeners for retry handling and ExtentReports.
- Timestamped HTML reports and screenshot capture.
- Maven dependency management and test execution.

## Technology stack

| Component | Purpose |
| --- | --- |
| Java 21 | Framework and test implementation |
| Maven | Build and dependency management |
| Selenium WebDriver 4.21.0 | Browser automation |
| TestNG 7.9.0 | Test framework and data providers |
| Apache POI | Reading `.xlsx` workbooks |
| Jackson and `org.json` | JSON parsing support |
| OpenCSV | CSV utility support |
| ExtentReports | HTML execution reporting |
| Chrome | Browser under test |

Dependency versions are declared in [`pom.xml`](./pom.xml).

## Project layout

```text
.
├── README.md
├── pom.xml
├── regressionSuite.xml
└── src
    ├── main
    │   └── java/FTH
    │       ├── Abstractcomponent/AbstractComponent.java
    │       ├── DataProvider/DataProviders.java
    │       ├── PageObject/
    │       │   ├── LoginPage.java
    │       │   └── PIMPage.java
    │       ├── Util/
    │       │   ├── CSVReaderUtil.java
    │       │   ├── ExcelReaderUtil.java
    │       │   ├── ExtendReports.java
    │       │   ├── ExtentReportListener.java
    │       │   ├── RetryAnalyzer.java
    │       │   └── RetryListener.java
    │       └── resource/
    │           ├── extentReport.css
    │           └── GlobalData.properties
    └── test
        └── java/FTH
            ├── BaseComponent/BaseClass.java
            ├── Data/
            │   ├── EmployeeData.json
            │   └── LoginTestData.xlsx
            └── Test/
                ├── TestLoginPage.java
                └── TestPIMPage.java
```

### Key components

- **`BaseClass`** initializes Chrome, loads the application URL, and provides shared setup for test classes.
- **`AbstractComponent`** contains reusable browser helpers and methods for reading properties and JSON data.
- **`LoginPage` and `PIMPage`** encapsulate page elements and user actions.
- **`DataProviders`** converts rows from the login Excel workbook into TestNG `Object[][]` data.
- **`ExcelReaderUtil`** provides workbook, sheet, row, and cell-reading helpers.
- **`regressionSuite.xml`** lists the test classes and registers the retry and ExtentReports listeners.

## Data sources and current usage

### Excel: login test cases

The `getData` TestNG data provider reads:

```text
src/test/java/FTH/Data/LoginTestData.xlsx
```

It reads the `Sheet1` worksheet, skips the first row, and supplies each remaining row to `TestLoginPage.testLoginPage`. Keep the data columns in this order to match the test method parameters:

| Column | Test parameter | Example values |
| --- | --- | --- |
| 1 | `username` | A demo username |
| 2 | `password` | A demo password |
| 3 | `testcase` | `valid` or `invalid` |

Each data row produces a separate TestNG test invocation. Add cases as new rows beneath the header. The provider currently uses the column position rather than looking up values by header name.

### JSON: employee details

The PIM example reads:

```text
src/test/java/FTH/Data/EmployeeData.json
```

The current file is a JSON array of employee objects. Each object should include the fields consumed by `PIMPage.enterEmployeeDetails`:

```json
[
  {
    "firstName": "Test",
    "middleName": "A",
    "lastName": "Employee"
  }
]
```

`AbstractComponent.getJsonData` parses this structure as a list of string-keyed maps. The PIM page currently reads the JSON file directly; it is not supplied through a TestNG data provider.

### Properties: application configuration

The application URL is configured in:

```text
src/main/java/FTH/resource/GlobalData.properties
```

Current property:

```properties
url=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
```

`BaseClass` reads the `url` property before navigating the browser. The current implementation loads this file from a path relative to the project root, so run Maven from the repository root. Additional properties can be added as needed, but the current tests only consume `url`.

### Data-driven scope

The framework contains reusable Excel and JSON readers. In the current tests, Excel is connected to TestNG through `@DataProvider` for login scenarios, while JSON supplies the PIM employee details. Properties configure the target URL. The existing CSV reader is a utility and is not currently used by these test cases.

## Prerequisites

1. Java Development Kit (JDK) 21.
2. Apache Maven 3.x.
3. Google Chrome installed on the machine.
4. Network access to the configured demo site and, when needed, to Selenium Manager for browser-driver resolution.

Check the installed tools:

```bash
java -version
mvn -version
```

Selenium Manager is used by Selenium to locate or resolve a compatible Chrome driver. If browser startup fails, check the Chrome installation, network access, and Selenium/Maven error output.

## Setup

Clone the repository and change into its root directory:

```bash
git clone <repository-url>
cd SeleniumDataDriven
```

Review the configured URL and test data before running. For another environment, update `GlobalData.properties` and use test data and credentials authorized for that environment. Do not commit production credentials, personal information, or other secrets.

## Running tests

Run the TestNG suite declared in `regressionSuite.xml`:

```bash
mvn clean test "-Dsurefire.suiteXmlFiles=regressionSuite.xml"
```

The quotes make the command work in Windows PowerShell as well as common shells.

Run a single test class through Maven Surefire:

```bash
mvn "-Dtest=TestLoginPage" test
mvn "-Dtest=TestPIMPage" test
```

Run all tests discovered by Maven's default naming patterns:

```bash
mvn clean test
```

Use the suite command when you want the listeners configured in `regressionSuite.xml` to be applied. The suite currently includes `TestLoginPage` and `TestPIMPage`.

## Reports and screenshots

- Maven Surefire reports: `target/surefire-reports/`
- Timestamped ExtentReports HTML: `test-output/ExtentReport_<timestamp>.html`
- Screenshots: `test-output/screenshots/`

Open the newest timestamped ExtentReports file to review its execution details. Surefire also writes its own reports under `target/surefire-reports/`; these are separate from the timestamped ExtentReports output. Generated reports and screenshots may be removed between runs.

The suite registers `RetryListener` and `ExtentReportListener`. The retry analyzer is configured for up to three retries per failed test. The ExtentReports listener records test outcomes and attempts to capture screenshots when a test passes or fails.

## Adding a data-driven test

1. Add or update a test method in a class under `src/test/java`.
2. Add a `@DataProvider` method that returns `Object[][]`, or reuse `DataProviders.getData` for the login workbook.
3. For JSON-backed cases, read structured data with the JSON helper in `AbstractComponent`.
4. Keep test inputs separate from page interaction logic in the page object.
5. Add the test class to `regressionSuite.xml` if it should run as part of the configured suite.
6. Run the suite and review both Surefire and ExtentReports output.

## Troubleshooting

| Symptom | Checks |
| --- | --- |
| Excel file not found | Run Maven from the repository root; confirm `src/test/java/FTH/Data/LoginTestData.xlsx` exists and the path in `DataProviders` matches. |
| Excel sheet or cell errors | Confirm the worksheet is named `Sheet1`, the first row is the header, and each data row matches the test method's parameter order. |
| JSON parsing or missing-field error | Validate `EmployeeData.json` as a JSON array and include `firstName`, `middleName`, and `lastName` for each employee. |
| Browser or driver startup error | Confirm Chrome is installed and that Selenium Manager can resolve the compatible driver. |
| Test not included in suite | Confirm its fully qualified class name is listed in `regressionSuite.xml`, then run with the suite command above. |
| Old or unrelated report appears | Check the report's timestamp. Surefire reports are under `target/surefire-reports/`; ExtentReports files are timestamped under `test-output/`. |

## Notes

- The tests interact with a live public demo application, which may be unavailable or change without notice.
- The PIM test creates employee data in the target application; run it only against an approved environment.
- Current test data paths are repository-relative filesystem paths. Keep the working directory at the project root when launching the tests.
