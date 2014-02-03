package checkpoint3;

import java.util.Random;

import shape.Vector;
import shape.XYVector;
import shape.XYZVector;
import util.Copyable;
import util.Factory;

public class Particle<T extends Vector<T>> implements Copyable<Particle<T>>{
	
	public T r;
	public T v;
	
	public double m;
	
	public Particle(T position, T velocity, double mass){
		r=position;
		v=velocity;
		this.m=mass;
	}
	
	@Override
	public Particle<T> copy() {
		return new Particle<T>(r.copy(), v.copy(), m);
	}
	
	public static class XYParticleFactory implements Factory<Particle<XYVector>>{
		
		private XYVector box;
		private Random r;
		private double m;
		
		public XYParticleFactory(long seed, XYVector box, double mass){
			r=new Random(seed);
			this.box=box;
			m=mass;
		}

		@Override
		public Particle<XYVector> create() {
			return new Particle<XYVector>(new XYVector(r.nextDouble()*box.x, r.nextDouble()*box.y).mod(box), new XYVector(0d, 0d), m);
		}
	}
	
	public static class OrderedXYParticleFactory implements Factory<Particle<XYVector>>{
		
		private XYVector box;
		private double m;
		int nParticles;
		double filled;
		
		public OrderedXYParticleFactory(XYVector box, double mass, int nParticles){
			this.box=box;
			m=mass;
			this.nParticles=nParticles;
			filled=Math.pow(box.volume()/nParticles, 1d/box.dimension())/2d;
		}

		@Override
		public Particle<XYVector> create() {
			filled+=Math.pow(box.volume()/nParticles, 1d/box.dimension());
			return new Particle<XYVector>(new XYVector(filled%box.x, ((filled-(filled%box.x))/Math.pow(nParticles, 1d/box.dimension()))%box.y), new XYVector(Math.random()*2-1, Math.random()*2-1), m);
		}
	}
	
	public static class XYZParticleFactory implements Factory<Particle<XYZVector>>{
		
		private XYZVector box;
		private Random r;
		private double m;
		
		public XYZParticleFactory(long seed, XYZVector box, double mass){
			r=new Random(seed);
			this.box=box;
			m=mass;
		}

		@Override
		public Particle<XYZVector> create() {
			return new Particle<XYZVector>(new XYZVector(r.nextDouble()*box.x, r.nextDouble()*box.y, r.nextDouble()*box.z), new XYZVector(0d, 0d, 0d), m);
		}
	}
	
	public static class OrderedXYZParticleFactory implements Factory<Particle<XYZVector>>{
		
		private XYZVector box;
		private double m;
		int nParticles;
		double filled;
		
		public OrderedXYZParticleFactory(XYZVector box, double mass, int nParticles){
			this.box=box;
			m=mass;
			this.nParticles=nParticles;
			filled=Math.pow(box.volume()/nParticles, 1d/box.dimension())/2d;
		}

		@Override
		public Particle<XYZVector> create() {
			filled+=Math.pow(box.volume()/nParticles, 1d/box.dimension());
			double xpos=filled%box.x;
			double ypos=((filled-(filled%box.x))/Math.pow(nParticles, 1d/box.dimension()))%box.y; //this would be easier with integers...
			double zpos=(filled-(ypos*box.x)-xpos)/Math.pow(nParticles, 2d/box.dimension());
			return new Particle<XYZVector>(new XYZVector(xpos, ypos, zpos), new XYZVector(Math.random()*2-1, Math.random()*2-1, Math.random()*2-1), m);
		}
	}
}
