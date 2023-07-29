package ERP.Process.Commerciale.Vente.Facture_client.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_vente_article", schema = "vente")
public class MvtVente_articleBean extends GenericBean  {
 
	private static final long serialVersionUID = 6924183728963247938L;

	@Id
	@Column
	private String mvt_vente_id="";
	
 
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"),
			@JoinColumn(name = "ar_id", insertable = true, updatable = true, referencedColumnName = "ar_id"),
			@JoinColumn(name = "code_barre", insertable = true, updatable = true, referencedColumnName = "code_barre"), }) 
	private Code_barreBean fkcode_barre = new Code_barreBean();
	
	@Column
	private Double quantite;
	
	@ManyToOne
	@JoinColumn(name = "tva_id", insertable = true, updatable = true, referencedColumnName = "tva_id")
	private TVABean tvaBean = new TVABean();
	
	
	
	@Column
	private Double montant_tva_vente;

	@Column
	private Double montant_ht_vente;
	
	@Transient
	private Double montant_ht_vente_reel;
	
	@Transient
	private Double nbrBox;
	
	
	public Double getNbrBox() {
		return nbrBox;
	}


	public void setNbrBox(Double nbrBox) {
		this.nbrBox = nbrBox;
	}


	@Transient  
	private Double montant_Remise_Ligne;
	
	@Transient
	private Double taux_remise_ligne;



	/*@ManyToOne
	@JoinColumn(name = "fact_clt_id", insertable = true, updatable = true)
	private Facture_clientBean fact= new Facture_clientBean();
	
	
	@ManyToOne
	@JoinColumn(name = "type_fact_id", insertable = true, updatable = true)
	private TypeFactureBean typef= new TypeFactureBean();*/
	
	
	
	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etablissement", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "societe", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();
	
	
	@Transient
	private List list_detail_mvt_vente= new ArrayList();
	 

	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}


	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}


	public String getMvt_vente_id() {
		return mvt_vente_id;
	}


	public void setMvt_vente_id(String mvt_vente_id) {
		this.mvt_vente_id = mvt_vente_id;
	}


	 

	public Double getQuantite() {
		return quantite;
	}


	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}


	public Double getMontant_tva_vente() {
		return montant_tva_vente;
	}


	public void setMontant_tva_vente(Double montant_tva_vente) {
		this.montant_tva_vente = montant_tva_vente;
	}


	public Double getMontant_ht_vente() {
		return montant_ht_vente;
	}


	public void setMontant_ht_vente(Double montant_ht_vente) {
		this.montant_ht_vente = montant_ht_vente;
	}


	public List getList_detail_mvt_vente() {
		return list_detail_mvt_vente;
	}


	public void setList_detail_mvt_vente(List list_detail_mvt_vente) {
		this.list_detail_mvt_vente = list_detail_mvt_vente;
	}


	public TVABean getTvaBean() {
		return tvaBean;
	}


	public void setTvaBean(TVABean tvaBean) {
		this.tvaBean = tvaBean;
	}


	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}


	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}


	public Double getMontant_ht_vente_reel() {
		return montant_ht_vente_reel;
	}


	public void setMontant_ht_vente_reel(Double montant_ht_vente_reel) {
		this.montant_ht_vente_reel = montant_ht_vente_reel;
	}


	public Double getMontant_Remise_Ligne() {
		return montant_Remise_Ligne;
	}


	public void setMontant_Remise_Ligne(Double montant_Remise_Ligne) {
		this.montant_Remise_Ligne = montant_Remise_Ligne;
	}


	public Double getTaux_remise_ligne() {
		return taux_remise_ligne;
	}


	public void setTaux_remise_ligne(Double taux_remise_ligne) {
		this.taux_remise_ligne = taux_remise_ligne;
	}
	 
	 
	 
 
	 

	 
}
