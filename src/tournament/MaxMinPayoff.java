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
        int maxMinIndex = 1;
        for (int row = 1; row < mg.getNumActions(playerNumber); row++) {
            double minValue = Double.MAX_VALUE;
            for (int column = 1; column < mg.getNumActions(playerNumber); column++) {
                int[] actions = {row, column};
                double[] payoffs = mg.getPayoffs(actions);
                if (minValue > payoffs[playerNumber]){
                    minValue = payoffs[playerNumber];
                }
            }
            minValues[row] = minValue;
        }
        double currentMax = Double.MIN_VALUE;

        for (int i = 0; i < minValues.length; i++) {
            if(minValues[i] > currentMax){
                currentMax = minValues[i];
                maxMinIndex = i+1;
            }
        }

        for(int a = 0; a <= mg.getNumActions(playerNumber);a++) {
            ms.setProb(a, 0.0);
        }
        ms.setProb(maxMinIndex, 1.0);


        return ms;
    }
}
