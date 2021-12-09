import java.util.LinkedList;
import java.util.Scanner;

class Main {

	public static void printMenu() {
		System.out.println("\n\nWelcome to the Main Menu");
		System.out.println("0: Exit");
		System.out.println("1: Test CardsUtil Functions");
		System.out.println("2: Play Greedy War!");
		System.out.print("\nYour selection: ");

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int mainSelection;

		do {
			printMenu();

			mainSelection = Integer.parseInt(in.nextLine().trim());
			if (mainSelection == 1) {
				CardsUtil.testCardsUtil();
				System.out.println();
			} else if (mainSelection == 2) {
				// creating the standard deck
				LinkedList<Card> deck = CardsUtil.createStandardDeck();
        
        // dealing the cards (dealCards shuffles before dealing)
        LinkedList<Card> computerHand = (LinkedList<Card>) CardsUtil.dealCards(deck, 26);
        LinkedList<Card> userHand = (LinkedList<Card>) CardsUtil.dealCards(deck, 26);

        System.out.println("FIXME: Implement the rest of the game.");

			} else if (mainSelection != 0){ 
        
				System.out.println("Your selection is invalid.  You will be taken back to the main menu.");
			}

		} while (mainSelection != 0);

		in.close();

	}
}