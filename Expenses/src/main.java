/*
Ryan Suos
CECS 277 Lab
Week 2 Lab
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class main 
{
	  public static void main(String[] args) 
	  {		
		  // Creates string format for displaying doubles as U.S. currency used in this application
		  DecimalFormat money = new DecimalFormat("$#,###.00");
		  
		  Scanner in = new Scanner(System.in);
		  
		  double balance = 0;
		  
		  // Creates an ArrayList of Expenses
		  ArrayList<Expense> expenses = new ArrayList<Expense>();
		  // Counter for number of expenses
		  int expenseNumber = 0;
		  		  
		  // Creates an ArrayList of Incomes
		  ArrayList<Income> incomes = new ArrayList<Income>();
		  //Counter for number of incomes
		  int incomeNumber = 0;
		  
		  // Start of main program getting Current Balance
		  balance = currentBalance(balance);
		  
		  // Loop for main program
		  char loop = 'y';
		  while (loop != 'N')
		  {
			  header("Expenses Recorder");

			  // Main menu to give the user a list of options to choose from
			  System.out.print("Main Menu\n"
					  + "\nPlease choose from one the following:"
					  + "\n1) Update Current Balance" 
					  + "\n2) Enter Expenses"
					  + "\n3) Enter Incomes"
					  + "\n4) Calculate Current Balance After Expenses and Incomes"
					  + "\n5) Display Expenses and Expenses Grand Total Amount"
					  + "\n6) Display Incomes and Incomes Grand Total Amount"
					  + "\n7) Delete an Expense"
					  + "\n8) Delete an Income"
					  + "\n0) Summary & Exit\n");
			  int choice = 0;
			  if (in.hasNextInt()) choice = in.nextInt();
			  else 
				  {
				  String trash1 = in.next();
				  String trash2 = in.nextLine();
				  choice = -1;
				  }		  
			  
			  // Switch case to determine the interaction with user
			  switch (choice)
			  {
			  case 1:
				  balance = currentBalance(balance);
				  break;
			  case 2:
				  header("Enter Expenses");
				  System.out.print("Expense " + (expenseNumber + 1) + ": \n");
				  Expense expense = new Expense(setDescription(), setAmount());
				  expenses.add(expense);
				  expenseNumber++;
				  displayIECount("Expenses", expenseNumber);
				  totalExpenseAmount(expenseNumber, expenses);
				  break;				  
			  case 3:
				  header("Enter Incomes");
				  System.out.print("Income " + (incomeNumber + 1) + ": \n");
				  Income income = new Income(setDescription(), setAmount());
				  incomes.add(income);
				  incomeNumber++;
				  displayIECount("Incomes", incomeNumber);
				  totalIncomeAmount(incomeNumber, incomes);
				  break;
			  case 4:
				  header("Current Balance after Expenses and Incomes");
				  sum(0, balance, expenseNumber, expenses, incomeNumber, incomes);
				  break; 
			  case 5:
				  header("Expenses");
				  displayExpenses(expenseNumber, expenses);
				  break;
			  case 6:
				  header("Incomes");
				  displayIncomes(incomeNumber, incomes);
				  break;
			  case 7:
				  // Allows the user to delete an Expense
				  header("Delete an Expense");
				  displayExpenses(expenseNumber, expenses);
				  expenseNumber = deleteEItem(expenseNumber, expenses);
				  break;
			  case 8:
				  // Allows the user to delete an Income
				  header("Delete an Income");
				  displayIncomes(incomeNumber, incomes);
				  incomeNumber = deleteIItem(incomeNumber, incomes);
				  break;
			  case 0:
				  header("Goodbye");
				  sum(1, balance, expenseNumber, expenses, incomeNumber, incomes);
				  loop = 'N';
				  break;
			 default:
				 	System.out.println("Invalid Choice.");
				 	break;
			  }	  
		  }
	  }
	  
	  /*
	   * @param b keeps track of the balance of the user
	   * @return balance returns current balance of user to main to be used by the whole program
	   */
	  public static double currentBalance(double b)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");
		  
		  Scanner in = new Scanner(System.in);
		  
		  double balance = b;
		  
		  if (b != 0)
		  {
		  
			  header("Update Current Balance");
			  System.out.printf("%-40s %40s\n" , "Your current balance is:", money.format(balance));
		  
			  char update;
			  System.out.print("\nDo you want to update your current balance [Y/N]? ");
			  update = Character.toUpperCase(in.next().charAt(0));
		  if (update == 'Y')
		  {
			  char loop = 'n';		  
			  while (loop != 'Y')
			  {
				  boolean doubleCheck = true;
				  while (doubleCheck)
				  {
					  System.out.print("\nEnter your current balance: $");
					  if (in.hasNextDouble())
					  {
						  doubleCheck = false;
						  balance = in.nextDouble();
					  }
					  else
					  {
						  String trash1 = in.next();
						  String trash2 = in.nextLine();
						  System.out.printf("Invalid input.\n");
					  }
				  }
				  
				  System.out.print("Is " + money.format(balance) + " correct?[Y/N] ");
				  loop = Character.toUpperCase(in.next().charAt(0));
			  }
			  
		  }
		  		  
		  System.out.printf("\n%-40s %40s\n" , "Your current balance is:", money.format(balance));
		  return balance;
	  }
		  else
		  {
			  header("Enter Current Balance");
			  boolean loop = true;
			  while (loop)
			  {
				  System.out.printf("Enter your current balance: $");
				  if (in.hasNextDouble()) 
					  {
					  loop = false;
					  balance = in.nextDouble();
					  }
				  else 
				  {
					  String trash1 = in.next();
					  String trash2 = in.nextLine();
					  System.out.printf("Invalid input.\n");
				  }
				  
			  }
		  }
		  
		  return balance;
	  }
	  
	  /*
	   * @param s displays either the input "Expense" or "Income"
	   * @param x displays the number of items in either Expense or Income
	   * Displays count of either Expenses or Incomes
	   */
	  public static void displayIECount(String s, int x)
	  {
		  String display = ("Number of " + s + ":");
		  System.out.printf("\n%-40s %40d\n", display, x);
	  }
	  
	  /*
	   * Sets the description of either an Expense or an Income
	   */
	  public static String setDescription()
	  {
		  Scanner in = new Scanner(System.in);
		  System.out.print("Enter a description: ");
		  return in.nextLine();
	  }

	  /*
	   * Sets the amount of either an Expense or an Income
	   */
	  public static double setAmount()
	  {
		  Scanner in = new Scanner(System.in);
		  
		  double amount = 0;
		  
		  char loop = 'y';
		  while ( loop != 'N' )
		  {
			  System.out.print("Enter an amount: $");
			  if (in.hasNextDouble()) 
				  {
				  amount = in.nextDouble();
				  break;
				  }
			  else
			  {
				  String trash1 = in.next();
				  String trash2 = in.nextLine();
				  System.out.println("Invalid Input");
			  }
			  
		  }
		  return amount;
	  }
	  
	  /*
	   * @param x takes in the counter for Incomes
	   * @param i takes in the array of Incomes to get the grand total of income amounts
	   * Gets the total income grand total
	   */
	  public static void totalIncomeAmount(int x, ArrayList<Income> i)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");
		  
		  double totalIncomeAmount = 0;
		  
		  for (int j = 0; j < x; j++)
		  {
			  totalIncomeAmount += i.get(j).amount;
		  }
		  
		  System.out.printf("%-40s %40s\n", "Total Income Amount:", money.format(totalIncomeAmount));
	  }
	  
	  /*
	   * @param x takes in the counter for Expenses
	   * @param e takes in the array of Expenses to get the grand total of expenses
	   * Gets the total expenses grand total
	   */
	  public static void totalExpenseAmount(int x, ArrayList<Expense> e)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");
		  
		  double totalExpenseAmount = 0;

		  for (int j = 0; j < x; j++)
		  {
			  totalExpenseAmount += e.get(j).amount;
		  }
		  
		  System.out.printf("%-40s %40s\n", "Total Expense Amount:", money.format(totalExpenseAmount));
	  }
	  
	  /*
	   * @param a takes in an integer to determine whether to display output for calculations or exiting the program
	   * @param balance takes in the current balance of the user
	   * @param x takes in the counter for Expenses
	   * @param e takes in the array of Expenses
	   * @param y takes in the counter for Incomes
	   * @param i takes in the array of Incomes
	   * Shows the grand total and calculations for everything the user has input so far
	   */
	  public static void sum(int a, double balance, int x, ArrayList<Expense> e, int y, ArrayList<Income> i)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");
		  
		  if (a == 0) System.out.printf("\n%-40s %40s\n" , "Current Balance:", money.format(balance));
		  if (a == 1) System.out.printf("\n%-40s %40s\n" , "Initial Balance:", money.format(balance));
		  
		  double expenses = 0;
		  displayIECount("Expenses", x);
		  for (int j = 0; j < x; j++)
		  {
			  expenses += e.get(j).amount;
		  }
		  //System.out.printf("%-40s %40s\n" , "Current Expenses:", money.format(expenses));
		  
		  displayExpenses(x, e);
		  
		  double incomes = 0;
		  displayIECount("Incomes", y);
		  for (int j = 0; j < y; j++)
		  {
			  incomes += i.get(j).amount;
		  }
//		  System.out.printf("%-40s %40s\n" , "Current Incomes:", money.format(incomes));
		  
		  displayIncomes(y, i);
		  
		  if (a == 0) System.out.printf("\n%-43s %37s\n" , "Balance after Expenses and Incomes:", money.format(balance + incomes - expenses));
		  if (a == 1) System.out.printf("\n%-43s %37s\n" , "Final Balance:", money.format(balance + incomes - expenses));
	  }	
	  
	  /*
	   * @param x takes in the counter for Expenses
	   * @param e takes in the array of Expenses
	   * Lists and displays all Expenses, their cost, and sum of their costs
	   */
	  public static void displayExpenses(int x, ArrayList<Expense> e)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");

		  for (int i = 0; i < x; i++)
		  {
			  System.out.printf("%2d] %-36s %40s\n", i + 1, e.get(i).description, money.format(e.get(i).amount));
		  }

		  System.out.printf("\n");
		  displayIECount("Expenses", x);
		  totalExpenseAmount(x, e);

	  }
	  
	  /*
	   * @param x takes in the counter for Incomes
	   * @param i takes in the array of Incomes
	   * Lists and displays all Incomes, their amount, and sum of their amounts
	   */
	  public static void displayIncomes(int x, ArrayList<Income> income)
	  {
		  DecimalFormat money = new DecimalFormat("$#,###.00");

		  for (int i = 0; i < x; i++)
		  {
			  System.out.printf("%2d] %-36s %40s\n", i + 1, income.get(i).description, money.format(income.get(i).amount));
		  }

		  System.out.printf("\n");
		  displayIECount("Incomes", x);
		  totalIncomeAmount(x, income);
	  }  
	  
	  /*
	   * @param s takes in a the header title to display
	   * Displays a header for the main program and sub programs
	   */
	  public static void header(String s)
	  {
		  for (int i = 0; i <= 80; i++) System.out.print("=");
		  
		  
		  System.out.printf("\n\n");
		  
		  int spaces = 40 - (s.length() / 2);
		  for (int i = 0; i <= spaces; i++) System.out.print(" ");
		  
		  System.out.print(s + "\n\n");
		  
		  for (int i = 0; i <= 80; i++) System.out.print("=");
		  System.out.println("");
	  }
	  
	  public static int deleteEItem(int e, ArrayList<Expense> expenses)
	  {
		  Scanner in = new Scanner (System.in);
		  int expenseNumber = e;
		  int choiceEDelete = 1;
		  
		  while (choiceEDelete != 0)
		  {
			  System.out.print("Which expense would you like to delete [0 to Exit]: ");
			  if (in.hasNextInt()) choiceEDelete = in.nextInt();
			  else
			  {
				  String trash1 = in.next();
				  String trash2 = in.nextLine();
				  choiceEDelete = -1;
			  }
			  
			  if (choiceEDelete == 0) break;
			  if (choiceEDelete > 0 && choiceEDelete <= expenseNumber)
			  {
				  choiceEDelete += -1;
				  expenses.remove(choiceEDelete);
				  expenseNumber += -1;
				  displayExpenses(expenseNumber, expenses);
			  }
			  else
			  {
				  System.out.println("Invalid Choice.");
			  }
			  
		  }
		  
		  return expenseNumber;
	  }
	  
	  public static int deleteIItem(int i, ArrayList<Income> incomes)
	  {
		  Scanner in = new Scanner (System.in);
		  int incomeNumber = i;
		  
		  int choiceIDelete = 1;
		  while (choiceIDelete != 0)
		  {
			  System.out.print("Which income would you like to delete [0 to Exit]: ");
			  if (in.hasNextInt()) choiceIDelete = in.nextInt();
			  else
			  {
				  String trash1 = in.next();
				  String trash2 = in.nextLine();
				  choiceIDelete = -1;
			  }
			  if (choiceIDelete == 0) break;
			  if (choiceIDelete > 0 && choiceIDelete <= incomeNumber)
			  {
				  choiceIDelete += -1;
				  incomes.remove(choiceIDelete);
				  incomeNumber += -1;
				  displayIncomes(incomeNumber, incomes);
				  
			  }
			  else
			  {
				  System.out.println("Invalid Choice.");
			  }
			  
		  }
		  
		  return incomeNumber;
	  }
}

/*
 * Expense object for creating expense objects
 */
class Expense
{
	String description;
	double amount;
	
	Expense(String description, double amount)
	{
		this.amount = amount;
		this.description = description;
	}
}

/*
 * Income object for creating income objects
 */
class Income
{
	String description;
	double amount;
	
	Income(String description, double amount)
	{
		this.amount = amount;
		this.description = description;
	}
}
