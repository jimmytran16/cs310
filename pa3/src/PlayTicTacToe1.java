import java.util.Scanner;

public class PlayTicTacToe1 {
	public PlayTicTacToe1() {
		g = new TicTacToe1(); //init the TicTacToe1
	}

	// function to do the computer move
	public void doComputerMove() {
		printBoard();
		Best compMove = g.chooseMove(TicTacToe1.COMPUTER, 0);  // depth 0 call
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe1.COMPUTER, compMove.row, compMove.column);
	}

	// function to do the human move
	public void doHumanMove() {
		boolean legal;
		printBoard();
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("column: ");
			int col = scan.nextInt();
			legal = g.playMove(TicTacToe1.HUMAN, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}
	
	// return true if game is continuing, false if done
	public boolean checkAndReportStatus() {
		if (g.isAWin(TicTacToe1.COMPUTER)) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.isAWin(TicTacToe1.HUMAN)) {
			System.out.println("Computer says: You WIN!!");
			return false; // game is done
		}
		if (g.isADraw()) {
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
		System.out.println(g);
	}

	// function to start the game
	public void playOneGame() {
		boolean continueGame = true;
		g.init();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayTicTacToe1 ui = new PlayTicTacToe1();
		ui.playOneGame();
	}

	private TicTacToe1 g; // g for game
	private Scanner scan = new Scanner(System.in);
}
