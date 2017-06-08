/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author KPC
 */
public class BulletController {
    private int i;
    private LinkedList<Bullet> b = new LinkedList<Bullet>();
    private LinkedList<EnemyBullet> e = new LinkedList<EnemyBullet>();
    
    Bullet bull;
    EnemyBullet enbull;
    CAP3027TermProject pv;
    
    public BulletController(CAP3027TermProject pv){
        this.pv = pv;
//        addBullet(new Bullet(100, 300, pv, pv.getPlayer()));
//        for (int x = 0; x < 500; x += 64){
//            addEnemy(new Enemy(x, 0, pv));
//        }
}
    
    public void tick(){
        for (i = 0; i < b.size(); ++i){
            bull = b.get(i);
            if (bull.getY() < 0){
                removeBullet(bull);
            }
            if (bull.getY() > 510){
                removeBullet(bull);
            }
            if (bull.getX() < 0){
                removeBullet(bull);
            }
            if (bull.getX() > 510){
                removeBullet(bull);
            }
            bull.tick(pv.getPlayer());
        }
        for (i = 0; i < e.size(); ++i){
            enbull = e.get(i);
            if (enbull.getY() < 0){
                removeEnemyBullet(enbull);
            }
            if (enbull.getY() > 510){
                removeEnemyBullet(enbull);
            }
            if (enbull.getX() < 0){
                removeEnemyBullet(enbull);
            }
            if (enbull.getX() > 510){
                removeEnemyBullet(enbull);
            }
            enbull.tick(pv.getPlayer());
        }
    }
    
    public void render(Graphics g){
        for (i = 0; i < b.size(); ++i){
            bull = b.get(i);
            bull.render(g);
        }
        for (i = 0; i < e.size(); ++i){
            enbull = e.get(i);
            enbull.render(g);
        }
    }
    
    public void addBullet(Bullet block){
        b.add(block);
    }
    public void removeBullet(Bullet block){
        b.remove(block);
    }
    public void addEnemyBullet(EnemyBullet en){
        e.add(en);
    }
    public void removeEnemyBullet(EnemyBullet en){
        e.remove(en);
    }
}
