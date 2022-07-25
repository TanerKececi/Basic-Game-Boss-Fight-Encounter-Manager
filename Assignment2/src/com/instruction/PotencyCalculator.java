package com.instruction;

/***
 * PotencyCalculator interface is being implemented
 * by Player and EnemyEntity to execute fight dynamics
 */
public interface PotencyCalculator {

    int dealDamage();                       // This method is going to be implemented to deal damage to the opponent(s)

    void takeDamage(int damageReceived);    // This method is going to be implemented to take damage from the opponent(s)

}
