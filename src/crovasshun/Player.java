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
public class Player implements Actor {
    
    public final ArrayList<BodyAction> actions;
    
    public Player() {
        actions = new ArrayList<>();
    }

    @Override
    public boolean ready() {
        return false;
    }

    @Override
    public void act() {
        
    }
    
}
