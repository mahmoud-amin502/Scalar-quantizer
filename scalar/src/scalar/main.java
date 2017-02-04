package scalar;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class main {

	public static void main(String args[]) {

		Scanner w1 = new Scanner(System.in);
		System.out.println("Enter Number Of Bits : ");
		int br = w1.nextInt();

		int width = 0;
		int height = 0;
		File file = new File("lena.jpg");
		BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		width = image.getWidth();
		height = image.getHeight();
		int[][] pixels = new int[height][width];
		ArrayList<Integer> pixi = new ArrayList<Integer>();

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int rgb = image.getRGB(x, y);
				int alpha = (rgb >> 24) & 0xff;
				int r = (rgb >> 16) & 0xff;
				int g = (rgb >> 8) & 0xff;
				int b = (rgb >> 0) & 0xff;

				pixels[y][x] = r;
				pixi.add(r);
			}
		}
		
		//System.out.println(pixi);
		
		
		int[][] pixi_2 = new int[height][width];
		int count = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixi_2[y][x] = pixi.get(count);
				count++;
			}
		}
		
		
	    int baris = 3;
	    int kolom = 3;
	    int lebarZona = 2;
	    int tinggiZona = 2;
	    int counterTinggi = 0;
	    ArrayList<int[][]> daftarZona = new ArrayList<int[][]>();
	    counterTinggi = 0;
	    int counterBaris = 0;
	    int counterKolom = 0;

	    for (int i = 0; i < tinggiZona; i++) {
	        for (int j = 0; j < lebarZona; j++) {
	            int copyArray[][] = new int[baris][kolom];

	            for (int k = 0; k < baris; k++) {
	                System.arraycopy(dataArray[counterBaris], counterKolom, copyArray[k], 0, kolom);
	                counterBaris++;
	            }

	            daftarZona.add(copyArray);
	        }
	        counterBaris = 0;
	        counterKolom += kolom;
	    }

	    for (int i = 0; i < daftarZona.size(); i++) {
	        System.out.println(Arrays.deepToString(daftarZona.get(i)));
	    }
		
		
		
		
	

//		compress s1 = new compress();
//		s1.basic_data = pixi;
//		s1.bits = br;
//		s1.compress();
		// s1.Print_levels();
		// System.out.println(s1.compressed_data);

//		decompress s2 = new decompress();
//		s2.map = s1.map;
//		s2.compressed_data = s1.compressed_data;
//		s2.decompress();
		// System.out.println(s2.decompressed_data);

		
		
		
		
//		int[][] pixi_2 = new int[height][width];
//		int count = 0;
//		for (int x = 0; x < width; x++) {
//			for (int y = 0; y < height; y++) {
//				pixi_2[y][x] = s2.decompressed_data.get(count);
//				count++;
//			}
//		}
//		writeImage(pixi_2, "decom_2.jpg", width, height);
//
	}

	public static void writeImage(int[][] pixels, String outputFilePath, int width, int height) {
		File fileout = new File(outputFilePath);
		BufferedImage image2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image2.setRGB(x, y, (pixels[y][x] << 16) | (pixels[y][x] << 8) | (pixels[y][x]));
			}
		}
		try {
			ImageIO.write(image2, "jpg", fileout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
