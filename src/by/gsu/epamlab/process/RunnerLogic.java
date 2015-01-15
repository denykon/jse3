package by.gsu.epamlab.process;

import by.gsu.epamlab.connector.DBConnector;
import by.gsu.epamlab.loaders.ResultsLoader;
import by.gsu.epamlab.readers.IResultDAO;
import by.gsu.epamlab.results.Result;
import by.gsu.epamlab.results.ResultFactory;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * jse2
 *
 * @author Dzianis Kanavalau on 10.01.2015.
 * @version 1.0
 */
public class RunnerLogic {
    private static final Connection CONNECTION = DBConnector.CONNECTOR.getConnection();
    private static final String GETTING_MEAN = "SELECT round(avg(mark/(?)),1) AS mean, name " +
            "FROM results, logins " +
            "WHERE results.loginId = logins.idLogin " +
            "GROUP BY results.loginId ORDER BY mean DESC";
    private static final String TESTS_COLLECTION = "SELECT dat, mark, logins.name, tests.name " +
            "FROM results, logins, tests\n " +
            "WHERE results.loginId = logins.idLogin\n " +
            "AND results.testId = tests.idTest\n " +
            "AND MONTH(results.dat) = MONTH(current_date()) " +
            "ORDER BY results.dat";

    private static final int LOGIN_POSITION = 3;
    private static final int TEST_POSITION = 4;
    private static final int DATE_POSITION = 1;
    private static final int MARK_POSITION = 2;
    private static final int NAME_INDEX = 2;
    private static final int MEAN_INDEX = 1;


    public static void go(ResultFactory resultFactory) throws SQLException, IOException, SAXException {

        IResultDAO iResultDAO = resultFactory.getResultDaoFromFactory();
        ResultsLoader resultsLoader = new ResultsLoader(iResultDAO);

        Thread readInBuffer = new Thread(iResultDAO, "Reader");
        readInBuffer.start();

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resultsLoader.loadResults();

        List<Result> resultList = new ArrayList<>();
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet meanRS = null;
        ResultSet collectionRS = null;

        try {
            //getting the mean value of marks on every student in descending order by the mean value
            preparedStatement = CONNECTION.prepareStatement(GETTING_MEAN);
            preparedStatement.setInt(1, resultFactory.getDivisor());
            meanRS = preparedStatement.executeQuery();

            //printing the mean values of marks
            while (meanRS.next()) {
                System.out.println(meanRS.getString(NAME_INDEX) + " - " + meanRS.getString(MEAN_INDEX));
            }

            //creating the collection of tests results for the current month sorting by the date ascending.
            statement = CONNECTION.createStatement();
            collectionRS = statement.executeQuery(TESTS_COLLECTION);
            while (collectionRS.next()) {
                String login = collectionRS.getString(LOGIN_POSITION);
                String test = collectionRS.getString(TEST_POSITION);
                Date date = collectionRS.getDate(DATE_POSITION);
                int mark = collectionRS.getInt(MARK_POSITION);
                Result currentResult = resultFactory.getResultFromFactory(login, test, date, mark);
                resultList.add(currentResult);
            }
            System.out.println();

            //printing the collection of tests results
            if (resultList.size() != 0) {
                for (Result aResultList : resultList) {
                    System.out.println(aResultList);
                }

                System.out.println();

                //printing tests results in the latest day of the current month when tests have been passed
                Date lastTestsDay = resultList.get(resultList.size() - 1).getDate();
                for (Result aResultList : resultList) {
                    if (aResultList.getDate().equals(lastTestsDay)) {
                        System.out.println(aResultList);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (collectionRS != null) {
                try {
                    collectionRS.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem : " + e);
                }
            }
            if (meanRS != null) {
                try {
                    meanRS.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem : " + e);
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem : " + e);
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem : " + e);
                }
            }
            if (CONNECTION != null) {
                try {
                    CONNECTION.close();
                } catch (SQLException e) {
                    System.err.println("Resource closing problem : " + e);
                }
            }
        }
    }
}