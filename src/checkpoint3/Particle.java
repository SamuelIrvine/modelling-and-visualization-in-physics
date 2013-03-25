package checkpoint3;

import java.util.Random;

import shape.Vector;
import shape.XYVector;
import shape.XYZVector;
import util.Copyable;
import util.Factory;

public class Particle<T extends Vector<T>> implements Copyable<Particle<T>>{
	
	private T r;
	private T v;
	
	public Particle(T r, T v){
		this.r=r;
		this.v=v;
	}
	
	public T getPosition(){
		return r;
	}
	
	@Override
	public Particle<T> copy() {
		return new Particle<T>(r.copy(), v.copy());
	}
	
	
	public class XYParticleFactory implements Factory<Particle<XYVector>>{
		
		private XYVector box;
		private Random r;
		
		public XYParticleFactory(long seed, XYVector box){
			r=new Random(seed);
			this.box=box;
		}

		@Override
		public Particle<XYVector> create() {
			return new Particle<XYVector>(new XYVector(r.nextDouble(), r.nextDouble()).mod(box), new XYVector(r.nextDouble(), r.nextDouble()));
		}
	}
	
	public class XYZParticleFactory implements Factory<Particle<XYZVector>>{
		
		private XYZVector box;
		private Random r;
		
		public XYZParticleFactory(long seed, XYZVector box){
			r=new Random(seed);
			this.box=box;
		}

		@Override
		public Particle<XYZVector> create() {
			return new Particle<XYZVector>(new XYZVector(r.nextDouble(), r.nextDouble(), r.nextDouble()).mod(box), new XYZVector(r.nextDouble(), r.nextDouble(), r.nextDouble()));
		}
	}
}
