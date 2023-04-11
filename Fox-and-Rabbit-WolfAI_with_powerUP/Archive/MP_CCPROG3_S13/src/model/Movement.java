package model;

public class Movement {
    //Movements of the rabbit or the fox
    //needs the 2d integer board, animal value (1 or 5), x and y coordinates
    //will not move if on the wall
    public void moveDown(int[][] nPlacement, int nAnimalValue, int x, int y,Fox fox,Wolf wolf) {

        //powerUp!
        if(nAnimalValue==5)
        {
            if(y < 19 && nPlacement[x][y+1]>7)
            {
                fox.pUp=nPlacement[x][y+1];
                nPlacement[x][y+1]=0;
            }
        }

        //for the fox
        if ((nAnimalValue == 5 || nAnimalValue == 7) && y < 19) {
            //kill rabbit
            if((nPlacement[x][y+1]==1 || nPlacement[x][y+1]==0)) {
                if (y < 19 && nPlacement[x][y + 1] == 1) {

                    while (y < 19 && nPlacement[x][y + 1] == 1) {
                        nPlacement[x][y] = 0;
                        nPlacement[x][y + 1] = nAnimalValue;
                        y++;
                        if (nAnimalValue == 5) {
                            fox.FoxY++;
                        } else {
                            wolf.WolfY++;
                        }
                    }

                    if (y < 19) {
                        nPlacement[x][y] = 0;
                        nPlacement[x][y + 1] = nAnimalValue;
                        y++;
                        if (nAnimalValue == 5) {

                            fox.FoxY++;
                        } else {
                            wolf.WolfY++;

                        }
                    }
                } else if (y < 19) {
                    nPlacement[x][y] = 0;
                    nPlacement[x][y + 1] = nAnimalValue;

                    if (nAnimalValue == 5) {
                        y++;
                        fox.FoxY++;
                    } else {
                        y++;
                        wolf.WolfY++;
                    }
                }
            }

        }
        //for the rabbit
        if (nAnimalValue == 1) {
            if (y < 19 && nPlacement[x][y + 1] == 0) {
                nPlacement[x][y] = 0;
                nPlacement[x][y + 1] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2

            }
        }

    }
    public void moveUp(int[][] nPlacement,int nAnimalValue, int x, int y,Fox fox,Wolf wolf) {

        //powerUp!
        if(nAnimalValue==5)
        {
            if(y > 0 && nPlacement[x][y-1]>7)
            {
                fox.pUp=nPlacement[x][y-1];
                nPlacement[x][y-1]=0;
            }
        }
        //for the fox
        if((nAnimalValue == 5 || nAnimalValue == 7) && y > 0) {
            if (nPlacement[x][y - 1] == 1 || nPlacement[x][y - 1] == 0) {
                if (y > 0 && nPlacement[x][y - 1] == 1) {
                    while (y > 0 && nPlacement[x][y - 1] == 1) {
                        nPlacement[x][y] = 0;
                        nPlacement[x][y - 1] = nAnimalValue;
                        y--;
                        if (nAnimalValue == 5) {
                            fox.FoxY--;
                        } else {
                            wolf.WolfY--;
                        }
                    }
                    if (y > 0) {
                        nPlacement[x][y] = 0;
                        nPlacement[x][y - 1] = nAnimalValue;
                        y--;
                        if (nAnimalValue == 5) {
                            fox.FoxY--;
                        } else {
                            wolf.WolfY--;

                        }
                    }
                } else if (y > 0) {
                    nPlacement[x][y] = 0;
                    nPlacement[x][y - 1] = nAnimalValue;

                    if (nAnimalValue == 5) {
                        fox.FoxY--;
                        y--;
                    } else
                        wolf.WolfY--;
                }
            }


        }
        //for the rabbit
        if(nAnimalValue == 1){
            if (y > 0 && nPlacement[x][y - 1] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x][y - 1] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }

    public void moveLeft(int[][] nPlacement,int nAnimalValue, int x, int y,Fox fox,Wolf wolf) {

        if(nAnimalValue==5)
        {
            if(x >0 && nPlacement[x-1][y]>7)
            {
                fox.pUp=nPlacement[x-1][y];
                nPlacement[x-1][y]=0;
            }
        }

        //for the fox
        if((nAnimalValue == 5 || nAnimalValue == 7) && x > 0) {
            if (nPlacement[x - 1][y] == 1 || nPlacement[x - 1][y] == 0) {
                if (x > 0 && nPlacement[x - 1][y] == 1) {
                    while (x > 0 && nPlacement[x - 1][y] == 1) {
                        nPlacement[x][y] = 0;
                        nPlacement[x - 1][y] = nAnimalValue;
                        x--;
                        if (nAnimalValue == 5) {
                            fox.FoxX--;
                        } else
                            wolf.WolfX--;
                    }
                    if (x > 0) {
                        nPlacement[x][y] = 0;
                        nPlacement[x - 1][y] = nAnimalValue;
                        x--;

                        if (nAnimalValue == 5) {
                            fox.FoxX--;
                        } else {
                            wolf.WolfX--;
                        }
                    }
                } else if (x > 0) {
                    nPlacement[x][y] = 0;
                    nPlacement[x - 1][y] = nAnimalValue;
                    x--;
                    if (nAnimalValue == 5) {
                        fox.FoxX--;

                    } else {
                        wolf.WolfX--;
                    }
                }
            }

        }
        //for the rabbit
        if(nAnimalValue == 1){
            if (x > 0 && nPlacement[x - 1][y] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x - 1][y] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }

    public void moveRight(int[][] nPlacement,int nAnimalValue, int x, int y,Fox fox,Wolf wolf) {

        if(nAnimalValue==5)
        {
            if(x < 19 && nPlacement[x+1][y]>7)
            {
                fox.pUp=nPlacement[x+1][y];
                nPlacement[x+1][y]=0;
            }
        }
        //for the fox
        if((nAnimalValue == 5 || nAnimalValue == 7) && x < 19) {

            if (nPlacement[x + 1][y] == 1 || nPlacement[x + 1][y] == 0) {
                if (x < 19 && nPlacement[x + 1][y] == 1) {
                    while (x < 19 && nPlacement[x + 1][y] == 1) {
                        nPlacement[x][y] = 0;
                        nPlacement[x + 1][y] = nAnimalValue;
                        x++;

                        if (nAnimalValue == 5) {
                            fox.FoxX++;
                        } else {
                            wolf.WolfX++;
                        }

                    }
                    if (x < 19) {
                        nPlacement[x][y] = 0;
                        nPlacement[x + 1][y] = nAnimalValue;
                        x++;
                        if (nAnimalValue == 5) {
                            fox.FoxX++;
                        } else {
                            wolf.WolfX++;
                        }
                    }
                } else if (x < 19) {
                    nPlacement[x][y] = 0;
                    nPlacement[x + 1][y] = nAnimalValue;
                    x++;
                    if (nAnimalValue == 5) {
                        fox.FoxX++;
                    } else {
                        wolf.WolfX++;
                    }
                }
            }
        }
        //for the rabbit
        if(nAnimalValue == 1) {
            if (x < 19 && nPlacement[x + 1][y] == 0){
                nPlacement[x][y] = 0;
                nPlacement[x + 1][y] = nAnimalValue + 1;// so that the find rabbit function will not get the rabbit because the value is 2
            }
        }
    }
}