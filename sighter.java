import java.io.*;
import java.util.Scanner;
public class sighter
{
	public static void main(String[]args) throws IOException
	{
		String fileName = "abc.txt";
		File f = new File(fileName);
			if (f.exists())
				{
			FileReader fin = new FileReader(fileName);
			Scanner scanner = new Scanner(fin);

			while (scanner.hasNextLine())
					{
				String line = scanner.nextLine();
				System.out.println(line);
					}
			scanner.close();
			fin.close();
				}
	}
}
