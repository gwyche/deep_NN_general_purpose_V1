import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class commander {
	
	
	public static void main(String[] args) {
    	
    	////DOWNLOAD DATA FROM A CSV AND STORE IT IN AN ARRAY
    	List<List<String>> records = new ArrayList<>();
    	try (BufferedReader br = new BufferedReader(new FileReader("src/data.csv"))) {
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
    	int layers = 2;
        double learnrate = 70;
        double bias = .1;
        double error;
        double axisRatio = 2;
        //END CONTROLS/////
        
        //*
        int totalrows = 161082;
        int trainingsamples = 159782;
        int testsamples = 1300;
        int target;

        //*
        int count = 0;
        int goal = trainingsamples;
        int correct = 0;
        double successrate = 0;
        int dataColumns = columns - 1;
        double[] inputarray = new double[(int)(dataColumns * axisRatio)];

        int hotelement;
        


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
        
    	//DISENGAGE NN////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    
}