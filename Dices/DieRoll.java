package Week_1_Lab;

import java.util.Scanner;
import java.util.Random;

public class DieRoll {
	
	public static void dieRoll() {
		
		Scanner in = new Scanner(System.in);
		
		char answer = 'Y';
		
		while (answer == 'Y')
		{
			System.out.println( "Would you like to roll a die? Y/N");
			
			answer = Character.toUpperCase(in.next().charAt(0));
			
			while (answer != 'Y' && answer != 'N') {
				System.out.println("Please enter Y or N");
				System.out.println( "Would you like to roll a die? Y/N");
				answer = Character.toUpperCase(in.next().charAt(0));
			}
			
			if (answer == 'N') break;
			
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
		
		System.out.println("Goodbye!");
		
	}
}
