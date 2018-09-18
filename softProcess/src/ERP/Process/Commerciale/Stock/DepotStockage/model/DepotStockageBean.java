package ERP.Process.Commerciale.Stock.DepotStockage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Stock.NatureLieu.model.NatureLieuBean;
import ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Utilisateur.model.UtilisateurBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "depot_stockage", schema = "stock")
public class DepotStockageBean  extends GenericBean {
 
	private static final long serialVersionUID = 7941917126144672535L;
	@Id
	@GeneratedValue
	@Column
	private Integer				depot_id;
	@Column
	private String				depot_libelle	= "";
	@Column
	private Integer				depot_ordre;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean	fk_etab_Bean		= new EtablissementBean();
	
	
	@ManyToOne
	@JoinColumn(name = "nat_lieu_id", insertable = true, updatable = true)
	private NatureLieuBean nature = new NatureLieuBean();

	@Column
	private String				usr_cre			= "";
	@Column
	private Date				date_cre;
	@Column
	private String				usr_mod			= "";
	@Column
	private Date				date_mod;

	public void setDepot_libelle(String depot_libelle) {
		this.depot_libelle = depot_libelle;
	}

	public String getDepot_libelle() {
		return depot_libelle;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public Integer getDepot_id() {
		return depot_id;
	}

	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}

	public Integer getDepot_ordre() {
		return depot_ordre;
	}

	public void setDepot_ordre(Integer depot_ordre) {
		this.depot_ordre = depot_ordre;
	}

	 

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
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

	public NatureLieuBean getNature() {
		return nature;
	}

	public void setNature(NatureLieuBean nature) {
		this.nature = nature;
	}

	 
	 
}
