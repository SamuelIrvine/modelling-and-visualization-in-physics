package simulation;


public interface Output<T extends State> {
	
	public abstract void dump(T s);

}
