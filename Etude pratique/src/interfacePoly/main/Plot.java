package interfacePoly.main;

import javax.swing.JFrame;

import java.awt.*;
 
 
@SuppressWarnings("serial")
public final class Plot extends JFrame
{
	    private static Graphics gBuf = null;
	    private static GraphPaperCanvas canvas = null;
	    private static Image vm = null;
	    private int x, y;
	    private int w, h;
	     
	
	    public Plot( int x, int y )
	    {
	        if ( canvas == null )
	        {
	            setTitle("Curve");
	            setSize(900,900);//taille fenêtre
	            setLocation(0,0);// endroit d'apparition de la fenêtre
	 
	            canvas = new GraphPaperCanvas(null);
	            getContentPane().add(canvas);
	            setVisible(true);
	 
	            vm = canvas.createImage(1100,950);//la limite de ce qu'il faut dessiner (doit etre plus grand que la taille du graph)
	            gBuf = vm.getGraphics();
	            canvas.setVm(vm);
	 
	            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        }
	 
	        this.x = x;
	        this.y = y;
	        w = 800; //taille du graph
	        h = 800; //taille du graph
	         
	        drawBounds();
	        gBuf.setColor( Color.RED );
	    }
	     
	    public void drawBounds()
	    {
	        Color cur = gBuf.getColor();
	        gBuf.setColor( Color.LIGHT_GRAY );
	        for ( int d=0; d<w; d+=w/10 ) //ratio de taille de 1 case
	            gBuf.drawLine( x+d, y+0, x+d, y+h );
	        for ( int d=0; d<h; d+=h/10 ) //ratio
	            gBuf.drawLine( x+0, y+d, x+h, y+d );
	 
	        gBuf.setColor( Color.BLACK );
	        gBuf.drawRect( x, y, w+1, h+1 );
	        gBuf.drawLine( x+w/2, y+0, x+w/2, y+h );
	        gBuf.drawLine( x+0, y+h/2, x+w, y+h/2 );
	        gBuf.setColor( cur );
	        canvas.repaint();
	    }   
	    public void setColor( Color c )
	    {
	        gBuf.setColor(c);
	    }
	
	    public void drawPoint( double px, double py )
	    {
	        if ( px > 100 || px < -100 || py > 100 || py < -100 )
	            return;
	 
	        px *= w/50;//ratio du nb de cases totales
	        py *= h/50;//ratio
	        px += w/2 + 1;
	        py = h/2 - py + 1;
	 
	        gBuf.drawLine( x+(int)px, y+(int)py, x+(int)px, y+(int)py );
	        canvas.repaint();
	    }
}

@SuppressWarnings("serial")
class GraphPaperCanvas extends Canvas
{
	    private Image vm;
	     
	    public GraphPaperCanvas( Image vm )
	    {
	        this.vm = vm;
	        setBackground( Color.white );
	    }
	     
	    public void setVm( Image vm )
	    {
	        this.vm = vm;
	    }
	     
	        @Override
	    public void paint( Graphics g )
	    {
	        g.drawImage(vm,0,0,this);
	    }
	     
	        @Override
	    public void update(Graphics g) { paint(g); }    
 
}