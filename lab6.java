import java.util.Scanner;

public class lab6 {

    public static LinkedList initialize_deck() {

        LinkedList deck = new LinkedList();

        // populate linked list with a single deck of cards
        for (Card.suites s : Card.suites.values()) {
            for(Card.ranks r : Card.ranks.values()) {
                if (r != Card.ranks.NULL && s != Card.suites.NULL) {
                    Card newCard = new Card(s, r);
                    //newCard.print_card();
                    deck.add_at_tail(newCard);
                }
            }
        }

        return deck;
    }

    private static void play_blind_mans_bluff(LinkedList player1, LinkedList computer, LinkedList deck) {
        System.out.println("\nStarting Blind mans Bluff \n");
        Scanner control = new Scanner(System.in);
        int rageCntr=0;
        int comWins=0;
        int plyWins=0;
        while (player1.size>0) {
            if (rageCntr==3) {
                rage_quit(player1,computer,deck);
            }
            computer.head.data.print_card();
            System.out.println();
            System.out.println("Higher or lower?");
            String input = control.nextLine();

            if (input.equals("higher")||input.equals("Higher")) {
                if(computer.head.data.getRank().compareTo(player1.head.data.getRank())>=1) {
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("You lose");
                    comWins++;
                    rageCntr++;
                    deck.add_at_tail(computer.remove_from_head());
                    deck.add_at_tail(player1.remove_from_head());
                } else {
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("You win");
                    plyWins++;
                    rageCntr=0;
                    deck.add_at_tail(computer.remove_from_head());
                    deck.add_at_tail(player1.remove_from_head());
                }
            } else if (input.equals("lower")||input.equals("Lower")) {
                if(computer.head.data.getRank().compareTo(player1.head.data.getRank())>=1) {
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("You win");
                    plyWins++;
                    rageCntr=0;
                    deck.add_at_tail(computer.remove_from_head());
                    deck.add_at_tail(player1.remove_from_head());
                } else if (computer.head.data.getSuit().compareTo(player1.head.data.getSuit())>0){
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("Tie goes to suit, computer wins!");
                } else if (computer.head.data.getSuit().compareTo(player1.head.data.getSuit())<0){
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("Tie goes to suit, player wins!");
                } else {
                    System.out.print("You're card is a ");
                    player1.head.data.print_card();
                    System.out.println();
                    System.out.println("You lose");
                    comWins++;
                    rageCntr++;
                    deck.add_at_tail(computer.remove_from_head());
                    deck.add_at_tail(player1.remove_from_head());
                }
            }
        }
        System.out.println("Player wins: "+plyWins);
        System.out.println("Computer wins: "+comWins);
    }
    public static void rage_quit(LinkedList player1, LinkedList computer, LinkedList deck){
        System.out.println("Rage quit!");
        while (computer.size>0) {
            deck.add_at_tail(computer.remove_from_head());
        }
        while(player1.size>0){
            deck.add_at_tail(player1.remove_from_head());
        }
        deck.shuffle(512);
        for(int i=0;i<5;i++){
            player1.add_at_tail(deck.remove_from_head());
        }
        for(int i=0;i<5;i++){
            computer.add_at_tail(deck.remove_from_head());
        }
    }

    public static void main(String[] args) {

        // create a deck (in order)
        LinkedList deck = initialize_deck();
        deck.print();
        deck.sanity_check(); // because we can all use one


        // shuffle the deck (random order)
        deck.shuffle(512); //was 512
        deck.print();
        deck.sanity_check(); // because we can all use one

        // cards for player 1 (hand)
        LinkedList player1 = new LinkedList();
        // cards for player 2 (hand)
        LinkedList computer = new LinkedList();

        int num_cards_dealt = 5;
        for (int i = 0; i < num_cards_dealt; i++) {
            // player removes a card from the deck and adds to their hand
            player1.add_at_tail(deck.remove_from_head());
            computer.add_at_tail(deck.remove_from_head());
        }

        // let the games begin!
        play_blind_mans_bluff(player1, computer, deck);
    }
}
