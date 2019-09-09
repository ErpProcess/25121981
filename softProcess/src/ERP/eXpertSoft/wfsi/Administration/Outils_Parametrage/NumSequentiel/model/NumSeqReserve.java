package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model;

import java.io.Serializable;
 
import java.util.Date;

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

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@JsonAutoDetect
@Entity
@Table(name = "num_sequentiel_reserve", schema = "administration")
public class NumSeqReserve implements Serializable {

	private static final long serialVersionUID = -877036845618737036L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_num_seq;

	@Column
	private String code_num;

	@Column
	private String numero;

	@Column
	private Date date_time_cre;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean fk_etab_Bean = new EtablissementBean();

	public Integer getId_num_seq() {
		return id_num_seq;
	}

	public void setId_num_seq(Integer id_num_seq) {
		this.id_num_seq = id_num_seq;
	}

	public String getCode_num() {
		return code_num;
	}

	public void setCode_num(String code_num) {
		this.code_num = code_num;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	 
	public EtablissementBean getFk_etab_Bean() {
		return fk_etab_Bean;
	}

	public void setFk_etab_Bean(EtablissementBean fk_etab_Bean) {
		this.fk_etab_Bean = fk_etab_Bean;
	}

	public Date getDate_time_cre() {
		return date_time_cre;
	}

	public void setDate_time_cre(Date date_time_cre) {
		this.date_time_cre = date_time_cre;
	}
	
	

}
