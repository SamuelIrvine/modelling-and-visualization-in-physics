package checkpoint3;

import shape.Vector;
import shape.XYVector;
import shape.XYZVector;
import simulation.Dynamic;
import simulation.Model;
import simulation.Output;
import simulation.RotateableDisplay;
import util.ConstantFunction;
import util.Function;

public class MolecularDynamics<T extends Vector<T>> extends Model<ParticleEnsemble<T>>{
	
	private enum Arg{
		NONE, D3, POTENTIAL, DENSITY, DT, PARTICLES;
	}
	
	public static void main(String[] args0){
		Arg arg=Arg.NONE;
		boolean d3=false;
		double density=0.01d;
		double deltaT=0.00001;
		int particles=27;
		String pot="";
		for (String s: args0){
			if (s.contains("-")){
				if (s.startsWith("-3d")){
					arg=Arg.D3;
				}else if (s.startsWith("-p")){
					arg=Arg.POTENTIAL;
				}else if (s.startsWith("-den")){
					arg=Arg.DENSITY;
				}else if (s.startsWith("-n")){
					arg=Arg.PARTICLES;
				}else{
					arg=Arg.NONE;
				}
			}
			else {
				try{
					switch(arg){
						case D3:
							d3=s.startsWith("t");
							continue;
						case POTENTIAL:
							pot=s;
							continue;
						case DENSITY:
							density=Double.parseDouble(s);
							continue;
						case PARTICLES:
							particles=Integer.parseInt(s);
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
		Function dt=new ConstantFunction(deltaT);
		if (!d3){
			Potential<XYVector> potential; 
			if (pot.toLowerCase().startsWith("l")){
				potential=new LennardJonesPotential<XYVector>(particles);
			}else{
				potential=new WeeksChandlerAndersonPotential<XYVector>(particles);
			}
			VerletDynamic<XYVector> dynamic=new VerletDynamic<XYVector>(potential, dt);
			ParticleEnsemble<XYVector> ens=new XYEnsemble(particles, density, (VerletDynamic<XYVector>) dynamic, dt);
			RotateableDisplay<ParticleEnsemble<XYVector>> display=new RotateableDisplay<ParticleEnsemble<XYVector>>("2d Molecular Dynamics");
			MolecularDynamics<XYVector> s=new MolecularDynamics<XYVector>(ens, 100, 10000000, dynamic, display);
			s.run();
		}else{
			Potential<XYZVector> potential; 
			if (pot.toLowerCase().startsWith("l")){
				potential=new LennardJonesPotential<XYZVector>(particles);
			}else{
				potential=new WeeksChandlerAndersonPotential<XYZVector>(particles);
			}
			VerletDynamic<XYZVector> dynamic=new VerletDynamic<XYZVector>(potential, dt);
			ParticleEnsemble<XYZVector> ens=new XYZEnsemble(particles, density, (VerletDynamic<XYZVector>) dynamic, dt);
			RotateableDisplay<ParticleEnsemble<XYZVector>> display=new RotateableDisplay<ParticleEnsemble<XYZVector>>("3d Molecular Dynamics");
			MolecularDynamics<XYZVector> s=new MolecularDynamics<XYZVector>(ens, 100, 10000000, dynamic, display);
			s.run();
		}
	}

	@SafeVarargs
	protected MolecularDynamics(ParticleEnsemble<T> state, int samplePeriod,
			int nSteps, Dynamic<ParticleEnsemble<T>> dynamic,
			Output<ParticleEnsemble<T>>... output) {
		super(state, samplePeriod, nSteps, dynamic, output);
	}
}
