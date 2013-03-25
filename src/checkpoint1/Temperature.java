package checkpoint1;

import util.Function;

public class Temperature implements Function{
	
	private double t;
	
	public Temperature(double t){
		this.t=t;
	}
	
	public void setTemp(double t){
		this.t=t;
	}

	@Override
	public double evaluate(double... d) {
		return t;
	}

}
