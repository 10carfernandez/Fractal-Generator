
public class Complex {
	
	 double x;	// Real component
	 double y;	// Imaginary component
	
	 // Complex Constructors
		public Complex(){
			this.x = 0;
			this.y = 0;		
		}
		
	public Complex(double x, double y){
		this.x = x;
		this.y = y;		
	}
	
	// Basic mathematical operations for complex numbers
	public Complex plus(Complex that){		
		double x = this.x + that.x;
		double y = this.y + that.y;
		Complex z = new Complex(x, y);
		return z;
	}
	
	public Complex plus(double that){		
		double x = this.x + that;
		Complex z = new Complex(x, this.y);
		return z;
	}		
	
	public Complex minus(Complex that){
		double x = this.x - that.x;
		double y = this.y - that.y;
		Complex z = new Complex(x, y);
		return z;
	}

	public Complex minus(double that){		
		double x = this.x - that;
		Complex z = new Complex(x, this.y);
		return z;
	}	
	
	public Complex times(Complex that){
		double real = this.x * that.x - (this.y * that.y);
		double imag = this.y * that.x + this.x * that.y;
		Complex z = new Complex(real, imag);
		return z;
	}
	
	public Complex times(double that){
		double x = this.x * that;
		double y = this.y * that;
		Complex z = new Complex(x, y);
		return z;
	}
	
	public Complex squared(){
		double real = this.x * this.x - (this.y * this.y);
		double imag = 2 * this.x * this.y;		
		Complex z = new Complex(real, imag);
		return z;
	}
	
	// Use recursion so that he calculation is done more quickly
	public Complex ToThePowerOf(int n){
		if (n == 0){
			Complex z = new Complex(1.0,0.0);
			return z.times(this);
		}
		if (n == 1){
			return this;
		}
		if (n == 2){
			return this.squared();
		}
		if (n%2 == 0){
			return this.ToThePowerOf(n/2).ToThePowerOf(2);
		}		
		else{
			return this.times(this.ToThePowerOf((n-1)/2).ToThePowerOf(2));
		}		
	}
	
	public double magnitude(){		
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	// Return number of iterations for z_n = z_(n-1)^n + c
	public int MultibrotIterations(Complex this, int maxIterations, Complex c, int n){
		if(maxIterations < 1) throw new RuntimeException("Max number of iterations must be positive and non-null.");		
		Complex z0 = (this.ToThePowerOf(n)).plus(c);
		double mag = z0.magnitude();
		double maxMag = 2;
		int iterations = 0;
		
		if (mag > 2) return 1;
		
			while (iterations <= maxIterations && mag < maxMag){
				z0 = z0.ToThePowerOf(n).plus(c);
				mag = z0.magnitude();
				iterations++;	
		}
			return iterations;	
	}
	
	// Return number of iterations for z_n = z_(n-1)*^2 + c where z_n* = |x_n| + |y_n|i
	public int BurningShipIterations(Complex this, int maxIterations, Complex c, int n){
		if(maxIterations < 1) throw new RuntimeException("Max number of iterations must be positive and non-null.");
		this.x = Math.abs(this.x);
		this.y = -Math.abs(this.y);
		Complex z0 = (this.ToThePowerOf(n)).plus(c);
		double mag = z0.magnitude();
		double maxMag = 2;
		int iterations = 0;
		while (iterations <= maxIterations && mag < maxMag){
			z0.x = Math.abs(z0.x);
			z0.y = -Math.abs(z0.y);
			z0 = z0.ToThePowerOf(n).plus(c);
			mag = z0.magnitude();
			iterations++;
		}
		return iterations;		
	}
	
	// Used for printing complex values
	public String toString(){
		StringBuilder s = new StringBuilder();
		if (y > 0){
			s.append(x + " + " + y + "i");
		}
		else if (y < 0){
			s.append(x + " " + y + "i");
		}
		else{
			s.append(x);
		}
		return s.toString();
	}
	
	//-----------------------------COMPLEX DRIVER-----------------------------//
	public static void main(String[] args){		
		Complex z = new Complex(-0.4f, 0.6f);
		System.out.println(z);
		
		// Test the speed of ToThePowerOf method
		double a = System.nanoTime();
		System.out.println( z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z)))))))))) );
		double b = System.nanoTime();		
		
		double c = System.nanoTime();
		System.out.println(z.ToThePowerOf(11));
		double d = System.nanoTime();
		
		
		System.out.print((b-a)/1000000 + " milliseconds ");
		System.out.println("for z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z.times(z))))))))))");
		System.out.print((d-c)/1000000 + " milliseconds ");
		System.out.println("for z.ToThePowerOf(11)");		
	}
	
}
