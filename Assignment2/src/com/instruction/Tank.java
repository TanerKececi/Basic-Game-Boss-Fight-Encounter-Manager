package com.instruction;

/***
 * Tank class which extends Player class
 */

public class Tank extends Player{

    private int defense;
    private EncounterManager encounterManager;

    /***
     * Constructor of Healer Class that calls Constructor of parent Class named Player
     * @param role  The role of the Object generally "Tank"
     * @param entityID  The entityID of the object
     * @param healthPoints  The Health Points is important to understand if the object is alive or dead
     * @param baseDamage    The base damage is being used in combats to deal damage to Enemy entity with intelligence
     * @param defense   The defense is being used in take damage mechanics as an input value
     */
    public Tank(String role, int entityID, int healthPoints, int baseDamage, int defense) {
        super(role, entityID, healthPoints, baseDamage);
        this.defense = defense;
    }

    public int getDefense() {                           // returns defense value
        return defense;
    }

    public void setDefense(int defense) {               // sets defense value
        this.defense = defense;
    }

    @Override
    public int dealDamage(){                            // returns base damage value
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damageReceived){                                 // Decrements current health point by difference of
        setHealthPoints(getHealthPoints() - (damageReceived - getDefense()));   // input value (damageReceived) and defense value
    }                                                                           // then sets the result value as new health points

    @Override
    public void run(){
        while(this.getHealthPoints() > 0 && getEncounterManager().enemyIsAlive())
        {
            getEncounterManager().playerAttack('t');

            getEncounterManager().printStats();

            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
        }
    }

}
