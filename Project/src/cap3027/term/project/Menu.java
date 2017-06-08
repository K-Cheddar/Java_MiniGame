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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author KPC
 */
public class Menu {
    
    public Rectangle playButton = new Rectangle(215, 200, 80, 40);
//    public Rectangle optionsButton = new Rectangle(215, 275, 80, 40);
    public Rectangle quitButton = new Rectangle(215, 275, 80, 40);
    
    public void render(Graphics g){
       
       Graphics2D g2d = (Graphics2D) g;
       
       Font fnt0 = new Font("arial", Font.BOLD, 30);
       g.setFont(fnt0);
       g.setColor(Color.white);
       g.drawString("Project Vorpal", 150, 150);
       
       Font fnt1 = new Font("arial", Font.BOLD, 20);
       g.setFont(fnt1);
       g.drawString("Play", playButton.x +21, playButton.y +28);
       g2d.draw(playButton);
//       g.drawString("Options", optionsButton.x +2, optionsButton.y +28);
//       g2d.draw(optionsButton);
       g.drawString("Quit", quitButton.x +21, quitButton.y +28);
       g2d.draw(quitButton);
    }
    
}
