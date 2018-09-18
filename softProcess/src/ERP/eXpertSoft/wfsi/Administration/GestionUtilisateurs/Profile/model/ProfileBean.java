package ERP.eXpertSoft.wfsi.Administration.GestionUtilisateurs.Profile.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;


@JsonAutoDetect
@Entity
@Table(name = "profile", schema = "administration")
public class ProfileBean   implements Serializable   {
	
	 

	@Id
	@Column
	private String prf_id = "";
	@Column
	private String prf_libelle = "";
	@Column
	private String usr_cre = "";
	@Column
	private Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private Date date_mod;
	@Column
	private Time time_cre;
	@Column
	private Time time_mod;
	 


	public void setPrf_id(String prf_id) {
		this.prf_id = prf_id;
	}

	public String getPrf_id() {
		return prf_id;
	}

	public void setPrf_libelle(String prf_libelle) {
		this.prf_libelle = prf_libelle;
	}

	public String getPrf_libelle() {
		return prf_libelle;
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

	public Time getTime_cre() {
		return time_cre;
	}

	public void setTime_cre(Time time_cre) {
		this.time_cre = time_cre;
	}

	public Time getTime_mod() {
		return time_mod;
	}

	public void setTime_mod(Time time_mod) {
		this.time_mod = time_mod;
	}

	public ProfileBean() {
		super();
	 
	}
 
}
