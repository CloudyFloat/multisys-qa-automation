Feature: e-Negosyo Page Navigation and Inquiry Submission

  Scenario: Verify successful loading of the e-Negosyo landing page
    Given the user opens a browser and navigates to "https://www.multisyscorp.com/e-Negosyo"
    When the page is fully loaded
    Then the hero banner, service overview, and "Inquire Now" CTA buttons should be visible
    And the page title should contain "e-Negosyo - Digital Solutions for MSMEs"

  Scenario: Navigate to the inquiry form from e-Negosyo
    Given the user is on the "https://www.multisyscorp.com/e-Negosyo" page
    When the user clicks on the "Mail" Icon button
    Then the user should be redirected to the contact or inquiry form section
    And input fields for Select Reason dropdown, Full Name, Email, Mobile Number, and Message text area should be displayed

  Scenario: Submit an inquiry with valid required details and privacy consent
    Given the user is on the e-Negosyo inquiry form
    When the user selects a reason from the "Select Reason" dropdown
    And enters valid details in Full Name, Email, and Mobile Number fields
    And enters a message in the Message text area
    And opens the Privacy Statement link
    And ticks the check box inside the Privacy Statement dialog
    And checks the "I agree with the privacy statement" check box on the inquiry form
    And clicks the "Submit" button
    Then the submit button will grayout for a brief moment

  Scenario: Attempt inquiry submission with missing mandatory fields or unaccepted privacy statement
    Given the user is on the e-Negosyo inquiry form
    When the user leaves the Email field blank and fills out other fields
    And clicks the "Submit" button
    Then validation error messages "required" should prompt the user to fill in the required fields

    Scenario: Verify mobile responsiveness of the e-Negosyo page
    Given the user sets the browser viewport to mobile dimensions (375x812)
    When the user navigates to "https://www.multisyscorp.com/e-Negosyo"
    Then the navigation menu should collapse into a hamburger menu
    And content blocks should stack vertically without horizontal overflow
