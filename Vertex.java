public class Vertex
{
	private double x;
	private double y;
	private double z;

	public Vertex(double X,double Y,double Z)
	{
		x = X;
		y = Y;
		z = Z;
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
	}
	public double getZ()
	{
		return z;
	}
	public String toString()
	{
		return toString2();
	/*	String out = "";
		out+=(int)x+"-"+(int)y+"-"+(int)z;
		return out;*/

	}
	public String toString2()
	{
		if (Math.abs(x) < 0.0005)
			x = 0;
			if (Math.abs(y) < 0.0005)
				y = 0;
				if (Math.abs(z) < 0.0005)
					z = 0;

	String out = "";
		out+=x+"-"+y+"-"+z;
		return out;
	}




}