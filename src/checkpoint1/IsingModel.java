package checkpoint1;

import simulation.Display;
import simulation.Model;
import simulation.Output;
import simulation.Sleep;

public class IsingModel extends Model<SpinLattice>{
	
	private enum Arg{
		NONE, WIDTH, HEIGHT, TEMPERATURE, DYNAMIC;
	}
	
	public static void main(String[] args0){
		Arg arg=Arg.NONE;
		double temp=0.00000000000001;
		int width=100;
		int height=100;
		String dynamic="";
		for (String s: args0){
			if (s.contains("-")){
				if (s.contains("w")){
					arg=Arg.WIDTH;
				}else if (s.contains("h")){
					arg=Arg.HEIGHT;
				}else if (s.contains("t")){
					arg=Arg.TEMPERATURE;
				}else if (s.contains("d")){
					arg=Arg.DYNAMIC;
				}else{
					arg=Arg.NONE;
				}
			}
			else {
				try{
					switch(arg){
						case WIDTH:
							width=Integer.parseInt(s);
							continue;
						case HEIGHT:
							height=Integer.parseInt(s);
							continue;
						case TEMPERATURE:
							temp=Double.parseDouble(s);
							continue;
						case DYNAMIC:
							dynamic=s;
							continue;
						default: continue;
					}
				}catch(Exception e){
					System.out.println("Invalid Arguments!");
					System.exit(0);
				}
				arg=Arg.NONE;
			}
		}
		long seed=0L;
		Temperature t=new Temperature(temp);
		IsingDynamic d=new GlauberDynamic(t, seed);
		if (dynamic.toLowerCase().startsWith("k")){
			d=new KawasakiDynamic(t, seed);
		}else if (dynamic.toLowerCase().startsWith("g")){
			d=new GlauberDynamic(t, seed);
		}
		IsingModel s = new IsingModel(100000, 100000000, width, height, d, new Display<SpinLattice>("IsingModel"), new Sleep<SpinLattice>(0.5d));
		s.run();
	}
	
	@SafeVarargs
	IsingModel(int samplePeriod, int nSteps, int width, int height, IsingDynamic dynamic, Output<SpinLattice>... output) {
		super(new SpinLattice(width, height), samplePeriod, nSteps, dynamic, output);
	}
}
