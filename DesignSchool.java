package lectureplayer;
import battlecode.common.*;

public class DesignSchool extends Building {
    static int numLandscapers = 0;
    
    public DesignSchool(RobotController r) {
        super(r);
    }

    public void takeTurn() throws GameActionException {
        super.takeTurn();
        
        // will only actually happen if we haven't already broadcasted the creation
        comms.broadcastDesignSchoolCreation(rc.getLocation());
        
        if (numLandscapers < 12) {
        for (Direction dir : Util.directions) {
            if(tryBuild(RobotType.LANDSCAPER, dir)) {
                System.out.println("made a landscaper");
            }
        }
        }
    }
}
