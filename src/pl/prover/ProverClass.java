package pl.prover;

import java.util.Collection;
import java.util.Map;

import pl.core.KB;
import pl.core.Sentence;
import pl.core.Model;
import pl.core.ModelClass;
import pl.core.Symbol;
import pl.core.SymbolTable;

public class ProverClass implements Prover {
	
	/**
	 * Return true if the given KB entails the given Sentence.
	 */
	public boolean entails(KB kb, Sentence alpha) {
		ModelClass m = new ModelClass();
		Collection<Symbol> s = kb.symbols();
		return checkAll(kb, alpha, s, m, 0);
	}
	//check all returns boolean value 
	public static boolean checkAll(KB kb, Sentence alpha, Collection<Symbol> s, Model m, int index){
		if(index == s.size()){//empty
			if(m.satisfies(kb)){//if model satisfies the given knowledge base
				//System.out.println("ALPHA: " + m.satisfies(alpha));
				return m.satisfies(alpha);//return true if sentence satisfies model
			} else{
				return true; //default return true
			}
		} else{
			Symbol p = null;
			int count = 0;
			//iterate through symbol collection and set p(first) to s1
			for(Symbol s1 : s){
				p = s1;
				//exit loop when iterated through collection
				if(count == index){
					break;
				}
				count++;
			}
			
			Model m1 = m;
			Model m2 = m;
			/*add p symbol to first model, set to true
			call check all on model 1
			*/
			m1.set(p, true);
			boolean part1 = checkAll(kb, alpha, s, m1, index+1);
			/*add p symbol to second model, set to true
			call check all on model 2
			*/
			m2.set(p, false);
			boolean part2 = checkAll(kb, alpha, s, m2, index+1);
				
			boolean part3 = part1 && part2;
			//System.out.println(part1 + " and " + part2 + " = " + part3);
			return part1 && part2;
		}
	}

}
