package checkpoint3;

import shape.Vector;

public interface Potential<T extends Vector<T>>{
	
	public double energy(Particle<T> a, Particle<T> b, T box);
	
	public T gradient(Particle<T> a, Particle<T> b, T box);

}
