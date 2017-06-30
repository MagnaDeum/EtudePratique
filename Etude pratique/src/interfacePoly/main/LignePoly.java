package interfacePoly.main;

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 /**
  * LignePoly defines the nature of the lines(rows) of the table. It also specifies methods to modify or get the data of a line.
  *
  */
public class LignePoly {
	
	/**
	 * Specifies the name of the function on this line.
	 */
	private final SimpleStringProperty name;
	
	/**
	 * Boolean value to indicate if the function on this line is currently a distribution or not.
	 */
    private final BooleanProperty distrib;
    
    /**
     * Specifies the current limits of the function on the line.
     */
    private final SimpleStringProperty limites;
    
    //pas utile ? Si on veut la proba que x inferieur ou superieur a une valeur il faudrait plutot faire un bouton.
    private final DoubleProperty proba;
    
    /**
     * Specifies the dimension of the TPF currently on the line.
     */
    private final IntegerProperty dimension;
    
    /**
     * The function described on this line.
     */
    private FctPolyND func;


    //constructeur a commenter quand add sera implementer TODO
    public LignePoly(){
    	name=new SimpleStringProperty("test");
    	distrib=new SimpleBooleanProperty(false);
    	limites=new SimpleStringProperty("");
    	proba=new SimpleDoubleProperty(0.0);
    	dimension=new SimpleIntegerProperty(1);
    	Poly test2=new Poly(6, 8, 5);
    	Poly test3=new Poly(5, 6, 2);
    	Poly[] temp= {test2,test3};

    	func=new FctPolyND(new FctPoly(temp, 0.0,10.0));
    }

    /**
     * Constructor for a table-line(row).
     * @param n : Name of the function (String)
     * @param function 
     */
    public LignePoly(String n, FctPolyND function) {
        name = new SimpleStringProperty(n);
        func = new FctPolyND(function.getChildren());
        distrib = new SimpleBooleanProperty(func.verifDistrib());
        limites = new SimpleStringProperty(func.getInf(0)+" | "+func.getSup(0)); //only shows the limits of the first variable/dimension.
        proba = new SimpleDoubleProperty(func.integraleND());
        dimension = new SimpleIntegerProperty(func.getDim());
    }

    public String getName() {
        return name.get();
    }

    public void setName(String n) {
        name.set(n);
    }

    public boolean getDistrib() {
        return distrib.get();
    }

    public void setDistrib(boolean b) {
        distrib.set(b);
    }

    public String getLimites() {
        return limites.get();
    }

    public void setLimites(double inf, double sup) {
    	for(int i=0;i<this.getFunc().getChildren().size();i++){
    		this.getFunc().getChildren().get(i).setInf(inf);
    		this.getFunc().getChildren().get(i).setSup(sup);
    	}    	
    	// a voir pour la mise en page des limites dans le tableau TODO
        limites.set(inf+" | "+sup);
    }
    
    public double getProba() {
        return proba.get();
    }

    public void setProba(double p) {
        proba.set(p);
    }
    
   
    public int getDimension() {
        return dimension.get();
    }

    public void setDimension(int d) {
        dimension.set(d);
    }
    
    public FctPolyND getFunc() {
        return func;
    }

    public void setFunc(FctPolyND f) {
        func = f;
    }
    
    public LignePoly multiplier(FctPolyND parent){
    	return new LignePoly( this.getName()+(this.getDimension()+parent.getDim()), //si quelqu'un a une idee de comment nommer les nouvelles fct TODO
    			new FctPolyND(parent.getChildren(), this.getFunc().getChildren()));  	
    	
    }
    
    public LignePoly projection(List<Integer> choix){
    	return new LignePoly(this.getName()+getDimension(), //idem TODO
    			this.getFunc().projection(choix));  	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
}

