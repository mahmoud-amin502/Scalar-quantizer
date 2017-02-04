package scalar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class compress {
	ArrayList<level> levels = new ArrayList<level>(); // array list of array list
	ArrayList<Integer> basic_data = new ArrayList<Integer>();
	ArrayList<Integer> compressed_data = new ArrayList<Integer>();
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//	int[][] pixels  = readImage(filePath); 
	int bits = 0;
//	String filePath = ""; 

	public void compress() {
		
		int max = 0;
		int upper_range = 0;

		level level1 = new level();
		Node node = new Node();

		node.data = basic_data;
		node.average = get_average(basic_data);
		
		
		level1.level.add(node);
		levels.add(level1);  // add level to an array list of levels .
		
		for (int i = 0; i < bits; i++) {
                
			ArrayList<Node> associatedresult = new ArrayList<Node>();
			level level2 = new level();
			
			
			for (int j = 0; j < levels.get(i).level.size(); j++) {
				associatedresult = associate(levels.get(i).level.get(j));
				level2.level.add(associatedresult.get(0));
				level2.level.add(associatedresult.get(1));
			}
			levels.add(level2);
		}
		
		

		max = get_max();
		
		System.out.println("MAX = "+max);
		
		
		upper_range = get_upper_range(max);
		
		System.out.println("upper_range = "+upper_range);
		
		
		//set the ranges
		Double[] ranges = new Double[levels.get(levels.size()-1).level.size()+2];
		ranges = get_ranges(upper_range,ranges.length);
		for(int i=0; i<ranges.length; i++)
			System.out.println(ranges[i]);
		
		
		//System.out.println(basic_data.size());
		System.out.println(bits);
		for(int i=0; i<basic_data.size(); i++)
		{
			for(int j=0; j<Math.pow(2,bits); j++)
			{
				if(basic_data.get(i)>=ranges[j] && basic_data.get(i)<=ranges[j+1])
				{
					compressed_data.add(j);
					break;
				}
			}
		}
		
		for(int i=0;i<Math.pow(2,bits);i++)
		{
			map.put(i,(int)(ranges[i]+ranges[i+1])/2);
		}
		System.out.println("map = "+map);
			
		
	}
	
	
	

	public int get_numOFlevels(int bit) {
		int numOFlevels = 0;
		while (true) {
			if (bit == 1)
				break;
			else {
				bit = bit / 2;
				numOFlevels++;
			}
		}
		//System.out.println("kkhkkhgh"+numOFlevels);
		return numOFlevels;
	}

	public Double[] get_ranges(int upper_range,int length) {
		Double[] ranges = new Double[length-1]; 
		ranges[0] = 0.0;
		for(int i=0;i<levels.get(levels.size()-1).level.size()-1;i++)
		{
			ranges[i+1] = (double)((int)levels.get(levels.size()-1).level.get(i).average+(int)levels.get(levels.size()-1).level.get(i+1).average)/2;
		}
		ranges[levels.get(levels.size()-1).level.size()] = (double)upper_range;
		
		return ranges;
	}
	
	
	
	
	
	

	public int get_upper_range(int MAX) {
		int two = 1;
		while(true)
		{
			two*=2;
			if(MAX-two<two)
				break;
				
		}
		return (two*2)-1;
	}
	
	
	
	

	public int get_max() {
		int MAX = 0; 
		for(int i =0; i<basic_data.size(); i++)
		{
			if(basic_data.get(i)>MAX)
				MAX = basic_data.get(i);
		}
		return MAX;
	}
	
	
	
	

	public ArrayList<Node> associate(Node node) {

		ArrayList<Integer> less = new ArrayList<Integer>();
		ArrayList<Integer> great = new ArrayList<Integer>();
		ArrayList<Node> x = new ArrayList<Node>();
		Node n_temp1 = new Node();
		Node n_temp2 = new Node();

		int[] s_f = spliting(node.average);
//		System.out.println("sell = "+s_f[0]+" floor = "+s_f[1]);
		
		for (int i = 0; i < node.data.size(); i++) {
			if (node.data.get(i) <= s_f[0] || node.data.get(i) == node.average)
			{
				less.add(node.data.get(i));
			}
			else if (node.data.get(i) >= s_f[1])
			{
				great.add(node.data.get(i));
			}
			else
				System.out.println("There Something Wrong!!");

		}
		n_temp1.data = less;
		n_temp1.average = get_average(less);
//		System.out.println("less "+n_temp.data);
		x.add(n_temp1);
		

		n_temp2.data = great;
		n_temp2.average = get_average(great);
//		System.out.println("great "+n_temp.data);
		x.add(n_temp2);
		
//		System.out.println("less "+x.get(0).data);
//		System.out.println("great "+x.get(1).data);
		return x;
	}
	
	
	
	
	
	

	public int[] spliting(Double AVG) {
		int[] x = { 0, 0 };
		int sell = 0;
		int floor = 0;

		if (AVG % 1 == 0) {
			sell = AVG.intValue() + 1;
			floor = AVG.intValue() - 1;
		} else {
			sell = AVG.intValue() + 1;
			floor = AVG.intValue();
		}
		x[0] = floor;
		x[1] = sell;
		return x;
	}

	public Double get_average(ArrayList<Integer> temp) {
		Double AVG = new Double(0.0);
		double sum = 0.0d;
		for (int i = 0; i < temp.size(); i++) {
			sum += temp.get(i);
		}
		AVG = sum / (double) temp.size();
		return AVG;
	}
	
	public void Print_levels(){
		for(int i=0;i<levels.size();i++)
		{
//			System.out.println(levels.get(i).level.size());
			for(int j=0;j<levels.get(i).level.size();j++)
			{
				System.out.println(levels.get(i).level.get(j).data);
				System.out.println(levels.get(i).level.get(j).average);
				System.out.println("******************");
			}
		}
	}
	
	public void readImage(String filePath)
    {
	    int width=0;
		int height=0;
        File file=new File(filePath);
        BufferedImage image=null;
        try
        {
            image=ImageIO.read(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

          width=image.getWidth();
          height=image.getHeight();
        int[][] pixels=new int[height][width];

        for(int x=0;x<width;x++)
        {
            for(int y=0;y<height;y++)
            {
                int rgb=image.getRGB(x, y);
                int alpha=(rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb >> 0) & 0xff;

                pixels[y][x]=r;
            }
        }
        
        for(int i=0; i<width; i++)
            for(int j=0; j<height; j++)
            {
            	basic_data.add(pixels[j][i]);
            }
//        return pixels;
    }

}
