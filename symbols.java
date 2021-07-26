import java.io.*;
import java.util.*;
import java.util.ArrayList;
public class symbols
{
	ArrayList<Shape> Shapes = new ArrayList();
public symbols()
{

	//////////////////
}

public void addSphere(int X, int Y, int Z, double s1ze, int radius)
{
	for (int z = (int)(Z-radius*Math.sqrt(2)); z < (int)(Z+radius*Math.sqrt(2)); z++)
{
	for (int y = (int)(Y-radius*Math.sqrt(2)); y < (int)(Y+radius*Math.sqrt(2)); y++)
	{
		for (int x = (int)(X-radius*Math.sqrt(2)); x < (int)(X+radius*Math.sqrt(2)); x++)
		{
			if (Math.sqrt(Math.pow(50-x,2) + Math.pow(50-y,2) + Math.pow(50-z,2)) <= 30+s1ze && Math.sqrt(Math.pow(50-x,2) + Math.pow(50-y,2) + Math.pow(50-z,2)) >= 30-s1ze)
			{
				Shapes.add(new Cube(x-30,y-30,z+30,5));
			}
		}

	}
}
}
public ArrayList<Shape> getShapes()
{
	return Shapes;
}
public void add(Shape s)
{
	Shapes.add(s);
}



	public static void main(String[]args)
	{
symbols s = new symbols();
Pathmaker p = new Pathmaker();
p.go();
ArrayList<Shape> path = p.getPath();
for (Shape sh : path)
{
	s.add(sh);
}
//s = new symbols(); // deletes everything

/*for (double z = 10; z <= 100; z+= 10)
s.add(new Wall(0, 10, z, 5, 5, 0, 0));
for (double x = -200; x <= 200; x+=10)
s.add(new Wall(x, 10, 10, 5, 5, 90, 0));
for (double y = 0; y <= 100; y+= 10)
{
	s.add(new Wall(-50, y, 50, 5, 5, 90, 0));
}
*/
for (double x = -100; x <= 100; x+=10)
{
	for (double y = -100; y <= 100; y+= 10)
	{
		if (x == y || x == -y)
		s.add(new Wall(x + 200,y, -50, 5, 5, 90, 0  ));
	}
}





/*double o = 0;
o = 45;
double s1ze = 5;
for (double x = 0; x < 50; x+=s1ze*Math.cos(o*Math.PI/180))
{
	s.add(new Wall(x,x,0,s1ze/2,s1ze/2,0,o));
}*/

/*double x = 0, y = 0, z = 0;
int dir = 0;
double screwupcount = 0;
for (double i = 0; i < 2000; i++)
{
	double amt = 10*Math.random();
	int next = 0;
	for (double c = 0; c < amt; c++)
	{
		next = (int)(4*Math.random());
	}
	double nextx = x;
	double 	nexty = y;
	double 	nextz = z;
double size = 0.05;
	if (next == 0)
		nextz+=size;
	if (next == 1)
		nextz-=size;
	if (next == 2)
		nextx-=size;
	if (next == 3)
		nextx+=size;
	boolean quit = false;
	for (Shape sh : s.getShapes())
	{
		if (sh.getX() == nextx && sh.getY() == nexty && sh.getZ() == nextz)
		{
			quit = true;
			break;
		}
	}
	System.out.println(i);
	if (quit == true && screwupcount < 4)
	{
		screwupcount++;
		i--;
		continue;
	}
	else if (quit == true)
	{
		int cnt = (int)(s.getShapes().size()*Math.random());
		int cntt = -1;
		for (Shape sh : s.getShapes())
		{
			cntt++;
			if (cntt == cnt)
			{
				x = sh.getX();y= sh.getY(); z=sh.getZ();
			}

		}
		screwupcount = 0;
		i--;
		continue;

	}

	//else
	if (quit == false)
	{
		x = nextx;y=nexty;z=nextz;

		s.add(new Wall(x,y,z,size/2,size/2,0,0));
	}

}*/

/*for (double i = 0; i < 360; i++)
{
	for (double c = 0; c < 10; c++)
	{
		s.add(new Wall(i*2,0,c,1,1,i+c,0));

	}

	//s.add(new Wall(i*2,0,0,1,1,0,i));
}*/

//s.addSphere(0,0,0,0.05,100);
//double o = 0;
//s.add(new Wall(0,0,0,100,100,0,0));


/*for (double x = -100; x <= 100; x+=10)
{
	for (double y = 0; y <= 100; y+=10)
	{
		if (x == y)
		s.add(new Cube(x,y,100,10));
		if (x == 100 - y || x == -100 + y)
			s.add(new Cube(100,y,x,10));
	}
}*/

























/*for (double x = 1; x < 100; x+=5)
{
	for (double z= 1; z < 100; z+=5)
	{
		s.add(new Wall(x,0,z,5,5,0,0));
	}
}*/


/*Wall last = new Wall(0,0,0,5,5,25,0);

int delay = 0;
int t = 0;*/
/*for (int i =0; i< 1000; i++)
{


	if (delay <= 0)
	{
		t = (int)(2*Math.random());
		if (Math.random() > 0.1)
		t*=-1;
		if ((o == Math.PI/2 && t == 1) || (o == -Math.PI/2 && t == -1))
		{
			i--;
			continue;
		}
		o+=Math.PI/2*t;
		if (t != 0)
			delay = 10;
	}
	if (t != 0)
	{
		delay--;
	}



	last = new Wall(last.getX()+last.getWidth()*Math.cos(o), last.getY(), last.getZ() + last.getHeight()*Math.sin(o),last.getWidth(),last.getHeight(),last.getJ(),last.getN());
	s.add(last);

}*/

//s.add(new Cube(0,-250,0,500));
/*for (int y = 0; y < 100; y+= 2.5)
for (int x = 0; x < 100; x+=2.5)
s.add(new Cube(x,y,0,1));*/
/*for (int x = 0; x < 100; x+=5)
{

for (int y = 0; y < 100; y+=5)
{

s.add(new Cube(x,0,y,5));

	//s.add(new Cube(x,0,y,5));
}
}*///stairs
/*for (int x = 10; x < 100; x+=1)
{
	for(int y = 0; y<100; y+=1)
	{
		if (x-10 == y)
			s.add(new Cube(x,y,20,5));
	}
}*/
//s = new symbols();
//s.add(new Cube(0,0,0,20));
//s.add(new Cube(0,0,200,400));
//s.addSphere(0,0,0,0.005, 100);
/*for (int x = 0; x < 100; x+=5)
{
	s.add(new Cube(x,0,0,5));
	s.add(new Cube(x,100, 0, 5));
}
for (int y = 5; y < 95; y+=5)
{
	s.add(new Cube(0,y,0,5));
	s.add(new Cube(100,y,0,5));
}*/
///////////////
/*for (int x = 0; x <= 25; x+=5)//////////X shape
{
	s.add(new Cube(x,x,0,5));
	s.add(new Cube(25-x,x,0,5));
}*/
/*double x = 0;
double z = 0;
double y = 0;
double size = 5;*/

//s.add(new Cube(0,-250,0,500));
//s.addSphere(0,0,0,0.05,100);

/*for (y = -100; y<= 100; y+=size)
{
	for (z = -100; z<=100; z+=size)
	{
		if (z == -100 || z == 100 || y == 100 || y == -100)
			s.add(new Cube(100,y,z,size));
	}
}*/

//s.add(new Cube(0,-250,0,500));
//s.add(new Cube(0,0,0,1000));
//s.add(new Cube(0,0,0,20));


//s.add(new Cube(0,0,0,50));
//s.add(new Cube(0,-250,0,500));
/*for (int i = 0; i < 500; i++)
{
	x = 500*Math.random() - 250;
	y = 500*Math.random();
	z = 500*Math.random() - 250;
	s.add(new Cube(x,y,z,size));
}
for (x = 0; x < 300; x+=size)
{
	for (z = 0; z < size*5; z+=size)
	s.add(new Cube(x,x,z,size));
}*/
//s.add(new Cube(0,0,0,5));
/*for (x = 0; x < 50; x+=size)
{
	for (y = 0; y < 50; y+=size)
	{
		for (z = 0; z < 50; z+=size)
		{
			s.add(new Cube(x,y,z,size));
		}
	}
}*/

// xy plane single rectangle cubes
/*for (x = 0; x < 50; x+=size)
{
	for (y = 0; y < 50; y+=size)
	{
		s.add(new Cube(x,y,0,size));
	}
}*/


/*
for (int i = 0; i < 3; i++)
for (int xx = 0; xx < 200; xx++)
{

	int rand = (int)(3*Math.random()) + 1;


	if (rand == 1)
		x+=size/2;
	if (rand == 2)
		z+=size/2;
	if (rand == 3)
		y+=size/2;
	//if (rand == 4)
	//	y-=size/2;
s.add(new Cube(x,y,z,size/2));

}*/
double x, y , z,size=5;
/*for (x = 0; x <= 200; x+=size/2)
for (y = 0; y <= 200; y+=size/2)
for (z = 0; z <= 200; z+=size/2)
//if (x == z || x == 200 - z || y == z || y == 200 - z || x == y || x == 200 - y)
if (x == z || x == 200 - z)
s.add(new Cube(x,y,z,size/2));*/

/*for (x = 0; x <=200; x+= size/2)
{
	for (y = 0; y <= 200; y+=size/2)
	{
		if (x == 0 || y == 0 || x == 200 || y == 200)
			s.add(new Cube(x,y,0,size/2));
	}
}*/
//s.add(new Cube(0,0,0,size/2));



/*for (x = 0; x < 500; x+=5)
{
	s.add(new Cube(x,0,0,5));
	s.add(new Cube(500,0,x,5));
s.add(new Cube(0,x,0,5));
s.add(new Cube(500,x,0,5));
}*/

//Xs in Z plane
/*for (x = 0; x <= 200; x+=size/2)
for (y = 0; y<= 200; y+=size/2)
for (z = 0; z <= 200; z+=40)
if (x == y || x == 200 - y)
s.add(new Cube(x,y,z,size/2));*/

/*for (x = 0; x <= 200; x+=size/2)
{
	s.add(new Cube(x,0,100,size/2));
}*/
/*for (x = 0; x <= 200/4; x+=size/2)
{
	for (z = 0; z <= 200/4; z+=size/2)
		s.add(new Cube(x,0,z,size/2));
}*/



double ø = 0;
/*for (double r = 0; r < 100; r+=size/20/10)
{
	ø+=Math.PI/13*r%0.1;
	s.add(new Cube(0,r*Math.sin(ø),r*Math.cos(ø),size/2));
}*/

	//abc.add(new Cube(0,0,20,5));
//	abc.addSphere(0,0,0,)
//	abc.add(new DoublePyramid(0,0,20,5,10));
	//abc.add(new DoublePyramid(-20,0,20,5,10));
	//abc.add(new Cube(-20,0,20,5));
	//abc.addSphere(0.05);
	//abc.add(new Cube(0,0,20,5));
	//
	//abc.addSphere(-200,0,0,0.05,100);



	///////////////
	/*	Scanner tEmP = new Scanner(System.in);
    System.out.println("What is the name of the file you would like to print to? ( use _ for spaces ) ");
    String fileName = tEmP.next();
    char[] word = new char[fileName.length()];


    for (int x = 0; x < fileName.length(); x++)
    {
    	word[x] = fileName.charAt(x);

    }
    ArrayList<String> finished = new ArrayList();
    for (int x = 0; x < fileName.length(); x++)
    {
    	if (word[x] == '_')
    	{
			finished.add(" ");
    	}
    	else
    	{
    		String t = "";
    		t+=word[x];
    		finished.add(t);
    	}
    		//finished+=(String)(word[x]);
    }
	fileName = "";
	for (int c = 0; c < finished.size(); c++)
	{
		fileName+=finished.get(c);
	}
	//System.out.println("unfinished:"+fileName);
	//if (!((fileName.substring(fileName.length()-1-4,4).equals(".txt"))))
	fileName+=".txt";
	System.out.println("fileName: "+fileName);*/

	// new code
	String fileName = "map.txt";
	//ArrayList<Shape> shapes = new ArrayList();
	//shapes.add(new Cube(0,5,50,10));
	//ArrayList<Cube> shapes = new ArrayList();
	//ArrayList<SquarePyramid> spyramids = new ArrayList();
	double r = 10;
	//shapes.add(new SquarePyramid(0,5+r,50,10,10)); // needs to be +height/2 above and same X
	//shapes.add(new DoublePyramid(10,10,50,5,10));
//	shapes.add(new Cube(10-10,5,50,r)); // size = 10. so height = 10.*/
	//shapes.add(new Cube(0,5,-100,10));
	///shapes.add(new Cube(10,5,50,r));
	/*for (double c = 50000; c >= 5; c = c/10)
	{*/

	//****************
//square of cubes
//int size = 3;

/*for (int b = 0; b <= size*10; b+=size)
{
	for (int a = 0; a <= size*10; a+=size)
	{
		//if (a == 0 || b == 0 || a == size*10 || b == size*10)
		//if (a == 0)
		abc.add(new Cube(b,a,100,size/2));
	}

}*/
//
//	double c = 0;
/*for (int x = 0; x < 200; x++)
{
	shapes.add(new Cube())
}*/
/*	for (int h = 0; h < 25; h+=10)
	{
		for (int x = 0; x < 25; x+=5)
		{
			shapes.add(new DoublePyramid(x,h,50,5,10));
		}
	}*/
	//shapes.add(new Cube(0,0,20,5));

//shapes.add(new Cube(0,0,500/10,200/10));
/*for (int x = 0; x < 100; x++)
{
	for (int y = 0; y < 100; y++)
	{
		if (Math.sqrt(Math.pow(50-x,2)+Math.pow(50-y,2)) <= 30+1 && Math.sqrt(Math.pow(50-x,2)+Math.pow(50-y,2)) >= 30-1)
			shapes.add(new Cube(x,y,20,5));
	}
}*/

	int w = 5;
	/*for (int b = 0-w/2; b < 25+w/2; b+=w)
	{
		shapes.add(new Cube(b,0-w/2-w,50,w));
	}*/
//shapes.add(new Cube(0,5,20,10));
	//shapes.add(new DoublePyramid(10,10,50,5,10));
	//shapes.add(new LineSegment(0,0,0,20));
	//shapes.add(new SquarePyramid(0,0,200,20,40));
	//shapes.add(new DoublePyramid(10,10,50,5,10));
	//shapes.add(new Cube(0,-1010,0,2000));

	/*for (double h = 0; h < 150; h+=5)
	{
		shapes.add(new Cube(0,h,20,5));
	}*/


	//******************

	//shapes.add(new Point(0,0,20));
	//shapes.add(new Cube(0,0,0,10));
	//double r = 1;
	//shapes.add(new Cube(0,0,c,3));

	//shapes.add(new Cube(0,1,c,3));
	//shapes.add(new Cube(1,0,c,3));

	//shapes.add(new Cube(10,0,c,3));
	/*shapes.add(new Cube(10,0,c,r));
	shapes.add(new Cube(0,10,c,r));
	shapes.add(new Cube(10,10,c,r));
	shapes.add(new Cube(5,5,c,r));*/
	r = 2;
	/*for (double x = 2; x <= 10; x+=2)
	{
		for (double y = 2; y <= 10; y +=2)
		{
			if (!(x == 6 && y == 6) && (x == y))
			shapes.add(new Cube(x,y,c,r));
		}
	}
	for (double x = 2; x <= 10; x+=2)
	{
		for (double y = 10; y >= 2; y-=2)
		{
			if (12-x == y)
			shapes.add(new Cube(x,y,c,r));
		}
	}*/

	/*shapes.add(new Cube(8,2,c,r));
	shapes.add(new Cube(8,8,c,r));
	shapes.add(new Cube(6,6,c,r));
	shapes.add(new Cube(6,4,c,r));
	shapes.add(new Cube(4,2,c,r));*/
	//shapes.add(new Cube(4,6,c,r));
	/*}*/










	//shapes.add(new Cube(0,5,50,10));
	// end new code

    Scanner scan = new Scanner(fileName);
    try{
    	FileWriter fstream = new FileWriter(fileName);
    	BufferedWriter write = new BufferedWriter(fstream);

	//new code

	/*for (int x = 0; x < shapes.size(); x++)
	{
		write.write((shapes.get(x)).toFile());
		write.newLine();
	}
	for (int x = 0; x < spyramids.size(); x++)
	{
		write.write((spyramids.get(x)).toFile());
		write.newLine();
	}*/
	for (int X = 0; X < s.getShapes().size(); X++)
	{
		write.write(s.getShapes().get(X).toFile());
		write.newLine();
	}


	// end new code
    /*for (int x = 0; x < 1000; x++)
    {
    	write.write(x+" : " + (char)x);
    	write.newLine();

    }*/



    //System.out.println(scan.next());
    write.close();
    }catch(Exception e){
    	System.err.println("Error: " + e.getMessage());


    }///////////////
    scan.close();
    System.out.println("The shapes have been saved to the file.");



	}
}