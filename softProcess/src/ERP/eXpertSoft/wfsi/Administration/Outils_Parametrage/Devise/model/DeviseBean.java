package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Devise.model;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonAutoDetect;
@JsonAutoDetect
@Entity
@Table(name = "devise", schema = "administration")
public class  DeviseBean  {
	 @Id
	 @GeneratedValue
	 @Column	private Integer dev_id; 
	 @Column	private String  dev_abrv =""; 
	 @Column	private String  dev_libelle =""; 
	 
	 
	 @Column	private String  symbole_monetaire	 ;
	 @Column	private String  chiffre_pattern ;
	 
	 @Column	private String  usr_cre =""; 
	 @Column	private java.sql.Date  date_cre;
	 @Column	private String  usr_mod =""; 
	 @Column	private java.sql.Date  date_mod;
	 @Column	private String  soc_id =""; 
	 
 
	public void setDev_abrv (String  dev_abrv) {
		this.dev_abrv = dev_abrv;
	}
	public String getDev_abrv() {
		return dev_abrv;
	}
	public void setDev_libelle (String  dev_libelle) {
		this.dev_libelle = dev_libelle;
	}
	public String getDev_libelle() {
		return dev_libelle;
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
	public void setSoc_id (String  soc_id) {
		this.soc_id = soc_id;
	}
	public String getSoc_id() {
		return soc_id;
	}
	public Integer getDev_id() {
		return dev_id;
	}
	public void setDev_id(Integer dev_id) {
		this.dev_id = dev_id;
	}
	public String getSymbole_monetaire() {
		return symbole_monetaire;
	}
	public void setSymbole_monetaire(String symbole_monetaire) {
		this.symbole_monetaire = symbole_monetaire;
	}
	public String getChiffre_pattern() {
		return chiffre_pattern;
	}
	public void setChiffre_pattern(String chiffre_pattern) {
		this.chiffre_pattern = chiffre_pattern;
	}
	
	
	
	}
