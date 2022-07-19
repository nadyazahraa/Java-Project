package Neuron;

public class Neuron1 {
	
	final double x[], w[];
	Neuron1 (double x[], double w[]) {
		this.x = x;
		this.w = w;
	}
	
	public double Output() {
		double sum = 0.0;
		for(int i=0;i<x.length;i++) 
		{
			sum += w[i]*x[i];
		}
		return Math.tanh(sum);
	}

}
