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

        /*double[] minValues = new double[mg.getNumActions(playerNumber)];
        int maxmin = 1;
        for (int i = 1; i < mg.getNumActions(playerNumber); i++) {
            double min = Double.MAX_VALUE;
            for (int j = 1; j < mg.getNumActions(playerNumber); j++) {
                int[] actions = {i, j};
                double[] payoffs = mg.getPayoffs(actions);
                if (min > payoffs[playerNumber])
                    min = payoffs[playerNumber];
            }
            minValues[i] = min;
        }
        double temp = Double.MIN_VALUE;

        for (int i = 0; i < minValues.length; i++) {
            if (minValues[i] > temp) {
                temp = minValues[i];
                maxmin = i + 1;
            }
        }

        for (int i = 0; i <= mg.getNumActions(playerNumber); i++)
            ms.setProb(i, 0.0);

        ms.setProb(maxmin, 1.0);*/

        return ms;
    }
}
