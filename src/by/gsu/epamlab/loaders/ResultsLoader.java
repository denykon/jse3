package by.gsu.epamlab.loaders;

import by.gsu.epamlab.connector.DBConnector;
import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.results.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class ResultsLoader{

    private static final String INSERT_LOGINS_NAME = "INSERT INTO logins (name) VALUES (?)";
    private static final String SELECT_LOGINS_NAME = "SELECT * from logins WHERE name LIKE (?)";
    private static final String INSERT_TESTS_NAME = "INSERT INTO tests (name) VALUES (?)";
    private static final String SELECT_TESTS_NAME = "SELECT * from tests WHERE name LIKE (?)";
    private static final String INSERT_RESULT = "INSERT INTO results (loginId, testId, dat, mark) VALUES (?,?,?,?)";

    private static final int LOGIN_INDEX = 1;
    private static final int TEST_INDEX = 2;
    private static final int DATE_INDEX = 3;
    private static final int MARK_INDEX = 4;
    private static final int COLUMN_INDEX = 1;

    private IResultDAO reader;
    private Connection con;
    private ResultSet resultSet;

    private PreparedStatement psInsertLogin;
    private PreparedStatement psSelectLogin;
    private PreparedStatement psInsertTest;
    private PreparedStatement psSelectTest;
    private PreparedStatement psInsertResult;

    public ResultsLoader(IResultDAO reader) {
        this.reader = reader;
        this.con = DBConnector.CONNECTOR.getConnection();
    }

    public void loadResults() throws SQLException {
        try {
            psInsertLogin = con.prepareStatement(INSERT_LOGINS_NAME);
            psSelectLogin = con.prepareStatement(SELECT_LOGINS_NAME);
            psInsertTest = con.prepareStatement(INSERT_TESTS_NAME);
            psSelectTest = con.prepareStatement(SELECT_TESTS_NAME);
            psInsertResult = con.prepareStatement(INSERT_RESULT);

            while (reader.hasResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();
                int idLogin = getId(login, psSelectLogin, psInsertLogin);
                int idTest = getId(test, psSelectTest, psInsertTest);
                psInsertResult.setInt(LOGIN_INDEX, idLogin);
                psInsertResult.setInt(TEST_INDEX, idTest);
                psInsertResult.setDate(DATE_INDEX, result.getDate());
                psInsertResult.setInt(MARK_INDEX, result.getMark());
                psInsertResult.executeUpdate();
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (psSelectTest != null) {
                psSelectTest.close();
            }
            if (psInsertTest != null) {
                psInsertTest.close();
            }
            if (psSelectLogin != null) {
                psSelectLogin.close();
            }
            if (psInsertLogin != null) {
                psInsertLogin.close();
            }
            if (psInsertResult != null) {
                psInsertResult.close();
            }
            if (reader != null) {
                reader.closeReader();
            }
        }
    }

    private int getId(String name, PreparedStatement psSelect, PreparedStatement psInsert) throws SQLException {
        psSelect.setString(LOGIN_INDEX, name);
        resultSet = psSelect.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(COLUMN_INDEX);
        } else {
            psInsert.setString(LOGIN_INDEX, name);
            psInsert.executeUpdate();
            resultSet = psInsert.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(COLUMN_INDEX);
            }
        }
        return 0;
    }
}
