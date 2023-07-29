package ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Smfonction.model;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "smfction",schema="administration")
public class SmfonctionModel  implements Serializable,Cloneable{
	
	@EmbeddedId 
	private PKSmfonctionModel pk = new PKSmfonctionModel();
	@Column     private String view_smfct_action = "";
	@Column     private Integer ordre ;
	@Transient  private String indx_row = "";
	@Transient  private String indx_row_next = "";
	@Transient  private String to_check = "";
    @Transient  private String id_entite = "";
    
     
    

	 

	 
	
	public String getView_smfct_action() {
		return view_smfct_action;
	}

	public void setView_smfct_action(String view_smfct_action) {
		this.view_smfct_action = view_smfct_action;
	}

	public PKSmfonctionModel getPk() {
		return pk;
	}

	public void setPk(PKSmfonctionModel pk) {
		this.pk = pk;
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

	public Integer getOrdre() {
		return ordre;
	}

	public void setOrdre(Integer ordre) {
		this.ordre = ordre;
	}
}
