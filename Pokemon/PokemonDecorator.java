/**
 * Decorates pokemon with buffs/debuffs that alters their values (ex. Hp, Attack)
 * @author Ryan Suos
 * @author John Teano
 */
public abstract class PokemonDecorator extends Pokemon  {
  private Pokemon pokemon;

  /**
   * Overloaded Constructor
   * Takes in the pokemon to be decorated with extra buffs and stats
   * @param p is the pokemon to be decorated
   * @param extraName is the extra buffs/debuffs to be concatenated to the pokemon name
   * @param extraHp is the amount of extra hp to be added to the hp and maxHp
   */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
    super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
    pokemon = p;
    
  }

  /**
   * Returns the appropriate attack menu based on attack type
   * @param atkType is the type of attack chosen between basic and special
   * @return is the string of the chosen attack type
   */
  @Override
  public String getAttackMenu(int atkType)  {
    if (atkType == 1) return super.getAttackMenu(1);

    else return pokemon.getAttackMenu(atkType);
  }
  
  /**
   * Returns the number of attack menu items
   * @param atkType is the type of attack chosen between basic and special
   * @return is the number of attack menu items
   */
  @Override
  public int getNumAttackMenuItems(int atkType) {
    if (atkType == 1) return super.getNumAttackMenuItems(1);
    else return pokemon.getNumAttackMenuItems(atkType);
  }

  /**
   * Returns the attack string based on the attack type and move choice
   * @param atkType is the type of attack chosen between basic and special
   * @param move is the choice of attack
   * @return is the pokemon attack string
   */
  @Override
  public String getAttackString(int atkType, int move)  {
    if (atkType == 1) return super.getAttackString(1, move);
    else return pokemon.getAttackString(atkType, move);
  }

  /**
   * Returns the attack damage dealt
   * @param atkType is the type of attack chosen between basic and special
   * @param move is the choice of attack
   * @return is the attack damage dealt depending on the attack type and move choice
   */
  @Override
  public int getAttackDamage(int atkType, int move) {
    if (atkType == 1) super.getAttackDamage(1, move);
    return pokemon.getAttackDamage(atkType, move);
  }

  /**
   * Returns the attack multiplier based on Pokemon atkType
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

  /**
   * Returns the attack bonus from the buffs received
   * @param type is the type of attack chosen between basic and special
   * @return is the attack bonus
   */
   @Override
  public int getAttackBonus(int type) {
    return pokemon.getAttackBonus(type);
  }

  /**
  * Retrieves the element type of a Pokemon so the battle table can be applied appropriately
  * @return the type specific type of pokemon as an integer
  */
  @Override
  public int getType()  {
    return pokemon.getType();
  }
}