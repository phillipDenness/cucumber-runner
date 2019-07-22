Feature: Daily fund scraper

  Scenario: The response returns fund data for a provided fund URL
    Given I have a trustnet URL https://www.trustnet.com/factsheets/p/fl96/av-artemis-uk-special-situations-fp-pn
    When I send a request to the daily scraper with trustnet URL as a query param
    Then I get a 200 response code
    And I have a valid price
    And I have a valid date
    And I have a valid fund name Av Baillie Gifford Managed FP Pn
    And I have a valid difference in price

  Scenario: The response returns fund data for a provided citicode when i supply a fund URL aswell
    Given I have a trustnet URL https://www.trustnet.com/factsheets/p/fl96/av-artemis-uk-special-situations-fp-pn
    And I have a trustnet citicode o965
    When I send a request to the daily scraper with trustnet URL and citicode as a query params
    Then I get a 200 response code
    And I have a valid price
    And I have a valid date
    And I have a valid fund name Av Baillie Gifford Japanese FP Pn
    And I have a valid difference in price

  Scenario: The response returns fund data for a provided citicode
    Given I have a trustnet citicode o965
    When I send a request to the daily scraper with citicode as a query param
    Then I get a 200 response code
    And I have a valid price
    And I have a valid date
    And I have a valid fund name Av Baillie Gifford Japanese FP Pn
    And I have a valid difference in price