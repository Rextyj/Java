package whackAMole;
import java.util.*;

public class WhackAMole {
    int score;
    int molesLeft;
    int attempts;
    char[][] moleGrid;

    public WhackAMole(int numAttempts, int gridDimension) {
	attempts = numAttempts;
	moleGrid = new char[gridDimension][gridDimension];
	for (char[] row : moleGrid) {
	    Arrays.fill(row, '*');
	}
	
    }

    public boolean place(int x, int y) {
	if (moleGrid[x][y] != 'M') {
	    moleGrid[x][y] = 'M';
	    molesLeft++;
	    return true;
	} else {
	    return false;
	}	    
    }

    public void whack(int x, int y) {
	if (moleGrid[x][y] == 'M') {
	    score++;
	    molesLeft--;
	    attempts--;
	} else {
	    attempts--;//attempts left is decreased
	}
	moleGrid[x][y] = 'W';
    }

    public void printGridToUser() {
	char[][] gridDup = new char[moleGrid.length][moleGrid.length]; //crate a copy of the array moleGrid, not referencing to the same obj
	for (int i = 0; i < moleGrid.length; i++) {
	    gridDup[i] = Arrays.copyOf(moleGrid[i], moleGrid[i].length);
	}
	for (int x = 0; x < gridDup.length; x++) {
	    for (int y = 0; y < gridDup[0].length; y++) {
		if (gridDup[x][y] != 'W') {
		    gridDup[x][y] = '*';
		} 
	    }
	}
	System.out.println("Current grid: ");
	for (char[] row : gridDup) {
	    System.out.println(row);
	}
    }

    public void printGrid() {
//	for (int x = 0; x < moleGrid.length; x++) {
//	    for (int y = 0; y < moleGrid[0].length; y++) {
//		if (moleGrid[x][y] != 'W' && moleGrid[x][y] != 'M') {
//		    moleGrid[x][y] = '*';
//		} 
//	    }
//	}
	System.out.println("The final grid is:");
	for (char[] row : moleGrid) {
	    System.out.println(row);
	}
    }

    public static void main(String[] args) {
	WhackAMole newGame = new WhackAMole(10, 10);
	int xCoor = 0;
	int yCoor = 0;
	newGame.attempts = 50;
	newGame.score = 0;
	Random randX = new Random();
	Random randY = new Random();

	while (newGame.molesLeft < 10) {
	    xCoor = randX.nextInt(10);
	    yCoor = randY.nextInt(10);
	    newGame.place(xCoor, yCoor);
	}


	while (newGame.attempts > 0 && newGame.molesLeft > 0) {
	    System.out.println("You have " + newGame.attempts + " attempts left.");
	    System.out.println("Enter the coordinate (-1,-1) will terminate the current game.");
	    System.out.println("Please enter x coordinate value between 0 and 9:");
	    Scanner userPosition = new Scanner(System.in);
	    xCoor = userPosition.nextInt();
	    System.out.println("Please enter y coordinate value between 0 and 9:");
	    userPosition = new Scanner(System.in);
	    yCoor = userPosition.nextInt();
	    if (xCoor == -1 && yCoor == -1) {
		System.out.println("The game has ended.");
		newGame.printGrid();
		System.out.println("Your final score is: " + newGame.score);
		break;
	    } else if ((xCoor < 0 || xCoor > 9) || (yCoor < 0 || yCoor > 9)) {
		System.out.println("Please enter a valid coordinate.");
		continue;
	    } else {
		newGame.whack(xCoor, yCoor);
		newGame.printGridToUser();
	    }
	    System.out.println("Your current score is: " + newGame.score);
	}
    }

}
