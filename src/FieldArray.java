
public class FieldArray {

	int N; // Array horizontal length
	int M; // Array vertical length
	double x;	// x coordinate to center on
	double y;	// y coordinate to center on
	Complex dataPlex[][]; // Array of complex values
	
	// Field Array Constructor that builds coordinate array from x_0 to x_1 and from y_0 to y_1
	public FieldArray(double left, double right, double bottom, double top, double incr){
		if (left >= right) throw new RuntimeException("Left limit must be less than right limit");
		if (bottom >= top) throw new RuntimeException("Bottom limit must be less than top limit");
		if (incr > right - left || incr > top - bottom) throw new RuntimeException("Invalid increment size");
		
		this.N = (int)Math.floor((right - left)/incr) + 1;
		this.M = (int)Math.floor((top - bottom)/incr) + 1;
		this.dataPlex = new Complex[N][M];
		
		for (int i = 0; i < N; i++){
			for (int j = 0; j < M; j++){
				this.dataPlex[i][j] = new Complex((left + (double)i*incr), (top - (double)j*incr));
			}
		}
	}
	
	// Field Array Constructor that builds square coordinate array from x_0 to x_1 and from y_0 to y_1
	public FieldArray(double left, double right, double incr){
		FieldArray fieldArray = new FieldArray(left, right, left, right, incr);
		this.N = fieldArray.N;
		this.M = fieldArray.M;
		this.dataPlex = fieldArray.dataPlex;
		
	}
	
	// Create fieldArray centered at (x,y), zoomed in zoom times after it being size coordSize, scaled by scale with x-y ratio of xyRatio
	public FieldArray(double x, double y, int zoom, double coordSize, double scale, double xyRatio){
		this.x = x;
		this.y = y;
		// The actual size of the picture and hence window
		scale *= 1000;
		
		// The ratio by which the next frame will be zoomed into
		double phi = 1.61803398875;
		double zm = Math.pow(phi, zoom);
		
		// The range, left, right, lower, and upper limits in the actual coordinate plane
		double range = coordSize/zm;
		double left = -range + x;
		double right = range + x;
		double bottom = -range*xyRatio + y;
		double top = range*xyRatio + y;
		double incr = 0;
		
		if ((right-left) < top-bottom){
			incr = (top-bottom)/(0.75*scale);
		}
		else{
			incr = (right-left)/scale;
		}
		
		FieldArray fieldArray = new FieldArray(left, right, bottom, top, incr);
		
		this.N = fieldArray.N;
		this.M = fieldArray.M;
		this.dataPlex = fieldArray.dataPlex;
		
		
	}
	
	// Print FieldArray for testing
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("\n"+"[");
		for(int j = 0; j<M; j++){
			for(int i = 0; i<N; i++){
			s.append(dataPlex[i][j] + "\t");
			}
			if(j == M-1){
				s.append("]"+"\n");
			}
			else{
				s.append("\n");
			}
		}
		s.append("\n");
		s.append("\n");
		return s.toString();
	}
	
	public static void main(String[] args){
		FieldArray fieldArray = new FieldArray(-15, 15, -10, 10, 1);
		
		System.out.println(fieldArray);
	}
}
