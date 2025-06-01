<h2 align="center">Songsterr Test Automation Project</h2>

## <h>Description</h>

The purpose is to create a complete test automation project in Java that showcases the usage of a common libraries.\
The project includes various `UI` and `API` Java tests for _[songsterr.com](www.songsterr.com)_ web application.

### Key Features:

- `Page Object` design pattern
- Parametrized test cases
- `Owner` configurations
- `Lombok` for API test models
- Data Classes for test data
- API specs
- Allure reports

## <h>Tools</h>

Tests are written using `Selenide` Java framework.

| Category       | Technologies                          |
|----------------|---------------------------------------|
| Test Framework | Selenide (Selenium WebDriver wrapper) |
| Build Tool     | Gradle                                |
| Test Runner    | JUnit 5                               |
| API Testing    | REST Assured                          |
| Reporting      | Allure Report                         |
| Utilities      | Lombok, Owner                         |

## <h>Launch</h>

### Prerequisites

- Java 17
- Gradle 7+
- Configured `src/test/resources/auth.properties` `src/test/resources/browser.properties` config files

### Basic Command

```bash
gradle clean test -Dtag=<testType> -Dbrowser=<browser>
```

### Specifying auth properties

```bash
gradle clean test -Dauth.email=<email> -Dauth.username=<username> -Dauth.password=<password>
```

### Generating Report

```bash
allure serve build/allure-results
```





