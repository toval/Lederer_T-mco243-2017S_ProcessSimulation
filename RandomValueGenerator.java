package asgn02;

import java.util.Random;

public class RandomValueGenerator implements IRandomValueGenerator{

	Random rand;
	public RandomValueGenerator(){
		rand = new Random(System.currentTimeMillis());
	}

	public int getNextInt() {
	int val = rand.nextInt();
	return val;
	}

	public boolean getTrueWithProb(double val) {
		int value = (rand.nextInt(101));
		if(value <= (val*100))return true;
		else return false;
	}

	
}
