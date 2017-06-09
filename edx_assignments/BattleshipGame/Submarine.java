
/**
 * Created by Rex on 6/3/17.
 */
public class Submarine extends Ship {
    public Submarine() {
        setLength(3);
        setHit(new boolean[3]);
    }
    @Override
    public String getShipType() {
        return "submarine";
    }
}
