import java.sql.*;

public class JDBCTask {

    private static String conStr = "jdbc:postgresql://localhost:5432/test";
    private static String login = "user";
    private static String pwd = "123456";


    public static void main(String[] args) {
        try {
            getDepartmentInfo();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getDepartmentInfo () throws ClassNotFoundException, SQLException {
        String selectDep = "SELECT department, SUM(salary) as sumsal FROM employees GROUP BY department;";
        Class.forName("org.postgresql.Driver");

        try (Connection connection = DriverManager.getConnection(conStr,login,pwd);
             Statement statement = connection.createStatement())
        {
            ResultSet resultSet = statement.executeQuery(selectDep);
            while (resultSet.next()) {
                String department = resultSet.getString("department");
                int sumSal = resultSet.getInt("sumsal");
                System.out.println(department + ": " + sumSal);
            }
        }

    }
}