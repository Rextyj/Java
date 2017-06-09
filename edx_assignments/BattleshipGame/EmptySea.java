
/**
 * Created by Rextyj on 2017/6/2.
 */
public class EmptySea extends Ship {
    public EmptySea() {
        setLength(1);
        setHit(new boolean[1]);
    }
    @Override
    public boolean shootAt(int row, int column) {
        boolean[] hit = new boolean[1];
        hit[0] = true;
        setHit(hit);
        return false; // nothing to shoot at
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    public String toString() {
        if (this.getHit()[0]) {
            return "-";
        } else {
            return "."; // indicate it's empty
        }
    }

    @Override
    public String getShipType() {
        return "empty";
    }

    public static void main(String[] args) {

    }

}
