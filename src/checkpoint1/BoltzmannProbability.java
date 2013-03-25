package checkpoint1;

import util.Function;

public class BoltzmannProbability implements Function{
	
	private Function t;
	
	BoltzmannProbability(final Function temperature){
		t=temperature;
	}

	@Override
	public double evaluate(double... energy) {
		return Math.exp(-energy[0]/t.evaluate());
	}
}
