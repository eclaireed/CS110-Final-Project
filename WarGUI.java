import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WarGUI extends JFrame
{
   //Declare necessary variables
	private War game;
	private JPanel userPanel, compPanel, messagePanel, labelPanel ;
	private JLabel userHand, compHand, userPile, compPile, gameText;
   private JLabel playerLabel;
	private ImageIcon backImage, userCard, playerCard;
	private JButton playButton;
   private int userTurnOne = 1, compTurnOne = 1, roundOne = 1;
	
	//Constructor
	public WarGUI()
	{
      
		game = new War();
      backImage = new ImageIcon("cardPics/back.jpg");       

      //Set up window
		setTitle("War");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setLayout(new BorderLayout());
      setSize(700,350);
      validate();
      panelSetUp();
      
      //Add player panels
      add(userPanel, BorderLayout.WEST);
      userPanel.setSize(350,220);
      add(compPanel, BorderLayout.EAST);
      userPanel.setSize(350,220);
      add(labelPanel,BorderLayout.NORTH);
      add(messagePanel, BorderLayout.SOUTH);

      setVisible(true);
	}

	private void panelSetUp()
	{  
		//Create a text fields to print messages
		gameText = new JLabel("This is War");
      String newText = "<html> Computer &emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp User &emsp</html>";
      playerLabel = new JLabel(newText);

      //Create card pile labels
      userHand = new JLabel();
      compHand = new JLabel();
      userPile = new JLabel();
      compPile = new JLabel();
      update();
      
		//Start button
		playButton = new JButton("Start Game");
      
		//add listener on the play button
		playButton.addActionListener(new PlayButtonListener());
      
		//Set up panels
      userPanel = new JPanel();
      compPanel = new JPanel();
      messagePanel = new JPanel();
      labelPanel = new JPanel();

      userPanel.add(userHand);
      userPanel.add(userPile);
      
      compPanel.add(compPile);
      compPanel.add(compHand);
      
      labelPanel.add(playerLabel);

		messagePanel.add(gameText);
		messagePanel.add(playButton);
	}

	//Button listener
	private class PlayButtonListener implements ActionListener
    {
        // Called when the button is pushed.
        public void actionPerformed(ActionEvent e)
        {
            if (roundOne == 1)
            {
               playButton.setText("Draw");
               roundOne = 0;
            }
            else
            {
               game.play();
               update();
                          
               // Figure out who won at finish
               if (game.getWinner() != 0)
               {
   	            if (game.getWinner() > 0)
   	            {
   	            	javax.swing.JOptionPane.showMessageDialog(null, "User Wins!");
   	            }
   	            else
   	            {
   	            	javax.swing.JOptionPane.showMessageDialog(null, "Computer Wins!");
   	            }
   	            
   	            updateText(); // update text to display winner
   	            playButton.setEnabled(false); //turn off button
               }
            }
            

        }
    }

    public void update()
    {
        updateImages();
        updateText();
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
        userHand.revalidate();
        
        // Set the player's top card on the pile to the correct image if there are cards on the pile, otherwise set it to nothing.
        if (game.userHand.topCard() != null)// && !(userTurnOne == 1))
        {
            userCard = new ImageIcon(game.userHand.topCard().cardPic);
            userPile.setIcon(userCard);
        }
        else
        {
            userPile.setIcon(null);
            //userTurnOne = 0;
        }
        userPile.revalidate();
        
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
        if (game.compHand.topCard() != null) // && !(compTurnOne == 1))
        {
            compPile.setIcon(game.compHand.topCard().getImage());
        }
        else
        {
            compPile.setIcon(null);
            //compTurnOne = 0;
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