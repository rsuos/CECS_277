import java.util.Random;
import java.lang.Math;

/**
 * Random number generator (RNG) that generates integer and double values
 * @author John Teano
 */
public class RandomNumberGenerator {
	
	/**
	 * Generates a random integer within a given range
	 * @param lowerBound is the lower limit of the range (inclusive)
	 * 		  ex. If range is 1-10, lowerBound must be set to 1
	 * @param upperBound is the upper limit of the range (inclusive)
	 * 		  ex. If range is 1-10, upperBound must be set to 10
	 * @return the random integer within the given range
	 */
	public static int RandomNumberInRange(int lowerBound, int upperBound)	{
		Random random = new Random();
    
    // Add 1 to the upperBound to make the range include the upper bound
    upperBound += 1; 
		
		int randomIntInRange = 0;
		
		randomIntInRange = random.nextInt(upperBound - lowerBound) + lowerBound;
		
		return randomIntInRange;
	}

  /**
   * Generates a random double between 0 and 1
   * @return Math.random generates a random value between 0 and 1
   */
   public static double RandomDouble()  {
     return Math.random();
   }

  /**
   * Randomly determines whether an attempt is successful or not based on chance
   * @param percentage is the chance of how successful an attempt would be (ex. set percentage to 10 to calculate for a 10% chance)
   * @return if true, means the attempt was succesful, otherwise false
   */
   public static boolean probability(double percentage)  {
     percentage = percentage * .01;

     boolean successfulAttempt = false;

     if(RandomNumberGenerator.RandomDouble() < percentage)  {
        successfulAttempt = true;
     }

     return successfulAttempt;
   }
}