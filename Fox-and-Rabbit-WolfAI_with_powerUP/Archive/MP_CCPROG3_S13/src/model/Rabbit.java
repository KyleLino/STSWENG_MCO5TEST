package model;

import java.util.Random;

public class Rabbit {

    final public int nRabbitValuePlacement = 1;// value of rabbit on board
    // 1 - not moved , 2 - moved
    public int nRabbitCount = 3;// total number of rabbits
    public int nBeforeRabbitCount;// condition if the rabbit was/not eaten
    final public int nRabbitMax = 50;//Max number of rabbits
    //public int nRabbitOnBoard;// number of rabbits on the board

    public int prevMove=0;

    //doubles the rabbits
    public void doubles(int nRabbits) {

        nRabbitCount += nRabbits ;

    }

    //finding number of rabbits on the board after resetRabbitValues
    public int findNumberOfRabbits(int[][] nPlacement) {
        int nCount = 0;

        for(int i = 0; i < 20; i ++){
            for(int j = 0; j < 20; j++){
                if(nPlacement[i][j] == nRabbitValuePlacement)
                    nCount ++;
            }
        }
        return nCount;
    }

    //this gets a random number 1-4 to determine the movement of the rabbit
    //edit here
    public int generateAutoNumberMovementForRabbit() {
        Random rand = new Random();
        // generate random number from 1 to 4
        int randomNum = rand.nextInt((4 - 1) + 1) + 1;

        //System.out.println(randomNum);
        return randomNum;
    }

    //resets the values of rabbits that moved/2 back to 1
    //so that the automatic move function for the rabbit will not repeat on the same rabbit because the value would be 2
    public void resetRabbitValuesOnBoard(int[][] nPlacement) {
        for(int j = 0; j < 20; j++){
            for(int k = 0; k < 20; k++){
                if (nPlacement[j][k] == nRabbitValuePlacement + 1){
                    nPlacement[j][k] = nRabbitValuePlacement;
                }
            }
        }
    }

    public int RabbitAdder(int nMoves){
//        double func = (5nMoves)/(nMoves nMoves) + 1;
//        return (int) Math.floor(func);
        /*
        int [] arr = {1, 1, 2, 3, 5, 8};
        if(nMoves > 6)
            return 1;
        else
            return arr[arr.length - nMoves];
         */

        if(nMoves == 5)
            return 6;
        if(nMoves == 10)
            return 5;
        if(nMoves == 15)
            return 4;
        if(nMoves == 20)
            return 3;
        if(nMoves == 25)
            return 2;
//        if(nMoves >= 30)
//            return 1;
        return 1;
    }
}

