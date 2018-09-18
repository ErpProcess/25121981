package ERP.Process.Commerciale.Vente.Livraison.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.Parametrage.Transport.model.TransportBean;
import ERP.Process.Commerciale.Vente.ProcedureVente.model.ProcedureVenteBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "livraison_commande", schema = "vente")
public class LivraisonBean extends GenericBean {

 
	private static final long serialVersionUID = 5227197846297636860L;
	
	@Id
	@Column
	private String liv_id = "";
	@Column
	private String liv_libelle = "";
	@Column
	private Date liv_date;
	@Column
	private Integer liv_type;
	@Column
	private String liv_obs = "";
	
	@Column
	private Double montant_livraison  ;
	
	

	@ManyToOne
	@JoinColumn(name = "trans_id", insertable = true, updatable = true)
	private TransportBean trans = new TransportBean();
	
	
	@ManyToOne 
	@JoinColumn(name = "vente_id", insertable = true, updatable = true)
	private ProcedureVenteBean  vente= new ProcedureVenteBean ();
	

	@Column
	private java.sql.Time time_cre;
	@Column
	private java.sql.Time time_mod;

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	@ManyToOne
	@JoinColumn(name = "mode_op", insertable = true, updatable = true)
	private FonctionBean modeBean = new FonctionBean();

	public void setLiv_obs(String liv_obs) {
		this.liv_obs = liv_obs;
	}

	public String getLiv_obs() {
		return liv_obs;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_mod(java.sql.Time time_mod) {
		this.time_mod = time_mod;
	}

	public java.sql.Time getTime_mod() {
		return time_mod;
	}

	public String getLiv_id() {
		return liv_id;
	}

	public void setLiv_id(String liv_id) {
		this.liv_id = liv_id;
	}

	public String getLiv_libelle() {
		return liv_libelle;
	}

	public void setLiv_libelle(String liv_libelle) {
		this.liv_libelle = liv_libelle;
	}

	public Integer getLiv_type() {
		return liv_type;
	}

	public void setLiv_type(Integer liv_type) {
		this.liv_type = liv_type;
	}

	public Date getLiv_date() {
		return liv_date;
	}

	public void setLiv_date(Date liv_date) {
		this.liv_date = liv_date;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public FonctionBean getModeBean() {
		return modeBean;
	}

	public void setModeBean(FonctionBean modeBean) {
		this.modeBean = modeBean;
	}

	public TransportBean getTrans() {
		return trans;
	}

	public void setTrans(TransportBean trans) {
		this.trans = trans;
	}

	public ProcedureVenteBean getVente() {
		return vente;
	}

	public void setVente(ProcedureVenteBean vente) {
		this.vente = vente;
	}

	public Double getMontant_livraison() {
		return montant_livraison;
	}

	public void setMontant_livraison(Double montant_livraison) {
		this.montant_livraison = montant_livraison;
	}

	 
}
