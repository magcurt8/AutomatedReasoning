package pl.prover;

import pl.core.KB;
import pl.core.Sentence;
import pl.core.*;

import java.util.Collection;
import java.util.Set;

import pl.cnf.*;

public interface Solver {
	
	/**
	 * If the given KB is satisfiable, return a satisfying Model.
	 */
	public Model solve(Set<Clause> clause, Collection<Symbol> kbs);


}
