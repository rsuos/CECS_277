/**
 *  An abstract class to be used by all other classes
 * @author Ryan Suos
 * @author John Teano
 */
public abstract class Entity 
{
  private String name;
  private int hp;
  private int maxHp;

  /**
  * Default constructor for Entity
  */
  Entity()
  {
    name = "void";
    hp = 0;
    maxHp = 0;
  }
  
  /**
  * Constructor for Entity 
  * @param String name sets the name of the object
  */
  Entity(String name)
  {
    this.name = name;
    hp = 24;
    maxHp = 24;
  }

  /**
  * Constructor for Entity 
  * @param String name sets the name of the object
  * @param int maxHP sets the max HP of the entity 
  */
  Entity(String name, int maxHP)
  {
    this.name = name;
    this.maxHp = maxHP;
    this.hp = maxHP;
  }

    Entity(String name, int hp, int maxHP)
  {
    this.name = name;
    this.maxHp = maxHP;
    this.hp = hp;
  }
 
  /**
  * A way to get the current HP of an entity 
  * @return hp returns the current HP of an entity 
  */
  int getHp()
  {
    return hp;
  }

  /**
  * A way to get the max HP of an entity 
  * @return hp returns the max HP of an entity 
  */
  int getMaxHp()
  {
    return maxHp;
  }

  /**
  * A way to implement an entity taking damage 
  * @param int d is the damage being done to the entity 
  */
  void takeDamage(int d)
  { 

    hp = hp - d;

    if (hp < 0) hp = 0;
  }

  /**
  * A way to increase the current HP of a Pokemon to its max HP
  */
  void heal()
  {
    hp = maxHp;
  }

  /**
  * A way to get the name of an entity 
  * @return returns the name of an entity as a String
  */
  String getName()
  {
    return name;
  }

  /**
  * Displays all current information of an entity 
  * @return returns the current information of an entity as a String
  */
  public String toString()
  {
    String str = name + " HP: " + getHp() + "/" + getMaxHp() + "\n";
    
    return str;
  }
}