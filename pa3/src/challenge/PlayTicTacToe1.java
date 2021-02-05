package challenge;

// rewrite of Weiss's TicTacToe1   to do chooseMove outside the game class
// This still has printBoard, so needs step 1 fixup
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayTicTacToe1 {
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;
	static int moves = 0;

	public PlayTicTacToe1() {
		g = new TicTacToe1();
	}

	// the position inner class Part 1
	final private class Position {
		private int[][] board;
	
		public Position(int[][] theBoard) {
			board = new int[3][3];
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					board[i][j] = theBoard[i][j];
		}
	
		public boolean equals(Object rhs) {
			if (!(rhs instanceof Position))
				return false;
	
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					if (board[i][j] != ((Position) rhs).board[i][j])
						return false;
			return true;
		}
	
		public int hashCode() {
			int hashVal = 0;
	
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
					hashVal = hashVal * 4 + board[i][j];
	
			return hashVal;
		}
	}

	// Find optimal move
	public Best chooseMove(TicTacToe1   g, int side, int depth) {
		moves++;
		int opp; // The other side
		Best reply; // Opponent's best reply
		int simpleEval; // Result of an immediate evaluation
		int bestRow = -1; // Initialize running value with out-of-range value
		int bestColumn = -1;
		int value;
		// Position thisPosition = new Position(board);
		if ((simpleEval = positionValue(g)) != UNCLEAR)
			return new Best(simpleEval);

		// Don't look up top-level value: at top level, we need to explore moves
		// out from here to find the best move to make)
		if (depth > 0) {
			Integer lookupVal = store.get(g);
			if (lookupVal != null)
				return new Best(lookupVal);
		}

		// Initialize running values with out-of-range values (good software practice)
		// Here also to ensure that *some* move is chosen, even if a hopeless case
		if (side == COMPUTER) {
			opp = HUMAN;
			value = HUMAN_WIN - 1; // impossibly low value
		} else {
			opp = COMPUTER;
			value = COMPUTER_WIN + 1; // impossibly high value
		}
		// about to make moves, make copy to work on, leave g alone
		TicTacToe1   trial = g.clone(); // take a snapshot of the board's value
		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++) {
				if (!trial.playMove(side, row, column)) // try move to see effect
					continue; // bad move, go on
				reply = chooseMove(trial, opp, depth + 1);
				trial = g.clone(); // undo move
				// Update if side gets better position
				if (side == COMPUTER && reply.val > value || side == HUMAN && reply.val < value) {
					value = reply.val;
					bestRow = row;
					bestColumn = column;
				}
			}
		// The following code was used during debugging this code
//		if (depth < 3 || moves < 14) {
//			System.out.println("at depth " + depth + " storing " + value + "for: ");
//			printBoard();
//		}
//		if (g.count() != depth) {  // added method to TicTacToe1   just for this
//			System.out.println("wrong no. marks for depth " + depth + "at move " + moves);
//			printBoard();
//		}
		store.put(g.clone(), value); // store snapshot of g and its value
		return new Best(value, bestRow, bestColumn);
	}

	public void doComputerMove() {
		printBoard();
		Best compMove = chooseMove(g, TicTacToe1.COMPUTER, 0); // level 0 call
		// System.out.println("after chooseMove: ");
		// printBoard();
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe1.COMPUTER, compMove.row, compMove.column);
	}

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
	boolean checkAndReportStatus() {
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
		System.out.println(g);
		// System.out.println("count = " + t.getCount());
		if (!checkAndReportStatus())
			return false; // game over
		doHumanMove();
		if (!checkAndReportStatus())
			return false; // game over
		return true;
	}

	void printBoard() {
		System.out.println("PRINT BOARD");
		System.out.println(g.toString());
	}

	void playOneGame() {
		boolean continueGame = true;
		g.clearBoard();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayTicTacToe1 ui = new PlayTicTacToe1();
		ui.playOneGame();
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue(TicTacToe1   g) {
		return g.isAWin(COMPUTER) ? COMPUTER_WIN : g.isAWin(HUMAN) ? HUMAN_WIN : g.isADraw() ? DRAW : UNCLEAR;
	}

	private Map<TicTacToe1, Integer> store = new HashMap<TicTacToe1, Integer>();
	private TicTacToe1   g; // g for game
	private Scanner scan = new Scanner(System.in);
	// private String computerSide = "O";
	// private String humanSide = "X";

}
