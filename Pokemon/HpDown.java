/**
 * Decreases the hp/maxhp value of the pokemon
 * @author Ryan Suos
 * @author John Teano
 */
public class HpDown extends PokemonDecorator {
  /**
   * Overloaded constructor
   * @param p is the pokemon whose hp/maxhp will be decreased by 1
   */
  public HpDown(Pokemon p)  {
    super(p, " [-HP]", -1);
  }
}