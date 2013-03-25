package checkpoint2;

import checkpoint2.Cell.State;

public class ParrallelSirsDynamic extends SirsDynamic{

	public ParrallelSirsDynamic(double p1, double p2, double p3, long seed) {
		super(p1, p2, p3, seed);
	}

	@Override
	public void update(CellLattice lattice) {
		CellLattice old=lattice.copy();
		for (int i=0;i<lattice.getWidth();i++){
			for (int j=0;j<lattice.getHeight();j++){
				Cell ij=lattice.get(i, j);
				if (ij.state==State.SUSCEPTIBLE){
					for (Cell c:old.adjacents(i, j)){
						if (c.state==State.INFECTED){
							if (r.nextDouble()<p1){
								lattice.get(i, j).state=State.INFECTED;
							}
							break;
						}
					}
				}else if (ij.state==State.INFECTED&&r.nextDouble()<p2){
					ij.state=State.RECOVERED;
				}else if (ij.state==State.RECOVERED&&r.nextDouble()<p3){
					ij.state=State.SUSCEPTIBLE;
				}
			}
		}
	}
	
	//Rules S-> I with prob p1 if at least 1 nearest neighbour is I
	//I --> R with prob p2 
	//R-->S with prob p3

}
