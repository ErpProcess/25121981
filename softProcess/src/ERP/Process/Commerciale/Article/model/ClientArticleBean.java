package ERP.Process.Commerciale.Article.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.DBSchemaTable;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;

@JsonAutoDetect
@Entity
@Table(name = "article_client", schema = DBSchemaTable.SCHEMA_GESTIONCOMMERCIALE)
public class ClientArticleBean extends GenericBean {

  
	private static final long serialVersionUID = 3464431252965246609L;

	@EmbeddedId
	private PKClient pk = new PKClient();

	@Column
	private Date date_cre;
	@Column
	private Date date_mod;
	@Column
	private String usr_cre = "";
	@Column
	private String usr_mod = "";
	
	
 
	
	@Transient
	private String				indx_row			= "";
	
	@Transient
	private String				indx_row_next		= "";
	
	@Transient
	private String				to_check			= ""; //checked
	
	

	public Date getDate_cre() {
		return date_cre;
	}

	public void setDate_cre(final Date date_cre) {
		this.date_cre = date_cre;
	}

	public Date getDate_mod() {
		return date_mod;
	}

	public void setDate_mod(final Date date_mod) {
		this.date_mod = date_mod;
	}

	public void setUsr_cre(final String usr_cre) {
		this.usr_cre = usr_cre;
	}

	public String getUsr_cre() {
		return usr_cre;
	}

	public void setUsr_mod(final String usr_mod) {
		this.usr_mod = usr_mod;
	}

	public String getUsr_mod() {
		return usr_mod;
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

	public PKClient getPk() {
		return pk;
	}

	public void setPk(PKClient pk) {
		this.pk = pk;
	}

}
