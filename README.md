# TicTacToe Game

Tic Tac Toe is a game played between 2 Players with moves 'X' and 'O'. It consist of a board with 9 squares and each player proceeds with the game by making moves with either 'X' or 'O'. The player completely occupying either of the row or column or any of the two diagonals with there moves wins the game.

## Rules 
```
The rules are described below :

1) X always goes first.
2) Players cannot play on a played position.
3) Players alternate placing X’s and O’s on the board until either:
4) One player has three in a row, horizontally, vertically or diagonally
5) All nine squares are filled.
6) If a player is able to draw three X’s or three O’s in a row, that player wins.
7) If all nine squares are filled and neither player has three in a row, the game is a draw.
```

## Code commit guidelines

  Inspired from Udacity

* feat: a new feature
* refactor: refactoring production code
* test: adding tests, refactoring test; no production code change
* chore: updating build tasks, package manager configs, etc; no production code change

## Steps to run the test cases in Eclipse/STS IDE
```
1) Download project as zip file and unzip the same to a folder If not yet done. 
2) If project not imported in IDE yet,
   Then in eclipse/sts IDE,  Go to the below path
   File -> Import -> select Existing Maven Project option -> Next -> 
   Browse 	extracted folder and Finish
3) Then, Right click project in Project Explorer window and 
   select Run As -> Maven install
4) Once above steps done, Right click project in Project explorer window and
   select Run as --> Run Configurations..
5) Under configuration wizard from the left window right click on Maven Build and
   select New Configuration.
6) Select Base directory as your project root folder and under Goals mention clean test
7) Apply and Run the same. This will execute all test cases.
```

## Steps to run the application in command prompt
```
1) Download project as zip file and unzip the same to a folder If not yet done. 
2) If project not imported in IDE yet,
   Then in eclipse/sts IDE,  Go to the below path
   File -> Import -> select Existing Maven Project option -> Next -> 
   Browse 	extracted folder and Finish
3) Then, Right click project in Project Explorer window and 
   select Run As -> Maven install
4) Once above steps done,
   Goto <ProjectDirectory>\target
   and execute below command

   java -jar Tic-Tac-Toe-0.0.1-SNAPSHOT.jar

```

