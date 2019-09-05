
public class radio_object {
	
	//Private attributes 
	
	private double[] internalArray;
	private int target;
	
	radio_object(double[] inputedArray){
		
		double[] tempArray = inputedArray;
		int arraySize = tempArray.length;
		
		double[] tempArray2 = new double[arraySize - 1];
		setInternalArray(tempArray2);
		
		setTarget((int)inputedArray[arraySize - 1]);
	}
	

	//Getters and Setters
	public double[] getInternalArray() {
		return internalArray;
	}

	public void setInternalArray(double[] internalArray) {
		this.internalArray = internalArray;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}
	
}