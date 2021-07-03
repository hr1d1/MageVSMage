import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;





public class Cave {

    


    int tiles[][];
  //  private boolean visible[][];
    Agent agent[];
    Location idol;
    Location player, ai;
    int winner = -1;
    public static final int  ENEMY = 1, PLAYER = 0, GROUND = 2, WALL = 3, POTION = 4, IDOL = 5, EMPTY = 6, UP = 11, DOWN = 12, LEFT = 13, RIGHT = 14;
    GraphicsContext gc;

    private String path = "C:/MIST/L-4 T-1/CSE - 403_404/Lab/5/trial/src";

    private Image groundImage, wallImage, potionImage, idolImage, whiteImage, playerImage, aImage, up, down, left, right, win, lose;
    public Cave(){
        
        agent = new Agent[2];
        agent[0] = new Player("Player",0);
        agent[1] = new Ai("Bot",1);
        tiles = new int [10][10];
      //  visible = new boolean [10][10];
        
        //play();
        setTiles();
        try{
        FileInputStream inputStream = new FileInputStream(path + "/images/floor4.png");
        groundImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/wall.png");
        wallImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/mana.png");
        potionImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/idol.png");
        idolImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/whitetile.png");
        whiteImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/ai.png");
        aImage = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/robe.png");
        playerImage= new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/up.png");
        up= new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/down.png");
        down= new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/left.png");
        left= new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/right.png");
        right= new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/win.png");
        win = new Image(inputStream);
        inputStream = new FileInputStream(path + "/images/lose.png");
        lose = new Image(inputStream);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        
    }

    public void setGc(Canvas canvas){
        this.gc = canvas.getGraphicsContext2D();
    }

    public void move(int x,int y, int z, int a, int b){
        tiles[x][y] = z;
        if(z == PLAYER){
            player.setRow(x);
            player.setCol(y);
        }

        else if(z == ENEMY){
            ai.setRow(x);
            ai.setCol(y);
        }

        tiles[a][b] = GROUND;
    } 


    void setTiles(){

        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){

                if(tiles[i][j] != WALL || tiles[i][j] != POTION || tiles[i][j] != IDOL || tiles[i][j] != PLAYER || tiles[i][j] != ENEMY )
                tiles[i][j] = GROUND;

            }
        } 

        tiles[0][0] = PLAYER;
        player = new Location(0, 0);
        tiles[9][9] = ENEMY;
        ai = new Location(9, 9);
        Random rand = new Random();
        Set<Integer> pos = new LinkedHashSet<Integer>();
        
        while(pos.size() <14){
            pos.add(rand.nextInt(97)+1);
        }

        int k = 0;

        for(int j : pos){
           
            if(k == 0){
                tiles[j/10][j%10] = IDOL;
                idol = new Location(j/10,j%10);
            }

            else if(k >= 1 && k < 11){
                tiles[j/10][j%10] = WALL;
            }

            else{
                tiles[j/10][j%10] = POTION;
            }

            k++;
        }

       


        
    }

    public void draw(GraphicsContext gc){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[0].length; j++){
                if(tiles[i][j] == GROUND){
                    gc.drawImage(groundImage, (j*80), (i*80));
                }

                if(tiles[i][j] == EMPTY){
                    gc.drawImage(whiteImage, (j*80), (i*80));
                }

                if(tiles[i][j] == IDOL){
                    gc.drawImage(idolImage, (j*80), (i*80));
                }

                if(tiles[i][j] == POTION){
                    gc.drawImage(potionImage, (j*80), (i*80));
                }

                if(tiles[i][j] == WALL){
                    gc.drawImage(wallImage, (j*80), (i*80));
                }

                if(tiles[i][j] == PLAYER){
                    gc.drawImage(playerImage, (j*80), (i*80));
                }

                if(tiles[i][j] == ENEMY){
                    gc.drawImage(aImage, (j*80), (i*80));
                }
            }
            
        }
    }

   /* public void play(){
        Random rand = new Random();
        int turn = rand.nextInt(2);
       // turn = (turn + 1) % 2;
        
        setTiles();
       /* tiles[player.getRow()][player.getCol()] = GROUND;
        player.setRow(player.getRow()+1);
        player.setCol(player.getCol());
        tiles[player.getRow()][player.getCol()] = PLAYER;
        //draw(gc);
        System.out.println(isWinner());
       
        while(!isFinished()){
            turn = (turn + 1) % 2;
            if(turn == ENEMY){
                System.out.println(ai.toString());
               // agent[1].makeMove(this);
                
            }

            else{
                System.out.println(player.toString());
                //agent[0].makeMove(this);
                
            }

            //draw(gc);
            
        }

        if(isWinner() == 50){

           // gc.setFill(Color.BLACK);
           // gc.fillText("You Win", 900, 500);
           System.out.println("You Win");
        }

        else{
           // gc.setFill(Color.RED);
           // gc.fillText("You Win", 900, 500);
           System.out.println("You Lose");
        }
           


    }*/
    
    public void playPlayer(int dir){
        int row, col,a,b;
        row = a = agent[0].self.getRow();
        col = b = agent[0].self.getCol();
        
        if(dir == UP) 
            row -= 1;
        else if(dir == DOWN)
            row += 1;
         else if(dir == LEFT)
            col -= 1;
        else if(dir == RIGHT)
            col += 1;
        
        if(isValid(new Location(row,col)) && agent[0].getMana() > 0){
            
            
            if(tiles[row][col] == POTION){
                
                agent[0].mana = 10;
                
            }
            else
                agent[0].setMana(agent[0].mana-1);

            if(tiles[row][col] == IDOL)
                winner = PLAYER;
            
            move(row, col, 0, a, b);
            agent[0].self.setRow(row);
            agent[0].self.setCol(col);
                
        }

        if(agent[0].getMana() == 0){
            winner = ENEMY;
        }

    }

   /* public void text(GraphicsContext gc){
        if (winner == PLAYER){
            gc.setFill(Color.BLACK);
            gc.setFont(Font.font("Tahoma", 100));
            gc.fillText("YOU WIN",950 , 300);
        }
    }*/

    

    public void playAI(){
        int a = ai.getRow();
        int b = ai.getCol();
        
        Location nxt = ((Ai) agent[1]).makeMove(this);

        if(tiles[nxt.getRow()][nxt.getCol()] == POTION)                
            agent[1].mana = 10;
        else
            agent[1].setMana(agent[1].mana-1);
        
        if(tiles[nxt.getRow()][nxt.getCol()] == IDOL)
            winner = ENEMY;

        move(nxt.getRow(), nxt.getCol(), ENEMY, a, b);
        agent[1].self.setRow(nxt.getRow());
        agent[1].self.setCol(nxt.getCol());

        if(agent[1].getMana() == 0){
            winner = PLAYER;
        }



    }
    
    public static int getGround() {
        return GROUND;
    }

    public static int getWall() {
        return WALL;
    }

    public static int getPotion() {
        return POTION;
    }

    public static int getIdol() {
        return IDOL;
    }

    public static int getEnemy() {
        return ENEMY;
    }

    public static int getPlayer() {
        return PLAYER;
    }

    public Image getUp() {
        return up;
    }

    public Image getDown() {
        return down;
    }

    public Image getLeft() {
        return left;
    }

    public Image getRight() {
        return right;
    }

    public Image getWin(){
        return win;
    }

    public Image getLose(){
        return lose;
    }

    public boolean isValid(Location loc){
        return loc.getRow() >= 0 && loc.getRow() < tiles.length && loc.getCol() >= 0 && loc.getCol() < tiles[loc.getRow()].length && tiles[loc.getRow()][loc.getCol()] != WALL;
    }

  /* public int isWinner (){
        if (player.getRow() == idol.getRow() && player.getCol() == idol.getCol())
            return 50;
        else if (ai.getRow() == idol.getRow() && ai.getCol() == idol.getCol())
            return -50;
        else return 69;
   }*/

   public boolean isFinished(){
       return (player.getRow() == idol.getRow() && player.getCol() == idol.getCol()) || (ai.getRow() == idol.getRow() && ai.getCol() == idol.getCol());
   }

   public int getPlayerMana(){
        return agent[0].getMana();
   }

   public int getAIMana(){
    return agent[1].getMana();
}
}
