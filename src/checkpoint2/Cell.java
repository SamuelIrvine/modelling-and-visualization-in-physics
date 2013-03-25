package checkpoint2;

import java.util.Random;

import util.Factory;

public class Cell {
	
	public enum State{
		SUSCEPTIBLE, INFECTED, RECOVERED, IMMUNE;
	}
	
	public State state;
	
	public Cell(State state){
		this.state=state;
	}
	
	public static class CellFactory implements Factory<Cell>{
		
		private double imm;
		private double sus;
		private double inf;
		
		private Random r;
		
		public CellFactory(double imm, double sus, double inf, double rec){
			this(imm, sus, inf, rec, new Random().nextLong());
		}
		
		public CellFactory(double imm, double sus, double inf, double rec, long seed){
			r=new Random(seed);
			double den=imm+sus+inf+rec;
			this.imm=imm/den;
			this.sus=sus/den;
			this.inf=inf/den;
		}
		
		@Override
		public Cell create() {
			State state;
			double prob=r.nextDouble();
			if (prob<imm){
				state=State.IMMUNE;
			}else if (prob<imm+sus){
				state=State.SUSCEPTIBLE;
			}else if (prob<imm+sus+inf){
				state=State.INFECTED;
			}else{
				state=State.RECOVERED;
			}
			return new Cell(state);
		}
	}
}
