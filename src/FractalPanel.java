import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Graphics;
import javax.swing.*;

public class FractalPanel extends JFrame implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label;
	JFrame frame;
	public static int zoom = 0;
	public static double zm = 1;
	static int hSize = 1000;
	static int vSize = (int)(hSize*ImageCreator.xyRatio);
	double xHalfway = hSize/2.0;
	double yHalfway = vSize/2.0;
	double currentX = 0;
	double currentY = 0;
/*	double currentX = -0.2;
	double currentY = 0.8;*/
	double size = 2;	
	private static double xPressed = 0;
	private static double yPressed = 0;
	private static double xReleased = 0;
	private static double yReleased = 0;
	
	//Complex c = new Complex(-0.4, 0.6);
	Complex c = new Complex();
	
    public static void main(String[] args) throws IOException
    { 
    	System.out.print("\n\n\n\n\n");
    	new FractalPanel();
    }
	
    public FractalPanel() throws IOException {
        this.frame = new JFrame();
        this.label = new JLabel();
    	frame.addMouseListener(this);
    	CenterAt(this.currentX, this.currentY, this.size);
    }

    public void mouseClicked(MouseEvent me){
    	
    	int zoomBy = 0;
    	int zoomDirection = 0;
    	double chi = 0.61803398875;
    	double [] coords = ReparametrizePoints(me.getX(), me.getY());
    	
		double xChange = (xReleased - xPressed)/2;
		double yChange = (yReleased - yPressed)/2;
    	
    	if (xChange != 0 || yChange != 0){
    		if (zoom > 0) chi += 1;
    		zm = Math.pow(chi, zoom);
    		coords[0] = xChange;
    		coords[1] = yChange;
    		zoomDirection = -1;
    		System.out.println("xChange = " + xChange);
    		System.out.println("yChange = " + yChange);
    		System.out.println("");
    	}
    	else{
        	if (me.getButton() == 1){
        		zoomBy = 1;
        		zoomDirection = 1;
        		chi += 1;
        	}
        	else if (me.getButton() == 3){
        		zoomBy = -1;
        		zoomDirection = -1;
        		chi *= zoom;
        	}
        	else if (me.getButton() == 2){
        		ResetParameters();
        	}
    	}
    	
        zoom += zoomBy;
        zm = Math.pow(chi, zoom);
        this.currentX += zoomDirection*(1/ImageCreator.xyRatio)*(coords[0]/zm);
        this.currentY += zoomDirection*(coords[1]/zm);
        
    	System.out.println("x = " + this.currentX);
    	System.out.println("y = " + this.currentY);
    	System.out.println("");
        
    	try {
			CenterAt(currentX, currentY, this.size);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("IOException occured");
		}
    }
    
    public void CenterAt(double x, double y, double size) throws IOException{
    	int fractalType = 1;	// 1 for mulibrot set and 3 for burning ship set
    	int power = 2;
    	int dim = 20;
    	int maxIterations = 250;
    	int granularity = 1; //Between 1 and max number of iterations
    	// Buffer image for Mandelbrot set and for Julia set corresponding to center
    	Complex d = new Complex(this.currentX, this.currentY);
        BufferedImage mandelbrotImage = ImageCreator.SetFractalImage(this.currentX, this.currentY, zoom, maxIterations, c, power, size, 1, fractalType, granularity);
        BufferedImage juliaImage = ImageCreator.SetFractalImage(0, 0, 0, maxIterations, d, power, size, .25, fractalType+1, granularity);
        BufferedImage centerImage = SetCenter(dim);
        int mWidth = mandelbrotImage.getWidth();
        int mHeight = mandelbrotImage.getHeight();
        
        // Set canvas to be the size of the Mandelbrot image
        BufferedImage canvas = new BufferedImage(mWidth, mHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics finalIcon = canvas.getGraphics();
        finalIcon.drawImage(mandelbrotImage, 0, 0, mWidth, mHeight, null, null);
        finalIcon.drawImage(juliaImage, 0, 0, juliaImage.getWidth(), juliaImage.getHeight(), null, null);
        finalIcon.drawImage(centerImage, mWidth/2 - dim/2, mHeight/2 - dim/2, centerImage.getWidth(), centerImage.getHeight(), null, null);
        finalIcon.dispose();
        
        label.setIcon(new ImageIcon(canvas));
        frame.add(label);

        frame.setVisible(true);
        frame.setSize(hSize + 18, vSize + 47);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
    }
    
    public BufferedImage SetCenter(int dim){
    	BufferedImage centerImage = new BufferedImage(dim, dim, 3);
    	for (int i = 0; i < dim; i++){
    		centerImage.setRGB(dim/2, i, -10000000);
    	}
    	
    	for (int i = 0; i < dim; i++){
    		centerImage.setRGB(i, dim/2, -10000000);
    	}
    	return centerImage;
    }
    
    public double[] ReparametrizePoints(double x, double y){
    	double xHalfway = this.xHalfway;
    	double yHalfway = this.yHalfway;
    	double xH = 0;
    	double yV = 0;
    	
    	xH = (this.size*(x - xHalfway))/xHalfway;
    	yV = (this.size*(yHalfway - y))/yHalfway;
    	
    	double[] arr = {xH, yV};
    	return arr;
    }
    
    public void ResetParameters(){
    	zoom = 0;
    	this.xHalfway = hSize/2.0;
    	this.yHalfway = vSize/2.0;
    	this.currentX = 0;
    	this.currentY = 0;
    }
    	  
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		double pressedCoords[] = ReparametrizePoints(e.getX(), e.getY());
		xPressed = pressedCoords[0];
		yPressed = pressedCoords[1];
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		double releasedCoords[] = ReparametrizePoints(e.getX(), e.getY());
		xReleased = releasedCoords[0];
		yReleased = releasedCoords[1];
		
		double xChange = (xReleased - xPressed);
		double yChange = (yReleased - yPressed);
		if (xChange != 0 || yChange != 0){
			mouseClicked(e);
		}
		
	}

}
