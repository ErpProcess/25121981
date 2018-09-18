package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.util.StringHelper;

public class DBUtility {
    private static final String[] TYPES = {"TABLE", "VIEW"};
    public static void getTableMetadata(Connection jdbcConnection, String tableNamePattern, String schema, String catalog, boolean isQuoted) throws HibernateException {
            try {
                DatabaseMetaData meta = jdbcConnection.getMetaData();
                ResultSet rs = null;
                try {
                    if ( (isQuoted && meta.storesMixedCaseQuotedIdentifiers())) {
                        rs = meta.getTables(catalog, schema, tableNamePattern, TYPES);
                    } else if ( (isQuoted && meta.storesUpperCaseQuotedIdentifiers())
                        || (!isQuoted && meta.storesUpperCaseIdentifiers() )) {
                        rs = meta.getTables(
                                StringHelper.toUpperCase(catalog),
                                StringHelper.toUpperCase(schema),
                                StringHelper.toUpperCase(tableNamePattern),
                                TYPES
                            );
                    }
                    else if ( (isQuoted && meta.storesLowerCaseQuotedIdentifiers())
                            || (!isQuoted && meta.storesLowerCaseIdentifiers() )) {
                        rs = meta.getTables( 
                                StringHelper.toLowerCase( catalog ),
                                StringHelper.toLowerCase(schema), 
                                StringHelper.toLowerCase(tableNamePattern), 
                                TYPES 
                            );
                    }
                    else {
                        rs = meta.getTables(catalog, schema, tableNamePattern, TYPES);
                    }

                    while ( rs.next() ) {
                        String tableName = rs.getString("TABLE_NAME");
                        System.out.println("table = " + tableName);
                    }



                }
                finally {
                    if (rs!=null) rs.close();
                }
            }
            catch (SQLException sqlException) {
                // TODO 
                sqlException.printStackTrace();
            }

    }

    public static void main(String[] args) {
        Connection jdbcConnection;
        try {
        	
        	try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
        	jdbcConnection = DriverManager.getConnection(
        	   "jdbc:postgresql://localhost:5432/Process_DB","postgres", "manager");
        	
            getTableMetadata(jdbcConnection, "utilisateur", null, null, false);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
