package test;

import interfacePoly.main.FctPoly;

import interfacePoly.main.FctPolyND;
import interfacePoly.main.Poly;

import org.junit.Before;
import org.junit.Test;

public class testFctPolyND {

    FctPoly testx;
    FctPoly testy;
    FctPolyND testND;
    
    @Before
    public void setUp() throws Exception {
            Poly test1=new Poly(1, 2, 0.1);
            Poly test2=new Poly(1, 2, 0.01);
            Poly test3=new Poly(1, 2, 0.08);
            Poly[] temp= {test1,test2,test3};
            testx= new FctPoly(temp, 0.0,1.0); // f(x)= 1*x^2*exp(-0.1x)+1*x^2*exp(-0.01x)+1*x^2*exp(-0.08x)
            Poly test4=new Poly(2, 5,5);
            Poly test5=new Poly(2, 1, 3);
            Poly test6=new Poly(3, 2, 1);
            Poly[] temp1= {test4,test5,test6};
            testy= new FctPoly(temp1,0.0,2.0);// f(y)= 2*y^5*exp(-5y)+2*y^1*exp(-3y)+3*y^2*exp(-y)
            testND= new FctPolyND(testx,testy,testy,testx);//on peut en mettre autant qu'on veut...c'est génial !
               
    
    }
    
	    @Test
	    public void testIntegrale2D() {
	           
	    	System.out.println(testND.integraleND());

	    }

}
