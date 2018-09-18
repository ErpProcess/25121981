package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

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

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.TVA.model.TVABean;

@JsonAutoDetect
@Entity
@Table(name = "mvt_achat_facture", schema = "achat")
public class Mvt_achat_factureBean extends GenericBean  {
 
	 
	private static final long serialVersionUID = -4436282382602739389L;


	@Id
	@Column
	private String mvt_achat_id="";
	
 
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
	private Double montant_tva_achat;

	@Column
	private Double montant_ht_achat;


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


	 


	 

	public Double getQuantite() {
		return quantite;
	}


	public void setQuantite(Double quantite) {
		this.quantite = quantite;
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


	public String getMvt_achat_id() {
		return mvt_achat_id;
	}


	public void setMvt_achat_id(String mvt_achat_id) {
		this.mvt_achat_id = mvt_achat_id;
	}


	public Double getMontant_tva_achat() {
		return montant_tva_achat;
	}


	public void setMontant_tva_achat(Double montant_tva_achat) {
		this.montant_tva_achat = montant_tva_achat;
	}


	public Double getMontant_ht_achat() {
		return montant_ht_achat;
	}


	public void setMontant_ht_achat(Double montant_ht_achat) {
		this.montant_ht_achat = montant_ht_achat;
	}
	 
	 
	 
 
	 

	 
}
