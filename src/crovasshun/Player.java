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
    
    private Body body;
    
    public final ArrayList<BodyAction> actions;
    
    public Player(Body body) {
        this.body = body;
        actions = new ArrayList<>();
    }

    public boolean ready() {
        return actions.size() > 0;
    }

    @Override
    public synchronized void act(CombatLoop combatLoop) {
        
        combatLoop.player = this;
        try {
            while(!ready())
                wait(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        actions.get(0).perform(body);
        actions.remove(0);
    }
    
}
