
public class red1 {
	
	public static void main(String[] args) {
		
		
		//USER CONTROLS
		String trainLocation = "src/data.csv";
		int layers = 2;
	    double learnrate = 100;
	    double bias = .1;
	    double axisRatio = 1.5;
	    int totalrows = 161082;
	    int trainingsamples = 159782;
	    int testsamples = 1300;
	    int dataRows = 8;
		double[] inputVector = {57.0625,85.79734025,1.406391047,0.089519707,188.3060201,64.71256228,-1.597526579,1.42947536};
	    //END USER CONTROLS
	    
		commander blue = new commander();
		blue.train(layers, learnrate, bias, axisRatio, totalrows, trainingsamples, testsamples, trainLocation, true);
		double successRate = blue.getSuccessRate();
		System.out.println("Training evaluation successRate: "+successRate);
		double[] testResults = blue.test(inputVector);
		
		System.out.print("Test Specimen Classification Vector:  ");
		for(int i = 0; i < testResults.length - 1;i++) {
			System.out.print(testResults[i] + ", ");
		}
		System.out.print(testResults[testResults.length - 1]);
	
	}
}
