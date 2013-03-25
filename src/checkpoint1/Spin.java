package checkpoint1;

import java.util.Random;

import shape.Pair;
import util.Factory;

public class Spin{
	
	private boolean spin;
	
	public Spin(boolean spin){
		this.spin=spin;
	}
	
	public boolean getSpin(){
		return spin;
	}
	
	public void flip(){
		spin=!spin;
	}
	
	public void swap(Spin s){
		boolean oldSpin=spin;
		spin=s.spin;
		s.spin=oldSpin;
	}
	
	public static int energy(Pair<Spin, Spin> p){
		return energy(p.getA(), p.getB());
	}
	
	public static int energy(Spin a, Spin b){//Energy in units of J
		if (a.spin!=b.spin){
			return 1;
		}else{
			return -1;
		}
	}
	
	public static class SpinFactory implements Factory<Spin>{
		
		private Random r;
		
		SpinFactory(){
			r=new Random();
		}
		
		SpinFactory(long seed){
			r=new Random(seed);
		}
		
		@Override
		public Spin create() {
			return new Spin(r.nextBoolean());
		}
		
	}



}
