package controller;

import java.io.IOException;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Board;
import model.Fox;
import model.Movement;
import model.Pawpcicle;
import model.Rabbit;
import model.Wolf;


public class GameController {

    Rabbit ObjRabbit = new Rabbit();
    Fox ObjFox = new Fox();

    Wolf ObjWolf= new Wolf();
    Board ObjBoard = new Board();
    Movement ObjMovement = new Movement();

    Pawpcicle ObjPawp=new Pawpcicle();
    private static Rectangle[][] rec;
    int i,j;
    boolean lose = false;
    boolean win = false;
    int nUserInput;
    int nMoves = 0;
    int nMoveCounter = 0;
    Boolean PowerUP=false;
    int PowerUpTurns=0;

    int Two = 0;
    private static Image Grass = new Image("/images/grass.png");
    private static ImagePattern tGrass = new ImagePattern(Grass);
    private static Image Fox = new Image("/images/fox.png");
    private static ImagePattern tFox = new ImagePattern(Fox);
    private static Image Bunny = new Image("/images/bunny.png");

    private static ImagePattern tBunny = new ImagePattern(Bunny);
    private static Image Wolf= new Image("/images/wolf.png");
    private static ImagePattern tWolf = new ImagePattern(Wolf);

    private static Image Pawpcicle= new Image("/images/Pawcicle.png");
    private static ImagePattern tPawp=new ImagePattern(Pawpcicle);

    Timeline animate;


    @FXML
    private Pane gridPane;
    @FXML
    private Label labelRabbits;
    @FXML
    private Label labelEaten;
    @FXML
    private Label labelMoves;
    @FXML
    private Label labelPositionFox;

    public void initialize(){
        int[] place= new int[2];
        ObjBoard.InitializenBoardPlacement();
        //generate wolf
        place=ObjBoard.generateAnimalOnBoard(ObjWolf.nWolfValuePlacement);
        ObjWolf.WolfX=place[0];
        ObjWolf.WolfY=place[1];
        //generate fox
        place=ObjBoard.generateAnimalOnBoard(ObjFox.nFoxValuePlacement);
        ObjFox.FoxX=place[0];
        ObjFox.FoxY=place[1];

        //generate powerup Pawpcicle
        place=ObjBoard.generateAnimalOnBoard(ObjPawp.nPawpcicleValuePlacement);
        ObjPawp.X=place[0];
        ObjPawp.Y=place[1];
        //generate 3 rabbits
        for(int j = 0; j < ObjRabbit.nRabbitCount; j++) {
            ObjBoard.generateAnimalOnBoard2(ObjRabbit.nRabbitValuePlacement);
        }
        rec = new Rectangle[20][20];

        for ( i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                rec[i][j] = new Rectangle();
                rec[i][j].setX(i * 25);

                rec[i][j].setY(j * 25);
                rec[i][j].setWidth(25);
                rec[i][j].setHeight(25);
                if (ObjBoard.nBoardPlacement[i][j] == 0)
                    rec[i][j].setFill(tGrass);
                if (ObjBoard.nBoardPlacement[i][j] == 1)
                    rec[i][j].setFill(tBunny);
                if (ObjBoard.nBoardPlacement[i][j] == 5)
                    rec[i][j].setFill(tFox);
                if (ObjBoard.nBoardPlacement[i][j] == 7)
                    rec[i][j].setFill(tWolf);
                if (ObjBoard.nBoardPlacement[i][j] == 10)
                    rec[i][j].setFill(tPawp);
                gridPane.getChildren().add(rec[i][j]);
            }
        }
        startMoving();
    }


    public void openWin(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/winView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("CONGRATS");
            stage.setScene(new Scene(root, 500, 500));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openLose(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/loseView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("YOU LOSE");
            stage.setScene(new Scene(root, 500, 500));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void openMainMenu(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainView.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("MAIN MENU");
            stage.setScene(new Scene(root, 800, 600));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startGame(){
        ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);
    }


    public void startMoving(){
        animate = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            try {
                if(!lose&&!win){
                    startGame();
                    updateGUI();
                }
                else if(lose){
                    updateGUI();
                    animate.stop();
                    openLose(event);
                }
                else if (win){
                    updateGUI();
                    animate.stop();
                    openWin(event);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();
    }

    public void updateGUI(){
        for ( i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                if (ObjBoard.nBoardPlacement[i][j] == 0)
                    rec[i][j].setFill(tGrass);
                if (ObjBoard.nBoardPlacement[i][j] == 1)
                    rec[i][j].setFill(tBunny);
                if (ObjBoard.nBoardPlacement[i][j] == 5)
                    rec[i][j].setFill(tFox);
                if(ObjBoard.nBoardPlacement[i][j] == 7)
                    rec[i][j].setFill(tWolf);
            }
        }
    }

    public int checkedMoves(Boolean[] b)
    {
        String moves="";
        Random random=new Random();

        for(int i=0;i<4;i++)
        {
            if(b[i]==true)
            {
                moves+=(char)(i+1+'0');
            }
        }

        if(moves=="")
            return 0;

        else //get randomly checked number
        {
            int index=random.nextInt(moves.length());
            return Integer.parseInt(String.valueOf(moves.charAt(index)));
        }

    }

    public void moveRabbit(){
        Boolean Check[];
        for (int j = 0; j < ObjRabbit.nRabbitCount; j++) {

            ObjBoard.findAnimalPlace(ObjRabbit.nRabbitValuePlacement);
            Check=ObjBoard.checkPlacement();

            nUserInput = checkedMoves(Check);

            switch (nUserInput) {
                case 1: //UP
                    ObjMovement.moveUp(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
                    break;

                case 2: //DOWN
                    ObjMovement.moveDown(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
                    break;

                case 3: //LEFT
                    ObjMovement.moveLeft(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
                    break;

                case 4: //RIGHT
                    ObjMovement.moveRight(ObjBoard.nBoardPlacement, ObjRabbit.nRabbitValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
                    break;
            }
        }
        ObjRabbit.resetRabbitValuesOnBoard(ObjBoard.nBoardPlacement);
    }


    public void moveFox(KeyEvent keyEvent) {

        if (!lose && !win) {
            ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);
            labelEaten.setText("" + ObjFox.nRabbitsEaten);
            labelRabbits.setText("" + ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement));
            labelMoves.setText("" + nMoves);
            ObjRabbit.nBeforeRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);

            if (keyEvent.getCode() == KeyCode.UP) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveUp(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);

            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                System.out.println("1Y on Board ="+ObjBoard.y);
                ObjMovement.moveDown(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
                System.out.println("Fox Y ="+ObjFox.FoxY);
                System.out.println("2Y on Board ="+ObjBoard.y);
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveLeft(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                ObjBoard.findAnimalPlace(ObjFox.nFoxValuePlacement);
                ObjMovement.moveRight(ObjBoard.nBoardPlacement, ObjFox.nFoxValuePlacement, ObjBoard.x, ObjBoard.y,ObjFox,ObjWolf);
            }
            labelPositionFox.setText("" + ObjBoard.x + "-" + ObjBoard.y);

            nMoveCounter++;
            nMoves++;

            ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);

            System.out.println("Wolf X ="+ObjWolf.WolfX);
            System.out.println("Wolf Y ="+ObjWolf.WolfY);
            moveWolf();
            ObjRabbit.nRabbitCount = ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement);
            System.out.println("Wolf X ="+ObjWolf.WolfX);
            System.out.println("Wolf Y ="+ObjWolf.WolfY);


            if (ObjRabbit.nBeforeRabbitCount > ObjRabbit.nRabbitCount) {
                ObjFox.nRabbitsEaten += ObjRabbit.nBeforeRabbitCount - ObjRabbit.nRabbitCount;
            }
            if (ObjRabbit.nRabbitCount == 0)
            {
                win = true;
            }

            //check PowerUp
            if(ObjFox.pUp==10 && PowerUP==true)
            {
                PowerUpTurns++;
            }

            if(ObjFox.pUp>0 && PowerUP==false)
            {
                PowerUP=true;
            }

            if(ObjFox.pUp==10 && PowerUpTurns>5)
            {
                PowerUP=false;
                ObjFox.pUp=0;
                PowerUpTurns=0;
            }


            if(ObjFox.pUp!=10) {
                //moveRabbit();
                Two++;
                if(Two == 2){
                    moveRabbit();
                    Two = 0;
                }

                ObjRabbit.resetRabbitValuesOnBoard(ObjBoard.nBoardPlacement);


                //rabbits multiply
                if (nMoveCounter == 5 && ObjRabbit.nRabbitCount + ObjRabbit.RabbitAdder(nMoves) < ObjRabbit.nRabbitMax && ObjRabbit.nRabbitCount != 0) {
                    for (int j = 0; j < ObjRabbit.RabbitAdder(nMoves); j++) {
                        ObjBoard.generateAnimalOnBoard2(ObjRabbit.nRabbitValuePlacement);
                    }
                    ObjRabbit.doubles(ObjRabbit.RabbitAdder(nMoves));
                    nMoveCounter = 0;
                }

                if (nMoveCounter == 5 && ObjRabbit.nRabbitCount + ObjRabbit.RabbitAdder(nMoves) >= ObjRabbit.nRabbitMax && ObjRabbit.nRabbitCount != 0) {
                    for (int k = 0; k < ObjRabbit.nRabbitMax - ObjRabbit.nRabbitCount; k++) {
                        ObjBoard.generateAnimalOnBoard2(ObjRabbit.nRabbitValuePlacement);
                    }
                    ObjRabbit.nRabbitCount = ObjRabbit.nRabbitMax;
                    //lose = true;
                }



            }

            if(ObjRabbit.nRabbitCount == ObjRabbit.nRabbitMax){
                lose = true;
            }

            labelEaten.setText("" + ObjFox.nRabbitsEaten);
            labelRabbits.setText("" + ObjRabbit.findNumberOfRabbits(ObjBoard.nBoardPlacement));
            labelMoves.setText("" + nMoves);


        }

    }

    public void moveWolf()
    {
        int[][] rabbits=ObjBoard.findAllRabbits(ObjRabbit.nRabbitCount);
        ObjBoard.findAnimalPlace(ObjWolf.nWolfValuePlacement);
        int move=1; //temporary
        int newDistance;
        int distance = 0;
        int x=0,y=0;
        for(int i=0;i<rabbits.length;i++)
        {
            if(Math.abs(ObjWolf.WolfX-rabbits[i][0])<ObjBoard.nBoardPlacement.length/2)
            {
                newDistance=Math.abs(ObjWolf.WolfX-rabbits[i][0]);

            }

            else
            {
                newDistance=ObjBoard.nBoardPlacement.length-Math.abs(ObjWolf.WolfX-rabbits[i][0]);

            }

            if(Math.abs(ObjWolf.WolfY-rabbits[i][1])<ObjBoard.nBoardPlacement.length/2)
            {
                newDistance+=Math.abs(ObjWolf.WolfY-rabbits[i][1]);

            }

            else
            {
                newDistance+=ObjBoard.nBoardPlacement.length-Math.abs(ObjWolf.WolfY-rabbits[i][1]);

            }

            if(distance==0 || newDistance<distance)
            {
                distance=newDistance;
                x=rabbits[i][0];
                y=rabbits[i][1];

            }
        }

        //select move
        if(ObjWolf.WolfX==x)
        {
            if(y>ObjWolf.WolfY)
                move=2;
            else
                move=1;
        }

        else if(ObjWolf.WolfY==y)
        {
            if(x>ObjWolf.WolfX)
                move=4;
            else
                move=3;
        }

        else{
            int dx,dy;//distance between wolf and rabbit
            dx=Math.abs(ObjWolf.WolfX-x);
            dy=Math.abs(ObjWolf.WolfX-y);

            if(dx>dy)
            {
                if(y>ObjWolf.WolfY)
                    move=2;
                else
                    move=1;
            }

            else if(dx<dy)
            {
                if(x>ObjWolf.WolfX)
                    move=4;
                else
                    move=3;
            }

            else{
                int[] choice= new int[2];
                int j=0;
                if(y>ObjWolf.WolfY) {
                    choice[j]= 2;
                    j++;
                }
                else
                {
                    choice[j]=1;
                    j++;
                }
                if(x>ObjWolf.WolfX) {
                    choice[j] = 4;
                }
                else {
                    choice[j]= 3;
                }
                move = new Random().nextBoolean() ? choice[0] : choice[1];
            }
        }

        switch (move) {
            case 1:ObjMovement.moveUp(ObjBoard.nBoardPlacement, ObjWolf.nWolfValuePlacement, ObjWolf.WolfX, ObjWolf.WolfY, ObjFox,ObjWolf);
                break;
            case 2: ObjMovement.moveDown(ObjBoard.nBoardPlacement, ObjWolf.nWolfValuePlacement, ObjWolf.WolfX, ObjWolf.WolfY,ObjFox,ObjWolf);
                break;
            case 3:ObjMovement.moveLeft(ObjBoard.nBoardPlacement, ObjWolf.nWolfValuePlacement, ObjWolf.WolfX, ObjWolf.WolfY,ObjFox,ObjWolf);
                break;
            case 4:ObjMovement.moveRight(ObjBoard.nBoardPlacement, ObjWolf.nWolfValuePlacement, ObjWolf.WolfX,ObjWolf.WolfY,ObjFox,ObjWolf);
                break;
        }


    }

}

