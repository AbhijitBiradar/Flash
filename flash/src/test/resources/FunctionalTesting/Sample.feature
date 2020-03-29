Feature: Automated E2E Test
Description: This is simple Login Test

Scenario: Login Applciation with valid Credentials
Given user is on Login page
When user enter valid user name
And user enter valid password
And user click on Login button
Then application should login successfully
