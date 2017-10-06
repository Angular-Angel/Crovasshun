/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crovasshun;

/**
 *
 * @author angle
 */
public interface BodyAction {
    
    public boolean isValid();
    
    public void perform(Body body);
    
}
