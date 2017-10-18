/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Actor;
import crovasshun.Body;
import crovasshun.CombatLoop;
import crovasshun.LocalArea;
import crovasshun.PlayerUnit;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;

/**
 *
 * @author angle
 */
public class CombatScreen extends Screen {
    
    private final Screen tileScreen;
    public final LocalAreaScreen localAreaScreen;
    public final CommandScreen commandScreen;
    private final JLayeredPane layeredPane;
    public LocalArea area;
    public final CombatLoop combatLoop;
    public final ArrayList<PlayerUnit> units;
    public PlayerUnit currentUnit;
    public final HashSet<ControlMode> controlModes;
    
    public CombatScreen(LocalArea area) {
        
        controlModes = new HashSet<>();
        
        this.area = area;
        
        combatLoop = new CombatLoop(this);
        
        units = new ArrayList<>();
        
        for (Body b : area.bodies) {
            Actor actor = b.getActor();
            combatLoop.addActor(actor);
            if (actor instanceof PlayerUnit)
                units.add((PlayerUnit) actor);
        }
        
        currentUnit = units.get(0);
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        layeredPane = new JLayeredPane();
        add(layeredPane);
        
        localAreaScreen = new LocalAreaScreen(this);
        
        CombatScreen combatScreen = this;
        
        localAreaScreen.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                try { 
                    Point point = e.getPoint();
                    point.x -= localAreaScreen.panX;
                    point.y -= localAreaScreen.panY;
                    combatScreen.showTileReadout(combatScreen.area.getDetails(point));
                } catch (IllegalArgumentException ex) { 
                    System.out.println(ex);
                    combatScreen.hideTileReadout(); 
                }
            }
        });
        
        new MousePanning().init(combatScreen);
        layeredPane.add(localAreaScreen, new Integer(0));
        
        commandScreen = new CommandScreen(this);
        add(commandScreen);
        
        tileScreen = new Screen();
        tileScreen.setLayout(new FlowLayout());
    }
    
    public void processInput(long dt) {
        synchronized (controlModes) {
            for (Iterator<ControlMode> it = controlModes.iterator(); it.hasNext();) {
                ControlMode mode = it.next();
                mode.step(dt);
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        layeredPane.setPreferredSize(new Dimension(getWidth(), getHeight() * 3/4));
        localAreaScreen.setSize(layeredPane.getWidth(), layeredPane.getHeight());
        tileScreen.setLocation(layeredPane.getWidth()*5/6 -20, 10);
        revalidate();
        super.paintComponent(g);
    }
    
    public void showTileReadout(ArrayList<Screen> details) {
        if (details.isEmpty()) {
            hideTileReadout();
            return;
        }
        
        tileScreen.removeAll();
        
        for (Screen s : details) {
            tileScreen.add(s);
        }
        
        tileScreen.setSize(tileScreen.getPreferredSize());
        layeredPane.remove(tileScreen);
        layeredPane.add(tileScreen, new Integer(1));
    }
    
    public void hideTileReadout() {
        layeredPane.remove(tileScreen);
        repaint();
    }
    
    @Override
    public void start() {
        combatLoop.run();
    }
    
}
