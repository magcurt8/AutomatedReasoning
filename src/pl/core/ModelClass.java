package pl.core;

import java.util.*;

/**
 * A Model is an assignment of boolean values (true or false) to
 * PropositionalSymbols.
 */
public class ModelClass implements Model {

	HashMap<Symbol, Boolean> m = new HashMap<Symbol, Boolean>();
	
	/**
	 * Set the value assigned to the given PropositionSymbol in this
	 * Model to the given boolean VALUE.
	 */
	public void set(Symbol sym, boolean value){
		m.put(sym, value);
	}

	/**
	 * Returns the boolean value associated with the given PropositionalSymbol
	 * in this Model.
	 */
	public boolean get(Symbol sym){
		//System.out.println(sym);
		return m.get(sym);
	}
	
	/**
	 * Return true if this Model satisfies (makes true) the given KB.
	 */
	public boolean satisfies(KB kb){
		boolean val = true;
		for(Sentence s : kb.sentences){
			val = val && s.isSatisfiedBy(this);
		}
		return val;
	}

	/**
	 * Return true if this Model satisfies (makes true) the given Sentence.
	 */
	public boolean satisfies(Sentence sentence){
		return sentence.isSatisfiedBy(this);
	}
	
	/**
	 * Print the assignments in this Model to System.out.
	 */
	public void dump(){
		for (Map.Entry<Symbol, Boolean> i : m.entrySet()){
			System.out.println(i.getKey().toString() + " : " + i.getValue());
		}
	}

}
