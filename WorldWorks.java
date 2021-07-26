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

public class WorldWorks extends JApplet implements KeyListener, MouseListener , ActionListener, MouseMotionListener
{ // shapes and vertices
//boolean flux = false;
private double rot = 0;
	int current;
	private User user;
	private double[] m;
	private Timer t;
	private Robot bot;
	private boolean mouseFocus = false;
	private ArrayList<Shape> shapes = new ArrayList();
	ArrayList<Color> colors = new ArrayList();

	String[] DOWN = "b1 b2 b3 a b c d e f g h i j k l m n o p q r s t u v w x y z escape delete shift space 1 2 3 4 5 6 7 8 9 0 - = ` [ ] ; ' , . / left right up down backspace enter".split(" ");
	boolean[] down = new boolean[DOWN.length];

	public WorldWorks() throws Exception
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
	int mode = 2;

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
// Fuck i accidently deleted some code, ignore this whole part
	{
		m = user.M();

		/*double x1 = V.getX() - m[1];

		double y1 = V.getY() - m[2];

		double z1 = V.getZ() - m[3];

		double h = m[4];*/


		//h = 0;

		//return atan(x1 / Math.sqrt(y1*y1 + z1*z1)) - h;
		//return atan(x1 / z1) - h;
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
		//System.out.println(XY);

		//double out = -(atan(z / XY) - gw);
		/*double out = atan(z / XY);
		if (x < 0)
			out = atan(-z/XY);*/
		double out = atan(x/XY);

		return out - m[4];

			//System.out.println(out);
			//return out;
		/*double[] p = new double[3];
		p[0] = getWQuad(V);
		if (p[0] == 3)
				out-=180;
			if (p[0] == 4)
				out+=180;*/

		//return -(out - gw);



		/*if (x > 0)
		return 90-out-m[4];
		else{



			return -90-out-m[4];}*/

	/*	double x2 = V.getX() - m[1];

		double y2 = V.getY() - m[2];

		double z2 = V.getZ() - m[3];

		double g = m[4];
		double v = m[5];/////////////////////////////////////@@@camera(mx,my,mz,mx+cos(vg)*sin(hg),my+sin(vg+PI),mz+cos(vg)*cos(hg+PI),0,1,0); // rightside up

		x1*= cos(g);

		z1*= cos(g) * Math.abs(cos(v));
		x2*= sin(g) * Math.abs(cos(v));

		z2*= -sin(g);

		y1*= sin(v);

	//	System.out.println("x1 = " + x1 + " z2 = " + z2);

		//System.out.println("x1 = " + x1 + "z2  = " + z2 + " = " + (x1+z2));

		if ((current == 1 || current == 2) && print)
			System.out.println(atan((x1 + z2)/Math.sqrt(x2*x2 + y1*y1 + z1*z1))); // RIGHT OVER FRONT

		return atan((x1 + z2)/Math.sqrt(x2*x2 + y1*y1 + z1*z1));*/

	}
	public double gV(Vertex V) // this value is subtracted; positive values are near the top of the screen.
	{
		m = user.M();

		double y = V.getY() - m[2];
		double x = V.getX() - m[1];
		double z = V.getZ() - m[3];

/*x+cos(vg)*sin(hg),
			y+sin(vg+pi),
			z+cos(vg)*cos(hg+pi),*/



		double gv = -m[5];
		//gv = 0;

		double gw = -(m[4] - 90);

//		y*=this.sin(gv-90);
//x*=this.cos(gv)*this.sin(gw);
//z*=this.cos(gv)*this.cos(gw);

		//gw = 90;

		// new 0 = old 90,  new 90 = old 0, new 180 = old -90, new 270 = old -180

		// new = -(old - 90);
		// -new = old - 90
		// old = -new + 90

		// new 180 = old -90

		// new 270 = old +-180
		/*double[] look = {cos(gw)*cos(gv), sin(gv), sin(gw)*cos(gv)}; // is NOT reduced.

		double ma = Math.sqrt(look[0]*look[0]+look[1]*look[1]+look[2]*look[2]);

		for (int i =0; i<3;i++)
			look[i]/=ma;

		//double[] point = {x*cos(gw), y, z*sin(gw)};
		double[] point = {cos(gw), y, sin(gw)};

		double mag = Math.sqrt(point[0]*point[0] + point[1]*point[1] + point[2]*point[2]);

		for (int i = 0; i < 3; i++)
		point[i]/=mag;

		double scalar = 0;
		for (int i = 0; i < 3; i++)
			scalar+=look[i] * point[i];

		System.out.println(acos(scalar));

		return acos(scalar);*/

		//double T = -1/tan(gv);
		//Z = -(X - x) / tan(gv)  + z;
		//new Line2D.Double(x - 10, Z of (x-10), x+10, Z of (x+10));
		double XZ;
		/*if (Math.abs(gw) == 90)
		{
			XZ = z;
			return y / XZ;
		}else if (gw == 0 || Math.abs(gw) == 180)
		{
			XZ = x;
			return y / XZ;
		}
		else{*/

		//Line2D.Double T = new Line2D.Double(x - 10, -(x - 10 - x) / tan(gw) + z, x + 10, -(x + 10 - x) / tan(gw) + z);

		Line2D.Double T = new Line2D.Double(
			x - 10, 10 / tan(gw) + z,
			x + 10,	-10 / tan(gw) + z);





		XZ = T.ptLineDist(0,0);
		//System.out.println(XZ);
		//double Y = y;
		// decreasing a to increasing a
		// when it is working correctly, a is increasing in absolute value.
		// so, when it breaks, the angle maxes out and becomes smaller.
		// it should max out but then add the difference between the max and the smaller angle.
		/*if (vertexBehind)
		{
			if (y < 0)
			return -45 + (atan(y/XZ));
			if (y > 0)
				return 45 + atan(y/XZ);
				//return (-90);
		}*/
		return y / XZ;
//		return y / Math.sqrt(x*x + z*z);
	//	return y / XZ;// - gv     *y/XZ;  == (y/XZ)*(1-gv);
		//}




		/*double y2 = y1;
		double x2 = x1;
		double z2 = z1;

		double g = m[4];
		double v = m[5];

		y1*= cos(v);

*/
		/*x1*=-sin(v)*sin(g);
		y1*=cos(v);
		z1*=sin(v)*cos(g);

		x2*=Math.abs(cos(v))*sin(g);
		y2*=sin(v);
		z2*=Math.abs(cos(v))*cos(g);

		return atan((x1 + y1 + z1) / Math.sqrt(x2*x2 + y2*y2 + z2*z2));*/
/*
		z1 *= cos(g) * Math.abs(cos(v)); // front
		x1 *= sin(g) * Math.abs(cos(v)); // front
		y2 *= sin(v); // front

		y1 *= cos(v); // above
		z2 *= sin(v) * cos(g);
		x2 *= sin(v) * sin(g);*/
		// v = 90 && g = 0, z is above
		// v = 90 && g = 90, x2 is above
	//	System.out.println("above = " + "y1 = " + y1 + " z2 = " + z2 + " x2 = " + x2);



	//	if ((current == 1 || current == 2) && print)
	//		System.out.println(atan((y1 + Math.sqrt(x1*x1+z1*z1))/(Math.sqrt(x1*x1+z1*z1))));
	//	return atan((y1 + z2 + x2)/(Math.sqrt(x1*x1+z1*z1))); // above / front

	//return atan((y1) / (Math.sqrt(x1*x1 + z1*z1))) + v;



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
//System.out.println ("x = " + e.getX() + "    " + "y = " + e.getY());
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
		//System.out.println(e.getButton());
		//System.out.println(shapes.size());
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

	//	setSize(1280,800);
	//	setSize(800,800);

		//setSize(800,800);
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

		/*	m = user.M();
		if (this.se("m"))
		{
			move();
		}

		if (this.se("escape"))
		{
			if (mode == 0)
			mode = 1;
			else
				mode = 0;
		}

			if (this.se("p"))
				print = !(print);
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


		if (this.se("left") || this.se("back quote"))
		{
			user.move(4,false,user.getHorizontalSensitivity());//left of me = negative

		}
		if (this.se("right"t) || this.se("2"))
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
				user.set(x,0);
			}
		}
if (!(this.se("m")))
paint();

*/

	}

	public void move()
	{

		Scanner scan = new Scanner(System.in);
		System.out.println("x, y, z");
		user.set(1, scan.nextDouble());
		user.set(2, scan.nextDouble());
		user.set(3, scan.nextDouble());


		//public void set(int which, double number)



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
	//currenttime = System.currentTimeMillis();
	//System.out.println(currenttime);
	user.beginMoving();
	/*if (mode != 2)
	user.move(2,false,user.getFallSpeed());*/ /// THE IMPORTANT ONE
	runs++;
	lastClick++;


	//user.move(2,false,1);

	m = user.M();
	/*if (m[2] < 3.75+2.5){user.set(2,3.75+2.5);
	m = user.M();}*/

	/*if (runs%1 == 0){

	for (int i = 0; i < shapes.size(); i++)
	{
		shapes.get(i).t(runs);
		shapes.get(i).updateAtt();
		shapes.get(i).defineVertices();
	}}*/

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


//lasttime = currenttime;





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

	//if (last[2] >= attempt[2])
	//{

	//}
	/*else
	{
		Ypath = new Line2D.Double(0,last[2] - 3.75, 0, attempt[2] + 0.25);
		//Ypath = new Line2D.Double(0,last[2] + 0.25, 0, attempt[2] - 3.75);
	}*/


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

			/*Face f = null;
			//if (s instanceof Cube)
			//f = s.getFaces().get(2); // top face
			if (s instanceof Wall)
			f = s.getFaces().get(0);

			ArrayList<Vertex> v = s.getVertices();
			int[] corners = f.getCorners();


			int[] xs = new int[f.getCorners().length];
			int[] zs = new int[f.getCorners().length];
			for (int i = 0; i < xs.length; i++)
			{
				xs[i] = (int)v.get(corners[i]-1).getX();
				zs[i] = (int)v.get(corners[i]-1).getZ();
			}

			Polygon p = new Polygon(xs,zs,xs.length);
			double y;
			//if (s instanceof Cube)
			//y = s.getY() + s.getSize()/2;// the three intersection coordinates.
			//else //if (s instanceof Wall)
			y = s.getY(); // needs to be worked on

			double m = (attempt[2]-last[2]) / (attempt[1] - last[1]);

			double x = last[1] + (y - last[2]) / m;

			m = (attempt[2]-last[2]) / (attempt[3] - last[3]);

			double z = last[3] + (y - last[2]) / m;

			if (new Polygon(xs,zs,xs.length).contains((int)x,(int)z))
			//if (new Polygon(xs,zs,xs.length).intersects(new Ellipse2D.Double(x-1.25,z-1.25,2.5,2.5)))
			//if (new Ellipse2D.Double(x-1.25,z-1.25,2.5,2.5).intersects(new Polygon(xs,zs,xs.length).getBounds()))
			{
				//System.out.println("It worked for once!");
				//user.set(1,x);
				user.set(2,y+3.8);
				//user.set(3,z);
				p.invalidate();
				//return;
			}*/


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
		//g.setPaint(new Color(146,181,220));
		//line(x-10,y,x+10,y,c);
		//line(x,y-10,x,y+10,c);

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
		//Color bg = Color.BLACK;
Color bg = new Color((float)0.53,(float)0.81,(float)0.92);
setBackground(bg);
		bi = new BufferedImage(getSize().width, getSize().height, 5);//5 or 2
		//Graphics2D g = (Graphics2D)(getGraphics());
		//g.clearRect(0,0,getSize().width-1,getSize().height-1);
	//	BufferedImage bi = new BufferedImage(getSize().width,getSize().height,5);
		g = bi.createGraphics();

		//System.out.println(g.getClip());
		//setBackground(new Color((float)0.53,(float)0.81,(float)0.92)); // sky blue.
		//setBackground(Color.GRAY);

ArrayList<Color> c2 = new ArrayList<Color>();
c2.add(Color.RED);
c2.add(Color.BLUE);
c2.add(Color.GREEN);
int three = 0;
		drawShapes();
		/*if (middleindex != -1)
		System.out.println(middleface);*/
		g.setPaint(Color.WHITE);
		g.drawString(user.toString(),50,200);
		drawCrosshair();
	//	line(getWidth()-10,getHeight(),get)
		Graphics2D g2 = (Graphics2D)(getGraphics());
		if (mode == 2){

		for (int x = 0; x < bi.getWidth(); x+=5)
			for (int y = 0; y < bi.getHeight(); y+=5){

				three+=(int)(3*Math.random());
				if (bi.getRGB(x,y) == bg.getRGB())//colors.get(0).getRGB())
				bi.setRGB(x,y,c2.get(three%3).getRGB());
			}}

		//if (mode == 2){g2.setPaint(new Color(200,50,10,123));g2.fillRect(0,0,100,100);}
		g2.drawImage(bi,null,0,0);



		/*g.setPaint(RC());
		g.fillPolygon(xs, ys, 8);*/
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
		/*double A1;
		double A2;
		double B1;
		double B2;*/
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
		//ds[x] = distance(new Vertex(m[1],m[2],m[3]), fs.get(x).getCenter());
		//System.out.println("eye.lengh = " + EYE.length);
		//System.out.println(EYE[EYE.length-1]);
		//if (shapes.get(x) instanceof Wall)
		//	shortestDistance[x] = distance(new Vertex(m[1],m[2],m[3]),fs.get(0).getCenter());
		//	else
		shortestDistance[x] = distance(new Vertex(m[1],m[2],m[3]), fs.get(EYE[EYE.length-1]).getCenter());
	}
	stopp = 0;
	for (int i= 0; i < shortestDistance.length; i++)
	{
		if (shortestDistance[i] > 400)
			stopp++;
	}

	//sort the shapes back to front... biggest distance to shortest distance
//int[] shapeOrder = new int[shapes.size()];
boolean once = false;
/*for (int i = 0; i < shortestDistance.length; i++)
{
	System.out.println("["+shortestDistance[i]+"]");
}*/
for (int x = 0; x < shortestDistance.length; x++)
{
	int lower = 0;
	//int equal = 0;
	for (int c = 0; c < shortestDistance.length; c++)
	{
		if (shortestDistance[x] >= shortestDistance[c])
			lower++;

		/*if (shortestDistance[x] == shortestDistance[c])
			equal++;*/

	}
	//shapeOrder[shapeOrder.length-1-lower] = shortestDistance[x];


	//if (equal == 0)
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

	/*else-
	{
		for (int i = seq.length-1-lower; i < seq.length; i++)
		{
			if (seq[i] == 555)
			{

			}
		}


	}*/



}






for (int J = 0 + stopp; J < shapes.size(); J++)
{

	int x = seq[J];
	//System.out.println(seq[0]);
	/*System.out.println(seq.length);
	System.out.println(shapes.get(0).getIdentifier());*/
	//vertexBehind = true;
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
//if (Math.abs(gw%90) + 1 < 47 && Math.abs(gw%90) + 1 > 44)
//System.out.println(new Line2D.Double(
		//	xc - 10, 10 / tan(gw) + zc,
		//	xc + 10,	-10 / tan(gw) + zc).ptLineDist(0,0));
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
	/*System.out.println("\n");
	for (int eff = 0; eff < EYE.length; eff++)
	{
		System.out.print("["+EYE[eff]+"]");
	}*/

	/*colors.add(Color.RED);
	colors.add(Color.PINK);
	colors.add(Color.GREEN);
	colors.add(Color.YELLOW);
	colors.add(Color.ORANGE);
	colors.add(Color.BLUE);*/
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

//vertices = shapes.get(x).getVertices();








		}



	}
renderShapes();

}
public void renderShapes()
{
	//boolean f = false;
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
/////!drawshapes
public void fill(int[] aa, int[] bb, Color cc)
{
	//Graphics2D g = (Graphics2D)(getGraphics());
//	Image image = Toolkit.getDefaultToolkit().getImage("image.gif");
	///image = new ImageIcon("image.gif").getImage(); // makes sure it loads correctly ?
//	TexturePaint p = new TexturePaint(image, null);
	g.setPaint(cc);
	g.fillPolygon(bb,aa,bb.length);
}
/*public void fill(int[] aa, int[] bb, Color cc)
{
	Graphics2D g = (Graphics2D)(getGraphics());
	g.setPaint(cc);
	g.fillPolygon(bb,aa,bb.length);
}*/

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
	/*for (int x = 0; x < ds.length; x++)
	{
		System.out.print("["+ds[x]+"]\n");
	}*/
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
		//System.out.println();
		/*for (int x = 0; x < order.length; x++)
		{
			System.out.print("["+order[x]+"]");
		}*/
		i++;

	}

//	for (int x = 0; x < order.length; x++)
//	{
//		System.out.print("\n"+(x+1)+":   ["+ds[order[x]]+"]");
//	}

	return order;




	/*for (int i = 1; i < fs.size(); i++) // i stole this code off a website, out of laziness. it's insertion sorting. least to greatest.
	{
    int j = i;
    double B = ds[i];
    while ((j > 0) && (ds[j-1] > B)){
    ds[j] = ds[j-1];
    j--;
    }
    ds[j] = B;
    }*/








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




	// this used to be part of DrawShapes vv
	/*startingPoints = shapes.get(x).getStartingPoints();
	endingPoints = shapes.get(x).getEndingPoints();
	int SIZE = shapes.get(x).getVertices().size();
	vertices = shapes.get(x).getVertices();

	int[] As = new int[SIZE];
	int[] Bs = new int[SIZE];

	int[] tp = shapes.get(x).getPolySequence();
	int CCC;
	for (int c = 0; c < tp.length; c++)
	{
		CCC = tp[c] - 1;
		As[c] = (int)(AlignA(vertices.get(CCC)));
		Bs[c] = (int)(AlignB(vertices.get(CCC)));
	}*/
	/*polyLine(As,Bs);
	System.out.println(As.length);*/





	/*for (int l = 0; l < startingPoints.length; l++)
	{


		A1 = AlignA(vertices.get(startingPoints[l]-1));
		B1 = AlignB(vertices.get(startingPoints[l]-1));
		A2 = AlignA(vertices.get(endingPoints[l]-1));
		B2 = AlignB(vertices.get(endingPoints[l]-1));
		line(B1,A1,B2,A2,Color.BLACK);


		//System.out.println("A = " + A1 + "   B = " + B1);

	}*/
	////////////////////@@@@@@@@@@@@@ temp code


	//vertices = shapes.get(0).getVertices();

	/*for (int c = 0; c < 8; c++)
	{
		xs[c] = (int)(AlignB(vertices.get(c)));
		ys[c] = (int)(AlignA(vertices.get(c)));
	}*/









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
	public static double AlignA(Vertex V, double[] m, double width, double height)
	{
		double A;
		double s = width/2;
		double v = gV(V,m);


		A = height/2 - s*v;

		return A;

	}
	public static Vertex rotate(Vertex V , double o)
	{
double[][] rot = new double[3][3];/*new{cos(o),-sin(o),0,
		sin(o),cos(o),0,
		0,0,1
};*/
rot[0][0] = cos(o);
rot[0][1] = -sin(o);
rot[0][2] = 0;
rot[1][0] = sin(o);
rot[1][1] = cos(o);
rot[1][2] = 0;
rot[2][0] = 0;
rot[2][1] = 0;
rot[2][2] = 1;

double[] ver = {V.getX(),V.getY(),V.getZ()};

double[] out = new double[3];


for (int r = 0; r < 3;  r++)
{
	float temp = 0;
	for (int c = 0; c < 3 ; c++)
	{
		temp += rot[r][c] * ver[c];
	}
	out[r] = temp;
}

return new Vertex(out[0],out[1],out[2]);


	}
	public static double gV(Vertex V, double[] m)
	{
			//m = user.M();

		double y = V.getY() - m[2];
		double x = V.getX() - m[1];
		double z = V.getZ() - m[3];

		double gv = -m[5];
		double gw = -(m[4] - 90);

double XZ;

Line2D.Double T = new Line2D.Double(x - 10, -(x - 10 - x) / pointTest.tan(gw) + z, x + 10, -(x + 10 - x) / pointTest.tan(gw) + z);
		XZ = T.ptLineDist(0,0);

return y / XZ;


	}
	public static double AlignB(Vertex V, double[] m, double width, double height)
	{
		double B;
		//double[] m = user.M();
		double s = width/2;
		double v = fixW(getW1(V, m), m);//getW1(V) - m[4];

B = s+s*pointTest.tan(v);

	return B;

	}
	public static double fixW(double w, double[] m)
	{
		double g = m[4];
		w-=g;
		while (w > 180)
			w -= 360;
		while (w < -180)
			w+= 360;
		return w;
	}
	public static double getW1(Vertex V, double[] m)
	{
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();
		double w;
		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

double[] p = new double[3];
		p[0] = getWQuad(V, m);

		if (!(p[0] == 1 || p[0] == 2 || p[0] == 3 || p[0] == 4))
		{
			w = p[0];
		//	w*= cos(dx/dz);
		}
		else
		{
			w = pointTest.atan((x-m1)/(z-m3)); // old
if (p[0] == 3)
				w-=180;
			if (p[0] == 4)
				w+=180;
}
return w;



	}
	public static double getWQuad(Vertex V, double[] m)
	{
		double x = V.getX();
		double y = V.getY();
		double z = V.getZ();

		double m1 = m[1];
		double m2 = m[2];
		double m3 = m[3];

		double p = -1;

		if (x > m1 && z > m3)
			p = 1;
		if (x < m1 && z > m3)
			p = 2;
		if (x < m1 && z < m3)
			p = 3;
		if (x > m1 && z < m3)
			p = 4;

		//--
		if (x == m1 && z > m3)
			p = 0;
		if (z == m3 && x < m1)
			p = -90;
		if (z == m3 && x > m1)
			p = 90;
		return p;
	}
	public double AlignA(Vertex V)//camera(mx,my,mz,mx+cos(vg)*sin(hg),my+sin(vg+PI),mz+cos(vg)*cos(hg+PI),0,1,0); // rightside up
	{
		double A;
		/*F = 90;*/
		double s = getSize().width/2;

		double v = gV(V);
		/*if (vertexBehind)
			v = -v;
*/
		//if (current == 1 && print)
		//if (print)
		//	System.out.println(current + " :: " + (getSize().height/2 - s*v));


//double g = m[5];//vertical orientation
//v -= g;
//v = tan(atan(v) + g);





		/*v+=g;
		// vvvvvv THIS IS CORRECT vvvvvvv
		if (v < -90)
		{
			v+= (-90 - v)*2;
		}
		if (v > 90)
		{
			v-=(v-90)*2;
		}*/
		/*if (v < -45)
		{
			v+= (-45 - v)*2;
		}
		if (v > 45)
		{
			v -= (v-90)*2;
		}*/
		//System.out.println(v);

		//System.out.println("getSize().height/2 = " + getSize().height/2 + "\ns = " + s + "\nv = "+v+"\ng = "+g);

		/*if (g >= 135 && v < 0)
		{
			v+=360;
		}
		if (g <= -135)
		{
			if (v > 0)
				v-=360;

		}
*/
		/*if (v<=g+45 && v>=g-45)
		{*/
			//A = (getSize().height/2-s*(v/(45))); // if vertexY > mY, it is a negative difference,.

			A = getSize().height/2 - s*v;

			/*if (vertexBehind)
			{
				//System.out.println(A + " , " + (getSize().height/2 - 3*s*v));

				//A = getSize().height/2 + 3*s*Math.abs(v);
				A = getSize().height/2 + trans*s*v;//Math.abs(v);
			}*/




			//if (print)
			//System.out.println ("v = " + v);

			//System.out.print("It is in front of the user.\n");
			/*if (print && (current == 2))// || current == 3))
			{
				System.out.println(current + " :: " + vertexBehind + " " + (int)A + " X:"+ (int)(V.getX()-m[1])+" Y:"+(int)(V.getY()-m[2])+" Z:" + (int)(V.getZ()-m[3]));
			}*/
			return A;
		/*}
		else if ((v>= 135 && (v <= 180 || v == -180)) || (v <= -135  && (v >= -180 || v == 180))) //(v<=-g+45 && v>=-g-45)//else if (v<=-g+45 && v>=-g-45)
		{
			A = (getSize().height/2-s*(v/(g+45)));
			System.out.println("1");
			//A = -555;

			return A; // A would be the same value, except it is behind where the user is looking. </
		}
		else if (v <= g+F && v >= g) // this means that it is on the bottom half of the screen, in front of the user
		{
			A = (-s - s*(((180-v)-45)/(g+F))); // correct
			//A = s+2*(45+s*((v-g)/(45)));

		//	A = (getSize().height/2-2*s*(v/g+45));
		//	A = 555;
			return A;
		}
		else if (v >= g && v <= g + 2*F)// it is behind and below the user.
		{


			A = (-s - s*(((180-v)-45)/(g+F))); // random guess copy, 1
			System.out.print("2");
			return A;


		}
		else if (v >= g - F) // this means that it is on the top half of the screen, in front of the user
		{
			//A = s + s*(-v-45)/(g+F);
			//A = s + s*()
			A = (s + s*(((180-v)-45)/(g+F))); // correct
			//B = (2*s+s*((v-45)/(g+F)));
			return A;


		}
		else
		{
			//it is behind and above the user.
			A = 555;
			System.out.print("3");
			System.out.println("v = " + v);
			A = (s + s*(((180-v)-45)/(g+F))); // random guess copy, 2


			return A;
		}*/

		///A = 555;
	//	return A;
		//return A;
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


		//double v = gW(V);
	//	System.out.println(v);
		//v = fixW(v);


		//double g = m[4];
	//	double vcopy = v;
		//v-=g;

		/*if (g >= 135 && v < 0)
		{
			v+=360;
		}
		if (g <= -135)
		{
			if (v > 0)
				v-=360;

		}*/


		/*if (v<=g+45 && v>=g-45) //
		{*/
			//B = (cos(g)/cos(g)*s+s*(v/(g-g+45))); // this one is also correct
	//	if (v > 180)
	//		v -= 360;
	//	if (v < -180)
	//		v+= 360;




		//double tV = v;
		//double tG = g;
		/*if ((tV < -90 && tG > 90) || (tG <-90 && tV > 90))
	{

		if (tV < -90 && tG > 90)
		{
			tV+= 180;
			tG-= 180;
		}
		else if (tG < -90 && tV > 90)
		{
			tG+= 180;
			tV-= 180;
		}
		if (!(tV<=tG+45 && tV>=tG-45))
		{

			B = 2*s+1;
			return B;
		}
	}*/

			//v*=



			//B = s+s*(v/45)*cos((vcopy+90)*-1);


		//if (Math.abs(v) > 135)//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ Why was this here?
		//	v%=90;
		//double x = V.getX() - m[1];
		//double z = V.getZ() - m[3];
		//double gw = fixW(-(m[4]-90));

		/*if (Math.abs(gw) < 135 && Math.abs(gw) > 45)
		{
			return s + s*((x-z/tan(gw))/z);
		}
		else
			return s - s*((z-x*tan(gw))/x);*/

			//B = s+s*(v/45);
			B = s+s*tan(v);

		//	if (vertexBehind){System.out.println(B + "  " + (s-3*s*tan(v))); //B = s-3*s*tan(v);
		//	}
		if (vertexBehind)
		{
			B = s-trans*s*tan(v);
			//System.out.println(B + "  " + (s-3*s*tan(v)));
			//B = s-s*tan(v);
			//if (v < 0)
				//B = s-3*s*tan(v);
				//else
				//	B = s+2*s*tan(v);
		}

//if (print == true)
//		System.out.println(current + " :: " + vertexBehind + " " + B);
			return B;
			//return s+s*((V.getX() - m[1]) / (V.getZ() - m[3]));
			//return B;
		/*}
		else if ((v>= 135 && (v <= 180 || v == -180)) || (v <= -135  && (v >= -180 || v == 180))) //(v<=-g+45 && v>=-g-45)
		{/////////-?b
		System.out.println("It is currently behind the user, but onScreen.");
			B = (cos(g)/cos(g)*s+s*(v/(g-g+45)));
			return B; // A would be the same value, except it is behind the user. </
		}
		else if (v <= g+F && v >= g)
		{
			//B = 2*s*(1 + v/(g + F));
			//System.out.println("It has reached the right boundary and is in front of the user.");
			B = (cos(g)/cos(g)*2*s+s*((v-45)/(g-g+F)));// this one is correct

			//B = -1;
			return B;
		}
		else if (v >= g && v <= g + 2*F)
		{
			//B = 2*s*(1 + (180-v)/(g+F));
			//B = 0 - (s*((v+45)/(g-F)));
			//System.out.println("It has reached the right boundary but is behind the user");
			B = (cos(g)/cos(g)*2*s + s*(((180-v)-45)/(g-g+F))); // this one probably is not correct.
			//B = -1;
			B = getSize().width+1;
			return B;
		}
		else if (v >= g - F)
		{
			//B = -1;
			//System.out.println("It has reached the left boundary.");
			//B = 0+(-s*((v+45)/(g+F))); // this one is funny
			//B = 0 - (s*(((90)-(-v)+45)/(g+F))); i do not know why this one does not work.
			B = sin(g)*s - s*(-v-45)/(g-g+F); // this one is practically correct
			return B;
			//B = (v/(g+F));

		}
		else
			B = (-s*((v-45)/(g+F)));
			B = -1;
			//System.out.println("B = -1");
			//System.out.println("It has reached the left boundary, and it is behind the user.");
			return B;*/



	}



	/*public int getTotalVertices()
	{
		int	totalVertices = 0;
		//for (int x = 0; x < cubes.size(); x++)
		//{
		//	totalVertices+=cubes.get(x).getNumVertices();
		//}
		for (int x = 0; x < shapes.size(); x++)
		{
			totalVertices+=shapes.get(x).getNumVertices();
		}

		return totalVertices;
	}*/

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

		//double dist = Math.sqrt((m1-x)*(m1-x) + (m2-y)*(m2-y));
		//double dx = Math.sqrt((m1-x)*(m1-x) + (m2-y)*(m2-y));
		//double dz = Math.sqrt((m3-z)*(m3-z) + (m2-y)*(m2-y));

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


			//w = atan((x-m1)/Math.sqrt((z-m3)*(z-m3) + (y-m2)*(y-m2)));
		//System.out.println(cos(dx/dz))
			//w*= cos(dx/dz);
			///w = atan((dx)/(dz));


		//	w*=(1-Math.abs(atan(Math.abs(m2-y)/m2)));
			//w *= (1 - dist/(z-m3));

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
			//w1 = fixW(w1);
	/*	int wq = fixWQuad(w1);
		double Dist;
		double c;
		if (w1 > 0 == b[-1+wq])
		System.out.println (w1 > 0 == b[-1+wq]);
		if (w1 > 0 == b[-1+wq]) // if true, increase
		{
			B = true;
			c = (x-m1)/Math.abs(x-m1);

			w = atan((z-m3)/Math.sqrt(Math.pow(x-m1,2) + Math.pow(y-m2,2)));
			System.out.println(w);
		}
		else
		{
			c = (z-m3)/Math.abs(z-m3);
			w = atan(c*(x-m1)/Math.sqrt(Math.pow(y-m2,2) + Math.pow(z-m3,2)));
		}*/
		//double Dist = Math.sqrt(Math.pow(cos(w1)*(m2-y),2) + Math.pow(m3-z,2)); // latest
	//	double Dist = Math.sqrt(Math.pow(m2-y,2) + Math.pow(m3-z,2));
		//Dist = Math.sqrt(Math.abs(cos(w1))*Math.pow(m2-y,2) + Math.pow(m3-z,2));
		//double Dist = Math.sqrt(Math.pow(m1-x,2) + Math.pow(m2-y,2) + Math.pow(m3-z,2));
		w =  atan((x-m1)/((z-m3)*cos(v1) + (m2-y)*sin(v1))); // old
		//w = atan((x-m1)/(z-m3)*(90-atan(Math.abs(m2-y)/hDist))/90);
		//if (z >= m3)
	//double charge = (m3-z)/Math.abs(m3-z) * -1;
	//	w = atan((x-m1)/Dist*charge);
	//	w = atan((x-m1)/(z-m3));
		//w = asin((x-m1)/Dist*charge);
		//else
		//	w = 555;
		//else
		//w = atan(-(x-m1)/Dist);

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














	/*public void polyLine(int[] As, int[] Bs)
	{
		Graphics2D g = (Graphics2D)(getGraphics());
		g.setPaint(Color.BLACK);
		g.drawPolyline(Bs, As, As.length);
	}*/

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
	//public void line(int x1, int y1, int x2, int y2, int width, Color Color)
///	{
	//	line((double)x1,(double)y1,(double)x2,(double)y2, (double)width, Color);
//	}
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
/*Vertex v = new Vertex(1,1,0);

System.out.println(World.rotate(v, 45).toString2());*/


		ArrayList<JFrame> windows = new ArrayList();
windows.add(new JFrame("3D World -- best attempt yet"));
//windows.add(new JFrame("Window Two"));
    //JFrame f = new JFrame("Steven's Cube");
    for (JFrame f : windows)
    {
	WorldWorks applet = new WorldWorks();

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