import java.util.*;

public class CrazyEight
{
    private Deck d;
    private Hand user;
    private Hand comp;
    private Cards topCard;
    Scanner console;
    private boolean win = false;
    
    // create a new game of crazy 8
    public CrazyEight()
    {
        d = new Deck();
        d.shuffleDeck();
        user = new Hand();
        comp = new Hand();
        console = new Scanner(System.in);
    }
    
    public void playGame()
    {
        /** Set up */
        // dealing cards out -- 7 cards each (2 players)
        for(int i=0; i<7; i++)
        {
            user.addCard(d.dealCard());
            comp.addCard(d.dealCard());
        }
        System.out.println("User hand: "+user.toString());
        System.out.println("Computer hand: "+comp.printCompHand());
        // get first top card
        topCard = d.dealCard();
        System.out.println("Current card: "+topCard);
        System.out.println("======================");
        while(!win)
        {
            /** Turns */
            takeATurn();
            System.out.println("======================");
            computerTurn();
        }
    }
    
    /**
     * user takes a turn
     */
    public void takeATurn()
    {
        // check for eligible cards to play -- else draw from deck
        checkCards(user, "User");
        // pick a card
        System.out.println("User hand: "+user.toString());
        System.out.print("Play a card (num on list): ");
        int num = console.nextInt();
        // ensure user input works
        while(num >user.getHand().size())
        {
            System.out.print("Try again...");
            num = console.nextInt();
        }
        Cards playCard = user.getHand().get(num-1);
        // check for eligibility
        while(playCard.getRank()!=topCard.getRank()&&!playCard.getSuit().equals(topCard.getSuit())
            &&playCard.getRank()!=8)
        {
            System.out.print("Try again...Play a card: ");
            num = console.nextInt();
            playCard = user.getHand().get(num-1);
        }
        user.useCard(playCard);
        System.out.println("You have played: "+playCard.toString());
        // Wild card 8 -- change of suit
        if(playCard.getRank()==8)
        {
            System.out.print("New suit: ");
            char newSuit = console.next().charAt(0);
            String suit = String.valueOf(newSuit).toUpperCase();
            while(!suit.equals("D")&&!suit.equals("C")&&!suit.equals("H")&&!suit.equals("S"))
            {
                System.out.print("Try again...");
                suit = console.nextLine().toUpperCase();
            }
            playCard.changeSuit(suit);
        }
        // becomes new topCard
        topCard = playCard;
        System.out.println("Current card: "+topCard);
        checkWinner();
    }
    
    /**
     * computer takes a turn
     */
    public void computerTurn()
    {
        // check for eligible cards to play -- else draw from deck
        checkCards(comp, "Computer");
        // play a card
        System.out.println("Computer hand: "+comp.printCompHand());
        for(int i=0; i<comp.getHand().size(); i++)
        {
            if(comp.getHand().get(i).getRank()==topCard.getRank()
                ||comp.getHand().get(i).getSuit().equals(topCard.getSuit())
                ||comp.getHand().get(i).getRank()==8)
            {
                Cards playCard = comp.getHand().get(i);
                comp.useCard(playCard);
                System.out.println("Computer has played: "+playCard.toString());
                // Select a suit if card played is a wild card (ie. 8)
                if(playCard.getRank()==8)
                {
                    String newSuit = comp.getHand().get(0).getSuit();
                    playCard.changeSuit(newSuit);
                    System.out.println("Change suit to: "+playCard.getSuit());
                }
                topCard = playCard;
                System.out.println("Current card: "+topCard);
                checkWinner();
                break;
            }
        }
    }
    
    /**
     * // check for eligible cards to play -- else draw from deck
     */
    public void checkCards(Hand h, String name)
    {
        boolean hasCard = false;
        while(!hasCard)
        {
            for(int i=0; i<h.getHand().size(); i++)
            {
                // either ranks or suits must match or is rank 8 (wild card)
                if(h.getHand().get(i).getRank()==topCard.getRank()
                    ||h.getHand().get(i).getSuit().equals(topCard.getSuit())
                    ||h.getHand().get(i).getRank()==8)
                {
                    hasCard = true;
                    break;
                }
            }
            // no cards works so deal a card
            if(hasCard==false)
            {
                if(d.getDeck().size()==0)
                {
                    continue;
                }
                h.addCard(d.dealCard());
                System.out.print("Enter to deal a card... ");
                String pause = console.nextLine();
                System.out.println(name+" Hand: "+h.toString());
                System.out.println("Current card: "+topCard);
                System.out.println("======================");
            }
        }
    }
    
    /**
     * check to see if anyone won
     */
    public void checkWinner()
    {
        if(user.getHand().size()==0)
        {
            System.out.println("Congrats! You win!");
            win = true;
        }
        else if(comp.getHand().size()==0)
        {
            System.out.println("Computer wins!");
            win = true;
        }
    }
}