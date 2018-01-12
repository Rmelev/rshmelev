package ru.job4j.testtask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * BomberMan Game class.
 */
public class Game {
    /**
     * game board.
     */
    private final ReentrantLock[][] board;
    /**
     * pool of threads to execute.
     */
    private ExecutorService poll;
    /**
     * number of Monster thread.
     */
    private static int iM = 0;
    /**
     * number of Wall thread.
     */
    private static int iW = 0;
    /**
     * array of theoretical steps.
     */
    private final int[][] dirArr = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * Constructor.
     * @param monstersNum - number of Monsters.
     * @param wallsNum - number of Walls.
     * @param boardSize - size of square game board.
     */
    private Game(int monstersNum, int wallsNum, int boardSize) {
        poll = Executors.newFixedThreadPool(monstersNum + wallsNum + 1);
        board = new ReentrantLock[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        final Player player = new Player(6, 6);
        poll.execute(player);
        final Monster[] monstersArr = new Monster[monstersNum];
        for (int i = 0; i < monstersNum; i++) {
            monstersArr[i] = new Monster(i * 2 + 3, i * 2 + 3);
            System.out.println("Monster " + (i + 1) + "-й stand on position " + (i * 2 + 3) + " " + (i * 2 + 3) + "  " + board[i][i * 2 + 3]);
            poll.execute(monstersArr[i]);
        }
        final Wall[] wallsArr = new Wall[wallsNum];
        for (int i = 0; i < wallsNum; i++) {
            wallsArr[i] = new Wall(i, i + 2);
            System.out.println("Wall " + (i + 1) + "-й stand on position " + i + " " + (i + 2) + "  " + board[i][i + 2]);
            poll.execute(wallsArr[i]);
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.println(i + "  " + j + "  " + board[i][j]);
            }
        }
    }

    /**
     * Player.
     */
    class Player extends Term {
        /**
         * Constructor.
         * @param x - x position.
         * @param y - y position.
         */
        Player(int x, int y) {
            super(x, y);
        }

        /**
         * array of theoretical steps in string representation (for user manual input).
         */
        private String[] arrMove = {"left", "up", "right", "down"};

        /**
         * method for step in decided direction.
         * @param dir - direction.
         * @param curX - current x position.
         * @param curY - current y position.
         * @return - new position.
         */
        int[] move(String dir, int curX, int curY) {
            int[] newPos = {curX, curY};
            switch (dir) {
                case "left":
                    newPos[0] = curX - 1;
                    newPos[1] = curY;
                    System.out.println("////////// иду влево: " + Thread.currentThread().getName());
                    break;
                case "up":
                    newPos[0] = curX;
                    newPos[1] = curY + 1;
                    System.out.println("//////// иду вверх: " + Thread.currentThread().getName());
                    break;
                case "right":
                    newPos[0] = curX + 1;
                    newPos[1] = curY;
                    System.out.println("///////// иду вправо: " + Thread.currentThread().getName());
                    break;
                case "down":
                    newPos[0] = curX;
                    newPos[1] = curY - 1;
                    System.out.println("////////// иду вниз: " + Thread.currentThread().getName());
                    break;
                default:
                    System.out.println("Неправильный ввод. Попробуйте еще раз.");
            }
            return newPos;
        }

        /**
         * overrided run().
         */
        @Override
        public void run() {
            Thread.currentThread().setName("Player");
            int[] playerNewPos;
            board[super.getX()][super.getY()].lock();
            int newX = super.getX();
            int newY = super.getY();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (newX > board.length - 2) {
                        playerNewPos = this.move("left", newX - 1, newY);
                    } else if (newX < 2) {
                        playerNewPos = this.move("right", newX + 1, newY);
                    } else if (newY > board.length - 2) {
                        playerNewPos = this.move("up", newX, newY - 1);
                    } else if (newY < 2) {
                        playerNewPos = this.move("down", newX, newY + 1);
                    } else {
                        playerNewPos = this.move(arrMove[(int) (Math.random() * 4)], newX, newY);
                    }
                    if (board[newX][newY].isHeldByCurrentThread()) {
                        board[newX][newY].unlock();
                    }
                    newX = playerNewPos[0];
                    newY = playerNewPos[1];

                    if (board[newX][newY].tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.println("//////////// " + Thread.currentThread().getName() + " думает куда пойти");
                        System.out.println(board[newX][newY] + ": newX " + newX + ", newY " + newY);
                        Thread.sleep(3000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                board[newX][newY].unlock();
            }
        }
    }

    /**
     * Monster.
     */
    class Monster extends Term {
        /**
         * Constructor.
         * @param x - x position.
         * @param y - y position.
         */
        Monster(int x, int y) {
            super(x, y);
        }
        /**
         * overrided run().
         */
        @Override
        public void run() {
            int[] r;
            Thread.currentThread().setName("Monster" + iM);
            iM++;
            board[super.getX()][super.getY()].lock();
            int newX = super.getX();
            int newY = super.getY();
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    if (board[newX][newY].isHeldByCurrentThread()) {
                        board[newX][newY].unlock();
                    }
                    r = dirArr[(int) (Math.random() * 4)];
                    newX = Math.abs(Math.min(newX + r[0], 9));
                    newY = Math.abs(Math.min(newY + r[1], 9));
                    if (board[newX][newY].tryLock(500, TimeUnit.MILLISECONDS)) {
                        Thread.sleep(2000);
                        System.out.println("************ " + Thread.currentThread().getName() + " думает куда пойти");
                        System.out.println(board[newX][newY] + ": newX " + newX + ", newY " + newY);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("++++++++++++++++++Монстр вышел из-за закрытия ThreadPool  " + Thread.currentThread().getName());
                e.printStackTrace();
            } finally {
                board[newX][newY].unlock();
            }
        }
    }

    /**
     * Wall.
     */
    class Wall extends Term {
        /**
         * Constructor.
         * @param x - x position.
         * @param y - y position.
         */
        Wall(int x, int y) {
            super(x, y);
        }
        /**
         * overrided run().
         */
        @Override
        public void run() {
            Thread.currentThread().setName("Wall" + iW);
            iW++;
            board[super.getX()][super.getY()].lock();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    board[super.getX()][super.getY()].unlock();
                }
            }
        }
    }

    /**
     * current states of threads (locked/unlocked).
     */
    private void stateOf() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i + "  " + j + "  " + board[i][j]);
            }
        }
    }

    /**
     * main().
     * @param args - args.
     */
    public static void main(String[] args) {
        Game game = new Game(3, 2, 10);
        try {
            Thread.sleep(20000);
            System.out.println(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.poll.shutdownNow();
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Новое состояние: ");
        game.stateOf();
        System.out.println(game.poll.isTerminated());
    }
}