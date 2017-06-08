package cap3027.term.project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Image scene1;
    private Menu menu;
    public enum State{
        Menu,
        Game
    };
    public State state = State.Menu;

    public Board() {
        
        BufferedImage image = new BufferedImage (500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) image.createGraphics();
        
        menu = new Menu();
        //loadImage();
        if (state == State.Menu){
            loadImage();
            menu.render(g);
        }
        else if (state == State.Game){
            //loadImage();
            initBoard();
        }

    }
    
    private void initBoard() {
        
        //loadImage();   
    }
    
    private void loadImage() {
        
        ImageIcon s1 = new ImageIcon("images/scene_1.png");
        scene1 = s1.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(scene1, 0, 0, null);
    }
}