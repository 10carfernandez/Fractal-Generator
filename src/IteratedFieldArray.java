
public class IteratedFieldArray {

	int N;
	int M;
	Complex dataPlex[][]; // Array of complex values
	int iteratedArray[][]; // Array of integer values
	
	// This is the "master" fractal constructor
	public IteratedFieldArray(FieldArray fieldArray, int maxIterations, Complex c, int n, int k){
		this.N = fieldArray.N;
		this.M = fieldArray.M;
		this.iteratedArray = new int[N][M];
		this.dataPlex = fieldArray.dataPlex;
		
		// Due to the size of the array, the Multibrot and BurningShip functions are not set as one
		// because it would require checking an additional condition for all entries. In the future,
		// if more functions are anticipated, a new function class will be made in order to instantiate 
		// a function object once and reduce and organize the number of case statements used here, so
		// one switch with two case statements for Mandelbrot vs Julia and another switch for the
		// function being used. 
		switch (k){
		case 1:
			for(int i = 0; i < N; i++){
				for (int j = 0; j < M; j++){
					this.iteratedArray[i][j] = c.MultibrotIterations(maxIterations, this.dataPlex[i][j], n);
				}
			}
			break;
		case 2:
			for(int i = 0; i < N; i++){
				for (int j = 0; j < M; j++){
					this.iteratedArray[i][j] = this.dataPlex[i][j].MultibrotIterations(maxIterations, c, n);
				}
			}
			break;
		case 3:
			for(int i = 0; i < N; i++){
				for (int j = 0; j < M; j++){
					this.iteratedArray[i][j] = c.BurningShipIterations(maxIterations, fieldArray.dataPlex[i][j], n);
				}
			}
			break;
		case 4:
			for(int i = 0; i < N; i++){
				for (int j = 0; j < M; j++){
					this.iteratedArray[i][j] = fieldArray.dataPlex[i][j].BurningShipIterations(maxIterations, c, n);
				}
			}
			break;
		}
	}
	
	// Create 2D array for Mandelbrot set 
	public IteratedFieldArray MandelbrotSet(FieldArray fieldArray, int maxIterations, Complex c, int n){
		return new IteratedFieldArray(fieldArray, maxIterations, c, n, 1);
	}
	
	// Create 2D array for Julia set 
	public IteratedFieldArray JuliaSet(FieldArray fieldArray, int maxIterations, Complex c, int n){
		return new IteratedFieldArray(fieldArray, maxIterations, c, n, 2);
	}
	
	// Create 2D array for Burning Ship Mandelbrot set
	public IteratedFieldArray BurningShipSet(FieldArray fieldArray, int maxIterations, Complex c, int n){
		return new IteratedFieldArray(fieldArray, maxIterations, c, n, 3);
	}
	
	// Create 2D array for Burning Ship Julia set
	public IteratedFieldArray BurningShipJuliaSet(FieldArray fieldArray, int maxIterations, Complex c, int n){
		return new IteratedFieldArray(fieldArray, maxIterations, c, n, 4);
	}

}
