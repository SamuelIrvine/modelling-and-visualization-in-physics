package simulation;

public class Sleep<T extends State> implements Output<T> {
	
	private long sleepNano;
	private long lastAction;
	
	public Sleep(double sleepSeconds){
		sleepNano=(long)(sleepSeconds*1000000000);
		lastAction=-1L;
	}

	@Override
	public void dump(T s) {
		if (lastAction==-1L){
			lastAction=System.nanoTime();
		}else{
			lastAction+=sleepNano;
			long sleepTime=lastAction-System.nanoTime();
			if (sleepTime>0){
				try {
					Thread.sleep(sleepTime/1000000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
