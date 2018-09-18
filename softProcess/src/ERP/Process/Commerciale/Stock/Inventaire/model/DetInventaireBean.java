package ERP.Process.Commerciale.Stock.Inventaire.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "det_inventaire", schema = "stock")
public class DetInventaireBean  extends  GenericBean{
 
	private static final long serialVersionUID = -5107826839779338189L;


	@EmbeddedId
	private PkDetInventaire pk = new PkDetInventaire();
	 
	 
	
	
	
 
	
	
 
	 
	


	@Column
	private String usr_cre = "";
	@Column
	Date date_cre;
	@Column
	private String usr_mod = "";
	@Column
	private Date date_mod;

	@Transient
	private String indx_row = "";
	@Transient
	private String indx_row_next = "";
	@Transient
	private String to_check = "";
	
	 
	
	

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

	public PkDetInventaire getPk() {
		return pk;
	}

	public void setPk(PkDetInventaire pk) {
		this.pk = pk;
	}

	 
	 
	
	 

	public String getTo_check() {
		return to_check;
	}

	public void setTo_check(String to_check) {
		this.to_check = to_check;
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

	 

	 

	 

	 

}
