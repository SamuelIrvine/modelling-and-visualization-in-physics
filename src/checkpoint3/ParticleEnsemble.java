package checkpoint3;

import java.awt.Graphics;

import shape.Vector;
import simulation.State;

public class ParticleEnsemble<V extends Vector<V>, T extends Particle<V>> implements State{
	
	private T[] particles;
	private V boundries;
	
	@SuppressWarnings("unchecked")
	public ParticleEnsemble(double density, V boundries){
		particles=(T[])(new Object[n]);
		for (T p: particles){
			
		}
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
