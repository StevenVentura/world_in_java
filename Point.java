import java.util.ArrayList;
import java.util.Scanner;
public class Point extends Shape
{
	public double x;
	public double y;
	public double z;
	public double size;
	public double height;
	public double orientation = 0;
	public int numVertices = 1;
	public String identifier = "t";
	public int[] startingPoints = {1};
	public int[] endingPoints =   {1};
	public ArrayList<Vertex> vertices = new ArrayList();

	public Point(double X, double Y, double Z)
	{
		x = X;
		y = Y;
		z = Z;

		defineVertices();
	}

	public Point(String in)
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
			System.out.println("in = " + in);
			Scanner scan = new Scanner(in).useDelimiter(",");
			//useDelimiter("\\s*fish\\s*");
			//System.out.println(scan.nextDouble() + "     " + scan.nextInt() + "      " + scan.nextDouble());
			x = scan.nextDouble();
			y = scan.nextDouble();
			z = scan.nextDouble();

			scan.close();
			defineVertices();


		//}


	}
	/*public Point(double X, double Y, double Z, double SIZE, double HEIGHT, double ORIENTATION)
	{
		x = X;
		y = Y;
		z = Z;

		defineVertices();
	}*/
	public int[] getStartingPoints()
	{
		return startingPoints;
	}
	public int[] getEndingPoints()
	{
		return endingPoints;
	}
	public void defineVertices()
	{
		vertices.add( new Vertex(x,y,z));
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
		out+= "Point: " + "X: " + (int)x + " Y: " + (int)y + " Z: " + (int)z;
		return out;
	}
	public String toFile()
	{
		String out = "";
		out+=getIdentifier()+"\r\n";
		//out+= (Double)x + "\r\n"+(Double)y +"\r\n"+ (Double)z +"\r\n"+ (Double)size+"\r\nEnd";
		out+=(Double)x+","+(Double)y+","+(Double)z;
		return out;
	}

public static void main(String[]args)
{

}

}