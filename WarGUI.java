import java.io.File;
//import java.io.FileNotFoundException;
import java.io.IOException;
//import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarGUI extends JFrame
{
   //Declare necessary variables
	private War game;
	private JPanel userPanel, compPanel;
	private JLabel userHand, compHand, userPile, compPile, gameText;
	private ImageIcon backImage, userCard, playerCard;
	private JButton playButton;
	
	//Constructor
	public WarGUI()
	{
      
		game = new War();
      backImage = new ImageIcon("cardPics/back.jpg");       

      //Set up window
		setTitle("War");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new FlowLayout());
      setSize(800,600);
      validate();
      panelSetUp();
      
      //Add player panels
      add(userPanel);
      add(compPanel);

      setVisible(true);
	}

	private void panelSetUp()
	{  
		//create a text field to print status messages in can use HTML :)
		gameText = new JLabel("This is War");
      //create cards
      userHand = new JLabel();
      compHand = new JLabel();
      userPile = new JLabel();
      compPile = new JLabel();
      update();
      
		//create play button
		playButton = new JButton("Play");
      
		//add listener on the play button
		playButton.addActionListener(new PlayButtonListener());
      
		//Set up panels
      userPanel = new JPanel();
      compPanel = new JPanel();

      userPanel.add(userHand);
      userPanel.add(userPile);
      
      userPanel.add(compHand);
      userPanel.add(compPile);

		compPanel.add(gameText);
		compPanel.add(playButton);
	}

	//button listener
	private class PlayButtonListener implements ActionListener
    {
        // Called when the button is pushed.
        public void actionPerformed(ActionEvent e)
        {
        	playButton.setText("Continue Game");
            game.play();
            update();
            
            // If the game is over
            if (game.getWinner() != 0)
            {
            	// Figure out who won
	            if (game.getWinner() < 0)
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "User Wins!");
	            }
	            else //g.getWinner() > 0
	            {
	            	javax.swing.JOptionPane.showMessageDialog(null, "Computer Wins!");
	            }
	            // And finish up.
	            updateText(); // To get the "P1 wins!"/"P2 Wins." message
	            playButton.setEnabled(false); // Disable the continue button.
            }
            

        }
    }

    public void update()
    {
        updateImages();
        updateText();
        //updateTotals();
    }

	//call this everytime something happens
	public void updateImages()
    {
    	// Set the player's hand to the back of a card if it has cards in it, otherwise set it to nothing.
        if (!(game.userHand.isEmpty()))
        {
            userHand.setIcon(backImage);
        }
        else
        {
            userHand.setIcon(null);
        }
        userHand.revalidate(); // Repaint
        
        // Set the player's top card on the pile to the correct image if there are cards on the pile, otherwise set it to nothing.
        if (game.userHand.topCard() != null)
        {
            userPile.setIcon(game.userHand.topCard().getImage());
        }
        else
        {
            userPile.setIcon(null);
        }
        userPile.revalidate(); // Repaint
        
        // Set the computer's hand to the back of a card if it has cards in it, otherwise set it to nothing.
        if (!(game.compHand.isEmpty()))
        {
            compHand.setIcon(backImage);
        }
        else
        {
            compHand.setIcon(null);
        }
        compHand.revalidate(); // Repaint
        
        // Set the player's top card on the pile to the correct image if there are cards on the pile, otherwise set it to nothing.
        if (game.compHand.topCard() != null)
        {
            compPile.setIcon(game.compHand.topCard().getImage());
        }
        else
        {
            compPile.setIcon(null);
        }
        compPile.revalidate(); // Repaint
    }
	//call this method whenever something happens in the game
	public void updateText()
    {
    	String newText = "<html>"+game.gameText()
    			+ "<br>User cards: " + (game.userHand.cardsInList()+game.userPile.cardsInList())
    			+ "<br>Computer size: " + (game.compHand.cardsInList()+game.compPile.cardsInList())
    			+ "</html>";
    	
      gameText.setText(newText);
    }
    
	public static void main(String[] args) 
	{
	    WarGUI wgui = new WarGUI();
	}
}