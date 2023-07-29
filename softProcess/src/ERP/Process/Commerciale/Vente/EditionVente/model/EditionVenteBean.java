package ERP.Process.Commerciale.Vente.EditionVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Vente.Client.model.ClientBean;
import ERP.Process.Commerciale.Vente.Facture_client.model.Facture_clientBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "edition_vente", schema = "vente")
public class EditionVenteBean extends GenericBean {

	private static final long serialVersionUID = 7680730925659117969L;

	@Id
	@Column
	private String edition_id = "";

	@Column
	private String edition_libelle = "";

	@Column
	private Date date_debut;

	@Column
	private Date date_fin;
	
	
	@Transient
	private String natureEdition;
	

	@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = false)
	private Facture_clientBean factclient = new Facture_clientBean();

	@ManyToOne
	@JoinColumn(name = "clt_id", insertable = true, updatable = true)
	private ClientBean client = new ClientBean();

	@Column
	private java.sql.Time time_cre;

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public Facture_clientBean getFactclient() {
		return factclient;
	}

	public void setFactclient(Facture_clientBean factclient) {
		this.factclient = factclient;
	}

	public void setEdition_id(String edition_id) {
		this.edition_id = edition_id;
	}

	public String getEdition_id() {
		return edition_id;
	}

	public void setEdition_libelle(String edition_libelle) {
		this.edition_libelle = edition_libelle;
	}

	public String getEdition_libelle() {
		return edition_libelle;
	}

	public String getNatureEdition() {
		return natureEdition;
	}

	public void setNatureEdition(String natureEdition) {
		this.natureEdition = natureEdition;
	}

	public ClientBean getClient() {
		return client;
	}

	public void setClient(ClientBean client) {
		this.client = client;
	}

	public java.sql.Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(java.sql.Time time_cre) {
		this.time_cre = time_cre;
	}

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(Date date_cre) {
		this.date_cre = date_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}
	
}
