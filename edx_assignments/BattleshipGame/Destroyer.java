
/**
 * Created by Rex on 6/3/17.
 */
public class Destroyer extends Ship {
    public Destroyer() {
        setLength(4);
        setHit(new boolean[4]);
    }

    @Override
    public String getShipType() {
        return "destroyer";
    }
}
