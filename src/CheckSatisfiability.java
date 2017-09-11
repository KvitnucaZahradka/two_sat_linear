import java.util.Scanner;

public class CheckSatisfiability {
    private static final String PATH = "./data/";
    private Graph graph;


    private CheckSatisfiability(String nameOfTwoSatOnDrive) throws Exception {

        // read in the graph
        this.graph = new Graph(nameOfTwoSatOnDrive);
    }

    private void checkTwoSat() throws Exception {
        this.graph.checkTwoSatisfiability();
    }


    public static void main(String[] args) throws Exception {
        String file;
        CheckSatisfiability check;

        file = "trial_1.txt";
        check = new CheckSatisfiability( CheckSatisfiability.PATH + file);
        check.checkTwoSat();


        // check for the rest of examples
        for(int i = 1; i<7; i++){
            file = "2sat" + i + ".txt";

            System.out.println("two sat instance : " + file);

            check = new CheckSatisfiability( CheckSatisfiability.PATH + file);

            check.checkTwoSat();
        }

    }
}
