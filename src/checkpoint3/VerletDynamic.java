package checkpoint3;

import shape.Vector;
import simulation.Dynamic;
import util.Function;

public class VerletDynamic<T extends Vector<T>> implements Dynamic<ParticleEnsemble<T>>{
	
	public Potential<T> potential;
	
	public Function deltaT;
	
	VerletDynamic(Potential<T> potential, Function timeStep){
		this.potential=potential;
		deltaT=timeStep;
	}
	
	public T[] updateForces(Particle<T>[] particles, T[] forces, T boundries){
		for (int i=0;i<particles.length;i++){
			forces[i]=particles[0].r.zero();
		}
		for (int i=0;i<particles.length;i++){
			for (int j=i+1;j<particles.length;j++){
				T force=potential.gradient(particles[i], particles[j], boundries);
				forces[i]=forces[i].add(force);
				forces[j]=forces[j].subtract(force);
			}
		}
		return forces;
	}

	@Override
	public void update(ParticleEnsemble<T> state) {
		double dt=deltaT.evaluate();
		for (int i=0;i<state.particles.length;i++){
			Particle<T> p=state.particles[i];
			T f=state.forces[i];
			p.r=p.r.add(p.v.scale(dt).add(f.scale(dt*dt/(2*p.m)))).mod(state.boundries);
			p.v=p.v.add(f.scale(dt/(2*p.m)));
		}	
		T[] forces=updateForces(state.particles, state.forces, state.boundries);
		for (int i=0;i<state.particles.length;i++){
			Particle<T> p=state.particles[i];
			T f=forces[i];
			p.v=p.v.add(f.scale(dt/(2*p.m)));
		}
	}
}
