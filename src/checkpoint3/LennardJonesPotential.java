package checkpoint3;

import shape.Vector;

public class LennardJonesPotential<V extends Vector<V>, T extends Particle<V>>  implements Potential<V, T>{
	
	private double e, o;
	
	public LennardJonesPotential(double interactionStrength, double interactionRange){
		e=interactionStrength; 
		o=interactionRange;
	}

	@Override
	public double evaluate(T a, T b) {
		double d=a.getPosition().subtract(b.getPosition()).magnitude();
		return 4d*e*(Math.pow(o/d, 12)-Math.pow(o/d, 6));
	}
}
