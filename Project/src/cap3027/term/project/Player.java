/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author KPC
 */
public class Player {
    
    private double x;
    private double y;
    private double velX = 0;
    private double velY = 0;
    
    private HealthBar healthbar;
    private int health;
    
    private int checker = 200;
    
    private boolean xCheck = true;
    private boolean yCheck = true;
    private boolean lvl_2_1st_wait = false;
    private boolean lvl_2_2nd_wait = false;
    private boolean tip1_shown = false;
    private boolean tip2_shown = false;
    private boolean tip3_shown = false;
    private boolean tip4_shown = false;
    private boolean tip5_shown = false;
    private boolean alive = true;
    
    private String direction = "up";
            
    private BufferedImage player;
    CAP3027TermProject pv;
    private Terrain terr;
    private Tips tip = new Tips();
    GrabImage gb;
    
    public Player(double x, double y, CAP3027TermProject pv){
        
        healthbar = new HealthBar(this);
        this.x = x;
        this.y = y;
        this.pv = pv;
        gb = new GrabImage(pv.getPlayerImage());
        player = gb.grabImage();
        
    }
    public Player(double x, double y, CAP3027TermProject pv, int health){
        
        healthbar = new HealthBar(this, health);
        this.x = x;
        this.y = y;
        this.pv = pv;
        gb = new GrabImage(pv.getPlayerImage());
        player = gb.grabImage();
        this.health = health;
    }
    
    public void changeDirection(BufferedImage img, String s){
        
        direction = s;
        player = img;       
    }
    
    public String getDirection(){
        return direction;
    }
    
    public void tick(Terrain ter, CAP3027TermProject pv){
        if(alive){
        terr = ter;
        ++checker;
        xCheck = true;
        yCheck = true;
        if (velX > 0){
          if (terr.isWalkable(x+velX+16, y+velY)){
            xCheck = true;
        }
          else{
              xCheck = false;
          }
        }
        if (velY > 0){
            if (terr.isWalkable(x+velX, y+velY+16)){
            yCheck = true;
        }
            else{
                yCheck = false;
            }
        }
        if (terr.isWalkable(x+velX, y+velY)&&xCheck&&yCheck){
            x+=velX;
            y+=velY;
        }
        
        if (x < 0){
            x = 0;
        }
        if (x > 500-15){
            x = 500-15;
        }
        if (y < 0){
            y = 0;
        }
        if (y > 500 -15){
            y = 500 -15;
        }
        if (terr.advance(x, y) && pv.objectivesAreComplete()){
            
            pv.incrementLevel(); 
            pv.init();
        }
        else if(terr.advance(x, y) && checker >= 200 && !pv.objectivesAreComplete()){
            pv.getTip().tipSet("Objectives incomplete", 350, 120);
            pv.setShowTip(true);
            pv.setState("Paused");
            checker = 0;
        }
        
        //Level 1
        
        if(pv.getLevel() == 1){
            if(x == 234.5 && y == 485 && !tip1_shown){
            pv.getTip().tipSet("Don't get hit!", 300, 460);
            pv.setShowTip(true);
            pv.setState("Paused");
            tip1_shown = true;
        }
            if(y < 200 && !tip2_shown){
            pv.getTip().tipSet("Get near the machine and disable it by pressing \"D\"", 105, 50);
//            pv.health_pickup = new HealthPickup(182, 78, pv);
            pv.setShowTip(true);
            pv.setState("Paused");
            tip2_shown = true; 
        }
        }
        
        //Level 2
        
        else if(pv.getLevel() == 2){
            if(!tip1_shown){
            pv.getTip().tipSet("Use A to fire!", (int)x + 30, (int)y -30);
            pv.setShowTip(true);
            pv.setState("Paused");
            tip1_shown = true;
        }
            if(y < 140 && !tip2_shown){
            pv.getTip().tipSet("Some enemies are tougher", (int)x + 30, (int)y -30);
            pv.setEnemy(2, 400.5, 124.5, "lvl_2_2");
            pv.setShowTip(true);
            pv.setState("Paused");
            tip2_shown = true; 
        }
            if(y > 275 && x > 380 && !tip3_shown){
            pv.getTip().tipSet("It's an ambush!", (int)x - 180, (int)y -30);
            pv.setEnemy(1, 400.5, 127.5, "lvl_2_1");
            pv.setEnemy(2, 400.5, 440.5, "lvl_2_1");
            pv.setTicker(10);
            lvl_2_1st_wait = true;
            lvl_2_2nd_wait = true;
            pv.setShowTip(true);
            pv.setState("Paused");
            tip3_shown = true; 
        }
            if(y > 430 && x > 380 && !tip4_shown){
            pv.setEnemy(6, 142.5, 366, "lvl_2_3", 25);
            pv.setEnemy(7, 178.5, 366, "lvl_2_3", 45);
            pv.setEnemy(8, 214.5, 366, "lvl_2_3", 65);
            pv.setEnemy(9, 142.5, 330, "lvl_2_4", 35);
            pv.setEnemy(10, 142.5, 294, "lvl_2_4", 55);
            tip4_shown = true; 
        }
            if(y < 210 && y > 200 && x > 100 && x < 140 && !tip5_shown){
                pv.health_pickup = new HealthPickup(207, 265, pv);
            tip5_shown = true; 
        }
        }
        if(healthbar.getHealth() <= 0){
            pv.getTip().tipSet("You have FAILED!", (int)x, (int)y);
            pv.setShowTip(true);
            pv.endGame();
            alive = !alive;
        }
        
        }
        if (lvl_2_1st_wait){
            if (pv.getTicker().getSeconds() >= 2){
                pv.setEnemy(3, 400.5, 127.5, "lvl_2_1");
                lvl_2_1st_wait = false;
            }
        }
        if (lvl_2_2nd_wait){
            if (pv.getTicker().getSeconds() >= 3){
                pv.setEnemy(4, 400.5, 440.5, "lvl_2_2");
                lvl_2_2nd_wait = false;
            }
        }
        if (pv.getLevel() == 2 && x > 137 && x < 255 && y > 335 && y < 370){
            if (pv.enemyIsDead(1) && pv.enemyIsDead(2) && pv.enemyIsDead(3)&& pv.enemyIsDead(4)){
                pv.objectivesComplete();
            }
        }
    }
    
    public void render(Graphics g){
        g.drawImage(player, (int)x, (int)y, null);
        healthbar.render(g);
    }
    
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public HealthBar getHealthBar(){
        return healthbar;
    }
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public void setVelX(double velX){
        this.velX = velX;
    }
    public void setVelY(double velY){
        this.velY = velY;
    }
    public boolean bulletHit(double x, double y){
        
        Rectangle p = new Rectangle((int)this.x, (int)this.y, 17, 20);
        Rectangle b = new Rectangle((int)x, (int)y, 1, 5);
        if(p.intersects(b)){
            return true;
        }
        return false;
    }
    public boolean missileHit(double x, double y){
        
        Rectangle p = new Rectangle((int)this.x, (int)this.y, 17, 20);
        Rectangle b = new Rectangle((int)x, (int)y, 10, 10);
        if(p.intersects(b)){
            return true;
        }
        return false;
    }
    public boolean enemyHit(double x, double y){
        
        Rectangle p = new Rectangle((int)this.x, (int)this.y, 17, 20);
        Rectangle b = new Rectangle((int)x, (int)y, 15, 15);
        if(p.intersects(b)){
            return true;
        }
        return false;
    }
    public void resetTips(){
        tip1_shown = !tip1_shown;
        tip2_shown = !tip2_shown;
        tip3_shown = !tip3_shown;
        tip4_shown = !tip4_shown;
    }
}
