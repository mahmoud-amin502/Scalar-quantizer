import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		 ArrayList<Integer> test = new ArrayList<Integer>();
		 test.add(1);
		 test.add(2);
		 test.add(28);
		 test.add(50);
		 test.add(69);
		 test.add(28);
		 test.add(31);
		 test.add(6);
		 test.add(32);
		 test.add(31);
		 test.add(52);
		 test.add(30);
		
		// test.add(11);
		// test.add(255);
		// test.add(238);
		// test.add(250);
		// test.add(69);
		// test.add(228);
		// test.add(231);
		// test.add(206);
		// test.add(232);
		// test.add(231);
		// test.add(252);
		// test.add(230);
		//
		 Compression comp = new Compression();
		 comp.basic_data = test;
		 comp.bits = 4;
		 comp.compress();
		 comp.Print_levels();
		 System.out.println(comp.compressed_data);
		 
		 Decompression decomp = new Decompression();
		 decomp.map = comp.map;
		 decomp.compressed_data = comp.compressed_data;
		 decomp.decompress();
		 
		 System.out.println(decomp.decompressed_data);

		// double d = 128d;
		// int two = 1;
		// while(true)
		// {
		// two*=2;
		// if(d-two<two)
		// break;
		//
		// }
		// System.out.println(two*2);

//		int bits = 2;
//		int numOFlevels = 0;
//		int div = 0;
//		while (true) {
//			if (bits == 1)
//				break;
//			else {
//				bits = bits / 2;
//				numOFlevels++;
//			}
//		}
//		System.out.println(numOFlevels);

	}

}
