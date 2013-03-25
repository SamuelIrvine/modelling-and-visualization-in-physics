package checkpoint3;

public class WeeksChandlerAndersonPotential implements Potential{
	
	private double e, o;
	private double cutoff;
	
	public WeeksChandlerAndersonPotential(double interactionStrength, double interactionRange){
		e=interactionStrength; 
		o=interactionRange;
		cutoff=Math.pow(2, (1.0/6.0));
	}

	@Override
	public double evaluate(Particle a, Particle b) {
		double d=a.getPosition().subtract(b.getPosition()).magnitude();
		return d>cutoff?0d:4d*e*(Math.pow(o/d, 12)-Math.pow(o/d, 6))+e;
	}

}
