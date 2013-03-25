package checkpoint2;

import simulation.Display;
import simulation.Model;
import simulation.Output;
import simulation.Sleep;
import util.Factory;

public class InfectionModel extends Model<CellLattice>{
	
	private enum Arg{
		NONE, WIDTH, HEIGHT, DYNAMIC, IMM, SUS, INF, REC, P1, P2, P3;
	}
	
	public static void main(String[] args0){
		Arg arg=Arg.NONE;
		int width=100;
		int height=100;
		double imm=0d;
		double sus=0.8d;
		double inf=0.1d;
		double rec=0.1d;
		double p1=0.1;
		double p2=0.1;
		double p3=0.1;
		String dynamic="";
		for (String s: args0){
			if (s.contains("-")){
				s=s.concat("-");
				if (s.contains("w")){
					arg=Arg.WIDTH;
				}else if (s.startsWith("-h")){
					arg=Arg.HEIGHT;
				}else if (s.startsWith("-d")){
					arg=Arg.DYNAMIC;
				}else if (s.startsWith("-imm")){
					arg=Arg.SUS;
				}else if (s.startsWith("-sus")){
					arg=Arg.SUS;
				}else if (s.startsWith("-inf")){
					arg=Arg.INF;
				}else if (s.startsWith("-rec")){
					arg=Arg.REC;
				}else if (s.startsWith("-p1")){
					arg=Arg.P1;
				}else if (s.startsWith("-p2")){
					arg=Arg.P2;
				}else if (s.startsWith("-p3")){
					arg=Arg.P3;
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
						case DYNAMIC:
							dynamic=s;
							continue;
						case IMM:
							imm=Double.parseDouble(s);
							continue;
						case SUS:
							sus=Double.parseDouble(s);
							continue;
						case INF:
							inf=Double.parseDouble(s);
							continue;
						case REC:
							rec=Double.parseDouble(s);
							continue;
						case P1:
							p1=Double.parseDouble(s);
							continue;
						case P2:
							p2=Double.parseDouble(s);
							continue;
						case P3:
							p3=Double.parseDouble(s);
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
		SirsDynamic d=new SequentialSirsDynamic(p1, p2, p3, seed);
		if (dynamic.toLowerCase().startsWith("p")){
			d=new ParrallelSirsDynamic(p1, p2, p3, seed);
		}else if (dynamic.toLowerCase().startsWith("s")){
			d=new SequentialSirsDynamic(p1, p2, p3, seed);
		}
		InfectionModel s = new InfectionModel(width, height, 1, 100000000, d, new Cell.CellFactory(imm, sus, inf, rec, seed), new Display<CellLattice>("InfectionModel"), new Sleep<CellLattice>(0.016d));
		s.run();
	}
	


	@SafeVarargs
	public InfectionModel(int width, int height, int samplePeriod, int nSteps,
			SirsDynamic dynamic, Factory<Cell> factory, Output<CellLattice>... output) {
		super(new CellLattice(width, height, factory), samplePeriod, nSteps, dynamic, output);
	}


	


}
