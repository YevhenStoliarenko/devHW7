import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class DatabaseQueryPrepared {

    private static final String SET_CLIENT = """
            INSERT INTO mega.client(NAME)
            VALUES (?)
            """;

    private static final String SET_WORKER = """
            INSERT INTO mega.worker(NAME, BIRTHDAY, LEVEL, SALARY)
            VALUES (?, ?, ?, ?)
            """;

    private static final String SET_PROJEKT = """
            INSERT INTO mega.project(CLIENT_ID, START_DATE, FINISH_DATE)
            VALUES (?, ?, ?)
            """;

    private static final String SET_PROJECT_WORKER = """
            INSERT INTO mega.project_worker(PROJECT_ID, WORKER_ID)
            VALUES (?, ?)
            """;


    public static void innitDb() throws IOException, SQLException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/init_db.sql"));) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String[] result = stringBuilder.toString().split(";");
            for (String sqlQuery : result) {
                Connection connection = Database.getInstance().getConnection();
                try (Statement statement = connection.createStatement();){
                    statement.execute(sqlQuery);
                }catch (SQLException ex) {
                    throw new ConnectionExeption("Error with init");
                }

            }
        }


    }

    public static void setClient(String fileName) throws IOException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));) {
            StringBuilder stringBuilder = new StringBuilder();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuilder.append(lines);
                lines = bufferedReader.readLine();
            }
            String[] clients = stringBuilder.toString().split(",");

            for (String name : clients) {
                try (Connection connection = Database.getInstance().getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(SET_CLIENT);
                ) {
                    preparedStatement.setString(1, name);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new ConnectionExeption("Error with client");                }
            }
        }

    }

    public static void setWorker(String workerName) throws IOException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(workerName));) {
            StringBuilder stringBuilder = new StringBuilder();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuilder.append(lines);
                lines = bufferedReader.readLine();
            }
            String[] clients = stringBuilder.toString().split(";");

            for (String worker : clients) {
                String[] clientsDate = worker.split(",");
                try (
                        Connection connection = Database.getInstance().getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(SET_WORKER);
                ) {
                    preparedStatement.setString(1, clientsDate[0]);
                    preparedStatement.setString(2, clientsDate[1]);
                    preparedStatement.setString(3, clientsDate[2]);
                    preparedStatement.setString(4, clientsDate[3]);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new ConnectionExeption("Error with worker");
                }

            }
        }


    }

    public static void setProjekt(String projektName) throws IOException, ConnectionExeption {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(projektName));) {
            StringBuilder stringBuilder = new StringBuilder();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuilder.append(lines);
                lines = bufferedReader.readLine();
            }
            String[] projektLine = stringBuilder.toString().split(";");
            for (String projekts : projektLine) {
                String[] clientsDate = projekts.split(",");
                try (
                        Connection connection = Database.getInstance().getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(SET_PROJEKT);
                ) {
                    preparedStatement.setString(1, clientsDate[0]);
                    preparedStatement.setString(2, clientsDate[1]);
                    preparedStatement.setString(3, clientsDate[2]);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new ConnectionExeption("Error with project");
                }

            }
        }
        }




    public static void setProjektWorker(String projektWorker) throws IOException, ConnectionExeption {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(projektWorker));) {
            StringBuilder stringBuilder = new StringBuilder();
            String lines = bufferedReader.readLine();
            while (lines != null) {
                stringBuilder.append(lines);
                lines = bufferedReader.readLine();
            }
            String[] projektWorkerLine = stringBuilder.toString().split(";");
            for (String projektsWorker : projektWorkerLine) {
                String[] clientsDate = projektsWorker.split(",");
                try (
                        Connection connection = Database.getInstance().getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(SET_PROJECT_WORKER);
                ) {
                    preparedStatement.setString(1, clientsDate[0]);
                    preparedStatement.setString(2, clientsDate[1]);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    throw new ConnectionExeption("Error");
                }

            }
        }



    }


}