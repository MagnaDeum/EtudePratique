package interfacePoly.main;

/**
 * Represents one line or iteration of the sum from the formula that defines TPFs (truncated poly-exponential functions).
 * Stores the parameters c, a, and lambda from this formula : f(x)=c.(x^a).exp(-lam.x)
 */
public class Poly {
	
		double c;
		double a; //Si des erreurs surviennent sur les calculs d'intégrale, possiblement du au type de a qui était int au début.
		double lam;
		
		/**
		 * Create a new Poly, a.k.a. a new line for the sum of a TPF.
		 * @param c
		 * @param a
		 * @param lam
		 */
		public Poly(double c, double a, double lam) {
			this.c = c;
			this.a = a;
			this.lam = lam;
	
		}
		
		//par recursion, a voir si boucles moins couteux
		/**
		 * Computes the value of the integral of one line of a TPF sum : Integral of f(x)=c.x^a.exp(-lam.x)
		 * @param inf : upper limit of the integral
		 * @param sup : lower limit of the integral
		 * @return (double) integral of a Poly
		 */
		public double integral(double inf, double sup) {
			if (a == 0) {
				return primPartiel(inf, sup);
			} else {
				Poly derive = new Poly(c * a / lam, a - 1, lam);
				return (primPartiel(inf, sup) + derive.integral(inf, sup));
			}
		}
		/**
		 * Used by integral, shouldn't be used on its own, or only if you know what you're doing.
		 * @param inf
		 * @param sup
		 * @return (c/-lam)*B^a*exp(-lam*B) - (c/-lam)*A^a*exp(-lam*A)
		 */
		public double primPartiel(double inf, double sup) {
			return (c / -lam) * (Math.pow(sup, a)) * (Math.exp(-lam * sup))
					- (c / -lam) * (Math.pow(inf, a) * (Math.exp(-lam * inf)));
		}
		
		public double getC(){
			return this.c;
		}
		
		public void setC(double val){
			this.c=val;
		}
		public double getLam(){
			return this.lam;
		}
		
		public void setLam(double val){
			this.lam=val;
		}
		public double getA(){
			return this.a;
		}
		
		public void setA(double val){
			this.a=val;
		}
	
}
