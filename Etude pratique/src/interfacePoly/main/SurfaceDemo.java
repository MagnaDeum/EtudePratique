package interfacePoly.main;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import org.jzy3d.maths.Coord3d;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.ChartLauncher;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Range;
import org.jzy3d.plot3d.builder.Builder;
import org.jzy3d.plot3d.builder.Mapper;
import org.jzy3d.plot3d.builder.concrete.OrthonormalGrid;
import org.jzy3d.plot3d.primitives.Shape;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.legends.Legend;

@SuppressWarnings("unused")
public class SurfaceDemo extends Application{
		FctPolyND func;
		
		public SurfaceDemo (FctPolyND f){
			func=f;
		}
		@Override
		public void start(Stage primaryStage) throws Exception {
			FctPoly fctX=func.getChildren().get(0);
			FctPoly fctY=func.getChildren().get(1);
			// Define a function to plot
			Mapper mapper = new Mapper() {
			    public double f(double x, double y) {
			    	double resx=0;
			    	for(int i=0;i<fctX.getK();i++){
			    		resx+=fctX.tabPoly[i].getC()*Math.pow(x, fctX.tabPoly[i].getA())*Math.exp(-x*fctX.tabPoly[i].getLam());
			    	}
			    	double resy=0;
			    	for(int i=0;i<fctY.getK();i++){
			    		resy+=fctY.tabPoly[i].getC()*Math.pow(y, fctY.tabPoly[i].getA())*Math.exp(-y*fctY.tabPoly[i].getLam());
			    	}
			        return resx*resy;
			    	
			    }
			};
			
			// Define range and precision for the function to plot
			Range range = new Range(Math.min(fctX.getInf(), fctY.getInf()), Math.max(fctX.getSup(),fctY.getSup()));
			int steps = 100;
			
			// Create a surface drawing that function
			Shape surface = Builder.buildOrthonormal(new OrthonormalGrid(range, steps, range, steps), mapper);
			
			surface.setColorMapper(new ColorMapper(new ColorMapRainbow(), surface.getBounds().getZmin(), surface.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
			surface.setFaceDisplayed(true);
			surface.setWireframeDisplayed(false);
			surface.setWireframeColor(Color.BLACK);
	
			/*
			List<Coord3d> coordinates = new ArrayList<Coord3d>();
			for (int x = 0; x < 2; x+=1) {
			    for (int y = 0; y < 2; y+=1) {
			    	 Coord3d coord = new Coord3d(x,y,Math.exp(x));
			    	 coordinates.add(coord);
			    }
			}
			
			Shape bis = Builder.buildDelaunay(coordinates);
			bis.setColorMapper(new ColorMapper(new ColorMapRainbow(), bis.getBounds().getZmin(), bis.getBounds().getZmax(), new Color(1, 1, 1, .5f)));
			bis.setFaceDisplayed(true);
			bis.setWireframeDisplayed(false);
			bis.setWireframeColor(Color.BLACK);*/
			
			
			
			// Create a chart and add the surface
			Chart chart = new Chart(Quality.Advanced);
			chart.getScene().getGraph().add(surface);
			ChartLauncher.openChart(chart);
		
		}
		
}