package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class Batman extends Player{
    protected final String newName = "Batman";

    public Batman() {
        super();
        playerName = newName;
    }

    public void initialize() {
        //System.out.println("MaxMinPayoff "+getParameters().getDescription());
    }

    //One-shot games with no uncertainty
    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));

        int numActions = mg.getNumActions(playerNumber);
        double[] best = new double[numActions];
        double max = Double.MIN_VALUE;
        for (int i = 0; i < numActions; i++){}

        return ms;
    }
}
