/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author KPC
 */
public class Enemy {
    
    private double x;
    private double y;
    private double ticker = 50;
    private double missile_delay = 50;
    private double open_close = 25;
    private double tester = 0;
    private double velX;
    private double velY;
    private int r_setter = 0;
    private int l_setter = 0;
    private int enemy_number;
    
    private EnemyHealthBar healthbar;
    
    private boolean alive;
    private boolean xCheck = true;
    private boolean yCheck = true;
    private boolean movingX_Y;
    private boolean direction_change = false;
    
    private int gunside;
    private int missile_direction;
    private Random rand = new Random();
    
    private String enemy_type = "";
    private String direction = "";
    
    private BufferedImage enemy;
    CAP3027TermProject pv;
    GrabImage gb;
    Explosion explosion1, explosion2, explosion3;
    
    public Enemy(double x, double y, CAP3027TermProject pv, String s, int num){
        this.x = x;
        this.y = y;
        this.pv = pv;
        enemy_number = num;
        healthbar = new EnemyHealthBar(this);
        switch (s){
            case "lvl_1_1":{
                gb = new GrabImage(pv.getLevel1Machine());
                break;
            }
            case "lvl_2_1":{
                gb = new GrabImage(pv.getLevel2RaptorOpen());
                break;
            }
            case "lvl_2_2":{
                gb = new GrabImage(pv.getLevel2BigRaptorOpenLeft());
                break;
            }
            case "lvl_2_3":{
                gb = new GrabImage(pv.getMissileLauncher("2"));
                break;
            }
            case "lvl_2_4":{
                gb = new GrabImage(pv.getMissileLauncherLeft("2"));
                break;
            }
        }
        enemy = gb.grabImage();
        enemy_type = s;
        alive = true;
    }
    
    public Enemy(double x, double y, CAP3027TermProject pv, String s, int num, double missile_delay){
        this.x = x;
        this.y = y;
        this.pv = pv;
        enemy_number = num;
        healthbar = new EnemyHealthBar(this);
        this.missile_delay = missile_delay;
        switch (s){
            case "lvl_1_1":{
                gb = new GrabImage(pv.getLevel1Machine());
                break;
            }
            case "lvl_2_1":{
                gb = new GrabImage(pv.getLevel2RaptorOpen());
                break;
            }
            case "lvl_2_2":{
                gb = new GrabImage(pv.getLevel2BigRaptorOpenLeft());
                break;
            }
            case "lvl_2_3":{
                gb = new GrabImage(pv.getMissileLauncher("2"));
                break;
            }
            case "lvl_2_4":{
                gb = new GrabImage(pv.getMissileLauncherLeft("2"));
                break;
            }
        }
        enemy = gb.grabImage();
        enemy_type = s;
        alive = true;
    }
    
    public void tick(){
        ++ticker;
        ++open_close;
        ++missile_delay;
        if(ticker > 32 && alive){
            gunside = rand.nextInt(2);
            if("lvl_1_1".equals(enemy_type)){
                pv.laser.playOnce();
            if(r_setter > 1){
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+1, y+5, pv, this, "bullet"));
                ticker = 0;
                ++l_setter;
                r_setter = 0;
            }
            else if(l_setter > 1){
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+12, y+5, pv, this, "bullet"));
                ticker = 0;
                ++r_setter;
                l_setter = 0;
            }
            
            else if(gunside == 1){
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+1, y+5, pv, this, "bullet"));
                ticker = 0;
                ++l_setter;
                r_setter = 0;
            }
            else
            {
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+12, y+5, pv, this, "bullet"));
                ticker = 0;
                ++r_setter;
                l_setter = 0;
            }
            }
            
            
        }
        if(healthbar.getHealth() <= 0){
            alive = false;
        }
        
        if("lvl_2_2".equals(enemy_type) && direction_change == true){
        switch (direction){
            case "left":{
                gb = new GrabImage(pv.getLevel2BigRaptorCloseLeft());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "right":{
                gb = new GrabImage(pv.getLevel2BigRaptorCloseRight());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "up":{
                gb = new GrabImage(pv.getLevel2BigRaptorCloseUp());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "down":{
                gb = new GrabImage(pv.getLevel2BigRaptorClose());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
        }
        }
        if("lvl_2_1".equals(enemy_type) && direction_change == true){
        switch (direction){
            case "left":{
                gb = new GrabImage(pv.getLevel2RaptorCloseLeft());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "right":{
                gb = new GrabImage(pv.getLevel2RaptorCloseRight());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "up":{
                gb = new GrabImage(pv.getLevel2RaptorCloseUp());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
            case "down":{
                gb = new GrabImage(pv.getLevel2RaptorClose());
                enemy = gb.grabImage();
                direction_change = false;
                break;
            }
        }
        }
        
        if (open_close >= 20 && alive){
            if ("lvl_2_2".equals(enemy_type)){
            switch(direction){
                case "left":{
                    if(enemy == pv.getLevel2BigRaptorOpenLeft()){
                            gb = new GrabImage(pv.getLevel2BigRaptorCloseLeft());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2BigRaptorCloseLeft()){
                    gb = new GrabImage(pv.getLevel2BigRaptorOpenLeft());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "right":{
                    if(enemy == pv.getLevel2BigRaptorOpenRight()){
                            gb = new GrabImage(pv.getLevel2BigRaptorCloseRight());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2BigRaptorCloseRight()){
                    gb = new GrabImage(pv.getLevel2BigRaptorOpenRight());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "down":{
                    if(enemy == pv.getLevel2BigRaptorOpen()){
                            gb = new GrabImage(pv.getLevel2BigRaptorClose());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2BigRaptorClose()){
                    gb = new GrabImage(pv.getLevel2BigRaptorOpen());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "up":{
                    if(enemy == pv.getLevel2BigRaptorOpenUp()){
                            gb = new GrabImage(pv.getLevel2BigRaptorCloseUp());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2BigRaptorCloseUp()){
                    gb = new GrabImage(pv.getLevel2BigRaptorOpenUp());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
            }
            
            
            }
            
            if ("lvl_2_1".equals(enemy_type)){
            switch(direction){
                case "left":{
                    if(enemy == pv.getLevel2RaptorOpenLeft()){
                            gb = new GrabImage(pv.getLevel2RaptorCloseLeft());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2RaptorCloseLeft()){
                    gb = new GrabImage(pv.getLevel2RaptorOpenLeft());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "right":{
                    if(enemy == pv.getLevel2RaptorOpenRight()){
                            gb = new GrabImage(pv.getLevel2RaptorCloseRight());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2RaptorCloseRight()){
                    gb = new GrabImage(pv.getLevel2RaptorOpenRight());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "down":{
                    if(enemy == pv.getLevel2RaptorOpen()){
                            gb = new GrabImage(pv.getLevel2RaptorClose());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2RaptorClose()){
                    gb = new GrabImage(pv.getLevel2RaptorOpen());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
                case "up":{
                    if(enemy == pv.getLevel2RaptorOpenUp()){
                            gb = new GrabImage(pv.getLevel2RaptorCloseUp());
                            enemy = gb.grabImage();
                            open_close = 0;
                        if(pv.getPlayer().enemyHit(x, y)){
                            pv.getPlayer().getHealthBar().meleeHit25();
                        }
                    }
                else if(enemy == pv.getLevel2RaptorCloseUp()){
                    gb = new GrabImage(pv.getLevel2RaptorOpenUp());
                    enemy = gb.grabImage();
                    open_close = 0;
                }
                    break;
                }
            }
            
            
            }
            
//            if(enemy == pv.getLevel2RaptorOpen()){
//                gb = new GrabImage(pv.getLevel2RaptorClose());
//                enemy = gb.grabImage();
//                open_close = 0;
//                if(pv.getPlayer().enemyHit(x, y)){
//                    pv.getPlayer().getHealthBar().meleeHit15();
//                }
//            }
//            else if(enemy == pv.getLevel2RaptorClose()){
//                gb = new GrabImage(pv.getLevel2RaptorOpen());
//                enemy = gb.grabImage();
//                open_close = 0;
//            }
//            else if(enemy == pv.getLevel2BigRaptorOpen()){
//                gb = new GrabImage(pv.getLevel2BigRaptorClose());
//                enemy = gb.grabImage();
//                open_close = 0;
//                if(pv.getPlayer().enemyHit(x, y)){
//                    pv.getPlayer().getHealthBar().meleeHit25();
//                }
//            }
//            else if(enemy == pv.getLevel2BigRaptorClose()){
//                gb = new GrabImage(pv.getLevel2BigRaptorOpen());
//                enemy = gb.grabImage();
//                open_close = 0;
//            }
//            else if(enemy == pv.getLevel2BigRaptorOpenLeft()){
//                gb = new GrabImage(pv.getLevel2BigRaptorCloseLeft());
//                enemy = gb.grabImage();
//                open_close = 0;
//                if(pv.getPlayer().enemyHit(x, y)){
//                    pv.getPlayer().getHealthBar().meleeHit25();
//                }
//            }
//            else if(enemy == pv.getLevel2BigRaptorCloseLeft()){
//                gb = new GrabImage(pv.getLevel2BigRaptorOpenLeft());
//                enemy = gb.grabImage();
//                open_close = 0;
//            }
        }
        if(!alive){
            if ("lvl_1_1".equals(enemy_type)){
                gb = new GrabImage(pv.getLevel1MachineDead());
                enemy = gb.grabImage();                
            }
            else switch (enemy_number){
                case 1:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(1);
                    break;
                }
                case 2:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(2);
                    break;
                }
                case 3:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(3);
                    break;
                }
                case 4:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(4);
                    break;
                }
                case 5:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(5);
                    break;
                }
                case 6:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(6);
                    break;
                }
                case 7:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(7);
                    break;
                }
                case 8:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(8);
                    break;
                }
                case 9:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(9);
                    break;
                }
                case 10:{
                    gb = new GrabImage(null);
                    enemy = gb.grabImage();
                    pv.killEnemy(10);
                    break;
                }
            }
//            else if ("lvl_2_1".equals(enemy_type)){
//                gb = new GrabImage(null);
//                enemy = gb.grabImage();
//                pv.killEnemy(1);
//            }
//            else if ("lvl_2_2".equals(enemy_type)){
//                gb = new GrabImage(null);
//                enemy = gb.grabImage();
//                pv.killEnemy(1);
//            }
            
        }
        if("lvl_2_1".equals(enemy_type) && alive){
           
            if(isNotNear(pv.getPlayer().getX(), pv.getPlayer().getY())){
               
               if(this.x - pv.getPlayer().getX() > 2){
                velX = -1.5;
                movingX_Y = true;
                if(!"left".equals(direction)){
                    direction_change = true;
                }
                direction = "left";
                }
               else if(this.x - pv.getPlayer().getX() < -2){
                velX = 1.5;
                movingX_Y = true;
                if(!"right".equals(direction)){
                    direction_change = true;
                }
                direction = "right";
                }
               else if(this.y - pv.getPlayer().getY() > 2){
                velY = -1.5;
                movingX_Y = false;
                if(!"up".equals(direction)){
                    direction_change = true;
                }
                direction = "up";
                }
               else if(this.y - pv.getPlayer().getY() < -2){
                velY = 1.5;
                if(!"down".equals(direction)){
                    direction_change = true;
                }
                direction = "down";
                movingX_Y = false;
                } 
               
                if (pv.getTerrain().isWalkable(x+velX+10, y+velY)){
                xCheck = true;
            }
               else{
                xCheck = false;
                }
               
               if (pv.getTerrain().isWalkable(x+velX, y+velY+10)){
                yCheck = true;
            }
               else{
                yCheck = false;
               }
           
            if (pv.getTerrain().isWalkable(x+velX, y+velY)&&xCheck&&movingX_Y){
                x+=velX;
            }
            if (pv.getTerrain().isWalkable(x+velX, y+velY)&&yCheck&&!movingX_Y){
                y+=velY;
            }
            
            } 
        }
        if("lvl_2_2".equals(enemy_type) && alive){
           
//            switch(direction){
//                case "left":{
//                    gb = new GrabImage(pv.getLevel2BigRaptorCloseLeft());
//                    enemy = gb.grabImage();
//                    break;
//                }
//                case "right":{
//                    gb = new GrabImage(pv.getLevel2BigRaptorCloseRight());
//                    enemy = gb.grabImage();
//                    break;
//                }
//                case "down":{
//                    gb = new GrabImage(pv.getLevel2BigRaptorClose());
//                    enemy = gb.grabImage();
//                    break;
//                }
//                case "up":{
//                    gb = new GrabImage(pv.getLevel2BigRaptorCloseUp());
//                    enemy = gb.grabImage();
//                    break;
//                }
//            }
            
            if(isNotNear(pv.getPlayer().getX(), pv.getPlayer().getY())){
               
               if(this.x - pv.getPlayer().getX() > 2){
                velX = -1;
                if(!"left".equals(direction)){
                    direction_change = true;
                }
                direction = "left";
                movingX_Y = true;
                }
               else if(this.x - pv.getPlayer().getX() < -2){
                velX = 1;
                if(!"right".equals(direction)){
                    direction_change = true;
                }
                direction = "right";
                movingX_Y = true;
                }
               else if(this.y - pv.getPlayer().getY() > 2){
                velY = -1;
                if(!"up".equals(direction)){
                    direction_change = true;
                }
                direction = "up";
                movingX_Y = false;
                }
               
               else if(this.y - pv.getPlayer().getY() < -2){
                velY = 1;
                if(!"down".equals(direction)){
                    direction_change = true;
                }
                direction = "down";
                movingX_Y = false;
                } 
               
                if (pv.getTerrain().isWalkable(x+velX+5, y+velY)){
                xCheck = true;
            }
               else{
                xCheck = false;
                }
               
               if (pv.getTerrain().isWalkable(x+velX, y+velY+5)){
                yCheck = true;
            }
               else{
                yCheck = false;
               }
           
            if (pv.getTerrain().isWalkable(x+velX, y+velY)&&xCheck&&movingX_Y){
                x+=velX;
            }
            if (pv.getTerrain().isWalkable(x+velX, y+velY)&&yCheck&&!movingX_Y){
                y+=velY;
            }
            
            }            
        }
        
        if ("lvl_2_3".equals(enemy_type) && alive && missile_delay > 45){
            missile_direction = rand.nextInt(3);
            if(missile_direction == 0){
                gb = new GrabImage(pv.getMissileLauncher("1"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x-45, y+80, 2));
                missile_delay = 0;
            }
            else if (missile_direction == 1){
                gb = new GrabImage(pv.getMissileLauncher("2"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x, y+80, 2));
                missile_delay = 0;
            }
            else if (missile_direction == 2){
                gb = new GrabImage(pv.getMissileLauncher("3"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x+45, y+80, 2));
                missile_delay = 0;
            }

        }
        if ("lvl_2_4".equals(enemy_type) && alive && missile_delay > 45){
            missile_direction = rand.nextInt(3);
            if(missile_direction == 0){
                gb = new GrabImage(pv.getMissileLauncherLeft("3"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x-5, y, pv, this, "missile", x-60, y+45, 2));
                missile_delay = 0;
            }
            else if (missile_direction == 1){
                gb = new GrabImage(pv.getMissileLauncherLeft("2"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x-5, y, pv, this, "missile", x-60, y, 2));
                missile_delay = 0;
            }
            else if (missile_direction == 2){
                gb = new GrabImage(pv.getMissileLauncherLeft("1"));
                enemy = gb.grabImage();
                pv.getBulletController().addEnemyBullet(new EnemyBullet(x-5, y, pv, this, "missile", x-60, y-45, 2));
                missile_delay = 0;
            }

        }
        if (explosion1 != null){
            explosion1.tick();
        }
        if (explosion2 != null){
            explosion2.tick();
        }
        if (explosion3 != null){
            explosion3.tick();
        }

    }
    
    public void render(Graphics g){
        g.drawImage(enemy, (int)x, (int)y, null);
        healthbar.render(g);
        if (explosion1 != null){
            explosion1.render(g);
        }
        if (explosion2 != null){
            explosion2.render(g);
        }
        if (explosion3 != null){
            explosion3.render(g);
        }
        
    }
    
    public boolean isAlive(){
        return alive;
    }
    
    public void disable(){
         alive = false;
         healthbar.bulletHit100();
    }
    
    public boolean bulletHit(double x, double y, String direction){
        
        Rectangle p = new Rectangle((int)this.x, (int)this.y, 20, 20);
        Rectangle b = new Rectangle((int)x, (int)y, 4, 7);
        if("h".equals(direction)){
        b = new Rectangle((int)x, (int)y, 7, 4);
        }
        return p.intersects(b);
    }

    public boolean isNear(double x, double y){
        
        return (Math.abs(this.x - x) < 25)&&(Math.abs(this.y - y) < 25);
    }
    public boolean isNotNear(double x, double y){
        
        return (Math.abs(this.x - x) > 5)||(Math.abs(this.y - y) > 5);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public EnemyHealthBar getHealthBar(){
        return healthbar;
    }
    public String getEnemyType(){
        return enemy_type;
    }
    
    public int getEnemyNumber(){
        return enemy_number;
    }
    public void activateExplosion(Explosion ex){
        if (explosion1 == null){
            explosion1 = ex;
        }
        else if (explosion2 == null){
            explosion2 = ex;
        }
        else if (explosion3 == null){
            explosion3 = ex;
        }
    }
    public void endExplosion(Explosion ex){
        if (explosion1 == ex){
            explosion1 = null;
        }
        else if (explosion2 == ex){
            explosion2 = null;
        }
        else if (explosion3 == ex){
            explosion3 = null;
        }
    }
}
//            if (tester == 0){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x, y+42, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 1){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x+125, y+74, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 2){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x-25, y+66, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 3){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x+90, y, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 4){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x-30, y, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 5){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x+65, y-128, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 6){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x-45, y-80, 2));
//                missile_delay = 0;
//                ++tester;
//            }
//            else if (tester == 7){
//                gb = new GrabImage(pv.getMissileLauncher("3"));
//                enemy = gb.grabImage();
//                pv.getBulletController().addEnemyBullet(new EnemyBullet(x+5, y+5, pv, this, "missile", x, y-45, 2));
//                missile_delay = 0;
//                tester++;
//            }