import java.awt.Point; // Implements the Point class
import java.util.ArrayList;


/**
 * Contains all specific Trainer information that extends the Entity class
 * @author Ryan Suos
 * @author John Teano
 */
public class Trainer extends Entity{

  private int money;

  private int potions;

  private int pokeballs;

  private Point loc;

  private ArrayList<Pokemon> pokemon;
  
  Map map = Map.getInstance();  // Instance of the map used throughout Trainer class for map movement
  
  /** Overloaded Constructor
   * @param n is the trainer name
   * @param p is the starter pokemon
   * @param m is the starting map
   */
  public Trainer(String n, Pokemon p)  {
    super(n);
    money = 25;
    potions = 1;
    pokeballs = 5;
    pokemon = new ArrayList<Pokemon>(); // Trainer can have 6 pokemon max at one time
    pokemon.add(p);
    loc = map.findStart();
  }


  /**
   * Returns the player's amount of money
   * @return money stores the amount of money
   */
  public int getMoney() {
    return money;
  }

  /**
   * Determines whether the player has enough money to spend on the amount given
   * @return true if the player has enough money to cover the amount; otherwise false
   */
  public boolean spendMoney(int amt)  {
    
    // if player has enough money to cover the amount, return true
    if(money >= amt) {
      money -= amt;
      return true;
    }

    // if money is less than the amount, return false
    else {
      return false;
    }
  }

  /**
   * Add to the player's money based on the amount given
   * @param amt determines the value to be added to the player's money
   */
  public void receiveMoney(int amt)  {
    money += amt;
  }

  /**
   * Determines whether the player has potions
   * @return true if the player has potions; otherwise false
   */
  public boolean hasPotion()  {
    boolean hasPotion;

    // if player has potions, return true
    if(potions > 0)  {
      hasPotion = true;
    }
    else {
      hasPotion = false;
    }

    return hasPotion;
  }

  /**
   * Trainer receives one potion
   */
  public void receivePotion() {
    potions += 1;
  }

  /**
   * Heal a specific player's pokemon as long as there are potions available
   * @param pokeIndex contains the value of the index to call on a specific pokemon in the
   * pokemon ArrayList
   */
  public void usePotion(int pokeIndex)  {
    Pokemon p = pokemon.get(pokeIndex - 1);
    // Conditional to check if the player has a potion in the inventory
    if(potions > 0) {
      p.heal();
      potions -= 1;
      
      PokemonGenerator createPokemon = PokemonGenerator.getInstance();

      Pokemon pokemonHolder = pokemon.get(pokeIndex - 1);

      pokemonHolder = createPokemon.addRandomBuff(pokemonHolder);

      removePokemon(pokeIndex);

      pokemon.add(pokeIndex - 1, pokemonHolder);
    }
  }

  /**
   * Determines whether the player has pokeballs
   * @return hasPokeball is true if player has pokeballs, otherwise false
   */
  public boolean hasPokeball()  {
    boolean hasPokeball;

    // if player has a pokeball, return true
    if(pokeballs > 0) {
      hasPokeball = true;
    }

    // if player has no pokeballs, return false
    else  {
      hasPokeball = false;
    }

    return hasPokeball;
  }

  /**
   * Trainer receives one pokeball
   */
  public void receivePokeball() {
    pokeballs += 1;
  }

  /**
   * Determines if the attempt to catch Pokemon p has succeeded
   * @param p is the pokemon that the player is attempting to catch
   */
  public boolean catchPokemon(Pokemon p)  {
    // The attempt should be less likely if HP is high
    // If pokemon HP < 50%, then we should increase the chance of catching the pokemon
    // Chance can be represented by an integer, the closer it is to 1, then the likelier the player will catch the pokemon.
    // ex. wild pokemon hp/maxHP = 5/20 = .25
    // 1 - .25 = .75 which translates to a 75% chance to catch the wild pokemon
    // ex. wild pokemon hp/maxHP = 1/20 = .05
    // 1 - .05 = .95 which translates to a 95% chance to catch the wild pokemon

    if (this.hasPokeball() == true)
    {
      pokeballs -=1;

      boolean caughtPokemon = false;

      double chance = 0;

      chance = 1 - ((double) p.getHp() / (double) p.getMaxHp()); // The lower the HP percentage, the lesser we have to take away from 1

      // Generate a value between 1 to 0
      // If the random value is less than the chance, then pokemon is caught
      
      if(RandomNumberGenerator.RandomDouble() < chance) {
        caughtPokemon = true;
        System.out.println("Shake...Shake...Shake...");
        System.out.println("You've caught a " + p.getName() + "!");
        pokemon.add(p);
        
      }
      if (caughtPokemon == false) System.out.println(p.getName() + " broke free!");
      
      return caughtPokemon;
      }
      else
      {
       System.out.println("You don't have any PokeBalls!");
        return false;
      }

  }

  /**
   * Provide the x,y location
   * @return Point is the current location
   */
  public Point getLocation()  {
    return loc.getLocation(); // Return (x,y) coordinates
  }

  /**
   * Moves the player up one unit
   * @return nextChar is the character in the next location
   */
  public char goNorth() {
    char nextChar;
    int x = loc.x;
    int y = loc.y;
    
    // Set nextLocation to (x,y+1) to provide next move to map
    Point nextLocation = new Point(x-1,y);
    
    // Check if that new position still contains a char in map
    // If nextChar = e, player is out of bounds and player location will not change. e will be returned
    // which means no prompt should occur.
    
    nextChar = map.getCharAtLoc(nextLocation);
    
    // If nextChar = anything else, set currentLocation to nextLocation
    // nextChar != 'e' also indicates that we have a valid char from the map
    // ***Must also alter map to reflect the player(*) moved to the new position and reveal the previous position***

    	map.reveal(loc);
    	loc = nextLocation;

    return nextChar;
  }

  /**
   * Moves the player down one unit
   * @return nextChar is the character in the next location
   */
  public char goSouth() {
	    char nextChar;
	    int x = loc.x;
	    int y = loc.y;
	    
	    // Set nextLocation to (x,y-1) to provide next move to map
	    Point nextLocation = new Point(x+1,y);
	    
	    // Check if that new position still contains a char in map
	    // If nextChar = e, player is out of bounds and player location will not change. e will be returned
	    // which means no prompt should occur.
	    
	    nextChar = map.getCharAtLoc(nextLocation);
	    // If nextChar = anything else, set currentLocation to nextLocation
	    // nextChar != 'e' also indicates that we have a valid char from the map
	    // ***Must also alter map to reflect the player(*) moved to the new position and reveal the previous position***

	    map.reveal(loc);
	    loc = nextLocation;
	  
	    return nextChar;
  }

  /**
   * Moves the player to the right one unit
   * @return nextChar is the character in the next location
   */
  public char goEast()  {
	    char nextChar;
	    int x = loc.x;
	    int y = loc.y;
	    
	    // Set nextLocation to (x+1,y) to provide next move to map
	    Point nextLocation = new Point(x,y+1);
	    
	    // Check if that new position still contains a char in map
	    // If nextChar = e, player is out of bounds and player location will not change. e will be returned
	    // which means no prompt should occur.
	    
	    nextChar = map.getCharAtLoc(nextLocation);
	    // If nextChar = anything else, set currentLocation to nextLocation
	    // nextChar != 'e' also indicates that we have a valid char from the map
	    // ***Must also alter map to reflect the player(*) moved to the new position and reveal the previous position***

	    map.reveal(loc);
      
	    loc = nextLocation;

	    return nextChar;
  }

  /**
   * Moves the player to the left one unit
   * @return nextChar is the character in the next location
   */
  public char goWest()  {
	    char nextChar;
	    int x = loc.x;
	    int y = loc.y;
	    
	    // Set nextLocation to (x-1,y) to provide next move to map
	    Point nextLocation = new Point(x,y-1);
	    
	    // Check if that new position still contains a char in map
	    // If nextChar = e, player is out of bounds and player location will not change. e will be returned
	    // which means no prompt should occur.
	    
	    nextChar = map.getCharAtLoc(nextLocation);
	    // If nextChar = anything else, set currentLocation to nextLocation
	    // nextChar != 'e' also indicates that we have a valid char from the map
	    // ***Must also alter map to reflect the player(*) moved to the new position and reveal the previous position***

	    map.reveal(loc);	
	    loc = nextLocation;

	    return nextChar;
  }

  /**
   * Counts the number of pokemon currently owned by the player
   * @return numPokemon represents the number of pokemon in the player's roster
   */
  public int getNumPokemon()  {
    int numPokemon = pokemon.size();
    
    return numPokemon;
  }

  /**
   * Heals all pokemon in the player's roster to their maximum hit points
   */
  public void healAllPokemon()  {

    // Iterate through the player's pokemon ArrayList
    for(int i = 0; i < pokemon.size(); i++) {
      // Heal each pokemon to maxHP
      pokemon.get(i).heal();
    }
  }

  /**
   * Buffs all pokemon in the player's array list
   */
  public void buffAllPokemon()  {
    PokemonGenerator createPokemon = PokemonGenerator.getInstance();

    Pokemon pokemonHolder;

    ArrayList<Pokemon> pokemonArrayHolder = new ArrayList<Pokemon>();

    for(int i = 0; i < pokemon.size();i++) {
      pokemonHolder = createPokemon.addRandomBuff(pokemon.get(i));

      pokemonArrayHolder.add(pokemonHolder);
    }

    pokemon.clear();

    for(int j = 0; j < pokemonArrayHolder.size();j++) {
      pokemonHolder = pokemonArrayHolder.get(j);

      pokemon.add(pokemonHolder);
    }
  }

  /**
   * Debuffs all pokemon in the player's array list
   */
  public void debuffAllPokemon()  {
    PokemonGenerator createPokemon = PokemonGenerator.getInstance();

    Pokemon pokemonHolder;

    ArrayList<Pokemon> pokemonArrayHolder = new ArrayList<Pokemon>();

    for(int i = 0; i < pokemon.size();i++) {
      pokemonHolder = createPokemon.addRandomDebuff(pokemon.get(i));

      pokemonArrayHolder.add(pokemonHolder);
    }

    pokemon.clear();

    for(int j = 0; j < pokemonArrayHolder.size();j++) {
      pokemonHolder = pokemonArrayHolder.get(j);

      pokemon.add(pokemonHolder);
    }
    
  }

  /**
   * Debuff a specific pokemon in the player's array list
   * @param index is the index of the pokemon to be debuffed
   */
  public void debuffPokemon(int index)  {
    PokemonGenerator createPokemon = PokemonGenerator.getInstance();

    Pokemon pokemonHolder = pokemon.get(index - 1);

    pokemonHolder = createPokemon.addRandomDebuff(pokemonHolder);

    removePokemon(index);

    pokemon.add(index - 1, pokemonHolder);
  }

  /**
   * Retrieves the Pokemon object stored in the ArrayList based on the index
   * @param index is the index for the specific pokemon in the array list
   * @return p is the pokemon chosen
   */
  public Pokemon getPokemon(int index)  {
    Pokemon p = pokemon.get(index - 1);
    
    return p;
  }

  /**
   * Retrieves the list of pokemon in the player's roster
   * @return pokemonList is the Trainer's pokemon stored in the array list saved as a String
   */
  public String getPokemonList()  {
    // Store the detailed information of every pokemon in the player's roster
    String pokemonList = "";
    
    // Iterate through the player's pokemon ArrayList
    for(int i = 0; i < pokemon.size(); i++) {
    	int pokeNumber = i + 1;
    	pokemonList += pokeNumber + ". " +  pokemon.get(i).toString();
    }

    return pokemonList;
  }

  /**
   * Removes the Pokemon object stored in the ArrayList based on the index
   * @param index is the index of the specific pokemon in the array list
   * @return p is the pokemon removed
   */
  public Pokemon removePokemon(int index)  {
    Pokemon p = pokemon.remove(index - 1);

    return p;
  }
 
  /**
   * Overrides the toString() method of the Trainer class
   * @return is the detailed information of the trainer returned as a String
   */
  @Override
  public String toString()  {
    // What do we want to see when we print out a Trainer object?
    // Name CurrentHP
    // Money: money
    // Potions: potions
    // Pokeballs: pokeballs
    // Pokemon List. . .
    return super.toString() + "Money: " + money + "\n" + "Potions: " + potions + "\n" + "Poke Balls: " + pokeballs + "\n" + "Pokemon" + "\n" + "-------" + "\n" + getPokemonList();
  }
}