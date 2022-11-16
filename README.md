# Assignment
CarSHAiR QA Assessment List of Bugs

1.Note details page is broken for newly created notes - throws 404-This page could not be found. 
Steps to reproduce:
Go to Home page
Create a note
Search for the created note by text
open the note from the search results
Expected - it should open the Note details page
Actual - 404 This page could not be found.
Browser - chrome 103 version

2. Owner Info is displayed as Object Object
Go to search page
Observe the list of note for the Owner Name
Expected - the owner name should be displayed
Actual - Object Object is displayed

3. Search Note by owner doesnt work
Go to search page
Enter Owner as test
Expected - The list of all notes by owner 'test' should be listed, if there are no note by that owner name then empty list with prompt message as 'No notes found for owner 'test' should be displayed.
Actual - All the notes are displayed and /search API throws 400

4. Profile API is broken - needs login every time
Login with valid credentials
perform any transaction like create new note, go to search
go back to profile
Excepted - the login should persist till the user logs out.
Actual - https://master.hiring-assignment.qa.c66.me:3001/v3/profile throw 401. 

On UI the prev notes are not displayed and submit button for new note is disabled
