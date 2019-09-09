import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class commander {
	
	//Attributes
	private r3weights trainedModel;
	private double successRate;
	private boolean trained = false;

	
	private int layers;
    private double learnrate;
    private double bias;
    private double axisRatio;
    private int totalrows;
    private int trainingsamples;
    private int testsamples;
    private String trainLocation;
    
	private double[] testVector;
    private double[] testSampleVector;

	
	
	
	//Constructor
	public commander() {}
	
	//Testing Method
	public double[] test(double[] inputVector) {
		double[] conclusionVector = new double[(int)(inputVector.length * this.axisRatio)];
		
		if(this.trained = false) {
			System.out.println("Neural Net has not been trained yet. Run method 'train'");
		}else {
			r3weights weights = this.trainedModel;

			double[] tempInput = new double[(int)(inputVector.length * this.axisRatio)];

			for(int i = 0; i < inputVector.length; i++) {
				tempInput[i] = inputVector[i];
			}
			
			this.testSampleVector = tempInput;
			
			double[] results;
			boolean trainingOn = false;
			train(this.layers, this.learnrate, this.bias, this.axisRatio, this.totalrows, this.trainingsamples, this.testsamples, this.trainLocation, trainingOn);
			conclusionVector = this.testVector;
		}
		return conclusionVector;
	}
	
	public r3weights getTrainedModel() {
		return trainedModel;
	}

	public void setTrainedModel(r3weights trainedModel) {
		this.trainedModel = trainedModel;
	}

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	public boolean isTrained() {
		return trained;
	}

	public void setTrained(boolean trained) {
		this.trained = trained;
	}

	public double[] getTestVector() {
		return testVector;
	}

	public void setTestVector(double[] testVector) {
		this.testVector = testVector;
	}

	//Training Method
	public void train(int layers, double learnrate, double bias, double axisRatio, int totalrows, int trainingsamples, int testsamples, String trainLocation, Boolean trainingOn){
		
		this.trainLocation = trainLocation;

		
    	////DOWNLOAD DATA FROM A CSV AND STORE IT IN AN ARRAY
    	List<List<String>> records = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader(this.trainLocation))) {
    	    String line;
    	    while ((line = br.readLine()) != null) {
    	        String[] values = line.split(",");
    	        records.add(Arrays.asList(values));
    	    }
    	}

    	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	////END DATA DOWNLOAD////////////////////////////////
    	
    	//Convert List of strings to an array of Strings
    	Object[] alldata_list_array = new List[records.size()];
    	alldata_list_array = (records.toArray());
    	int columns = ((String[]) ((List<List<String>>) alldata_list_array[0]).toArray()).length;
    	
    	String[][] alldata_array_string = new String[records.size()][columns]; 
    	radio_object[] alldata_array = new radio_object[records.size()];
    	

    	
    	for(int i = 0; i< records.size();i++) {
    		String[] tempArray = ((String[]) ((List<List<String>>) alldata_list_array[i]).toArray());
    		for(int j = 0; j < columns; j++) {
        		alldata_array_string[i][j] = tempArray[j];
    		}
    		
    		//Convert string values in element i of alldata_array_string to doubles and assign them to variables
    		double[] arrayForEmbedding = new double[columns];
    		
    		for(int k = 0; k < columns; k++) {
    			arrayForEmbedding[k] = Double.parseDouble((alldata_array_string[i][k]));
    		}
    		
    		//Create new radio_object
    		radio_object newRecord = new radio_object(arrayForEmbedding);
    		
    		//Add radio_object to alldata_array
    		alldata_array[i] = newRecord;
    	}
    	

    	
    	
    	
    	int tablelength = alldata_array.length;
    	
    	//ENGAGE NN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
    	//CONTROLS/////
    	//int layers = 2;
        //double learnrate = 100;
        //double bias = .1;
        double error;
        //double axisRatio = 1.5;
        //END CONTROLS/////
        
        //*
        //int totalrows = 161082;
        //int trainingsamples = 159782;
        //int testsamples = 1300;
        int target;

        //*
        int count = 0;
        int goal = trainingsamples;
        int correct = 0;
        double successrate = 0;
        int dataColumns = columns - 1;
        double[] inputarray = new double[(int)(dataColumns * axisRatio)];

        int hotelement;
        
        //PRIMARY IF CONDITION 1
    	if(trainingOn == true) {

        //TRAIN
        r3weights returnedweightobject = r3MLPfunction_w_BP.r3MLPfunction_w_BP(layers, goal, learnrate, bias, alldata_array, axisRatio, dataColumns);

        //EVALUATE
        for(int i = trainingsamples; i < totalrows; i++) {
        	radio_object tempdata = ((radio_object)(alldata_array[i]));

        	
        	//Get values from array/////////////////////////
            
            for(int j = 0; j < dataColumns; j++) {
            	inputarray[j] = tempdata.getInternalArray()[j];
            }
            
            hotelement = tempdata.getTarget();

            //End get values from array////////////////////
        	
            error = r3MLPfunction_n_BP.r3MLPfunction_n_BP(layers, goal, learnrate, bias, returnedweightobject, inputarray, hotelement);
            if(error < (10E-4)){
                correct = correct + 1;
            }
        }
        
        double successdecimal = ((double)correct / (double)testsamples) * 100;
        successrate = (int)successdecimal;
        System.out.println("Evaluation Success Rate: "+successrate+"%");
        
        this.successRate = successrate;
        this.trainedModel = returnedweightobject;
        this.trained = true;
        this.layers = layers;
        this.axisRatio = axisRatio;
        

        this.learnrate = learnrate;
        this.bias = bias;
        this.totalrows = totalrows;
        this.trainingsamples = trainingsamples;
        this.testsamples = testsamples;
        this.trainLocation = trainLocation;

        
    	//DISENGAGE NN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	}
    	//END PRIMARY IF CONDITION 1
    	
    	//PRIMARY IF CONDITION 2
    	if(trainingOn == false) {
    		
            //EVALUATE 2



            	
            	//Get values from array/////////////////////////
                
              for(int j = 0; j < dataColumns; j++) {
                	inputarray[j] = this.testSampleVector[j];
               }
                
                hotelement = 0;

                //End get values from array////////////////////
            	
                this.testVector = r3MLPfunction_n_BP_test.r3MLPfunction_n_BP(layers, goal, learnrate, bias, this.trainedModel, inputarray, hotelement);

            
    		
    	}
    	//END PRIMARY IF CONDITION 2
    }

    
}