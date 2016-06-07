package com.example.restservicedemo;

import com.example.restservicedemo.domain.Game;
import com.example.restservicedemo.service.GameManager;
import com.jayway.restassured.RestAssured;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Vuko on 31.05.2016.
 */
public class GameServiceRESTDBTest {
    private static IDatabaseConnection connection ;
    private static IDatabaseTester databaseTester;

    private static GameManager gm = new GameManager();

    @BeforeClass
    public static void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";

        Connection jdbcConnection;
        jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        connection = new DatabaseConnection(jdbcConnection);

        databaseTester = new JdbcDatabaseTester(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream(new File("src/test/java/resources/fullData.xml")));
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @AfterClass
    public static void tearDown() throws Exception{
        databaseTester.onTearDown();
    }
    @Test
    public void addGame() throws Exception{

        Game g = new Game(0, "Ziutek", 2010);
        given().contentType(MediaType.APPLICATION_JSON).body(g)
                .when().post("/game/").then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("GAME");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new File("src/test/java/resources/gameData.xml"));
        ITable expectedTable = expectedDataSet.getTable("GAME");

        Assertion.assertEquals(expectedTable, filteredTable);
    }

}
