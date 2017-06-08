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
public class Bullet {
    
    private double x;
    private double y;
    private String direction;
    
    private boolean hit = false;
    
    private BufferedImage image;
    CAP3027TermProject pv;
    
    public Bullet(double x, double y, CAP3027TermProject pv, Player p){
        this.x = x;
        this.y = y;
        this.pv = pv;
        if (p.getDirection() == "up"){
           image = pv.getBulletFacingUp();
           direction = "up";
        }
        else if (p.getDirection() == "down"){
           image = pv.getBulletFacingDown();
           direction = "down";
        }
        else if (p.getDirection() == "left"){
           image = pv.getBulletFacingLeft();
           direction = "left";
        }
        else if (p.getDirection() == "right"){
           image = pv.getBulletFacingRight();
           direction = "right";
        }
    }
    
    public void tick(Player p){
        switch (direction){
            case "up":{
                y -= 2.5;
                break;
            }
            case "down":{
                y += 2.5;
                break;
            }
            case "left":{
                x -= 2.5;
                break;
            }
            case "right":{
                x += 2.5;
                break;
            }
            
        }
            if(direction == "left" || direction == "right"){
            if(!pv.enemyIsDead(1)){
            if(pv.getEnemy(1).bulletHit(x, y, "h") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(1).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(1).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(1).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(1).getHealthBar().bulletHit10();
            }

//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            }
            else if(!pv.enemyIsDead(1)){
            if(pv.getEnemy(1).bulletHit(x, y, "v") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(1).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(1).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(1).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(1).getHealthBar().bulletHit10();
            }
//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            if(direction == "left" || direction == "right"){
            if(!pv.enemyIsDead(2)){
            if(pv.getEnemy(2).bulletHit(x, y, "h") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(2).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(2).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(2).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(2).getHealthBar().bulletHit10();
            }

//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            }
            else if(!pv.enemyIsDead(2)){
            if(pv.getEnemy(2).bulletHit(x, y, "v") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(2).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(2).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(2).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(2).getHealthBar().bulletHit10();
            }
//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            if(direction == "left" || direction == "right"){
            if(!pv.enemyIsDead(3)){
            if(pv.getEnemy(3).bulletHit(x, y, "h") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(3).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(3).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(3).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(3).getHealthBar().bulletHit10();
            }

//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            }
            else if(!pv.enemyIsDead(3)){
            if(pv.getEnemy(3).bulletHit(x, y, "v") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(3).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(3).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(3).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(3).getHealthBar().bulletHit10();
            }
//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }if(direction == "left" || direction == "right"){
            if(!pv.enemyIsDead(4)){
            if(pv.getEnemy(4).bulletHit(x, y, "h") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(4).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(4).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(4).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(4).getHealthBar().bulletHit10();
            }

//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            }
            else if(!pv.enemyIsDead(4)){
            if(pv.getEnemy(4).bulletHit(x, y, "v") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(4).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(4).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(4).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(4).getHealthBar().bulletHit10();
            }
//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }if(direction == "left" || direction == "right"){
            if(!pv.enemyIsDead(5)){
            if(pv.getEnemy(5).bulletHit(x, y, "h") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(5).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(5).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(5).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(5).getHealthBar().bulletHit10();
            }

//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
            }
            else if(!pv.enemyIsDead(5)){
            if(pv.getEnemy(5).bulletHit(x, y, "v") && !hit){
            hit = !hit;
            pv.getBulletController().removeBullet(this);
            if(pv.getEnemy(5).getEnemyType() == "lvl_2_1"){
                pv.getEnemy(5).getHealthBar().bulletHit25(); 
            }
            if(pv.getEnemy(5).getEnemyType() == "lvl_2_2"){
                pv.getEnemy(5).getHealthBar().bulletHit10();
            }
//            pv.getPlayer().getHealthBar().bulletHit();
            }
            }
 
    }
    
    public void render(Graphics g){
        g.drawImage(image, (int)x, (int)y, null);
    }
    
    public double getY(){
        return y;
    }
    public double getX(){
        return x;
    }
    public boolean isNear(double x, double y){
        
        if((Math.abs(this.x - x) < 10)&&(Math.abs(this.y - y) < 10)){
            return true;
        }
        
        return false;
    }
}