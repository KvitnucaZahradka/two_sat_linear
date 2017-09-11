import java.io.IOException;

public interface Reading<T> {

    /**
     * function that reads clauses, returns the array of the :
     * *
     * */
    Object readClauses(String nameOfFile, int numberOfClauses) throws IOException;


    /**
     * function that reads clauses, returns the array of the :
     * *
     * */
    Object readClauses(String nameOfFile) throws IOException;



    /**
     * reads number of clauses from the file
     * returns T
     */
    T readNumberOfClauses(String nameOfFile) throws IOException;
}
