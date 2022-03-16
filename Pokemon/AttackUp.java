/**
 * Increases the attack value of the pokemon
 * @author Ryan Suos
 * @author John Teano
 */
public class AttackUp extends PokemonDecorator  {
  /**
   * Overloaded Constructor
   * @param p is the pokemon whose attack will be increased
   */
  public AttackUp(Pokemon p)  {
    super(p, " [+ATK]", 0);
  }

  /**
   * Returns the increased attack value by 1-2
   * @param type is the attack type
   * @return is the attack bonus of the pokemon increased by 1-2
   */
   @Override
  public int getAttackBonus(int type) {
    return RandomNumberGenerator.RandomNumberInRange(1,2) + super.getAttackBonus(type);
  }
}