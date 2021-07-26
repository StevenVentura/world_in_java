public class User
{

	private double[] Last;
	private double[] My;
	private double[] Attempt;
	private double speed;
	private double HorizontalSensitivity;
	private double VerticalSensitivity;
	private boolean crouched = false;
	private boolean moving = false;
	public User(double myX, double myY, double myZ, double myHorizontalOrientation, double myVerticalOrientation)
	{
		speed = 1;
		My = new double[6];
		My[0] = 555; // placeholder
		My[1] = myX;
		My[2] = myY;
		My[3] = myZ;

		My[4] = myHorizontalOrientation;
		My[5] = myVerticalOrientation;

Attempt = new double[My.length];
		Last = new double[My.length];

		Attempt = My; // no movement



		HorizontalSensitivity = 5;
		VerticalSensitivity = 5;


	}
	public void crouch()
	{
		//crouched = !crouched;
	}






	public boolean isMoving()
	{
		return moving;
	}















	public double getFallSpeed()
	{

		return 1;
	}
	public double getJumpSpeed()
	{
		return 2;
	}
	public double get(int index)
	{
		return My[index];
	}
	public double getHorizontalSensitivity()
	{
		return HorizontalSensitivity;
	}
	public double getVerticalSensitivity()
	{
		return VerticalSensitivity;
	}
	//private double am = 0;
	private boolean inc;
	private int h;
	private double[] MCopy;

	public void beginMoving()
	{




		//MCopy = My;


		Attempt = My; // to be mathed
		for (int i = 0; i < My.length; i++)
		{
			Last[i] = My[i];
		}
		//Last = My;
		/*for (int i = 0; i < Attempt.length; i++)
		{
			System.out.print("["+Attempt[i]+"]");
		}*/
	}
	public void doneMoving()
	{
		//System.out.println("Last:");
		/*for (int i = 0; i < Attempt.length; i++)
		{
			System.out.print("["+Last[i]+"]");
		}
		System.out.println("After:");
		for (int i = 0; i < Attempt.length; i++)
		{
			System.out.print("["+Attempt[i]+"]");

		System.out.println();*/
	//	Attempt = My;
		/*Last = My;
		Attempt = My;*/
		/*last = My;
		Attempt = My;*/
	}
	public void move(int how, boolean increment, double amount)
	{
		if (increment == false)
				amount = -amount;

		if (crouched)
			amount*=0.2;

		if (how == 4 || how == 5)
		{

			My[how]+=amount;
			for (int i = 4; i <= 5; i++)
			{
			if (My[i] > 180)
			My[i]-=360;
			if (My[i] < -180)
			My[i]+=360;
			}
		}


		/*if (how == 5) //code segment used to nullify any requests to change GvLook
			return;*/
if (how < 4){
//System.out.println(Attempt[how]);
		Attempt[how] += amount;}//System.out.println(Attempt[how]);System.out.println("this code is being read");}





/*if (how == 5)
{
	if (My[5] > 90)
		My[5] = 90;
	if (My[5] < -90)
		My[5] = -90;
}*/



	}
	/*	public void move(int how, boolean increment, int amo)
	{
	double amount = (double)amo;

		if (crouched)
			amount*=0.2;
		if (!(increment))
			amount = -amount;
		My[how]+=amount;
		if (how == 4 || how == 5)
		{
			for (int qoe = 4; qoe <= 5; qoe++)
			{
			if (My[qoe] > 180)
			My[qoe]-=360;
			if (My[qoe] < -180)
			My[qoe]+=360;
			}
		}
		if (am == 0)
			moving = false;





	}*/
	/*public void attempt(int how, boolean increment, double amount)
	{
		if (crouched)
			amount*=0.2;
		if (!(increment))
			amount = -amount;
		My[how]+=amount;
		if (how == 4 || how == 5)
		{
			for (int qoe = 4; qoe <= 5; qoe++)
			{
			if (My[qoe] > 180)
			My[qoe]-=360;
			if (My[qoe] < -180)
			My[qoe]+=360;
			}
		}





	}*/
	public void set(int which, double number)
	{
		if (which == 5)
			return;
		My[which] = number;
		//Last = My; // the set method is not accessable by the player himself, unless maybe he is teleporting or something. therefore, i don't want collision detection on this method.
		for (int i = 0; i < My.length; i++)
		{
			Last[i] = My[i];
		}
		for (int i = 0; i < My.length; i++)
		{
			Attempt[i] = My[i];
		}
	}
	public double[] M()
	{
		return My;
	}
	public double[] getLast()
	{
		return Last;
	}
	public double[] getAttempt()
	{
		return Attempt;
	}
	public double getSpeed()
	{
		return speed;
	}
	public String toString()
	{
		String out = "";
		out+= "X: "+(int)My[1]+" Y: " + (int)My[2] + " Z: " + (int)My[3] + " wg: " + (int)My[4] + " vg: " + (int)My[5];
		return out;
	}




}