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
public class Explosion {
    
    private double x, y;
    Ticker ticker;
    CAP3027TermProject pv;
    BufferedImage image;
    Enemy e;

    public Explosion(double x, double y, CAP3027TermProject pv, Enemy e){
        this.x = x;
        this.y = y;
        this.pv = pv;
        image = pv.getExplosions(1);
        ticker = new Ticker (pv, 2);
        this.e = e;
        e.activateExplosion(this);
        pv.explosion.playOnce();
//        ticker = 0;
    }
    
    public void tick (){
//        ticker = ticker + 3;
//        System.out.println(ticker);
        ticker.tick();
    }
    
    public void render(Graphics g){
        if (ticker.getSecondFrag() == 1){
            image = pv.getExplosions(2);
            g.drawImage(image, (int)x, (int)y, null);
        }
        else if (ticker.getSecondFrag() == 2){
            image = pv.getExplosions(3);
            g.drawImage(image, (int)x, (int)y, null);
        }
        else if (ticker.getSecondFrag() == 3){
            image = pv.getExplosions(4);
            g.drawImage(image, (int)x, (int)y, null);
        }
        else if (ticker.getSecondFrag() == 4){
            image = pv.getExplosions(5);
            g.drawImage(image, (int)x, (int)y, null);
        }
        else {
            image = pv.getExplosions(1);
            g.drawImage(image, (int)x, (int)y, null);
        }
        if (ticker.getSecondFrag() >= 5){
            e.endExplosion(this);
        }

//        if (ticker > 0){
//        Graphics2D g2d = (Graphics2D) g;
//       //tip = new Rectangle(x, y, 60, 120);
//        g2d.setColor(Color.RED);
//        g2d.fillOval((int) x, (int)y,(int)(1+ticker/30) , (int)(1+ticker/30));
////      g2d.drawRect((int)x, (int)y, 15, 15); 
//        }
        
        
    }
    
//    public double getTicker(){
//        return ticker;
//    }
}
