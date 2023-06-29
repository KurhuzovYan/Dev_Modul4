package global.goit.services;

import global.goit.connection.Database;
import global.goit.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import static global.goit.util.Reader.getStringFromSQL;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        String maxCountOfProjects = getStringFromSQL("sql/find_max_projects_client.sql");
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();

        ResultSet resultSet = getResultSet(maxCountOfProjects);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int projectCount = resultSet.getInt("project_count");
            maxProjectCountClients.add(new MaxProjectCountClient(name, projectCount));
        }
        return maxProjectCountClients;
    }

    public List<LongestProject> findLongestProject() throws SQLException {
        String longestProject = getStringFromSQL("sql/find_longest_project.sql");
        List<LongestProject> maxProjectCountClients = new ArrayList<>();

        ResultSet resultSet = getResultSet(longestProject);
        while (resultSet.next()) {
            int id = resultSet.getInt("project_id");
            int count = resultSet.getInt("month_count");
            maxProjectCountClients.add(new LongestProject(id, count));
        }
        return maxProjectCountClients;
    }

    public List<MaxSalary> findMaxSalary() throws SQLException {
        String salary = getStringFromSQL("sql/find_max_salary_worker.sql");
        List<MaxSalary> listOfSalaries = new ArrayList<>();

        ResultSet resultSet = getResultSet(salary);
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            int wages = resultSet.getInt("salary");
            listOfSalaries.add(new MaxSalary(name, wages));
        }
        return listOfSalaries;
    }

    public List<YoungestAndOldest> findYoungestAndOldest() throws SQLException {
        String youngAndOld = getStringFromSQL("sql/find_youngest_eldest_workers.sql");
        List<YoungestAndOldest> maxSalary = new ArrayList<>();

        ResultSet resultSet = getResultSet(youngAndOld);
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            String name = resultSet.getString("name");
            LocalDate date = LocalDate.parse(resultSet.getString("birthday"));
            maxSalary.add(new YoungestAndOldest(type, name, date));
        }
        return maxSalary;
    }

    public List<ProjectPrices> findProjectPrices() throws SQLException {
        String projectPrice = getStringFromSQL("sql/print_project_prices.sql");
        List<ProjectPrices> maxSalary = new ArrayList<>();

        ResultSet resultSet = getResultSet(projectPrice);
        while (resultSet.next()) {
            int id = resultSet.getInt("project_id");
            int cost = resultSet.getInt("project_cost");
            maxSalary.add(new ProjectPrices(id, cost));
        }
        return maxSalary;
    }

    private static ResultSet getResultSet(String sql) throws SQLException {
        return Database.getInstance().getConnection().createStatement().executeQuery(sql);
    }
}
