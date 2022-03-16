import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

/**
 * Singleton Factory class that generates new Pokemon objects. Generates both buffed, debuffed and regular pokemon
 * @author Ryan Suos
 * @author John Teano
 */
public class PokemonGenerator {

  private HashMap<String, String> pokemon = new HashMap<>(); //<PokemonName, Type>

  private static PokemonGenerator instance = null;

  /**
   * Default Constructor for PokemonGenerator
   * Reads in PokemonList.txt and saves the corresponding values to the pokemon HashMap
   */
  private PokemonGenerator() {
    try{
    File pokemonListFile = new File("PokemonList.txt");
    Scanner fileScan = new Scanner(pokemonListFile);

    Pattern p = Pattern.compile("(\\w+),(\\w+)");

    Matcher match;

    while(fileScan.hasNextLine()) {
      String currentLine = fileScan.nextLine();

      match = p.matcher(currentLine);

      if(match.find())  {
        pokemon.put(match.group(1),match.group(2));
      }
    }
    fileScan.close();
    }catch(FileNotFoundException e) {
      System.out.println("File does not exist. Please try again.");
      e.printStackTrace();
    }
  }

  /**
	 * Returns the instance of PokemonGenerator if it exists
   * @return instance contains the single instance of PokemonGenerator
	 */
  public static PokemonGenerator getInstance()  {
    if(instance == null) {
      instance = new PokemonGenerator();
    }
    return instance;
  }

  /**
   * Generates a random pokemon and buffs the pokemon based on level
   * @param level determines the number of buffs the random pokemon will receive
   * @return is the randomly generated pokemon
   */
  public Pokemon generateRandomPokemon(int level) {
    // Choose random pokemon from the HashMap
    Pokemon randomPokemon = null;

    int randomPokemonChoice = RandomNumberGenerator.RandomNumberInRange(1, 23);
    
    String pokemonKey = "";

    switch(randomPokemonChoice) {
        case 1: pokemonKey = "Bulbasaur";
                break;
        case 2: pokemonKey = "Charmander";
                break;
        case 3: pokemonKey = "Squirtle";
                break;
        case 4: pokemonKey = "Vulpix";
                break;
        case 5: pokemonKey = "Oddish";
                break;
        case 6: pokemonKey = "Psyduck";
                break;
        case 7: pokemonKey = "Growlithe";
                break;
        case 8: pokemonKey = "Poliwag";
                break;
        case 9: pokemonKey = "Bellsprout";
                break;
        case 10: pokemonKey = "Tentacool";
                break;
        case 11: pokemonKey = "Ponyta";
                break;
        case 12: pokemonKey = "Slowpoke";
                break;
        case 13: pokemonKey = "Seel";
                break;
        case 14: pokemonKey = "Shellder";
                break;
        case 15: pokemonKey = "Krabby";
                break;
        case 16: pokemonKey = "Exeggcute";
                break;
        case 17: pokemonKey = "Tangela";
                break;
        case 18: pokemonKey = "Horsea";
                break;
        case 19: pokemonKey = "Goldeen";
                break;
        case 20: pokemonKey = "Staryu";
                break;
        case 21: pokemonKey = "Magikarp";
                break;
        case 22: pokemonKey = "Lapras";
                break;
        case 23: pokemonKey = "Moltres";
                break;
    }

    // Use type to create a new pokemon
    randomPokemon = getPokemon(pokemonKey);

    // add random buffs to new pokemon depending on level
    if(level > 1) {
      for(int i = 1; i < level; i++)  {
        randomPokemon = addRandomBuff(randomPokemon);
      }
    }

    // return randomly generated pokemon with new buffs
    return randomPokemon;
  }

  /**
   * Generates a new Pokemon based on the name provided
   * @param name is the name of the pokemon
   * @return is the specific pokemon created using the name
   */
  public Pokemon getPokemon(String name)  {
    Pokemon targetPokemon = null;

    int randomHp = RandomNumberGenerator.RandomNumberInRange(20, 25);

    // Return pokemon base class from HashMap based on name
    // the name is the key
    switch(pokemon.get(name)) {
        case "Fire":  targetPokemon = new Fire(name, randomHp, randomHp);
                      break;
        case "Grass": targetPokemon = new Grass(name, randomHp, randomHp);
                      break;
        case "Water": targetPokemon = new Water(name, randomHp, randomHp);
                      break;
    }

    return targetPokemon;
  }

  /**
   * Adds a random buff to the pokemon
   * @param p is the pokemon to be buffed
   * @return is the buffed pokemon
   */
  public Pokemon addRandomBuff(Pokemon p) {
    Pokemon buffedPokemon = null;

    //RNG to determine whether AttackUp or HpUp
    int randomBuffChoice = RandomNumberGenerator.RandomNumberInRange(1, 2);

    switch(randomBuffChoice)  {
        //AttackUp
        // Add 1-2 to attack
        // Concatenate +ATK to pokemon name
        case 1: buffedPokemon = new AttackUp(p);
                break;
        //HpUp
        // Add 1-2 to hp and maxHp
        // Concatenate +HP to pokemon name
        case 2: buffedPokemon = new HpUp(p);
                break;
    }
    return buffedPokemon;
  }

  /**
   * Adds a random debuff to the pokemon
   * @param p is the pokemon to be debuffed
   * @return is the debuffed pokemon
   */
  public Pokemon addRandomDebuff(Pokemon p) {
    Pokemon debuffedPokemon = p;

    //RNG to determine whether AttackDown or HpDown
    int randomDebuffChoice = RandomNumberGenerator.RandomNumberInRange(1, 2);
    
    switch(randomDebuffChoice)  {
        // AttackDown
        // Subtract 1 to attack
        // Concatenate -ATK to pokemon name
        case 1: debuffedPokemon = new AttackDown(p);
                System.out.println(debuffedPokemon.getName() + " has their ATTACK power lowered!");
                break;

        // HpDown
        // Subtract 1 to hp and maxHp
        // Concatenate HP to pokemon name
        // If statement to ensure pokemon hp doesn't go into negative
        case 2: if(debuffedPokemon.getHp() == 0)  {
                System.out.println("Oh no! " + debuffedPokemon.getName() + " 's HP can't go any lower!");
                break;
                }
                debuffedPokemon = new HpDown(p);
                System.out.println(debuffedPokemon.getName() + " has their HP lowered!");
                break;
    }
    return debuffedPokemon;
  }
}