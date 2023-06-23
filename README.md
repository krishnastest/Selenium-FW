#Selenium Project From Basics

##Project Description
This project contains the code for an end to end selenium test framework

------

Steps to write a selenium test case:
1. Instantiate the webdriver object for the browser you want ti use.
2. Navigate to the webpage you want to test.
3. Locate the element on the webpage you need to interact with.
4. Ensure the browser is in correct state to interact with the element.
5. Perform the action on the element.
6. Record the test results.
7. Quit the test or the webdriver.

Types of Locators in Selenium:
1. Id
2. Name
3. CSS
4. Class Name
5. Tag Name
6. Link Text
7. Partial Link Text
8. XPath


###Project Details
WithDriverTest file contains setting up the tests using the previous method of adding a chromedriver in the resource folder and using selenium to setup the driver.

How to write CSS selectors:
1. Classname will be written as ".nameofclass"
2. id will be written as "#idvalue".
3. Any other attribute will be written as "[attribute = 'value']"
4. For compound class name or class which contain multiple values, use ".btn_action.checkout_button" format, a dot between the two names.
5. 