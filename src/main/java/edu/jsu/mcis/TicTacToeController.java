package edu.jsu.mcis;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToeController implements ActionListener
{

    private final TicTacToeModel model;
    private final TicTacToeView view;
    boolean gameOver = false;
    
    public String getMarkAsString(int row, int col) {        
        return (model.getMark(row, col).toString());        
    }
    
    public TicTacToeView getView() {        
        return view;        
    }

   // @Override
    public void actionPerformed(ActionEvent event) 
    {
        // INSERT YOUR CODE HERE
        
        String button = ((JButton) event.getSource()).getName();
        int row = button.charAt(6) -48;
        int col = button.charAt(7) -48;

        model.makeMark(row,col);
        view.updateSquares(row,col);



    }

     /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(this, model.getWidth());
        
    }


    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */

        // INSERT YOUR CODE HERE

        // Double Check this
        

        while (!gameOver)
        {
            //view.showBoard(model.toString());
            //TicTacToeMove currentMove = view.getNextMove(model.isXTurn());

            //model.makeMark(currentMove.getRow(), currentMove.getCol());

            if(model.isGameover())
            {
                gameOver = true;
            }

        }

        
        /* After the game is over, show the final board and the winner */



        view.showResult(model.getResult().toString());
        
    }

    public boolean turnOfX()
    {
       boolean x = model.isXTurn();
       return x;
    }

    public boolean getGameOver()
    {
        boolean game = gameOver;
        return game;
    }

    public int contGetWidth()
    {
        return model.getWidth();
    }
    

}
