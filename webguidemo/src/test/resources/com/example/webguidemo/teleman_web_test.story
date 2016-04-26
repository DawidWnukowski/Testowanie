Scenario: User is trying to login

Given user is on Home page
When user opens Login link and fill input with data
Then login page is shown
Then login failed
When user trying to subscribe
Then subscribe failed