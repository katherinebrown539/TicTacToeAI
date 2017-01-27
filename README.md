# TicTacToeAI
Just me playing around with creating a TicTacToe AI.

To Run: 
1) Unpack contents into a single directory.
2) Double-click TicTacToeAI.jar

If you have issues, open a command line and direct it to the location of the game files. Run "build.bat".
Repeat step 2. If issues persist, run "java -jar TicTacToeAI.jar". If you still have issues, ensure Java is up-to-date.

Known quirks:
The AI must win first. It's stubborn and won't play with you again if you beat it (which you won't) or tie first.
After each game, if the game is tied, the players remain the same. If the computer wins, positions are swapped (O becomes X and X becomes O).
By nature of minimax, no human can beat this game. The best possible is a tie.
