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
    
    private synchronized void update(long dt) {
        for (Actor a : actors) {
            a.act(this, dt);
        }
    }
    
    private static final int MILLISECONDS_PER_FRAME = 1000/60; //120 frames per second
    
    public synchronized void run() {
        long lastTime = System.currentTimeMillis();
        while(true) {
            long currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;
            update(dt);
            combatScreen.localAreaScreen.repaint();
            lastTime = currentTime;
        }
    }
}
