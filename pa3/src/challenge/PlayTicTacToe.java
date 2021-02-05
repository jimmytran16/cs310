package challenge;

// rewrite of Weiss's TicTacToe to do chooseMove outside the game class
// This still has printBoard, so needs step 1 fixup
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PlayTicTacToe {
	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;
	static int moves = 0;

	public PlayTicTacToe() {
		g = new TicTacToe();
	}

	// Find optimal move
	public Best chooseMove(TicTacToe g, int side, int depth) {
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
		TicTacToe trial = g.clone(); // take a snapshot of the board's value
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
//		if (g.count() != depth) {  // added method to TicTacToe just for this
//			System.out.println("wrong no. marks for depth " + depth + "at move " + moves);
//			printBoard();
//		}
		store.put(g.clone(), value); // store snapshot of g and its value
		return new Best(value, bestRow, bestColumn);
	}

	public void doComputerMove() {
		printBoard();
		Best compMove = chooseMove(g, TicTacToe.COMPUTER, 0); // level 0 call
		// System.out.println("after chooseMove: ");
		// printBoard();
		System.out.println("Computer plays ROW = " + compMove.row + " COL = " + compMove.column);
		g.playMove(TicTacToe.COMPUTER, compMove.row, compMove.column);
	}

	public void doHumanMove() {
		boolean legal;
		printBoard();
		do {
			System.out.println("row: ");
			int row = scan.nextInt();
			System.out.println("column: ");
			int col = scan.nextInt();
			legal = g.playMove(TicTacToe.HUMAN, row, col);
			if (!legal)
				System.out.println("Illegal move, try again");
		} while (!legal);
	}

	// return true if game is continuing, false if done
	boolean checkAndReportStatus() {
		if (g.isAWin(TicTacToe.COMPUTER)) {
			System.out.println("Computer says: I WIN!!");
			return false; // game is done
		}
		if (g.isAWin(TicTacToe.HUMAN)) {
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
		// TODO: stop using this encapsulation-busting method
		int[][] board = g.getBoard();
		// TODO: move this code into TicTacToe2's toString--
		StringBuilder boardStr = new StringBuilder("");
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				String spot;
				switch (board[row][col]) {
				case TicTacToe.HUMAN:
					spot = " " + humanSide + " ";
					break;
				case TicTacToe.COMPUTER:
					spot = " " + computerSide + " ";
					break;
				case TicTacToe.EMPTY:
					spot = "   ";
					break;
				default:
					System.out.println("Bad board entry in printBoard");
					return;
				}
				boardStr.append(spot);
				if (col < 2)
					boardStr.append("|");
			}
			if (row < 2)
				boardStr.append("\n-----------\n");
		}
		System.out.println(boardStr);

	}

	void playOneGame() {
		boolean continueGame = true;
		g.clearBoard();
		while (continueGame) {
			continueGame = getAndMakeMoves();
		}
	}

	public static void main(String[] args) {
		PlayTicTacToe ui = new PlayTicTacToe();
		ui.playOneGame();
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue(TicTacToe g) {
		return g.isAWin(COMPUTER) ? COMPUTER_WIN : g.isAWin(HUMAN) ? HUMAN_WIN : g.isADraw() ? DRAW : UNCLEAR;
	}

	private Map<TicTacToe, Integer> store = new HashMap<TicTacToe, Integer>();
	private TicTacToe g; // g for game
	private Scanner scan = new Scanner(System.in);
	private String computerSide = "O";
	private String humanSide = "X";

}
