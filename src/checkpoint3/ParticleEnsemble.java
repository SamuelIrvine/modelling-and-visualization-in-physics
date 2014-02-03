package checkpoint3;

import java.lang.reflect.Array;

import shape.Vector;
import simulation.State;
import util.Function;

public abstract class ParticleEnsemble<T extends Vector<T>> implements State{
	
	public Particle<T>[] particles;
	public T[] forces;
	public T boundries;
	public Potential<T> potential;
	public double friction=0d;
	public Function dt;
	
	public ParticleEnsemble(VerletDynamic<T> d, Function dt){
		potential=d.potential;
		this.dt=dt;
	}
	
	@SuppressWarnings("unchecked")
	public T[] forces(VerletDynamic<T> d){
		return d.updateForces(particles, (T[])(Array.newInstance(boundries.getClass(), particles.length)), boundries);
	}
	
	public double kineticEnergy(){
		double energy=0d;
		for (Particle<T> p: particles){
			energy+=p.v.dot(p.v)/(2d*p.m);
		}
		return energy;
	}
	
	public double potentialEnergy(){
		double energy=0d;
		for (int i=0;i<particles.length;i++){
			for (int j=i+1;j<particles.length;j++){
				energy+=potential.energy(particles[i], particles[j], boundries);
			}
		}
		return energy;
	}
}
