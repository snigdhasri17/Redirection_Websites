Feature:


Scenario:Compare product price across websites

Given user launches the browser
And user searches "iphone 15" on Amazon
##And user searches "iphone 15" on Filpcart
And user searches "iphone 15" on croma
When user compares price on all websites
Then user Should navigate to lowest price site 
And close browser

##Scenario:Represtent the result sites in Extent Report



