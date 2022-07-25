package com.instruction;

/***
 * Abstract Player class is the parent of Healer, Tank and DamageDealer classes
 * and impelements PotencyCalculator interface to support fight dynamics
 */

public abstract class Player extends Thread implements PotencyCalculator {

    private String role;
    private int entityID;
    private int healthPoints;
    private int baseDamage;
    private EncounterManager encounterManager;



    /***
     * Constructor of Player Class
     * @param role  The role of the Object as String
     * @param entityID  The entityID of the object as Integer
     * @param healthPoints  The Health Points is important to understand if the object is alive or dead
     * @param baseDamage    The base damage is being used in combats to deal damage to Enemy entity with intelligence
     */
    public Player(String role, int entityID, int healthPoints, int baseDamage) {
        this.role = role;
        this.entityID = entityID;
        this.healthPoints = healthPoints;
        this.baseDamage = baseDamage;
    }

    public void setEncounterManager(EncounterManager encounterManager) {
        this.encounterManager = encounterManager;
    }

    public String getRole() {                           // Returns role value
        return role;
    }

    public void setRole(String role) {                  // Sets role value
        this.role = role;
    }

    public int getEntityID() {                          // Returns entity ID value
        return entityID;
    }

    public void setEntityID(int entityID) {             // Sets entity ID value
        this.entityID = entityID;
    }

    public int getHealthPoints() {                      // Returns health points value
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {     // Sets health points value
        this.healthPoints = healthPoints;
    }

    public int getBaseDamage() {                        // Returns Base damage value
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {         // Sets base Damage value
        this.baseDamage = baseDamage;
    }

    public EncounterManager getEncounterManager() {
        return encounterManager;
    }

    @Override
    abstract public int dealDamage();                       // Abstract deal damage method to be implemented by child classes

    @Override
    abstract public void takeDamage(int damageReceived);    // Abstract take damage method to be implemented by child classes

    @Override
    abstract public void run();

}
