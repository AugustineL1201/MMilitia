package MMilitia;
import battlecode.common.*;

public class Refinery extends Building {
    public Refinery(RobotController r) throws GameActionException {
        super(r);
        comms.broadcastRefineryCreation(rc.getLocation());
    }
}
