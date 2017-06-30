package interfacePoly.main;

import java.util.*;
/**
 * Class that modelizes a TPF like FctPoly, but here the TPF can be of any dimension.
 * When two or more 1D. TPF are multiplied a new FctPolyND should be created storing each TPF.
 * TPF variables should be independent. 
 */
public class FctPolyND {

		/** Stores multiple TPF to modelize a N dimension TPF */
		List<FctPoly> N_FctPoly;		
		
		/**
		 * Creates a new N dimension TPF from a TPF array or TPF seperated by a comma.
		 * @param args : any number of TPF
		 */
		public FctPolyND(FctPoly... args){
			N_FctPoly = new ArrayList<FctPoly> (args.length);
			for(FctPoly arg : args){
				N_FctPoly.add(arg);
			}
		}
		
		/**
		 * Creates a new N dimension TPF from a previous FctPolyND with added TPF seperated by a comma.
		 * @param parent 
		 * @param args : any number of TPF
		 */
		public FctPolyND(List<FctPoly> parent, FctPoly... args){
			parent.addAll(Arrays.asList(args));
			N_FctPoly = new ArrayList<FctPoly> (parent.size());
			N_FctPoly.addAll(parent);
		}
		
		/**
		 * Creates a new N dimension TPF from two previous FctPolyND.
		 * @param parent1  
		 * @param parent2
		 */
		public FctPolyND(List<FctPoly> parent1, List<FctPoly> parent2){
			N_FctPoly = new ArrayList<FctPoly> (parent1.size()+parent2.size());
			N_FctPoly.addAll(parent1);
			N_FctPoly.addAll(parent2);
		}
	
	
		/**
		 * Computes the value (double) of the integral of this TPF. 
		 * The integral of f(x,y) is equal to integral of f(x) * integral of f(y) since variables should be independent.
		 * @return (Double) Product of all the TPF this one contains
		 */
		public double integraleND(){
			double res=1;
			Iterator<FctPoly> i= N_FctPoly.iterator();
			while(i.hasNext()){
				res*=i.next().integraleFct();
			}
			return res;
		}
		
		/**
		 * Verifies that this TPF is a distribution, i.e its integral equals 1.
		 * @return Boolean
		 */
		public boolean verifDistrib(){
			return !(1<this.integraleND() || this.integraleND()<0.999); 
					
		}
		
		
		/**
		 * Transforms this TPF into a distribution by using faireDistrib() on each TPF. 
		 * Does nothing if the TPF is already a distribution.
		 */
		public void faireDistrib(){
			if(!this.verifDistrib()){
				for(FctPoly n : N_FctPoly){
					n.faireDistrib();
				}
			}
		}

		public FctPolyND projection(List<Integer> choix){
			if(choix.size()>N_FctPoly.size()||choix.size()<1){
				System.out.println("Erreur");//a voir pour produire une erreur
				return null;
			}
			else {
				//si choix = 0,2 ; on selectionne x, et z
				List<FctPoly> proj= new ArrayList<FctPoly>();

				for (int i=0; i< choix.size(); i++){
					proj.add(N_FctPoly.get(choix.get(i)));
				}

				return new FctPolyND(proj);
			}			
		}

		
		/**
		 * Computes the probability of variables being inferior to the corresponding limit. If your ND function is composed of 3, f(x), g(y), h(z) functions
		 * entering an array of 1, 2, 3 will compute the probability of x<1, y<2, z<3. The array entered should be the same size than your function's dimension.
		 * @param lim : List(double)
		 * @return Probability of the variables being inferior to the entered values.
		 */
		public double probaInf(List<Double> lim){
			double res=1;
			//test s'il y a plus ou moins de limites que de variables
			if (lim.size() != N_FctPoly.size())
				return -1;// a voir pour produire une erreur
			else {
				for (int i=0; i< lim.size(); i++){
					res*=N_FctPoly.get(i).probaInf(lim.get(i));
				}
				return res;
			}
		}
		// a faire pour proba limite supérieur TODO
		
		
		
		/**
		 * Restricts the upper limit of the chosen variables. If your ND function is composed of 3, f(x), g(y), h(z) functions
		 * entering an array of 0, 2 and another of 20, 30 will set x on [lower, 20] and z on [lower, 30].
		 * @param choix : List(int)
		 * @param lim : List(double)
		 */
		public void restrictionSup(List<Integer> choix, List<Double> lim){
			if(choix.size()!=lim.size())
				System.out.println("Erreur");//a voir pour produire une erreur
			else {
				//si choix = 1,3 ; on selectionne f(x), et f(z)
				for (int i=0; i< choix.size(); i++){
					N_FctPoly.get(choix.get(i)).setSup(lim.get(i));
				}
				double vd = this.integraleND();  //integrale f(x,y,z,etc) avec x<d, z<u, etc
				//  1/vd appliqué a chaque ligne de f(x) et f(z), etc 
				for (int i=0; i< choix.size(); i++){
					FctPoly temp = N_FctPoly.get(choix.get(i)); // on recupere f(x), etc
					for(int j=0;j<temp.getK();j++){
						temp.tabPoly[j].setC(temp.tabPoly[j].getC()/vd); // on divise chaque iteration par vd
					}
				}
			}
		}


		/**
		 * Restricts the lower limit of the chosen variables. If your ND function is composed of 3, f(x), g(y), h(z) functions
		 * entering an array of 0, 2 and another of 20, 30 will set x on [20, upper] and z on [30, upper].
		 * @param choix : List(int)
		 * @param lim : List(double)
		 */
		public void restrictionInf(List<Integer> choix, List<Double> lim){
			if(choix.size()!=lim.size())
				System.out.println("Erreur");//a voir pour produire une erreur
			else {
				//si choix = 1,3 ; on selectionne f(x), et f(z)
				for (int i=0; i< choix.size(); i++){
					N_FctPoly.get(choix.get(i)).setInf(lim.get(i));
				}
				double vd = this.integraleND();  //integrale f(x,y,z,etc) avec x<d, z<u, etc
				//  1/vd appliqué a chaque ligne de f(x) et f(z), etc 
				for (int i=0; i< choix.size(); i++){
					FctPoly temp = N_FctPoly.get(choix.get(i)); // on recupere f(x), etc
					for(int j=0;j<temp.getK();j++){
						temp.tabPoly[j].setC(temp.tabPoly[j].getC()/vd); // on divise chaque iteration par vd
					}
				}
			}
		}
		
		
		
		/**
		 * Gets the dimension N of the function. Also equivalent to the number of 1D functions composing it.		
		 * @return Dimension : int
		 */
		public int getDim(){
			return N_FctPoly.size();		
		}
		
		public double getInf(int i){
				return N_FctPoly.get(i).getInf();
		}
		
		public double getSup(int i){
			return N_FctPoly.get(i).getSup();
		}
		
		public List<FctPoly> getChildren(){
			return N_FctPoly;
		}

	
	
}
