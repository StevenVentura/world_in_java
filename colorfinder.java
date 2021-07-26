
import java.io.*;

import java.awt.MouseInfo;
import java.awt.Robot;

public class colorfinder
{
	Robot r;
	public colorfinder() throws Exception
	{
		r = new Robot();
	}
	public void wayt(long time) throws Exception
	{
		Thread.sleep(time);
	}
	public void g() throws Exception
	{
		while(true){
		
  System.out.println(r.getPixelColor(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y));
  wayt(500);
}
}
//	public 
	public static void main(String[]args) throws Exception
	{
		colorfinder c = new colorfinder();
		c.wayt(5000);
		c.g();
	}
}

