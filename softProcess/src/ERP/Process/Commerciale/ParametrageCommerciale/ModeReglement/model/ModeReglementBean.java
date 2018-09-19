package ERP.Process.Commerciale.ParametrageCommerciale.ModeReglement.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import javax.persistence.EmbeddedId;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "mode_reglement", schema = "gestioncommerciale")
public class  ModeReglementBean   extends  GenericBean {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer  mod_id  ; 
	 @Column	private String  mod_libelle =""; 
	 @Column	private Integer mod_ordre;
	 
	 @Column	private String  usr_cre =""; 
	 @Column	private java.sql.Date  date_cre;
	 @Column	private String  usr_mod =""; 
	 @Column	private java.sql.Date  date_mod;
	 
	 @ManyToOne
		@JoinColumns( {
				@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
				@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
		private EtablissementBean fk_etab_Bean = new EtablissementBean();
	 
	public Integer getMod_id() {
		return mod_id;
	}
	public void setMod_id(Integer mod_id) {
		this.mod_id = mod_id;
	}
	public void setMod_libelle (String  mod_libelle) {
		this.mod_libelle = mod_libelle;
	}
	public String getMod_libelle() {
		return mod_libelle;
	}
	public void setMod_ordre (Integer  mod_ordre) {
		this.mod_ordre = mod_ordre;
	}
	public Integer getMod_ordre() {
		return mod_ordre;
	}
	
	
 
	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}
	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}
	public void setUsr_cre (String  usr_cre) {
		this.usr_cre = usr_cre;
	}
	public String getUsr_cre() {
		return usr_cre;
	}
	public void setDate_cre (java.sql.Date  date_cre) {
		this.date_cre = date_cre;
	}
	public java.sql.Date getDate_cre() {
		return date_cre;
	}
	public void setUsr_mod (String  usr_mod) {
		this.usr_mod = usr_mod;
	}
	public String getUsr_mod() {
		return usr_mod;
	}
	public void setDate_mod (java.sql.Date  date_mod) {
		this.date_mod = date_mod;
	}
	public java.sql.Date getDate_mod() {
		return date_mod;
	}
	}
