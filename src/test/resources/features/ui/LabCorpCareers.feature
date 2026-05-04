Feature: Search job posted in Careers page on Labcorp website

  Scenario Outline: As a test user I want to search job posted in Careers page
    Given user is already on Labcorp main page
    When user click on Careers link
    And user search "<position>" and select the position
    Then user verifies the "<jobTitle>", "<jobLocation>" and "<jobId>"
    Then user verifies additional requirement texts "<req1>", "<req2>" and "<req3>"
    And user click on ApplyNow button
    Then user again verifies "<jobTitle>", "<startAppTxt>"
    Examples:
      | position              | jobTitle              | jobLocation          | jobId   | startAppTxt            | req1                                                | req2                                                       | req3                                                     |
      | QA Compliance Auditor | QA Compliance Auditor | Singapore, Singapore | 2612055 | Start Your Application | Development and delivery of training to operational | 4 years or more experience in a GxP regulatory environment | Able to clearly articulate processes to provide training |