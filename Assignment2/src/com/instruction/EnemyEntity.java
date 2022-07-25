package com.instruction;

/***
 * EnemyEntity Class implements PotencyCalcutor to support fight dynamics
 */
public class EnemyEntity extends Thread implements PotencyCalculator{

    private int entityID;
    private int healthPoints;
    private int baseDamage;
    private EncounterManager encounterManager;

    /***
     * Constructor of EnemyEntity
     * @param entityID  The entityID of the object as Integer
     * @param healthPoints  The Health Points is important to understand if the object is alive or dead
     * @param baseDamage    The base damage is being used in combats to deal damage to Players
     */
    public EnemyEntity(int entityID, int healthPoints, int baseDamage) {
        this.entityID = entityID;
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;
    }

    public void setEncounterManager(EncounterManager encounterManager) {
        this.encounterManager = encounterManager;
    }

    public int getEntityID() {                          // Returns entity id value
        return entityID;
    }

    public void setEntityID(int entityID) {             // Sets entity id value
        this.entityID = entityID;
    }

    public int getHealthPoints() {                      // Returns health points value
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {     // Sets health points value
        this.healthPoints = healthPoints;
    }

    public int getBaseDamage() {                        // Returns base damage value
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {         // Sets base damage value
        this.baseDamage = baseDamage;
    }

    @Override
    public int dealDamage(){                            // returns base damage value
        return getBaseDamage();
    }

    @Override
    public void takeDamage(int damageReceived){                 // Decrements current health point by input value (damageReceived)
        setHealthPoints(getHealthPoints() - damageReceived);    // Then sets the result value as new health points
    }

    public void run(){
        int counter = 0;
        while(this.getHealthPoints() > 0 && encounterManager.playersAreAlive())
        {
            if(counter < 3)
            {
                encounterManager.enemyAttack();
                counter++;
            }
            else{
                encounterManager.groupWideAttack();
                counter = 0;
            }

            encounterManager.printStats();

            try {
                Thread.sleep(1000);
            }catch (InterruptedException ex){

            }
        }
    }

}
