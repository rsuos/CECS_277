package Week_1_Lab;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) {
		
		int program = 9;
		
		Scanner in = new Scanner(System.in);
		
		while (program != 0) {
			
			System.out.println( "Choose a program [1-3]:");
			System.out.println( "1) SimpleIOBranching");
			System.out.println( "2) DieRoll");
			System.out.println( "3) DiceRoll");
			System.out.println( "0) Exit");
			
			program = in.nextInt();
			
			switch (program) {
			case 1:
				SimpleIOBranching.simpleIOBranching();
				break;
			case 2:
				DieRoll.dieRoll();
				break;
			case 3:
				DiceRoll.diceRoll();
			case 0:
				System.out.println("Thank you.");
				break;
			
			default: 
				System.out.println( "Choice not in range." );
				break;			
			}
			
			if (program == 0) break;
			
		}

	}

}
