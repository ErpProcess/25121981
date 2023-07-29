package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;



@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "num_seq", schema = "administration")
public class Num_seqBean implements Serializable {
	
	@Id
	@Column(name = "code_num", unique = true, nullable = false)
	private String code_num = "";
	
	@Column(name = "table_seq")
	private String table_seq = "";
 
	@Column(name = "label_num")
	private String label_num = "";
	
	@Column(name = "type_num")
	private String type_num = "";
	
	@Column(name = "nbr_chiffre")
	private Integer nbr_chiffre ;
	
	@Column(name = "prefix")
	private String prefix = "";
	
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getTable_seq() {
		return table_seq;
	}

	public void setTable_seq(String table_seq) {
		this.table_seq = table_seq;
	}

	 

	public String getType_num() {
		return type_num;
	}

	public void setType_num(String type_num) {
		this.type_num = type_num;
	}

	public String getCode_num() {
		return code_num;
	}

	public void setCode_num(String code_num) {
		this.code_num = code_num;
	}

	public String getLabel_num() {
		return label_num;
	}

	public void setLabel_num(String label_num) {
		this.label_num = label_num;
	}

	public Integer getNbr_chiffre() {
		return nbr_chiffre;
	}

	public void setNbr_chiffre(Integer nbr_chiffre) {
		this.nbr_chiffre = nbr_chiffre;
	}

	 

	 

	 
	 

	 

}
