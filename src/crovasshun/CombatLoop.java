/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

import display.CombatScreen;
import java.util.ArrayList;

/**
 *
 * @author angle
 */
public class CombatLoop {
    
    private ArrayList<Actor> actors;
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
    
    private boolean actorsReady() {
        for (Actor a : actors) {
            if (!a.ready()) return false;
        }
        return true;
    }
    
    public synchronized void run() {
        long lastTime = System.currentTimeMillis();
        while(true) {
            long currentTime = System.currentTimeMillis();
            long dt = currentTime - lastTime;
            combatScreen.processInput(dt);
            if (actorsReady()) update(dt);
            combatScreen.localAreaScreen.repaint();
            lastTime = currentTime;
        }
    }
}
