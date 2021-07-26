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


public class SpriteCreator extends JFrame implements MouseListener, MouseMotionListener, KeyListener{

//private ArrayList<Boolean> methods;
private int x;
private int y;
//private int[][] grid;
private boolean[][] check;
private boolean erase = false;
private double[] encryption;
private boolean[][] next;
Scanner scan = new Scanner(System.in);
									// 0 = numrows, 1 = numcols, 2 = number.
							// 0 = placeholder, 1 = mouseDragged,
public void encrypt()
{
	int row1 = getEarliestRow();
	int row2 = getLatestRow();

	int col1 = getEarliestCol();
	int col2 = getLatestCol();

	next = new boolean[getLatestRow()-getEarliestRow() + 1][getLatestCol()-getEarliestCol() + 1];

	for (int r = 0; r <= (getLatestRow() - getEarliestRow()); r++)
	{
		for (int c = 0; c <= (getLatestCol() - getEarliestCol()); c++)
		{
			if (check[getEarliestRow()+r][getEarliestCol()+c])
				next[r][c] = true;
		}
	}

	for (int r = 0; r < next.length; r++)
	{
		for (int c = 0; c < next[0].length; c++)
		{
			if (next[r][c])
				System.out.print(1);
			else
				System.out.print(0);

		}
		System.out.println();
	}
	//String Name = "Sprite Test.txt";
	String Name = "";
	int cxx = 0;
	while (Name.length() == 0)
	{
		if (cxx > 0)
			System.out.println("You need to enter a valid file name...");
		if (cxx==0)
			System.out.println("\n\n\n ");
		System.out.println("Enter the name of the file you would like to save to.");
		Name = scan.next();
		Name = Name.toLowerCase();
		cxx++;
	}



	writeToFile(next, addTxt(Name), next.length, next[0].length);

}
public String addTxt(String name)
{
	if (!(name.length()>0))
		return "Error";

	if (name.length()>4)
	{
		if (name.substring(name.length()-4).toLowerCase().equals(".txt"))
			return name;
		else
			return name + ".txt";
	}
	return name + ".txt";



}
public static void writeToFile(boolean[][] sprite, String name, int rows, int cols)
{
try{
    	FileWriter fstream = new FileWriter(name);
    	BufferedWriter writer = new BufferedWriter(fstream);
    	System.out.println("You are now saving to a file.");
	String out = "";
	String written = "";
	written+=rows; // numRows
	written+="\r\n";
	written+=cols; //numCols
	//int count = 0;
	System.out.println("sprite.length= "+sprite.length);
    for (int r = 0; r < sprite.length; r++)
    {
    	for (int c = 0; c < sprite[0].length; c++)
    	{
			if (sprite[r][c])
				out+="a";
			else
				out+="b";

    	}
    	//count++;
    	written+="\r\n";
    	written+=out;
    	out = "";

    }
    	writer.write(written);/*Your String Here*/
    	writer.close();
    }catch(Exception e){
    	System.err.println("Error: " + e.getMessage());
    }




}
public int getEarliestRow()
{
	for (double i = 0; i < check.length; i++)
	{
		for (double f = 0; f < check.length; f++)
		{
			if (check[(int)i][(int)f])
			{
				return (int)i;
			}
		}
	}
	return -1;
}
public int getLatestRow()
{
	for (double i = check.length - 1; i >= 0; i--)
	{
		for (double f = 0; f < check.length; f++)
		{
			if (check[(int)i][(int)f])
			{
				return (int)i;
			}
		}
	}
	return -1;
}
public int getEarliestCol()
{
	for (double f = 0; f < check.length; f++)
	{
		for (double i = 0; i < check.length; i++)
		{
			if (check[(int)i][(int)f])
				return (int)f;
		}
	}
	return -1;

}
public int getLatestCol()
{
	for (double f = check.length-1; f >= 0; f--)
	{
		for (double i = 0; i < check.length; i++)
		{
			if (check[(int)i][(int)f])
				return (int)f;
		}
	}
	return -1;
}
public SpriteCreator()
{
setVisible(true);
	//getContentPane().setLayout( new FlowLayout() );/////////
//WindowUtilities.setNativeLookAndFeel();
	check = new boolean[100][100];

	setSize(1280,760);
	addMouseMotionListener(this);
	addKeyListener(this);
	addMouseListener(this);

	getContentPane().setBackground(Color.RED);//repaint();//System.out.println("it should have repainted")
	//getContentPane().getGraphics().clearRect(0,0,getSize().width,getSize().height);
//pack();
	drawGrid();




}
public void mouseExited(MouseEvent e)
{

}
public void mouseEntered(MouseEvent e)
{
	drawGrid();
//
}
public void drawGrid()
{
/*for (double X = 0; X < check.length*15-1; X++)
{
	for (double Y = 0; Y < check.length*15-1; Y+=15)
	{
		line(X,Y,X,Y);
	}


}*/
for (double Y = 0; Y < check.length*15-1; Y+=15)
{
	line(0,Y,(double)(check[0].length*15-1),Y);
}
for (double X = 0; X < check[0].length*15-1; X+=15)
{
	line(X,0,X,(double)(check[0].length*15-1));
}
/*for(double X = 0; X < check.length*15-1; X+=15)
{
	for (double Y = 0; Y < check.length*15-1; Y++)
	{
		line(X,Y,X,Y);
	}
}*/
}
public void clear()
{
	for (int i = 0; i < check.length; i++)
	{
		for (int c = 0; c < check[0].length; c++)
		{
			if (check[i][c])
				check[i][c] = false;
		}
	}
	System.out.println("The screen was cleared.");
}

public void mouseDragged(MouseEvent e)
{
	trans = false;
if (e.getX() <= 15*check[0].length && e.getY() <= 15*(check.length+1))// && e.getX() > 0 && e.getY() > 0)
{
	/*x = e.getX();
	y = e.getY();*/
	x = e.getX();
x-=8;
y = e.getY();
y-=28;
//System.out.println(e.getMouseModifiersText(e.getModifiers()).toLowerCase());
if (!(e.getMouseModifiersText(e.getModifiers()).toLowerCase().equals("meta+button3")))
{
	fill();
}
else
{
	delete();
}





}
}

public void mouseReleased(MouseEvent e)
{
//
}
public void line(double x1, double y1, double x2, double y2)
{
	Graphics2D g = (Graphics2D)(getContentPane().getGraphics());
	//g.setPaint(r());
	g.setPaint(Color.BLACK);

	if (erase)
	{
		//g.setPaint(getContentPane().getBackground());
		g.setPaint(Color.RED);
	}
	g.draw(new Line2D.Double(x1,y1,x2,y2));
}
public void line(double x1, double y1, double x2, double y2, Color color)
{
	Graphics2D g = (Graphics2D)(getContentPane().getGraphics());
	//g.setPaint(r());
	g.setPaint(color);

	if (erase)
	{
		//g.setPaint(getContentPane().getBackground());
		g.setPaint(Color.RED);
	}
	g.draw(new Line2D.Double(x1,y1,x2,y2));
}
public Color r()
{
	return new Color((float)(Math.random()),(float)(Math.random()),(float)(Math.random()));
}
public void line(int x1, int y1, int x2, int y2)
{
	line((double)x1,(double)y1,(double)x2,(double)y2);
}
public void mousePressed(MouseEvent e)
{
if (e.getX() <= 15*check[0].length && e.getY() <= 15*(check.length+1))
{
	/*x = e.getX();
	y = e.getY();*/
	x = e.getX();
x-=8;
y = e.getY();
y-=28;
//System.out.println(e.getMouseModifiersText(e.getModifiers()).toLowerCase());
if (!(e.getMouseModifiersText(e.getModifiers()).toLowerCase().equals("meta+button3")))
{
	fill();
}
else
{
	delete();
}





}
}
public void mouseClicked(MouseEvent e)
{
trans = false;
if (e.getX() <= 15*check[0].length && e.getY() <= 15*(check.length+1))// && e.getX() > 0 && e.getY() > 0)
{
	System.out.print("filled: ");
	/*x = e.getX();
	y = e.getY();*/
	x = e.getX();
x-=8;
y = e.getY();
y-=28;
//System.out.println(e.getMouseModifiersText(e.getModifiers()).toLowerCase());
if (!(e.getMouseModifiersText(e.getModifiers()).toLowerCase().equals("meta+button3")))
{
	fill();
}
else
{
	delete();
}





}
System.out.println(e.getX() + ", " + e.getY());

}
public void delete()
{
	/*System.out.println("Deleting...");
	double xt = (double)x/15;
	double yt = (double)y/15;
	double X = Math.floor(xt);
	double Y = Math.floor(yt);
	check[(int)X][(int)Y] = false;
	X*=15;
	Y*=15;*/
	erase = true;
	fill();

}
boolean trans = false;
public void fill()
{
	if (x < 0 || y < 0)
		return;
	double xt = (double)x/15;
	double yt = (double)y/15;
	double X = Math.floor(xt);
	double Y = Math.floor(yt);

	if (!erase && !trans)
	check[(int)Y][(int)X] = true;
	else if (!trans)
		check[(int)Y][(int)X] = false;


	/*for (double a = Y; a < Y + 15; a++)
	{
		line(X,a,X+15,a);
	}*/
	//grid[X][Y] =

	X*=15;
	Y*=15;


	/*for (double b = X; b < X + 15; b++)
	{
		for (double a = Y; a < Y + 15; a++)
		{

			line(b,a,b,a);
		}
	}*/
	Color randy = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(),0.05f);
	for (double a = Y+1; a < Y + 15; a++)
	{
		if (!trans)
		line(X+1,a,X+15-1,a);
		else
		{
			line(X+1,a,X+15-1,a,randy);
		}

	}
erase = false;

}
public void mouseMoved(MouseEvent e)
{
	trans = true;
	if (e.getX() <= 15*check[0].length && e.getY() <= 15*(check.length+1))// && e.getX() > 0 && e.getY() > 0)
{
	/*x = e.getX();
	y = e.getY();*/
	x = e.getX();
x-=8;
y = e.getY();
y-=28;
//System.out.println(e.getMouseModifiersText(e.getModifiers()).toLowerCase());
fill();





}
}
public void keyReleased(KeyEvent e)
{

}
public void keyPressed(KeyEvent e)
{
	trans = false;
	String u = e.getKeyText(e.getKeyCode()).toLowerCase();
	if (u.equals("delete"))
	{
clear();



	}
	if (u.equals("p"))
	{
		System.out.println("The Sprite was pasted.");
		drawGrid();
		for (int i = 0; i < check.length; i++)
		{
			for (int c = 0; c < check[0].length; c++)
			{
				if(check[i][c])
				{
				//	fill(i,c)
				y = i*15;
				x = c*15;
				fill();
				}

			}
		}



	}
if (u.equals("y"))
{
	System.out.println("\n\n\n");
	for( int r = 0; r < check.length; r++)
	{
		for (int c = 0; c < check.length; c++)
		{
			if (check[r][c])
				System.out.print("1");
			else
				System.out.print("0");
		}
		System.out.println();
	}

}
	if (u.equals("enter"))
	{
		String answer = " ";
		Scanner scan = new Scanner(System.in);
		while (!(answer.equals("yes") || answer.equals("no")))
		{
			System.out.println("Would you like to save now?");
			answer = scan.next();
			if (answer.equals("yes"))
				encrypt();






		}


	}

}
public void keyTyped(KeyEvent e)
{

}

public static void main(String[]args)
{
	SpriteCreator c = new SpriteCreator();
	c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	/*c.setSize(1280,760);
	c.setVisible(true);
	c.drawGrid();*/




}

}