/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author KPC
 */
public class Tips {
    
    //public Rectangle tip;
    private String s;
    int x;
    int y;
    public void tipSet(String tip, int x, int y){
        s = tip;
        this.x = x;
        this.y = y;
    }
    
    public void render(Graphics g){
       
       Graphics2D g2d = (Graphics2D) g;
       //tip = new Rectangle(x, y, 60, 120);
       g2d.setColor(new Color(0, 32, 56));
       g2d.fillRect(x, y, 170, 60);
       g2d.setColor(Color.white);
       g2d.drawRect(x, y, 170, 60);
       Font fnt0 = new Font("arial", Font.BOLD, 12);
       g.setFont(fnt0);
       g.setColor(Color.white);
       if(s.length() > 24){
            g.drawString(s.substring(0, 24), x+9, y+15);
            g.drawString(s.substring(24), x+15, y+30);
       }
       else{
           g.drawString(s, x+15, y+15);
       }
       fnt0 = new Font("arial", Font.ITALIC, 10);
       g.setFont(fnt0);
       g.drawString("Hit \"Enter\" to continue.", x+15, y + 15 + 30);

//       g.drawString("Play", playButton.x +21, playButton.y +28);
//       g.drawString("Options", optionsButton.x +2, optionsButton.y +28);
//       g2d.draw(optionsButton);
//       g.drawString("Quit", quitButton.x +21, quitButton.y +28);
//       g2d.draw(quitButton);
    }
}
