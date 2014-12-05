import java.util.ArrayList;

public class CardList
{
   protected ArrayList<Card> cardList;
   
   //Constructor that creates an empty ArrayList
   public CardList()
   {
      cardList = new ArrayList<Card>();
   }
   
   //Constructor that starts with a list of cards
   //@param ArrayList<Card> newCardList cards to start list with
   public CardList(ArrayList<Card> newCardList)
   {
      cardList = new ArrayList<Card>();
      copyArrayCards(newCardList);
   }
   
   //Adds a card to list
   //@param Card newCard card to add to list
   public void addCard(Card newCard)
   {
      cardList.add(newCard);
   }
   
   //Method to copy an ArrayList of Cards into current card list
   //@param ArrayList<Card> newCardList cards to be added to CardList
   public void copyArrayCards(ArrayList<Card> newCardList)
   {   
      for (Card card : newCardList) 
      {
        cardList.add(new Card(card));
      }
   }
   
   //Plays a specific card from list
   //@param int cardIndex card to be used
   //@return Card card that has been removed from list
   public Card useCard(int cardIndex)
   {
      Card c = cardList.remove(cardIndex);
      return c;
   }
   
   //Returns and removes all cards from list
   //@return ArrayList<Card> all cards from list
   public ArrayList<Card> useAllCards()
   {
      ArrayList<Card> tempCardList = new ArrayList<Card>();
      
      for (Card card : cardList) 
      {
        tempCardList.add(new Card(card));
      }
      
      cardList.clear();
      
      return tempCardList;   
   }
   
   //Checks top card
   //@return Card copy of top card
   public Card topCard()
   {
      try
      {
         Card c = cardList.get(0);
         return c;
      }
      catch (ArrayIndexOutOfBoundsException a)
      {
         return null;
      }
   }
   
   //Returns copy of card at index
   //@return Card copy of card at index
   public Card getCard(int index)
   {
      Card c = cardList.get(index);
      return c;
   }
   
   //Checks if the list is empty
   //@return boolean indicates empty/not
   public boolean isEmpty()
   {
      return (cardList.size() == 0);
   }
   
   //Checks number of cards in list
   //@return int size of list
   public int cardsInList()
   {  
      return cardList.size();
   }
   
   //Returns copy of card list
   //@return arraylist of all cards
   public ArrayList<Card> getList()
   {
      return cardList;
   }


}

