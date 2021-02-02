package com.poc.testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSqlRepositoryTests {

  @ClassRule
  public static PostgreSQLContainer postgreSQL = new PostgreSQLContainer(PostgreSQLContainer.IMAGE);

  @BeforeAll
  public static void startup() {
    postgreSQL.start();
  }

  @Test
  public void someTestMethod() throws SQLException {
    System.out.println( postgreSQL.getJdbcUrl());
    System.out.println( postgreSQL.getUsername());
    System.out.println( postgreSQL.getPassword());

    Connection con = DriverManager.getConnection(
        postgreSQL.getJdbcUrl(),postgreSQL.getUsername(),postgreSQL.getPassword());

    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("select 'HELLO WORLD from PostgreSQL'");
    rs.next();

    System.out.println(rs.getString(1) );

    con.close();

  }

  @AfterAll
  public static void tearDown() {
    postgreSQL.stop();
  }

}
