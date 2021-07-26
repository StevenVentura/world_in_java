import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.awt.Color;
public class mmt
{
public static void main(String[]args)
{

//public Wall (double x,double y,double z,double width,double height, double j, double n)
ArrayList<Wall> walls = new ArrayList<Wall>();

for (int i = 0; i < 1000; i++)
{
//walls.add(new Wall( 1000*Math.random(), 1000*Math.random(), 1000*Math.random(), 24, 24, 90*Math.random(), 0));
//walls.get(walls.size()-1).color = new Color((float)Math.random(),(float)Math.random(),(float)Math.random(), 0.5f);
}
walls.add(new Wall(0,0,0,
			5, 5,
			90, 0));

try{
FileWriter f = new FileWriter("map.txt");
BufferedWriter w = new BufferedWriter(f);

for (Wall wall : walls)
{
w.write(wall.toFile());
w.newLine();
}
w.close();
f.close();

}catch(Exception e){};

}

}