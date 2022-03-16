import java.util.Random;

/**
* An abstract class extended by specific Pokemon classes
* @author Ryan Suos
* @author John Teano
*/
public abstract class Pokemon extends Entity
{
  /**
  * Battle table that determines the total amount of damage being applied to from attacking pokemon to the
  * Pokemon receiving the attack by their type
  */
  static final double [][] battleTable = {{1, .5, 2}, {2,1,.5}, {.5, 2, 1}};

  /**
  * Overloaded constructor for Pokemon
  * @param n is the name of the Pokemon 
  * @param maxHp is the max hp of the pokemon
  */
  public Pokemon(String n, int hp, int maxHp)
  {
    super(n, hp, maxHp);
  }

  /**
   * Displays the basic attacks that is used by all Pokemon
   * @return returns the basic attack options as a String
   */
  public String getBasicMenu()
  {
    String basicMenu = "1. Slam\n2. Tackle\n3. Punch";

    return basicMenu;
  }


  /**
   * Menu to let the player choose between basic attacks and special attacks 
   * @return the options of type of attacks as a String
   */
  public String getAttackTypeMenu()
  {
    return "1. Basic Attacks\n2. Special Attacks";
  }

  /**
   * The number of attack menu items
   * @return the number of different kinds of attack menus as an integer
   */
  public int getNumAttackTypeMenuItems()
  {
    return 2;
  }

  /**
  * Returns the attack menu for normal attacks for the user to choose from
  * @return the options for normal attacks as a String
  */
  public String getAttackMenu(int atkType)
  {
    return "1) Slam\n2) Tackle\n3) Punch";
  }

  /**
  * Returns the number of items in the attack menu
  * @return the number of items in the attack menu as an integer
  */
  public int getNumAttackMenuItems(int atkType)
  {
    // The number of items in attack menu
    return 3;
  }

  /**
  * Returns the displaying attack move on the opposing Pokemon
  * @return returnString returns the displaying attack move on the opposing Pokemon as a String
  */
  public String getAttackString(int atkType, int move)
  {
    // This is overridden in the base pokémon classes for the special elemental attacks.
    String returnString = "";

    switch (move)
    {
      case 1:
      // returns a string for the Slam attack
      returnString += "SLAMMED";
      break;
      // returns a string for the tackle attack
      case 2:
      returnString += "TACKLED";
      break;
      case 3:
      // returns a string for the Punch attack
      returnString += "PUNCHED";
      break;
    }

    // The final string returned dependant on the attack being performed
    return returnString;
  }

  /*
  * Returns the attack damage for normal attacks
  * Also overridden in the base pokémon classes for special elemental attacks.
  * @return damage returns the damage done by a normal attack as an integer
  */
  
  public int getAttackDamage(int atkType, int move)
  {
    // Initial value damage. 0 because no attack is being implemented at this point
    int damage = 0;

    Random r = new Random();

    switch (move)
    {
      case 1:
      // random damage for Slam
      damage = r.nextInt(6);

      break;
      case 2:
      // random damage for Tackke
      damage = r.nextInt(2) + 2;

      break;
      case 3:
      // random damage for Punch
	    damage = r.nextInt(4) + 1;

      break;
    }

    return damage;
  }
	
  /**
  * Returns the attack multiplier damage done by a normal attack onto a pokemon
  * @return 1 returns the damage multiplier of 1 for normal attacks 
  */
  public double getAttackMultiplier(Pokemon p, int atkType)
  {
    // 1 for normal attacks, In the elemental classes we will apply the multiplier depending on the opposing Pokemon
    return 1;
  }

  /* Returns basic attack bonus damage
  * @return 1 because it is a normal attack in this class
  */
  public int getAttackBonus(int atkType)
  {
    return 0;
  }

  /*
  * Implements the damage done by a Pokemon to an opposing pokemon, calculating all damages and returns the string of the attack and damage done
  * @return returnedString returns the move done onto an opposing Pokemon by the attacking Pokemon as well as the damage as a String
  */
  public String attack(Pokemon p, int atkType, int move)
  {

    // Total damage after all other aspects of damage is calculated
    int totalDamage = (int)(this.getAttackMultiplier(p, atkType) * (getAttackDamage(atkType, move) + this.getAttackBonus(atkType)));

    // Fix to check for negative damage due to attack debuffs. If damage is negative, default to 0
    if(totalDamage < 0) {
      totalDamage = 0;
    }

    // Deals totalDamage to opposing Pokemon
    p.takeDamage(totalDamage);

    // The final string to be retured which shows the opposing Pokemon's name, damage, and attacking Pokemon's name
    String returnedString = p.getName() + " is " + this.getAttackString(atkType, move) + " by " + this.getName() + " and takes " + totalDamage + " damage.";
    
    return returnedString;
  }

  /**
  * Retrieves the element type of a Pokemon so the battle table can be applied appropriately
  * @return the type specific type of pokemon as an integer
  */
  public int getType()
  {
	  int type = -1;
	  
	  if (this instanceof Fire) type = 0;
	  if (this instanceof Water) type = 1;
	  if (this instanceof Grass) type = 2;
     
    return type;
  }



}