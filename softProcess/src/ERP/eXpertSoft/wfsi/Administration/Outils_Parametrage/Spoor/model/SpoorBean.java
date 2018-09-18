package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.Fonction.model.FonctionBean;
import ERP.eXpertSoft.wfsi.Administration.GestionDesMenus.SousModule.model.SousModuleBean;

@JsonAutoDetect
@Entity
@Table(name = "spoor", schema = "administration")
public class SpoorBean implements Serializable {

	@EmbeddedId
	private SpoorPKBean spoorPKBean = new SpoorPKBean();

	@Column(name = "fct_id")
	private String fct_id = "";

	@Column(name = "sous_mod")
	private String sous_mod = "";

	@Column(name = "observation")
	private String observation = "";

	@Column(name = "op_id")
	private String op_id = "";

	@Column(name = "colonne")
	private String colonne = "";
	
	@Column  private String entite = "";

	@Column private String detail_op = "";

	@Column private String mod_id = "";
	@Column private String pack_id = "";
	@Column private String spack_id = "";
	
	@Transient private  String date1="";
	@Transient private  String date2="";
	
	@ManyToOne
	@JoinColumn(name="sous_mod",   insertable=false, updatable=false )
	private  SousModuleBean bds= new  SousModuleBean();
	
	

	@ManyToOne
	@JoinColumn(name="fct_id",   insertable=false, updatable=false )
	private  FonctionBean bFonction= new  FonctionBean();
	
	
	
	
	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public String getPack_id() {
		return pack_id;
	}

	public void setPack_id(String pack_id) {
		this.pack_id = pack_id;
	}

	public String getSpack_id() {
		return spack_id;
	}

	public void setSpack_id(String spack_id) {
		this.spack_id = spack_id;
	}

	public String getDetail_op() {
		return detail_op;
	}

	public void setDetail_op(String detail_op) {
		this.detail_op = detail_op;
	}

	public String getFct_id() {
		return fct_id;
	}

	public void setFct_id(String fct_id) {
		this.fct_id = fct_id;
	}

	public String getSous_mod() {
		return sous_mod;
	}

	public void setSous_mod(String sous_mod) {
		this.sous_mod = sous_mod;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getOp_id() {
		return op_id;
	}

	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}

	public String getColonne() {
		return colonne;
	}

	public void setColonne(String colonne) {
		this.colonne = colonne;
	}

	public SpoorPKBean getSpoorPKBean() {
		return spoorPKBean;
	}

	public void setSpoorPKBean(SpoorPKBean spoorPKBean) {
		this.spoorPKBean = spoorPKBean;
	}
	
	public String getEntite() {
		return entite;
	}

	public void setEntite(String entite) {
		this.entite = entite;
	}

	public SousModuleBean getBds() {
		return bds;
	}

	public void setBds(SousModuleBean bds) {
		this.bds = bds;
	}

	public FonctionBean getBFonction() {
		return bFonction;
	}

	public void setBFonction(FonctionBean fonction) {
		bFonction = fonction;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
	}

}
