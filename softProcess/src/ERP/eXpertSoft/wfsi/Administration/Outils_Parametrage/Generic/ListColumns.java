package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

/**
 * ListColumns.java
 * Copyright (c) 2007 by Dr. Herong Yang. All rights reserved.
 */
import java.sql.*;
public class ListColumns {
  public static void main(String [] args) {
    Connection con = null;
    try {

    	Class.forName("org.postgresql.Driver");
      con = DriverManager.getConnection(
       	   "jdbc:postgresql://localhost:5432/Process_DB","postgres", "manager");

      DatabaseMetaData meta = con.getMetaData();
      ResultSet res = meta.getColumns(null, null, "utilisateur", null);
      System.out.println("List of columns: "); 
      while (res.next()) {
         System.out.println(
           "  "+res.getString("TABLE_SCHEM")
           + ", "+res.getString("TABLE_NAME")
           + ", "+res.getString("COLUMN_NAME")
           + ", "+res.getString("TYPE_NAME")
           + ", "+res.getInt("COLUMN_SIZE")
           + ", "+res.getString("NULLABLE")); 
      }
      res.close();

      con.close();
    } catch (java.lang.ClassNotFoundException e) {
      System.err.println("ClassNotFoundException: "
        +e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQLException: "
        +e.getMessage());
    }
  }
}
