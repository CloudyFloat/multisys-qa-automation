Feature: E-Negosyo Page Navigation and Inquiry Submission

  Scenario: Verify successful loading of the E-Negosyo landing page
    Given the user opens a browser and navigates to "https://www.multisyscorp.com/e-negosyo"
    When the page is fully loaded
    Then the hero banner, service overview, and "Inquire Now" CTA buttons should be visible
    And the page title should contain "E-Negosyo"

  Scenario: Navigate to the inquiry form from E-Negosyo
    Given the user is on the "https://www.multisyscorp.com/e-negosyo" page
    When the user clicks on the "Inquire Now" or "Get Started" button
    Then the user should be redirected to the contact or inquiry form section
    And input fields for Name, Email, and Mobile Number should be displayed

  Scenario: Submit an inquiry with valid required details
    Given the user is on the E-Negosyo inquiry form
    When the user enters valid details in Name, Email, and Mobile Number fields
    And clicks the "Submit" button
    Then a success confirmation message should appear indicating the inquiry was sent

  Scenario: Attempt inquiry submission with missing mandatory fields
    Given the user is on the E-Negosyo inquiry form
    When the user leaves the Email field blank and fills out other fields
    And clicks the "Submit" button
    Then validation error messages should prompt the user to fill in the required fields

  Scenario: Verify mobile responsiveness of the E-Negosyo page
    Given the user sets the browser viewport to mobile dimensions (375x812)
    When the user navigates to "https://www.multisyscorp.com/e-negosyo"
    Then the navigation menu should collapse into a hamburger menu
    And content blocks should stack vertically without horizontal overflow
