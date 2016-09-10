import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageCreator {
	
	private static ColorSchemeCreator colorSchemeCreator = new ColorSchemeCreator();
	private static int[] colorScheme = colorSchemeCreator.colorArray;
	public static double xyRatio = 0.5625;	// My screen ratio
	//public static double xyRatio = 1;
	
	public static BufferedImage SetFractalImage(double x, double y, int zoom, int iterations, Complex c, int n, 
			double coordSize, double scale, int fractalType, int granularity) throws IOException{

		FieldArray fieldArray = new FieldArray(x, y, zoom, coordSize, scale, xyRatio);		
		IteratedFieldArray iteratedFieldArray = new IteratedFieldArray(fieldArray, iterations, c, n, fractalType);
		int arra[][] = iteratedFieldArray.iteratedArray;
		int xLength = iteratedFieldArray.N;
		int yLength = iteratedFieldArray.M;
		int lim = colorScheme.length - 1;
		BufferedImage bI = new BufferedImage(xLength, yLength, 3);
		
		for (int xx = 0; xx < xLength; xx++) {
			for (int yy = 0; yy < yLength; yy++) {
				int rgb = colorScheme[(granularity*arra[xx][yy])%lim];
				bI.setRGB(xx, yy, -rgb);
			}
		}
		return bI;
	}
}
