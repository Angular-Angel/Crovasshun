/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old;

import display.CombatScreen;

/**
 *
 * @author angle
 */
public interface BodyAction {
    
    public boolean isValid();
    
    public boolean isDone();
    
    public void perform(Body body, long dt);
    
    public void onEnd(CombatScreen combatScreen);
    
}
