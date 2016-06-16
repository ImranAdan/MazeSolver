# MazeSolver
MazeSolver is a program to solve simple mazes to explore different searching algorithms. The mazes are given in a file and the program must reads in the file, solve the maze and output a solution if it exists otherwise it specifies that the maze cannot be solved.

The application must meet the following specification:

  - Handle any _M X M_ Maze
  - Valid moves during expansion are N, S, E, W (not diagonally)
  - Output should be written to the Standard Output/Console

### Maze Definition File

The input is a file in plain text saved with the extension .maze

### Input 
```sh
1 - {{ROW}} {{COLUMNS}}\n
2 - {{START_X}} {{START_Y}}\n
3 - {{GOAL_X}} {{GOAL_Y}}\n
4 - {{ 
        [M X M] MATRIX, 
         WHERE: 
           1 = DENOTES WALLS, 
           0 = TRAVERSABLE PASSAGE WAY 
    }}
```

#### Example Input FILE (PLESE BASE ON BELOW FILE):

```sh
10 10                   <--- {{START_X}}  {{START_Y}}\n
1 1                     <--- START_X START_1
8 8                     <--- GOAL_X GOAL_Y
1 1 1 1 1 1 1 1 1 1     <--- MATRIX REPRESENTING THE MAZE
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1
```

### OUTPUT 

THE MAZE WITH:
 - A PATH FROM START TO END WALLS MARKED BY '#', 
 - PATHS MARKED BY 'X',
 - START/END MARKED BY 'S'/'E'

#### Example Input and Output of a FILE:

```sh
INPUT: FILE_NAME.maze
10 10
1 1
8 8
1 1 1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 0 0 1
1 0 1 0 1 1 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 0 1 0 1 1 1
1 0 1 0 0 1 0 1 0 1
1 0 1 0 0 0 0 0 0 1
1 0 1 1 1 0 1 1 1 1
1 0 1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1 1 1


OUTPUT: FILE_NAME.maze
1 1 1 1 1 1 1 1 1 1
1 S X X X X X X X 1
1 X 1 X 1 1 1 1 1 1
1 X 1 X X X X X X 1
1 X 1 1 X 1 X 1 1 1
1 X 1 X X 1 X 1 X 1
1 X 1 X X X X X X 1
1 X 1 1 1 X 1 1 1 1
1 X 1 X X X X X E 1
1 1 1 1 1 1 1 1 1 1
_DONE_
```

EMPAHSIS IS ON THE SEARCH ALGORITHMS HOWEVER EXTRA POINTS FOR BEAUTIFICATION.

FOR EVEN BETTER OUTPUT YOU COULD REPLACE ALL NON VALID PATHS WITH ' ' AND
REPRESENT THE VALID PATH FROM 'S' to 'E' WITH 'X '

### EXAMPLE CLEAR OUTPUT 
```sh
# # # # # # # # # #
# S X X           #
#   # X # # # # # #
#   # X X         #
#   # # X #   # # #
#   #   X #   #   #
#   #   X X       #
#   # # # X # # # #
#   #     X X X E #
# # # # # # # # # #
_DONE_
```
### How should the application be run. 

You *will* need Maven as to manage the build and the dependecies for the application. 

There is no main method to run. Create a test for the specific search implementation that you provided and invoke that as a maven target. 

```sh
$ mvn -Dtest=MY_SUBJECT_UNDER_TEST test
```

Have a look at: 
http://maven.apache.org/surefire/maven-surefire-plugin/examples/single-test.html for running single test cases using maven.