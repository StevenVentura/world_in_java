import java.util.ArrayList;
import java.util.Scanner;
public class DoublePyramid extends Shape
{
	private double x;
	private double y;
	private double z;
	private double size;
	private double height;
	private double orientation = 0;
	private int numVertices = 8;
	private String identifier = "d";
	private int[] startingPoints = {1,2,3,4,5,6,7,8,1,2,3,4,5,6,7,8};
	private int[] endingPoints =   {2,3,4,1,6,7,8,5,9,9,9,9,9,9,9,9};
	private ArrayList<Vertex> vertices = new ArrayList();
	private ArrayList<Face> faces = new ArrayList();
	private ArrayList<Coordinate> cs;

	public DoublePyramid(double X, double Y, double Z, double SIZE, double HEIGHT)
	{
		x = X;
		y = Y;
		z = Z;
		size = SIZE;
		orientation = 0;
		height = HEIGHT;
		defineVertices();
	}
	public void Align(int which, Coordinate c)
	{
		cs.set(which, c);
	}

	public DoublePyramid(String in)
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
	public DoublePyramid(double X, double Y, double Z, double SIZE, double HEIGHT, double ORIENTATION)
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

		vertices.add(new Vertex(x+r,y+h,z-r));
		vertices.add(new Vertex(x-r,y+h,z-r));
		vertices.add(new Vertex(x-r,y+h,z+r));
		vertices.add(new Vertex(x+r,y+h,z+r));


		vertices.add(new Vertex(x,y,z));

		cs = new ArrayList();
		for (int yyy = 0; yyy < vertices.size(); yyy++)
		{
		cs.add(new Coordinate(0,0,false));
		}

		defineFaces();
	}
	public void defineFaces()
	{
		ArrayList<int[]> dirs = new ArrayList();
		{int[] t = {1,2,3,4};
		dirs.add(t);
		}
		{int[] t = {5,6,7,8};
		dirs.add(t);
		}
		{int[] t = {2,3,9};
		dirs.add(t);
		}
		{int[] t = {2,9,1};
		dirs.add(t);
		}
		{int[] t = {1,9,4};
		dirs.add(t);
		}
		{int[] t = {3,9,4};
		dirs.add(t);
		}
		{int[] t = {9,6,7};
		dirs.add(t);
		}
		{int[] t = {9,6,5};
		dirs.add(t);
		}
		{int[] t = {8,5,9};
		dirs.add(t);
		}
		{int[] t = {7,9,8};
		dirs.add(t);
		}


		for (int tt = 0; tt < dirs.size(); tt++)
				{
		double xx = 0;
		double yy = 0;
		double zz = 0;
		int[] directions = dirs.get(tt);

		for (int c = 0; c < directions.length; c++)
		{
			xx+=vertices.get(directions[c]-1).getX();
			//System.out.println("xx = " + xx);
			yy+=vertices.get(directions[c]-1).getY();
			zz+=vertices.get(directions[c]-1).getZ();
		}

		double cc = directions.length;
		xx/=cc;
		yy/=cc;
		zz/=cc;
		faces.add(new Face(directions,new Vertex(xx,yy,zz)));
				}


	}
	public ArrayList<Face> getFaces()
	{
		return faces;
	}
	public ArrayList<Coordinate> getCs()
	{
		return cs;
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
		out+= "DoublePyramid: " + "X: " + (int)x + " Y: " + (int)y + " Z: " + (int)z + " Size: " + (int)size;
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
	DoublePyramid c = new DoublePyramid(5,5,5,5,5);
	System.out.println(c.getVertices());
}

}