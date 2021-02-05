// rewrite of Weiss's TicTacToe1   to do chooseMove outside the game class
// This still has printBoard, so needs step 1 fixup
import java.util.Scanner;

public class PlayNim3 {

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;
	static int moves = 0;

	public PlayNim3() {
        g = new Nim3();
        g.init();
	}

	// function to show the instructions of how to play Nim
	private static void showNimGameInstructions() {
		System.out.println(g.help());
	}

	// function to do the computer move
	public void doComputerMove() {
		Best bestMove = g.chooseMove(Nim3.COMPUTER,0);
		g.makeMove(Nim3.COMPUTER, bestMove.row, bestMove.val);
		System.out.println("(COMPUTER DOING MOVE): TOOK " + bestMove.val + " stars out of ROW "+(bestMove.row + 1));
		printBoard();
		return;
	}

	// function to do the human move
	public void doHumanMove() {
		int ROW;
		int SIZE;
		System.out.println("(HUMAN DOING MOVE):");
		do {
			System.out.print("Choose row :"); ROW = scan.nextInt();
			System.out.print("Choose stars to remove :"); SIZE = scan.nextInt();
			if((ROW < 0  || ROW > 2) || (SIZE > 5 || SIZE < 0)) System.out.println("INVALID CHOICES! PLEASE TRY AGAIN!");
		}while((ROW < 0  || ROW > 2) || (SIZE > 5 || SIZE < 0));
		g.makeMove(Nim3.HUMAN, ROW, SIZE);
		printBoard();
		return;
	}

	// verify if the human or the computer has won yet
	public boolean checkAndReportStatus() {
		// if the human or computer has won -- then return false
		if (g.isWin(Nim3.HUMAN) || g.isWin(Nim3.COMPUTER)) {
			System.out.println("Human won? " + g.isWin(Nim3.HUMAN));
			System.out.println("Computer won? " + g.isWin(Nim3.COMPUTER));
			return false;
		}
		System.out.println("Human won? " + g.isWin(Nim3.HUMAN));
		System.out.println("Computer won? " + g.isWin(Nim3.COMPUTER));
		return true;
	}

	// prompt the human and computer to make a move
	public boolean getAndMakeMoves() {
		// let computer go first...
		doComputerMove();
		// System.out.println("count = " + t.getCount());
		if (!checkAndReportStatus())
			return false; // game over
		doHumanMove();
		if (!checkAndReportStatus())
			return false; // game over
		return true;
	}

	// function to print the current board
	public void printBoard() {
		System.out.println(g);
	}

	// function to start the game
	public void playOneGame() {
		boolean continueGame = true;
		// g.clearBoard();

		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayNim3 ui = new PlayNim3();
		showNimGameInstructions();
		System.out.println("Start of game:");
		ui.printBoard();
		ui.playOneGame();
	}

	private static Nim3   g; // g for game
	private Scanner scan = new Scanner(System.in);
}
