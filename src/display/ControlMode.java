/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

/**
 *
 * @author angle
 */
public interface ControlMode {
    
    public void init(CombatScreen combatScreen);
    
    public void end();
    
    public void step(long dt);
    
}
