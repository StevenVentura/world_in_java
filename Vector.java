public class Vector
{
	private double x, y, z;
	public Vector(double X, double Y , double Z)
	{
		x = X; y = Y; z = Z;
	}
	public void reduce()
	{
		double m = Math.sqrt(x*x+y*y+z*z);
		x = x/m;
		y = y/m;
		z = z/m;
	}
	public double angle(Vector other)
	{
		return -50324023;
		
	}
}