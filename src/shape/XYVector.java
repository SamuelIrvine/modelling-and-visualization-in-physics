package shape;


public class XYVector extends Vector<XYVector>{
	
	public double x;
	public double y;
	
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

	@Override
	public double volume() {
		return x*y;
	}

	@Override
	public XYVector scale(double a) {
		return new XYVector(x*a, y*a);
	}

	@Override
	public XYVector zero() {
		return new XYVector(0d, 0d);
	}

	@Override
	public XYVector modMinDist(XYVector b, XYVector mod) {
		XYVector sub=b.subtract(this);
		if (Math.abs(sub.x)>mod.x/2.0){
			sub.x-=Math.signum(sub.x)*mod.x;
		}
		if (Math.abs(sub.y)>mod.y/2.0){
			sub.y-=Math.signum(sub.y)*mod.y;
		}
		return sub;
	}

	@Override
	public int dimension() {
		return 2;
	}
}
