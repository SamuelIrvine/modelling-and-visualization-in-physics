package checkpoint1;

import util.ConstantFunction;


public class GlauberDynamic extends IsingDynamic{
	
	
	GlauberDynamic(ConstantFunction t, long seed){
		super(t, seed);
	}

	@Override
	public void update(SpinLattice lattice) {
		int x=r.nextInt();
		int y=r.nextInt();
		int energy=0;  
		Spin s=lattice.get(x,y);
		for (Spin spin:lattice.adjacents(x, y)){
			energy+=Spin.energy(s, spin);
		}
		int dEnergy=-2*energy;
		if (dEnergy<=0||prob.evaluate(dEnergy)>r.nextDouble()){
			s.flip();
		}
	}

}
