package checkpoint3;

import shape.Vector;

public interface Potential<V extends Vector<V>, T extends Particle<V>>{
	
	public double evaluate(T a, T b);

}
