package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

 
public class HibernateUtil {

	private static Log log = LogFactory.getLog(HibernateUtil.class);

	private static Configuration configuration= new Configuration();;

	private static final ThreadLocal<Session> threadSession = new ThreadLocal<Session>();
	private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();
	private static final ThreadLocal<Interceptor> threadInterceptor = new ThreadLocal<Interceptor>();
	private static org.hibernate.SessionFactory sessionFactory;
   


    static {
		try {
			configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory();
			// We could also let Hibernate bind it to JNDI:
			// configuration.configure().buildSessionFactory()
		} catch (Throwable ex) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

    
    
	 
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static Session getSessionHibernate() throws InfrastructureException {
		Session s = (Session) threadSession.get();
		try {
			if (s == null) {
				s = sessionFactory.openSession();
				threadSession.set(s);
				beginTransaction();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return s;
	}

	public static void beginTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx == null) {
				//log.debug("Starting new database transaction in this thread.");
				tx = getSessionHibernate().beginTransaction();
				threadTransaction.set(tx);
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	public static void commitTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				//log.debug("Committing database transaction of this thread.");
				tx.commit();
			}
			threadTransaction.set(null);
		} catch (HibernateException ex) {
			rollbackTransaction();
			throw new InfrastructureException(ex);
		}
	}

	public static void rollbackTransaction() throws InfrastructureException {
		Transaction tx = (Transaction) threadTransaction.get();
		try {
			threadTransaction.set(null);
			if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
				//log.debug("Tyring to rollback database transaction of this thread.");
				tx.rollback();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		} finally {
			closeSession();
		}
	}

	public static void closeSession() throws InfrastructureException {
		try {
			Session s = (Session) threadSession.get();
			threadSession.set(null);
			if (s != null && s.isOpen()) {
				//log.debug("Closing Session of this thread.");

				s.flush();
				s.close();

			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	public static void registerInterceptor(Interceptor interceptor) {
		threadInterceptor.set(interceptor);
	}

	private static Interceptor getInterceptor() {
		Interceptor interceptor = (Interceptor) threadInterceptor.get();
		return interceptor;
	}

}
