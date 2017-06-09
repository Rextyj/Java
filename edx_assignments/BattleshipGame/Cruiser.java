
/**
 * Created by Rex on 6/3/17.
 */
public class Cruiser extends Ship {
    public Cruiser() {
        setLength(6);
        setHit(new boolean[6]);
    }

    @Override
    public String getShipType() {
        return "cruiser";
    }
}
