/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

/**
 *
 * @author angle
 */
public interface Actor {
    
    public void act(CombatLoop combatLoop, long dt);
    
    public boolean ready();
    
}
