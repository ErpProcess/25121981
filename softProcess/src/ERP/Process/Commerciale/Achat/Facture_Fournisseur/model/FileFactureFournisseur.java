package ERP.Process.Commerciale.Achat.Facture_Fournisseur.model;

import java.math.BigDecimal;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Etablissement.model.EtablissementBean;

@JsonAutoDetect
@Entity
@Table(name = "file_facture_founisseur", schema = "achat")
public class FileFactureFournisseur {

	@Id
	@GeneratedValue
	@Column
	private BigDecimal file_id;

	@Column
	private String file_name;

	@Column
	private String fact_frs_id;
	
	@Column
	private String doc_id_interne;
	
	@Column
	private String mime_content_type;
	
	
	@Column
	private byte[] file_byte;

	@ManyToOne
	@JoinColumns( {
			@JoinColumn(name = "etab_id", insertable = true, updatable = true, referencedColumnName = "etab_id"),
			@JoinColumn(name = "soc_id", insertable = true, updatable = true, referencedColumnName = "soc_id"), })
	private EtablissementBean etablissment = new EtablissementBean();
	
	@Transient
	private MultipartFile  multipartFile;
	 
	

	 

	 
	public String getMime_content_type() {
		return mime_content_type;
	}

	public void setMime_content_type(String mime_content_type) {
		this.mime_content_type = mime_content_type;
	}

	public String getDoc_id_interne() {
		return doc_id_interne;
	}

	public void setDoc_id_interne(String doc_id_interne) {
		this.doc_id_interne = doc_id_interne;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getFact_frs_id() {
		return fact_frs_id;
	}

	public void setFact_frs_id(String fact_frs_id) {
		this.fact_frs_id = fact_frs_id;
	}

	public BigDecimal getFile_id() {
		return file_id;
	}

	public void setFile_id(BigDecimal file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public byte[] getFile_byte() {
		return file_byte;
	}

	public void setFile_byte(byte[] file_byte) {
		this.file_byte = file_byte;
	}

	public EtablissementBean getEtablissment() {
		return etablissment;
	}

	public void setEtablissment(EtablissementBean etablissment) {
		this.etablissment = etablissment;
	}
	
	

}
