import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, MyExeption {

        Test.innitDb();
        Test.setClient("sql/setclient.txt");
        Test.setWorker("sql/setworker.txt");
        Test.setProjekt("sql/projekt.txt");
        Test.setProjektWorker("sql/projekt_worker.txt");


    }

}
