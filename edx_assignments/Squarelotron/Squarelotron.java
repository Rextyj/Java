
import java.util.*;

public class Squarelotron {

    int[][] squarelotron;
    int size;

    public Squarelotron(int n) {
	int value = 0;//Actual value filled into the array
	squarelotron = new int[n][n];
	size = n;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j < n; j++) {
		value++;
		squarelotron[i][j] = value;
	    }
	}

    }

    public Squarelotron upsideDownFlip(int ring) {
	Squarelotron resultSquare = new Squarelotron(size);
	int numMidCell;
	int[] colInd = new int[] {ring -1, size - 1 - (ring - 1)};
	if (!isEven(size)) { //if the square array has an odd size
	    numMidCell = -1 + (size -1) / 2;
	} else { //even size
	    numMidCell = -1 + size / 2;
	}
	for (int i = ring - 1; i <= size - 1 - (ring - 1); i++) {//col index
	    resultSquare.swapArrayValue(ring -1, size - 1 - (ring - 1), i, i);
	}
	for (int j = (ring -1) + 1; j <= numMidCell; j++){//row index
	    for (int i : colInd ) {
		resultSquare.swapArrayValue(j, size - 1 - (ring - 1) - j, i, i);
	    }
	}
	//	resultArray.swapArrayValue(1, 2, 0, 0);
	return resultSquare;
    }

    public Squarelotron mainDiagonalFlip(int ring) {
	Squarelotron resultSquare = new Squarelotron(size);
	int ringsize = size - (ring -1) * 2;
	int i = ring -1;
	for (int j = ring -1; j <= size -1 - (ring - 1); j++ ) {
	    resultSquare.swapArrayValue(i, j, j, i);
	}
	int j = size -1 - (ring - 1); 
	for (i = ring; i <= ringsize - 2; i++) {
	    resultSquare.swapArrayValue(i, j, j, i);
	}
	return resultSquare;
    }

    public void rotateRight(int numberOfTurns) {
	//if n = 0, do nothing
	if (numberOfTurns > 0) {
	    /*
	     * for cell with coordinates (i,j), it is transformed to 
	     * (j, size-1-i) after a 90 degree rotate
	     */
	    for (int counter = 0; counter < numberOfTurns; counter++) {
		int[][] tempArray = new int[size][size];
		for (int i = 0; i < size; i++) {
		    for (int j = 0; j < size; j++) {
			tempArray[j][size - 1 - i] = squarelotron[i][j];
		    }
		}
		squarelotron = tempArray;
	    }

	} else if (numberOfTurns < 0) {
	    /*
	     * for cell with coordinates (i,j), it is transformed to 
	     * (size-1-j, i) after a -90 degree rotate
	     */
	    for (int counter = 0; counter < numberOfTurns; counter++) {
		int[][] tempArray = new int[size][size];
		for (int i = 0; i < size; i++) {
		    for (int j = 0; j < size; j++) {
			tempArray[size - 1 - j][i] = squarelotron[i][j];	
		    }
		}
		squarelotron = tempArray;
	    }
	}
    }

    public boolean isEven(int num) {
	if (num % 2 == 0) {
	    return true;
	} else {
	    return false;
	}
    }


    //swap two cells
    public void swapArrayValue(int x1, int x2, int y1, int y2) {
	int temp = 0;
	temp = squarelotron[x1][y1];
	squarelotron[x1][y1] = squarelotron[x2][y2];
	squarelotron[x2][y2] = temp;
    }

    public static void main(String[] args) {
	int numRotate;
	int squareSize = 0;
	boolean validCheck = false;
	boolean exitCheck = false;
	int ringNum;
	Squarelotron newSquare;
	Squarelotron resultSquare;
	//initializing 

	do {
	    System.out.println("Please enter the size of the squarelotron. (Max:8)");
	    Scanner userInput = new Scanner(System.in);
	    squareSize = userInput.nextInt();
	    if (squareSize > 0 && squareSize < 9) {
		validCheck = true;
	    } else {
		System.out.println("Please enter a valid size. (Min: 1, Max: 8)");
	    }
	} while (!validCheck);
	newSquare = new Squarelotron(squareSize);

	do {
	    System.out.println("Please select the function you want to carry out.");
	    System.out.println("1. Upside down flip 2. Main diagonal flip 3. Rotation");
	    Scanner userInput = new Scanner(System.in);
	    int menuSel = userInput.nextInt();
	    if (menuSel == -1) {
		exitCheck = true;
	    }

	    switch (menuSel) {
	    case 1: 
		System.out.println("Please enter the ring number you want to perform on");
		ringNum = userInput.nextInt();
		resultSquare = newSquare.upsideDownFlip(ringNum);
		System.out.println("Result after upsideDown at ring " + ringNum);
		for (int[] row : resultSquare.squarelotron) {
		    System.out.println(Arrays.toString(row));
		}
		break;
	    case 2:
		System.out.println("Please enter the ring number you want to perform on");
		ringNum = userInput.nextInt();
		resultSquare = newSquare.mainDiagonalFlip(ringNum);
		System.out.println("Result after MainDiagonalFlip at ring " + ringNum);
		for (int[] row : resultSquare.squarelotron) {
		    System.out.println(Arrays.toString(row));
		}
		break;
	    case 3:
		System.out.println("Please enter the number of times you want to rotate.");
		numRotate = userInput.nextInt();
		newSquare.rotateRight(numRotate % 4);//save time 
		System.out.println("Result after rotating " + numRotate * 90 + " degrees.");
		for (int[] row : newSquare.squarelotron) {
		    System.out.println(Arrays.toString(row));
		}
		break;
	    default: 
		break;
	    }

	} while (!exitCheck);
    }
}
