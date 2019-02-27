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
    private int[][] board;
    private int[][] possMoves;
    private int[][] track;
    private int row;
    private int col;

//Constructor:
    public KnightBoard(int len, int width){
      if(len < 0 || width < 0){
        throw new IllegalArgumentException();
      }

      board = new int[len][width];
      row = len;
      col = width;
      possMoves = new int[][] { {-2, -1}, {-2, 1},
                                {-1, 2}, {1, 2},
                                {2, 1}, {2, -1},
                                {1, -2}, {-1, -2} };
      track = new int[len][width];
    }

    public void tracker(){
//refer to map on notes
      for(int i = 0; i < track.length; i++){
        for(int j = 0; j < track[i].length; j++){
          if(i > 1 && i < track.length -2 && j > 1 && j < track[i].length - 2){
            track[i][j] = 8;
          }

          else{
            int res = 0;
            for(int a = 0; i < possMoves.length; a++){
              int row = i + possMoves[a][0];
              int col = j + possMoves[a][0];

              if(!(row < 0 || col < 0 || row >= board.length || col >= board[i].length)){
                res++;
              }
            }
            track[i][j] = res;
          }

        }
      }
    }



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
      for (int i = 0; row < board.length; i++){
        for (int j = 0; j < board[i].length; j++){
          if (i * j >= 10 && board[i][j] < 10){
            res += " ";
          }
          if(board[i][j] == 0){
            res += "_";
          }
          else{
            res += board[i][j];
          }
          res += " ";
        }
        res += "\n";
      }

      return res;
    }

//should work on boards where the number of squares is under 100.
/*
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns the number of solutions from the starting position specified
*/

    public boolean test(int r, int c, int level){
      if(r < 0 || c < 0 || r >= board.length || c >= board[r].length){ //out of bounds?
        return false;
      }
      if(board[r][c] != 0){ //has sq been visited?
        return false;
      }
      board[r][c] = level; //move
      return true;
    }

    public boolean solve(int startRow, int startCol){
      if(isExcep()){
        throw new IllegalStateException();
      }

      if (startRow < 0 || startRow > board.length || startCol < 0 || startCol > board.length){
        throw new IllegalStateException();
      }

      int level = 1;

      return solveH(startRow, startCol, level);
    }

    private boolean solveH(int startRow, int startCol, int level){
      //base case
      if(level > (board.length * board[0].length)){
        return true;
      }

      //recursive step
      for (int i = 0; i < possMoves.length; i++){
        if(test(startRow, startCol, level)){
          if (solveH(startRow + possMoves[i][0], startCol + possMoves[i][1], level +1)){
            return true;
          }
          board[startRow][startCol] = 0;
        }
      }
      return false;
    }

      //recursive backtracking
      /*ref code from queens
      for(int i =0; i < board.length; i++){ //for loop works better than the while
        if(addQueen(i, c)){
          if(solveHelp(c + 1)){
            return true; //try next col
          }
          removeQueen(i, c); //rm and try again
        }
      }*/


//would only work on smaller boards! The exact sizes will be determined later.

  public int countSolutions(int startRow, int startCol){
      clear();

      if(isExcep()){
        throw new IllegalStateException();
      }

      if (startRow < 0 || startRow > board.length || startCol < 0 || startCol > board.length){
        throw new IllegalStateException();
      }

      return countSolutionsH(startRow, startCol, 1);
    }

    private int countSolutionsH(){
      return 1;
    }

    public static void main(String[] args){
      //testing purposes
      KnightBoard board = new KnightBoard(5, 6);

      System.out.println(board.isExcep());

      System.out.println(board);;

      board.solve(0, 0);
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

      if(res != 0){return true;}
      else{return false;}
    }

}
