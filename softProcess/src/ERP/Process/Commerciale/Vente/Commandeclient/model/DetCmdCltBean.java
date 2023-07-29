package ERP.Process.Commerciale.Vente.Commandeclient.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.Process.Commerciale.Tarification.model.TarificationBean;

@JsonAutoDetect
@Entity
@Table(name = "det_cmd_client", schema = "vente")
public class DetCmdCltBean implements Serializable, Cloneable {

	private static final long serialVersionUID = -7762742561844074178L;

	@EmbeddedId
	private PKDetCmdClt pk = new PKDetCmdClt();

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	@Transient
	private String to_check = "";
	@Transient
	private String id_entite = "";
	@Column
	private Double quantite;
	@Column
	private String observation = "";
	@Column
	private String usr_cre = "";
	@Column
	private String usr_mod = "";
	@Column
	private Date date_cre;
	@Column
	private Date date_mod;

	@Transient
	private Double quantite_calcul;
	
	@Transient
	private Double montant_ttc_cmd;
	
	
	 
	
	@Column
	private Double taux_remise_ligne;
	
	@Column
	private Double montant_remise_ligne;
	
	
	@Transient
	private Double montant_ht_cmd_reel;
	
	

	@Transient
	private Double quantite_calcul_for_modif;
	
	
	@Transient
	private String info;
	

	@Transient
	private String total_mnt_ht = "";

	@Transient
	private String total_mnt_tva = "";

	@Transient
	private String total_mnt_gen = "";

	@Transient
	private String total_quantite = "";

	
	@ManyToOne
	@JoinColumn(name = "tarif_vente_id", insertable = true, updatable = true)
	private TarificationBean  tarif = new TarificationBean();
	  
	

	@Column
	private Double montant_tva_vente;

	@Column
	private Double montant_ht_vente;

 
 
	

	 

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getObservation() {
		return observation;
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

	public String getId_entite() {
		return id_entite;
	}

	public void setId_entite(String id_entite) {
		this.id_entite = id_entite;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_cre(String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_mod() {
		return usr_mod;
	}

	public void setUsr_mod(String usr_mod) {
		this.usr_mod = usr_mod;
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

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public PKDetCmdClt getPk() {
		return pk;
	}

	public void setPk(PKDetCmdClt pk) {
		this.pk = pk;
	}

	public Double getQuantite_calcul() {
		return quantite_calcul;
	}

	public void setQuantite_calcul(Double quantite_calcul) {
		this.quantite_calcul = quantite_calcul;
	}

	public Double getQuantite_calcul_for_modif() {
		return quantite_calcul_for_modif;
	}

	public void setQuantite_calcul_for_modif(Double quantite_calcul_for_modif) {
		this.quantite_calcul_for_modif = quantite_calcul_for_modif;
	}

	public String getTotal_mnt_ht() {
		return total_mnt_ht;
	}

	public void setTotal_mnt_ht(String total_mnt_ht) {
		this.total_mnt_ht = total_mnt_ht;
	}

	public String getTotal_mnt_tva() {
		return total_mnt_tva;
	}

	public void setTotal_mnt_tva(String total_mnt_tva) {
		this.total_mnt_tva = total_mnt_tva;
	}

	public String getTotal_mnt_gen() {
		return total_mnt_gen;
	}

	public void setTotal_mnt_gen(String total_mnt_gen) {
		this.total_mnt_gen = total_mnt_gen;
	}

	public String getTotal_quantite() {
		return total_quantite;
	}

	public void setTotal_quantite(String total_quantite) {
		this.total_quantite = total_quantite;
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

	public TarificationBean getTarif() {
		return tarif;
	}

	public void setTarif(TarificationBean tarif) {
		this.tarif = tarif;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Double getTaux_remise_ligne() {
		return taux_remise_ligne;
	}

	public void setTaux_remise_ligne(Double taux_remise_ligne) {
		this.taux_remise_ligne = taux_remise_ligne;
	}

	public Double getMontant_ht_cmd_reel() {
		return montant_ht_cmd_reel;
	}

	public void setMontant_ht_cmd_reel(Double montant_ht_cmd_reel) {
		this.montant_ht_cmd_reel = montant_ht_cmd_reel;
	}

	public Double getMontant_remise_ligne() {
		return montant_remise_ligne;
	}

	public void setMontant_remise_ligne(Double montant_remise_ligne) {
		this.montant_remise_ligne = montant_remise_ligne;
	}

	public Double getMontant_ttc_cmd() {
		return montant_ttc_cmd;
	}

	public void setMontant_ttc_cmd(Double montant_ttc_cmd) {
		this.montant_ttc_cmd = montant_ttc_cmd;
	}

}
