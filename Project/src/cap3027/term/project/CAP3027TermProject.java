
package cap3027.term.project;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CAP3027TermProject extends Canvas implements Runnable {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public final String TITLE = "Project Vorpal";
    
    private boolean running = false;
    private boolean right_key_down = false;
    private boolean left_key_down = false;
    private boolean up_key_down = false;
    private boolean down_key_down = false;
    private boolean hold_key_down = false;
    private boolean gunside = true;
    private boolean is_shooting = false;
    private boolean paused = true;
    private boolean show_tip = false;
    private boolean finished = false;
    private boolean objectives_done = false;
    int player_health = 100;
    
    private Thread thread;
    
    private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
    private BufferedImage grabImage = null;

    private BufferedImage machine_bullet = null;
    private BufferedImage vorpal_up = null;
    private BufferedImage vorpal_down = null;
    private BufferedImage vorpal_left = null;
    private BufferedImage vorpal_right = null;
    private BufferedImage vorpal_bullet_up = null;
    private BufferedImage vorpal_bullet_down = null;
    private BufferedImage vorpal_bullet_left = null;
    private BufferedImage vorpal_bullet_right = null;
    private BufferedImage background = null;
    private BufferedImage paused_background = null;
    
    private BufferedImage level_2_BigRaptor_open = null;
    private BufferedImage level_2_BigRaptor_open_right = null;
    private BufferedImage level_2_BigRaptor_open_up = null;
    private BufferedImage level_2_BigRaptor_close_right = null;
    private BufferedImage level_2_BigRaptor_close_up = null;
    private BufferedImage level_2_BigRaptor_close = null;
    private BufferedImage level_2_BigRaptor_open_left = null;
    private BufferedImage level_2_BigRaptor_close_left = null;
    private BufferedImage level_2_raptor_open = null;
    private BufferedImage level_2_raptor_close = null;
    private BufferedImage level_2_raptor_open_right = null;
    private BufferedImage level_2_raptor_close_right = null;
    private BufferedImage level_2_raptor_open_left = null;
    private BufferedImage level_2_raptor_close_left = null;
    private BufferedImage level_2_raptor_open_up = null;
    private BufferedImage level_2_raptor_close_up = null;
    private BufferedImage level_1_machine = null;
    private BufferedImage level_1_machine_dead = null;
    private BufferedImage level_2_missile_launcher_1 = null;
    private BufferedImage level_2_missile_launcher_2 = null;
    private BufferedImage level_2_missile_launcher_3 = null;
    private BufferedImage level_2_missile_launcher_1_left = null;
    private BufferedImage level_2_missile_launcher_2_left = null;
    private BufferedImage level_2_missile_launcher_3_left = null;
    private BufferedImage level_2_machine_missile = null;
    private BufferedImage level_2_machine_missile_up = null;
    private BufferedImage level_2_machine_missile_left = null;
    private BufferedImage level_2_machine_missile_right = null;
    private BufferedImage level_2_machine_missile_RU = null;
    private BufferedImage level_2_machine_missile_RD = null;
    private BufferedImage level_2_machine_missile_LU = null;
    private BufferedImage level_2_machine_missile_LD = null;
    private BufferedImage explosion_1 = null;
    private BufferedImage explosion_2 = null;
    private BufferedImage explosion_3 = null;
    private BufferedImage explosion_4 = null;
    private BufferedImage explosion_5 = null;
    private BufferedImage health_pickup_image = null;
    
//    private BufferedImage player;
    
    private RescaleOp op = new RescaleOp(.5f, 0, null);
    Sound music = new Sound("sounds/Take_a_Chance.wav"), gunshot, explosion, laser;
    private Ticker ticker;
    private Player p;
    private BulletController bc;
    private MouseInput mi;
    private Terrain ter = null;
    private Tips tip = new Tips();
    private int level = 1;
    private Menu menu;
    HealthPickup health_pickup;
    private Graphics g;
    private Enemy e1 = null, e2= null, e3= null, e4= null, e5= null, 
                  e6= null, e7= null, e8= null, e9= null, e10= null;
    
    public static enum STATE{
        MENU, GAME, PAUSED
        
    };
    
    public static STATE State = STATE.MENU;
    
    
    public void init() {
        requestFocus();
        e1 = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
        e6 = null;
        e7 = null;
        e8 = null;
        e9 = null;
        e10 = null;
        ImageLoader loader = new ImageLoader();
        try{
            vorpal_up = loader.loadImage("images/vorpal_up.png");
            vorpal_down = loader.loadImage("images/vorpal_down.png");
            vorpal_left = loader.loadImage("images/vorpal_left.png");
            vorpal_right = loader.loadImage("images/vorpal_right.png");
            vorpal_bullet_up = loader.loadImage("images/vorpal_bullet_up.png");
            vorpal_bullet_down = loader.loadImage("images/vorpal_bullet_down.png");
            vorpal_bullet_left = loader.loadImage("images/vorpal_bullet_left.png");
            vorpal_bullet_right = loader.loadImage("images/vorpal_bullet_right.png");
            level_1_machine = loader.loadImage("images/level_1_machine.png");
            level_1_machine_dead = loader.loadImage("images/level_1_machine_dead.png");
            machine_bullet = loader.loadImage("images/level_1_machine_bullet.png");
            level_2_raptor_open = loader.loadImage("images/level_2_raptor_open.png");
            level_2_raptor_open_up = loader.loadImage("images/level_2_raptor_open_up.png");
            level_2_raptor_open_right = loader.loadImage("images/level_2_raptor_open_right.png");
            level_2_raptor_open_left = loader.loadImage("images/level_2_raptor_open_left.png");
            level_2_raptor_close = loader.loadImage("images/level_2_raptor_close.png");
            level_2_raptor_close_up = loader.loadImage("images/level_2_raptor_close_up.png");
            level_2_raptor_close_right = loader.loadImage("images/level_2_raptor_close_right.png");
            level_2_raptor_close_left = loader.loadImage("images/level_2_raptor_close_left.png");
            level_2_BigRaptor_open = loader.loadImage("images/level_2_BigRaptor_open.png");
            level_2_BigRaptor_close = loader.loadImage("images/level_2_BigRaptor_close.png");
            level_2_BigRaptor_open_left = loader.loadImage("images/level_2_BigRaptor_open_left.png");
            level_2_BigRaptor_close_left = loader.loadImage("images/level_2_BigRaptor_close_left.png");
            level_2_BigRaptor_open_right = loader.loadImage("images/level_2_BigRaptor_open_right.png");
            level_2_BigRaptor_open_up = loader.loadImage("images/level_2_BigRaptor_open_up.png");
            level_2_BigRaptor_close_right = loader.loadImage("images/level_2_BigRaptor_close_right.png");
            level_2_BigRaptor_close_up = loader.loadImage("images/level_2_BigRaptor_close_up.png");
            level_2_missile_launcher_1 = loader.loadImage("images/level_2_missile_launcher_1.png");
            level_2_missile_launcher_2 = loader.loadImage("images/level_2_missile_launcher_2.png");
            level_2_missile_launcher_3 = loader.loadImage("images/level_2_missile_launcher_3.png");
            level_2_missile_launcher_1_left = loader.loadImage("images/level_2_missile_launcher_1_left.png");
            level_2_missile_launcher_2_left = loader.loadImage("images/level_2_missile_launcher_2_left.png");
            level_2_missile_launcher_3_left = loader.loadImage("images/level_2_missile_launcher_3_left.png");
            level_2_machine_missile = loader.loadImage("images/level_2_machine_missile.png");
            level_2_machine_missile_up = loader.loadImage("images/level_2_machine_missile_up.png");
            level_2_machine_missile_left = loader.loadImage("images/level_2_machine_missile_left.png");
            level_2_machine_missile_right = loader.loadImage("images/level_2_machine_missile_right.png");
            level_2_machine_missile_RD = loader.loadImage("images/level_2_machine_missile_RD.png");
            level_2_machine_missile_RU = loader.loadImage("images/level_2_machine_missile_RU.png");
            level_2_machine_missile_LD = loader.loadImage("images/level_2_machine_missile_LD.png");
            level_2_machine_missile_LU = loader.loadImage("images/level_2_machine_missile_LU.png");
            explosion_1 = loader.loadImage("images/explosion_1.png");
            explosion_2 = loader.loadImage("images/explosion_2.png");
            explosion_3 = loader.loadImage("images/explosion_3.png");
            explosion_4 = loader.loadImage("images/explosion_4.png");
            explosion_5 = loader.loadImage("images/explosion_5.png");
            health_pickup_image = loader.loadImage("images/health_pickup.png");
//            music = new Sound("sounds/Take_a_Chance.wav");
            gunshot = new Sound("sounds/Gun_Shot.wav");
            explosion = new Sound("sounds/Explosion.wav");
            laser = new Sound("sounds/Laser.wav");
            
        }catch(IOException e){
            e.printStackTrace();
        }
//            music.play();
        switch (level){
            case 1:{
               try{
                    grabImage = loader.loadImage("images/vorpal_up.png");
                    background = loader.loadImage("images/scene_1_new.png");
                    paused_background = loader.loadImage("images/scene_1_faded.png");
            }catch(IOException e){
            e.printStackTrace();
        }
            p = new Player(234.5, 485, this);
            player_health = 100;
            e1 = new Enemy(245, 105, this, "lvl_1_1", 1);
            objectives_done = false;
            break;
            }
            case 2:{
               try{
                    grabImage = loader.loadImage("images/vorpal_up.png");
                    background = loader.loadImage("images/scene_2_new.png");
                    paused_background = loader.loadImage("images/scene_2_faded.png");
            }catch(IOException e){
            e.printStackTrace();
        }
            health_pickup = null;
            p = new Player(0, 480, this, player_health);
            e1 = new Enemy(2, 135, this, "lvl_2_1", 1);
            objectives_done = false;
//            e2 = new Enemy(400.5, 124.5, this, "lvl_2_2");
//            e.disable();
            break;
            }
            default:{
                tip.tipSet("You Won!", 250, 250);
                show_tip = true;
                State = STATE.PAUSED;
                finished = true;
                level = 0;

                break;
            }
        }
        
        
//        player1 = new ImageIcon("images/vorpal.png").getImage();
        ter = new Terrain(background);
        this.addKeyListener(new KeyInput(this));
        this.addMouseListener(new MouseInput());
        bc = new BulletController(this);
        menu = new Menu();
//        tip = new Tips();
//        p = new Player(234.5, 485, this);
    }
    private synchronized void start(){
        if (running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    private synchronized void stop(){
        if(!running){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.exit(1);
    }
    
    public void run(){
        init();
        music.play();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
//        int updateS = 0;
//        int frames = 0;
        long timer = System.currentTimeMillis();
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >= 1){
                tick();
//                ++updateS;
                --delta;
            }
            render();
//            ++frames;
            
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
//                System.out.println(updateS + " Ticks, FPS: " + frames);
                System.out.println(p.getX() + ", " + p.getY());
//                ter.isWalkable(p.getX(), p.getY());
//                updateS = 0;
//                frames = 0;
            }
        }
        stop();
    }
    
    private void tick(){
        if(State == STATE.GAME){

        if (p != null){
            p.tick(ter,this);
        }
        if (ticker != null){
            ticker.tick();  
        }

        if (bc != null){
            bc.tick();
        }
        if (e1 != null){
            e1.tick();
        }
        if (e2 != null){
            e2.tick();
        }
        if (e3 != null){
            e3.tick();
        }
        if (e4 != null){
            e4.tick();
        }
        if (e5 != null){
            e5.tick();
        }
        if (e6 != null){
            e6.tick();
        }
        if (e7 != null){
            e7.tick();
        }
        if (e8 != null){
            e8.tick();
        }
        if (e9 != null){
            e9.tick();
        }
        if (e10 != null){
            e10.tick();
        }
        if (health_pickup != null){
            health_pickup.tick();
        }
        }
    }
    
    private void render(){
        
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        //g.drawImage(player, 100, 100, this);
        if (State == STATE.GAME || State == STATE.PAUSED){
        g.drawImage(background, 0, 0, this);
        }
        else if (State == STATE.MENU || State == STATE.PAUSED){
            g.drawImage(paused_background, 0, 0, this);
        }
        if(State == STATE.GAME || State == STATE.PAUSED){
            
            if(p != null){
                p.render(g);  
            };
            if(bc != null){
                bc.render(g);  
            }
            if(e1 != null){
                e1.render(g);  
            }
            if(e2 != null){
                e2.render(g);  
            }
            if(e3 != null){
                e3.render(g);  
            }
            if(e4 != null){
                e4.render(g);  
            }
            if(e5 != null){
                e5.render(g);  
            }
            if(e6 != null){
                e6.render(g);  
            }
            if(e7 != null){
                e7.render(g);  
            }
            if(e8 != null){
                e8.render(g);  
            }
            if(e9 != null){
                e9.render(g);  
            }
            if(e10 != null){
                e10.render(g);  
            }
            if(health_pickup != null){
                health_pickup.render(g);  
            }

        }
        else if (State == STATE.MENU){
            menu.render(g);
        }
        if(show_tip){
            tip.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(State == STATE.GAME){
        if(key == KeyEvent.VK_RIGHT){
            p.setVelX(1.8);
            if(!hold_key_down){
                p.changeDirection(vorpal_right, "right");
            }
            right_key_down = true;
        }
        if(key == KeyEvent.VK_LEFT){
            p.setVelX(-1.8);
            if(!hold_key_down){
                p.changeDirection(vorpal_left, "left");
            }
            left_key_down = true;
        }
        if(key == KeyEvent.VK_DOWN){
            p.setVelY(1.8);
            if(!hold_key_down){
                p.changeDirection(vorpal_down, "down");
            }
            down_key_down = true;
        }
        if(key == KeyEvent.VK_UP){
            p.setVelY(-1.8);
            if(!hold_key_down){
                p.changeDirection(vorpal_up, "up");
            }
            up_key_down = true;
        }
        if(key == KeyEvent.VK_S){
                hold_key_down = true;
            }
        if(key == KeyEvent.VK_ESCAPE){
//                if (State == STATE.GAME){
//                    paused = false;
//                }
//                if (!paused){
                    paused = !paused;
                    State = STATE.MENU;
//                }
//                else {
//                    State = STATE.GAME;
//                    paused = !paused;
//                }
            }
        if(key == KeyEvent.VK_A && !is_shooting && level > 1){
            is_shooting = true;
            gunshot.playOnce();
                if(gunside){
                    switch (p.getDirection()){
                        case "up":{
                            bc.addBullet(new Bullet(p.getX(), p.getY(), this, p));
                            break;
                        }
                        case "down":{
                            bc.addBullet(new Bullet(p.getX(), p.getY()+18, this, p));
                            break;
                        }
                        case "left":{
                            bc.addBullet(new Bullet(p.getX(), p.getY()+18, this, p));
                            break;
                        }
                        case "right":{
                            bc.addBullet(new Bullet(p.getX()+18, p.getY()+18, this, p));
                            break;
                        }
                    }
                    gunside = !gunside;
                }
                else{
                    switch (p.getDirection()){
                        case "up":{
                            bc.addBullet(new Bullet(p.getX()+18, p.getY(), this, p));
                            break;
                        }
                        case "down":{
                            bc.addBullet(new Bullet(p.getX()+18, p.getY()+18, this, p));
                            break;
                        }
                        case "left":{
                            bc.addBullet(new Bullet(p.getX(), p.getY(), this, p));
                            break;
                        }
                        case "right":{
                            bc.addBullet(new Bullet(p.getX()+18, p.getY(), this, p));
                            break;
                        }
                    }
                    gunside = !gunside;
                }
            }
            if(key == KeyEvent.VK_D && this.e1.isAlive() && this.e1.isNear(p.getX(),p.getY()) && level == 1){
                this.e1.disable();
                health_pickup = new HealthPickup(182, 78, this);
                objectives_done = true;
            }
        }
        if(key == KeyEvent.VK_ENTER){
           if (State == STATE.PAUSED && !finished){
            
                State = STATE.GAME;
                show_tip = false;
            }
           else if (State == STATE.PAUSED && finished){
               State = STATE.MENU;
               show_tip = false;
               finished = false;
           }
           else if(finished){
               State = STATE.MENU;
               show_tip = false;
               finished = false;
               level = 1;
               init();
           }
        }
    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT){
            if (!left_key_down){
                p.setVelX(0);
            }
            right_key_down = false;
        }
        if(key == KeyEvent.VK_LEFT){
            if (!right_key_down){
                p.setVelX(0);
            }
            left_key_down = false;
        }
        if(key == KeyEvent.VK_DOWN){
            if (!up_key_down){
                p.setVelY(0);
            }
            down_key_down = false;
        }
        if(key == KeyEvent.VK_UP){
            if (!down_key_down){
                p.setVelY(0);
            }
            up_key_down = false;
        }
        if(key == KeyEvent.VK_S){
            hold_key_down = false;
        }
        if(key == KeyEvent.VK_A){
            is_shooting = false;
        }
    }
    
    public static void main(String args[]){
        CAP3027TermProject PV = new CAP3027TermProject();
        
        PV.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        PV.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        PV.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        
        JFrame frame = new JFrame(PV.TITLE);
        frame.add(PV);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        PV.start();
    }
    
    public BufferedImage getPlayerImage(){
        return grabImage;
        //return (BufferedImage)player1;
    }
    public Player getPlayer(){
        return p;
        //return (BufferedImage)player1;
    }
    public Enemy getEnemy(int i){
        switch (i){
            case 1: {
                return e1;
            }
            case 2: {
                return e2;
            }
            case 3: {
                return e3;
            }
            case 4: {
                return e4;
            }
            case 5: {
                return e5;
            }
            case 6: {
                return e6;
            }
            case 7: {
                return e7;
            }
            case 8: {
                return e8;
            }
            case 9: {
                return e9;
            }
            case 10: {
                return e10;
            }
            default:
                return e1;
        }
        //return (BufferedImage)player1;
    }
    public void setEnemy(int i, double x, double y, String type){
        switch (i){
            case 1: {
                e1 = new Enemy(x, y, this, type, i);
                break;
            }
            case 2: {
                e2 = new Enemy(x, y, this, type, i);
                break;
            }
            case 3: {
                e3 = new Enemy(x, y, this, type, i);
                break;
            }
            case 4: {
                e4 = new Enemy(x, y, this, type, i);
                break;
            }
            case 5: {
                e5 = new Enemy(x, y, this, type, i);
                break;
            }
            case 6: {
                e6 = new Enemy(x, y, this, type, i);
                break;
            }
            case 7: {
                e7 = new Enemy(x, y, this, type, i);
                break;
            }
            case 8: {
                e8 = new Enemy(x, y, this, type, i);
                break;
            }
            case 9: {
                e9 = new Enemy(x, y, this, type, i);
                break;
            }
            case 10: {
                e10 = new Enemy(x, y, this, type, i);
                break;
            }
            default:
                e1 = new Enemy(x, y, this, type, i);
                break;
        }
        //return (BufferedImage)player1;
    }
    public void setEnemy(int i, double x, double y, String type, double missile_delay){
        switch (i){
            case 1: {
                e1 = new Enemy(x, y, this, type, i, missile_delay);
                break;
            }
            case 2: {
                e2 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 3: {
                e3 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 4: {
                e4 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 5: {
                e5 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 6: {
                e6 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 7: {
                e7 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 8: {
                e8 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 9: {
                e9 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            case 10: {
                e10 = new Enemy(x, y, this, type, i,missile_delay);
                break;
            }
            default:
                e1 = new Enemy(x, y, this, type, i,missile_delay);
                break;
        }
        //return (BufferedImage)player1;
    }
    
        public void killEnemy(int i){
        switch (i){
            case 1: {
                e1 = null;
                break;
            }
            case 2: {
                e2 = null;
                break;
            }
            case 3: {
                e3 = null;
                break;
            }
            case 4: {
                e4 = null;
                break;
            }
            case 5: {
                e5 = null;
                break;
            }
            case 6: {
                e6 = null;
                break;
            }
            case 7: {
                e7 = null;
                break;
            }
            case 8: {
                e8 = null;
                break;
            }
            case 9: {
                e9 = null;
                break;
            }
            case 10: {
                e10 = null;
                break;
            }
            default:
                e1 = null;
                break;
        }
        //return (BufferedImage)player1;
    }
        
    public boolean enemyIsDead(int i){
        switch (i){
            case 1: {
                return (e1 == null);
            }
            case 2: {
                return (e2 == null);
            }
            case 3: {
                return (e3 == null);
            }
            case 4: {
                return (e4 == null);
            }
            case 5: {
                return (e5 == null);
            }
            case 6: {
                return (e6 == null);
            }
            case 7: {
                return (e7 == null);
            }
            case 8: {
                return (e8 == null);
            }
            case 9: {
                return (e9 == null);
            }
            case 10: {
                return (e10 == null);
            }
            default:
                return (e1 == null);
        }
        //return (BufferedImage)player1;
    }
    
    public BufferedImage getBulletFacingUp(){
        return vorpal_bullet_up;
        //return (BufferedImage)player1;
    }
    public BufferedImage getBulletFacingDown(){
        return vorpal_bullet_down;
        //return (BufferedImage)player1;
    }
    public BufferedImage getBulletFacingLeft(){
        return vorpal_bullet_left;
        //return (BufferedImage)player1;
    }
    public BufferedImage getBulletFacingRight(){
        return vorpal_bullet_right;
        //return (BufferedImage)player1;
    }
    public STATE getState(){
        return State;
    }
    public void setState(String state){
        switch (state){
            case "Game" :{
               State = STATE.GAME;
               break;
            }
            case "Paused" :{
               State = STATE.PAUSED;
               break;
            }
            case "Menu" :{
               State = STATE.MENU;
               break;
            }
            default :{
               State = STATE.GAME;
               break;
            }
            
    }
        //return (BufferedImage)player1;
    }
    public void incrementLevel(){
        ++level;
    }
    public int getLevel(){
        return level;
    }
    public Graphics getGraphic(){
        return g;
    }
    public void setShowTip(Boolean t){
        show_tip = t;
    }
    public Tips getTip(){
        return tip;
    }
    public BufferedImage getHealthPickupImage(){
        return health_pickup_image;
    }
    public BufferedImage getLevel1Machine(){
        return level_1_machine;
    }
    public BufferedImage getLevel2RaptorOpen(){
        return level_2_raptor_open;
    }
    public BufferedImage getLevel2RaptorClose(){
        return level_2_raptor_close;
    }
    public BufferedImage getLevel2RaptorOpenUp(){
        return level_2_raptor_open_up;
    }
    public BufferedImage getLevel2RaptorCloseUp(){
        return level_2_raptor_close_up;
    }
        public BufferedImage getLevel2RaptorOpenRight(){
        return level_2_raptor_open_right;
    }
    public BufferedImage getLevel2RaptorCloseRight(){
        return level_2_raptor_close_right;
    }
        public BufferedImage getLevel2RaptorOpenLeft(){
        return level_2_raptor_open_left;
    }
    public BufferedImage getLevel2RaptorCloseLeft(){
        return level_2_raptor_close_left;
    }
    public BufferedImage getLevel2BigRaptorOpen(){
        return level_2_BigRaptor_open;
    }
    public BufferedImage getLevel2BigRaptorClose(){
        return level_2_BigRaptor_close;
    }
    public BufferedImage getLevel2BigRaptorOpenLeft(){
        return level_2_BigRaptor_open_left;
    }
    public BufferedImage getLevel2BigRaptorCloseLeft(){
        return level_2_BigRaptor_close_left;
    }
    public BufferedImage getLevel2BigRaptorOpenRight(){
        return level_2_BigRaptor_open_right;
    }
    public BufferedImage getLevel2BigRaptorCloseRight(){
        return level_2_BigRaptor_close_right;
    }
    public BufferedImage getLevel2BigRaptorOpenUp(){
        return level_2_BigRaptor_open_up;
    }
    public BufferedImage getLevel2BigRaptorCloseUp(){
        return level_2_BigRaptor_close_up;
    }
    public BufferedImage getLevel1MachineDead(){
        return level_1_machine_dead;
    }
    public BufferedImage getMachineBullet(){
        return machine_bullet;
    }
    public BufferedImage getMachineMissile(String s){
        
        switch (s){
            case "left":{
                return level_2_machine_missile_left;
            }
            case "right":{
                return level_2_machine_missile_right;
            }
            case "up":{
                return level_2_machine_missile_up;
            }
            case "down":{
                return level_2_machine_missile;
            }
            case "LU":{
                return level_2_machine_missile_LU;
            }
            case "LD":{
                return level_2_machine_missile_LD;
            }
            case "RU":{
                return level_2_machine_missile_RU;
            }
            case "RD":{
                return level_2_machine_missile_RD;
            }
            default:{      
                return level_2_machine_missile;
            }
        }

    }
    public BufferedImage getMissileLauncher(String s){
        
        switch (s){
            case "1":{
                return level_2_missile_launcher_1;
            }
            case "2":{
                return level_2_missile_launcher_2;
            }
            case "3":{
                return level_2_missile_launcher_3;
            }
            default :{
                return level_2_missile_launcher_2;
            }
        }
    }
    public BufferedImage getMissileLauncherLeft(String s){
        
        switch (s){
            case "1":{
                return level_2_missile_launcher_1_left;
            }
            case "2":{
                return level_2_missile_launcher_2_left;
            }
            case "3":{
                return level_2_missile_launcher_3_left;
            }
            default :{
                return level_2_missile_launcher_2_left;
            }
        }
    }
    public BulletController getBulletController(){
        return bc;
    }
    public Terrain getTerrain(){
        return ter;
    }
    public void endGame(){
        finished = true;
    }
    public Ticker getTicker(){
        return ticker;
    }
    public void setTicker(double s){
        ticker = new Ticker(this, s);
    }
    public void stopTicker(){
        ticker = null;
    }
    public void objectivesComplete(){
        objectives_done = true;
    }
    public boolean objectivesAreComplete(){
        return objectives_done;
    }
    public BufferedImage getExplosions(int i){
        switch (i){
            case 1: {
                return explosion_1;
            }
            case 2: {
                return explosion_2;
            }
            case 3: {
                return explosion_3;
            }
            case 4: {
                return explosion_4;
            }
            case 5: {
                return explosion_5;
            }
            default: {
                return explosion_1;
            }
        }
    }
}
