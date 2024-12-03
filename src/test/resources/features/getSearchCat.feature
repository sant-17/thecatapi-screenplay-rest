Feature: Search Cat

  @GetSearchCat

  Scenario Outline: Successfully find a Cat

    When I send a Get request to the endpoint "<endpoint>"
    Then I validate the response code is "<code>" and the structure "<keys>" is valid

    Examples:

    |endpoint|code|keys|
##@externaldata@parameters/Data.xlsx@EndpointData
   |/images/search   |200   |id,url,width,height|
