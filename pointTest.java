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


public class pointTest
{

public static double atan(double t)
	{
		return Math.atan(t)/(2*Math.PI)*360;
	}
		public static double tan(double t)
	{
		return Math.tan(t*Math.PI/180);
	}


	public static void main(String[]args) throws Exception
	{
		User user = new User(0,0,0, 90 + 0.000001, 0);
		double[] m = user.M();
		double screenWidth = 800;
		double screenHeight = 600;

		//User user = new User(0,0,0, 90 => 0, 0);
		ArrayList<Vertex> vs = new ArrayList<Vertex>();
		vs.add(new Vertex(10,10,10));
		/*System.out.printf("alignA(V) = " + "%.3f" , (float)World.AlignA(v,m,screenWidth,screenHeight) +
			"  alignB = " + "%.3f" + World.AlignB(v,m,screenWidth,screenHeight));*/
for (Vertex v : vs)
{

		System.out.printf("A = " + "%.3f" , (float)World.AlignA(v,m,screenWidth,screenHeight));
		System.out.printf(" B = " + "%.3f" , (float)World.AlignB(v,m,screenWidth,screenHeight));
		System.out.printf(" v = " + "%.3f" , (float)World.gV(v,m));
		System.out.printf(" w = " + "%.3f" , (float)World.getW1(v, m));
		System.out.println();
}


	}
}