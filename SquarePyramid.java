import java.util.ArrayList;
import java.util.Scanner;
public class SquarePyramid extends Shape
{
	public double x;
	public double y;
	public double z;
	public double size;
	public double height;
	public double orientation = 0;
	public int numVertices = 5;
	public String identifier = "p";
	public int[] startingPoints = {1,2,3,4,1,2,3,4};
	public int[] endingPoints = {2,3,4,1,5,5,5,5};
	public ArrayList<Vertex> vertices = new ArrayList();

	public SquarePyramid(double X, double Y, double Z, double SIZE, double HEIGHT)
	{
		x = X;
		y = Y;
		z = Z;
		size = SIZE;
		orientation = 0;
		height = HEIGHT;
		defineVertices();
	}

	public SquarePyramid(String in)
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
			size = scan.nextDouble();
			height = scan.nextDouble();
			scan.close();
			defineVertices();


		//}


	}
	public SquarePyramid(double X, double Y, double Z, double SIZE, double HEIGHT, double ORIENTATION)
	{
		x = X;
		y = Y;
		z = Z;
		size = SIZE;
		height = HEIGHT;
		orientation = ORIENTATION;
		defineVertices();
	}
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
		double r = size/2;
		double h = height/2;
		vertices.add(new Vertex(x+r,y-h,z-r));
		vertices.add(new Vertex(x-r,y-h,z-r));
		vertices.add(new Vertex(x-r,y-h,z+r));
		vertices.add(new Vertex(x+r,y-h,z+r));

		vertices.add(new Vertex(x,y+h,z));

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
		out+= "SquarePyramid: " + "X: " + (int)x + " Y: " + (int)y + " Z: " + (int)z + " Size: " + (int)size;
		return out;
	}
	public String toFile()
	{
		String out = "";
		out+=getIdentifier()+"\r\n";
		//out+= (Double)x + "\r\n"+(Double)y +"\r\n"+ (Double)z +"\r\n"+ (Double)size+"\r\nEnd";
		out+=(Double)x+","+(Double)y+","+(Double)z+","+(Double)size+","+(Double)height;
		return out;
	}

public static void main(String[]args)
{
	double x = (double)5;
	SquarePyramid c = new SquarePyramid(5,5,5,5,5);
	System.out.println(c.getVertices());
}

}