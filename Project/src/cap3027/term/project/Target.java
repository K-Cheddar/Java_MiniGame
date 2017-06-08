/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cap3027.term.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Kevin
 */
public class Target {
    
    private double x, y;
    private double time;
    public Target(){
        
    }
    public Target(double x, double y, double time){
        this.x = x;
        this.y = y;
        this.time = time*60;
    }
    
    public void tick (){
        if (time > -1){
            --time;            
        }

    }
    
    public void render(Graphics g){
        
        if (time > 0){
        Graphics2D g2d = (Graphics2D) g;
       //tip = new Rectangle(x, y, 60, 120);
        g2d.setColor(Color.RED);
        g2d.drawRect((int)x, (int)y, 15, 15); 
        }
    }
    
}
