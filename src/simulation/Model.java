package simulation;

import java.util.LinkedList;
import java.util.List;


public abstract class Model<T extends State> implements Runnable, Dynamic<T> {
	
	private boolean isRunning;
	private int samplePeriod;
	private int step;
	private int nSteps;
	
	private T state;
	private List<Output<T>> outputs;
	private Dynamic<T> dynamic;
	
	
	protected Model(T state, int samplePeriod, int nSteps, Dynamic<T> dynamic, @SuppressWarnings("unchecked") Output<T>... output){
		step=0;
		this.dynamic=dynamic;
		this.nSteps=nSteps;
		this.samplePeriod=samplePeriod;
		this.state=state;
		outputs=new LinkedList<Output<T>>();
		for (Output<T> o: output){
			outputs.add(o);
		}
	}
	
	public void addOutput(Output<T> toAdd){
		outputs.add(toAdd);
	}
	
	public void removeOutput(Output<T> toRemove){
		outputs.remove(toRemove);
	}
	
	public void dumpOutputs(){
		for (Output<T> o: outputs){
			o.dump(state);
		}
	}
	
	public void run(){
		isRunning=true;
		while(isRunning()&&!terminate()){
			update(state);
			step++;
			if (step%samplePeriod==0){
				dumpOutputs();
			}
		}
	}
	
	public boolean isRunning(){
		if (step>=nSteps){
			return false;
		}
		return isRunning;
	}
	
	public T getState(){
		return state;
	}
	
	public int getSamplePeriod(){
		return samplePeriod;
	}
	
	public void update(T state){
		dynamic.update(state);
	}
	
	public boolean terminate(){
		return false;
	}

}
