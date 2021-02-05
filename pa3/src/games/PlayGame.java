package games;

import java.util.*;

public class PlayGame {

    private final static String DEFAULT_GAME = "TicTacToe";
    private final static String NIM_GAME = "Nim";
    private static Game game; // game
    private final static Scanner scan = new Scanner(System.in);
    private String CURRENT_GAME;

    public PlayGame(String CHOSEN_GAME) {
        // conditions to check which game is chosen
        if (CHOSEN_GAME.equals(NIM_GAME))
            game = new Nim4();
        else
            game = new TicTacToe4();

        this.CURRENT_GAME = CHOSEN_GAME;
    }

    // function to do the computer move
    public void doComputerMove() {
        printBoard();
        Best compMove = game.chooseMove(Game.COMPUTER, 0); // depth 0 call
        System.out.println("Computer plays ROW = " + compMove.row + (CURRENT_GAME.equals(DEFAULT_GAME) ? " COL = " : " STARS = ") + compMove.column);
        game.makeMove(Game.COMPUTER, compMove.row, compMove.column);
    }

    // function to do the human move
    public void doHumanMove() {
        boolean legal;
        printBoard();
        if (CURRENT_GAME.equals(NIM_GAME)) {
            int ROW;
            int SIZE;
            System.out.println("(HUMAN DOING MOVE):"); 
            do {
                System.out.print("Choose row :");
                ROW = scan.nextInt();
                System.out.print("Choose stars to remove :");
                SIZE = scan.nextInt();
                if ((ROW < 0 || ROW > 2) || (SIZE > 5 || SIZE < 0))
                    System.out.println("INVALID CHOICES! PLEASE TRY AGAIN!");
            } while ((ROW < 0 || ROW > 2) || (SIZE > 5 || SIZE < 0));
            game.makeMove(Game.HUMAN, ROW, SIZE);
            printBoard();
        } else {
            do {
                System.out.println("row: ");
                int row = scan.nextInt();
                System.out.println("column: ");
                int col = scan.nextInt();
                legal = game.makeMove(Game.HUMAN, row, col);
                if (!legal)
                    System.out.println("Illegal move, try again");
            } while (!legal);
        }
    }

    // return true if game is continuing, false if done
    public boolean checkAndReportStatus() {
        if (game.isAWin(Game.COMPUTER)) {
            System.out.println("Computer says: I WIN!!");
            return false; // game is done
        }
        if (game.isAWin(Game.HUMAN)) {
            System.out.println("Computer says: You WIN!!");
            return false; // game is done
        }
        if (game.isADraw()) {
            System.out.println(" Game is a DRAW");
            return false;
        }
        System.out.println("game continuing");
        return true;
    }

    // do one round of playing the game, return true at end of game
    public boolean getAndMakeMoves() {
        // let computer go first...
        doComputerMove();
        System.out.println("back from doComputerMove");
        // System.out.println("count = " + t.getCount());
        if (!checkAndReportStatus())
            return false; // game over
        doHumanMove();
        if (!checkAndReportStatus())
            return false; // game over
        return true;
    }

    public void printBoard() {
        System.out.println(game);
    }

    // function to start the game
    public void playOneGame() {
        boolean continueGame = true;
        game.init();
        while (continueGame) {
            continueGame = getAndMakeMoves();
        }
    }

    public static void main(String args[]) {
        System.out.println(Arrays.toString(args));

        // set the chosen game to be the default which is TicTacToe
        String CHOSEN_GAME = DEFAULT_GAME;

        // check to see if there is an argument passed in
        if (!(args.length == 0)) {
            if (args[0].equals(NIM_GAME))
                CHOSEN_GAME = NIM_GAME;
            else{
                System.out.println("Please pass in the right valid arguments for playing Nim (For example: java games.PlayGame Nim or just java games.PlayGame for tic tac toe)");
                return;
            }
        }

        // instansiate the game
        PlayGame ui = new PlayGame(CHOSEN_GAME);
        ui.playOneGame();

    }

}
