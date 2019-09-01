#Author: shubham.jain@cuelogic.co.in
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios 
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template

Feature: Testing Framework Action

#		Scenario: Testing my Framework cases
#			 Given Test1
	
		Scenario Outline: "<TC_ID>" | validation for date
   		 Given Book a date
   		 When create a deal

    Examples: 
      | TC_ID | REFER  |
      | TC_02 | CMS-02 |

#	Scenario: failtestcase
#	Given fail testase
#	
#	Scenario: Testing my Framework cases test
#	Given if username and password provoded
#	When when user click on sign in button
  #And click on robot radio button
  #Then the user should able to login in application
  
  