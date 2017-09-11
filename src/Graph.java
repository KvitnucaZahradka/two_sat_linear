
/**
 *
 * IMPORTS
 * */
import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import java.util.*;


class Graph extends GraphAbstract {
    // here I am keeping the strongly connected components
    private Stack<Set<Integer>> scc;

    /**
     *
     * CONSTRUCTOR
     * */
     Graph(String nameOfTwoSatOnDrive){
        Read read = new Read();

        try {
            super.graph = (Map<Object, Set<Object>[]>) read.readClauses(nameOfTwoSatOnDrive);
            this.scc = new Stack<>();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * HELPFUL METHODS
     * */

    // function that creates the post order in a graph
    private Stack<Integer> calculateTraversalStack() throws Exception{
        Integer vertex;

        // basically here you say everyone is unvisited at the beginning
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> postOrder = new Stack<>();

        // iterate through the graph vertices
        for (Object o : this.graph.keySet()) {
            vertex = (Integer) o;

            // only if vertex is still unvisited
            if (!visited.contains(vertex))
                dfs(vertex, visited, postOrder);
        }
        return postOrder;
    }


    private void findSccCoponents() throws Exception {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> traversalStack = this.calculateTraversalStack();

        // iterate through the traversal stack
        while(!traversalStack.empty()){
            Integer vertex = traversalStack.pop();

            if(!visited.contains(vertex)){
                // here you are creating a new set holding vertices in the same scc
                this.scc.push(new HashSet<>());
                inverseDfs(vertex, visited);
            }
        }
    }

    private void check(){
        Set<Integer> component;

        while(!this.scc.empty()){
           component = this.scc.pop();

           for(Integer o: component) {
                if(component.contains(-o)){
                    System.out.println("0");
                    return;
                }
           }
        }

        System.out.println("1");
    }


    private void inverseDfs(Integer startVertex, HashSet<Integer> visited){
        Integer vertex;
        Boolean[] added;
        Stack<Integer> traversalStack = new Stack<>();
        traversalStack.push(startVertex);

        while (!traversalStack.empty()){
               vertex = traversalStack.pop();

            if(!visited.contains(vertex)) {
                // immediately add stuff to visited
                visited.add(vertex);

                // peek and edd a new vertex
                this.scc.peek().add(vertex);

                added = new Boolean[]{false};

                for (Object vert : this.graph.get(vertex)[1]) {
                    if (!visited.contains(vert)) {
                        traversalStack.add((Integer) vert);
                        added[0] = true;
                    }
                }

                if (!added[0])
                    this.scc.peek().add(vertex);
            }
        }
    }


    private void dfs(Integer startVertex, HashSet<Integer> visited, Stack<Integer> postOrder){
        Integer vertex;
        Boolean[] added;
        Stack<Integer> traversalStack = new Stack<>();
        traversalStack.push(startVertex);

        while(!traversalStack.empty()){
            vertex = traversalStack.pop();

            if(!visited.contains(vertex)){
                // immediately add stuff to visited
                visited.add(vertex);

                // check whether you added something
                added = new Boolean[]{false};

                for(Object vert: this.graph.get(vertex)[0]){
                    if(!visited.contains(vert)){
                        traversalStack.add((Integer)vert);
                        added[0] = true;
                    }
                }

                // add to the post order
                if(!added[0])
                    postOrder.add(vertex);
            }
        }
    }



    @Deprecated
    @Nullable
    private Integer discoverNextUnvisited(Set<Object>[] nextVertices, HashSet<Integer> visited,
                                          boolean inverseGraph) {
        Integer nextTrialVertex;
        Set<Object> next;

        if(!inverseGraph){
            next = nextVertices[0];
        }
        else {
            next = nextVertices[1];
        }

        while(!next.isEmpty()){
            // note implement the way in which you remember only iterator in some state!!!! do not destroy the info
            Integer integ;

            if(!inverseGraph){
                integ = (Integer) nextVertices[0].iterator().next();
                nextVertices[0].remove(integ);
            }
            else{
                integ = (Integer) nextVertices[1].iterator().next();
                nextVertices[1].remove(integ);
            }

            nextTrialVertex = integ;

            if(!visited.contains(nextTrialVertex)){
                return nextTrialVertex;
            }
        }
        return null;
    }


    /**
     * PUBLIC HELPFUL FUNCTIONS
     * */

    void checkTwoSatisfiability() throws Exception {

        // first calculate SCC
        this.findSccCoponents();

        // then just check whether there is such scc that contains A and also -A
        // if yes then 2 sat is not satisfiable
        // if no then 2 sat is satisfiable
        this.check();
    }

}



















