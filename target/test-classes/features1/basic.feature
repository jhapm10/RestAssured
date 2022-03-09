Feature: Title of your feature

  Scenario Outline: Title of your scenario
 # Outline: Title of your scenario
  # Given I hit the request for get
   And I hit Post Request by hitting Body param as "<id>" and "<Password>" and response message as "<Status Code>" 
   
    Examples:
     
    |id|Password|Status Code|
    |1234|Anjali|200|
    |4567|Sweety|200|