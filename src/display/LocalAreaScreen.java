/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import crovasshun.Body;
import crovasshun.GameHex;
import crovasshun.LargeObject;
import crovasshun.LocalArea;
import crovasshun.LocalMapGenerator;
import crovasshun.Terrain;
import crovasshun.TerrainType;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author angle
 */
public class LocalAreaScreen extends Screen {
    private int pixelsPerBorder;
    private int sideLength;	// length of one side
    private int triangleLength;	// short side of 30o triangle outside of each hex
    private int hexRadius;	// radius of inscribed circle (centre to middle of each side). r= h/2
    private int hexHeight;	// height. Distance between centres of two adjacent hexes. Distance between two opposite sides in a hex.
    private Point selectedTile;
    private CombatPane combatPane;
    
    private LocalArea area;

    public LocalAreaScreen(CombatPane combatPane) {
        this(15, combatPane);
    }
    
    public LocalAreaScreen(int borderSize, CombatPane combatPane) {
        super();
        this.combatPane = combatPane;
        setFont(new Font("Monospaced", Font.PLAIN, 12));
        setBackground(Color.BLACK);
        pixelsPerBorder = borderSize;
        
        LocalAreaScreen localAreaScreen = this;
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point tile = localAreaScreen.pxtoHex(e.getX(), e.getY());
                try {
                    /*GameHex hex = area.getHex(tile);
                    if (hex.terrain != null) {
                        selectedTile = tile;
                        combatPane.showTileReadout(hex);
                    } else {
                        selectedTile = null;
                        combatPane.hideTerrainReadout();
                    }*/
                    
                } catch (IllegalArgumentException ex) {
                    selectedTile = null;
                    combatPane.hideTerrainReadout();
                }
                localAreaScreen.repaint();
            }

            
        });
        
        area = LocalMapGenerator.getObelisk(1600, 800);
        
        area.bodies.add(new Body("Player", new ASCIISprite(new Color(30, 30, 30, 255), new Color(255, 182, 193), "_ |\n" +
                                                                                     "-0-"), 
                new ASCIISprite(new Color(0, 0, 0, 0), new Color(255, 182, 193), "   0\n" +
                                                          "1_/|\\_O\n" +
                                                          "   |\n" +
                                                          "  / \\\n" +
                                                          "  | |"),new Point(105, 150)));
    }
    
    public void setSide(int side) {
        sideLength = side;
        triangleLength =  (int) (sideLength / 2);	   //t = s sin(30) = (int) CalculateH(s);
        hexRadius =  (int) (sideLength * 0.8660254037844); //r = s cos(30) = (int) CalculateR(s); 
        hexHeight=2*hexRadius;
    }
    
    public void setHeight(int height) {
        hexHeight = height;                             // h = basic dimension: height (distance between two adj centresr aka size)
        hexRadius = hexHeight/2;			// r = radius of inscribed circle
        sideLength = (int) (hexHeight / 1.73205);	// s = (h/2)/cos(30)= (h/2) / (sqrt(3)/2) = h / sqrt(3)
        triangleLength = (int) (hexRadius / 1.73205);	// t = (h/2) tan30 = (h/2) 1/sqrt(3) = h / (2 sqrt(3)) = r / sqrt(3)
    }
    
    private Polygon hex (int x, int y) {
        if (sideLength == 0  || hexHeight == 0) {
            System.out.println("ERROR: size of hex has not been set");
            return new Polygon();
        }

        int[] cx, cy;

        cx = new int[] {x+triangleLength,x+sideLength+triangleLength,x+sideLength+(2*triangleLength),x+sideLength+triangleLength,x+triangleLength,x};	//this is for the whole hexagon to be below and to the right of this point

        cy = new int[] {y,y,y+hexRadius,y+(2*hexRadius),y+(2*hexRadius),y+hexRadius};
        return new Polygon(cx,cy,6);
    }
    
    private Polygon hex(Point point) {
        return hex(point.x, point.y);
    }
    
    public Point getHexPosition(int i, int j) {
        return new Point(i * (sideLength+triangleLength) + pixelsPerBorder, j * hexHeight + (i%2) * hexHeight/2 + pixelsPerBorder);
    }
    
    public Point getHexPosition(float i, float j) {
        return new Point((int) (i * (sideLength+triangleLength) + pixelsPerBorder), (int) (j * hexHeight + (i%2) * hexHeight/2 + pixelsPerBorder));
    }
    
    public Point getHexCenterPosition(int i, int j) {
        Point ret = getHexPosition(i, j);
        
        ret.x += triangleLength + sideLength/2;
        ret.y += hexRadius;
        
        return ret;
    }
    
    public Point getHexCenterPosition(float i, float j) {
        Point ret = getHexPosition(i, j);
        
        ret.x += triangleLength + sideLength/2;
        ret.y += hexRadius;
        
        return ret;
    }
    
    public Polygon getHex(int i, int j) {
        Point pos = getHexPosition(i, j);
        return hex(pos);
    }
    
    
    public void drawHex(int i, int j, Graphics2D g) {
        Polygon hex = getHex(i, j);
        g.setColor(Color.WHITE);
        g.drawPolygon(hex);
    }
    
    public void drawHex(Point point, Graphics2D g) {
        drawHex(point.x, point.y, g);
    }
    
    public void fillHex(int i, int j, Graphics2D g, ASCIITexture texture) {
        Point pos = getHexPosition(i, j);
        Polygon hex = hex(pos);
        texture.fillShape(hex, g);
    }
    
    public Point pxtoHex(int mx, int my) {
        Point p = new Point(-1,-1);

        //correction for BORDERS and XYVertex
        mx -= pixelsPerBorder;
        my -= pixelsPerBorder;

        int x = (int) (mx / (sideLength+triangleLength)); //this gives a quick value for x. It works only on odd cols and doesn't handle the triangle sections. It assumes that the hexagon is a rectangle with width s+t (=1.5*s).
        int y = (int) ((my - (x%2)*hexRadius)/hexHeight); //this gives the row easily. It needs to be offset by h/2 (=r)if it is in an even column

        /******FIX for clicking in the triangle spaces (on the left side only)*******/
        //dx,dy are the number of pixels from the hex boundary. (ie. relative to the hex clicked in)
        int dx = mx - x*(sideLength+triangleLength);
        int dy = my - y*hexHeight;

        if (my - (x%2)*hexRadius < 0) return p; // prevent clicking in the open halfhexes at the top of the screen

        //System.out.println("dx=" + dx + " dy=" + dy + "  > " + dx*r/t + " <");

        //even columns
        if (x%2==0) {
            if (dy > hexRadius) {	//bottom half of hexes
                if (dx * hexRadius /triangleLength < dy - hexRadius) {
                        x--;
                }
            }
            if (dy < hexRadius) {	//top half of hexes
                if ((triangleLength - dx)*hexRadius/triangleLength > dy ) {
                        x--;
                        y--;
                }
            }
        } else {  // odd columns
            if (dy > hexHeight) {	//bottom half of hexes
                if (dx * hexRadius/triangleLength < dy - hexHeight) {
                        x--;
                        y++;
                }
            }
            if (dy < hexHeight) {	//top half of hexes
                if ((triangleLength - dx)*hexRadius/triangleLength > dy - hexRadius) {
                        x--;
                }
            }
        }
        p.x=x;
        p.y=y;
        return p;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintComponent(g2);
        
        g2.translate(10, 10);
        
        for (Terrain t : area.terrain) {
            t.draw(g2);
        }
        
        for (Body b : area.bodies) {
            b.draw(g2);
        }
        
        for (LargeObject l : area.objects) {
            l.draw(g2);
        }
    }
    
}
