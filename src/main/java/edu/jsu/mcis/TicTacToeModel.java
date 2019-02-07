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
        boolean validity = false;
        if (isValidSquare(row,col))
        {
            if (!isSquareMarked(row,col))
            {
                    if (isXTurn())
                    {
                        board[row][col] = Mark.X;
                        xTurn = false;
                        validity = true;
                    }
        
                    else if (!isXTurn())
                    {
                        board[row][col] = Mark.O;
                        xTurn = true;
                        validity = true;
                    }
        
                
                
            }
        }
        else
        {
            validity = false;
        }

        return validity;
    }
            
            private boolean isValidSquare(int row, int col) {
                
                /* Return TRUE if the specified location is within the bounds of the board */
                
                // INSERT YOUR CODE HERE

                boolean isValid = false;
        
                if (row > -1 && row <= getWidth())
                {  
                    if (col > -1 && col <= getWidth())
                    {
                        isValid= true;
                    }
                }
        
                else
                {
                    isValid = false;
                }
                
                return isValid;
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


        Mark tile = board[row][col];

        return tile;
            
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
        
        boolean won = false;
        int count = 0;

        if(isTie())
        {
            won = true;
        }

        for ( int i = 0; i < getWidth(); ++i)
        {
            for (int j = 0; j < getWidth(); ++j )
            {
                if (board[i][j] == mark)
                {
                    ++count;
                }
                
                else if (board[i][j] != mark)
                {
                    count = 0;
                }

                if (count == getWidth())
                {
                    won = true;
                }
            }
        }


        for ( int i =0; i < getWidth(); ++i)
        {
            for (int j = 0; j < getWidth(); ++ j)
            {
                if ( board[j][i] == mark)
                {
                    ++count;
                }
                
                else
                {
                    count = 0;
                }

                if (count == getWidth())
                {
                    won = true;
                }
            }

        }

        for (int i = 0; i < getWidth(); ++i)
        {
            if (board[i][i] == mark)
            {
                ++count;
            }   

            else
            {
                count =0;
            }

            if (count == getWidth())
            {
                won = true;
            }
        
        }

        for (int i = 0; i < getWidth(); ++i)
        {
            if (board[((getWidth()-1)-i)][i] == mark)
            {
                ++count;
            }

            else
            {
                count =0;
            }

            if (count == getWidth())
            {
                won =true;
            }
        }

        return won;
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        // INSERT YOUR CODE HERE
        boolean draw = false;

        for (int i =0; i < getWidth(); ++i)
        {
            for (int j =0; j < getWidth(); ++j)
            {
                if (board[i][j] == Mark.EMPTY)
                {
                    draw = false;
                }

                else
                {
                    draw = true;
                }

            }
        }

        return draw;
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
