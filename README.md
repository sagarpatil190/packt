# PacktPub UI Automation
By [Sagar Patil](https://www.linkedin.com/in/sagarpatil190/)

PacktPub UI Autoation project for test automation covering UI automation testing and cross browser (chrome and firefox) testing.

## Concepts Included

* Cross browser testing
* Shared state across cucumber step definitions
* Dependency injection
* Object Manager for Managing Objects
* Common web page interaction methods
* Externalised test configuration
* Commonly used test utility classes

## Tools

* Maven
* Cucumber-JVM
* TestNG
* Selenium Webdriver
* Cucumber Extent Report
* Git

In order to utilise this project you need to have the following installed locally:

* Maven 3
* Chrome and Chromedriver (UI tests use Chrome by default, can be changed in config)
* FireFox and Geckodriver

## Usage

To run UI automation tests, navigate to `packt` directory and run:

`mvn clean test`

by default test will run on chrome browser, if you wan t run it on firefox then uncomment the test inside `/src/test/resources/testNG.xml` file.

### Object Repository Usage
Object Repository is located inside `/src/test/resources/ObejctRepository.loc`, you can add objects using format `LocatorName:LocatorType=LocatorValue`

## Reporting

Reports are written into  `/target` directories after a successful run.

Extent Report HTML file is written into `/target/cucumber-reports` and In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the html report.