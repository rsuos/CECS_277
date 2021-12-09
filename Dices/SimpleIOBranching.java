package Week_1_Lab;

import java.util.Scanner;

public class SimpleIOBranching {
		public static void simpleIOBranching() {
		System.out.println("Enter an integer ranging from 1 - 10.");
		
		Scanner in = new Scanner(System.in);
		
		int input = in.nextInt();
		
		if (input < 1 || input > 10) System.out.println("Integer must be in the range 1 - 10, inclusive.");
		else System.out.println("Thank you!");
		}
}
