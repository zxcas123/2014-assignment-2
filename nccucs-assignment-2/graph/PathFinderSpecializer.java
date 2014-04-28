package ps3.graph;

import java.util.Comparator;

/**
 * <p>
 * <tt>PathFinderSpecializer</tt> encapsulates a specific pathfinding behavior. Namely,
 * a <tt>PathFinderSpecializer</tt> exports information on how nodes are connected and
 * what order the paths should be explored in when performing the search.
 * </p>
 *
 * <p>
 * <tt>PathFinderSpecializer</tt> acts as a Facade for an abstract Graph ADT, exporting
 * node connectivity information through the <tt>expandNode</tt> method. Note that most
 * implementing classes will accept a Graph object in the constructor to provide this
 * functionality.
 * </p>
 *
 * <p>
 * Additionally, since the order in which nodes are visited is the main defining characteristic of
 * path search, <tt>PathFinderSpecializer</tt> allows creating custom searching
 * behaviors by specifying search order through the <tt>Comparator<Path<N>></tt> interface.
 * For example, simply ordering Paths by their cost would result in Dijkstra's shortest-path
 * search, while comparing Paths by their </tt>cost + heuristic</tt> would create A* behavior.
 * </p>
 *
 * @param <N>
 */
public interface PathFinderSpecializer<N> extends Comparator<Path<N>> {

        /**
         * Returns an iterable collection of children nodes of a <tt>node</tt>.
         * No specific ordering of the children nodes is required.
         * @param node the parent node to expand.
         * @return an iterable collection of nodes whose parent is <tt>node</tt>
         */
        public Iterable<N> expandNode(N node);

}
