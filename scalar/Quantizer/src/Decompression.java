import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Decompression {
	
	ArrayList<Integer> decompressed_data = new ArrayList<Integer>();
	ArrayList<Integer> compressed_data = new ArrayList<Integer>();
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	
	public void decompress()
	{
		for(int i=0; i<compressed_data.size(); i++)
		{
			decompressed_data.add(map.get(compressed_data.get(i)));
		}
		
	}

}
