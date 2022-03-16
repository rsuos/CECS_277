/**
 * Decreases the attack value of the pokemon
 * @author Ryan Suos
 * @author John Teano
 */
public class AttackDown extends PokemonDecorator  {
  /**
   * Overloaded Constructor
   * @param p is the pokemon whose attack will be decreased
   */
  public AttackDown(Pokemon p)  {
    super(p, " [-ATK]", 0);
  }

  /**
   * Returns the decreased attack value by 1
   * @param type is the attack type
   * @return is the attack bonus of the pokemon decreased by 1
   */
  @Override
  public int getAttackBonus(int type) {
    return super.getAttackBonus(type) - 1;
  }
}