# Project 4: Finishing Touches

<h2>Things To Complete</h2>
- Track the High Scores
  - Submit high scores to http://craiginsdev.com/highscore/scores.php using an http post (week 8 thursday notes)
  - You will need to submit the following:
    - game=yourgame
    - name=ask player for initials
    - score=final game score
    - datetime=current time
  - Get high scores from http://craiginsdev.com/highscore/scores.php?game=yourgame (project 2 zipcode lookup)
    - Will return a JSON array like:
    - [{"game":"harry","name":"BAC","score":"29916","datetime":"Sun May 22 2016 16:56:55 GMT-0500 (Central Daylight Time)"},{"game":"halo","name":"BAC","score":"17997","datetime":"Sun May 22 2016 17:00:02 GMT-0500 (Central Daylight Time)"},{"game":"awesome game","name":"BAC","score":"14742","datetime":"Sun May 22 2016 17:31:59 GMT-0500 (Central Daylight Time)"},{"game":"ff6","name":"BAC","score":"14742","datetime":"Sun May 22 2016 17:31:59 GMT-0500 (Central Daylight Time)"}]
    - Manually check high scores here: http://craiginsdev.com/highscore/listHighScores.html

<h2>Completed</h2>
- Add a menu with the following items
  - Stage Selection
  - Global and Local high scores
  - About
- Add a 3rd stage to the game
  - Stage continues until the player dies (gets hit by the enemy)
  - Score continually increases until the player dies
  - Enemies move towards the player
- Track the High Scores 
  - Store local high scores in an SQLite table
