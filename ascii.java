import java.io.*;
public class ascii
{


	public static void main(String[]args)
	{
		try{

		String fileName = "symbolz.docx";
		FileWriter fstream = new FileWriter(fileName);
    	BufferedWriter write = new BufferedWriter(fstream);
		for (int i = 0; i < 1000; i++)
		{
			char c = (char)i;
			write.write(i + " : " + c);

		}
		write.close();
		fstream.close();
		
		}catch(Exception e){};
	}
}