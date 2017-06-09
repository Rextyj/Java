
/**
 * Created by Rex on 6/3/17.
 */
public class LightCruiser extends Ship {
    public LightCruiser() {
        setLength(5);
        setHit(new boolean[5]);
    }

    @Override
    public String getShipType() {
        return "lightcruiser";
    }
}
