package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.web;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;


public class  CustomIdGenerator implements IdentifierGenerator {

    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {

        String prefix = getIdPrefix();
        Connection connection = session.connection();
        PreparedStatement ps =null;
        try {

              ps = connection.prepareStatement("SELECT nextval ('stock.mvt_except_id') as nextval"); 
            // could be different, depending on database vendor

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("nextval");
                String code = prefix + id;
                return  code ;
            }

        } catch (SQLException e) {
            throw new HibernateException(
                    "Unable to generate ID");
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Throwable e) {
                    // log error, or rethrow exception
                }
            }
        }
        return null;
    }

    private String getIdPrefix() {
        DateFormat df = new SimpleDateFormat("YYYYMM");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

}
