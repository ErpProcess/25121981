package ERP.Process.Commerciale.Vente.ComplementVente.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import ERP.Process.Commerciale.Entite_etat_commerciale.model.Entite_etat_commercialeBean;
import ERP.Process.Commerciale.Parametrage.Nature_incident_mvt_retour.model.Nature_incident_mvt_retourBean;
import ERP.Process.Commerciale.Vente.RetourVente.model.PkDetRetourVente;
 
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;



@JsonAutoDetect
@Entity
@Table(name = "det_complement_vente", schema = "vente")
public class DetComplementBean extends GenericBean {
 
	private static final long serialVersionUID = 6246941613315414342L;

	@EmbeddedId
	private PkDetComplement pk = new PkDetComplement();

	@Transient
	private String indx_row = "";

	@Transient
	private String indx_row_next = "";

	@Transient
	private String to_check = "";

	@Column
	private Double quantite_ajoute;

	@Column
	private Double montant_tva_complement;

	@Column
	private Double montant_ht_complement;

	@Transient
	private Double quantite_en_stock;

	@Column
	private String observation = "";

	@Column
	private Date date_cre;
	@Column
	private String usr_cre = "";
	@Column
	private Date date_mod;
	@Column
	private String usr_mod = "";

	 
	 

	public PkDetComplement getPk() {
		return pk;
	}

	public void setPk(PkDetComplement pk) {
		this.pk = pk;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(Date date_mod) {
		this.date_mod = date_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getIndx_row() {
		return indx_row;
	}

	public void setIndx_row(String indx_row) {
		this.indx_row = indx_row;
	}

	public String getIndx_row_next() {
		return indx_row_next;
	}

	public void setIndx_row_next(String indx_row_next) {
		this.indx_row_next = indx_row_next;
	}

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
	}

	public Double getQuantite_en_stock() {
		return quantite_en_stock;
	}

	public void setQuantite_en_stock(Double quantite_en_stock) {
		this.quantite_en_stock = quantite_en_stock;
	}

	public Double getQuantite_ajoute() {
		return quantite_ajoute;
	}

	public void setQuantite_ajoute(Double quantite_ajoute) {
		this.quantite_ajoute = quantite_ajoute;
	}

	public Double getMontant_tva_complement() {
		return montant_tva_complement;
	}

	public void setMontant_tva_complement(Double montant_tva_complement) {
		this.montant_tva_complement = montant_tva_complement;
	}

	public Double getMontant_ht_complement() {
		return montant_ht_complement;
	}

	public void setMontant_ht_complement(Double montant_ht_complement) {
		this.montant_ht_complement = montant_ht_complement;
	}

	 

	 

	 

}
