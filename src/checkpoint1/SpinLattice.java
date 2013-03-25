package checkpoint1;

import java.awt.Color;
import java.awt.Graphics;

import shape.Lattice;
import shape.Pair;
import simulation.State;

public class SpinLattice extends Lattice<Spin> implements State{
	
	private Color down=Color.black;
	private Color up=new Color(0f, 1f, 0f);

	SpinLattice(int width, int height) {
		super(width, height, new Spin.SpinFactory());
	}
	
	public double magnetisation(){//Magnetisation, M in units of J
		double m=0;
		for (Spin s: all()){
			m+=s.getSpin()?1:-1;
		}
		return m;
	}
	
	public double energy(){//Energy, E in units of J
		double energy=0;
		for (Pair<Spin, Spin> p:adjacentPairs()){
			energy+=Spin.energy(p.getA(), p.getB());
		}
		return energy;
	}

	@Override
	public void render(Graphics g) {
		float ws=(float)g.getClipBounds().width/getWidth();
		float hs=(float)g.getClipBounds().height/getHeight();
		for (int i=0;i<getWidth();i++){
			for (int j=0;j<getHeight();j++){
				g.setColor(get(i, j).getSpin()?up:down);
				g.fillRect((int)(i*ws), (int)(j*hs), (int)(ws+1f), (int)(hs+1f));
			}
		}
	}
}
