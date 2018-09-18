package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.hibernate;

public class InfrastructureException  extends RuntimeException {
 
 
	private static final long serialVersionUID = 79333589026340797L;

	public InfrastructureException() {
	}

	public InfrastructureException(String message) {
		super(message);
	}

	public InfrastructureException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfrastructureException(Throwable cause) {
		super(cause);
	}
}
