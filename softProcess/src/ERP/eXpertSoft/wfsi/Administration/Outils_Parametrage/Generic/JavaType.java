package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

public class JavaType {

	public Class getJavaType(String schema, String object, String column)
			throws Exception {
		String fullName = schema + '.' + object + '.' + column;
		//DatabaseMetaData metaData = getConnection().getMetaData();
		//ResultSet columnMeta = metaData.getColumns(null, schema, object, column);
		Class javaType = null;

	/*	if (columnMeta.first()) {
			int dataType = columnMeta.getInt("DATA_TYPE");
			javaType = SQLTypeMap.toClass(dataType);
		} else {
			throw new Exception("Unknown database column " + fullName + '.');
		}*/

		return javaType;
	}

}
