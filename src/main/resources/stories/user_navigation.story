User Navigation Story

Narrative:
In order to buy products at the store
As a customer
I want to be able to access and navigate in the web store

Scenario: The customer should be able to create an account
Given the customer access the store's home page
When i click the account button
Then i should see the login popup
When i click on the Create New Account link
When i fill the users information on the new account page
Then i should verify if the required fields are filled
When i click on the 'Agree to terms and conditions' checkbox
Then i should verify if the 'Agree to terms and conditions' checkbox is selected
When I click on the register button
Then I should verify if the user's logged in

Scenario: The customer should be able to create an account and make an order
Given the customer access the store's home page
When i click the account button
Then i should see the login popup
When i click on the Create New Account link
When i fill the users information on the new account page
Then i should verify if the required fields are filled
When i click on the 'Agree to terms and conditions' checkbox
Then i should verify if the 'Agree to terms and conditions' checkbox is selected
When I click on the register button
Then I should verify if the user's logged in
When i click on any of the products displayed at the popular items section
Then the product page should load
When i select a new quantity of 2 items

