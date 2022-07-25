package com.instruction;

/***
 * Healer class which extends Player class
 */

public class Healer extends Player {

    private int mind;

    /***
     * Constructor of Healer Class that calls Constructor of parent Class named Player
     * @param role  The role of the Object generally "Healer"
     * @param entityID  The entityID of the object
     * @param healthPoints  The Health Points is important to understand if the object is alive or dead
     * @param baseDamage    The base damage is being used in combats to deal damage to Enemy entity with intelligence
     * @param mind  The mind is being used for healing mechanic as an input to calculations
     */
    public Healer(String role, int entityID, int healthPoints, int baseDamage, int mind) {
        super(role, entityID, healthPoints, baseDamage);
        this.mind = mind;
    }

    public int getMind() {                                         // returns mind value
        return mind;
    }

    public void setMind(int mind) {                                // sets mind value
        this.mind = mind;
    }

    public int heal(){                                             // returns the addition of
        return getMind() + 10;                                     // current mind value and ten
    }

    @Override
    public int dealDamage(){                                       // returns the base damage value
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damageReceived){                     // Decrements current health point by input value (damageReceived)
        setHealthPoints(getHealthPoints() - damageReceived);        // Then sets the result value as new health points
    }

    @Override
    public void run(){
        while(this.getHealthPoints() > 0 && getEncounterManager().enemyIsAlive())
        {
            getEncounterManager().healPlayer();

            getEncounterManager().printStats();

            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
        }
    }

}
