package MMilitia;
import battlecode.common.*;

public class Building extends Robot {
    
static int numVaporators = 0;
    
    public Building(RobotController r) {
        super(r);
        // building specific setup here
        if ((numVaporators < 2) && (HQ.numMiners == 7) && (DesignSchool.numLandscapers == 4)) {
            for (Direction dir : Util.directions)
                if(tryBuild(RobotType.VAPORATOR, dir)){
                    numVaporators++;
                }
        }
    }

    public void takeTurn() throws GameActionException {
        super.takeTurn();
    }
}
