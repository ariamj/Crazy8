import java.util.*;

public class MyProgram
{
    public static void main(String[] args)
    {
        CrazyEight game = new CrazyEight();
        game.playGame();
    }
}


/**
 * suit = colour
 * can only put either same colour or same number as before
 * number 8 = change suit to anything
 * Option: jack = skip
 *         queen = reverse
 * 
 * start: 2 players = 7 cards each, more = 5 cards
 *      -> remaining cards face down in stack
 *      -> top card gets revealed
 * 
 * Winner: first to discard all cards
 * 
 * Points: end of game, remaining pts in loser's hands goes to winner
 *      -> 10 pts for face cards
 *      -> rank = pts (1 pt for Ace)
 *      -> 50 pts for 8
 * 
 * STUFF NEEDED:
 * -dealing out the cards -> Keep track of cards in hand
 * -getting first card -> Keep track of card on top
 * -player turn -> either place card or draw from stack
 *              -> draw: continue until has a card that works
 *              -> place: card must match either suit or rank
 *                  -> match rank: suit changes to card suit
 *                  -> 8 = any suit & change to any suit
 *                  -> J = skip next player turn
 *                  -> Q = reverse order of turns
 * -other player/compt turn -> same as player turn
 * ---> if stack empty, turn is skipped
 * -continue until a player has no more cards
 * 
 * 
 * https://www.ducksters.com/games/crazy_eights_rules.php
 * 
 * 
 * 
 * CLASSES:
 *      Hand -> keep track of cards in hand
 *           -> able to add a card
 *           -> able to remove/use a card
 *      Cards -> creates a card
 *            -> have a rank and suit
 *      Deck -> create a deck of 52 cards
 *           -> able to deal out a card
 *      CrazyEight -> play the game
 *                 -> variable to track top card
 *                 -> rules (suit on suit, rank on rank, etc)
 */