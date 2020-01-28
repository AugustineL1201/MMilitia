package MMilitia;
import battlecode.common.*;

public class Landscaper extends Unit {

    public Landscaper(RobotController r) {
        super(r);
    }
    
    static MapLocation myHQLocation = null;

    public void takeTurn() throws GameActionException {
        super.takeTurn();

        // first, save HQ by trying to remove dirt from it
        if (hqLoc != null && hqLoc.isAdjacentTo(rc.getLocation())) {
            Direction dirtohq = rc.getLocation().directionTo(hqLoc);
            if(rc.canDigDirt(dirtohq)){
                rc.digDirt(dirtohq);
            }
        }

      
        
         if(myHQLocation == null){
            RobotInfo[] nearby = rc.senseNearbyRobots(RobotType.LANDSCAPER.sensorRadiusSquared, rc.getTeam());
            for (RobotInfo curr : nearby) {
                if (curr.getType() == RobotType.HQ) {
                    myHQLocation = curr.location;
                }
            }
        }

        MapLocation bestPlaceToBuildWall = null;
        // find best place to build
        if(hqLoc != null) {
            int lowestElevation = 9999999;
            for (Direction dir : Util.directions) {
                MapLocation tileToCheck = hqLoc.add(dir);
                if(rc.getLocation().distanceSquaredTo(tileToCheck) < 4
                        && rc.canDepositDirt(rc.getLocation().directionTo(tileToCheck))) {
                    if (rc.senseElevation(tileToCheck) < lowestElevation) {
                        lowestElevation = rc.senseElevation(tileToCheck);
                        bestPlaceToBuildWall = tileToCheck;
                    }
                }
            }
        }

        if (Math.random() < 0.8){
            // build the wall
            if (bestPlaceToBuildWall != null) {
                rc.depositDirt(rc.getLocation().directionTo(bestPlaceToBuildWall));
                rc.setIndicatorDot(bestPlaceToBuildWall, 0, 255, 0);
                System.out.println("building a wall");
            }
        }

        // otherwise try to get to the hq
        if(hqLoc != null){
            nav.goTo(hqLoc);
        } else {
            nav.goTo(Util.randomDirection());
        }
    }
    
    Direction[] diagonalsArray = {Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHWEST, Direction.SOUTHEAST};
        List<Direction> diagonals = Arrays.asList(diagonalsArray);

        Direction fromHQ = myHQLocation.directionTo(currLocation);
        if (diagonals.contains(fromHQ)) {
            MapLocation rightLocation = myHQLocation.add(fromHQ.rotateRight());
            MapLocation leftLocation = myHQLocation.add(fromHQ.rotateLeft());

            if (rc.canSenseLocation(rightLocation)) {
                if (rc.senseRobotAtLocation(rightLocation) == null) {
                    Direction rightDir = currLocation.directionTo(rightLocation);
                    if (rc.canMove(rightDir)) {
                        rc.move(rightDir);
                    }
                }
            }

    boolean tryDig() throws GameActionException {
        Direction dir;
        if(hqLoc == null){
            dir = Util.randomDirection();
        } else {
            dir = hqLoc.directionTo(rc.getLocation());
        }
        if(rc.canDigDirt(dir)){
            rc.digDirt(dir);
            rc.setIndicatorDot(rc.getLocation().add(dir), 255, 0, 0);
            return true;
        }
        return false;
    }
}
