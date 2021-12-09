package Week_1_Lab;

import java.util.Scanner;
import java.util.Random;

public class DiceRoll {

	public static void diceRoll() {
		
		Scanner in = new Scanner(System.in);
		
		char game = 'Y';
		
		while (game == 'Y')
		{
			System.out.println("How many dice would you like to roll?");
			int diceCount = in.nextInt();
			
			while (diceCount < 0)
			{
				System.out.println("Please enter a number 0 or greater.");
				diceCount = in.nextInt();
			}
			
			for (int i=0; i < diceCount; i++) {
				
				System.out.println("Dice face #" + (i + 1));
				Random rand = new Random();
				int dieNumber = rand.nextInt(6) + 1;
				
				switch (dieNumber) {
				case 1:
					System.out.println(" _____ ");
					System.out.println("|     |");
					System.out.println("|  O  |");
					System.out.println("|     |");
					System.out.println(" ----- ");
					break;
				case 2:
					System.out.println(" _____ ");
					System.out.println("|    O|");
					System.out.println("|     |");
					System.out.println("|O    |");
					System.out.println(" ----- ");
					break;
				case 3:
					System.out.println(" _____ ");
					System.out.println("|O    |");
					System.out.println("|  O  |");
					System.out.println("|    O|");
					System.out.println(" ----- ");
					break;
				case 4:
					System.out.println(" _____ ");
					System.out.println("|O   O|");
					System.out.println("|     |");
					System.out.println("|O   O|");
					System.out.println(" ----- ");
					break;
				case 5:
					System.out.println(" _____ ");
					System.out.println("|O   O|");
					System.out.println("|  O  |");
					System.out.println("|O   O|");
					System.out.println(" ----- ");
					break;
				case 6:
					System.out.println(" _____ ");
					System.out.println("|O   O|");
					System.out.println("|O   O|");
					System.out.println("|O   O|");
					System.out.println(" ----- ");
					break;
				}
				
			}
			
			System.out.println("Would you like to roll again? Y/N");
			game = Character.toUpperCase(in.next().charAt(0));
			
			while (game != 'Y' && game != 'N') {
				System.out.println("Please choose Y or N");
				System.out.println("Would you like to roll again? Y/N");
				game = Character.toUpperCase(in.next().charAt(0));
			}		
		}
	}	
}
