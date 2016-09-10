
public class ComplexMath {
	
	public static int[] Polynomial(int n){
		int[] polynomial = new int[n];
		for (int i = 0; i < n; i++){
			polynomial[i] = 1;
		}
		return polynomial;
	}
	
	public static int Factorial(int n){
		if (n < 0) throw new RuntimeException("Integer must be positive");
		if (n == 0 || n == 1) return 1;
		return n*Factorial(n-1);
	}
	
	public static int[] Derivative(int[] polynomial){
		int derivative[] = new int[polynomial.length];
		for(int i = 0; i < polynomial.length-1; i++){
			derivative[i] = (i+1)*polynomial[i+1];
		}
		return derivative;
	}
	
	public static int[] Derivative(int[] polynomial, int n){
		if(n > polynomial.length + 2) return new int[polynomial.length];
		int derivative[] = polynomial;
		for (int i = 0; i < n; i++){
			derivative = Derivative(derivative);
		}
		return derivative;

	}

}
