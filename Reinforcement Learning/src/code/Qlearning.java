package code;
import java.text.DecimalFormat;
import java.util.Random;

public class Qlearning {
	final DecimalFormat df = new DecimalFormat("#.##");
	
	// path finding
	final double alpha = 0.1;
	final double gamma = 0.9;
	
	// states A,B,C,D,E,F,G,H,I,J
	// e.g. from I we can go to J and F
	// from F we go to C then D
	// D is goal state, reward 100 when I->D
	// 
	// ________
	// |A|B|C|D|
	// |_______|
	// |E| |F|G|
	// |_______|
	// |H|I|J|K|
	// 
	
	final int stateA = 0;
	final int stateB = 1;
	final int stateC = 2;
	final int stateD = 3;
	final int stateE = 4;
	final int stateF = 5;
	final int stateG = 6;
	final int stateH = 7;
	final int stateI = 8;
	final int stateJ = 9;
	final int stateK = 10;
	
	final int statesCount = 11;
	final int[] states = new int[] {stateA, stateB, stateC, stateD, stateE, stateF, stateG, stateH, stateI, stateJ, stateK};
	
	// Q(s,a) = Q(s,a) + alpha * (R(s,a) + gamma * Max(next state, all actions) - Q(s,a))
	
	int[][] R = new int[statesCount][statesCount]; //reward lookup
	double[][] Q = new double[statesCount][statesCount]; //Q Learning
	
    int[] actionsFromA = new int[] { stateB, stateE };
    int[] actionsFromB = new int[] { stateA, stateC };
    int[] actionsFromC = new int[] { stateD };
	int[] actionsFromD = new int[] { stateD };
	int[] actionsFromE = new int[] { stateA, stateH };
	int[] actionsFromF = new int[] { stateC, stateJ };
	int[] actionsFromG = new int[] { stateK, stateF };
	int[] actionsFromH = new int[] { stateI, stateE };
	int[] actionsFromI = new int[] { stateJ, stateH };
	int[] actionsFromJ = new int[] { stateF, stateI };
	int[] actionsFromK = new int[] { stateJ };
	int[][] actions = new int[][] { actionsFromA, actionsFromB, actionsFromC, actionsFromD, actionsFromE, actionsFromF, actionsFromG, actionsFromH, actionsFromI, actionsFromJ, actionsFromK };
	
	String[] stateNames = new String[] { "A","B","C","D","E","F","G","H","I","J","K" };
	
	public Qlearning() {
		init();
	}
	
	public void init() {
		R[stateC][stateD] = 100;  // from C to D
		R[stateG][stateD] = 100;  // from G to D 
	}
	
	public static void main(String[] args) {
		long BEGIN = System.currentTimeMillis();
		
		Qlearning obj = new Qlearning();
		
		obj.run();
		obj.printResult();
		obj.showPolicy();
		
		long END = System.currentTimeMillis();
		System.out.println("Time: " + (END - BEGIN) / 1000.0 + " sec.");
	}
	
	void run() {
        /*
        1. Set parameter , and environment reward matrix R 
        2. Initialize matrix Q as zero matrix 
        3. For each episode: Select random initial state 
           Do while not reach goal state o 
               Select one among all possible actions for the current state o 
               Using this possible action, consider to go to the next state o 
               Get maximum Q value of this next state based on all possible actions o 
               Compute o Set the next state as the current state
        */

       // For each episode
		Random rand = new Random();
		// Select random initial state
		for (int i=0; i<1000; i++) { 				
			int state = rand.nextInt(statesCount);
			while (state != stateD) { 	//goal state
				int[] actionsFromState = actions[state];
				int index = rand.nextInt(actionsFromState.length);
				int action = actionsFromState[index];
				int nextState = action;
				
				double q = Q(state, action);
				double maxQ = maxQ(nextState);
				int r = R(state, action);
				
				double value = q + alpha * (r + gamma * maxQ - q);
				setQ(state, action, value);
				
				state = nextState;
			}
		}
	}
	
	double maxQ(int s) {
		int[] actionsFromState = actions[s];
		double maxValue = Double.MIN_VALUE;
		for (int i=0; i<actionsFromState.length; i++) {
			int nextState = actionsFromState[i];
			double value = Q[s][nextState];
			
			if (value > maxValue)
				maxValue = value;
		}
		return maxValue;
	}
	
	int policy(int state) {
		int[] actionsFromState = actions[state];
		double maxValue = Double.MIN_VALUE;
		int policyGotoState = state; 
		for (int i=0; i<actionsFromState.length; i++) {
			int nextState = actionsFromState[i];
			double value = Q[state][nextState];
			
			if (value > maxValue) {
				maxValue = value;
				policyGotoState = nextState;
			}
		}
		return policyGotoState;
	}
	
	double Q(int s, int a) {
		return Q[s][a];
	}
	
	void setQ(int s, int a, double value) {
		Q[s][a] = value;
	}
	
	int R(int s, int a) {
		return R[s][a];
	}
	
	void printResult() {
		System.out.println("Print result");
		for (int i=0; i<Q.length; i++) {
			System.out.print("out from " + stateNames[i] + ": ");
			for (int j=0; j<Q[i].length; j++) {
				System.out.print(df.format(Q[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	// policy is maxQ(states)
	void showPolicy() {
		System.out.println("\nshowPolicy");
		for (int i=0; i<states.length; i++) {
			int from = states[i];
			int to = policy(from);
			System.out.println("from "+ stateNames[from] +" goto "+stateNames[to]);
		}
	}

}
