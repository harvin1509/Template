Feature: Testing file creation

#Scenario: Scenario1- Test to verify whether txt file is created in local
#  Given Block "Scenario-Name" scenario and file type "txt" is created with following details
#      | Receiver |Sender | BIC  |
#      | Harveen  |  Bhup | bic  |
#  Then user loads the xml file "Scenario-Name" for "txt" for date published on day "D"

  Scenario: Scenario2- Test to verify whether xml file is created in local
    Given Block "Scenario-Name" scenario and xml file type "xml" is created with following details
      | Receiver |Sender | BIC  |
      | Harveen  |  Bhup | bic  |
    Then user loads the xml file "Scenario-Name" for "xml" for date published on day "D"