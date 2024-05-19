import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {


    public static List<MaxSalaryWorker> findMaxSalaryWorker() throws IOException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/find_max_salary_worker.sql"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            List<MaxSalaryWorker> maxSalaryWorkers = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }

            String stringQuery = stringBuilder.toString();
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(stringQuery);)
            {

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    long salary = resultSet.getObject("SALARY", Long.class);
                    maxSalaryWorkers.add(new MaxSalaryWorker(name, salary));
                }

            } catch (SQLException e) {
                throw new ConnectionExeption("Error with find max salary worker");
            }

            return maxSalaryWorkers;
        }


    }

    public static List<LongestProject> findLongestProject() throws IOException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/find_longest_project.sql"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            List<LongestProject> longestProjects = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }

            String stringQueri = stringBuilder.toString();
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(stringQueri);)
            {

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int months = resultSet.getObject("months", Integer.class);
                    int projectId = resultSet.getObject("ID", Integer.class);
                    longestProjects.add(new LongestProject(projectId, months));
                }

            } catch (SQLException e) {
                throw new ConnectionExeption("Error with find longest project");
            }

            return longestProjects;
        }


    }

    public static List<MaxProjectCountClient> findMaxProjectsClient() throws IOException, ConnectionExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/find_max_projects_client.sql"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }

            String stringQueri = stringBuilder.toString();
            try (Connection connection = Database.getInstance().getConnection();
                 PreparedStatement statement = connection.prepareStatement(stringQueri);)
            {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    long countproject = resultSet.getObject("countproject", Long.class);
                    maxProjectCountClientList.add(new MaxProjectCountClient(name, countproject));
                }

            } catch (SQLException e) {
                throw new ConnectionExeption("Error with find max project client");
            }
            return maxProjectCountClientList;
        }


    }


    public static List<YoungestEldestWorkers> findYoungestEldestWorkers() throws IOException, ConnectionExeption {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/find_youngest_eldest_workers.sql"));) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            List<YoungestEldestWorkers> youngestEldestWorkers = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }

            String stringQueri = stringBuilder.toString();
            try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(stringQueri);)
            {

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String name = resultSet.getString("NAME");
                    String birthday = resultSet.getString("BIRTHDAY");
                    String type = resultSet.getString("TYPE");
                    youngestEldestWorkers.add(new YoungestEldestWorkers(name, birthday, type));

                }

            } catch (SQLException e) {
                throw new ConnectionExeption("Error by find youngest and eldest worker");
            }
            return youngestEldestWorkers;
        }


    }


    public static List<ProjectPrices> printProjectPrices() throws IOException, ConnectionExeption {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("sql/print_project_prices.sql"));) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            List<ProjectPrices> projectPrices = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + " ");
            }

            String stringQueri = stringBuilder.toString();
            try(Connection connection = Database.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(stringQueri);)
            {

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int projectid = resultSet.getObject("PROJECT_ID", Integer.class);
                    int prices = resultSet.getObject("prices", Integer.class);
                    projectPrices.add(new ProjectPrices(projectid, prices));
                }

            } catch (SQLException e) {
                throw new ConnectionExeption("Error by find project prices");
            }
            return projectPrices;
        }


    }


}
