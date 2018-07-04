package soot.jimple.toolkits.ide.icfg;

import heros.InterproceduralCFG;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import soot.Value;
import soot.toolkits.graph.DirectedGraph;

/**
 * An {@link InterproceduralCFG} which supports the computation of predecessors.
 */
public interface BiDiInterproceduralCFG<N, M> extends InterproceduralCFG<N, M> {

	List<N> getPredsOf(N u);
	
	Collection<N> getEndPointsOf(M m);

	List<N> getPredsOfCallAt(N u);

	Set<N> allNonCallEndNodes();
		
	//also exposed to some clients who need it
    DirectedGraph<N> getOrCreateUnitGraph(M body);

	List<Value> getParameterRefs(M m);
	
	/**
	 * Gets whether the given statement is a return site of at least one call
	 * @param n The statement to check
	 * @return True if the given statement is a return site, otherwise false
	 */
    boolean isReturnSite(N n);
	
	/**
	 * Checks whether the given statement is rachable from the entry point
	 * @param u The statement to check
	 * @return True if there is a control flow path from the entry point of the
	 * program to the given statement, otherwise false
	 */
    boolean isReachable(N u);

}
