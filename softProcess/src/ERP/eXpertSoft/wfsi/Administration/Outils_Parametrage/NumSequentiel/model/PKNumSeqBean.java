package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model;

 

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

 

@Embeddable
public class PKNumSeqBean implements Serializable {
	
	 
	private static final long serialVersionUID = 6313323150073333763L;

	public PKNumSeqBean() {
		super();
	}


	@Column(name = "code_num")
	private String code_num="" ;
	
	@Column(name = "soc_id")
	private String soc_id="" ;
	
	@Column(name = "etab_id")
	private String etab_id="" ;
	 
	@Column(name = "mois")
	private String mois = "";
	 
	@Column(name = "annee")
	private String annee = "";

	public String getCode_num() {
		return code_num;
	}

	public void setCode_num(String code_num) {
		this.code_num = code_num;
	}

	public String getSoc_id() {
		return soc_id;
	}

	public void setSoc_id(String soc_id) {
		this.soc_id = soc_id;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getEtab_id() {
		return etab_id;
	}

	public void setEtab_id(String etab_id) {
		this.etab_id = etab_id;
	}

 
	 

	
 



	
	 

}
