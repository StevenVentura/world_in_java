import java.util.ArrayList;
import java.util.Scanner;
import java.awt.*;
public class Cube extends Shape
{
	public double x;
	public double y;
	public double z;
	public double size;
	private double orientation = 0;
	private double att[];
	private int numVertices = 8;
	private String identifier = "c";
	//private int[] startingPoints = {1,2,3,4,1,2,3,4,5,6,7,8};
	//private int[] endingPoints = {2,3,4,1,5,6,7,8,6,7,8,5};
	private ArrayList<Vertex> vertices = new ArrayList();
	private ArrayList<Face> faces = new ArrayList();
	//private int[] polySequence = {1,2,3,4,8,7,3,2,6,5,1,2};
	private ArrayList<Coordinate> cs;
	private ArrayList<Color> colors;
public Color getColor(int face)
{
	return colors.get(face);
}

	public Cube(double X, double Y, double Z, double SIZE)
	{
		x = X;
		y = Y;
		z = Z;
		size = SIZE;
		orientation = 0;
		updateAtt();
		defineVertices();
	}
	public double getSize()
	{
		return size;
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
	public double getTop(double x, double z)
	{
		return 0;
	}
	public void t(int runs)
	{
		//if (runs%10 != 0)
		//	return;
		double random = Math.random()*6;
		if (Math.random() > 0.5)
		{
			x+=random;
			y+=random;
			z+=random;
		}
		else{
		x-=random;
		y-=random;
		z-=random;
		}
	}
	public Cube(Cube c, int face)
	{
		x = c.x;//front:5 bottom:3 left: 3 right: 4
		y = c.y;
		z = c.z;
		size = c.size;
		if (face == 0)
			z-=c.size;
		if (face == 1)
			z+=c.size;
		if (face == 2)
			y-=c.size;
		if (face == 3) // top face
			y+=c.size;
		if (face == 4)
			x-=c.size;//z-=c.size;
		if (face == 5)
			x+=c.size;

		updateAtt();
		defineVertices();


	}
	public void Align(int which, Coordinate c)
	{
		cs.set(which, c);
	}
	public void updateAtt()
	{
		double[] ac = {x,y,z,size,orientation};
		att = ac;

	}
	public void Move(double xc, double yc, double zc)
	{
		x+=xc;
		y+=yc;
		z+=zc;
		updateAtt();
		defineVertices();
	}
	public double[] getAtt()
	{
		return att;
	}
	public void changeVertices(int which, double value)
	{




	}
	public int getMaxFaces()
	{
		return 3;
	}
	public Cube(String in)
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
			updateAtt();
			scan.close();
			defineVertices();


		//}


	}
	public Cube(double X, double Y, double Z, double SIZE, double ORIENTATION)
	{
		x = X;
		y = Y;
		z = Z;
		size = SIZE;
		orientation = ORIENTATION;
		updateAtt();
		defineVertices();
	}
	/*public int[] getStartingPoints()
	{

		return startingPoints;
	}
	public int[] getEndingPoints()
	{
		return endingPoints;
	}*/
	public void defineColors()
	{
		colors = new ArrayList<Color>();
		for (int i = 0; i < 6 ; i++)
		{
			colors.add(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
		}
	}
	public void defineVertices()
	{
		defineColors();
		double r = size/2;
		vertices.clear();
		if (orientation == 0)
		{
			vertices.add(new Vertex(x+r,y+r,z-r));
			vertices.add(new Vertex(x-r,y+r,z-r));
			vertices.add(new Vertex(x-r,y-r,z-r));
			vertices.add(new Vertex(x+r,y-r,z-r));
			//--
			for (int c = 0; c < 4; c++)
			{
				vertices.add(new Vertex(vertices.get(c).getX(),vertices.get(c).getY(), z+r));
			}
		}
		/*System.out.println("\n");
		for (int x = 0; x < vertices.size(); x++)
		{
			System.out.print("["+vertices.get(x)+"]");
		}*/
		//else, i know what to do, but rotations are complicated.
		//defineStartingPoints
		cs = new ArrayList();
		for (int yyy = 0; yyy < vertices.size(); yyy++)
		{
		cs.add(new Coordinate(0,0,false));
		}
		defineFaces();

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



	public void defineFaces()
	{
		//int[] t;
		ArrayList<int[]> dirs = new ArrayList();
		faces.clear();
		{int[] t = {1,2,3,4};
		dirs.add(t);}
		{int[] t = {5,6,7,8};
		dirs.add(t);}
		{int[] t = {3,4,8,7};
		dirs.add(t);}
		{int[] t = {1,2,6,5};
		dirs.add(t);}
		{int[] t = {2,3,7,6};
		dirs.add(t);}
		{int[] t = {1,4,8,5};
		dirs.add(t);}

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
		//System.out.println("xx = " + xx);
				}



	}
	/*public int[] getPolySequence()
	{
		return polySequence;
	}*/
	public ArrayList<Face> getFaces()
	{
		return faces;
	}



	public String toString()
	{
		String out = "";
		out+= "Cube: " + "X: " + (int)x + " Y: " + (int)y + " Z: " + (int)z + " Size: " + (int)size;
		return out;
	}
	public String toFile()
	{
		String out = "";
		out+=getIdentifier()+"\r\n";
		//out+= (Double)x + "\r\n"+(Double)y +"\r\n"+ (Double)z +"\r\n"+ (Double)size+"\r\nEnd";
		out+=(Double)x+","+(Double)y+","+(Double)z+","+(Double)size;
		return out;
	}

public static void main(String[]args)
{
	double x = (double)5;
	Cube c = new Cube(5,5,5,5);
	System.out.println(c.getVertices());
}

}