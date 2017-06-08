/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.awt.image.BufferedImage;

/**
 *
 * @author KPC
 */
public class GrabImage {
    
    private BufferedImage image;
    
    public GrabImage(BufferedImage image){
        this.image = image;
    }
    
    public BufferedImage grabImage(){
        BufferedImage img = image;
        return img;
    }
}
