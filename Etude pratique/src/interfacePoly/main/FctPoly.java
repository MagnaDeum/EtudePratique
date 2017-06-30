package interfacePoly.main;

/**
 * Class that modelizes a one dimensional truncated poly-exponential function.  
 */
public class FctPoly {
	
		/** Array of k times the 3 attributes that define TPFs (c,a,lambda) as found in class Poly. */
		Poly [] tabPoly;
		
		/** Value (type double) of the lower limit of the range */
		double inf;	
		
		/** Value (type double) of the upper limit of the range */
		double sup; 
		
		/** Size of the array of Poly. Represents the maximum index of the sum. */  
		int k; 
		
		
		/**
		 * Creates a TPF with an array of Poly. The k attribute is set by the size of this array and should not be modified. 
		 * @param p
		 * @param borneInf
		 * @param borneSup
		 */
		public FctPoly(Poly [] array,double borneInf, double borneSup){
			k=array.length;
			tabPoly=array;
			inf= borneInf;
			sup= borneSup;
		} 
		
		/** 
		 * The toString method of FctPoly
		 * @return String representation of a TPF with "x" as its variable.
		 */
		public String toString(){
			StringBuilder res= new StringBuilder();
			for(int i=0;i<tabPoly.length;i++){
				res.append(" + ");res.append(tabPoly[i].getC()); res.append("x^(");res.append(tabPoly[i].getA());res.append(") * exp(-");res.append(tabPoly[i].getLam());res.append("x)");
			}
			res.delete(0, 2);
			return res.toString();
		}
		
		/**
		 * Sets the value of the lower limit of the range.
		 * @param val
		 */
		public void setInf(double val){
			this.inf=val;
		}
		
		/**
		 * Sets the value of the upper limit of the range.
		 * @param val
		 */
		public void setSup(double val){
			this.sup=val;
		}
		
		/**
		 * @return Returns the value of the lower limit of the range.
		 */
		public double getInf(){
			return this.inf;
		}
		
		/**
		 * @return Returns the value of the upper limit of the range.
		 */
		public double getSup(){
			return this.sup;
		}
		
		/**
		 * @return Returns the size of the array, a.k.a the max index of the sum.
		 */
		public int getK(){
			return this.k;
		}


		/**
		 * Used to compute the probability that "x" is lower than lim.
		 * @param lim
		 * @return A probability
		 */
		public double probaInf(double lim){
			if(lim > sup || lim < inf){
				return 0;
			}
			double res=0;
			for(int i=0;i<k;i++){
				res+=this.tabPoly[i].integral(inf,lim);
			}
			return res;
		}
		
		/**
		 * Computes the value (double) of the integral of this TPF. 
		 * Depends on the current range so changing the limits will change this value. 
		 * @return Value of the integral
		 */
		public double integraleFct(){
			double res=0;
			for(int i=0;i<k;i++){
				res+=this.tabPoly[i].integral(inf,sup);
			}
			return res;
		}
		
		/**
		 * Verifies that this TPF is a distribution, i.e its integral equals 1.
		 * @return Boolean
		 */
		public boolean verifDistrib(){
			return !(1<this.integraleFct() || this.integraleFct()<0.999);
			
		}
		
		/**
		 * Transforms this TPF into a distribution by dividing each line of the array by its integral. 
		 * Does nothing if the TPF is already a distribution.
		 */
		public void faireDistrib(){
			if(!this.verifDistrib()){
				double integrale= this.integraleFct();
				for (int i=0;i<k;i++){
					tabPoly[i].setC(tabPoly[i].getC()/integrale);
				}
			}
		}
		//faire une methode qui construit la fonction cumulative c.a.d. integral(f(x)) avec x qui varie dans les limites. Egale a 1 a partir d'un certain x. 
		//le prof voudrait que l'on memorise cette fonction, ducoup contrairement a integral ci dessus, il ne faut pas appliquer direct.
		//on peut faire ca a la fin (sampling) TODO
		
}

