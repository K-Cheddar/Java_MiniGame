/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cap3027.term.project;

/**
 *
 * @author Kevin
 */
public class Ticker {
    
    private double ticker = 0;
    private double seconds, second_frag;
    private double secLimit;
    private boolean go = false;
    CAP3027TermProject pv;
    
    public Ticker(CAP3027TermProject pv, double s){
        this.pv = pv;
        secLimit = s;
    }
    
    public void tick(){
        ++ticker;
        if(ticker%60 == 0){
            seconds = ticker/60;
            go = true;
        }
        if(ticker%5 == 0){
            second_frag = ticker/5;
            go = true;
        }
//        if(go){
//            System.out.print(seconds + ", ");
//            go = false;
//        }
        if (seconds >= secLimit){
            pv.stopTicker();
        }

    }
    public double getSeconds(){
        return seconds;
    }
    public double getSecondFrag(){
        return second_frag;
    }
//    
//    public void setSeconds(double s){
//        secLimit = s;
//    }
}
