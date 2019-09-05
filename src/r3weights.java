public class r3weights {

    //private rank 3 matrix
    private double[][][] weightmatrix;


    //weights object constructor
    //m = height, n = width
    r3weights(int m, int n, int depth){

        //Create temp matrix
        double[][][] tempmatrix = new double[m][n][depth];

        //Populate matrix with random numbers
        for(int xindex = 0; xindex < n; xindex++) {
            for(int yindex = 0; yindex < m; yindex++) {
                for(int zindex = 0; zindex < depth; zindex++) {
                    //assign random number to each element
                    tempmatrix[xindex][yindex][zindex] = (Math.random() - .5);
                }
            }
        }
        //transfer matrix elements inside constructor to private rank 2 matrix
        this.weightmatrix = tempmatrix;
    }

    //Getter for rank 3 tensor
    public double[][][] getWeightmatrix() {
        return this.weightmatrix;
    }

    //Setter for rank 3 tensor
    public void setWeightmatrix(double[][][] inputweightmatrix) {
        this.weightmatrix = inputweightmatrix;
    }

}

