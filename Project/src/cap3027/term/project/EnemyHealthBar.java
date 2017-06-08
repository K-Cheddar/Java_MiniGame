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
 * @author KPC
 */
public class EnemyHealthBar {
    
    private String s;
    private int health = 100;
    private int x;
    private int y;
    private int i;
    private int counter = 0;
    private Color [] colorArr = new Color[100];
    private int r1, r2, r3, b1, b2, b3, g1, g2, g3;
    private int diffR12, diffG12, diffB12, diffR23, diffG23, diffB23;
    private int cR, cB, cG, cFull;
    private int c_green = (255 << 24) | (0 << 16) | (255 << 8) | (0),
                c_yellow = (255 << 24) | (255 << 16) | (255 << 8) | (0), 
                c_red = (255 << 24) | (255 << 16) | (0 << 8) | (0);
    
    private Enemy e;
   
//    public void tipSet(String tip, int x, int y){
//        s = tip;
//        this.x = x;
//        this.y = y;
//    }
    
    public EnemyHealthBar(Enemy e){
        this.e = e;
    }
    
    public void render(Graphics g){
       
        r1 = (c_green >> 16) & 0x000000FF;
        g1 = (c_green >> 8) & 0x000000FF;
        b1 = (c_green) & 0x000000FF;
        r2 = (c_yellow >> 16) & 0x000000FF;
        g2 = (c_yellow >> 8) & 0x000000FF;
        b2 = (c_yellow) & 0x000000FF;
        r3 = (c_red >> 16) & 0x000000FF;
        g3 = (c_red >> 8) & 0x000000FF;
        b3 = (c_red) & 0x000000FF;
        
        diffR12 = r2-r1;
        diffG12 = g2-g1;
        diffB12 = b2-b1;
        diffR23 = r3-r2;
        diffG23 = g3-g2;
        diffB23 = b3-b2;
        
        counter = 0;
        //From Green to Yellow
        while(counter < 50){
                cR = r1 + ((diffR12/50)*counter);
                cG = g1 + ((diffG12/50)*counter);
                cB = b1 + ((diffB12/50)*counter);
                cFull = (255 << 24) | ( (cR) << 16) | ((cG) << 8) | (cB);
                Color currentColor = new Color (cFull);
                colorArr[counter] = currentColor;
            ++counter;
        }
        //From Yellow to Red
        counter = 0;
        while(counter < 50){
                cR = r2 + ((diffR23/50)*counter);
                cG = g2 + ((diffG23/50)*counter);
                cB = b2 + ((diffB23/50)*counter);
                cFull = (255 << 24) | ( (cR) << 16) | ((cG) << 8) | (cB);
                Color currentColor = new Color (cFull);
                colorArr[counter+50] = currentColor;
            ++counter;
        }
        
       Graphics2D g2d = (Graphics2D) g;
        //tip = new Rectangle(x, y, 60, 120);
        switch (e.getEnemyType()) {
            case "lvl_1_1":
                if (health >0){
                    g2d.setColor(Color.white);
                    g2d.drawRect((int)e.getX() -2, (int)e.getY()-8, 20, 5);
                    g2d.setColor(colorArr[100-health]);
                }  for (i = 0; i < health/5; ++i){
                 g2d.fillRect((int)e.getX() + i -2, (int)e.getY()-8, 1, 5);
             }        break;
            case "lvl_2_1":
                if (health >0){
                    g2d.setColor(Color.white);
                    g2d.drawRect((int)e.getX() -2, (int)e.getY()-5, 20, 5);
                    g2d.setColor(colorArr[100-health]);
                }  for (i = 0; i < health/5; ++i){
                 g2d.fillRect((int)e.getX() + i -2, (int)e.getY()-5, 1, 5);
             }        break;
            case "lvl_2_2":
                if (health >0){
                    g2d.setColor(Color.white);
                    g2d.drawRect((int)e.getX(), (int)e.getY()-5, 20, 5);
                    g2d.setColor(colorArr[100-health]);
                }  for (i = 0; i < health/5; ++i){
                 g2d.fillRect((int)e.getX() + i, (int)e.getY()-5, 1, 5);
             }        break;
        }
 
    }
    
    public void bulletHit10(){
        health -= 10;
    }
    public void bulletHit20(){
        health -= 20;
    }
    public void bulletHit25(){
        health -= 25;
    }
    public void bulletHit50(){
        health -= 50;
    }
    public void bulletHit100(){
        health -= 100;
    }
    
//    public void fullHeal(){
//        health = 100;
//    }
    public int getHealth(){
        return health;
    }
}
