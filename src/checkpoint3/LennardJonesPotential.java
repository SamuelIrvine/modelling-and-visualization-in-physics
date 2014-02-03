package checkpoint3;

import shape.Vector;

public class LennardJonesPotential<T extends Vector<T>> implements Potential<T>{
	
	private final double e=1d, o;
	
	public LennardJonesPotential(int nParticles){
		//o=Math.pow(box.dimension()*box.volume()*2d*density/(nParticles*Math.PI), (1d/3d));
		o=1d;
	}

	@Override
	public double energy(Particle<T> a, Particle<T> b, T box) {
		double d=a.r.modMinDist(b.r, box).magnitude();
		return 4d*e*(Math.pow(o/d, 12)-Math.pow(o/d, 6));
	}

	@Override
	public T gradient(Particle<T> a, Particle<T> b, T box) {
		T distance=a.r.modMinDist(b.r, box);
		double r=distance.magnitude();
		double r2=r*r;
		double o_r=o/r;
		double o_r2=o_r*o_r;
		double o_r6=o_r2*o_r2*o_r2;
		double o_r12=o_r6*o_r6;
		return distance.scale(4d*e*(-12d*o_r12/r2+6d*o_r6/r2));
	}
}
