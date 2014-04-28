package ps3.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <b>PathFinder<b/> class provides facilities for finding paths between
 * sets of nodes in a graph. <p>
 *
 * The pathfinding algorithm accepts <b>PathFinderSpecializer</b> objects that dictate
 * how search must be carried out, allowing PathFinder's clients to perform Depth-First,
 * Breadth-First, Dijkstra shortest path, and A* search depending on what specializer is supplied.
 *
 *  @specfield none.
 *
 **/

public class PathFinder<E> {

        /**
         * <p>
         * Returns a path between any node on the ends of paths in the starting set
         * to any node in the set of goal nodes in a graph, using the provided
         * <tt>PathFinderSpecializer</tt>
         * </p>
         *
         * @param specializer the PathFinderSpecializer providing information on how
         * graph nodes are connected and what order search should examine nodes in.
         * @param starts the set of starting paths.
         * @param goals the set of goal nodes.
         * @requires <code> specializer != null && starts != null && starts.size()>0 && goals != null &&</code>
         *                              no node or path in starts or goals is null
         * @modifies none.
         * @return a path from any node in starts to any node in goals,
         *                      or null if none exists.
         **/
        public static<E> Path<E> findPath(PathFinderSpecializer<E> specializer,
                                                                          Collection<Path<E>> starts,
                                                                          Collection<E> goals) {

                PriorityQueue<Path<E>> active =
                        new PriorityQueue<Path<E>>(starts.size(), specializer);
                Set<E> finished = new HashSet<E>();
                for (Path<E> n: starts) {
                        active.add(n);
                }

                // Go while there are still nodes left to explore
                while (!active.isEmpty()) {
                        Path<E> path = active.poll();
                        E node = path.end();

                        // If we reached a goal then we are done
                        if (goals.contains(node))
                                return path;

                        finished.add(node);

                        for (E n: specializer.expandNode(node)){
                                if (!finished.contains(n)){
                                        boolean isActive = false;
                                        for (Path<E> p: active)
                                                if (n.equals(p.end())){
                                                        isActive = true;
                                                        break;
                                                }
                                        // If a child node is not already in finished or active
                                        // lists, then add to our active queue
                                        if (!isActive){
                                                active.add(path.extend(n));
                                        }
                                }
                        }
                }

                return null;
        }

}
