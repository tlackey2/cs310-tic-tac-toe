package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */
        
        // INSERT YOUR CODE HERE

        // Not sure if this is correct or not

        for (int i = 0; i < width; ++i)
        {
            for(int j = 0; j< width; ++j)
            {
                board[i][j] = Mark.EMPTY;
            }
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */

        
        // INSERT YOUR CODE HERE
        if (isValidSquare(row, col))
        {
            if (!isSquareMarked(row, col))
            {
                if (xTurn)
                 {
                    board[row][col] = Mark.X;
                }
                else 
                {
                    board[row][col] = Mark.O;
                }
                xTurn = !xTurn;
                return true;
            }
            else 
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }
    
            
            private boolean isValidSquare(int row, int col) {
                
                /* Return TRUE if the specified location is within the bounds of the board */
                
                // INSERT YOUR CODE HERE

                if ((row >= getWidth() || col >= getWidth()) || (row < 0 || col < 0))
                {
                    return false;
                }
                else
                 {
                    return true;
                }
                
            }
       
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        // INSERT YOUR CODE HERE

        if (board[row][col] != Mark.EMPTY)
        {
            return true;
        }

        else
        {
            return false;
        }

    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        // INSERT YOUR CODE HERE


        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        // INSERT YOUR CODE HERE

        if (isTie())
        {
            return Result.TIE;
        }
        else 
        {
            if (!xTurn)
             {
                if (isMarkWin(Mark.X))
                {
                    return Result.X;
                }
                else 
                {
                    return Result.NONE;
                }
            }
            else 
            {
                if (isMarkWin(Mark.O))
                {
                    return Result.O;
                }
                else 
                {
                    return Result.NONE;
                }
            }
        }
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        // INSERT YOUR CODE HERE
        
        boolean topLeftBottomRight = true;
        for (int f = 0; f < getWidth(); f++){
            if (board[f][f] != mark) { topLeftBottomRight = false; }
        }

        //Bottom left to top right check
        boolean bottomLeftTopRight = true;
        for (int k = 0; k < getWidth(); k++) {
            if (board[(getWidth() - 1) - k][k] != mark) { bottomLeftTopRight = false; }
        }

        //Row check
        boolean rows = false;
        for (int i = 0; i < getWidth(); i++) {
            boolean winner = true;

            for (int j = 0; j < getWidth(); j++) {
                if (board[j][i] != mark) {winner = false;}
            }
            if (winner) {
                rows = true;
            }
        }

        //Col check
        boolean cols = false;
        for (int g = 0; g < getWidth(); g++) {
            boolean winner = true;

            for (int h = 0; h < getWidth(); h++) {
                if (board[g][h] != mark) {winner = false;}
            }
            if (winner) {
                cols = true;
            }
        }

        if (topLeftBottomRight || bottomLeftTopRight || rows || cols) { return true; }
        else { return false; }

    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE
        boolean emptySpace = false;
        for (int i = 0; i < getWidth(); i++) 
        {
            for (int j = 0; j < getWidth(); j++)
             {
                if (board[i][j] == Mark.EMPTY)
                {
                    emptySpace = true;
                }
            }
        }

        if (emptySpace) { return false; }
        else { return true;}
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */
        
        // INSERT YOUR CODE HERE

        int counter = 0;
        for (int i = 0; i < getWidth(); i++)
        {
            output.append(i);
        }
        output.append("\n");
        for (int j = 0; j < getWidth(); j++)
        {
            output.append(counter + " ");
            for (int k = 0; k < getWidth(); k++)
            {
                output.append(board[j][k]);
            }
            output.append("\n");
            counter++;
        }

        
        return output.toString();
        
    }
    
}
