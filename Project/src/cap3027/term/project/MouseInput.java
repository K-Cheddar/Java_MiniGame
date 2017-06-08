/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author KPC
 */
public class MouseInput implements MouseListener {

    private int mx;
    private int my;
//    CAP3027TermProject pv;
//    public MouseInput(CAP3027TermProject pv){
//        this.pv = pv;
//    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {

        mx = e.getX();
        my = e.getY();
        
//            public Rectangle playButton = new Rectangle(215, 200, 80, 40);
//            public Rectangle optionsButton = new Rectangle(215, 275, 80, 40);
//            public Rectangle quitButton = new Rectangle(215, 350, 80, 40);
        
        if(CAP3027TermProject.State == CAP3027TermProject.STATE.MENU){
        
        if(mx >= 215 && mx <= 215 + 80 ){
            if(my >= 200 && my <= 200 + 40 ){
                //pressed play button
                CAP3027TermProject.State = CAP3027TermProject.STATE.GAME;
            }
        }
//        if(mx >= 215 && mx <= 215 + 80 ){
//            if(my >= 275 && my <= 275 + 40 ){
//                //pressed Sound button
//                CAP3027TermProject.State = CAP3027TermProject.STATE.GAME;
//            }
//        }
        if(mx >= 215 && mx <= 215 + 80 ){
            if(my >= 275 && my <= 275 + 40 ){
                //pressed quit button
                System.exit(1);
            }
        }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
