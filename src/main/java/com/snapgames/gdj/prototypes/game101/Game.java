package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Game extends JFrame{
    public static final long serialVersionUID = 12365498746514L;



    public class InputHandler implements KeyListener{
        boolean[] keys = new boolean[256];

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()>0 && e.getKeyCode()<256){
                keys[e.getKeyCode()] = true;
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()>0 && e.getKeyCode()<256){
                keys[e.getKeyCode()] = false;
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        public boolean getKey(int code){
            return keys[code];
        }
    }
    /**
     * The interface for all Objects managed by the Game101 main class
     */
    public interface GameObject{
        public int layer = 0;
        public void update(long dt);
        public void render(Graphics2D g);
        default public int getLayer(){
            return layer;
        }
    }

    List<GameObject> objects = new ArrayList<>();

    Sprite player;
    TileMap tilemap;    

    int WIDTH = 640;
    int HEIGHT= 400;
    
    boolean isExitRequest = false;
    boolean isPaused = false;

    InputHandler inputHandler = new InputHandler();

    public class TileMap implements GameObject{
        char[] map;
        int width,height,layers;
        int tileWidth,tileHeight;
        BufferedImage tileset;
        String tileCharMapping = "";
        public TileMap(int width,int height, int nbLayers){
            map = new char[width*height*nbLayers];
            this.width=width;
            this.height=height;
            this.layers = nbLayers;
        }
        public void loadFromFile(String fileTileMap, String tileCharMapping, String fileTileSet, int tileWidth, int tileHeight){
            this.tileset = ImageIO.read(new File(fileTileSet));
            this.tileCharMapping=tileCharMapping;
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
            File fm = new File(fileTileMap);
            if(fm.exists()){
                this.map = Files.readAllBytes(Paths.get(fileTileMap));
            }
        }
        public char getTile(int x, int y, int layer){
            return map[(x+y*width)+(layer*height*width)];
        }

        public void update(long dt){

        }
        public void render(Graphics2D g){
            for(int l=0;l<layers;l++){
                for(int x=0;x<width;x++){
                    for(int y=0;y<height;y++){
                        BufferedImage tileImg = convertCharToTile(map[x+(y*width)+(l*width*height)]);
                        if(tileImg!=null){
                            g.drawImage(tileImg, x*this.tileWidth, y*this.tileHeight,null);
                        }
                    }
                }
            }
        }
        private BufferedImage convertCharToTile(char c){
            int tileNb = this.tileCharMapping.indexOf(c);
            if(tileNb!=0){
                int tilesetWidth = tileset.getWidth()/this.tileWidth;
                //int tilesetHeight =  tileset.getHeight()/this.tileHeight;
                int posYinTileSet = tileNb/tilesetWidth;
                int posXinTileSet = tileNb-(posYinTileSet*tilesetWidth);
                
                return tileset.getSubimage(posXinTileSet, posYinTileSet, tileWidth, tileHeight);
            }
            return null;
        }
    }
    public class Sprite implements GameObject{
        long x,y;
        long dx,dy;
        float mass;
        float gravity;
        float friction;
        float elasticity;
        String name="noname";
        Color color= Color.WHITE;
        long width=32,height=32;

        Point[] colliders;
        
        String animation = "";
        Map<String,Image[]> animations = new HashMap<>();
        int animFrame=0;
        int animFrameDuration=1000/8;

        public Sprite(String name, long x, long y, long dx, long dy){
            this.name = name;
            this.x= x;
            this.y=y;
            this.dx=dx;
            this.dy=dy;
            this.gravity=-9.81f;
            this.elasticity=1.0f;
            this.friction=1.0f;
            this.animation="";
        }
        public void update(long dt){
            this.x += this.dx*dt*this.friction;
            this.y += (this.dy*dt*this.friction)-this.gravity*dt;
        }
        public void render(Graphics2D g){
            g.setColor(this.color);
            g.fillRect((int)x, (int)y, (int)width, (int)height);
            if(!animation.equals("") && animations!=null && animation.containsKey(animation)){
                g.drawImage(animations.get(animation)[animFrame], x, y, null);
            }
        }
        public void addAnimation(String name, String path, String[] files){
            BufferedImage[] animationsFrames = new BufferedImage[files.length];
            int i=0;
            for(String file:files){
                BufferedImage imgFrame = ImageIO.read(new File(path+File.separator+file));
                animationsFrames[i++]=imgFrame;
            }
            animations.put(name,animationsFrames);
        }
        public void collideMapAt(TileMap map){
            for(Point p:colliders){
                if(map.getTile((x+p.x)/map.tileWidth,(y+p.y)/map.tileHeight,0)!=0){

                }
            }
        }
    }

    public Game(){
    }
    public void initializeWindow(){
        JFrame frame = new JFrame("game");
        frame.getRootPane().add(this);
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        inputHandler = new InputHandler();
        frame.addKeyListener(inputHandler);
        
        frame.pack();
        frame.setVisible(true);
    }

    public void initializeGame(){
        tilemap = new TileMap(32,20,1);
        tilemap.loadFromFile("maps/level1.txt", "[=]#H<@>cP", "images/tile01.png", 32, 32);
        objects.add(tilemap);

        player = new Sprite("player",0,0,0,0);
        player.x=0;player.y=0;
        player.mass=50.0f;
        
        objects.add(player);
        

    }
    public void start(){
        initializeWindow();
        initializeGame();
        run();
    }

    public void run(){
        Graphics2D g = (Graphics2D)this.getGraphics();
        long previousTime=0;
        long currentTime = previousTime =  System.currentTimeMillis();
        long dt = 0;
        while(!iExitRequest){
            input(inputHandler);
            if(!isPaused){
            currentTime =  System.currentTimeMillis();
            dt = currentTime - previousTime;
            update(dt);
            render(g);
            previousTime = currentTime;
            }
        }
    }

    public void input(InputHandler inputHandler){
        if(inputHandler.getKey(KeyEvent.VK_ESCAPE)){
            isExitRequest = true;
        }
        if(inputHandler.getKey(KeyEvent.VK_PAUSE)){
            isPaused = !isPaused;
        }
    }

    public void update(long dt){
        for(GameObject o:objects){
            o.update(dt);
        }
    }

    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);

        for(GameObject o:objects){
            o.render(g);
        }
    }

    public static void main(String[] argv){
        Game game = new Game();
        game.start();
    }
}