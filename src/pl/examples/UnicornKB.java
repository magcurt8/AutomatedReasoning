package pl.examples;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import pl.cnf.CNFConverter;
import pl.cnf.Clause;
import pl.core.*;
import pl.prover.ProverClass;
import pl.prover.SolverClass;

public class UnicornKB extends KB {
	
	public UnicornKB() {
		super();
		Symbol m = intern("Mythical");
		Symbol i = intern("Immortal");
		Symbol h = intern("Horned");
		Symbol g = intern("Magical");
		Symbol ma = intern("Mammal");
		add(new Implication(m, i));
		add(new Implication(new Negation(m), new Conjunction(new Negation(i), ma)));
		add(new Implication(new Disjunction(i, ma), h));
		add(new Implication(h, g));
	}
	
	public static void main(String[] argv) {
		new UnicornKB().dump();
		UnicornKB kb = new UnicornKB();
		Symbol m = kb.intern("Mythical");
		Sentence s1 = m;
		//PROVER
		ProverClass pc = new ProverClass();
		boolean val = pc.entails(kb, s1);
		System.out.println("Can we prove unicorn is mythical? " + val); //should be false
	
		UnicornKB kb2 = new UnicornKB();
		Symbol g = kb2.intern("Magical");
		Sentence s2 = g;
		val = pc.entails(kb2, s2);
		System.out.println("Can we prove unicorn is magical? " + val); //should be true
		
		UnicornKB kb3 = new UnicornKB();
		Symbol h = kb3.intern("Horned");
		Sentence s3 = h;
		val = pc.entails(kb3, s3);
		System.out.println("Can we prove unicorn is horned? " + val); //should be true

		//SOLVER- CONVERT TO CNF
		UnicornKB kb4 = new UnicornKB();
		CNFConverter cnf = new CNFConverter();
		Set<Clause> cl = cnf.convert(kb4);
		//add new clause to set
		cl.add(new Clause(m));
		Collection<Symbol> kbs = kb.symbols();
		//call solver on clause set and kb
		SolverClass sc = new SolverClass();
		Model mo = sc.solve(cl, kbs);
		System.out.println("\nCan we solve unicorn is mythical?");
		if(mo == null){
			System.out.println("FAILURE");
		} else{
			mo.dump();
		}
		//CONVERT TO CNF
		Set<Clause> cl2 = cnf.convert(kb4);
		//add new clause to set
		cl2.add(new Clause(g));
		//call solver on clause set and kb	
		Model mo2 = sc.solve(cl2, kbs);
		System.out.println("\nCan we solve unicorn is magical?");
		if(mo2 == null){
			System.out.println("FAILURE");
		} else{
			mo2.dump();
		}
		//call solver on clause set and kb
		//CONVERT TO CNF	
		Set<Clause> cl3 = cnf.convert(kb4);
		cl3.add(new Clause(h));
		Model mo3 = sc.solve(cl3, kbs);
		System.out.println("\nCan we solve unicorn is horny?");
		if(mo3 == null){
			System.out.println("FAILURE");
		} else{
			mo3.dump();
		}
	}

}
