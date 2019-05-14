package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class MaxMinPayoff extends Player {
    protected final String newName = "MaxMinPayoff";

    public MaxMinPayoff() {
        super();
        playerName = newName;
    }

    public void initialize() {
        //System.out.println("MaxMinPayoff "+getParameters().getDescription());
    }

    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        //argmax si min s-i ui(s1, s2)
        //max si min s-i ui(s1, s2)

        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));

        double[] minValues = new double[mg.getNumActions(playerNumber)];
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

        ms.setProb(maxmin, 1.0);


        return ms;
    }
}
