import java.util.Scanner;

public class MineSweeper {
    static char[][] minefield;

    //It creates a minefield.
    static void crateMinefield() {
        Scanner input = new Scanner(System.in);
        int line, colm;
        //Retrieves data from the user until a valid number of rows and columns is entered.
        do {
            System.out.print("Satır sayısını giriniz: ");
            line = input.nextInt();
            System.out.print("Sütun sayısını giriniz: ");
            colm = input.nextInt();
            if (line < 2 || colm < 2) {
                System.out.println("Geçersiz satır ve sütün girişi yaptınız. Tekrar giriş yapınız.");
            }
        } while (line < 2 || colm < 2);
        //Calculates the number of mines.
        int mineCount = (line * colm) / 4;
        minefield = new char[line][colm];
        //The mines were placed randomly.
        for (int k = 0; k < mineCount; k++) {
            int randomRow = (int) (Math.random() * line);
            int randomColm = (int) (Math.random() * colm);
            if (minefield[randomRow][randomColm] == '*') {
                k--;
                continue;
            }
            minefield[randomRow][randomColm] = '*';
        }
        //It fills the places where there are no mines with the character '-'.
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                if (minefield[i][j] != '*') {
                    minefield[i][j] = '-';
                }

            }
        }
        //Prints the created minefield to the screen.
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                System.out.print(" " + minefield[i][j] + " ");
            }
            System.out.println();
        }
    }

    //The game is run with the method.
    static void runMinefield() {
        Scanner input = new Scanner(System.in);
        int line = minefield.length;
        int colm = minefield[0].length;
        char[][] gameBoard = new char[line][colm];
        boolean[][] repeat = new boolean[line][colm];
        //Fills the game board with the '-' character.
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                gameBoard[i][j] = '-';

            }
        }

        boolean gameOver = false;
        //Moves are taken from the user and the game loop is created.
        while (!gameOver) {
            System.out.println("Oyun Başladı!!!");
            printBoard(gameBoard);
            System.out.println("Hamle yapmak istediğiniz noktayı seçiniz:");
            int row = input.nextInt();
            int column = input.nextInt();
            //Invalid move check.
            if (row < 0 || row >= line || column < 0 || column >= colm) {
                System.out.println("Geçersiz satır veya sütun sayısı.Lütfen yeniden veri giriniz. ");
            }//Point control where a move has been made before.
            if (repeat[row][column]) {
                System.out.println("Bu nokta daha önce kontrol edildi.");
            }//Mine stepping situation.
            if (minefield[row][column] == '*') {
                System.out.println("Mayına bastınız! Oyun bitti. ");
                gameOver = true;
            } else {
                repeat[row][column] = true;
                gameBoard[row][column] = infoMine(row, column);
                //Game win situation.
                if (isWin(gameBoard)) {
                    System.out.println("Oyunu kazandınız.");
                    gameOver = true;
                }
            }


        }
    }

    //Checks if the given game board is completely filled.
    static boolean isWin(char[][] gameBorad) {
        for (char[] row : gameBorad) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    //It checks that the special number of mines are placed correctly in rows and columns.
    static char infoMine(int row, int column) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < minefield.length && j >= 0 && j < minefield[0].length && minefield[i][j] == '*') {
                    count++;
                }

            }
        }
        return (char) (count + '0');
    }

    //Prints the current game board to the screen.
    static void printBoard(char[][] board) {
        System.out.println("Oyun Tahtası:");
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(" " + cell + " ");
            }
            System.out.println();
        }

    }

}
