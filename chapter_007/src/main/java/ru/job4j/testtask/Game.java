package ru.job4j.testtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Game {
    final ReentrantLock[][] board;
    private final Monster[] monstersArr;
    private final Wall[] wallsArr;
    private ExecutorService poll;
    private Player player;

    Game(int monstersNum, int wallsNum, int boardSize) {
        poll = Executors.newFixedThreadPool(monstersNum + wallsNum + 1);
        board = new ReentrantLock[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        player = new Player(1, 1);
        poll.execute(player);
        monstersArr = new Monster[monstersNum];
        for (int i = 0; i < monstersNum; i++) {
            monstersArr[i] = new Monster(i + 2, i + 2);
            poll.execute(monstersArr[i]);
        }
        wallsArr = new Wall[wallsNum];
        for (int i = 0; i < wallsNum; i++) {
            wallsArr[i] = new Wall(i, i + 2);
            System.out.println(i + " " + (i + 2) + "  " + board[i][i + 2]);
            poll.execute(wallsArr[i]);
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.println(i + "  " + j + "  " + board[i][j]);
            }
        }
    }

    class Player extends Term implements Runnable {
        Player(int x, int y) {
            super(x, y);
        }

        @Override
        public void run() {
            Thread.currentThread().setName("Player");
            ReentrantLock tempCell = board[super.getX()][super.getY()];
            tempCell.lock();
            int newX = super.getX();
            int newY = super.getY();
            for (int i = 0; i < 10; i++) {
                newX = newX + (int) (Math.random() * 1.7);
                newY = newY + (int) (Math.random() * 1.7);
                try {
                    tempCell.unlock();
                    if (board[newX][newY].tryLock(500, TimeUnit.MILLISECONDS)) {
                        tempCell = board[newX][newY];
                        tempCell.lock();
                        System.out.println(tempCell + ": newX " + newX + ", newY " + newY);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    tempCell.unlock();
                }
            }
        }
    }

    class Monster extends Term implements Runnable {
        Monster(int x, int y) {
            super(x, y);
        }
        @Override
        public void run() {
            Thread.currentThread().setName("Monster");
            System.out.println("Monster: x " + super.getX() + ", y" + super.getY());
            board[super.getX()][super.getY()].lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board[super.getX()][super.getY()].unlock();
        }
    }

    class Wall extends Term implements Runnable {
        Wall(int x, int y) {
            super(x, y);
        }

        @Override
        public void run() {
            Thread.currentThread().setName("Wall");
            System.out.println("Wall: x " + super.getX() + ", y" + super.getY());
            board[super.getX()][super.getY()].lock();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            board[super.getX()][super.getY()].unlock();
        }
    }

    void stateOf() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(i + "  " + j + "  " + board[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game(2, 2, 5);

        game.poll.shutdownNow();
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Новое состояние: ");
        game.stateOf();
    }
}
