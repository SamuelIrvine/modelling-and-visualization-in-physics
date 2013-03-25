package checkpoint3;

import simulation.Dynamic;

public abstract class ParticleDynamic implements Dynamic<ParticleEnsemble>{
	
	public Potential potential;
	
	ParticleDynamic(Potential potential){
		this.potential=potential;
	}


}
