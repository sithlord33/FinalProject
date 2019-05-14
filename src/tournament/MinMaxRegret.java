package tournament;

import games.MatrixGame;
import games.MixedStrategy;

public class MinMaxRegret extends Player {
    protected final String newName = "MinMaxRegret";

    public MinMaxRegret() {
        super();
        playerName = newName;
    }

    public void initialize() {
        //System.out.println("MinMaxRegret "+getParameters().getDescription());
    }

    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        // argmin si max s-i u-i(si, s-i)
        // min si max s-i u-i(si, s-i)

        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));
        int numActions = mg.getNumActions(playerNumber);
        double[][] regretTable = null;
        int minmax;
        double[] best = new double[numActions];
        for (int i = 1; i < numActions; i++) {
            for (int j = 1; j <= numActions; j++) {
                int[] actions = {j, i};
                double[] payoffs = mg.getPayoffs(actions);
                if (best[i] < payoffs[playerNumber])
                    best[i] = payoffs[playerNumber];
            }

            regretTable = new double[mg.getNumActions(playerNumber)][mg.getNumActions(playerNumber)];
            for (int k = 1; k < mg.getNumActions(playerNumber); k++) {
                for (int l = 1; l < mg.getNumActions(playerNumber); l++) {
                    int[] actions = {l, k};
                    double[] payoffs = mg.getPayoffs(actions);
                    regretTable[l][k] = best[k] - payoffs[playerNumber];
                }

            }
        }

        double minMaxRegret = Double.MIN_VALUE;
        int minMaxIndex = 0;
        for (int i = 0; i < regretTable.length; i++) {
            double currentMaxRegret = Double.MIN_VALUE;
            for (int j = 0; j < regretTable[i].length; j++)
                if (currentMaxRegret < regretTable[i][j])
                    currentMaxRegret = regretTable[i][j];
            if (minMaxRegret < currentMaxRegret) {
                minMaxRegret = currentMaxRegret;
                minMaxIndex = i;
            }
        }

        minmax = minMaxIndex + 1;

        for (int i = 0; i <= numActions; i++)
            ms.setProb(i, 0.0);

        ms.setProb(minmax, 1.0);


        return ms;
    }
}
