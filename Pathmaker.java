
import java.util.ArrayList;
public class Pathmaker
{
	public double x, y, z, dir=0,size;
	private double xshift, yshift, zshift;
	private ArrayList<Shape> path = new ArrayList<Shape>();

	public Pathmaker()
	{
		x=0;y=0;z=0;dir=0;size=10;xshift = 2*size;
			yshift = 0;
			zshift = 2*size;
	}
	public Pathmaker(double x, double y, double z,double size)
	{
		this.x=x;this.y=y;this.z=z;xshift = 2*size;
			yshift = 0;
			zshift = 2*size;
	}
	public double sin(double t)
	{
		return Math.sin(t*Math.PI/180);
	}
	public double cos(double t)
	{
		return Math.cos(t*Math.PI/180);
	}
	private int turnConsecutiveCounter = 0;
	public void go()
	{
		for (int i = 0; i < 500-200-100; i++){

		shift();
		addTunnel();
		turn();
		}

	}
	public void setDirection(int d)
	{
		dir = d;
	}
	public void add(Wall w)
	{
		boolean bo = true;
		for (Shape s : disallowed)
		{
			if (w.getX() == s.getX() && w.getY() == s.getY() && w.getZ() == s.getZ())
				bo = false;
		}
		if (bo)
			path.add(w);
	}
	public void turn()
	{

		int dir2;
		double randy = Math.random();
if (turnConsecutiveCounter == 2)
	turnConsecutiveCounter = 0;
if (turnConsecutiveCounter != 0)
	turnConsecutiveCounter++;
		if (randy < 0.1 && turnConsecutiveCounter == 0)
		{
			turnConsecutiveCounter++;
			//dir is the direction the wall is going, and dir2 is the absolute turn direction.
			 // positive Z , left is far side up
			shift();
			double siz = size*1.05;
			this.add(new Wall(x,y-size,z,siz,siz,0,0));//floor
			this.add(new Wall(x,y+size,z,siz,siz,0,0));//ceiling
			dir2 = (int)(Math.random()*4);
			/*if (dir != 0 && dir2 != 0)
				this.add(new Wall(x,y,z-size,siz,siz,90,0));
			if (dir != 1 && dir2 != 1)
				this.add(new Wall(x,y,z+size,siz,siz,90,0));
			if (dir != 2 && dir2 != 2)
				this.add(new Wall(x-size,y,z,siz,siz,0,90));
			if (dir != 3 && dir2 != 3)
				this.add(new Wall(x+size,y,z,siz,siz,0,90));*/

			//0 is back, 1 is forward, 2 is left, 3 is right.
			/*
			 * 0 south
			 * 1 north
			 * 2 west
			 * 3 east
			 */
			
			//disallowed = new ArrayList<Shape>();//the reason i dont clear it, is sometimes they come back and make intersections.
			
			if (dir == 0 || dir == 1)
			{
if (dir == 0 || dir2 == 0)
				remove(x,y,z-size,90,0);
			if (dir == 1 || dir2 == 1)
				remove(x,y,z+size,90,0);
			if (dir == 2 || dir2 == 2)
				remove(x-size,y,z,0,90);
			if (dir == 3 || dir2 == 3)
				remove(x+size,y,z,0,90);
			}
			else
			{
				if (dir == 0 || dir2 == 0)
					remove(x,y,z+size,90,0);
				if (dir == 1 || dir2 == 1)
					remove(x,y,z-size,90,0);
				if (dir == 2 || dir2 == 2)
					remove(x+size,y,z,0,90);
				if (dir == 3 || dir2 == 3)
					remove(x-size,y,z,0,90);				
				
			}
			
				this.add(new Wall(x,y,z-size,siz,siz,90,0));
				this.add(new Wall(x,y,z+size,siz,siz,90,0));
				this.add(new Wall(x-size,y,z,siz,siz,0,90));
				this.add(new Wall(x+size,y,z,siz,siz,0,90));




			dir = dir2;
			//shift();
		} else if (randy > 0.1 && randy < 0.5)
		{
			//dir = 0;
			// go up
			double m;

			if (dir == 0)
			{

				shift();

				m = 45*Math.random();//this.y + height*sin(j)

				this.add(new Wall(x,y+size*sin(m) - size,z+size*cos(m) - size,size,size,m,0));
yshift = 2*size*sin(m);
				zshift = 2*size*cos(m);

			}


		}




	}
	private ArrayList<Shape> disallowed = new ArrayList<Shape>();
	public void remove(double x, double y, double z, double j, double n)//remove(x+size,y,z,0,90);
	{
		disallowed.add(new Wall(x,y,z,size,size,j,n));
		for (int i = 0; i < path.size(); i++)
		{
			if (path.get(i).getX() == x && path.get(i).getY() == y && path.get(i).getZ() == z)// && path.get(i).getJ() == j && path.get(i).getN() == n)
				path.remove(i);
		}
	}
	public void shift()
	{
		if (dir == 0)
			z+=zshift;
		if (dir == 1)
			z-=zshift;
		if (dir == 2)
			x-=xshift;
		if (dir == 3)
			x+=xshift;

		y+= yshift;

			xshift = 2*size;
			yshift = 0;
			zshift = 2*size;

	}
	public void addTunnel()
	{
		// floor, left wall, right wall, ceiling
double siz = size*1.05;
		this.add(new Wall(x,y-size,z,siz,siz,0,0));
		this.add(new Wall(x,y+size,z,siz,siz,0,0));

		if (dir == 0 || dir == 1)
		{
			this.add(new Wall(x-size,y,z,siz,siz,0,90));//left
			this.add(new Wall(x+size,y,z,siz,siz,0,90));//right

		}
		if (dir == 2 || dir == 3)
		{
			this.add(new Wall(x,y,z-size,siz,siz,90,0));
			this.add(new Wall(x,y,z+size,siz,siz,90,0));
		}

	}
	public ArrayList<Shape> getPath()
	{
		return path;
	}
	public static void main(String[]args)
	{

	}
}