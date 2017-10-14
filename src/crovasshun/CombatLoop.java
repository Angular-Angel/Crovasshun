/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angle
 */
public class CombatLoop implements Runnable {
    
    private ArrayList<Actor> actors;
    public Player player;
    public CombatScreen combatScreen;
    
    public CombatLoop(CombatScreen combatScreen) {
        this.combatScreen = combatScreen;
        actors = new ArrayList<>();
    }
    
    public void addActor(Actor actor) {
        actors.add(actor);
    }
    
    private synchronized void update() {
        for (Actor a : actors) {
            a.act(this);
        }
    }
    
    public synchronized void run() {
        long waitTimer = System.currentTimeMillis();
        while(true) {
            try {
                update();
                combatScreen.localAreaScreen.repaint();
                waitTimer += 16 - System.currentTimeMillis();
                if (waitTimer > 0)
                    wait(waitTimer);
                waitTimer = System.currentTimeMillis();
            } catch (InterruptedException ex) {
                Logger.getLogger(CombatLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
