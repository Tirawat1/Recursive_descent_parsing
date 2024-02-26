import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * graph
 */
public class graph {
    private Map<vertex, List<vertex>> adjVertices;

    public graph(Map<vertex, List<vertex>> adjVertices){
        this.adjVertices = adjVertices;
    }

    void addVertex(Character lang){
        adjVertices.putIfAbsent(new vertex(lang), new ArrayList<>());
    }
    
    void removeVertex(Character lang){
        vertex chk = new vertex(lang);
        adjVertices.values().stream().forEach(e -> e.remove(chk));
        adjVertices.remove(new vertex(lang));
    }

    void addEdge(Character begin , Character next){
        vertex parent = new vertex(begin);
        vertex child = new vertex(next);
        adjVertices.get(parent).add(child);
    }

    void removeEdge(Character begin , Character next){
        vertex parent = new vertex(begin);
        vertex child = new vertex(next);
        List<vertex> chk1 = adjVertices.get(parent);
        List<vertex> chk2 = adjVertices.get(child);
        if(chk1 != null){
            chk1.remove(chk2);
        }
        if(chk2 != null){
            chk2.remove(chk1);
        }
    }

    public Map<vertex, List<vertex>> getMap(){
        return this.adjVertices;
    }
    
}