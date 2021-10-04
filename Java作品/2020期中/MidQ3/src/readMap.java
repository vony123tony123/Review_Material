import java.awt.Point;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class readMap {
	private int maxrow=0;

	public int getMaxrow() {
		return maxrow;
	}

	public readMap(ArrayList<Point> p) {
		Scanner scanner;
		try {
			scanner = new Scanner(Paths.get("map.txt"));
			int row=1;
			while(scanner.hasNext())
			{
				String str=scanner.nextLine();
				String[] locate=str.split(" ");
				for(String s:locate)
				{
					p.add(new Point(Integer.valueOf(s),row));
				}
				row++;
			}
			maxrow=row-1;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
