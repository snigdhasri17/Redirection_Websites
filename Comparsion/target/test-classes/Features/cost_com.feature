Feature:


Scenario:Compare product price across websites

Given user launches the browser
And user searches "iphone 15" on Amazon
And user searches "iphone 15" on Filpcart
And user searches "iphone 15" on croma
And user compares price on all websites
Then user Should navigate to lowest price site and add to cart
And close browser