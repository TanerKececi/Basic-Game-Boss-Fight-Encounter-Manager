package com.instruction;

/***
 * Damage Dealer Class which extends Player Class
 */

public class DamageDealer extends Player {

    private int intelligence;

    /***
     * Constructor of DamageDealer Class that calls Constructor of parent Class named Player
     * @param role  The role of the Object generally "DamageDealer"
     * @param entityID  The entityID of the object
     * @param healthPoints  The Health Points is important to understand if the object is alive or dead
     * @param baseDamage    The base damage is being used in combats to deal damage to Enemy entity with intelligence
     * @param intelligence  The Intelligence is being used in combats to deal damage to Enemy Entity with baseDamage
     */
    public DamageDealer(String role, int entityID, int healthPoints, int baseDamage, int intelligence) {
        super(role, entityID, healthPoints, baseDamage);                // Constructor of parent class
        this.intelligence = intelligence;
    }

    public int getIntelligence() {                                      // Returns intelligence value
        return intelligence;
    }

    public void setIntelligence(int intelligence) {                     // Sets intelligence value
        this.intelligence = intelligence;
    }

    @Override
    public int dealDamage(){                                            // Adds the return value of parent class's getBaseDamage()
        return getBaseDamage() + intelligence;                          // with intelligence variable and returns it
    }

    @Override
    public void takeDamage(int damageReceived){                         // Decrements current health point by input value (damageReceived)
        setHealthPoints(getHealthPoints() - damageReceived);            // Then sets the result value as new health points
    }

    @Override
     public void run(){

        while(this.getHealthPoints() > 0 && getEncounterManager().enemyIsAlive())
        {
            getEncounterManager().playerAttack('d');

            getEncounterManager().printStats();

            try {
                Thread.sleep(500);
            }catch (InterruptedException ex){

            }
        }


    }
}
