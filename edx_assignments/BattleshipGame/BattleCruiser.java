
/**
 * Created by Rextyj on 2017/6/2.
 */
public class BattleCruiser extends Ship {

    public BattleCruiser() {
        setLength(7);
        setHit(new boolean[7]);
    }

    @Override
    public String getShipType() {
        return "battlecruiser";
    }
}
