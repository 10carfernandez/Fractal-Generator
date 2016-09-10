
public class ColorSchemeCreator {

	int colorArray[]; //The array containing the values for the color
	
	public ColorSchemeCreator(){
		int L = 256;
		int rgb = 0;
	    double frequency = 0.026;
	    this.colorArray = new int[L];
	    
	    for (int i = 0; i < L; i++)
	    {
	       double red   = Math.sin(frequency*i + 0) * 127 + 128;
	       double green = Math.sin(frequency*i + 2) * 127 + 128;
	       double blue  = Math.sin(frequency*i + 4) * 127 + 128;
	       
	       int rr = (int)red;
	       int gg = (int)green;
	       int bb = (int)blue;
	       
	       rgb = rr << 16 | gg << 8 | bb << 0;
	       
	       this.colorArray[i] = rgb;
	       
	    }
	}	
}
