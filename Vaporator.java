package lectureplayer;
import battlecode.common.*;

public class Vaporator extends Building {
    static int numVaporators = 0;
    public Vaporator(RobotController r) {
        super(r);
    }
    
    if(numVaporators < 2) && (numMiners == 7) && (numLandscapers == 4) {
            for (Direction dir : Util.directions)
                if(tryBuild(RobotType.VAPORATOR, dir)){
                    numMiners++;
                }
        }
}
