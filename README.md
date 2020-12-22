# relayr_task


This project has been build using Java with Maven, jars and plugins used are mentioned in pom.xml file which is located on project root folder. Aim of this project is to automate few scnearios of this website - https://imdb.com/


Scenarios Covered in this framework:

    a) To verify IMDB search is working properly

    b) To verify login functionality of IMDB

# Project Structure:

 # i) Packages

a) src/main/java/pageObject: This package has classes which contain all the locators and their corrosponding methods for doing various operation on IMDB Webelments

b) src/main/java/utilities: This package has classes where custom made java methods are present which we will use for perfoming  operations such as Reading Excel, TakingScreenshots, Reading Properties etc.

c) src/main/java/resources: This package has all the resources which will be required to run test cases, currently it contains webdriver and configuration files(.properties and .xml)  which are being used in this project.

d) src/test/java/tests: This package will contain all test classes related to IMDB. 

e) src/test/java/baseTests: This package contain base class which contains all the initialization methods, before and after TestNG methods which are required to run test cases in IMDBTests.

f) src/test/java/testData- This package will have all the test data required for running test cases, currently this package has data in   Excel format


# ii) CustomTestOutput

a) Logs- Logs will be created under log folder in customTestOutput folder(located in root project foler) in .log and .html format.
		Path: src/CustomTestOutput/log

b) Automation Test Report: Extent-reports will be generated in extent-reports folder which is present under customtestOutput folder
		Path: src/CustomTestOutput/extent-reports

c) Screenshots: Screenshot of failed test cases will get captured in FailedCase_ScreenShots folder.
		Path: src/CustomTestOutput/FailedCase_ScreenShots



# Pre-Requisite to run this project

    OS: Windows 10 
    Java Version:Java 10.0
    IDE: Eclipse IDE 
    Browser: Google Chrome or Mozilla Firefox Latest Version

# How to run this project ?

    Clone or download the repository
    Open the project in eclipse IDE
    Import the Maven project and then run pom.xml file
    Run the testCase java file "AzentTests.java" present in path : src/test/java/tests/AzentTests
    Run as TestNG tests.
