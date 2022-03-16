 class Main {
  public static void main(String[] args) {

	String playerName;
		  
	int firstPokemonChoice;

  int randomDirection = 0;

  int level = 1;
		  
	PokemonGenerator createPokemon = PokemonGenerator.getInstance();

  Pokemon firstPokemon = null;

  Map playerMap = Map.getInstance();
		  
	System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
		  
	playerName = CheckInput.getString();
		  
	System.out.println("Great to meet you, " + playerName);
		  
	System.out.println("Choose your first pokemon:\n1. Charmander\n2. Bulbasaur\n3. Squirtle");
		  
	firstPokemonChoice = CheckInput.getIntRange(1, 3);

	switch(firstPokemonChoice)	{
		case 1: firstPokemon = createPokemon.getPokemon("Charmander");
		  		break;
		case 2: firstPokemon = createPokemon.getPokemon("Bulbasaur");
		  		break;
		case 3: firstPokemon = createPokemon.getPokemon("Squirtle");
		  		break;
		  }
      
  playerMap.loadMap(1);

	Trainer player = new Trainer(playerName, firstPokemon);
	
	Boolean playingGame = true;

  Boolean ranAway = false;

  int mapNumber = 1;

  char mapTrigger = '\0';
	
	while (playingGame)
	{
    	if (player.getHp() == 0)
    	{
        System.out.println("Game over.");
        break;
      }
      	
      	System.out.println(player.toString());
      	
      	System.out.println("Map:\n" + playerMap.mapToString(player.getLocation()));
      	
	if(!ranAway)  {
  switch (mainMenu())		{	// Returns an int value that determines methods to be called upon
		case 1: 
				mapTrigger = player.goNorth();
				if (mapTrigger == 'e')
				{
          			System.out.println("You cannot go that way.\n");
          			mapTrigger = player.goSouth();         
        		}
				break;
		case 2: 
				mapTrigger = player.goSouth();
				if (mapTrigger == 'e')
				{
          			System.out.println("You cannot go that way.\n");
          			mapTrigger = player.goNorth(); 
          		}
				break;
		case 3: 
				mapTrigger = player.goEast();
				if (mapTrigger == 'e')
				{
          			System.out.println("You cannot go that way.\n");
          			mapTrigger = player.goWest();
          		} 
				break;
		case 4: 
				mapTrigger = player.goWest();
				if (mapTrigger == 'e')
				{
          			System.out.println("You cannot go that way.\n");
          			mapTrigger = player.goEast(); 
          		}
				break;
		case 5: playingGame = false;
        System.out.println("Thank you for playing!");
				break;				
		}
  }
		
		if (playingGame == false) break;
		
		switch (mapTrigger)
		{ 
      		case 's':
            ranAway = false;
            break;

          case 'f':
            ranAway = false;

            boolean gymLeaderDefeated;

            String gymType = "";

            String gymLeaderName = "";

            String gymLeaderIntroSpeech = "";

            String gymLeaderLossSpeech = "";

            String gymLeaderWinSpeech = "";

            Pokemon gymLeaderPokemon = null;

            switch(mapNumber) {
              case 1: gymLeaderName = "Grassina"; // Pokemon: Exegccute
                      gymType += "Grass";
                      gymLeaderIntroSpeech = "Your hiney is grass!";
                      gymLeaderLossSpeech = "As they say, the grass is always greener on the other side. . .";
                      gymLeaderWinSpeech = "Consider yourself, mowed!";
                      break;

              case 2: gymLeaderName = "Aquafina"; // Pokemon: Lapras
                      gymType += "Water";
                      gymLeaderIntroSpeech = "Prepare to get soaked!";
                      gymLeaderLossSpeech = "I don't like sand. It's coarse and rough and irritating and it gets everywhere. . .";
                      gymLeaderWinSpeech = "You're all washed up!";
                      break;

              case 3: gymLeaderName = "Blaze"; // Pokemon: Growlithe
                      gymType += "Fire";
                      gymLeaderIntroSpeech = "You're toast!";
                      gymLeaderLossSpeech = "As they say, the light that burns twice as bright burns half as long. . .";
                      gymLeaderWinSpeech = "Burn, baby! Burn!";
                      break;
            }


            // Gym leader says some things
            System.out.println("I am " + gymLeaderName + ", leader of the " + gymType + " gym! " + gymLeaderIntroSpeech);

            // Gym Leader Pokemon encounter

            // Create single random pokemon
              gymLeaderPokemon = createPokemon.generateRandomPokemon(level + 2);

            /** Commented out to change from a specific gym leader pokemon to a single random pokemon
            switch(gymType) {
              case "Grass": 
                           gymLeaderPokemon = createPokemon.getPokemon("Exeggcute");
                           break;
              case "Water": gymLeaderPokemon = createPokemon.getPokemon("Lapras");
                           break;
              case "Fire": gymLeaderPokemon = createPokemon.getPokemon("Growlithe");
                           break; 
            }
            

            // Add random buffs to gym leader pokemon 2 levels higher than player level
            for(int i = 0; i < level + 2; i++)  {
              gymLeaderPokemon = createPokemon.addRandomBuff(gymLeaderPokemon);
            }
            */

            System.out.println(gymLeaderPokemon.getName() + ", I choose you!");

            boolean gymBattle = true;

            // Boolean to determine if character should be removed.
            // if true, character is removed from player position.
            // if false, w character remains in player position.
            gymLeaderDefeated = false; 
            
            while (gymBattle)
            {
              int gymBattleChoice = 0;

              if (gymLeaderPokemon.getHp() == 0 || player.getHp() == 0) {
                gymBattle = false;
                gymLeaderDefeated = true;
                System.out.println(gymLeaderLossSpeech);
                System.out.println("You have defeated the " + gymType + " gym! All of your pokemon will receive a random buff!");
                player.buffAllPokemon();
              }

            if(gymLeaderDefeated) {
              switch (mapNumber)
              {
                // implement buffs here
                    case 1:
                      playerMap.loadMap(2);
                      mapNumber = 2;
                      break;
                    case 2:
                      playerMap.loadMap(3);
                      mapNumber = 3;
                      break;
                    case 3:
                      playerMap.loadMap(1);
                      mapNumber = 1;
                      break;
              }

                level += 1;
                System.out.println("You've found the next map! Loading area " + mapNumber + ". . .");
                break;
              }
              
            if (gymBattle == false) break;

              // System.out.println(wildPokemon.getName() + " HP: " + wildPokemon.getHp() + "/" + wildPokemon.getMaxHp());

              System.out.println("\n" + gymLeaderPokemon.toString());

              System.out.println("What do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away ");
              
              gymBattleChoice = CheckInput.getIntRange(1,4);

              switch (gymBattleChoice)
              {
                case 1: // Fight
                  boolean gymAllDead = false;
                  
                  
                  for (int i = 1; i <= player.getNumPokemon(); i++)
                  { 
                    Pokemon currentPokemon = player.getPokemon(i);
                    if (currentPokemon.getHp() == 0) gymAllDead = true;
                    if (currentPokemon.getHp() > 0)
                    {
                      gymAllDead = false;
                      break;
                    }
                  }
                  if (gymAllDead == true)
                  { 
                    int randomDamage = RandomNumberGenerator.RandomNumberInRange(1, 5);
                    System.out.println("All your Pokemon are unable to battle\nYou took " + randomDamage + " damage from " + gymLeaderPokemon.getName() + "!");
                    player.takeDamage(randomDamage);
                    gymBattle = false;
                    System.out.println(gymLeaderWinSpeech + "\n");
                  }
                  else 
                  {
                    // I think we need to use clone here because I'm not sure if this is a shallow copy
                    gymLeaderPokemon = trainerAttack(player, gymLeaderPokemon);
                  }

                break;
                case 2: // Use Potion
                System.out.println("Choose one of your Pokemon to heal.");
                System.out.println(player.getPokemonList());
                
                int gymHealChoice = CheckInput.getIntRange(1, player.getNumPokemon());
                
                if (player.getPokemon(gymHealChoice).getHp() == player.getPokemon(gymHealChoice).getMaxHp())
                {
                  System.out.println(player.getPokemon(gymHealChoice).getName() + " is already at full health!");
                }
                else if (player.getPokemon(gymHealChoice).getHp() < player.getPokemon(gymHealChoice).getMaxHp())
                { 
                  if(player.hasPotion())  {
                  player.usePotion(gymHealChoice);
                  System.out.println(player.getPokemon(gymHealChoice).getName() + " has been healed by a potion and received a random buff.");
                  }

                  else  {
                    System.out.println("Oh no! You don't have any potions!");
                  }
                }
                break;
                case 3: // Throw Poke Ball

                  System.out.println("Did you just throw a pokeball at my " + gymLeaderPokemon.getName() + "?! What's the matter with you?!");

                  break;
                case 4: // Run away
                  System.out.println("(You try to sneak away, but the gym doors are locked)");
                  System.out.println("What are you a wuss?! Come back here!");
                break;
              }
            }
      			break;

      		case 'n': {
            ranAway = false;
            System.out.println("There's nothing here...");
            break;
          }
      		case 'i':
            ranAway = false;
      			int item = RandomNumberGenerator.RandomNumberInRange(1, 2);
      			if (item == 1) 
      			{
             	System.out.println("You have received a Pokeball!");
              	player.receivePokeball();
            	}
            	if (item == 2)
            	{
                System.out.println("You have received a Potion!");
                player.receivePotion();
              	}
      			playerMap.removeCharAtLoc(player.getLocation());
      			break;
      		case 'w':
      			// Wild pokemon encounter
            ranAway = false;

            Pokemon wildPokemon = createPokemon.generateRandomPokemon(level);

            System.out.println("A wild " + wildPokemon.getName() + " has appeared!\n");

            boolean battle = true;

            boolean pokemonCaught = false;

            // Boolean to determine if character should be removed.
            // if true, character is removed from player position.
            // if false, w character remains in player position.
            boolean playerWins = false;

            int pokemonChoice = 0;  // Choice to hold index for pokemon to be removed
            
            while (battle)
            {
              int battleChoice = 0;

              if(wildPokemon.getHp() == 0)  {
                System.out.println(wildPokemon.getName() + " has been defeated!\n");
              }

              if (wildPokemon.getHp() == 0 || player.getHp() == 0 || pokemonCaught) {
                battle = false;
                playerWins = true;
              }

              if(playerWins)  {
                playerMap.removeCharAtLoc(player.getLocation());
              }

              if (battle == false) break;

              // System.out.println(wildPokemon.getName() + " HP: " + wildPokemon.getHp() + "/" + wildPokemon.getMaxHp());

              System.out.println("\n" + wildPokemon.toString());

              System.out.println("What do you want to do?\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away ");
              
              battleChoice = CheckInput.getIntRange(1,4);

              switch (battleChoice)
              {
                case 1: // Fight
                  boolean allDead = false;
                  
                  for (int i = 1; i <= player.getNumPokemon(); i++)
                  { 
                    Pokemon currentPokemon = player.getPokemon(i);
                    if (currentPokemon.getHp() == 0) allDead = true;
                    if (currentPokemon.getHp() > 0)
                    {
                      allDead = false;
                      break;
                    }
                  }
                  if (allDead == true)
                  { 
                    int randomDamage = RandomNumberGenerator.RandomNumberInRange(1, 5);
                    System.out.println("All your Pokemon are unable to battle\nYou took " + randomDamage + " damage from " + wildPokemon.getName() + "!");
                    player.takeDamage(randomDamage);
                    battle = false;
                    System.out.println("You ran away in fear.\n");
                  }
                  else 
                  {
                    // I think we need to use clone here because I'm not sure if this is a shallow copy
                    wildPokemon = trainerAttack(player, wildPokemon);
                  }

                break;
                case 2: // Use Potion
                System.out.println("Choose one of your Pokemon to heal.");
                System.out.println(player.getPokemonList());
                
                int healChoice = CheckInput.getIntRange(1, player.getNumPokemon());
                
                if (player.getPokemon(healChoice).getHp() == player.getPokemon(healChoice).getMaxHp())
                {
                  System.out.println(player.getPokemon(healChoice).getName() + " is already at full health!");
                }
                else if (player.getPokemon(healChoice).getHp() < player.getPokemon(healChoice).getMaxHp())
                { 
                  if(player.hasPotion())  {
                  player.usePotion(healChoice);
                  System.out.println(player.getPokemon(healChoice).getName() + " has been healed by a potion and received a random buff.");
                  }

                  else  {
                    System.out.println("Oh no! You don't have any potions!");
                  }
                }
                break;
                case 3: // Throw Poke Ball
                  pokemonCaught = player.catchPokemon(wildPokemon);

                  if(pokemonCaught == true) {
                    playerMap.removeCharAtLoc(player.getLocation());
                    battle = false;
                  }

                  // ArrayList size limit 6 implementation
                  while(player.getNumPokemon() == 7) {
                    System.out.println("\nYou can only have 6 pokemon max. Please choose a pokemon to set free:\n");
                    System.out.println(player.getPokemonList());
                    pokemonChoice = CheckInput.getIntRange(1,6);
                    System.out.println(player.getPokemon(pokemonChoice).getName() + " has been set free!\n");
                    player.removePokemon(pokemonChoice);
                  }

                  break;
                case 4: // Run away
                ranAway = true;

                randomDirection = RandomNumberGenerator.RandomNumberInRange(1, 4);
                battle = false;

                switch(randomDirection) { // Moves the player in a random direction after running away
                  case 1: 
                  mapTrigger = player.goNorth();
                  if (mapTrigger == 'e')
                  {
                          player.goSouth();
                          mapTrigger = player.goSouth(); // Move back twice to map to prevent repating encounter with wild pokemon
                      }
                  break;
                case 2: 
                  mapTrigger = player.goSouth();
                  if (mapTrigger == 'e')
                  {
                          player.goNorth();
                          mapTrigger = player.goNorth(); 
                        }
                  break;
                case 3: 
                  mapTrigger = player.goEast();
                  if (mapTrigger == 'e')
                  {
                          player.goWest();
                          mapTrigger = player.goWest();
                        } 
                  break;
                case 4: 
                  mapTrigger = player.goWest();
                  if (mapTrigger == 'e')
                  { 
                          player.goEast();
                          mapTrigger = player.goEast(); 
                  }
                  break;
                }
                break;
              }
            }
      			break;
      		case 'p':
            ranAway = false;

            int person = RandomNumberGenerator.RandomNumberInRange(1, 5);

            playerMap.removeCharAtLoc(player.getLocation());

            switch (person)
            {
              case 1:
              System.out.println("Brock: I can see that you love Pokemon. Here's a potion!");
              player.receivePotion();
              break;
              case 2:
              System.out.println("Misty: How dare you look at me!\n You took 3 damage!");
              player.takeDamage(3);
              break;
              case 3:
              System.out.println("Gary: Looks like you're as broke as ever heres $10 loser.");
              player.receiveMoney(10);
              break;
              case 4:
              System.out.println("Team Rocket: Take this!\nYou took 5 damage!");
              player.takeDamage(5);
              break;
              case 5:
              System.out.println("Trainer: You might need this on your adventure.\nYou have received a PokeBall!");
              player.receivePokeball();
              break;
            }

      			break;

      		case 'c':
            ranAway = false;
            
            System.out.println("You have entered a PokeTown!\n Would you like to go to a:\n1) PokeCenter\n2) Store");
            int cityChoice = CheckInput.getIntRange(1, 2);
            switch (cityChoice)
            {
              case 1:
              System.out.println("Thank you for visiting the PokeCenter! All your Pokemon have been healed!");
              player.healAllPokemon();
              break;
              case 2:
              store(player);
              break;
            }
      			break;
        }
      }
  	}
  
  
  // John
  /**
   * Chooses a random pokemon from all 23 pokemon available
   * @Pokemon is the random pokemon initialized
   

  public static Pokemon chooseRandomPokemon() {
	
  PokemonGenerator pokemonGenerator = PokemonGenerator.getInstance();
	Pokemon randomPokemon = pokemonGenerator.generateRandomPokemon();
    
  return randomPokemon;
  }

  */
  
  /**
   * Displays the main menu choice to the user
   * @return the users menu choice entered
   */
  public static int mainMenu()	{
	int mainMenuChoice;
	
	System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n","Main Menu:", "1. Go North", "2. Go South", "3. Go East", "4. Go West", "5. Quit");
	
	mainMenuChoice = CheckInput.getIntRange(1,5);
	
	return mainMenuChoice;
  }

  /**
   * Combat between the trainer/trainer's pokemon and the wild pokemon
   * @param t is the trainer
   * @param wild is the wild pokemon encountered
   * @return is the debuffed pokemon
   */
   public static Pokemon trainerAttack(Trainer player, Pokemon wildPokemon)	{
     int chosenPokemon = 0;
	   int attackMenuChoice = 0;
     int moveChoice = 0;
     // Wild variables will eventually be randomized to let opposing pokemon attack back
     int wildAttackMenuChoice = 0;
     int wildMoveChoice = 0;
     Pokemon debuffedWildPokemon = wildPokemon;
     PokemonGenerator createPokemon = PokemonGenerator.getInstance();

    System.out.println("Choose a pokemon:");
                  
    System.out.println(player.getPokemonList());

    chosenPokemon = CheckInput.getIntRange(1, player.getNumPokemon());
                  
    Pokemon currentPokemon = player.getPokemon(chosenPokemon); // This is a shallow copy. Any changes made to currentPokemon will be applied to the chosenPokemon from the arrayList
    
    while(currentPokemon.getHp() == 0)  {
      System.out.println(currentPokemon.getName() + " is passed out! Please choose another pokemon.");

      chosenPokemon = CheckInput.getIntRange(1, player.getNumPokemon());

      currentPokemon = player.getPokemon(chosenPokemon);
    }

    System.out.println(currentPokemon.getName() + ", I choose you!\n");

    System.out.println(currentPokemon.getAttackTypeMenu() + "\n");

    attackMenuChoice = CheckInput.getIntRange(1, 2);
    
    switch(attackMenuChoice)  {
    // Basic Attack Player Turn
    case 1: System.out.println(currentPokemon.getBasicMenu() + "\n");

            moveChoice = CheckInput.getIntRange(1,3);

            System.out.println(currentPokemon.attack(wildPokemon, 1, moveChoice));

            // 25% chance of wild pokemon getting debuffed
            if(RandomNumberGenerator.probability(25)) {
              debuffedWildPokemon = createPokemon.addRandomDebuff(debuffedWildPokemon);
            }

            // Wild Pokemon Attack Turn
            wildAttackMenuChoice = RandomNumberGenerator.RandomNumberInRange(1,2);

            wildMoveChoice = RandomNumberGenerator.RandomNumberInRange(1,3);

            if(wildAttackMenuChoice == 1)  {
              System.out.println(wildPokemon.attack(currentPokemon, 1, wildMoveChoice));
            }

            else if(wildAttackMenuChoice == 2)  {
                System.out.println(wildPokemon.attack(currentPokemon, 2, wildMoveChoice));
            }

            // 10% chance of player pokemon getting debuffed
            if(RandomNumberGenerator.probability(10)) {
              player.debuffPokemon(chosenPokemon);
            }

          break;
      // Elemental Attack Player Turn
      case 2: System.out.println(currentPokemon.getAttackMenu(2) + "\n");

              moveChoice = CheckInput.getIntRange(1,3);

              System.out.println(currentPokemon.attack(wildPokemon, 2, moveChoice));

              // 25% chance of wild pokemon getting debuffed
              if(RandomNumberGenerator.probability(25)) {
                debuffedWildPokemon = createPokemon.addRandomDebuff(debuffedWildPokemon);
              }

              // Wild Pokemon Attack Turn
              wildAttackMenuChoice = RandomNumberGenerator.RandomNumberInRange(1,2);

              wildMoveChoice = RandomNumberGenerator.RandomNumberInRange(1,3);

              if(wildAttackMenuChoice == 1)  {
                System.out.println(wildPokemon.attack(currentPokemon, 1, wildMoveChoice));
              }

              else if(wildAttackMenuChoice == 2)  {
                System.out.println(wildPokemon.attack(currentPokemon, 2, wildMoveChoice));
              }

              // 10% chance of player pokemon getting debuffed
              if(RandomNumberGenerator.probability(10)) {
              player.debuffPokemon(chosenPokemon);
            }
      }
    if (currentPokemon.getHp() == 0) System.out.println(currentPokemon.getName() + " has fainted!");

    return debuffedWildPokemon;
   }
  
  
  /**
   * Displays the store options for the trainer
   * @param t is the trainer
   */
   public static void store(Trainer t)  {
     int storeChoice = 0;

     while(storeChoice != 3)  {
       System.out.printf("%s\n%s\n%s\n%s\n","Hello! What can I help you with?", "1. Buy Potions - $5", "2. Buy Poke Balls - $3", "3. Exit");

       storeChoice = CheckInput.getInt();

       switch(storeChoice)  {
         case 1: if(t.spendMoney(5)) {
                  t.receivePotion();
                  System.out.println("Here's your potion.");
                 }
         		else	{
         			System.out.println("Hey! You don't have enough to pay for this! Get outta here! *Sigh* Deep breaths, think good thoughts. . .");
         		}
                 break;

         case 2: if(t.spendMoney(3)) {
                  t.receivePokeball();
                  System.out.println("Here's your pokeball.");
                 }
         		 else	{
         			 System.out.println("What are you pokebroke?! Get outta here!");
         		 }
                 break;
         case 3: { 
           System.out.println("Thank you! come again soon!\n");
           break;
         }
       }
     }
   }
}