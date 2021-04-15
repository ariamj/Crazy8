import java.util.*;

public class Hand
{
    private ArrayList<Cards> hand;
    
    // Create a new hand
    public Hand()
    {
        hand = new ArrayList<Cards>();
    }
    
    // getter method for hand arraylist
    public ArrayList<Cards> getHand()
    {
        return hand;
    }
    
    // Add a card into the hand
    public void addCard(Cards c)
    {
        hand.add(c);
    }
    
    // Play a card -- removes card from hand
    public void useCard(Cards c)
    {
        hand.remove(hand.indexOf(c));
    }
    
    // Print the computer hand --> hide the cards but show number of cards
    public String printCompHand()
    {
        ArrayList<String> hideHand = new ArrayList<String>();
        for(int i=0; i<hand.size(); i++)
        {
            hideHand.add("X");
        }
        return Arrays.toString(hideHand.toArray());
    }
    
    // String of the hand
    public String toString()
    {
        return Arrays.toString(hand.toArray());
    }
}
