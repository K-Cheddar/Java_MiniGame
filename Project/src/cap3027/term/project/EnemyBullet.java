/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author KPC
 */
public class EnemyBullet {
    
    private double x;
    private double y;
    
    private double destX;
    private double destY;
    
    private double speed;
    
    private double velX, velY;
    private double direction;
    private String type;
    
    private Target target;
//    private Explosion explosion;
    
    private boolean hit = false;
    
    private BufferedImage image;
    CAP3027TermProject pv;
    Enemy e;
    
    
    public EnemyBullet(double x, double y, CAP3027TermProject pv, Enemy e, String s){
        this.x = x;
        this.y = y;
        this.pv = pv;
        this.e = e;
        type = s;
        switch (s){
            
            case "bullet":{
                image = pv.getMachineBullet();
                break;
            }
            case "missile":{
                image = pv.getMachineMissile("default");
                break;
            }    
        }
//        if (p.getDirection() == "up"){
//           image = pv.getBulletFacingUp();
//           direction = "up";
//        }
//        else if (p.getDirection() == "down"){
//           image = pv.getBulletFacingDown();
//           direction = "down";
//        }
//        else if (p.getDirection() == "left"){
//           image = pv.getBulletFacingLeft();
//           direction = "left";
//        }
//        else if (p.getDirection() == "right"){
//           image = pv.getBulletFacingRight();
//           direction = "right";
//        }
    }
    public EnemyBullet(double x, double y, CAP3027TermProject pv, Enemy e, String s, double destX, double destY, double speed){
        this.x = x;
        this.y = y;
        this.pv = pv;
        this.e = e;
        type = s;
        this.destX = destX;
        this.destY = destY;
        switch (s){
            
            case "bullet":{
                image = pv.getMachineBullet();
                break;
            }
            case "missile":{
                image = pv.getMachineMissile("default");
                target = new Target(destX, destY, 3);
                velX = destX - x;
                velY = destY - y;
                direction = Math.atan2(velY, velX);
                this.speed = speed;
                break;
            }    
        }
//        if (p.getDirection() == "up"){
//           image = pv.getBulletFacingUp();
//           direction = "up";
//        }
//        else if (p.getDirection() == "down"){
//           image = pv.getBulletFacingDown();
//           direction = "down";
//        }
//        else if (p.getDirection() == "left"){
//           image = pv.getBulletFacingLeft();
//           direction = "left";
//        }
//        else if (p.getDirection() == "right"){
//           image = pv.getBulletFacingRight();
//           direction = "right";
//        }
    }
    
    public void tick(Player p){
//        switch (direction){
//            case "up":{
//                y -= 2.5;
//                break;
//            }
//            case "down":{
//                y += 2.5;
//                break;
//            }
//            case "left":{
//                x -= 2.5;
//                break;
//            }
//            case "right":{
//                x += 2.5;
//                break;
//            }
//            
//        }
        switch (type){
            case "bullet":{
                y+=3;
                if(pv.getPlayer().bulletHit(x, y) && !hit){
                    hit = !hit;
                    pv.getBulletController().removeEnemyBullet(this);
                    pv.getPlayer().getHealthBar().bulletHit();
                }   
                break;
            }
            case "missile":{
//                if(x - destX > 1){
//                    velX= -xSpeed;
//                }
//                if(x - destX < -1){
//                    velX= xSpeed;
//                }
//               if(y - destY > 1){
//                    velY= -ySpeed;
//                }
//               
//               if(y - destY < -1){
//                    velY= ySpeed;
//                } 
//               
//               if(velY > 0){
//                   if (velX > 0){
//                       image = pv.getMachineMissile("RU");
//                   }
//                   else if (velX < 0){
//                       image = pv.getMachineMissile("LU");
//                   }
//                   else {
//                       image = pv.getMachineMissile("up");
//                   }
//               }
//               else if(velY < 0){
//                   if (velX > 0){
//                       image = pv.getMachineMissile("RD");
//                   }
//                   else if (velX < 0){
//                       image = pv.getMachineMissile("LD");
//                   }
//                   else {
//                       image = pv.getMachineMissile("down");
//                   }
//                     
//               }
//               else if (velX > 0){
//                   image = pv.getMachineMissile("right");
//               }
//               else if (velX < 0){
//                   image = pv.getMachineMissile("left");
//               }
               
               if (direction > -2 && direction < -1.5){
                   image = pv.getMachineMissile("up");
               }
               else if (direction > -1.5 && direction < -.5){
                   image = pv.getMachineMissile("RU");
               }
               if (direction > -.5 && direction < 0){
                   image = pv.getMachineMissile("right");
               }
               else if (direction > 0 && direction < 1.5){
                   image = pv.getMachineMissile("RD");
               }
               if (direction > 1.4 && direction < 1.8){
                   image = pv.getMachineMissile("down"); 
               }
               else if (direction > 1.8 && direction < 2.6){
                   image = pv.getMachineMissile("LD");
               }
               if ((direction > -3.5 && direction < -2.5) || direction > 3 && direction < 3.5){
                   image = pv.getMachineMissile("left");
               }
               else if (direction > -2.5 && direction < -2){
                   image = pv.getMachineMissile("LU");
               }
                
               if((Math.abs(x - destX) < 2)&&(Math.abs(y - destY) < 2)){
                   Explosion explosion = new Explosion (x, y, pv, e);
                   target = null;
                   pv.getBulletController().removeEnemyBullet(this);
                   if (pv.getPlayer().missileHit(x, y)){
                        pv.getPlayer().getHealthBar().missileHit();
                   }
               }
               
               x = x + (speed * Math.cos(direction));
               y = y + (speed * Math.sin(direction));     
               
               
//               if (explosion != null){
//                   explosion.tick();
//                   if (explosion.getTicker() > 360){
//                       explosion = null;
//                   }
//               }
//               System.out.println(direction);
                break;
            }
        }
    }
    
    public void render(Graphics g){
        g.drawImage(image, (int)x, (int)y, null);
        if (target != null){
            target.render(g);
        }
//        if (explosion != null){
//            explosion.render(g);
//        }
    }
    
    public double getY(){
        return y;
    }
    public double getX(){
        return x;
    }
    public boolean isNear(double x, double y){
        
        if((Math.abs(this.x - x) < 25)&&(Math.abs(this.y - y) < 25)){
            return true;
        }
        
        return false;
    }
//    public void setDestination(double x, double y ){
//        destX = x;
//        destY = y;
//    }
}
