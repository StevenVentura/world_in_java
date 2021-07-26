import java.util.ArrayList;
public class PTR
{
	public static void main(String[]args)
	{
		// DOES NOT WORK.
		/*Object s = "lol";
		System.out.println(s);
		System.out.println(s.substring(0,1));*/
		/*int[] x = new int[5];
		System.out.println(x[0]);*/




		/*int[] x = new int[5];
		System.out.println(x.length);*/
		ArrayList<int[]> nums = new ArrayList();

		{int[] temp = {1,2,3,4};
		nums.add(temp);}
		int[] temp = {5,6,7,8};
		nums.add(temp);
for (int x = 0; x < nums.size(); x++)
{


		for (int c = 0; c < nums.get(x).length; c++)
		{
			System.out.print("["+nums.get(x)[c]+"]");
		}

	}}
}