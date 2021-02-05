package challenge;
//import java.util.HashMap;
//import java.util.Map;

public class TicTacToe {

	public static final int HUMAN = 0;
	public static final int COMPUTER = 1;
	public static final int EMPTY = 2;

	public static final int HUMAN_WIN = 0;
	public static final int DRAW = 1;
	public static final int UNCLEAR = 2;
	public static final int COMPUTER_WIN = 3;

	// Constructor
	public TicTacToe() {
		clearBoard();
	}

	public int[][] getBoard() {
		return board;
	}

	// chooseMove: find optimal move: now in PlayTicTacToe

	// Play move, including checking legality
	public boolean playMove(int side, int row, int column) {
		if (row < 0 || row >= 3 || column < 0 || column >= 3 || board[row][column] != EMPTY)
			return false;
		board[row][column] = side;
		return true;
	}

	// Simple supporting routines
	public void clearBoard() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				board[i][j] = EMPTY;
	}

	// added by eoneil: more general-purpose than "boardIsFull"
	public boolean isADraw() {
		return !isAWin(HUMAN)&&!isAWin(COMPUTER)&& boardIsFull();
	}
	
	public boolean isAWin(int side) {
		int row, column;

		/* Look for all in a row */
		for (row = 0; row < 3; row++) {
			for (column = 0; column < 3; column++)
				if (board[row][column] != side)
					break;
			if (column >= 3)
				return true;
		}

		/* Look for all in a column */
		for (column = 0; column < 3; column++) {
			for (row = 0; row < 3; row++)
				if (board[row][column] != side)
					break;
			if (row >= 3)
				return true;
		}

		/* Look on diagonals */
		if (board[1][1] == side && board[2][2] == side && board[0][0] == side)
			return true;

		if (board[0][2] == side && board[1][1] == side && board[2][0] == side)
			return true;

		return false;
	}
	// added by eoneil for integrity check in PlayTicTacToe, for debugging (see commented-out code)
//	public int count() {
//		int count = 0;
//		for (int i = 0; i < 3; i++)
//			for (int j = 0; j < 3; j++)
//				if (board[i][j] != EMPTY)
//					count++;	
//		return count;
//	}
	public boolean equals(Object rhs) {
		if (!(rhs instanceof TicTacToe))
			return false;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] != ((TicTacToe) rhs).board[i][j])
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
	
	// copy board (deep copy, i.e. copy array content) to new TicTacToe object
	// This action parallels the creation of a Position object in old code
	public TicTacToe clone() {
		TicTacToe clone = new TicTacToe();  // creates new array
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)   // copy elements to new array
				clone.board[i][j] = board[i][j];
		return clone;
	}
	
	private int[][] board = new int[3][3];
	// private Map<TicTacToe, Integer> store = new HashMap<TicTacToe, Integer>();
	
	// made private by eoneil: now a helper to isADraw of public API
	private boolean boardIsFull() {
		for (int row = 0; row < 3; row++)
			for (int column = 0; column < 3; column++)
				if (board[row][column] == EMPTY)
					return false;
		return true;
	}
		// These methods are no longer in use
	// Play a move, possibly clearing a square
//	private void place(int row, int column, int piece) {
//		board[row][column] = piece;
//	}
//
//	private boolean squareIsEmpty(int row, int column) {
//		return board[row][column] == EMPTY;
//	}
//
//	// Compute static value of current position (win, draw, etc.)
//	private int positionValue() {
//		return isAWin(COMPUTER) ? COMPUTER_WIN : isAWin(HUMAN) ? HUMAN_WIN : boardIsFull() ? DRAW : UNCLEAR;
//	}
}
