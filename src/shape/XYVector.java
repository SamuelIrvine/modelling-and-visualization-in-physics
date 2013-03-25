package shape;


public class XYVector extends Vector<XYVector>{
	
	private double x;
	private double y;
	
	public XYVector(double x, double y){
		this.x=x;
		this.y=y;
	}

	@Override
	public XYVector add(XYVector a) {
		return new XYVector(x+a.x, y+a.y);
	}

	@Override
	public XYVector negate() {
		return new XYVector(-x, -y);
	}

	@Override
	public XYVector mod(XYVector a) {
		return new XYVector(((x%a.x)+a.x)%a.x, ((y%a.y)+a.y)%a.y);
	}

	@Override
	public double dot(XYVector a) {
		return x*a.x+y*a.y;
	}

	@Override
	public XYVector copy() {
		return new XYVector(x, y);
	}

	@Override
	public XYVector multiple(XYVector a) {
		return new XYVector(x*a.x, y*a.y);
	}
}
