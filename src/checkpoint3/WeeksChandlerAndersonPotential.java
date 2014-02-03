package checkpoint3;

import shape.Vector;

public class WeeksChandlerAndersonPotential<T extends Vector<T>> implements Potential<T>{
	
	private final double e=1d, o;
	private final double cutoff;
	
	public WeeksChandlerAndersonPotential(int nParticles){
		//o=Math.pow(box.dimension()*box.volume()*2d*density/(nParticles*Math.PI), (1d/3d));
		o=1d;
		cutoff=Math.pow(2, (1.0/6.0))*o;
	}
	

	@Override
	public double energy(Particle<T> a, Particle<T> b, T box) {
		double r=a.r.modMinDist(b.r, box).magnitude();
		return r>cutoff?0d:4d*e*(Math.pow(o/r, 12)-Math.pow(o/r, 6))+e;
	}

	@Override
	public T gradient(Particle<T> a, Particle<T> b, T box) {
		T distance=a.r.modMinDist(b.r, box);
		double r=distance.magnitude();
		if (r>cutoff){
			return a.r.zero();
		}
		double r2=r*r;
		double o_r=o/r;
		double o_r2=o_r*o_r;
		double o_r6=o_r2*o_r2*o_r2;
		double o_r12=o_r6*o_r6;
		return distance.scale(4d*e*(-12d*o_r12/r2+6d*o_r6/r2));
		//return r>cutoff?a.r.zero():distance.scale(4d/o/o*e*(-12d*Math.pow(o/r, 14)+6d/o/o*Math.pow(o/r, 8)));
	}

}
