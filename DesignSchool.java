package MMilitia;
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
        numLandscapers += comms.getLandscaperCount();
        
        if (numLandscapers < 5) {
        for (Direction dir : Util.directions) {
            if(tryBuild(RobotType.LANDSCAPER, dir)) {
                numLandscapers++;
                System.out.println("made a landscaper");
                
            }
        }
        }
    }
}
