// rewrite of Weiss's TicTacToe1   to do chooseMove outside the game class
// This still has printBoard, so needs step 1 fixup
import java.util.Scanner;

public class PlayNim2 {
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;
	static int moves = 0;

	public PlayNim2() {
		// instansiate Nim
        g = new Nim();
		// init() to start the game and populate the board
		g.init();
	}

	// function to do computer move
	public void doComputerMove() {
		System.out.println("(DOING MOVE):");
		System.out.print("Choose row :");int ROW = scan.nextInt();
		System.out.print("Choose number :");int SIZE = scan.nextInt();
		// System.out.println("ROW " + ROW + "  SIZE " + SIZE);
		g.makeMove(Nim.COMPUTER, ROW, SIZE);
		printBoard();
		return;
	}

	// function to go human move
	public void doHumanMove() {
		System.out.println("(DOING MOVE):");
		System.out.print("Choose row :"); int ROW = scan.nextInt();
		System.out.print("Choose number :"); int SIZE = scan.nextInt();
		g.makeMove(Nim.HUMAN, ROW, SIZE);
		printBoard();
		return;
	}

	// verify if the human or the computer has won yet
	boolean checkAndReportStatus() {
		// if the human or computer has won -- then return false
		if(g.isWin(Nim.HUMAN) || g.isWin(Nim.COMPUTER)) {
			System.out.println("Human won? " + g.isWin(Nim.HUMAN));
			System.out.println("Computer won? " + g.isWin(Nim.COMPUTER));
			return false;
		}
		System.out.println("Human won? " + g.isWin(Nim.HUMAN));
		System.out.println("Computer won? " + g.isWin(Nim.COMPUTER));
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

	// function to print the board of the game
	void printBoard() {
		System.out.println(g);
	}

	// function to start the game
	void playOneGame() {
		boolean continueGame = true;
		// g.clearBoard();

		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayNim2 ui = new PlayNim2();
		System.out.println("Start of game:");
		System.out.println(ui.g);
		ui.playOneGame();
	}

	private Nim   g; // g for game
	private Scanner scan = new Scanner(System.in);

}
