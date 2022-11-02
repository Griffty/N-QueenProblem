import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class mainFrame{
    private final int gridSize;
    ArrayList<String[][]> Answer = new ArrayList<>();
    int QueenAmount = 0;

    mainFrame() {
        System.out.println("Choose grid:\n");
        Scanner sc = new Scanner(System.in);
        gridSize = sc.nextInt();

        for (int r =0; r<gridSize; r++){
            for (int c = 0; c<gridSize; c++){
                boolean ok = true;
                String[][] newBoard = start(r,c);
                for (String[][] strings : Answer) {
                    if (Arrays.deepEquals(strings, newBoard)) {
                        ok = false;
                        break;
                    }
                }
                if (ok){
                    Answer.add(newBoard);
                    if (QueenAmount > getAllQueens(Answer.get(Answer.size()-1))){
                        Answer.remove(Answer.size()-1);
                    }else if (QueenAmount < getAllQueens(Answer.get(Answer.size()-1))){
                        QueenAmount = getAllQueens(Answer.get(Answer.size()-1));
                        Answer.clear();
                        Answer.add(newBoard);
                    }
                }

            }
        }
        System.out.println("The number of queens on the board: " + QueenAmount);
        System.out.println("The number of possible options: " + Answer.size());

        for (String[][] string: Answer){
            boardPrint(string);
        }

    }
    private String[][] start(int initialRow, int initialCol){
        String[][] board = new String[gridSize][gridSize];
        for (int r = 0; r< gridSize; r++){
            for (int c = 0; c< gridSize; c++){
                board[r][c] = "Q";
            }
        }
        setQ(board, initialRow, initialCol);

        goThruAll(board);
        return board;
    }

    private int getAllQueens(String[][] board){
        int QuinAmount = 0;
        for (int r = 0; r< gridSize; r++){
            for (int c = 0; c< gridSize; c++){
                if (board[r][c].equals("Q"))
                    QuinAmount++;
            }
        }
        return QuinAmount;
    }
    private void goThruAll(String[][] board){
        for (int nowR = 0; nowR<gridSize; nowR++){
            for (int nowC = 0; nowC<gridSize; nowC++){
                if (!board[nowR][nowC].equals(".")) {
                    setQ(board, nowR, nowC);
                }
            }
        }
    }
    private void boardPrint(String[][] board){
        for (int i = 0; i < gridSize; i++){
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("----------------");
    }
    private void setQ(String[][] board, int row, int col){
        for (int r = 0; r < gridSize; r++) {
            board[r][col] = ".";
        }
        for (int c = 0; c < gridSize; c++) {
            board[row][c] = ".";
        }
        board[row][col] = "Q";
        for (int i = 1; i < gridSize; i++) {
            if (row + i < gridSize && col + i < gridSize) {
                board[row + i][col + i] = ".";
            }
            if (row - i >= 0 && col - i >= 0) {
                board[row - i][col - i] = ".";
            }
            if (row + i < gridSize && col - i >= 0) {
                board[row + i][col - i] = ".";
            }
            if (row - i >= 0 && col + i < gridSize) {
                board[row - i][col + i] = ".";
            }
        }
    }

    public static void main(String[] args) {
        new mainFrame();
    }
}
