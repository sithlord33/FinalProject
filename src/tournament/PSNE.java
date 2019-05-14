package tournament;

import games.MatrixGame;
import games.MixedStrategy;
import games.OutcomeIterator;

public class PSNE extends Player {
    protected final String newName = "PSNE";

    public PSNE() {
        super();
        playerName = newName;
    }

    public void initialize() {
        // System.out.println("PSNE"+getParameters().getDescription());
    }

    protected MixedStrategy solveGame(MatrixGame mg, int playerNumber) {
        MixedStrategy ms = new MixedStrategy(mg.getNumActions(playerNumber));

        boolean[][] p1DominantCoordinates = new boolean[mg.getNumActions(playerNumber)][mg.getNumActions(playerNumber)]; //p1disbig
        boolean[][] p2DominantCoordinates = new boolean[mg.getNumActions(playerNumber)][mg.getNumActions(playerNumber)]; //p2disbig

        for (int row = 1; row < mg.getNumActions(playerNumber); row++) {
            double p1CurrentMax = Double.MIN_VALUE;
            double p2CurrentMax = Double.MIN_VALUE;

            for (int column = 1; column < mg.getNumActions(playerNumber); column++) {
                int[] p1Actions = {row, column};
                int[] p2Actions = {column, row};
                double[] payoffs = mg.getPayoffs(p1Actions);
                if (p1CurrentMax < payoffs[playerNumber]){
                    p1CurrentMax = payoffs[playerNumber];
                }
                payoffs = mg.getPayoffs(p2Actions);
                if (p2CurrentMax < payoffs[getOpponentNumber()-1]){
                    p2CurrentMax = payoffs[getOpponentNumber()-1];
                }
            }

            for (int column = 1; column < mg.getNumActions(playerNumber); column++) { //populates boolean 2d array
                int[] p1Actions = {row, column};
                int[] p2Actions = {column, row};
                double[] payoffs = mg.getPayoffs(p1Actions);
                if (p1CurrentMax == payoffs[playerNumber]){
                    p1DominantCoordinates[row][column] = true;
                }
                else p1DominantCoordinates[row][column] = false;
                payoffs = mg.getPayoffs(p2Actions);
                if(p2CurrentMax == payoffs[getOpponentNumber()-1]){
                    p2DominantCoordinates[column][row] = true;
                }
                else p2DominantCoordinates[column][row] = false;
            }
        }
        double trueMax = Double.MIN_VALUE;
        int[] coordinates = {0,0};

        for (int row = 1; row < mg.getNumActions(playerNumber); row++) {
            for (int column = 1; column < mg.getNumActions(playerNumber); column++) {
                boolean p1IsDominant = p1DominantCoordinates[row][column];
                boolean p2IsDominant = p2DominantCoordinates[row][column];
                if(p1IsDominant && p2IsDominant){
                    int[] actions = {row, column};
                    double[] payoffs = mg.getPayoffs(actions);
                    if(payoffs[playerNumber] > trueMax){
                        trueMax = payoffs[playerNumber];
                        coordinates[0]= row;
                        coordinates[1] = column;
                    }
                }
            }
        }
        for(int a = 0; a <= mg.getNumActions(playerNumber);a++) {
            ms.setProb(a, 0.0);
        }
        if(coordinates[0] != 0) {
            ms.setProb(coordinates[playerNumber] + 1, 1.0);
            return ms;
        }
        else {
            return new MixedStrategy(mg.getNumActions(playerNumber));
        }
    }
}
