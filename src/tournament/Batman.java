package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class Batman extends Player {
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

        for (int i = 0; i < mg.getNumActions(playerNumber); i++)
            ms.setProb(i, 0.0);
        int numActions = mg.getNumActions(playerNumber);
        double[] best = new double[numActions];
        double max;
        for (int i = 0; i < numActions; i++) {
            max = mg.getExtremePayoffs()[0];
            best[i] = max;
        }
        max = Double.MIN_VALUE;
        for (int i = 0; i < best.length; i++) {
            if (best[i] > max) {
                max = best[i];
                ms.setProb(i, 1);
            }
            else
                ms.setProb(i, 0);
        }

        return ms;
    }
}
