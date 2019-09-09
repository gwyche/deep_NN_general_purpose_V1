
public class r3MLPfunction_n_BP_test {

    public static double[] r3MLPfunction_n_BP(int layers, int goal, double learnrate, double bias, r3weights weightmatrix1, double[] inputarray, int hotelement)  {

        //Training Parameters and Neural Net Geometry Controls

        int count = 0;


        //Assign inputarray size to variable
        int axis = inputarray.length;

        //Create target array of same length as input array with all zeroes
        double[] targetarray = new double[axis];

        //Decide which element of target should be hot

        targetarray[hotelement] = .9;

        //Instantiate main components
        r3inputlayer iotensor = new r3inputlayer(inputarray, layers);



        r3neuroncalculator neuroncalculator = new r3neuroncalculator(axis);



            //FEED FORWARD//
            for (int inputtinglayer = 0; inputtinglayer < layers; inputtinglayer++) {
                neuroncalculator.calculate_a_state(iotensor.getrelevantinputs(inputtinglayer), weightmatrix1.getWeightmatrix(), inputtinglayer, bias); //Calculates outputs
                iotensor.updateiolocation(neuroncalculator.getState(), inputtinglayer + 1);  //Updates input/output tensor
            }

            //Declare Variables for calculating error
            double SE[] = new double[axis];
            double runSE = 0;
            double errderiv[] = new double[axis];
            double deriv_activation[] = new double[axis];
            double difference;

            //Calculate errors
            for (int i = 0; i < axis; i++) {
                difference = targetarray[i] - iotensor.getrelevantoutputs(layers - 1)[i];

                SE[i] = .5 * Math.pow(difference, 2);
                errderiv[i] = iotensor.getrelevantoutputs(layers - 1)[i] - targetarray[i];
                deriv_activation[i] = 2 / Math.cosh(2 * iotensor.getrelevantoutputs(layers - 1)[i] + 1);
            }

            for (int i = 0; i < axis; i++) {
                runSE = (runSE + SE[i]);
            }

           //System.out.println("Squared Error: " + runSE);
            //END FEED FORWARD//
            return iotensor.getrelevantoutputs(layers - 1);

    }
}



