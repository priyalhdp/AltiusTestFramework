Feature: Vardenisiffror Home Page Tests
Scenario: Verify Vardenisiffror web Page is displayed
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
Then Page Title Should Be "Mina enhetslistor"
And  Page body has "Mina enhetslistor"

Scenario Outline:Verify Vardenisiffror web Page is displayed
Given User Launch "<Browser>" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
Then Page Title Should Be "Mina enhetslistor"
And  Page body has "Mina enhetslistor"

Examples:
|Browser  |
|Chrome   |
|Firefox  |
|IExplorer|


Scenario: Verify one Enhetslista Creation
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Label name Should be "Min lista (1)"

Scenario: Verify the Creation of given No of lists
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click Skapa enhetslista 5 times
Then Created Label count Should be 5
And  Created Label Name Should be "Min lista "

Scenario: Verify the Rename of First Enhetslista
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Clear the list name text box
And Type "My Automated List" to the name
And Click Tillbaka till mina enhetslistor
Then Updated List is displayed

Scenario: Verify Search units of Alla regioner + Riket
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Clear the list name text box
And Type "My Automated List" to the name
And Select and checked Alla regioner + Riket
And Click Tillbaka till mina enhetslistor
Then Updated List is displayed 

Scenario: Verify Search units with Sok efter enheter
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Clear the list name text box
And Type "My Automated List" to the name
And Enter key word in "Stockholms kommun" text box and select Unit
And Click Tillbaka till mina enhetslistor
Then Updated List is displayed 

Scenario: Verify the apply of colors to the selected device in enhet list
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Clear the list name text box
And Type "My Automated List" to the name
And Enter key word in "Stockholms kommun" text box and select Unit
And Click Farg icon_label of the device row
Then Display a color platter pop up
When Select one color from the platter
Then Close the color platter pop up
And Verify Applied selected color to the device Farg label
When Click Tillbaka till mina enhetslistor
Then Updated List is displayed 

Scenario: Verify the deletion of selected device in enhet list
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Clear the list name text box
And Type "My Automated List" to the name
And Enter key word in "Stockholms kommun" text box and select Unit
And Click Delete Unit Button
When Click Tillbaka till mina enhetslistor
Then Updated List is displayed 

Scenario: Verify the deletion of selected enhetslistor List
Given User Launch "Firefox" Webbrowser
When User Opens URL "https://vardenisiffror.se/unitlists"
And  User Click Accept Cookies
And Click ones Skapa enhetslista
Then Created Lists available 
When click on the First list in the grid
And Click Ta bort Listan button
And Verify List Deleted from the List