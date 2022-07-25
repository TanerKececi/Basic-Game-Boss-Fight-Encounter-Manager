package com.instruction;

import java.util.Scanner;
import java.util.Random;

/***
 *  This Class holds Tank, DamageDealer, Healer, EnemyEntity classes
 *  and operates fight dynamics with various methods
 */

public class EncounterManager {

    private Tank tank;
    private DamageDealer damageDealer;
    private Healer healer;
    private EnemyEntity enemy;
    private Scanner scanner = new Scanner(System.in);
    private static EncounterManager encounterManager_instance = null;

    private EncounterManager(){
        Tank tank = new Tank("Tank", 001, 100, 10, 6);
        DamageDealer damageDealer = new DamageDealer("DamageDealer", 002, 100, 10, 7);
        Healer healer = new Healer("Healer", 003, 100, 10, 8);
        EnemyEntity enemy = new EnemyEntity(666, 100, 10);
    }

    public static EncounterManager getInstanceEncounterManager(){
        if (encounterManager_instance == null){
            encounterManager_instance = new EncounterManager();
        }


        return encounterManager_instance;

    }

    /***
     * Menu method prints menu options and gets the user's choice
     */
    public void Menu(){

        System.out.println("1) Register entities\n2) Start encounter\n3) Exit\n");
        int intMenuOption = scanner.nextInt();
        char charMenuOption;

        if (intMenuOption == 2){
            System.out.println("\nThe encounter has started!\n");
        }

        /***
         * Printing 3 options to the user
         * If the user enters "1" then user will register
         * A champion or Enemy for the upcoming encounter
         * If the user enters "2" then the encounter starts
         * If user enters 3 then the program terminates
         */

        while (intMenuOption == 1){

            System.out.println("Select an entity to register:\n" +
                    "a) Tank\n" +
                    "b) Damage Dealer\n" +
                    "c) Healer\n" +
                    "d) Enemy\n");

            charMenuOption = scanner.next().charAt(0);                                  // Holding the menu option as char

            if(charMenuOption == 'a'){
                registerTank();                                                         // Calling registerTank method
                Menu();                                                                 // Returning to the beginning
            }
            else if (charMenuOption == 'b'){
                registerDamageDealer();                                                 // Calling registerDamageDealer method
                Menu();                                                                 // Returning to the beginning
            }
            else if (charMenuOption == 'c'){
                registerHealer();                                                       // Calling registerHealer method
                Menu();                                                                 // Returning to the beginning
            }
            else if (charMenuOption == 'd'){
                spawnEnemy();                                                           // Calling spawnEnemy method
                Menu();                                                                 // Returning to the beginning
            }
            else {                                                                          // If the input is not valid
                System.out.println("Unknown entity has been chosen!! Please try again");    // Giving error message to user
            }
        }
        while (intMenuOption == 2){

            startEncounter();

        }
        while (intMenuOption == 3){                                                      // Terminating the program
            System.exit(0);
        }

    }


    /***
     *  This method is used for enabling user to register a Tank object
     */
    public void registerTank(){

        System.out.print("Please enter entityID of Tank: ");
        int tempEntityID = scanner.nextInt();                                       // Getting entity id

        System.out.print("Please enter healthPoints of Tank: ");
        int tempHP = scanner.nextInt();                                             // Getting health points

        System.out.print("Please enter baseDamage of Tank: ");
        int tempBaseDmg = scanner.nextInt();                                        // Getting base damage

        System.out.print("Please enter defence of Tank: ");
        int tempDefence = scanner.nextInt();                                        // Getting defence

        tank = new Tank("Tank",tempEntityID,tempHP,tempBaseDmg,tempDefence);   // Calling constructor

        System.out.println("TANK PLAYER HAS BEEN REGISTERED SUCCESSFULLY!");        // Printing success message

    }

    /***
     * This method is used for enabling user to register a Damage Dealer object
     */
    public void registerDamageDealer(){

        System.out.print("Please enter entityID of Damage Dealer: ");
        int tempEntityID = scanner.nextInt();                                       // Getting entity ID

        System.out.print("Please enter healthPoints of Damage Dealer: ");
        int tempHP = scanner.nextInt();                                             // Getting health points

        System.out.print("Please enter baseDamage of Damage Dealer: ");
        int tempBaseDmg = scanner.nextInt();                                        // Getting base damage

        System.out.print("Please enter intelligence of Damage Dealer: ");
        int tempIntelligence = scanner.nextInt();                                   // Getting intelligence

        // Calling the constructor
        damageDealer = new DamageDealer("Damage Dealer",tempEntityID,tempHP,tempBaseDmg,tempIntelligence);

        System.out.println("DAMAGE DEALER PLAYER HAS BEEN REGISTERED SUCCESSFULLY!");   // Printing success message
    }

    /***
     * This method is used for enabling user to register a Healer object
     */
    public void registerHealer(){

        System.out.print("Please enter entityID of Healer: ");
        int tempEntityID = scanner.nextInt();                               // Getting entity id

        System.out.print("Please enter healthPoints of Healer: ");
        int tempHP = scanner.nextInt();                                     // Getting health points

        System.out.print("Please enter baseDamage of Healer: ");
        int tempBaseDmg = scanner.nextInt();                                // Getting base damage

        System.out.print("Please enter mind of Healer: ");
        int tempMind = scanner.nextInt();                                   // Getting mind value

        healer = new Healer("Healer",tempEntityID,tempHP,tempBaseDmg,tempMind);     // Calling constructor

        System.out.println("HEALER PLAYER HAS BEEN REGISTERED SUCCESSFULLY!");          // Printing success message

    }

    /***
     * This method is used for enabling user to register a Enemy Entity object
     */
    public void spawnEnemy(){

        System.out.print("Please enter entityID of Enemy: ");           // Getting entity id
        int tempEntityID = scanner.nextInt();

        System.out.print("Please enter healthPoints of Enemy: ");       // Getting health points
        int tempHP = scanner.nextInt();

        System.out.print("Please enter baseDamage of Enemy: ");         // Getting base damage
        int tempBaseDmg = scanner.nextInt();


        enemy = new EnemyEntity(tempEntityID,tempHP,tempBaseDmg);       // Calling constructor

        System.out.println("ENEMY ENTITY HAS BEEN REGISTERED SUCCESSFULLY!");   // Printing success message
    }


    /***
     * This method prints current health points of both parties
     * and shows options of the encounter
     */
    public synchronized void printStats(){
        System.out.println("======================\nEntities’ HP\nTank: " + tank.getHealthPoints() +
                "\nDamage Dealer: " + damageDealer.getHealthPoints() + "\nHealer: " + healer.getHealthPoints() +
                "\nEnemy: " + enemy.getHealthPoints() + "\n======================\n\n" +
                "a) Player attack\n" +
                "b) Player heal\n" +
                "c) Enemy attack\n" +
                "d) Enemy group-wide attack\n" +
                "e) Stop the encounter");
    }

    public void startEncounter(){

        tank.setEncounterManager(this);
        damageDealer.setEncounterManager(this);
        healer.setEncounterManager(this);
        enemy.setEncounterManager(this);

        tank.start();
        damageDealer.start();
        healer.start();
        enemy.start();
    }

    /***
     * This method is being used to check if Enemy Entity is alive or not
     * @return  If the health point of the enemy is less than or equal to 0 than this method will return false
     * otherwise it will return true
     */
    public synchronized Boolean enemyIsAlive(){

        if (enemy.getHealthPoints() > 0)
            return true;

        else
            return false;
    }

    /***
     * This method is being used to check if all players are alive or not
     * @return  If all players' health points are less than or equal to 0 than this method will return false
     * otherwise it will return true
     */
    public synchronized Boolean playersAreAlive(){

        if (tank.getHealthPoints() <= 0)

            if (damageDealer.getHealthPoints() <= 0)

                if (healer.getHealthPoints() <= 0)
                    return false;
        return true;
    }

    /***
     * This method is being called when the user enters 'd' in the encounter menu.
     * Enemy entity deals damage to all players simultaneously.
     */
    public synchronized void groupWideAttack(){
        if (tank.getHealthPoints() > 0){
            tank.takeDamage(enemy.dealDamage());
        }

        if (damageDealer.getHealthPoints() > 0){
            damageDealer.takeDamage(enemy.dealDamage());
        }

        if (healer.getHealthPoints() > 0){
            healer.takeDamage(enemy.dealDamage());
        }
    }

    /***
     * User is asked to enter which champion will do the attack.
     * If user chooses a dead player than will receive an error message that is asking to try again
     * with another player. After a valid input, the enemy takes the damage depending on the player role
     */
    public synchronized void playerAttack(char player){



        if (player == 't'){
            if(tank.getHealthPoints() <= 0){
                System.out.println("Tank Champion is not alive please try again with another Champion!");
            }
            else {
                System.out.println("Tank attacked the enemy (" + tank.dealDamage() + " damage attack)");
                enemy.takeDamage(tank.dealDamage());
            }
        }
        else if (player == 'd'){
            if(damageDealer.getHealthPoints() <= 0){
                System.out.println("Damage Dealer Champion is not alive please try again with another Champion!");
            }
            else {
                System.out.println("Damage dealer attacked the enemy (" + damageDealer.dealDamage() + " damage attack)");
                enemy.takeDamage(damageDealer.dealDamage());
            }
            }
        else if (player == 'h'){
            if(healer.getHealthPoints() <= 0){
                System.out.println("Healer Champion is not alive please try again with another Champion!");
            }
            else {
                System.out.println("Healer attacked the enemy (" + healer.dealDamage() + " damage attack)");
                enemy.takeDamage(healer.dealDamage());
            }
            }

    }


    /***
     * User is asked to enter the target of Enemy Entity.
     * If user chooses a dead player than will receive an error message that is asking to try again
     * with another player. After a valid input, the enemy deals the damage depending on the player role
     */
    public synchronized void enemyAttack(){

        Random random = new Random();

        int value = random.nextInt(3 + 1) + 1;

        if (value == 1){
            if(tank.getHealthPoints() <= 0){
                enemyAttack();
            }
            else {
                tank.takeDamage(enemy.dealDamage());
            }
        }
        else if (value == 2){
            if(damageDealer.getHealthPoints() <= 0){
                enemyAttack();
            }
            else {
                damageDealer.takeDamage(enemy.dealDamage());
            }
        }
        else if (value == 3){
            if(healer.getHealthPoints() <= 0){
                enemyAttack();
            }
            else {
                healer.takeDamage(enemy.dealDamage());
            }
        }
    }

    /***
     * User is asked to enter a player to heal.
     * The chosen player will get healed by the amount of healer's (mind value + 10) HP
     */
    public synchronized void healPlayer(){


        int minHealth = 100;
        char temp = 't';


        if (tank.isAlive() && minHealth >= tank.getHealthPoints()){
            minHealth = tank.getHealthPoints();
            temp = 't';
        }

        if (damageDealer.isAlive() && minHealth >= damageDealer.getHealthPoints()){
            minHealth = damageDealer.getHealthPoints();
            temp = 'd';
        }

        if (healer.isAlive() && minHealth >= healer.getHealthPoints()){
            temp = 'h';
        }






        if(healer.getHealthPoints() > 0){

            if (temp == 't'){

                if(tank.getHealthPoints() + healer.heal() >= 100){
                    tank.setHealthPoints(100);
                }
                else{
                    tank.setHealthPoints(tank.getHealthPoints() + healer.heal());
                }

                System.out.println("The tank has been healed " + healer.heal() + " HP");

            }

            else if(temp == 'd'){

                if(damageDealer.getHealthPoints() + healer.heal() >= 100){
                    damageDealer.setHealthPoints(100);
                }
                else{
                    damageDealer.setHealthPoints(damageDealer.getHealthPoints() + healer.heal());
                }
                System.out.println("The damage dealer has been healed " + healer.heal() + " HP");

            }

            else {

                if(healer.getHealthPoints() + healer.heal() >= 100){
                    healer.setHealthPoints(100);
                }
                else{
                    healer.setHealthPoints(healer.getHealthPoints() + healer.heal());
                }
                System.out.println("The healer has been healed " + healer.heal() + " HP");

            }



        }

        else{
            System.out.println("No one has been healed because Healer is not alive!!");
        }
    }


}
