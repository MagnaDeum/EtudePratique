package test;

import static org.junit.Assert.*;
import interfacePoly.main.FctPoly;
import interfacePoly.main.Poly;

import org.junit.Before;
import org.junit.Test;


public class testFctPoly {
		Poly test1;
        FctPoly testx;
        FctPoly testy;
        
        @Before
        public void setUp() throws Exception {
                test1=new Poly(1, 2.0, 0.1);
                Poly test2=new Poly(1, 2, 0.01);
                Poly test3=new Poly(1, 2, 0.08);
                Poly[] temp= {test1,test2,test3};
                testx= new FctPoly(temp, 0.0,1.0);
                
        }
        
        @Test
        public void testProbaInf(){
        	System.out.println("Valeur de la proba que X < 0,3 = "+testx.probaInf(0.3));
        	System.out.println("Valeur de la proba que X < 4 = "+testx.probaInf(4));//Si x > limite superieure la proba vaut bien 0 ?
        	assertEquals(0, testx.probaInf(4), 0.001);
        	assertEquals(0.0266192,testx.probaInf(0.3),0.001);
        }

        @Test
        public void testIntegrale() {
        		System.out.println(test1.integral(0, 1));
        		System.out.println("Valeur de l'integrale = "+testx.integraleFct());
                assertEquals(0.954109,testx.integraleFct(),0.00001);
        }
        @Test
        public void testVerifDistrib(){
                assertFalse(testx.verifDistrib());
        
        }
        @Test
        public void testFaireDistrib(){
                testx.faireDistrib();
                System.out.println("Integrale distibution = "+testx.integraleFct());
                assertEquals(1,testx.integraleFct(),0.001);
                
        }

}