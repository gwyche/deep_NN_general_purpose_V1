import java.math.*;

public class r3neuroncalculator {

    //Input vector
    private double[] state;

    //Constructor generates an unpopulated array
    public r3neuroncalculator(int width){
        double[] temp = new double[width];
        this.state = temp;
    }

    //Getter for state vector
    public double[] getState() {
        return this.state;
    }

    //Setter for state vector
    public void setState(double[] state) {
        this.state = state;
    }

    //Update neuron output values
    public void calculate_a_state(double[] lowerlevelsignals, double weights[][][], int outputlayerlevel, double bias){
        int weightmatrixaxis = weights[0].length; //Measure width of weight matrix and assign to variable
        double[] tempstate = new double[weightmatrixaxis];  //Create an array to hold outputs

        for (int i = 0; i < weightmatrixaxis; i++) {
            for (int j = 0; j < weightmatrixaxis; j++) {
                    tempstate[j] = Math.tanh(lowerlevelsignals[i] * weights[i][j][outputlayerlevel] + bias);
            }
        }
        this.state = tempstate;
    }

}


