import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardList
{
   public static final int CARDS_IN_DECK = 52;
   
   //Constructor that creates a standard non shuffled deck
   public Deck()
   {
      freshDeck();
   }
   
   //Creates standard non shuffled deck
   public void freshDeck()
   {
      cardList = new ArrayList<Card>();

      for (int r = Card.ACE; r<=Card.KING;r++)
      {
         for (int s = Card.SPADES;s<=Card.DIAMONDS;s++)
         {
           cardList.add(new Card(r,s));
         }
      } 
   }
   
   //Deals top card
   //@return Card card to be dealt
   public Card dealCard()
   {
      return this.useCard(0);
   }
   
   //Shuffles deck
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < cardList.size(); i++)
      {
         randNum = r.nextInt(cardList.size());
         temp = cardList.get(i);
         cardList.set(i,cardList.get(randNum));
         cardList.set(randNum,temp);
      }      
   }
   
}
