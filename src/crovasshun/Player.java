/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angle
 */
public class Player implements Actor {
    
    public Body body;
    
    private final ArrayList<BodyAction> actions;
    
    public Player(Body body) {
        this.body = body;
        actions = new ArrayList<>();
    }

    public boolean ready() {
        return actions.size() > 0;
    }
    
    public void addAction(BodyAction action) {
        actions.add(action);
    }

    @Override
    public synchronized void act(CombatLoop combatLoop) {
        
        combatLoop.player = this;
        try {
            while(!ready())
                wait(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actions.get(0).perform(body);
        actions.remove(0);
    }
    
}
