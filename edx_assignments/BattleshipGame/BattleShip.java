
/**
 * Created by Rextyj on 2017/6/2.
 */
public class BattleShip extends Ship {
//    private int length = 7;
//    private boolean[] hit;
    public BattleShip() {
        setLength(8);
        setHit(new boolean[8]);
    }

    @Override
    public String getShipType() {
        return "battleship";
    }
}
