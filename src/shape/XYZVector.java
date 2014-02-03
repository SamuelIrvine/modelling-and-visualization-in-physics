package shape;


public class XYZVector extends Vector<XYZVector>{
	
	public double x;
	public double y;
	public double z;
	
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

	@Override
	public double volume() {
		return x*y*z;
	}

	@Override
	public XYZVector scale(double a) {
		return new XYZVector(a*x, a*y, a*z);
	}

	@Override
	public XYZVector zero() {
		return new XYZVector(0d, 0d, 0d);
	}
	
	public XYZVector rotate(double phi, double theta){//rotate around x, then rotate around y
		double rx=Math.cos(theta)*x+Math.sin(phi)*Math.sin(theta)*y-Math.cos(phi)*Math.sin(theta)*z;
		double ry=Math.cos(phi)*y+Math.sin(phi)*z;
		double rz=Math.sin(theta)*x-Math.sin(phi)*Math.cos(theta)*y+Math.cos(theta)*Math.cos(phi)*z;
		return new XYZVector(rx, ry, rz);
	}
	
	@Override
	public XYZVector modMinDist(XYZVector b, XYZVector mod) {
		XYZVector sub=b.subtract(this);
		if (Math.abs(sub.x)>mod.x/2.0){
			sub.x-=Math.signum(sub.x)*mod.x;
		}
		if (Math.abs(sub.y)>mod.y/2.0){
			sub.y-=Math.signum(sub.y)*mod.y;
		}
		if (Math.abs(sub.z)>mod.z/2.0){
			sub.z-=Math.signum(sub.z)*mod.z;
		}
		return sub;
	}
	
	public int dimension(){
		return 3;
	}
}
