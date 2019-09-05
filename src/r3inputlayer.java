
//This class creates a rank 3 input tensor

public class r3inputlayer {

  //Input vector
  private double[][][] state;
  int width;

  //Constructor generates an unpopulated array
  public r3inputlayer(double[] inputarray, int depth){
      double[][][] newarray = new double[inputarray.length][1][depth + 2];
      for(int i = 0; i < inputarray.length; i++){
          newarray[i][0][0] = inputarray[i];
      }
      this.state = newarray;
      this.width = inputarray.length;
  }

  //Getter for state vector
  public double[][][] getState() {
      return this.state;
  }

  //Setter for state vector
  public void setState(double[][][] instate) {
      this.state = instate;
  }

  //Return a rank one tensor
  public double[] getrelevantinputs(int layer) {
      double[] output = new double[this.width];
      for(int i = 0; i < this.width; i++){
          output[i] = this.state[i][0][layer];
      }
      return output;
  }

  //Return a rank one tensor
  public double[] getrelevantoutputs(int layer) {
      double[] output = new double[this.width];
      for(int i = 0; i < this.width; i++){
          output[i] = this.state[i][0][layer + 1];
      }
      return output;
  }

  public void updateiolocation(double[] update, int layer) {
      for(int i = 0; i < this.width;i++){
          this.state[i][0][layer] = update[i];
      }
  }

}
