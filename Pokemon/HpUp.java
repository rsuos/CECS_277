/**
 * Increases the hp/maxhp value of the pokemon
 * @author Ryan Suos
 * @author John Teano
 */
public class HpUp extends PokemonDecorator {
  /**
   * Overloaded constructor
   * @param p is the pokemon whose hp/maxhp will be increased by 1-2
   */
  public HpUp(Pokemon p)  {
    super(p, " [+HP]", RandomNumberGenerator.RandomNumberInRange(1,2));
  }
}