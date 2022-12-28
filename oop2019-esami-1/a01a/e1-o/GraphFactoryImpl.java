package a01a.e1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphFactoryImpl implements GraphFactory {
    @Override
    public <X> Graph<X> createDirectedChain(List<X> nodes) {

        Map<X, Set<X>> edges = new HashMap<>();
        for (int i = 0; i < nodes.size() ; i++) {
            if (i != nodes.size()-1) {
                edges.put(nodes.get(i), Set.of(nodes.get(i+1)));
            }
        }
        Graph<X> graph = new GraphImpl<>(new HashSet<>(nodes), edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createBidirectionalChain(List<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        for (int i = 0; i < nodes.size() ; i++) {
            if (i == nodes.size()-1) {
                edges.put(nodes.get(i), Set.of(nodes.get(i-1)));
            } else if (i == 0) {
                edges.put(nodes.get(i), Set.of(nodes.get(i+1)));
            } else {
                edges.put(nodes.get(i), Set.of(nodes.get(i-1), nodes.get(i+1)));
            }
        }
        Graph<X> graph = new GraphImpl<>(new HashSet<>(nodes), edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createDirectedCircle(List<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        for (int i = 0; i < nodes.size() ; i++) {
            if(i == nodes.size()-1) {
                edges.put(nodes.get(i), Set.of(nodes.get(0)));
            } else {
            edges.put(nodes.get(i), Set.of(nodes.get(i+1)));
            }
        }
        Graph<X> graph = new GraphImpl<>(new HashSet<>(nodes), edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createBidirectionalCircle(List<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        for (int i = 0; i < nodes.size() ; i++) {
            if (i == nodes.size()-1) {
                edges.put(nodes.get(i), Set.of(nodes.get(i-1), nodes.get(0)));
            } else if (i == 0) {
                edges.put(nodes.get(i), Set.of(nodes.get(i+1), nodes.get(nodes.size()-1)));
            } else {
                edges.put(nodes.get(i), Set.of(nodes.get(i-1), nodes.get(i+1)));
            }
        }
        Graph<X> graph = new GraphImpl<>(new HashSet<>(nodes), edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createDirectedStar(X center, Set<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        edges.put(center, nodes);
        Set<X> allNodes = new HashSet<>(nodes);
        allNodes.add(center);
        Graph<X> graph = new GraphImpl<>(allNodes, edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createBidirectionalStar(X center, Set<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        edges.put(center, nodes);
        for (X x : nodes) {
            edges.put(x, Set.of(center));
        }
        Set<X> allNodes = new HashSet<>(nodes);
        allNodes.add(center);
        Graph<X> graph = new GraphImpl<>(allNodes, edges);
        return graph;
    }

    @Override
    public <X> Graph<X> createFull(Set<X> nodes) {
        Map<X, Set<X>> edges = new HashMap<>();
        for (X x : nodes) {
            Set<X> nodeSet = new HashSet<>(nodes);
            nodeSet.remove(x);
            edges.put(x, nodeSet);
        }
        Graph<X> graph = new GraphImpl<>(new HashSet<>(nodes), edges);
        return graph;
    }

    @Override
    public <X> Graph<X> combine(Graph<X> g1, Graph<X> g2) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
