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

public class World2 extends JApplet implements KeyListener, MouseListener, ActionListener
{
	//private ArrayList<Shape> shapes;

	private User user;
	private BufferedImage bi;
	private Graphics2D g;
	private Timer timer;
	private ArrayList<Wall> walls;
	public World2()
	{
		walls = new ArrayList<Wall>();
		defineShapes();
		user = new User(0,0,0,0,0);
		timer = new Timer(42,this);
		timer.setInitialDelay(5000);


	timer.start();}
	public void defineShapes()
	{

	}
	public void saveShapes(String name)
	{
		try{FileWriter f = new FileWriter(name);
		BufferedWriter write = new BufferedWriter(f);
for (Wall w : walls)
		{
			write.write(w.toFile());
			write.newLine();
		}
		}catch(Exception e)
		{

		}

	}


	public void init()
	{
		addKeyListener(this);
		setFocusable(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		paint();
	}
	public void paint()
	{
		bi = new BufferedImage(getSize().width, getSize().height, 5);
		g = bi.createGraphics();
		Color bg = Color.BLACK;
		setBackground(bg);
		//System.out.println(getSize().width + " , " +  getSize().height);

		// DRAW HERE

		// stop drawing here
		Graphics2D g2 = (Graphics2D)(getGraphics());
		g2.drawImage(bi,null,0,0);

	}
	public void keyPressed(KeyEvent e)
	{

	}
	public void mouseReleased(MouseEvent e)
	{

	}
	public void mouseClicked(MouseEvent e)
	{

	}
	public void mouseExited(MouseEvent e)
	{

	}
	public void mouseEntered(MouseEvent e)
	{

	}
	public void mousePressed(MouseEvent e)
	{

	}

	public void keyReleased(KeyEvent e)
	{

	}
	public void keyTyped(KeyEvent e)
	{

	}

	public static void main(String[]args)
	{
		JFrame frame = new JFrame("World2!");
		World2 world = new World2();
		frame.getContentPane().add(world);
		world.init();
		frame.setSize(1280, 750);
		frame.show();
		frame.addWindowListener(new WindowAdapter() {

      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
	}
}