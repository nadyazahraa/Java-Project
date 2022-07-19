package Neuron2;

public class Neuron2Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double x[][] = {{0,0}, {0,1}, {1,0}, {1,1}};
		int y[] 	 = {0,1,1,1};						// Logical AND
		Neuron2 n 	 = new Neuron2();
		n.Train(x, y,0.2, 0.5, 1000);
		
		System.out.println(n.Output(new double[] {0,0}));
		System.out.println(n.Output(new double[] {0,1}));
		System.out.println(n.Output(new double[] {1,0}));
		System.out.println(n.Output(new double[] {1,1}));
		
	}

}
