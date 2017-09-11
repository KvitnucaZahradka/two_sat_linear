import java.io.*;
import java.util.*;

public class Read implements Reading{


    /**
     *
     * PRIVATE HELPFUL FUNCTIONS
     * */
    private Set<Integer>[] createSet(Integer vertex, boolean inverseGraph){
        Set<Integer>[] st = new HashSet[2];

        /* add empty linked lists */
        // forward arrows
        st[0] = new HashSet<>();

        // backward arrows
        st[1] = new HashSet<>();

        if(!inverseGraph){
            st[0].add(vertex);
        }
        else {
            st[1].add(vertex);
        }

        return st;
    }

    private void addOneEdge(HashMap<Integer, Set<Integer>[]> graph, Integer vertexA, Integer vertexB){
        // this adds a normal arrow for (A v B) <==> A' ==> B && B' ==> A
        // NOTE: here we are adding the edge A' ==> B
        if(graph.containsKey(-vertexA)){
            graph.get(-vertexA)[0].add(vertexB);
        }
        else{
            graph.put(-vertexA, this.createSet(vertexB, false));
        }

        // you also need to add an edge in an inverse graph
        // NOTE: here we are adding edge B ==> A'
        if(graph.containsKey(vertexB)){
            graph.get(vertexB)[1].add(-vertexA);
        }
        else {
            graph.put(vertexB, this.createSet(-vertexA, true));
        }
    }


    private void addClause(HashMap<Integer, Set<Integer>[]> graph, Integer vertexA, Integer vertexB){
        this.addOneEdge(graph, vertexA, vertexB);
        this.addOneEdge(graph, vertexB, vertexA);
    }


    /**
     *
     * PUBLIC HELPFUL FUNCTIONS
     * */


    // THIS FUNCTION READS IN THE CLAUSES AND PRODUCES THE IMPLICATION GRAPH
    @Override
    public Object readClauses(String nameOfFile) throws IOException {
        String line;
        String[] arr;
        HashMap<Integer, Set<Integer>[]> graph =  new HashMap<>();


        // HERE WE IMPLEMENT THE READING FUNCTIONALITY
        try{
            LineNumberReader rdr = new LineNumberReader(new FileReader(nameOfFile));

            while((line = rdr.readLine()) != null){
                if(rdr.getLineNumber()>1){
                    arr = line.split("\\s+");

                    Integer vertexA = Integer.parseInt(arr[0]);
                    Integer vertexB = Integer.parseInt(arr[1]);


                    this.addClause(graph, vertexA, vertexB);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
            throw e;
        }
        return graph;
    }

    @Deprecated
    public Integer readNumberOfClauses(String nameOfFile) throws IOException{
        System.out.println("METHOD IS DEPRECATED");
         throw new IOException();
    }

    @Deprecated
    public Object readClauses(String nameOfFile, int numberOfClauses) throws IOException{
        System.out.println("METHOD IS DEPRECATED");
        throw new IOException();
    }
}




























