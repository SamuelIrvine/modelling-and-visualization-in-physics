package shape;

import util.Copyable;

public abstract class Vector<T extends Vector<T>> implements Copyable<T>{
	
	public abstract int dimension();
	
	public abstract T zero();
	
	public abstract T add(T a);
	
	public abstract T negate();
	
	public T subtract(T a){
		return add(a.negate());
	}
	
	public abstract T mod(T a);
	
	public abstract T scale(double a);
	
	public abstract T multiple(T a);
	
	public abstract T modMinDist(T b, T mod);
	
	public abstract double dot(T a);
	
	public abstract double volume();
	
	@SuppressWarnings("unchecked")
	public double magnitude(){
		return Math.sqrt(dot((T)this));
	}

}
