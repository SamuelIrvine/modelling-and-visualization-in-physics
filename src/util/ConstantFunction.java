package util;


public class ConstantFunction implements Function{
	
	private double t;
	
	public ConstantFunction(double t){
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
