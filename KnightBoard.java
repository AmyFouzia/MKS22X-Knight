public class KnightBoard{

/*
    Selecting a series of moves for a knight such that each square
    is visited exactly once. If the knight ends on a square that is
    reachable by a knight's move from the beginning square, the tour
    is closed, otherwise it is open.

    We represent this with numbers from 1 to K , where K is the area
    of the board.

    -The board dimensions do not have to be square.
    -The starting square counts as visited.
    -You do not have to come back to where you started. Closed tours
    take much longer to find (potentially).

    Any m × n board with m ≤ n, a closed knight's tour is always possible
    unless one or more of these three conditions are met:

    m and n are both odd
    m = 1, 2, or 4
    m = 3 and n = 4, 6, or 8.

   It follows that if a closed tour is possible, then an open tour is
   also possible.
  */

  /*
    KnightBoard has 3 public methods and a constructor, a private
    helper is needed as well.

    @throws IllegalArgumentException when either parameter is <= 0.
    public KnightBoard(int startingRows,int startingCols)

    Initialize the board to the correct size and make them all 0's
*/

//Instance Variables:
    private int[][]board;

//Constructor:
    public KnightBoard(int len, int width){
      board = new int[len][width];
    }

    private boolean addKnight(int r, int c){
      if (board[r][c] != 0){
        return false; //only empty spaces
      }

      board[r][c] ++ ; //Knight
      return true;
    }

    private boolean removeKnight(int r, int c){
      if (board[r][c] != -1){
          return false; //cant rm a Knight if there is none
        }

        board[r][c] --;
        return true;
    }
 The image below is an open tour.
/*
  see format for toString below
  blank boards display 0's as underscores
  you get a blank board if you never called solve or
  when there is no solution

  Modifies the board by labeling the moves from 1 (at startingRow,startingCol) up to the area of the board in proper knight move steps.
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns true when the board is solvable from the specified starting position

  Use the following format for toString:

  (THESE ARE NOT VALID SOLUTIONS, They JUST TO DEMONSTRATE FORMAT)

  Single spaces between numbers, but leading spaces on single digit numbers:
  1  2  5
  3  4  6
  7  8  9

 Which is equivalant to: " 1  2  5\n 3  4  6\n 7  8  9\n"

  When there are two digit numbers (rows*cols >= 10) Put a leading space in front of single digit numbers:
  (spaces replaced with _ to show the difference)
  _1 _2 15 _6
  _3 _4 _7 11
  _8 _9 10 12
  13 14 _5 16

  So it looks like this:
  1  2 15  6
  3  4  7 11
  8  9 10 12
  13 14  5 16

  I will not be testing boards that have a rows*cols that is >= 100, as the program would take a long time to complete.
*/
    public String toString(){
      String res = "";


      return res;
    }

//should work on boards where the number of squares is under 100.
/*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns the number of solutions from the starting position specified
*/
    public boolean solve(int startRow, int startCol){
      if(isExcep()){
        throw new IllegalStateException();
      }

      int level = 0;

      solveH(startRow, startCol, level);
    }

    private boolean solveH(int startRow, int startCol, int level){
      board.addKnight(startRow, startCol);

      return true;
    }

//would only work on smaller boards! The exact sizes will be determined later.

    public int countSolutions(int startRow, int startCol){
      if(isExcep()){
        throw new IllegalStateException();
      }

      return true;
    }

    private int countSolutionsH(){
      return true;
    }

    public static void main(String[] args){
      //testing purposes
      KnightBoard board = new KnightBoard(4);

      board.solve();

      board.addKnight(0,0);
      System.out.println(board);

      board.addKnight(2,0);
      System.out.println(board);

    }

    public void clear(){
      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          board[r][c] = 0;
        }
      }
    }

    public boolean isExcep(){
      int res = 0;

      for (int r = 0; r < board.length; r++){
        for (int c = 0; c < board[r].length; c++){
          if (board[r][c] != 0){
            res++;
          }
        }
      }

      if (startRow < 0 || startRow > board.length || startCol < 0 || startCol > board.length){
        res++;
      }

      if(res != 0){return true;}
      else{return false;}
    }

}
