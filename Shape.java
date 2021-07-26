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
public class Shape
{
	public Color color;
	public double x;
	public double y;
	public double z;
	public double height;
	public double width;
	public double size;
	public double j, n;
	public int numVertices;
	public ArrayList<Vertex> vertices;
	public String identifier;
	public int[] startingPoints;
	public int[] endingPoints;
	public double[] att;
	public String f;
	//public ArrayList<Face> faces;
	public int[] polySequence;
	public ArrayList<Coordinate> cs;
	public ArrayList<Face> faces;
	public Color getColor()
	{
		return color;
	}
	public Color getColor(int aguehjugahughuehgakjsgh)
	{
		return null;
	}
	public Vertex gp(int face, double[] last, double[] attempt, Line2D.Double yPath)
	{
		return null;
	}
	public boolean isClimbable(int face)
	{
		return false;
	}
	public void defineVertices()
	{

	}
	public void height(boolean b)
	{

	}
	public void width(boolean b)
	{

	}
	public void rotateX(boolean b)
	{

	}
	public void rotateZ(boolean b)
	{

	}
	/*public Shape(double x,double y,double z,double width,double height, double j, double n){
	}*/
	public double getSize()
	{
		return size;
	}
	/*public double getX()
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
	}*/
	public double getX(){return x;
	}public double getY(){return y;
	}public double getZ(){return z;
	}public double getHeight(){return height;
	}public double getWidth(){return width;
	}public double getJ(){return j;
	}public double getN(){return n;
	}
	public void updateAtt()
	{

	}
	public double getTop(double x, double z)
	{
		return 0;
	}
	public void t(int run)
	{double random = Math.random()*6;
		if (Math.random() > 0.1)
		{
			x+=random;
			y+=random;
			z+=random;
		}
		/*else{
		x-=random;
		y-=random;
		z-=random;
		}*/

	}
	public void changeVertex(int which, double value)
	{

	}
	public ArrayList<Coordinate> getCs()
	{
		return cs;
	}
	public ArrayList<Face> getFaces()
	{
		return faces;
	}
	public void Align(int which, Coordinate c)
	{
		cs.set(which, c);
	}
	public double[] getAtt()
	{
		return att;
	}
	public void Move(double xc, double yc, double zc)
	{

	}
	public void Rotate(double orientation)
	{

	}
	public int getMaxFaces()
	{
		return 0;
	}
	public int[] getPolySequence()
	{
		return polySequence;
	}
	public int getNumVertices()
	{
		return numVertices;
	}
	public ArrayList<Vertex> getVertices()
	{
		return vertices;
	}
	/*public void defineFaces()
	{

	}*/

	public String getIdentifier()
	{
		return identifier;
	}
	public int[] getStartingPoints()
	{
		return startingPoints;
	}
	public int[] getEndingPoints()
	{
		return endingPoints;
	}
	public String toFile()
	{
		return f;
	}
	// do i need to make a set of instructions for which spots to connect to which?















}