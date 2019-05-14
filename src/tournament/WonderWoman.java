package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class WonderWoman extends Player{
    protected final String newName = "WonderWoman";

    public WonderWoman() {
        super();
        playerName = newName;
    }

    public void initialize() {
        //System.out.println("MaxMinPayoff "+getParameters().getDescription());
    }

    //Repeated games with uncertainty
    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));

        return ms;
    }
}
