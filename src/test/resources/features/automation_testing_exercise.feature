Feature: Automation Testing Exercise

  Scenario: User should be able to access categories from top nav bar menu, and it should navigate to the correct pages.
    Given User navigates to the "https://subscription.packtpub.com/search"
    When User clicks on "HomePage_Browse_Menu" link in top nav bar menu
    Then User accesses each category from the dropdown

  Scenario: User should be able to access titles in the carousel below the main title inside "your suggested titles" section, and ensure that they appear correctly as the main title.
    Given User navigates to the "https://subscription.packtpub.com"
    When User clicks on titles in the carousel below the main title inside "your suggested titles" section.
    Then User should be able to see carousel title in main title.

  Scenario: User should be able to access view all books links and he should be able to remove if any existing filter then he should able to perform search action with publication year.
    Given User navigates to the "https://subscription.packtpub.com/search"
    When User clicks on "HomePage_Browse_Menu" link in top nav bar menu
    And User clicks on "HomePage_Browse_Menu_ViewAllBooks" link in top nav bar menu
    Then User should be able to see browse page
    When User remove filter if any already set
    Then User set publication year as "2021" and validates search results for below texts
       | Python |
       | Paint  |
       | Secure |
       | Tableau |


