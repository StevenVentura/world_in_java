public class Coordinate
{
	double A;
	double B;
	boolean BEHIND;
	public Coordinate(double a, double b, boolean behind)
	{
		A = a;
		B = b;
		BEHIND = behind;
	}
	public boolean isBehind()
	{
		return BEHIND;
	}
	public double getA()
	{
		return A;
	}
	public double getB()
	{
		return B;
	}
	public void setA(double A)
	{
		this.A = A;
	}
	public void setB(double B)
	{
		this.B = B;
	}
}