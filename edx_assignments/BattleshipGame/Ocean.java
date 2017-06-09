
/**
 * Created by Rextyj on 2017/6/2.
 */
import java.util.*;

public class Ocean {
    private Ship[][] ships = new Ship[20][20];
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private ArrayList<Ship> shipCollection = new ArrayList<>();

    public Ocean() {
        for (int i = 0; i < ships.length; i++) {
            for (int j = 0; j < ships[0].length; j++) {
                ships[i][j] = new EmptySea();
            }
        }

        this.shotsFired = 0;
        this.hitCount = 0;
        this.shipsSunk = 0;

        int count = 0;
        while (count < 4) {

            if (count < 1) {
                shipCollection.add(new BattleShip());
                shipCollection.add(new BattleCruiser());
            }
            if (count < 2) {
                shipCollection.add(new Cruiser());
                shipCollection.add(new LightCruiser());
            }
            if (count < 3) {
                shipCollection.add(new Destroyer());
            }

            shipCollection.add(new Submarine());
            count++;
        }

    }

//


    public ArrayList<Ship> getShipCollection() {
        return shipCollection;
    }

    public Ship[][] getShipArray() {
        return ships;
    }

    public void setShotsFired(int shotsFired) {
        this.shotsFired = shotsFired;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public void setShipsSunk(int shipsSunk) {
        this.shipsSunk = shipsSunk;
    }

    public int getShotsFired() {
        return shotsFired;
    }

    public int getHitCount() {
        return hitCount;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }
//

    public void placeAllShipsRandomly() {
        Random ranRow = new Random();
        Random ranCol = new Random();
        Random ranOrientation = new Random();

        for (Ship currentShip : shipCollection) {
            boolean isValid = false;
            int orit = ranOrientation.nextInt(1);
            while (!isValid) {
                int rowNum = ranRow.nextInt(19);
                int colNum = ranCol.nextInt(19);
                if (orit == 0 && currentShip.okToPlaceShipAt(rowNum, colNum, true, this)) {
                    currentShip.placeShipAt(rowNum, colNum, true, this);
                    isValid = true;
                } else if (orit == 1 && currentShip.okToPlaceShipAt(rowNum, colNum, false, this)) {
                    currentShip.placeShipAt(rowNum, colNum, false, this);
                    isValid = true;
                }

//                if (orit == 0) {//horizontal
//                    if (colNum + currentShip.getLength() - 1 < 20) {
//                        for (int j = colNum; j < colNum + currentShip.getLength(); j++) {
//                            if (isOccupied(rowNum, j)) {
//                                isValid = false;
//
//                            }
//
//                        }
//                        if (!anyAdj(rowNum, colNum, orit, currentShip)) {
//                            isValid = true;
//                            for (int j = colNum; j < colNum + currentShip.getLength(); j++) {
//                                ships[rowNum][j] = currentShip;//place ships!!!
//                            }
//                        }
//                    }
//
//                } else if (orit == 1) { //vertical
//                    if (rowNum + currentShip.getLength() - 1 < 20) {
//                        for (int i = rowNum; i < rowNum + currentShip.getLength(); i++) {
//                            if (isOccupied(i, colNum)) {
//                                isValid = false;
//
//                            }
//                        }
//                        if (!anyAdj(rowNum, colNum, orit, currentShip)) {
//                            isValid = true;
//                            for (int i = rowNum; i < rowNum + currentShip.getLength(); i++) {
//                                ships[i][colNum] = currentShip;
//                            }
//                        }
//
//                    }
//                }
            }
        }
    }

    //check if there is any ship adjacent to the location


    public boolean isOccupied(int row, int column) {
        return !this.ships[row][column].getShipType().equals("empty");
    }

    public boolean shootAt(int row, int column) {
        //also updates the num of shots fired and num of hits
        this.shotsFired++;
        if (ships[row][column].isSunk()) {
            shipsSunk++;
        }

        if (ships[row][column].toString().equals(".") || ships[row][column].toString().equals("x")) {//if the ship is sunk return false
            return false;
        } else {
            ships[row][column].shootAt(row,column);//calls the shoot at method in ships!
            this.hitCount++;//every time it hits a ship part it counted as one hit! even if it is the same part
            return true;
        }

    }


    public boolean isGameOver() {
        for (Ship thisShip : shipCollection) {
            if (!thisShip.isSunk()) {
                return false;
            }

        }
        return true;
    }



    public void print() {
        System.out.println("\".\": Unidentified location   \"-\": Missed shots   \"S\": Landed shots   \"x\": Sunken ship");
        System.out.println("    00  01  02  03  04  05  06  07  08  09  10  11  12  13  14  15  16  17  18  19  ");
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                System.out.print("0" + Integer.toString(i));
            } else {
                System.out.print(Integer.toString(i));
            }
            for (int j = 0; j < 20; j++) {
                int index = Math.abs(i - this.ships[i][j].getBowRow()) + Math.abs(j - this.ships[i][j].getBowColumn());
                if (this.ships[i][j].getShipType().equals("empty")) { // not hit at the location
                    System.out.print("   " + this.ships[i][j].toString());
                } else {
                    if (!this.ships[i][j].getHit()[index]) {
                        System.out.print("   " + ".");
                    } else {
                        System.out.print("   " + this.ships[i][j].toString());
                    }

                }
                if (j == 19) {
                    System.out.print("\n");
                }
            }
        }
    }

//this.ships[i][j].getShipType().equals("empty")

}
