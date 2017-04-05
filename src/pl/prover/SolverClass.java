package pl.prover;

import pl.core.KB;
import pl.core.Model;
import pl.core.Sentence;
import pl.core.*;
import java.math.*;
import java.util.Collection;
import java.util.Set;

import pl.cnf.*;

public class SolverClass implements Solver {
	
	/**
	 * If the given KB is satisfiable, return a satisfying Model.
	 */
	public Model solve(Set<Clause> cl, Collection<Symbol> kbs){
		double p = .5;
		Model model = new ModelClass();
		/*iterate through symbols in kb
		randomly set T/F in models*/
		for(Symbol s : kbs){
			double k = Math.random();
			if(k < p){
				model.set(s, true);
			} else{
				model.set(s, false);
			}
		}
		
		int max_flips = 25000;
		//iterate through max flips
		for(int i = 1; i < max_flips; i++){
			boolean ch = true;
			for(Clause s : cl){
				boolean j = s.isSatisfiedBy(model);
				ch = ch && j; 
			}
			/*true return model, else iterate through set of clauses
			if clause not satisfied by model, set new clause equal to unsatisfied clause*/
			if(ch){
				return model;
			} else{
				Clause b = null;
				for(Clause s : cl){
					if(!s.isSatisfiedBy(model)){
						b = s;
						break;
					}
				}
				//randomized symbol selection  
				double r = Math.random();
				if(r < p){
					Symbol sym = null;
					for(Literal l : b){
						sym = l.getContent();
						if(!model.get(sym)){
							break;
						}
					}
					if(sym != null){
						model.set(sym, true);
					}
				}
			}
		}return null;
		
	}
}
