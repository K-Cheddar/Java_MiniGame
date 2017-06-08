/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cap3027.term.project;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 *
 * @author KPC
 */
public class Sound {
    
    public static final Sound sound1 = new Sound ("");
    private AudioClip clip;
    
    public Sound(String filepath){
        try{
            clip = Applet.newAudioClip(Sound.class.getResource(filepath));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void play(){
//        try{
//            new Thread(){
//                public void run(){
//                    clip.play();
//                }
//            }.start();
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
        clip.loop();
    }
    public void stop(){
        clip.stop();
    }
    public void playOnce(){
                try{
            new Thread(){
                public void run(){
                    clip.play();
                }
            }.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
