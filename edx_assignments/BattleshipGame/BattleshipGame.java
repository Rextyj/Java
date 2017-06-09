
import java.util.*;

/**
 * Created by Rextyj on 2017/6/2.
 */
public class BattleshipGame {

    public static void main(String[] args) {
        int row;
        int col;
        boolean oceanHit;
        Scanner userInput = new Scanner(System.in);
        Ocean newOcean = new Ocean();
        System.out.println("You have started a new game");
        newOcean.placeAllShipsRandomly();
        newOcean.print();//original ocean

        while (!newOcean.isGameOver()) {//looping while the game is not over
            System.out.println("Please enter five coordinates you want to shoot at:");
            System.out.println("The input format should be separated by \";\", for example: 1,2; 3,6; 5,10; 12,9; 0,3");
            String inputString = userInput.nextLine();
            String[] inputCoord = inputString.split("; ");
            String[][] rowColInfo = new String[5][2];
            int[][] rowColCoord = new int[5][2];

            for (int i = 0; i < 5; i++) {
                rowColInfo[i] = inputCoord[i].split(",");
            }

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    rowColCoord[i][j] = Integer.parseInt(rowColInfo[i][j]);//convert to int
                }
            }


            for (int i = 0; i < 5; i++) {
                row = rowColCoord[i][0];
                col = rowColCoord[i][1];

                oceanHit = newOcean.shootAt(row, col);//returns whether the location contains a floating ship & add shots fired and hits
                newOcean.getShipArray()[row][col].shootAt(row, col);//mark the location of the ship as hit
                if (oceanHit) {
                    System.out.println("hit");
                } else {
                    System.out.println("miss");
                }
                if (newOcean.getShipArray()[row][col].isSunk()) { // if the ship has been sunk then we should display the shiptype
                    System.out.println("You have sunk a " + newOcean.getShipArray()[row][col].getShipType());
                }
                System.out.println("You have fired " + newOcean.getShotsFired() + " shots.");
                System.out.println("You have sunk " + newOcean.getShipsSunk() + " ships.");
                System.out.println("The current ocean is:");
                newOcean.print();
            }
        }
    }
}
