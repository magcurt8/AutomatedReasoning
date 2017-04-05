package pl.examples;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.cnf.CNFConverter;
import pl.cnf.Clause;
import pl.core.Biconditional;
import pl.core.Disjunction;
import pl.core.KB;
import pl.core.Model;
import pl.core.ModelClass;
import pl.core.Negation;
import pl.core.Sentence;
import pl.core.Symbol;
import pl.prover.ProverClass;
import pl.prover.SolverClass;

public class WumpusWorldKB extends KB {
	
	public WumpusWorldKB() {
		super();
		Symbol p11 = intern("P1,1");
		Symbol p12 = intern("P1,2");
		Symbol p21 = intern("P2,1");
		Symbol p22 = intern("P2,2");
		Symbol p31 = intern("P3,1");
		Symbol b11 = intern("B1,1");
		Symbol b21 = intern("B2,1");

		add(new Negation(p11));
		add(new Biconditional(b11, new Disjunction(p12, p21)));
		add(new Biconditional(b21, new Disjunction(p11, new Disjunction(p22, p31))));
		add(new Negation(b11));
		add(b21);
	}

	public static void main(String[] argv) {
		new WumpusWorldKB().dump();
		WumpusWorldKB kb = new WumpusWorldKB();
		Symbol p12 = kb.intern("P1,2");
		Sentence s = p12;
		
		//PROVER
		ProverClass pc = new ProverClass();
		boolean val = pc.entails(kb, s);
		System.out.println(val); //should be false
		
		//SOLVER
		WumpusWorldKB kb1 = new WumpusWorldKB();
		CNFConverter cnf = new CNFConverter();
		Set<Clause> cl = cnf.convert(kb1);
		//add new clause to set of clauses		
		cl.add(new Clause(p12));
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
