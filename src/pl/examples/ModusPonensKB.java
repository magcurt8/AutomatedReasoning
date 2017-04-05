package pl.examples;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.cnf.CNFConverter;
import pl.cnf.Clause;
import pl.core.*;
import pl.prover.ProverClass;
import pl.prover.SolverClass;

public class ModusPonensKB extends KB {
	
	public ModusPonensKB() {
		super();
		Symbol p = intern("P");
		Symbol q = intern("Q");
		add(p);
		add(new Implication(p, q));
	}
	
	public static void main(String[] argv) {
		new ModusPonensKB().dump();
		ModusPonensKB kb = new ModusPonensKB();
		//convert add symbol to symboltable
		Symbol q = kb.intern("Q");
		Sentence s = q;
		
		//PROVER - checks entailment of KB with interned sentence
		ProverClass pc = new ProverClass();
		boolean val = pc.entails(kb, s);
		System.out.println(val); //should be true
		
		//SOLVER - converts knowledge base to CNF
		ModusPonensKB kb1 = new ModusPonensKB();
		CNFConverter cnf = new CNFConverter();
		Set<Clause> cl = cnf.convert(kb1);
		//add new clause to set
		cl.add(new Clause(s));
		Collection<Symbol> kbs = kb1.symbols();
		//call solver on clause set and kb
		SolverClass sc = new SolverClass();
		Model m = sc.solve(cl, kbs);
		if(m == null){
			System.out.println("FAILURE");
		} else{
			m.dump();
		}
	}

}
