package checkpoint2;


import java.awt.Color;
import java.awt.Graphics;

import shape.Lattice;
import simulation.State;
import util.Copyable;
import util.Factory;

public class CellLattice extends Lattice<Cell> implements State, Copyable<CellLattice>{

	public CellLattice(int width, int height, Factory<Cell> factory) {
		super(width, height, factory);
	}
	
	public double infection(){
		double infection=0d;
		for (Cell c: all()){
			if (c.state==Cell.State.INFECTED){
				infection++;
			}
		}
		return infection/getSize();
	}

	@Override
	public void render(Graphics g) {
		float ws=(float)g.getClipBounds().width/getWidth();
		float hs=(float)g.getClipBounds().height/getHeight();
		for (int i=0;i<getWidth();i++){
			for (int j=0;j<getHeight();j++){
				switch(get(i, j).state){
				case SUSCEPTIBLE: g.setColor(new Color(1f, 0f, 0f));
					break;
				case INFECTED: g.setColor(new Color(0f, 1f, 0f));
					break;
				case RECOVERED: g.setColor(new Color(0f, 0f, 1f));
					break;
				case IMMUNE: g.setColor(new Color(0f, 0f, 0f));
					break;
				default:
					break;
				}
				g.fillRect((int)(i*ws), (int)(j*hs), (int)(ws+1f), (int)(hs+1f));
			}
		}	
	}

	@Override
	public CellLattice copy() {
		CellLattice copy=new CellLattice(getWidth(), getHeight(), new Factory<Cell>(){
			@Override
			public Cell create() {
				return new Cell(Cell.State.IMMUNE);
			}
		});
		for (int i=0;i<getWidth();i++){
			for (int j=0;j<getWidth();j++){
				copy.get(i,j).state=get(i,j).state;
			}
		}
		return copy;
	}
}
