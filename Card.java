//Claire Dickey
//CS 110
//Card class

import javax.swing.*;

public class Card
{
   //Declare all suit/face card variables 
   public static final int SPADES = 1;
   public static final int CLUBS = 2;
   public static final int HEARTS = 3;
   public static final int DIAMONDS = 4;
   public static final int ACE = 1;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
   public static final int DEFAULT = 1;
   public String cardPic;
   private ImageIcon image;
   private int rank;
   private int suit;
   
   
   
   //Constructor to create card and set rank and suit
   //@param newSuit is the number value of the new suit
   //@param newRank is the number of the cards rank
   public Card(int newSuit, int newRank)
   {
      //Check to see if arguments are valid, if not set to default values
      //Otherwise set suit/rank as arguments
      if (newSuit != SPADES && newSuit != CLUBS && 
            newSuit != HEARTS && newSuit != DIAMONDS)
         {suit = DEFAULT;}
      else
         {suit = newSuit;}
      
      if ((newRank < ACE || newRank > KING))
         {rank = DEFAULT;}
      else 
         {rank = newRank;}
      
      fileName();
   }
   
   //Copy constructor
   public Card(Card otherCard)
   {
      int newRank = otherCard.rank;
      int newSuit = otherCard.suit;
      
      fileName();
      
   }
   
   
   //Method to return suit value
   //@return suit is numerical suit value
   public int getSuit()
   {
      return suit;
   }
   
   //Method to return rank value
   //@return suit is numerical rank value
   public int getRank()
   {
      return rank;
   }
   
   //Sets the filename for the card image
   public void fileName()
   {
      //cardPic = "card/";
   
      //Use if/else statement to make string value of rank   
      if (rank == ACE) 
         {cardPic = "ace";}
      else if (rank == JACK)
         {cardPic = "jack";}
      else if (rank == QUEEN)
         {cardPic = "queen";}
      else if (rank == KING)
         {cardPic = "king";}
      else
         {cardPic = (Integer.toString(rank));}
       
      
      //Use if/else statement to make string vlaue of suit
      if (suit == SPADES)
         {cardPic += "s";}
      else if (suit == CLUBS)
         {cardPic += "c";}
      else if (suit == HEARTS)
         {cardPic += "h";}
      else if (suit == DIAMONDS)
         {cardPic += "d";}
         
      cardPic += ".jpg";
   }
   
   
   //Returns image icon
   //@return ImageIcon card face
   public ImageIcon getImage()
   {
      image = new ImageIcon(cardPic);

      return image;
   }
   
   //Ccompares calling Card object with other cards rank
   //@param another Card object to compare to
   //@return an int to indicate equality
   public int compareRank(Card otherCard)
   {
      //Compare two cards, return appropriate boolean
      if (this.getRank() == otherCard.getRank())
         return 0;
      else if (this.getRank() > otherCard.getRank())
         return -1;
      else
         return 1;
   }
   
   //Checks if calling card object and another are equal
   //@param another Card object to compare to
   //@return a boolean to show suit equality or not
   public boolean compareSuit(Card otherCard)
   {
      //Compare two cards, return appropriate boolean
      if (this.getSuit() == otherCard.getSuit())
         return true;
      return false;
   }

}