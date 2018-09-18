package ERP.Process.Commerciale.Vente.Parametrage.TypeAvoir.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericBean;
@JsonAutoDetect
@Entity
@Table(name = "type_avoir_facture", schema = "vente")
public class  TypeAvoirBean   extends  GenericBean {
  

	private static final long serialVersionUID = 4607723073659538505L;
	 @Id
	 @GeneratedValue
	 @Column	private Integer  type_avoir_id;
	 @Column	private String   type_avoir_lib =""; 
	 @Column	private Integer  type_avoir_res;
	 
	public void setType_avoir_lib (String  type_avoir_lib) {
		this.type_avoir_lib = type_avoir_lib;
	}
	public String getType_avoir_lib() {
		return type_avoir_lib;
	}
	public void setType_avoir_res (Integer  type_avoir_res) {
		this.type_avoir_res = type_avoir_res;
	}
	public Integer getType_avoir_res() {
		return type_avoir_res;
	}
	public Integer getType_avoir_id() {
		return type_avoir_id;
	}
	public void setType_avoir_id(Integer type_avoir_id) {
		this.type_avoir_id = type_avoir_id;
	}
	}
