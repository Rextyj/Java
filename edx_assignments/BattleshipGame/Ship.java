
/**
 * Created by Rextyj on 2017/6/2.
 */
public abstract class Ship {
    private int bowRow;// the row (0 to 19) which contains the bow (front) of the ship.
    private int bowColumn;// the column which contains the bow (front) of the ship.
    private int length;// the number of squares occupied by the ship. An ”empty sea” location has length 1.
    private boolean horizontal;// true if the ship occupies a single row, false otherwise.
                             // Ships will either be placed vertically or horizontally in the ocean.
    protected boolean[] hit; // this is a boolean array of size 8 that record hits. Only battleships use all the locations.
                             // The others will use fewer.

    // Constructor automatically provided


    public int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    public int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    public boolean[] getHit() {
        return hit;
    }

    public void setHit(boolean[] hit) {
        this.hit = hit;
    }


    //Abstract methods
    abstract String getShipType();

    public boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        for (Ship currentShip : ocean.getShipCollection()) {
            if (horizontal) { //horizontal
                if (column + currentShip.getLength() - 1 < 20) {
                    for (int j = column; j < column + currentShip.getLength(); j++) {
                        if (ocean.isOccupied(row, j)) {
                            return false;
                        }
                    }
                    return !anyAdj(row, column, horizontal, currentShip, ocean);
                } else {
                    return false;
                }

            } else { //vertical
                if (row + currentShip.getLength() - 1 < 20) {
                    for (int i = row; i < row + currentShip.getLength(); i++) {
                        if (ocean.isOccupied(i, column)) {
                            return false;
                        }
                    }
                    return !anyAdj(row, column, horizontal, currentShip, ocean);
                } else {
                    return false;
                }
            }
        }
        return false;//must add this one?
    }

    public boolean anyAdj(int row, int col, boolean horizontal, Ship thisShip, Ocean thisOcean) {
        //no adjacent ship will return false
        /*
            horizontal orientation
         */
        if (horizontal) {
            //checking middle cases
            if (row > 0 && row < 19 && col > 0 && col + thisShip.getLength() - 1 < 19) {
                //checking the
                if (!thisOcean.getShipArray()[row][col - 1].getShipType().equals("empty")  ||
                        !thisOcean.getShipArray()[row][col + thisShip.getLength()].getShipType().equals("empty")) {
                    return true;
                }
                for (int i = row - 1; i <= row + 1; i += 2) {
                    for (int j = col - 1; j <= col + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                }


                //checking edge cases
            } else if (col == 0) {
                if (!thisOcean.getShipArray()[row][col + thisShip.getLength()].getShipType().equals("empty")) {
                    return true;
                }
                if (row == 0) {
                    for (int j = col; j <= col + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[row + 1][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }

                } else if (row == 19) {
                    for (int j = col; j <= col + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[row - 1][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                } else {
                    for (int i = row - 1; i <= row + 1; i += 2) {
                        for (int j = col; j <= col + thisShip.getLength(); j++) {
                            if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                                return true;
                            }
                        }
                    }
                }
                return false;

            } else if (col + thisShip.getLength() - 1 == 19) {
                if (!thisOcean.getShipArray()[row][col - 1].getShipType().equals("empty")) {
                    return true;
                }
                if (row == 0) {
                    for (int j = col - 1; j < col + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[row + 1][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }

                } else if (row == 19) {
                    for (int j = col - 1; j < col + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[row - 1][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                } else {
                    for (int i = row - 1; i <= row + 1; i += 2) {
                        for (int j = col - 1; j < col + thisShip.getLength(); j++) {
                            if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }

            return false;//when nothing is around
        /*
            vertical orientation
         */
        } else {
            //checking middle cases
            if (col > 0 && col < 19 && row > 0 && row + thisShip.getLength() - 1 < 19) {
                //checking the
                if (!thisOcean.getShipArray()[row - 1][col].getShipType().equals("empty") ||
                        !thisOcean.getShipArray()[row + thisShip.getLength()][col].getShipType().equals("empty")) {
                    return true;
                }
                for (int j = col - 1; j <= col + 1; j += 2) {
                    for (int i = row - 1; i <= row + thisShip.getLength(); j++) {
                        if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                }

                //checking edge cases
            } else if (row == 0) {
                if (!thisOcean.getShipArray()[row + thisShip.getLength()][col].getShipType().equals("empty")) {
                    return true;
                }
                if (col == 0) {
                    for (int i = row; i <= row + thisShip.getLength(); i++) {
                        if (!thisOcean.getShipArray()[i][col + 1].getShipType().equals("empty")) {
                            return true;
                        }
                    }

                } else if (col == 19) {
                    for (int i = row; i <= row + thisShip.getLength(); i++) {
                        if (!thisOcean.getShipArray()[i][col - 1].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                } else {
                    for (int j = col - 1; j <= col + 1; j += 2) {
                        for (int i = row; i <= row + thisShip.getLength(); j++) {
                            if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                                return true;
                            }
                        }
                    }
                }
                return false;

            } else if (row + thisShip.getLength() - 1 == 19) {
                if (!thisOcean.getShipArray()[row - 1][col].getShipType().equals("empty")) {
                    return true;
                }
                if (col == 0) {
                    for (int i = row - 1; i < row + thisShip.getLength(); i++) {
                        if (!thisOcean.getShipArray()[i][col + 1].getShipType().equals("empty")) {
                            return true;
                        }
                    }

                } else if (col == 19) {
                    for (int i = row - 1; i < row + thisShip.getLength(); i++) {
                        if (!thisOcean.getShipArray()[i][col - 1].getShipType().equals("empty")) {
                            return true;
                        }
                    }
                } else {
                    for (int j = col - 1; j <= col + 1; j += 2) {
                        for (int i = row; i <= row + thisShip.getLength(); j++) {
                            if (!thisOcean.getShipArray()[i][j].getShipType().equals("empty")) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;//when nothing is around
        }


    }

    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.bowRow = row;
        this.bowColumn = column;
        this.horizontal = horizontal;

        if (horizontal) {
            for (int j = column; j < column + this.length; j++) {
                ocean.getShipArray()[row][j] = this;//set the reference to the current instance
            }
        } else {
            for (int i = row; i < row + this.length; i++) {
                ocean.getShipArray()[i][column] = this;
            }
        }
    }

    public boolean shootAt(int row, int column) {
        int index = Math.abs(row - this.bowRow) + Math.abs(column - this.bowColumn);
        if (!this.getShipType().equals("empty") && !isSunk()) {
            hit[index] = true;
            return true;
        } else {
            return false;// if no condition match return false
        }
    }

    public boolean isSunk() {
        for (boolean thisLocation : this.hit) {
            if (!thisLocation) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        if (isSunk()) {
            return "x";
        } else {
            return "S";
        }
    }


}
