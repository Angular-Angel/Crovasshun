/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.MoveAction;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;

/**
 *
 * @author angle
 */
public class MouseMovePlotting extends MouseAdapter implements ControlMode {

    private CombatScreen combatScreen;
    private LocalAreaScreen localAreaScreen;
    private final Pointer mousePointer;
    private DisplayLine pointerLine;
    private MovePath movePath;
    private boolean showingPointer;

    public MouseMovePlotting() {
        this.mousePointer = new Pointer(new Point());
        showingPointer = false;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            if (combatScreen.area.hasTerrain(point)) {
                movePath.addPoint(new Pointer(point));
                pointerLine.start = point;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 3) {
            Point point = e.getPoint();
            point.x -= localAreaScreen.panX;
            point.y -= localAreaScreen.panY;
            
            movePath.removePoint(point);
            pointerLine.start = movePath.getEnd();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        point.x -= localAreaScreen.panX;
        point.y -= localAreaScreen.panY;
        if (combatScreen.area.hasTerrain(point)) {
            if (!showingPointer) {
                localAreaScreen.drawables.add(mousePointer);
                localAreaScreen.drawables.add(pointerLine);
                showingPointer = true;
            }
            mousePointer.point.setLocation(point);
            localAreaScreen.repaint();
        } else if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            localAreaScreen.drawables.remove(pointerLine);
            showingPointer = false;
            localAreaScreen.repaint();
        }
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        if (showingPointer) {
            localAreaScreen.drawables.remove(mousePointer);
            localAreaScreen.drawables.remove(pointerLine);
            showingPointer = false;
            localAreaScreen.repaint();
        }
    }
    
    @Override
    public void init(CombatScreen combatScreen) {
        this.combatScreen = combatScreen;
        this.localAreaScreen = combatScreen.localAreaScreen;
        localAreaScreen.addMouseListener(this);
        localAreaScreen.addMouseMotionListener(this);
        
        this.movePath =  new MovePath(combatScreen.combatLoop.player.body.getCenterPoint());
        localAreaScreen.drawables.add(movePath);
        
        this.pointerLine = new DisplayLine(movePath.getEnd(), mousePointer.point);
        localAreaScreen.drawables.add(pointerLine);
        
        MouseMovePlotting self = this;
        
        ButtonScreen buttonScreen = new ButtonScreen();
        
        //adds the buttons.
        ASCIIButton button = new ASCIIButton("Move");
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("Move") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movePath.pointers.size() > 0) {
                    MoveAction moveAction = new MoveAction();
                    for (Pointer pointer : movePath.pointers) {
                        moveAction.addPoint(pointer.point);
                    }
                    combatScreen.combatLoop.player.addAction(moveAction);
                }
                self.end();
            }
        });
        buttonScreen.add(button);
        
        button = new ASCIIButton("Cancel");
        button.setAlignmentY(Component.CENTER_ALIGNMENT);
        button.setAction(new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                self.end();
            }
        });
        buttonScreen.add(button);
        
        combatScreen.commandScreen.pushButtonScreen(buttonScreen);
    }

    @Override
    public void end() {
        localAreaScreen.removeMouseListener(this);
        localAreaScreen.removeMouseMotionListener(this);
        
        localAreaScreen.drawables.remove(movePath);
        localAreaScreen.drawables.remove(mousePointer);
        localAreaScreen.drawables.remove(pointerLine);
        showingPointer = false;
        
        combatScreen.commandScreen.popButtonScreen();
        localAreaScreen.repaint();
    }
}
