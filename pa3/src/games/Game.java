package games;

interface Game {

    static final int HUMAN = 0;
    static final int COMPUTER = 1;
    static final int HUMAN_WIN =0;
    static final int COMPUTER_WIN = 3;

    void init();
    boolean isADraw();
    int positionValue();
    boolean isAWin(int side);
    String toString();
    Best chooseMove(int side, int depth);
    boolean makeMove(int side, int row, int number);
    
}

