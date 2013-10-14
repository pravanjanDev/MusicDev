package util;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public class PersistenceManagerUtil {

	static private PersistenceManagerFactory pmf = JDOHelper
			.getPersistenceManagerFactory("transactions-optional");;

	public static PersistenceManager getPersistanceManager() {
		PersistenceManager persistenceManager = pmf.getPersistenceManager();
		return persistenceManager;
	}

}