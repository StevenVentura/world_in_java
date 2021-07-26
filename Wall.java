import java.util.*;
import javax.swing.*; //camera(mx,my,mz,mx+cos(vg)*sin(hg),my+sin(vg+PI),mz+cos(vg)*cos(hg+PI),0,1,0); // rightside up
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
//import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Image;

import java.awt.image.*;

import java.io.*;
//import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;
public class Wall extends Shape
{

	//NOTES:: j = X-axis rotation, n = Z-axis rotation,   && At All Times [j == 0 xor n == 0] && width & height as parameters become twice their definition
	public double x, y, z, height, width, j = 0, n = 0;
	public Color color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
	public ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	public ArrayList<Coordinate> cs = new ArrayList<Coordinate>();
	public ArrayList<Face> faces = new ArrayList<Face>();
	//public char identifier = w;
	public Color getColor()
	{
		return color;
	}
	public Color getColor(int caogkeoa)
	{
		return color;
	}
	public int getMaxFaces()
	{
		return 0;
	}

	/*public Wall()
	{

	}*/
	/*private boolean uif(double[] last)
	{
		j%= 360;
		n%= 360;

		if (j != 0 && n == 0)
		{
			if (j > 0 && j <= 90)
			{
				return (last[2] )
			}


		} else if (j == 0 && n != 0)
		{

		} else if (j == 0 && n == 0)
		{
			return (last[2] > this.y);
		}


	}*/
	public Vertex gp(int face, double[] last, double[] attempt, Line2D.Double yPath)
	{//
	double maxY, minY;
	if (last[2] >= attempt[2])
	{
		minY = attempt[2] - 3.75;
		maxY = last[2] + 0.25 + 3;
	}
	else
	{
		minY = last[2] + 0.25 + 3;
		maxY = attempt[2] - 3.75;
	}
//	                                                                                      _
		if (j != 0 && n == 0) // left and right is a j rotation (X-axis) == old v roll //     _ ¦  // right = back goes up
		{

			Line2D.Double xRange = new Line2D.Double(this.x - this.width, 0, this.x + this.width, 0);
			Line2D.Double zRange = new Line2D.Double(this.z + this.height*cos(j+180), 0, this.z+this.height*cos(j),0);
			Line2D.Double mxRange = new Line2D.Double(last[1],0,attempt[1],0);
			Line2D.Double mzRange = new Line2D.Double(last[3],0,attempt[3],0);

			if (xRange.intersectsLine(mxRange) && zRange.intersectsLine(mzRange))
			{
				/*vertices.add(new Vertex(x+width,y+height*sin(j),z+height*cos(j)));

				vertices.add(new Vertex(x-width,y+height*sin(j+180),z+height*cos(j+180)));*/


				// find Y and Z intersection

				//double mYz = m1*(z - last[3]) + last[2]; // what is 'z'?

				//double fYz = m2*(z - (this.z+height*cos(j+180))) + this.y+height*sin(j+180);

				//double Z = last[3];////@@@@@@@ this is where i was working last at on. @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ i have undefined errors.



				//double m1 = (attempt[2]-last[2]) / (attempt[3] - last[3]); // user's : (y2-y1) / (z2-z1)
				//double m2 = ((this.y+height*sin(j+180)) - (this.y+height*sin(j))) / ((this.z+height*cos(j+180)) - (this.z+height*cos(j)));
				//double Z = (m1*last[3] - m2*(this.z+height*cos(j+180)) - last[2] + this.y+height*sin(j+180)) / (m1 - m2);
				if (this.isClimbable(0)){

				double m1;

				double m2 = tan(j);//(sin(j)-sin(j+180))/(cos(j)-cos(j+180));
				double Z;
				//if (last[3] == attempt[3])
				/*if (true)
				{*/
					Z = last[3];
				/*}
				else
				{
					m1 = (attempt[2]-last[2])/(attempt[3]-last[3]);
					Z = m1*last[3] + last[2] - (this.y + height*sin(j)) + m2*(this.z + height*cos(j));
					Z/= (m2 - m1);
				}*/


				double Y = m2*(Z-(this.z+height*cos(j))) + (this.y + height*sin(j));


				//double Y = m1*(Z - last[3]) + last[2];

				//double Y = m2*(Z - (this.z+height*cos(j+180))) + this.y+height*sin(j+180);//@@@@@@@
				//double Y = m2*(Z - (z+height*cos(j))) + y+height*sin(j)';
				/*if (this.y != 0)
				{System.out.println(Z);System.out.println("Y = " + Y + "  last[2] = " + last[2]);
				}*/
				//System.out.println("Y = " + Y + " Z =  " + Z);

				boolean containsYmax = false;
				boolean containsYmin = false;



				for (Vertex V : getVertices())
				{
					if (V.getY() >= Y)
						containsYmax = true;
					if (V.getY() <= Y)
						containsYmin = true;


				}

				if ( Y >= minY && Y < maxY && containsYmax && containsYmin)
				{




					double m3 = (attempt[1]-last[1]) / (attempt[3]-last[3] + 0.000001);
					double /*mXz*/ X = m3*(Z - last[3]) + last[1];
					X = attempt[1];
					Z = attempt[3];
					return new Vertex(X,Y,Z);}
				}else // if not climbable
				{
					/*double X = last[1], Y = last[2], Z = last[3];
					X = attempt[1];
					Y = attempt[2];

					/*X = (Y + 3.75 - this.y - this.width*sin(n))/tan(n) + this.x+width*cos(n);
			Y = attempt[2];
			Z = attempt[3];//
			if (j == 90)
			{
				Z = this.z;
			}
			else Z = (Y + 3.75 - this.y - this.height*sin(j))/tan(j) + this.z+height*cos(j);

			if (Math.abs(Z - 1.5 - last[3]) < Math.abs(Z + 1.5 - last[3]))
				Z-= 1.5;
				else
					Z+=1.5;

				Face f = this.getFaces().get(0);
			ArrayList<Vertex> v = this.getVertices();
			int[] corners = f.getCorners();


			int[] xs = new int[f.getCorners().length];
			int[] zs = new int[f.getCorners().length];
			for (int i = 0; i < xs.length; i++)
			{
				xs[i] = (int)v.get(corners[i]-1).getX();
				zs[i] = (int)v.get(corners[i]-1).getZ();
			}
			if (new Line2D.Double(last[1],last[3],attempt[1],attempt[3]).intersects(new Polygon(xs,zs,xs.length).getBounds2D()))*/




			double X = attempt[1], Y = attempt[2], Z;
			if (j == 90)
			{
				if (!((minY < this.y - height && maxY < this.y - height) || (minY > this.y + height && maxY > this.y + height)))
				{
					if ((last[3] <= this.z && attempt[3] >= this.z)   || (last[3] >= this.z && attempt[3] <= this.z))
						return new Vertex(X,Y,this.z);


				}


			}

	//return new Vertex(X,Y,Z);


				}}



			return null;
		}
		else if (n != 0 && j == 0) // up and down is an N rotation (Z-Axis) == old ø spin //     | / _
		{
			Line2D.Double xRange = new Line2D.Double(this.x - this.width, 0, this.x + this.width, 0);
			Line2D.Double zRange = new Line2D.Double(this.z + this.height*cos(j+180), 0, this.z+this.height*cos(j),0);
			Line2D.Double mxRange = new Line2D.Double(last[1],0,attempt[1],0);
			Line2D.Double mzRange = new Line2D.Double(last[3],0,attempt[3],0);
			if (xRange.intersectsLine(mxRange) && zRange.intersectsLine(mzRange))
			{

		double Y = attempt[2], X = attempt[1], Z = attempt[3];
		double Yi, Xi, Zi;
		Yi = tan(n)*(attempt[1]-(this.x+width*cos(n))) + this.y + width*sin(n);
		Xi = (Y - this.y - this.width*sin(n)) / tan(n)  + this.x + width*cos(n);
		Zi = attempt[3];
		if (this.isClimbable(0))
		{
			//Y = tan(n)*(attempt[1]-(this.x+width*cos(n))) + this.y + width*sin(n);
			//X = (Y - this.y - this.width*sin(n)) / tan(n)  + this.x + width*cos(n);
			//Z = attempt[3];
			Y = Yi;
			X = Xi;
			Z = Zi;
		}
		if (this.isClimbable(0) == false)
		{
			/*Y = attempt[2];
			Z = attempt[3]; // should be a function of my path at the created point of intersection

			X = (Y + 3.75 - this.y - this.width*sin(n))/tan(n) + this.x+width*cos(n);*/


			//System.out.println(attempt[1] + "  " + X);
		/*	boolean uif;
			//uif = true;
			n%=360;
		if (n >= 0 && n <= -90)
		{
			if (last[1] <= Xi && last[2] <= tan(n)*(Xi-(this.x+width*cos(n))) + this.y + width*sin(n);

		}*/
		/*if (Math.abs(n) <= 90)
		{
			if (last[2] <= Yi && last[1] <= Xi)
			{
				uif = false;
			}
			else
			{
				uif = true;
			}
		}
		else
		{
			if (last[2] <= Yi && last[1] >= Xi)
			{
				uif = false;
			}
			else
				uif = true;



		}*/

boolean uif = true;//@@@@@@@@@@@@@@@@

		if (uif)
		{
			Y = attempt[2];
			Z = attempt[3];
			X = (Y + 3.75 - this.y - this.width*sin(n))/tan(n) + this.x+width*cos(n);
			if (n == 90)
			{
				X = this.x;
				if ((last[1] <= this.x && attempt[1] >= this.x)   || (last[1] >= this.x && attempt[1] <= this.x))
				{
					if (!((minY < this.y - width && maxY < this.y - width) || (minY > this.y + width && maxY > this.y + width)))
					{
			return new Vertex(X,Y,Z);
					}
				}
				else
					return null;


			}

		}
		else
		{
			//double cx = attempt[1] - last[1];
			//if (cx >= 0)
			//{
				//if (Yi - minY < 4)
				//{
					//Y = minY + 3.75;

					//X = (Y - this.y - this.width*sin(n)) / tan(n)  + this.x + width*cos(n);
					X = (Yi + 3.75 - this.y - this.width*sin(n)) / tan(n)  + this.x + width*cos(n);
					Y = attempt[2];
					Z = attempt[3];
					if (Math.abs(n) <= 90)
					{
						X -= 1.5;// this might be wrong. just needs to be switched if so.
					}
					else
						X += 1.5;
				//}

			//}


		}


		}





		if (Y <= maxY && Y >= minY)
		{
			return new Vertex(X,Y,Z);
		}



			}
			return null;
		}
		else if (n == 0 && j == 0)
		{
			Line2D.Double xRange = new Line2D.Double(this.x - this.width, 0, this.x + this.width, 0);
			Line2D.Double zRange = new Line2D.Double(this.z + this.height*cos(j+180), 0, this.z+this.height*cos(j),0);
			Line2D.Double mxRange = new Line2D.Double(last[1],0,attempt[1],0);
			Line2D.Double mzRange = new Line2D.Double(last[3],0,attempt[3],0);
			if (!(xRange.intersectsLine(mxRange) && zRange.intersectsLine(mzRange)))
				return null;
			if (yPath.intersectsLine(new Line2D.Double(0,this.getY(), 0, this.getY())))
			{
			Face f = this.getFaces().get(0);
			ArrayList<Vertex> v = this.getVertices();
			int[] corners = f.getCorners();


			int[] xs = new int[f.getCorners().length];
			int[] zs = new int[f.getCorners().length];
			for (int i = 0; i < xs.length; i++)
			{
				xs[i] = (int)v.get(corners[i]-1).getX();
				zs[i] = (int)v.get(corners[i]-1).getZ();
			}

			//Polygon p = new Polygon(xs,zs,xs.length);
double X = last[1];
double Z = last[3];

		if (last[1] != attempt[1]){

			double m = (attempt[2]-last[2]) / (attempt[1] - last[1]);

			X = last[1] + (this.y - last[2]) / m;}
		if (last[3] != attempt[3]){

			double m = (attempt[2]-last[2]) / (attempt[3] - last[3]); // user's : (y2-y1) / (z2-z1)

			Z = last[3] + (this.y - last[2]) / m;
		}
			double Y = this.y;

			if (new Polygon(xs,zs,xs.length).contains((int)X,(int)Z))
			{
				//p.invalidate();
				X = attempt[1];// unwanted code
				Z = attempt[3];// unwanted code
				return new Vertex(X,Y,Z);
			}
			}

		}


		return null;



	}
	public boolean isClimbable(int face)
	{
		if (n == 0 && j != 0)
		return (Math.abs(j) <= 45 || (Math.abs(j) <= 225 && Math.abs(j) >= 135));

		if (j == 0 && n != 0)
			return (Math.abs(n) <= 45 || (Math.abs(n) <= 225 && Math.abs(n) >= 135));

		return true;
	}
	public ArrayList<Coordinate> getCs()
	{
		return cs;
	}
	public Wall(double x,double y,double z,double width,double height)
	{
		this.x=x;this.y=y;this.z=z;this.width=width;this.height=height;
		defineVertices();
	}
	public Wall (double x,double y,double z,double width,double height, double j, double n)
	{
			this.x=x;this.y=y;this.z=z;this.width=width;this.height=height;this.j=j;this.n=n;
			defineVertices();
	}
	private double sin(double t)
	{
		return Math.sin(t*Math.PI/180);
	}
	private double cos(double t)
	{
		return Math.cos(t*Math.PI/180);
	}
	private double tan(double t)
	{
		return Math.tan(t*Math.PI/180);
	}
	public double getX(){return x;
	}public double getY(){return y;
	}public double getZ(){return z;
	}public double getHeight(){return height;
	}public double getWidth(){return width;
	}public double getJ(){return j;
	}public double getN(){return n;
	}
	public void Align(int which, Coordinate c)
	{
		cs.set(which, c);
	}
	public void defineVertices()
	{
		vertices.clear();

		if (j == 0)
		{
			vertices.add(new Vertex(x+width*cos(n),y+width*sin(n),z+height));
			vertices.add(new Vertex(x+width*cos(n+180),y+width*sin(n+180),z+height));
			vertices.add(new Vertex(x+width*cos(n+180),y+width*sin(n+180),z-height));
			vertices.add(new Vertex(x+width*cos(n),y+width*sin(n),z-height));
		}
		else{

			if (n == 0)
			{
				vertices.add(new Vertex(x+width,y+height*sin(j),z+height*cos(j)));
				vertices.add(new Vertex(x-width,y+height*sin(j),z+height*cos(j)));
				vertices.add(new Vertex(x-width,y+height*sin(j+180),z+height*cos(j+180)));
				vertices.add(new Vertex(x+width,y+height*sin(j+180),z+height*cos(j+180)));
			}
		}




		cs = new ArrayList();
		for (int yyy = 0; yyy < vertices.size(); yyy++)
		{
		cs.add(new Coordinate(0,0,false));
		}
		defineFaces();
	}
	public ArrayList<Face> getFaces()
	{
		return faces;
	}
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	public void defineFaces()
	{
		faces.clear();
		int[] dirs = {1,2,3,4};
		double xx = 0, yy = 0, zz = 0;
		for (int i = 0; i < dirs.length; i++)
		{
			xx+=vertices.get(dirs[i]-1).getX();
			yy+=vertices.get(dirs[i]-1).getY();
			zz+=vertices.get(dirs[i]-1).getZ();
		}
		xx/=dirs.length;
		yy/=dirs.length;
		zz/=dirs.length;
		faces.add(new Face(dirs,new Vertex(xx,yy,zz)));
	}
	public void height(boolean b)
	{
		if (b)
		height+=1;
		else
			height-=1;
	}
	public void width(boolean b)
	{
		if (b)
			width+=1;
			else
				width-=1;
	}
	public void rotateX(boolean b)
	{
	//	System.out.println("working!@#$!@#$!@#$%!#$!$!@#$%!#$!$!@#$%!#$!$!@#$%!#$!$!@#$%!#$!$!@#$%!#$!$!@#$%!#$!$");
		n = 0;
		if (b)
			j+=2.5;
			else
				j-=2.5;

	}

	public void rotateZ(boolean b)
	{
		j = 0;
		if (b)
			n+=5;
			else
				n-=5;
	}
	public Wall(String in)
	{
			Scanner scan = new Scanner(in).useDelimiter(",");
			//useDelimiter("\\s*fish\\s*");
			//System.out.println(scan.nextDouble() + "     " + scan.nextInt() + "      " + scan.nextDouble());
			x = scan.nextDouble();
			y = scan.nextDouble();
			z = scan.nextDouble();
			width = scan.nextDouble();
			height = scan.nextDouble();
			j = scan.nextDouble();
			n = scan.nextDouble();
			updateAtt();
			scan.close();
			defineVertices();

	}
	public String toFile()
	{
		String out = "";
		out+="w"+"\r\n";
		//out+= (Double)x + "\r\n"+(Double)y +"\r\n"+ (Double)z +"\r\n"+ (Double)size+"\r\nEnd";
		out+=(Double)x+","+(Double)y+","+(Double)z+","+(Double)width+","+(Double)height+","+(Double)j+","+(Double)n;
		return out;
	}
}