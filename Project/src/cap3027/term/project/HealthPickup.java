/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cap3027.term.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Kevin
 */
public class HealthPickup {
    
    private double x, y;
    CAP3027TermProject pv;
    BufferedImage image;

    public HealthPickup(double x, double y, CAP3027TermProject pv){
        this.x = x;
        this.y = y;
        this.pv = pv;
        image = pv.getHealthPickupImage();

    }
    
    public void tick(){
        if (isNear()){
            pv.getPlayer().getHealthBar().fullHeal();
            pv.health_pickup = null;
        }
    }
    public void render(Graphics g){
        g.drawImage(image, (int)x, (int)y, null); 
    }
    public boolean isNear(){
        return (Math.abs(this.x - pv.getPlayer().getX()) < 10)&&(Math.abs(this.y - pv.getPlayer().getY()) < 10);
    }

}
