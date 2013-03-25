package checkpoint2;

import java.util.Random;

import simulation.Dynamic;

public abstract class SirsDynamic implements Dynamic<CellLattice>{
	
	public double p1;
	public double p2;
	public double p3;
	
	public long seed;
	
	public Random r;
	
	public SirsDynamic(double p1, double p2, double p3, long seed){
		this.p1=p1;
		this.p2=p2;
		this.p3=p3;
		r=new Random(seed);
	}
	
	

}
