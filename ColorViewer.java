import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;
import java.util.*;
import java.awt.image.BufferedImage.*;

import java.io.*;
import java.util.*;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorViewer extends JApplet implements KeyListener
{
	float one = (float)0.00001;
	float two = (float)0.00001;
	float three=(float)0.00001;

	float i = (float) 0.01;
	public void init()
	{
		setBackground(Color.BLACK);
		addKeyListener(this);
		setSize(1280,800);



	}
	public void paint()
	{
		Graphics2D g = (Graphics2D)(getGraphics());

		g.clearRect(0,0,getSize().width-1,getSize().height-1);
		//System.out.println((getSize().width-1)*(getSize().height-1));
		setBackground(Color.BLACK);


		Color c = new Color((float)one, (float)two, (float)three);

		int x = getSize().width-1;
		int y = getSize().height-1;

		int size = (int)(getSize().width/2);
g.setColor(c);
for (int r = 0; r < getSize().height-1; r++)
{
	g.draw(new Line2D.Double((double)0,(double)r,(double)(getSize().width-1),(double)r));
}

		//line(x,y,x,y,size,c);

		System.out.println("\nOne = " + one + "\nTwo = " + two + "\nThree = " + three);




	}
	public void line(double x1, double y1, double x2, double y2, double width, Color color)
	{
		Graphics2D g = (Graphics2D)(getGraphics());

		g.setPaint(color);
		int height = getSize().height;
		for (double c = x1-width/2; c <= x1+width/2; c++)
		{
			for (double r = y1-width/2; r <= y1+width/2; r++)
			{
				g.draw(new Line2D.Double(c,height-r,c,height-r));
			}
		}
		//g.draw(new Line2D.Double(x1,height-y1,x2,height-y2));
	}
	public void line(int x1, int y1, int x2, int y2, int width, Color color)
	{
		line((double)x1,(double)y1,(double)x2,(double)y2, (double)width, color);
	}
	public void keyPressed(KeyEvent e)
	{
		String s = (e.getKeyText(e.getKeyCode())).toLowerCase();



		if (s.equals("q") && one < 1-i-0.001)
			one+=i;
		if (s.equals("w") && two < 1-i-0.001)
			two+=i;
		if (s.equals("e") && three < 1-i-0.001)
			three+=i;
		if (s.equals("a") && one > 0+i+0.001)
			one-=i;
		if (s.equals("s") && two > 0+i+0.001)
			two-=i;
		if (s.equals("d") && three > 0+i+0.001)
			three-=i;

		/*if (one < 0)
		{
			one = 0.01;
		}
		if (two < 0)
		{
			two = 0.01;
		}
		if (three < 0)
		{
			three = 0.01;
		}
		if (one > 0)
			one = 0.09*/



		paint();
	}
	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}


	public static void main(String[]args)
	{
		ArrayList<JFrame> windows = new ArrayList();
	windows.add(new JFrame("My Color Viewing Window"));
//windows.add(new JFrame("Window Two"));
    //JFrame f = new JFrame("Steven's Cube");
    for (JFrame f : windows)
    {
	ColorViewer applet = new ColorViewer();

	f.getContentPane().add(applet);
    applet.init();
    f.pack();
    f.setSize(new Dimension(1280, 750));
	f.show();

    f.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });




	}
}
}
