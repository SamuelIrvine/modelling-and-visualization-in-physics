package checkpoint1;

public class KawasakiDynamic extends IsingDynamic{
	
	KawasakiDynamic(Temperature t, long seed){
		super(t, seed);
	}

	@Override
	public void update(SpinLattice lattice) {
		int x1=r.nextInt();
		int x2=r.nextInt();
		int y1=r.nextInt();
		int y2=r.nextInt();
		Spin s1=lattice.get(x1,y1);
		Spin s2=lattice.get(x2,y2);
		int energy=0;
		if (s1.getSpin()==s2.getSpin()){
			return;
		}
		for (Spin adj:lattice.adjacents(x1, y1)){
			if (adj==s2){
				continue;
			}
			energy+=Spin.energy(s1, adj);
		}
		for (Spin adj:lattice.adjacents(x2, y2)){
			if (adj==s1){
				continue;
			}
			energy+=Spin.energy(s2, adj);
		}
		int dEnergy=-2*energy;
		if (dEnergy<=0||prob.evaluate(dEnergy)>r.nextDouble()){
			s1.swap(s2);
		}
		
	}

}
