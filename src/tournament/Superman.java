package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class Superman extends Player{
    protected final String newName = "Superman";

    public Superman() {
        super();
        playerName = newName;
    }

    public void initialize() {
        //System.out.println("MaxMinPayoff "+getParameters().getDescription());
    }

    //One-shot games with uncertainty
    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));

        return ms;
    }
}
