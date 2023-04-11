package model;

import java.util.Random;
public class Board {

    public int[][] nBoardPlacement = new int[20][20];//2d integer array for the board

    public int x;// x coordinate
    public int y;// y coordinate


    //this function makes all values for the board to be 0
    public void InitializenBoardPlacement() {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
                nBoardPlacement[i][j] = 0;
        }
    }

    //this function shows the values in the board
     /*
    public void ShownBoardPlacement() {
        for(int i = 0; i < 20; i++)
        {
            for(int j = 0; j < 20; j++)
            {
                System.out.print(nBoardPlacement[i][j]+" ");
            }
            System.out.println();
        }
    }*/

    //places the animal value on the board in an empty space 0
    public int[] generateAnimalOnBoard(int nAnimalValue) {
        Random rand = new Random();
        int i = 0;
        int randomNumX;
        int randomNumY;
        int[] place=new int[2];
        // generate random number from 0 to 19
        while(i != 1){
            randomNumX = rand.nextInt((19 - 0) + 1) + 0;// x value
            randomNumY = rand.nextInt((19 - 0) + 1) + 0;// y value
            if (nBoardPlacement[randomNumX][randomNumY] == 0){
                nBoardPlacement[randomNumX][randomNumY] = nAnimalValue;
                i = 1;
                place[0]=randomNumX;
                place[1]=randomNumY;
            }
        }
        return place;
    }
    public void generateAnimalOnBoard2(int nAnimalValue) {
        Random rand = new Random();
        int i = 0;
        int randomNumX;
        int randomNumY;

        // generate random number from 0 to 19
        while(i != 1){
            randomNumX = rand.nextInt((19 - 0) + 1) + 0;// x value
            randomNumY = rand.nextInt((19 - 0) + 1) + 0;// y value
            if (nBoardPlacement[randomNumX][randomNumY] == 0){
                nBoardPlacement[randomNumX][randomNumY] = nAnimalValue;
                i = 1;
            }
        }
    }


    // this searches and gets the x and y value of the fox, rabbit, or for eat function
    // animal value can only be 1 - rabbit / 100 - fox
    public void findAnimalPlace(int nAnimalValue) {
        find:
        for( int j = 0; j < 20; j++){
            for( int k = 0; k < 20; k++){
                if(nBoardPlacement[j][k] == nAnimalValue){
                    x = j;
                    y = k;
                    break find;// to break both loops
                }
            }
        }
    }

    //get the location of all rabbits
    public int[][] findAllRabbits(int nRabbit)
    {
        int[][] rabbits=new int[nRabbit][2];
        int i=0;
        find:
        for( int j = 0; j < 20; j++) {
            for (int k = 0; k < 20; k++) {
                if (nBoardPlacement[j][k] == 1) {
                    rabbits[i][0] = j; //x
                    rabbits[i][1] = k; //y
                    i++;

                    if(i==nRabbit)

                        break find;
                }
            }
        }

        return rabbits;
    }

    //Checks for animals around the piece or if it's at the edge CHANGE
    public Boolean[] checkPlacement()
    {
        Boolean[] check=new Boolean[4]; //0 for UP, 1 for DOWN, 2 for LEFT, 3 for RIGHT

        for(int i=0;i<4;i++){
            check[i]=true;
        }

        if(x==19) {
            check[3]=false; //Can't go RIGHT due to edge
        }

        else if(nBoardPlacement[x+1][y]>0){
            check[3]=false;
        }


        if(x==0) {
            check[2] = false; //Can't go LEFT due to edge
        }

        else if(nBoardPlacement[x-1][y]>0){
            check[2]=false;
        }

        if(y==19) {
            check[1]=false; //Can't go Down due to edge
        }

        else if(nBoardPlacement[x][y+1]>0){
            check[1]=false;
        }

        if(y==0) {
            check[0]=false; //Can't go UP due to edge
        }

        else if(nBoardPlacement[x][y-1]>0){
            check[0]=false;
        }





        return check;
    }

}
