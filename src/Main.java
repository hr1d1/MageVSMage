import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{

    Cave cave = new Cave();
    private int mouseX, mouseY;
    private int selToolTile = -1;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Mage VS Mage");;
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        
        Canvas canvas = new Canvas(1200,800);
        root.getChildren().add(canvas);
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){

                mouseX = (int) event.getX();
                mouseY = (int) event.getY();
                
                
                // clicked Up
                if(mouseX >= 950 && mouseX <= 1030 && mouseY >= 120 && mouseY <= 200)
                    selToolTile = cave.UP;
                // clicked Down
                else if(mouseX >= 950 && mouseX <= 1030 && mouseY >= 280 && mouseY <= 360)
                    selToolTile = cave.DOWN;
                // clicked Left
                else if(mouseX >= 850 && mouseX <= 930 && mouseY >= 200 && mouseY <= 280)
                    selToolTile = cave.LEFT;
                // clicked Right
                else if(mouseX >= 1050 && mouseX <= 1130 && mouseY >= 200 && mouseY <= 280)
                    selToolTile = cave.RIGHT;
                cave.playPlayer(selToolTile);
                
                if(cave.winner == 0){
                   // System.out.println(cave.winner);
                    gc.setFill(Color.BLACK);
                    gc.setFont(Font.font("Tahoma", 100));
                    gc.fillText("YOU WIN",250 , 300);

                    try {
                        stop();
                        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cave.agent[0].mana = 0;
                }
                else if(cave.winner == 1){
                    gc.setFill(Color.RED);
                    gc.setFont(Font.font("Tahoma", 100));
                    gc.fillText("YOU LOSE",250 , 250);
                    try {
                        stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else{
                    cave.playAI();
                    //System.out.println("x");
                    if(cave.winner == 1){
                        gc.setFill(Color.RED);
                        gc.setFont(Font.font("Tahoma", 100));
                        gc.fillText("YOU LOSE",250 , 250);
                        try {
                            stop();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                
            }
        });
        
        
        new AnimationTimer(){

            @Override
            public void handle(long arg0) {
                gc.setFill(Color.LAVENDER);
                gc.fillRect(0, 0, 1200, 800);
                drawToolBar(gc);
                cave.draw(gc);
            }
    
        }.start();

        primaryStage.show();

    }

    public void drawToolBar(GraphicsContext gc){
        gc.drawImage(cave.getUp(), 950, 120);
        gc.drawImage(cave.getDown(), 950, 280);
        gc.drawImage(cave.getLeft(), 850, 200);
        gc.drawImage(cave.getRight(), 1050, 200);
        

        gc.setFill(Color.BLACK);
        gc.setFont(Font.font("Garamond",16));
        gc.fillText("Player Mana: "+cave.getPlayerMana(), 950, 500);
        gc.fillText("Enemy Mana: "+cave.getAIMana(), 950, 550);

    }

    public static void main(String[] args) {
        launch(args);
    }
}