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
public class Terrain {
   
   BufferedImage image;
   int  rgb, rgb1, rgb2, rgb3, rgb4;
    public Terrain(BufferedImage image){
        this.image = image;
    }
    
    public boolean advance(double x1, double y1){
        rgb = image.getRGB((int)x1, (int)y1);
        if (rgb == -6676737){
            return true;
        }
        return false;
    }
    
    public boolean isWalkable(double x1, double y1){
        if (x1 > 500 || x1 < 0 || y1 > 500 || y1 < 0){
            return false;
        }

        if(!(y1+5 >= 500)){
            rgb2 = image.getRGB((int)x1, (int)y1+5);
            rgb4 = image.getRGB((int)x1+5, (int)y1+5);
        }
        if(!(x1+5 >= 500)){
            rgb3 = image.getRGB((int)x1+5, (int)y1);
        }
        rgb1 = image.getRGB((int)x1, (int)y1);

        //System.out.println(rgb);
        /*
        Colors:
        Peach (path): -16505
        Blue (sky) : -3544833
        Purple (special) : -6676737
        */
        if (rgb1 == -16505 && rgb2 == -16505 && rgb3 == -16505 && rgb4 == -16505){
            return true;
        }
        else if (rgb1 == -6676737 || rgb2 == -6676737 || rgb3 == -6676737|| rgb4 == -6676737){
            return true;
        }
        else if (rgb1 == -3544833 || rgb2 == -3544833 || rgb3 == -3544833 || rgb4 == -3544833){
            return false;
        }
        
        return true;
        

    }
   
}
