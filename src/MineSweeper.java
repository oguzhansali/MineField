import java.util.Scanner;

public class MineSweeper {
    static char[][] minefield;

    static void crateMinefield() {
        Scanner input = new Scanner(System.in);
        int line, colm;
        do {
            System.out.print("Satır sayısını giriniz: ");
            line = input.nextInt();
            System.out.print("Sütun sayısını giriniz: ");
            colm = input.nextInt();
            if (line < 2 || colm < 2) {
                System.out.println("Geçersiz satır ve sütün girişi yaptınız. Tekrar giriş yapınız.");
            }
        } while (line < 2 || colm < 2);

        int mineCount = (line * colm) / 4;
        minefield = new char[line][colm];

        for (int k = 0; k < mineCount; k++) {
            int randomRow = (int) (Math.random() * line);
            int randomColm = (int) (Math.random() * colm);
            if (minefield[randomRow][randomColm] == '*') {
                k--;
                continue;
            }
            minefield[randomRow][randomColm] = '*';
        }
        /*
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                    minefield[i][j]='-';
            }
        }
        */
        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                System.out.print(" " + minefield[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void runMinefield() {
        Scanner input = new Scanner(System.in);
        int line = minefield.length;
        int colm = minefield[0].length;
        char[][] gameBoard = new char[line][colm];
        boolean[][] repeat = new boolean[line][colm];

        for (int i = 0; i < line; i++) {
            for (int j = 0; j < colm; j++) {
                gameBoard[i][j] = '-';

            }
        }

        boolean gameOver = false;

        while (!gameOver) {
            System.out.println("Oyun Başladı!!!");
            printBoard(gameBoard);
            System.out.print("Hamle yapmak istediğiniz noktayı seçiniz:");
            int row = input.nextInt();
            int column = input.nextInt();

            if (row < 0 || row >= line || column < 0 || column >= colm) {
                System.out.println("Geçersiz satır veya sütun sayısı.Lütfen yeniden veri giriniz. ");
            }
            if (repeat[row][column]) {
                System.out.println("Bu nokta daha önce kontrol edildi.");
            }
            if (minefield[row][column] == '*') {
                System.out.println("Mayına bastınız! Oyun bitti. ");
                gameOver = true;
            } else {
                repeat[row][column] = true;
                gameBoard[row][column] = infoMine(row, column);
                if (isWin(gameBoard)) {
                    System.out.println("Oyunu kazandınız.");
                    gameOver = true;
                }
            }


        }
    }

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
