package simulation;

public interface Dynamic <T extends State> {
	
	public void update(T state);

}
