package interfacePoly.main;

import javafx.application.Application;
import javafx.stage.Stage;

public class PlotLauncher extends Application{
	private FctPoly input;
	
	public PlotLauncher(FctPolyND inp){
		input=inp.getChildren().get(0);
	}
	
	@Override
	public void start(Stage secondaryStage) throws Exception
    {
		
        Plot gp1 = new Plot(10,10); //coordon�es du graphe dans la fen�tre
        gp1.setDefaultCloseOperation(Plot.DISPOSE_ON_CLOSE);
        double res;
        for ( double x = input.getInf(); x<=input.getSup(); x+=0.01 ) //precision du graph
        {	res=0;
            for(int i=0;i<input.getK();i++)
            	res += input.tabPoly[i].getC()*(Math.pow(x,input.tabPoly[i].getA()))*Math.exp(-1*input.tabPoly[i].getLam()*x); //equation
            gp1.drawPoint(x,res);
        }
        for ( double r = -25; r<=25; r+=0.01 ) {
        	
        	gp1.drawPoint(input.getInf(),r);
        	gp1.drawPoint(input.getSup(),r);
    	}        
    }
}
