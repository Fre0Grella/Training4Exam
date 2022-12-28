package a01a.e1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class GraphImpl<X> implements Graph<X> {

    Set<X> nodes = new HashSet<>();
    Map<X,Set<X>> edges = new HashMap<>();

    
    public GraphImpl(Set<X> nodes, Map<X, Set<X>> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }

    @Override
    public Set<X> getNodes() {
        return nodes;
    }

    @Override
    public boolean edgePresent(X start, X end) {
        return edges.get(start).contains(end);
    }

    @Override
    public int getEdgesCount() {
        return edges.entrySet().stream()
                                .mapToInt(x -> x.getValue().size())
                                .sum();
    }

    @Override
    public Stream getEdgesStream() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
