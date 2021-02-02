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

public class OracleRepositoryTests {

  @ClassRule
  public static OracleContainer oracle = new OracleContainer("martinsthiago/oraclexe-11g-fig");

  @BeforeAll
  public static void startup() {
    oracle.start();
  }

  @Test
  public void someTestMethod() throws SQLException {
    System.out.println( oracle.getJdbcUrl());
    System.out.println( oracle.getUsername());
    System.out.println( oracle.getPassword());

    Connection con = DriverManager.getConnection(
        oracle.getJdbcUrl(),oracle.getUsername(),oracle.getPassword());

    Statement stmt = con.createStatement();

    ResultSet rs = stmt.executeQuery("select 'HELLO WORLD from Oracle' from dual");
    rs.next();

    System.out.println(rs.getString(1) );

    con.close();

  }

  @AfterAll
  public static void tearDown() {
    oracle.stop();
  }

}
