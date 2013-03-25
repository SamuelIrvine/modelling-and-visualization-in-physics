package shape;


public class XYZVector extends Vector<XYZVector>{
	
	private double x;
	private double y;
	private double z;
	
	public XYZVector(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	@Override
	public XYZVector add(XYZVector a){
		return new XYZVector(x+a.x, y+a.y, z+a.z);
	}
	
	@Override
	public XYZVector negate(){
		return new XYZVector(-x, -y, -z);
	}
	
	public XYZVector cross(XYZVector a){
		return new XYZVector(y*a.z-z*a.y, z*a.x-x*a.z, x*a.y-y*a.x);
	}
	
	@Override
	public XYZVector mod(XYZVector a){
		return new XYZVector(((x%a.x)+a.x)%a.x, ((y%a.y)+a.y)%a.y, ((z%a.z)+a.z)%a.z);
	}
	
	@Override
	public double dot(XYZVector a){
		return x*a.x+y*a.y+z*a.z;
	}

	@Override
	public XYZVector copy() {
		return new XYZVector(x, y, z);
	}

	@Override
	public XYZVector multiple(XYZVector a) {
		return new XYZVector(x*a.x, y*a.y, z*a.z);
	}
}
