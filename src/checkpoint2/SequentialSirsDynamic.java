package checkpoint2;

import checkpoint2.Cell.State;

public class SequentialSirsDynamic extends SirsDynamic{

	public SequentialSirsDynamic(double p1, double p2, double p3, long seed) {
		super(p1, p2, p3, seed);
	}

	@Override
	public void update(CellLattice lattice) {
		for (int i=0;i<lattice.getSize();i++){
			int x=r.nextInt();
			int y=r.nextInt();
			Cell c=lattice.get(x, y);
			if (c.state==State.SUSCEPTIBLE){
				for (Cell a:lattice.adjacents(x, y)){
					if (a.state==State.INFECTED){
						if (r.nextDouble()<p1){
							c.state=State.INFECTED;
						}
						break;
					}
				}
			}else if (c.state==State.INFECTED&&r.nextDouble()<p2){
				c.state=State.RECOVERED;
			}else if (c.state==State.RECOVERED&&r.nextDouble()<p3){
				c.state=State.SUSCEPTIBLE;
			}
		}	
	}
	
	//Rules S-> I with prob p1 if at least 1 nearest neighbour is I
	//I --> R with prob p2 
	//R-->S with prob p3

}

