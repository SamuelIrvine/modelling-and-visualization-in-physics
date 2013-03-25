package shape;

import util.Copyable;

public abstract class Vector<T extends Vector<T>> implements Copyable<T>{
	
	public abstract T add(T a);
	
	public abstract T negate();
	
	public T subtract(T a){
		return add(a.negate());
	}
	
	public abstract T mod(T a);
	
	public abstract T multiple(T a);
	
	public abstract double dot(T a);
	
	@SuppressWarnings("unchecked")
	public double magnitude(){
		return Math.sqrt(dot((T)this));
	}

}
