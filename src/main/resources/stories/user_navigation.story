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
Meta: @singleTest
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
Then i should verify if the quantity is 2
When i click on the add to cart button
Then i should verify if the selected item is in the cart
When i click on the cart
Then i should verify if the product, desired quantity and price are correct
When i click on the checkout button
Then i should verify if the order payment screen has loaded
When i click on the next button
When i select the MasterCredit payment option
When i fill the required fields for payment
Then i should verify if the required fields for the payment are filled
When i click on the pay now button
Then i should verify if the order and tracking number are shown
When i click the account button
When i logout
Then i should be logged out


