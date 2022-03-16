import java.util.*;

import java.awt.Point;

import java.io.FileReader;
import java.io.FileNotFoundException;


/**
 * Represents the map traversed by the trainer
 * @author Ryan Suos
 * @author John Teano
 */
public class Map {
	private char [][] map;
  
	private boolean [][] revealed;

  static Map instance = null; // The instance used to check if Map has been initalized
	
	/**
	 * Default constructor for the map class.
	 * Initializes the map and revealed map 2D array.
	 */
	private Map()	{
		map = new char[5][5];
		revealed = new boolean[5][5];	// All elements initialize to false
	}

  /**
	 * Returns the instance of Map if it exists
   * @return instance contains the single instance of Map
	 */
	public static Map getInstance()	{
		if(instance == null)  {
      instance = new Map();
    }

    return instance;
	}
	
	/**
	 * Loads chosen text file to save onto map
	 * @param mapNum is the map number chosen
	 */
	public void loadMap(int mapNum)	{
		try {
			String mapFileName;
			
			// Switch statement to determine the map chosen
			switch(mapNum)	{
				case 1: mapFileName = "Area1.txt";
						break;
				case 2: mapFileName = "Area2.txt";
						break;
				case 3: mapFileName = "Area3.txt";
						break;
				default: mapFileName = "Invalid file name";
						break;
			}
			
			// Takes in the text file name in the mapFileName variable
			Scanner s = new Scanner(new FileReader(mapFileName)); // Had to point directly to the file path in my local computer. Once we move to replit, just remove the file path and have mapFileName only - Jt
			
			// Parses the values from the text file one character at a time
			// Stores each character to the corresponding position in the map array
			while(s.hasNextLine()) {
		         for (int i=0; i < map.length; i++) {
		            String[] line = s.nextLine().trim().split(" ");
		            for (int j=0; j<line.length; j++) {
		               map[i][j] = line[j].charAt(0);
		            }
		         }
		      }
			revealed = new boolean[5][5];
		} catch (FileNotFoundException e)	{
			System.out.println("Error reading file. Please try again.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the character at a specific point
	 * @param p is the exact point provided
	 * @return charAtLoc is the character that is located at the point passed in the parameter
	 */
	public char getCharAtLoc(Point p)	{
		// x is column, y is row
		// return the char in the specific position
		
		char charAtLoc;
		int x = p.x;
		int y = p.y;
		
		if(x < 0 || x > 4 || y < 0 || y > 4)	{
			charAtLoc = 'e'; // char e represents an error. that means that the location is out of bounds
		}
		
		else	{
			charAtLoc = map[x][y]; // char given at a specific location means that the location is available for the player to move to
		}
		
		return charAtLoc;
	}
	
	/**
	 * Takes the Point class object and convert to a string
	 * @param p is the Point class object
	 * @return returned formatted string representation of the Point object
	 */
	public String mapToString(Point p)	{
		String returned = "";
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if ((int)p.getX() == i && (int)p.getY() == j) {
					returned += "* "; // Player position set to '*'
					continue;
				}
				
				if (revealed[i][j] == true) {
					returned += map[i][j] + " "; // Case when point in the map is revealed
					continue;
				}
				
				if (revealed[i][j] == false) {
					returned += "x "; // Case when point in map is not yet revealed
					continue;
				}
			}
			
			returned += "\n";
		}
		
		return returned;
	}
	
	/**
	 * Locate the starting point in a map
	 * @return startPoint is the coordinate in the map that contains the char 's'
	 */
	public Point findStart()	{
		//int[][] startPoint = null;
		Point startPoint = new Point();
		for (int i = 0; i < map.length; i++)
		{
			for (int j = 0; j < map[i].length; j++)
			{
				if (map[i][j] == 's') startPoint.setLocation(i, j);
			}
		}
		return startPoint;
	}
	
	/**
	 * Reveals the actual character in the location that a player has passed
	 * @param p contains the point that will be revealed in the console
	 */
	public void reveal(Point p)	{
		if(p.getX() >= 0 && p.getX() <= 4 && p.getY() >= 0 && p.getY() <= 4)	{  //Check to reveal only if point is within the map
			revealed[(int) p.getX()][(int) p.getY()] = true;
		}
	}
	
	/**
	 * Remove character at location Point p
	 * I'm assuming it has something to do with the character returning to path that
	 * has already been passed. Prevents from abusing an item, pokemon or character location.
	 * @param p is the point in which the character will be removed
	 */
	public void removeCharAtLoc(Point p)	{
		// i w p
		if (map[(int)p.getX()][(int)p.getY()] == 'i' || map[(int)p.getX()][(int)p.getY()] == 'p' || map[(int)p.getX()][(int)p.getY()] == 'w') map[(int)p.getX()][(int)p.getY()] = 'n';
	}
	
}
