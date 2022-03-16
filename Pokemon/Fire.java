import java.util.Random;
/**
 * Fire type Pokemon implementation
 * @author Ryan Suos
 * @author John Teano
 */
public class Fire extends Pokemon  {
  /**
   * Overloaded Constructor
   * Creates a Fire pokemon
   * @param n is the name
   * @param h is the hp
   * @param m is the max hp
   */
  public Fire(String n, int h, int m) {
    super(n, h, m);
  }

  /**
   * Returns the appropriate attack menu based on attack type
   * @param atkType is the type of attack chosen between basic and special
   * @return is the string of the special attack
   */
  @Override
  public String getAttackMenu(int atkType)
  {
    if (atkType == 1) return super.getAttackMenu(1);
    else return "1. Ember \n2. Fire Blast\n3. Fire Punch";
  }

  /**
   * Returns the number of attack menu items
   * @param atkType is the type of attack chosen between basic and special
   * @return is the number of special attack menu items
   */

  @Override
  public int getNumAttackMenuItems(int atkType)
  {
    return 3;
  }

  /**
   * Returns the attack string based on the attack type and move choice
   * @param atkType is the type of attack chosen between basic and special
   * @param move is the choice of attack
   * @return is the pokemon attack string
   */
  @Override
  public String getAttackString(int atkType, int move)
  {
    if (atkType == 1) return super.getAttackString(atkType, move);
    else
    {
          String returnString = "";

    switch(move)
    {
      case 1:
      returnString += "EMBERED";
      break;
      case 2:
      returnString += "FIRE BLASTED";
      break;
      case 3:
      returnString += "FIRE PUNCHED";
      break;
    }

    return returnString;
    }

  }

  /**
   * Returns the attack damage dealt
   * @param atkType is the type of attack chosen between basic and special
   * @param move is the choice of attack
   * @return is the attack damage dealt depending on the attack type and move choice
   */
  @Override
  public int getAttackDamage(int atkType, int move)
  { 
    int damage = 0;

    if (atkType == 1) {
      damage = super.getAttackDamage(1, move);
    }

    else
    {
      Random r = new Random();

      switch (move)
      {
        case 1: damage = r.nextInt(4);  // random damage for Ember
                break;
        case 2:  damage = r.nextInt(4) + 1;  // random damage for Fire Blast
                System.out.println(damage);
                 break;
        case 3:  damage = r.nextInt(3) + 1;  // random damage for Fire Punch
                 break;
      }
    }
    return damage;
  }

  /**
   * Returns the attack multiplier based on Pokemon attack type
   * @param p is the Pokemon to be attacked
   * @param type is the type of attack chosen between basic and special
   * @return is the attack multiplier based off the battle table
   */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType)
  {
    if (atkType == 1) return super.getAttackMultiplier(p, 1);
    else 
    {
      return battleTable[this.getType()][p.getType()];
    }
  }

}