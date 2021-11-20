Feature: Automation Testing Exercise

  Scenario: User should be able to acess top nav bar menu, and their sub options go to the correct pages.
    Given User navigates to the "https://subscription.packtpub.com/search"
    When User clicks on "HomePage_Browse_Menu" link in top nav bar menu
    Then User accesses each category from the dropdown


    #  Then user accesses each categories and sub options