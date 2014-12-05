
public class War
{
   public static void main(String[] args) 
   {
      // Create a new War game and play it
      War warGame = new War();
   }

   public Deck deck;
   public CardList userHand;
   public CardList compHand;
   public CardList userPile;
   public CardList compPile;
   public CardList tempCards;
   public String gameText = "";
   public final int USER = 1, COMP = -1;
   public final int USER_WIN = 1, NO_WINNER = 0, COMP_WIN = -1;
   public int roundWinner;
   public int gameWinner = NO_WINNER;
   
   
   //Constructor that sets up game
   public War()
   {     
      startGame();  
   }
   
   //Sets up deck, deals hands
   public void startGame()
   {
       //Sets up deck
       deck = new Deck();
       deck.shuffle();
      
       //Instantiate hands/discards/temporary list
       userHand = new CardList();
       compHand = new CardList();
       userPile = new CardList();
       compPile = new CardList();
       tempCards = new CardList();
      
       //Deal hands
       for(int i = 0; i<Deck.CARDS_IN_DECK/2; i++)
       {
          userHand.addCard(deck.dealCard());
          compHand.addCard(deck.dealCard());
       }
   }
   
   //Method that actually loops and plays 
   public void play()
	{
      int completeRound = 1;
      
		//Check if user has cards in neither pile
		if(userHand.isEmpty() && userPile.isEmpty())
		{
         winGame(COMP);
         completeRound =0;
		}
		//check if user has no cards in just their hand
		else if(userHand.isEmpty())
		{
			userHand.copyArrayCards(userPile.useAllCards());
		}
		
      //Check if computer has cards in niether pile
   	if(compHand.isEmpty() && compPile.isEmpty())
   	{
         winGame(USER);
         completeRound = 0;
   	}
   	//check if computer has no cards just in their hand
   	else if(compHand.isEmpty())
   	{
   		compHand.copyArrayCards(compPile.useAllCards());
   	}
      
      if (completeRound == 1)
      {
   		//Check to see whose card is greater or if tie
   		if((userHand.topCard().compareRank(compHand.topCard()))>0)
   		{
   			roundWinner = USER;
   		}
   		else if(userHand.topCard().compareRank(compHand.topCard())<0)
   		{
   			roundWinner = COMP;
   		}
   		else
   		{
   			roundWinner = NO_WINNER;
   		}
    
         tempCards.addCard(userHand.useCard(0));
         tempCards.addCard(compHand.useCard(0));
         
   		//Give won cards to correct player or go to war
         int j = tempCards.cardsInList();
   
   		switch(roundWinner)
   		{
   			case USER:
   				gameText="User wins this round.";
   				for (int i=0;i<j;i++) 
   				{
   					userPile.addCard(tempCards.useCard(0));
   				}
   				break;
   			case COMP:
   				gameText="Computer wins this round.";
   				for (int i=0;i<j;i++) 
   				{
   					compPile.addCard(tempCards.useCard(0));
   				}
   				break;
   			default:
   				gameText+="War!";
   				tempCards.addCard(userHand.useCard(0));
   				tempCards.addCard(compHand.useCard(0));
   		}
      }
   }
   
   //Compares top cards
   //@return int indicates how cards compmare
   public int compare()
   {
      if(userHand.topCard().getRank() == compHand.topCard().getRank())
         return 0;
      else if(userHand.topCard().getRank() > compHand.topCard().getRank())
         return 1;
      else 
         return -1;       
   }
      
   //Distributes cards if user wins regular round
   //@param int player indicates if user or comp
   public void winRound(int player)
   {
      switch (player)
      {
         case USER:
            userPile.addCard(userHand.useCard(0));
            userPile.addCard(compHand.useCard(0));
         case COMP:
            compPile.addCard(compHand.useCard(0));
            compPile.addCard(userHand.useCard(0));
             
      }  
   }
   
   //Distributes cards if user wins round of war
   //@param int player indicates if user or comp 
   public void winWar(int player)
   {
      switch (player)
      {
         case USER:   
            winRound(USER);
            winRound(USER);
         case COMP:
            winRound(COMP);
            winRound(COMP);
      }          
             
   }
   
   //Returns total cards in player's piles
   //@param int player indicates if user or comp
   //@return int total player's cards
   public int totalCards(int player)
   {
      switch (player)
      {
         case USER:
            return (userHand.cardsInList() + userPile.cardsInList());
         case COMP:
            return (compHand.cardsInList() + compPile.cardsInList());
         default:
            return -99;   
      }
         
   }
   
   //Sets winner value if player wins game
   //@param int player indicates if user or comp
   private void winGame(int player)
	{
      switch (player)
      {
         case USER:
		      gameWinner = USER_WIN;
         case COMP:
            gameWinner = COMP_WIN;
         default:
            gameWinner = NO_WINNER;
      }
	}
   
   //Returns winner value
   //@return int indicates winner, or no winner
   public int getWinner()
	{
		return gameWinner;
	}
   
   //Returns current message presented by game
   //@return gameText current message
   public String gameText()
   {
      return gameText;
   }
   
}