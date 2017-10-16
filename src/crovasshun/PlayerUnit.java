/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class PlayerUnit implements Actor {
    
    public Body body;
    
    private final ArrayList<BodyAction> actions;
    
    public PlayerUnit(Body body) {
        this.body = body;
        actions = new ArrayList<>();
    }

    @Override
    public boolean ready() {
        return actions.size() > 0;
    }
    
    public void addAction(BodyAction action) {
        actions.add(action);
    }

    @Override
    public synchronized void act(CombatLoop combatLoop, long dt) {
        
        BodyAction action = actions.get(0);
        if (action.isValid())action.perform(body, dt);
        else actions.remove(action);
        if (action.isDone()) {
            action.onEnd(combatLoop.combatScreen);
            actions.remove(action);
        }
    }
    
}
