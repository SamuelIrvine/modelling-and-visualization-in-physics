package util;

public class GaussianFunction implements Function{
	
	private double spread;
	private double center;
	
	public GaussianFunction(double center, double spread){
		this.spread=spread;
		this.center=center;
	}

	@Override
	public double evaluate(double... d) {
		return 1.0/Math.sqrt(2.0*Math.PI*spread)*Math.exp(-Math.pow(d[0]-center, 2)/2.0/spread/spread);
	}

}
