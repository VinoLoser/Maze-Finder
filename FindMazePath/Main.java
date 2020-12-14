import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int start = shrinkCoords(0, 22);
        char[][] array = loadMaze("Maze.txt");
        for (char[] cs : array) {
            for (char c: cs) {
                System.out.print(c);
            }
            System.out.println();
        }
        /*
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(start);
        boolean[][] visited = new boolean[array.length][array[0].length];
        ArrayList<Integer> coorespondingIntegers = new ArrayList<Integer>();
        findFastestRoute(array, queue, visited, coorespondingIntegers);
        */
    }

    private static char[][] loadMaze(String fn) throws FileNotFoundException {
        File file = new File(fn);
        ArrayList<String> res = new ArrayList<String>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                res.add(scanner.nextLine());
            }
        }

        char[][] cs = new char[res.size()][res.get(0).length()];
        for (int i = 0; i < cs.length; ++i) {
            for (int j = 0; j < cs[0].length; ++j) {
                cs[i][j] = res.get(i).charAt(j);
            }
        }
        return cs;
    }

    public static void findFastestRoute(char[][] array, ArrayList<Integer> queue, boolean[][] visited, int[] coorespondingIntegers) {
        int column = queue.get(0)%4;
        int row = queue.get(0)/4;
        visited[column][row] = true;
        up(array, column, row, queue, coorespondingIntegers);  
        down(array, column, row, queue, coorespondingIntegers);
        left(array, column, row, queue, coorespondingIntegers);
        right(array, column, row, queue, coorespondingIntegers);
        queue.remove(0);
        if (queue.size() != 0) {
            findFastestRoute(array, queue, visited, coorespondingIntegers);
        } else {
            return;
        }
    }

    public static boolean isSpace(char[][] array, int column, int row) {
        if (array[column][row] == ' ') {
            return true;
        } else return false;
    }

    public static void up(char[][] array, int column, int row, ArrayList<Integer> queue, int[] coorespondingIntegers) {
        if (row > 0 && isSpace(array, column, row - 1)) {
            int nextPos = shrinkCoords(column, row - 1);
            queue.add(nextPos);
            int startPos = shrinkCoords(row, column);
            coorespondingIntegers[nextPos] = startPos;
        }
    }
    public static void down(char[][] array, int column, int row, ArrayList<Integer> queue, int[] coorespondingIntegers) {
        if (row + 1 < array.length && isSpace(array, column, row + 1)) {
            int nextPos = shrinkCoords(column, row + 1);
            queue.add(nextPos);
            int startPos = shrinkCoords(row, column);
            coorespondingIntegers[nextPos] = startPos;
        }
    }
    public static void left(char[][] array, int column, int row, ArrayList<Integer> queue, int[] coorespondingIntegers) {
        if (column > 0 && isSpace(array, column - 1, row)) {
            int nextPos = shrinkCoords(column - 1, row);
            queue.add(nextPos);
            int startPos = shrinkCoords(row, column);
            coorespondingIntegers[nextPos] = startPos;
        }
    }
    public static void right(char[][] array, int column, int row, ArrayList<Integer> queue, int[] coorespondingIntegers) {
        if (column + 1 < array[0].length && isSpace(array, column + 1, row)) {
            int nextPos = shrinkCoords(column + 1, row);
            queue.add(nextPos);
            int startPos = shrinkCoords(row, column);
            coorespondingIntegers[nextPos] = startPos;
        }
    }
    public static int shrinkCoords(int r, int c) {
        return r*4 + c;
    }
}