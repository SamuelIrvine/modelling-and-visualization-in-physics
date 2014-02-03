package checkpoint1;

import java.util.Random;

import simulation.Dynamic;
import util.ConstantFunction;

public abstract class IsingDynamic implements Dynamic<SpinLattice>{
	
	public ConstantFunction t;
	public Random r;
	public long seed;
	public BoltzmannProbability prob;
	
	public IsingDynamic(ConstantFunction t, long seed){
		this.t=t;
		this.seed=seed;
		r=new Random(seed);
		prob=new BoltzmannProbability(t);
	}
	
	public abstract void update(SpinLattice s);
	

}
