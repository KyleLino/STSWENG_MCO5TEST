package test;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class test {
    Board board = new Board();
    //GameController gamecontroller = new GameController();
    Rabbit rabbit = new Rabbit();
    Movement movement = new Movement();
    Fox fox = new Fox();
    Wolf wolf = new Wolf();

    @Test //1.1
    void testWolfMoveDown() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[2][19] = 7;
        wolf.WolfX = 2;
        wolf.WolfY = 19;
        movement.moveDown(board.nBoardPlacement, wolf.nWolfValuePlacement, wolf.WolfX, wolf.WolfY, fox, wolf);
        assertEquals(19, wolf.WolfY);
    }

    @Test //1.2
    void testWolfMoveUp() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[2][0] = 7;
        wolf.WolfX = 2;
        wolf.WolfY = 0;
        movement.moveUp(board.nBoardPlacement, wolf.nWolfValuePlacement, wolf.WolfX, wolf.WolfY, fox, wolf);
        assertEquals(0, wolf.WolfY);
    }

    @Test //1.3
    void testWolfMoveLeft() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[2][0] = 7;
        wolf.WolfX = 0;
        wolf.WolfY = 0;
        movement.moveLeft(board.nBoardPlacement, wolf.nWolfValuePlacement, wolf.WolfX, wolf.WolfY, fox, wolf);
        assertEquals(0, wolf.WolfX);
    }

    @Test //1.4
    void testWolfMoveRight() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[19][4] = 7;
        wolf.WolfX = 19;
        wolf.WolfY = 4;
        movement.moveRight(board.nBoardPlacement, wolf.nWolfValuePlacement, wolf.WolfX, wolf.WolfY, fox, wolf);
        assertEquals(19, wolf.WolfX);
    }

    @Test //2
    void testWolfSkipTile() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[0][0] = 7;
        board.nBoardPlacement[1][0] = 1;
        wolf.WolfX = 0;
        wolf.WolfY = 0;
        movement.moveRight(board.nBoardPlacement, wolf.nWolfValuePlacement, wolf.WolfX, wolf.WolfY, fox, wolf);
        //System.out.println(wolf.WolfX);
        //System.out.println(wolf.WolfY);
        assertTrue((2 == wolf.WolfX )&&(0 == wolf.WolfY));
    }

    @Test //3
    void testFindAllRabbits(){
        board.InitializenBoardPlacement();
        board.nBoardPlacement[2][3] = 1;
        board.nBoardPlacement[4][6] = 1;
        int[][] rabbits= board.findAllRabbits(2);
        assertTrue((2 == rabbits[0][0] )&&(3 == rabbits[0][1])&&(4 == rabbits[1][0])&&(6 == rabbits[1][1]));

    }

    @Test //4.1
    void testRabbitMoveDown() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[0][19] = 1;
        movement.moveDown(board.nBoardPlacement, rabbit.nRabbitValuePlacement, 0, 19, fox, wolf);
        int[][] rabbits= board.findAllRabbits(1);
        assertEquals(19, rabbits[0][1]);
    }

    @Test //4.2
    void testRabbitMoveUp() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[0][0] = 1;
        movement.moveUp(board.nBoardPlacement, rabbit.nRabbitValuePlacement, 0, 0, fox, wolf);
        int[][] rabbits= board.findAllRabbits(1);
        assertEquals(0, rabbits[0][1]);
    }

    @Test //4.3
    void testRabbitMoveLeft() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[0][0] = 1;
        movement.moveLeft(board.nBoardPlacement, rabbit.nRabbitValuePlacement, 0, 0, fox, wolf);
        int[][] rabbits= board.findAllRabbits(1);
        assertEquals(0, rabbits[0][0]);
    }

    @Test //4.4
    void testRabbitMoveRight() {
        board.InitializenBoardPlacement();
        board.nBoardPlacement[19][0] = 1;
        movement.moveRight(board.nBoardPlacement, rabbit.nRabbitValuePlacement, 19, 0, fox, wolf);
        int[][] rabbits= board.findAllRabbits(1);
        assertEquals(19, rabbits[0][0]);
    }

    @Test //7.1
    void testRabbitSpawn6() {
        assertEquals(6, rabbit.RabbitAdder(5));
    }

    @Test //7.2
    void testRabbitSpawn1() {
        assertEquals(1, rabbit.RabbitAdder(30));
    }
}
