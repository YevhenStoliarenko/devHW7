import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException, ConnectionExeption {

//        DatabaseQueryPrepared.innitDb();
//        DatabaseQueryPrepared.setClient("sql/setclient.txt");
//        DatabaseQueryPrepared.setWorker("sql/setworker.txt");
//        DatabaseQueryPrepared.setProjekt("sql/projekt.txt");
//        DatabaseQueryPrepared.setProjektWorker("sql/projekt_worker.txt");

//        System.out.println(DatabaseQueryService.findMaxSalaryWorker());
//        System.out.println(DatabaseQueryService.findLongestProject());
//        System.out.println(DatabaseQueryService.findMaxProjectsClient());
//        System.out.println(DatabaseQueryService.printProjectPrices());
        System.out.println(DatabaseQueryService.findYoungestEldestWorkers());


    }

}
