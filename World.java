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

public class World extends JApplet implements KeyListener, MouseListener , ActionListener, MouseMotionListener
{ // shapes and vertices
//boolean flux = false;
private double rot = 0;
	int current;
	private User user;
	private double[] m;
	private Timer t;
	private Robot bot;
	private double maxViewingDistance = 1000;
	private boolean mouseFocus = false;
	private ArrayList<Shape> shapes = new ArrayList();
	ArrayList<Color> colors = new ArrayList();

	String[] DOWN = "b1 b2 b3 a b c d e f g h i j k l m n o p q r s t u v w x y z escape delete shift space 1 2 3 4 5 6 7 8 9 0 - = ` [ ] ; ' , . / left right up down backspace enter".split(" ");
	boolean[] down = new boolean[DOWN.length];

	public World() throws Exception
	{
		//user = new User(0,10,19,0,0);
		user = new User(200,0,-150, 0 + 0.0001, -25);
		bot = new Robot();
		//t =  new Timer(32,this);
		t = new Timer(42, this);
	//	t= new Timer(500,this);
		//t = new Timer(400,this);
		addMouseMotionListener(this);
		//addActionListener(this);
		t.setInitialDelay(3000);
		//t = new Timer(1000,this);

		m = user.M();
		defineColors();
		defineShapes();
		addMouseListener(this);
t.start();
		System.out.println("\nshapes.size() = " + shapes.size());

		for (int x = 0; x < down.length; x++)
		{
			down[x] = false;
		}


	}



	double cx;
	double cy;
	double hh = 0;
	double ww = 0;
	int mode = 0;//2 = editing, 1 = i dont even know and 0 = i dont even know

	public void mouseMoved(MouseEvent e)
	{
		if (!mouseFocus)
			return;
		//if (mode == 0 || mode == 2)
		if (mode != 1)
		{



		if (hh != getSize().height || ww != getSize().width) // screen calibration
		{
			//Graphics2D g = (Graphics2D)(getGraphics());
			//g.setClip(new Rectangle(0,0,getSize().width, getSize().height));
			bot.mouseMove(getSize().width/2,getSize().height/2);
			cx = e.getXOnScreen();
			cy = e.getYOnScreen();
			hh = getSize().height;
			ww = getSize().width;
		}
		double x = e.getXOnScreen();
		double y = e.getYOnScreen();


		double b = cx;
		double a = cy;

		m = user.M();
		boolean h = true;

		//if (m[4] < 0)
		//	h = !h;



		user.move(4, true, (x-b)/(100) * 45);// horizontal THIS  CONTROLS THE HORIZONTAL SENSITIVITY. (the bottom part of the fraction.)
		user.move(5, true, (y-a)/(100) * 600/800*45);
		if (user.get(4) == 90 || user.get(4) == -90)
			user.set(4,user.get(4)+0.5);

		bot.mouseMove((int)b,(int)a);//middle of screen, not window.
		//paint();
		}
	}

	public void mouseDragged(MouseEvent e)
	{
		return;
	}

	public double gW(Vertex V)

	{
		m = user.M();

		double x = V.getX() - m[1];

		double y = V.getY() - m[2];

		double z = V.getZ() - m[3];

		//double v = m[5];
//	double g = m[4];
		double gv = -m[5];


		double gw = -(m[4] - 90);
		//gw = m[4];
		//m[4] = -(gw)+90

		if (gv == 0)
			gv = 0.001;
		//gw = 90;
		//gw = 90;
// Fuck i accidently deleted some code, ignore this whole part
		Line2D.Double T = new Line2D.Double(x-10,10/tan(-gv)+y,x+10,-10/tan(-gv)+y);
		double XY = T.ptLineDist(0,0);

		double out = atan(x/XY);

		return out - m[4];

	}
	public double gV(Vertex V) // this value is subtracted; positive values are near the top of the screen.
	{
		m = user.M();

		double y = V.getY() - m[2];
		double x = V.getX() - m[1];
		double z = V.getZ() - m[3];

		double gv = -m[5];
		
		double gw = -(m[4] - 90);

		double XZ;
	
		Line2D.Double T = new Line2D.Double(
			x - 10, 10 / tan(gw) + z,
			x + 10,	-10 / tan(gw) + z);





		XZ = T.ptLineDist(0,0);

		return y / XZ;// - gv ;   // *y/XZ;  == (y/XZ)*(1-gv);
		//}


	}
	public void mouseExited(MouseEvent e)
	{

	}
	public void mouseEntered(MouseEvent e)
	{

	}
	public void mouseReleased(MouseEvent e)
	{
if (e.getButton() == 1)
		{
			down[0] = false;
		}
		if (e.getButton() == 2)
		{
			down[1] = false;

		}
		if (e.getButton() == 3)
		{
			down[2] = false;
		}
	}
	public void mousePressed(MouseEvent e)
	{
System.out.println ("x = " + e.getX() + "    " + "y = " + e.getY());
		//System.out.println(shapes.size());

		//System.out.println(middleface);
		if (e.getButton() == 1)
		{
			down[0] = true;
		}
		if (e.getButton() == 2)
		{
			down[1] = true;

		}
		if (e.getButton() == 3)
		{
			down[2] = true;
		}

	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void defineColors()
	{

			colors.clear();



		for (int D = 0; D < 30; D++)
		{
			colors.add(RC());
		}
	}

	public void init()
	{
			setBackground(Color.WHITE);
		addKeyListener(this);
		setFocusable(true);

	}
	public static double sin(double t)
	{
		return Math.sin(t*Math.PI/180);
	}
	public static double cos(double t)
	{
		return Math.cos(t*Math.PI/180);
	}
	public double tan(double t)
	{
		return Math.tan(t*Math.PI/180);
	}

	public double asin(double t)
	{
		return Math.asin(t)/(2*Math.PI)*360;
	}public double acos(double t)
	{
		return Math.acos(t)/(2*Math.PI)*360;
	}public double atan(double t)
	{
		return Math.atan(t)/(2*Math.PI)*360;
	}


	private boolean print = false;
	private boolean stop = true;


public void uk(String s) //
{
	for (int x = 0; x < down.length; x++)
	{
		if (DOWN[x].equals(s))
			down[x] = true;
	}
}
public boolean se(String s)
{
	boolean out = false;
	for (int x = 0; x < DOWN.length; x++)
		if (down[x] == true && DOWN[x].equals(s))
			 out = true;


	return out;
}

	public void keyPressed(KeyEvent e)
	{
		String s = e.getKeyText(e.getKeyCode()).toLowerCase();

		this.uk(s);

	}

	public void move()
	{

		Scanner scan = new Scanner(System.in);
		System.out.println("x, y, z");
		user.set(1, scan.nextDouble());
		user.set(2, scan.nextDouble());
		user.set(3, scan.nextDouble());
	}


private double ø = 0;
long currenttime = System.currentTimeMillis();
long lasttime = currenttime;
int runs = -1;
//private double[] l;
public Wall carry = null;
private int lastClick = 0;
public void actionPerformed(ActionEvent e)
{

	user.beginMoving();
	/*if (mode != 2)
	user.move(2,false,user.getFallSpeed());*/ /// THE IMPORTANT ONE
	runs++;
	lastClick++;

	m = user.M();

	if (se("g"))
	{
		mode = 1;
	}
	if (se("b1") && lastClick > 2)// && runs%5 == 0)
	{

		if (mode == 2)
		{
			lastClick = 0;
			if (carry!=null)
			{
				//if (currenttime - lasttime > 1000)
				//shapes.add(carry);carry = null;
				shapes.add(new Wall(carry.getX(),carry.getY(),carry.getZ(),carry.getWidth(),carry.getHeight(),carry.getJ(),carry.getN()));
				System.out.println(carry.isClimbable(0));
				carry = null;
			}
			else
			{
				if (middleindex != -1 && shapes.get(middleindex) instanceof Wall)
				{

					carry = new Wall(shapes.get(middleindex).getX(),shapes.get(middleindex).getY(),shapes.get(middleindex).getZ(),shapes.get(middleindex).getWidth(),shapes.get(middleindex).getHeight(),shapes.get(middleindex).getJ(),shapes.get(middleindex).getN());
					if (!(se("shift")))
						shapes.remove(middleindex);
				}
			}


		}
	}
	if (this.se("escape"))
		mouseFocus = false;
	if (this.se("1"))
		mouseFocus = true;
	if (this.se("enter"))
	{
		if (this.se("shift"))// && mode == 2)
		{
			//shapes.add(new Wall(carry.getX(),carry.getY(),carry.getZ(),carry.getWidth(),carry.getHeight(),carry.getJ(),carry.getN())); // moved this to lefft click, carry != null, mode == 2
			carry = null;
			mode = 0;
		}
		else
		{
			mode = 2;
		//	flux = true;

			if (carry==null){	carry = new Wall(m[1]+10*sin(m[4]),m[2]-1,m[3]+10*cos(m[4]),2,2);//needs to be worked on

			}


		}
	}

	if (se("delete"))
	{
		if (mode == 2){carry = null; //mode = 0;
		}

	}
	if (mode == 2 && carry != null)
	{
		if (se("up"))
			if (se("shift")) carry.height(true); else carry.rotateZ(false);
		if (se("down"))
			if (se("shift")) carry.height(false); else carry.rotateZ(true);
		if (se("left"))
			if (se("shift")) carry.width(false); else carry.rotateX(true);
		if (se("right"))
			if (se("shift")) carry.width(true); else carry.rotateX(false);
	}



	/*if (this.se("b1"))
	{
		if (middleindex != -1)
		shapes.add(new Cube((Cube)shapes.get(middleindex),middleface));
	}*/
		if (this.se("m"))
		{
			move();
		}

		if (this.se("escape"))
		{
			mode = 0;/*if (mode == 0)
			mode = 1;
			else
				mode = 0;*/
		}

			if (this.se("p"))
				print = !(print);

		/*if (this.se("f"))
			flux = !flux;*/
		if (this.se("o"))
			stop = !(stop);
		if (this.se("c"))
		{
			defineColors();
		}
		if (this.se("shift"))
		{
			user.crouch();
		}
		if(this.se("e"))
		{
			user.move(2,true,user.getJumpSpeed());
		}
		if (this.se("space"))
		{
			user.move(2,false,user.getFallSpeed());
		}
		if (this.se("9"))
		{
			for (int x = 0; x < 40; x++)
			{
				System.out.println();
			}
		}



		if (this.se("w"))
		{
//normally, it would increase my Z value. however, if i turn to the left, then it will decrease my X value. therefore
// ø = 0; sin(0) = 0; x increases by sin(ø); z increases by cos(ø);
		user.move(1,true,user.getSpeed()*sin(user.get(4)));
		user.move(3,true,user.getSpeed()*cos(user.get(4)));

		}
		if (this.se("s"))
		{
			user.move(1,false,user.getSpeed()*sin(user.get(4)));
			user.move(3,false,user.getSpeed()*cos(user.get(4)));
		}
		if (this.se("a"))
		{
			user.move(1,false,user.getSpeed()*cos(user.get(4)));
			user.move(3,true,user.getSpeed()*sin(user.get(4)));
		}

		if (this.se("d"))
		{
			user.move(1,true,user.getSpeed()*cos(user.get(4)));
			user.move(3,false,user.getSpeed()*sin(user.get(4)));

		}

//if (mode != 2)
{

		if (this.se("left") || this.se("back quote"))
		{
			user.move(4,false,user.getHorizontalSensitivity());//left of me = negative

		}
		if (this.se("right") || this.se("2"))
		{
			user.move(4,true,user.getHorizontalSensitivity());// right of me = positive.
		}
		if (this.se("up"))
		{
			user.move(5,false,user.getVerticalSensitivity());
				// quadrant 2 is above equals NEGATIVE degrees.
				//below the ME-Z axis = positive degrees.
		}
		if (this.se("down"))
		{
			user.move(5,true,user.getVerticalSensitivity());
		}
		if (this.se("backspace"))
		{
			for (int x = 1; x < 6; x++)
			{
				user.set(x,0.01);
			}
			user.set(5,0);
		}
}

user.doneMoving();
if (mode != 2)
	this.checkCollisions();

	if (carry != null){

	carry = new Wall(m[1]+10*sin(m[4]),m[2]-1,m[3]+10*cos(m[4]),carry.getWidth(),carry.getHeight(),carry.getJ(),carry.getN());
	}



if (!(this.se("m")))
paint();
	}

private void checkCollisions()
{

	double[] last = user.getLast();
	double[] attempt = user.getAttempt();
	boolean same = true;
	for (int i = 1; i < last.length; i++)
		if (last[i] != attempt[i])
			same = false;
	if (same)
		return;

	Line2D.Double Ypath;


	Line2D.Double XYpath = new Line2D.Double(last[1],last[2],attempt[1],attempt[2]); // y(x)
	Line2D.Double ZYpath = new Line2D.Double(last[3],last[2],attempt[3],attempt[2]); // y(z)

	for (Shape s : shapes)
	{
		last = user.getLast();
		attempt = user.getAttempt();

		Ypath = new Line2D.Double(0,last[2] + 0.25, 0, attempt[2] - 3.75);

		Vertex gp;
		Face f;

		if (s instanceof Wall)
		{
			gp = s.gp(0,last,attempt,Ypath);

			if (gp == null)
				continue;

			if (s.isClimbable(0))
			{
				//user.set(1,gp.getX());
				user.set(2,gp.getY() + 3.75);// + 3.75);
				//return;
				//user.set(3,gp.getZ());
			}
			else
			{
				user.set(1,gp.getX());
				user.set(3,gp.getZ());
			}




		}

		

	}



}











	private double[] p = new double[3];


	private final double F = 90;//degrees/Field Of View


private ArrayList<Double> l1 = new ArrayList();
private ArrayList<Double> l2 = new ArrayList();

private int TOOL = 0;
private void drawCrosshair()
{
	int x = getSize().width/2;
	int y = getSize().height/2;

	if (TOOL == 0)
	{
			Color c = new Color(146,181,220);
		g.setPaint(c);

		g.fillRect(x-10,y-1,20,2);
		g.fillRect(x-1,y-10,2,20);






	}


}

private BufferedImage bi;
private Graphics2D g;
private int middleindex;
private int middleface;
	public void paint()
	{
		middleindex = -1;

Color bg = new Color((float)0.53,(float)0.81,(float)0.92);
setBackground(bg);
		bi = new BufferedImage(getSize().width, getSize().height, 5);//5 or 2
	
		g = bi.createGraphics();

	
ArrayList<Color> c2 = new ArrayList<Color>();
c2.add(Color.RED);
c2.add(Color.BLUE);
c2.add(Color.GREEN);
int three = 0;
		drawShapes();

		g.setPaint(Color.WHITE);
		g.drawString(user.toString(),50,200);
		drawCrosshair();
		Graphics2D g2 = (Graphics2D)(getGraphics());
		if (mode == 2){

		for (int x = 0; x < bi.getWidth(); x+=5)
			for (int y = 0; y < bi.getHeight(); y+=5){

				three+=(int)(3*Math.random());
				if (bi.getRGB(x,y) == bg.getRGB())//colors.get(0).getRGB())
				bi.setRGB(x,y,c2.get(three%3).getRGB());
			}}

		
		g2.drawImage(bi,null,0,0);



		
	}

int[] xs = new int[8];
int[] ys = new int[8];

private int si;
private ArrayList<Screen> screens;
private boolean vertexBehind;
private int stopp;
private ArrayList<Shape> sorted;
	public void drawShapes()//(double[] As, double[] Bs)
{
	if (carry != null)
		shapes.add(carry);
	sorted = new ArrayList<Shape>();
	screens = new ArrayList<Screen>();

	si = -1;
		int[] startingPoints;
		int[] endingPoints;
		ArrayList<Vertex> vertices;
	
	int[] order = new int[shapes.size()]; // (will be) based on the closest face.
	for (int x = 0; x < order.length; x++)
	{
		order[x] = x+1;
	}


	double[] shortestDistance = new double[shapes.size()];
	int[] seq = new int[shapes.size()];

	for (int x = 0; x < shapes.size();  x++)
	{
		ArrayList<Coordinate> cs = shapes.get(x).getCs();
		ArrayList<Face> fs = shapes.get(x).getFaces();

		int[] EYE = sortFaces(fs);
	
		shortestDistance[x] = distance(new Vertex(m[1],m[2],m[3]), fs.get(EYE[EYE.length-1]).getCenter());
	}
	stopp = 0;
	for (int i= 0; i < shortestDistance.length; i++)
	{
		if (shortestDistance[i] > maxViewingDistance) // sets the maximum viewing distance
			stopp++;
	}

	//sort the shapes back to front... biggest distance to shortest distance

boolean once = false;

for (int x = 0; x < shortestDistance.length; x++)
{
	int lower = 0;
	
	for (int c = 0; c < shortestDistance.length; c++)
	{
		if (shortestDistance[x] >= shortestDistance[c])
			lower++;

	}

	lower--;

	if (seq[seq.length-1-lower] == 0)
	{
		seq[seq.length-1-lower] = x;
	}
	else
	{
		int c = 0;
		while (seq[seq.length-1-lower+c] != 0)
		{
			c++;
		}
		seq[seq.length-1-lower+c] = x;

	}


}






for (int J = 0 + stopp; J < shapes.size(); J++)
{

	int x = seq[J];

	vertices = shapes.get(x).getVertices();



	for (int X = 0; X < vertices.size(); X++) // calculates the points of all of the vertices on the screen.
	{
		if (mode == 2)//flux)
		{
			rot+=15;

			vertexBehind = true;
		current = X;
		//double gw = user.get(4);
		double gw = -(user.get(4) - 90);
	double xc = vertices.get(X).getX() - user.get(1), yc = vertices.get(X).getY()-user.get(2), zc = vertices.get(X).getZ()-user.get(3);

double ang = atan(yc / new Line2D.Double(
			xc - 10, 10 / tan(gw) + zc,
			xc + 10,	-10 / tan(gw) + zc).ptLineDist(0,0));
ang += user.get(5);
yc = tan(ang)*new Line2D.Double(
			xc - 10, 10 / tan(gw) + zc,
			xc + 10,	-10 / tan(gw) + zc).ptLineDist(0,0);
xc += user.get(1); zc += user.get(3);

/*yc = World.rotate(new Vertex(yc / new Line2D.Double(
			xc - 10, 10 / tan(gw) + zc,
			xc + 10,	-10 / tan(gw) + zc).ptLineDist(0,0), yc, 0), user.get(5)).getY();*/


//camera(mx,my,mz,mx+cos(vg)*sin(hg),my+sin(vg+PI),mz+cos(vg)*cos(hg+PI),0,1,0); // rightside up

		/*double B = AlignB(new Vertex(xc + 0.2*cos(rot),yc + 0.2*sin(rot), zc + 0.2*cos(rot)));
		double A = AlignA(new Vertex(xc + 0.2*cos(rot),yc + 0.2*sin(rot), zc + 0.2*cos(rot)));*/
		double B = AlignB(new Vertex(xc + 0.0*cos(rot),yc + 0.0*sin(rot), zc + 0.0*cos(rot)));
		double A = AlignA(new Vertex(xc + 0.0*cos(rot),yc + 0.0*sin(rot), zc + 0.0*cos(rot)));
		shapes.get(x).Align(X,new Coordinate(A,B, vertexBehind));

		continue;
		}

		vertexBehind = true;
		current = X;

		double B = AlignB(vertices.get(X));
	double A = AlignA(vertices.get(X));
		shapes.get(x).Align(X,new Coordinate(A,B, vertexBehind));
	}
	ArrayList<Coordinate> cs = shapes.get(x).getCs();
	ArrayList<Face> fs = shapes.get(x).getFaces();

	int[] EYE = sortFaces(fs);
	
	boolean ad = false; // true if at least one of the faces is NOT behind.

	for (int i = 0+shapes.get(x).getMaxFaces(); i < fs.size(); i++)
	{
		int[] corn = fs.get(EYE[fs.size()-1-i]).getCorners();
		for (int s = 0; s < corn.length; s++)
		{
			if (!(cs.get(corn[s] - 1).isBehind()))
			ad = true;
		}
		if (!ad)
			continue;


		//System.out.println("Corn.length = " + corn.length); // == 3
		//System.out.println("EYE[i]="+EYE[i]);
		int[] sentA = new int[corn.length];
		int[] sentB = new int[corn.length];

		for (int s = 0; s < corn.length; s++)
		{
			sentA[s] = (int)(cs.get(corn[s] - 1).getA());
			sentB[s] = (int)(cs.get(corn[s] - 1).getB());
		}
		//fill(sentA,sentB, colors.get(EYE[fs.size()-1-i]));



		Polygon poly = new Polygon(sentB, sentA, sentA.length);

		if (poly.contains((double)(getSize().width/2),(double)(getSize().height/2))){

			middleindex = x;
			si = screens.size();

			//middleface = i;
			middleface = EYE[fs.size()-1-i];
			//screens.add(new Screen(sentA, sentB, x, EYE[fs.size()-1-i], colors.get(EYE[fs.size()-1-i]).darker()));// 11:30 tues,wed,thurs, 10:40 friday
		//fill(sentA,sentB, colors.get(EYE[fs.size()-1-i]).darker());
		}
		//else
		//screens.add(new Screen(sentA, sentB, x, EYE[fs.size()-1-i],colors.get(EYE[fs.size()-1-i])));// 11:30 tues,wed,thurs, 10:40 friday

screens.add(new Screen(sentA, sentB, x, EYE[fs.size()-1-i],shapes.get(x).getColor(EYE[fs.size()-1-i])));

		}

	}
renderShapes();

}
public void renderShapes()
{
	if (si != -1)
	{
		Screen temp = screens.get(si);
		screens.set(si, new Screen(temp.As, temp.Bs, temp.shapesindex, temp.faceindex, temp.color.darker()));
	}
	for (Screen s : screens)
	{
		fill(s.As,s.Bs,s.color);
	}
if (carry != null)
		shapes.remove(shapes.size()-1);
}

public void fill(int[] aa, int[] bb, Color cc)
{

	g.setPaint(cc);
	g.fillPolygon(bb,aa,bb.length);
}

public int[] sortFaces(ArrayList<Face> fs) // sorts the faces from furthest to closest
{
	if (fs.size() == 1)
	{
		int[] temp = {0};
		return temp;
	}
	double[] ds = new double[fs.size()];
	for (int x = 0; x < ds.length; x++)
	{
		ds[x] = distance(new Vertex(m[1],m[2],m[3]), fs.get(x).getCenter());
	}

	int[] order = new int[fs.size()];

	int i = 0;
	double[] copy = ds;

	while (!(i > order.length-1))
	{
		double smallest = 999999999;
		int chan = -1;
		for (int x = 0; x < copy.length; x++)
		{
			if (copy[x] <= smallest)
			{
				smallest = copy[x];
				chan = x;
			}
		}
		if (chan != -1)
		{
			copy[chan] = 99999 + 1;
		}
		order[i] = chan;
		i++;

	}

	return order;

}
public double distance(Vertex v1, Vertex v2) // returns a positive, always
{
	double x1 = v1.getX();
	double y1 = v1.getY();
	double z1 = v1.getZ();

	double x2 = v2.getX();
	double y2 = v2.getY();
	double z2 = v2.getZ();
//System.out.println("z1 = " + z1 + "  z2 = " + z2);
	return Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) + (z1-z2)*(z1-z2));
}



	public Color RC(double alpha)
	{
		return new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),(float)alpha);
	}



	public Color RC()
	{
		//return Color.BLACK;
		return new Color((float)Math.random(),(float)Math.random(),(float)Math.random());
		/*float f = (float)Math.random();
		float brightness = (float)0.75;
		return new Color(f*brightness,f*brightness,f*brightness);*/
	}
	public void defineShapes() throws IOException
	{
		String fileName = "map.txt";
		File f = new File(fileName);
		//System.out.println("f.exists():"+f.exists());
			if (f.exists())
				{
			FileReader fin = new FileReader(fileName);
			Scanner scanner = new Scanner(fin);
		//	int lineNumber = -2;
			while (scanner.hasNextLine())
					{
				//		lineNumber+=2;
						String line = scanner.nextLine();
						if (line.charAt(0) == 'C' || line.charAt(0) == 'c')
						{
						String lineAlt = scanner.nextLine();
						shapes.add(new Cube(lineAlt));
//						System.out.println(shapes.get(lineNumber/2) + "   " + lineAlt);
						}
						if (line.charAt(0) == 'w' || line.charAt(0) == 'W')
						{
							String lineAlt = scanner.nextLine();
							shapes.add(new Wall(lineAlt));
						}

						//if (line.charAt(0) ==  'P' || line.charAt(0) == 'p')
						//shapes.add(new SquarePyramid(scanner.nextLine()));

						//if (line.charAt(0) == 'D' || line.charAt(0) == 'd')
						//shapes.add(new DoublePyramid(scanner.nextLine()));

						//if (line.charAt(0) == 'T' || line.charAt(0) == 't')
						//shapes.add(new Point(scanner.nextLine()));
						//if (line.charAt(0) == 'n')
						//shapes.add(new LineSegment(scanner.nextLine()));
						// a really good idea is that i will have 1 line be C, and instead of saying "cubes.add(new Cube(line));", i will say "cubes.add(new Cube(scanner.nextLine());" which will work out much more nicely with the whole delimiter thing

					}
			scanner.close();
			fin.close();
				}
				/*System.out.println(cubes.size());*/

	}



	public boolean OOB(double a, double b)
	{
		/*if (a < 0 || a > getSize().height || b < 0 || b > getSize().width)
			return true;
		else
			return false;*/
			return false;
	}
	public double abs(double number)
	{
		return Math.abs(number);
	}
	
	public double AlignA(Vertex V)//camera(mx,my,mz,mx+cos(vg)*sin(hg),my+sin(vg+PI),mz+cos(vg)*cos(hg+PI),0,1,0); // rightside up
	{
		double A;
		/*F = 90;*/
		double s = getSize().width/2;

		double v = gV(V);
		if (vertexBehind)
			v = -v;//180 - v;//-v;

		//if (current == 1 && print)
		//if (print)
		//	System.out.println(current + " :: " + (getSize().height/2 - s*v));


double g = m[5];//vertical orientation

//System.out.println(v+"+"+tan(g)+"="+(v+tan(g)));
//System.out.println(v+"  "  + atan(v));
//System.out.print(v);
v = atan(v + tan(g)) / 45;
//System.out.println("  " + v);
//v -= 

//v = tan(atan(v + tan(g)));
//s = getSize().height/2;
			A = getSize().height/2 - s*v*(Math.PI/4);

			if (vertexBehind)
			{
				//System.out.println(A + " , " + (getSize().height/2 - 3*s*v));

				//A = getSize().height/2 + 3*s*Math.abs(v);
				A = getSize().height/2 + trans*s*v;//Math.abs(v);
			}




			
			return A;
		
	}
	public double fixW(double w)
	{
		double g = m[4];
		w-=g;
		while (w > 180)
			w -= 360;
		while (w < -180)
			w+= 360;
		return w;

	}
	private double trans;
	public double getWC(Vertex V)
	{
		m = user.M();
		double x = V.getX() - m[1];
		double y = V.getY() - m[2];
		double z = V.getZ() - m[3];
		double w;
		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

		double gv = -m[5];

Line2D.Double T = new Line2D.Double(z - 10, 10 / tan(gv) + y, z + 10, -10/tan(gv) + y);
double YZ = T.ptLineDist(0,0);

w = atan(x / YZ);
//*cos(-(m[1]-90)


		double[] p = new double[3];
		p[0] = getWQuad(V);






//w = atan((x-m1)/(z-m3)); // old////

			if (p[0] == 3)
				w-=180;
			if (p[0] == 4)
				w+=180;


return w;
	}
	public double AlignB(Vertex V)
	{
		// NEXT: I CAN ADD A .ADD METHOD FOR THE G AND Vg

		double B;
		double[] m = user.M();
		double s = getSize().width/2;
		//double v = fixW(getWC(V));//getW1(V) - m[4];
		double v = fixW(getW1(V));
		trans = 100;
		if (Math.abs(v) < 90){vertexBehind = false;//System.out.println("!@#!@#");
		}


			B = s+s*tan(v);

		if (vertexBehind)
		{
			B = s-trans*s*tan(v);

		}

//if (print == true)
//		System.out.println(current + " :: " + vertexBehind + " " + B);
			return B;
	



	}

	public Color randomColor()
	{
		return new Color((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
	}
	public double getW1(Vertex V)
	{

		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
		double w;
		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

		double[] p = new double[3];
		p[0] = getWQuad(V);

		if (!(p[0] == 1 || p[0] == 2 || p[0] == 3 || p[0] == 4))
		{
			w = p[0];
		//	w*= cos(dx/dz);
		}
		else
		{
			w = atan((x-m1)/(z-m3)); // old

			if (p[0] == 3)
				w-=180;
			if (p[0] == 4)
				w+=180;

		}
		//System.out.print(" w = " + (int)w+ " ");
		return w;
	}
	public double getW(Vertex V)
	{
		double v1 = getV(V);
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
		double w;
		double m1 = m[1];
		double m2 = m[2];
			double m3 = m[3];
		double w1 = getW1(V);
		// > 0 = true to increase
	//	boolean[] b = {false,true,false,true};
	//	boolean B = false;
	double[] p = new double[3];
		p[0] = getWQuad(V);

		if (!(p[0] == 1 || p[0] == 2 || p[0] == 3 || p[0] == 4))
		{
			w = p[0];

		}
		else
		{

		w =  atan((x-m1)/((z-m3)*cos(v1) + (m2-y)*sin(v1))); // old
	

		if (p[0] == 3)// && wq == 3)
				w-=180;
			if (p[0] == 4)// && wq == 4)
				w+=180;

		}
		return w;
		//return atan(((m1-x)*cos(v1) - (m3-z)*sin(v1)) / ( (m1-x)*sin(v1) - (m3-z)*cos(v1)     ) );


	}
	public double getW2(Vertex V)
	{

		double v1 = getV(V);
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
		double w;
		double m1 = m[1];
		double m2 = m[2];
			double m3 = m[3];

	double[] p = new double[3];
		p[0] = getWQuad(V);

		if (!(p[0] == 1 || p[0] == 2 || p[0] == 3 || p[0] == 4))
		{
			w = p[0];

		}
		else
		{
		double w1 = getW1(V);
		double Dist = Math.sqrt(Math.pow(x-m1,2)+Math.pow(y-m2,2)+Math.pow(z-m3,2));

		w = atan((x-m1)/(z-m3));
		w*=(100/Dist);


		if (p[0] == 3)
				w-=180;
			if (p[0] == 4)
				w+=180;
		}
		return w;



	}
	public double getV1(Vertex V)
	{
		double[] p = new double[3];
		p[2] = getVQuad(V);
		double x = V.getX();
		double z = V.getZ();
		double y = V.getY();
		double v = 0;
		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];
		//double dz = Math.sqrt((m3-z)*(m3-z) + (m1-x)*(m1-x));
		//double dy = Math.sqrt((m2-y)*(m2-y) + (m1-x)*(m1-x));
		//double dist = Math.sqrt((m1-x)*(m1-x) + (m2-y)*(m2-y));
		if (!(p[2] == 1 || p[2] == 2 || p[2] == 3 || p[2] == 4))
		{
			v = p[2];
			//v*=cos(dz/dy);
			return v;
		}
	v = atan((m2-y)/(m3-z));//v*=cos(dz/dy);
	//v*=(1-atan(m1-x));
//	v*=(1-(atan((m1-x)/m1)));

	//v *= (1 - Math.abs(m1-x)/dist);



	//v*= (1 - )
	/*if (m3-z == 0)
		System.out.println("Undefined!");*/
		if (p[2] == 3)
			v-=180;
		if (p[2] == 4)
			v+=180;
		return v;
	}
	public double getV(Vertex V)
	{

			double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
		double w;
		double m1 = m[1];
		double m2 = m[2];
			double m3 = m[3];
			double m5 = m[5];
			double v1 = getV1(V);
	//	System.out.println((y-m2)/((z-m3)*cos(w1) + (x-m1)*sin(w1)));
	double dist = Math.sqrt((x-m1)*(x-m1)+(z-m3)*(z-m3));
	//double v = atan((y-m2)/((z-m3)*cos(w1) + (x-m1)*sin(w1)));
	/*if (v == atan((y-m2)/dist))
	System.out.println("equal");*/
	return atan((y-m2)/dist) -m[5] ;
	//	return v;
		//return atan(-(m2-y)/((m3-z)*cos(w1) + (m1-x)*sin(w1)));




	}
	public double getVQuad(Vertex V)
	{
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
			double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

		double[] p = new double[3];

		for (int c = 0; c < 3; c++)
		{
			p[c] = 555;
		}

		if (z>m3 && y > m2)
			p[2]=2;
		if (z<m3 && y > m2)
			p[2] = 3;
		if (z<m3 && y < m2)
			p[2] = 4;
		if (z > m3 && y<m2)
			p[2] = 1;
		if (z == m3 && y < m2)
			p[2] = -270;
		if (z == m3 && y > m2)
			p[2] = -90;
		if (y == m2 && z < m3)
			p[2] = -180;
			//p[2] = 0;

		if (y == m2 && z > m3)
			p[2] = -0;
		if (p[2] == 555)
			System.out.println("oops");
		return p[2];

	}
	public int fixWQuad(double w)
	{
		int q;
		if (w > 0)
		{
			if (w < 90)
				q = 1;
				else
					q = 4;

		}
		else
		{
			if (w > -90)
				q = 2;
				else
					q = 3;

		}
		return q;
	}
	public double getWQuad(Vertex V)
	{
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();

		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

		if (x > m1 && z > m3)
			p[0] = 1;
		if (x < m1 && z > m3)
			p[0] = 2;
		if (x < m1 && z < m3)
			p[0] = 3;
		if (x > m1 && z < m3)
			p[0] = 4;

		//--
		if (x == m1 && z > m3)
			p[0] = 0;
		if (z == m3 && x < m1)
			p[0] = -90;
		if (z == m3 && x > m1)
			p[0] = 90;
		return p[0];


	}

	public void line(double x1, double y1, double x2, double y2, Color Color)
	{
		//Graphics2D g = (Graphics2D)(getGraphics());

		g.setPaint(Color);
		double height = (double)(getSize().height);
		//if (!(OOB(y1,x1) && OOB(y2,x2)))
		g.draw(new Line2D.Double(x1,height-y1,x2,height-y2));

	}

	public void line(double x1, double y1, double x2, double y2, double width, Color Color)
	{
		//Graphics2D g = (Graphics2D)(getGraphics());

		g.setPaint(Color);
		double height = (double)(getSize().height);
		for (double c = x1-width/2; c <= x1+width/2; c++)
		{
			for (double r = y1-width/2; r <= y1+width/2; r++)
			{
				g.draw(new Line2D.Double(c,height-r,c,height-r));
			}
		}
		//g.draw(new Line2D.Double(x1,height-y1,x2,height-y2));
	}

	public void line(int x1, int y1, int x2, int y2, Color Color)
	{
		g.setPaint(Color);
		g.draw(new Line2D.Double((double)x1,(double)y1,(double)x2,(double)y2));
	}

	public void keyTyped(KeyEvent e)
	{

	}
	public void keyReleased(KeyEvent e)
	{
		String s = e.getKeyText(e.getKeyCode()).toLowerCase();
for (int x = 0; x < DOWN.length; x++)
{
	if (s.equals(DOWN[x]))
		down[x] = false;
}
	}





	public static void main(String[]args) throws Exception
	{

		ArrayList<JFrame> windows = new ArrayList();
windows.add(new JFrame("3D World"));
  for (JFrame f : windows)
    {
	World applet = new World();

	f.getContentPane().add(applet);
    applet.init();
    f.pack();
   // f.setSize(new Dimension(1280, 750));
   //f.setSize(new Dimension(800,600));
   f.setSize(new Dimension(800,600));
   // f.setSize(new Dimension(750, 750));
	f.show();

    f.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

	}
}
}