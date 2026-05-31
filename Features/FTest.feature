Feature: As a user
I want to search for Teddy bear on amazon and sort, filter according to the input and add to cart and validate cart.

Background:
Given I am on Amazon home page on "edge"

Scenario: Add to cart flow
When user search for Teddy bear
And user sorts the result according to Customer Review
And user select the Age range between 5 to 7 years old
Then The user adds the first two items in his cart
And user validates the two items in the cart.