/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author KPC
 */
public class KeyInput extends KeyAdapter {
    
    CAP3027TermProject pv;
    
    public KeyInput(CAP3027TermProject pv){
        this.pv = pv;
    }
    
    public void keyPressed(KeyEvent e){
        pv.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        pv.keyReleased(e);
    }
    
}
