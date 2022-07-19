package Demo;

public class BackpropagationDemo1 {
	
	// Simple NN with 1 input layer, 1 hidden layer, and 1 output layer
	static int nInputs = 4, nHidden = 3, nOutput = 2;
	
	static double[][] input = {{0,1,1,0}, {1,0,0,1}, {1,1,0,0}};
	static int[][] target = {{0,1}, {1,0}, {1,1}};
	
	// initialize input layer weights w1 and hidden layer weights w2
	static double [] w1 = new double [nInputs * nHidden];
	static double [] w2 = new double [nHidden * nOutput];
	
	// initialize hidden layer output y1 and output layer output y2
	static double [] y1 = new double [nHidden];
	static double [] y2 = new double [nOutput];
	
	// initialize hidden layer errors delta1 and output layer errors delta2
	static double [] delta1 = new double [nHidden];
	static double [] delta2 = new double [nOutput];
	
	// initialize hidden layer bias b2
	static double [] b1 = new double [nHidden];
	static double [] b2 = new double [nOutput];
	
	// learning rate
	static double learningRate = 0.4;
	static int count = 0;
	static int maxCount = 1000;
	static boolean loop = true;
	
	public static void main(String[] args) {
		
		generateWR();
		while(loop) {
			for(int i=0;i<input.length;i++) 
			{
				calculateY(input[i]);
				calculateDelta(i);
				calculateNewWeights(i);
				calculateNewBias();
				count++;
				System.out.println(y2[0] + ", "+ y2[1]);
			}
			System.out.println("=============================");
			
			if(count>=maxCount) {
				loop = false;
			}
		}
	}
	
	
	static private void generateWR() {
		// generate random w1 and w2
		for(int i=0; i<nInputs * nHidden; i++)
		{
			w1[i] = Math.abs(Math.random() - 0.5);
		}
		
		for(int i=0; i<nHidden * nOutput; i++) 
		{
			w2[i] = Math.abs(Math.random() - 0.5);
		}
		
		// generate random b1 and b2
		for(int i=0; i<nHidden; i++)
		{
			b1[i] = Math.abs(Math.random() - 0.5);
		}
		for(int i=0; i<nOutput; i++)
		{
			b2[i] = Math.abs(Math.random() - 0.5);
		}
	}
	
	static void calculateY(double x[]) {
		// calculate the hidden layer output y1
		for(int i=0; i<nHidden; i++) {
			y1[i] = 0;
			for(int j=0; j<nInputs; j++) {
				y1[i] += (x[j]*w1[i+j*nHidden]);
			}
			y1[i] = sigmoid(y1[i] + b1[1]);
		}
		// calculate the hidden layer output y2
		for(int i=0; i<nOutput; i++) {
			y2[i] = 0;
			for(int j=0; j<nHidden; j++) {
				y2[i] += (y1[j]*w2[i+j*nOutput]);
			}
			y2[i] = sigmoid(y2[i] + b2[i]);
		}
	}
	
	static void calculateDelta(int j) {
		for(int i=0; i<nOutput; i++) {
			delta2[i] = target[j][i] - y2[i];
		}
		for(int i=0; i<nHidden; i++) {
			delta1[i] = 0;
			for(int k=0; k<nOutput; k++) {
				delta1[i] += (delta2[k]*w2[i*nOutput+k]);
			}
		}
	}
	
	private static void calculateNewWeights(int j) {
		for(int i=0; i<nInputs; i++) {
			for(int k=0; k<nHidden; k++) {
				int n = i*nHidden + k;
				w1[n] += (learningRate * delta1[k] * input[j][i] * y1[k] * (1-y1[k]));
			}
		}
		
		for(int i=0; i<nHidden; i++) {
			for(int k=0; k<nOutput; k++) {
				int n = i*nOutput+k;
				w2[n] += (learningRate * delta2[k] * y1[i] * y2[k] * (1-y2[k]));
			}
		}
	}
	
	private static void calculateNewBias() {
		for(int i=0; i<nHidden; i++) {
			b1[i] += delta1[i];
		}
		for(int i=0; i<nOutput; i++) {
			b2[i] += delta2[i];
		}
	}
	
	static double sigmoid(double exponent) {
		return (1.0/(1+Math.pow(Math.E, (-1)*exponent)));
	}

}
