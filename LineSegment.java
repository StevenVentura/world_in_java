/*import java.util.ArrayList;
import java.util.Scanner;
public class LineSegment extends Shape
{
	private double x;
	private double y;
	private double z;
	private double length;
	private double orientation = 0;
	private int numVertices = 2;
	private String identifier = "n";
	private int[] startingPoints = {1};
	private int[] endingPoints =   {2};
	private ArrayList<Vertex> vertices = new ArrayList();

	public LineSegment(double X, double Y, double Z, double LENGTH)
	{
		x = X;
		y = Y;
		z = Z;
		length = LENGTH;

		defineVertices();
	}

	public LineSegment(String in)
	{
		/*int numColons = 0;
		for (int c = 0; c < in.length(); c++)
		{
			if (in.substring(c,c+1).equals(":"))
			{
				numColons++;
			}
		}*/
		//if (numColons == 5) // i am just not going to bother with the other one yet at all.
		//{
			/*System.out.println("in = " + in);
			Scanner scan = new Scanner(in).useDelimiter(",");
			//useDelimiter("\\s*fish\\s*");
			//System.out.println(scan.nextDouble() + "     " + scan.nextInt() + "      " + scan.nextDouble());
			x = scan.nextDouble();
			y = scan.nextDouble();
			z = scan.nextDouble();
			length = scan.nextDouble();

			scan.close();
			defineVertices();*/


		//}


//	}
	/*public LineSegment(double X, double Y, double Z, double SIZE, double HEIGHT, double ORIENTATION)
	{
		x = X;
		y = Y;
		z = Z;

		defineVertices();
	}*/
	/*public int[] getStartingPoints()
	{
		return startingPoints;
	}
	public int[] getEndingPoints()
	{
		return endingPoints;
	}
	private void defineVertices()
	{
		vertices.add(new Vertex(x,y,z));
		vertices.add(new Vertex(x,y,z+length));
	}
	public int getNumVertices()
	{
		return numVertices;
	}
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	public String getIdentifier()
	{
		return identifier;
	}
	public String toString()
	{
		String out = "";
		out+= "LineSegment: " + "X: " + (int)x + " Y: " + (int)y + " Z: " + (int)z;
		return out;
	}
	public String toFile()
	{
		String out = "";
		out+=getIdentifier()+"\r\n";
		//out+= (Double)x + "\r\n"+(Double)y +"\r\n"+ (Double)z +"\r\n"+ (Double)size+"\r\nEnd";
		out+=(Double)x+","+(Double)y+","+(Double)z + "," + (Double)length;
		return out;
	}

public static void main(String[]args)
{

}

}*/