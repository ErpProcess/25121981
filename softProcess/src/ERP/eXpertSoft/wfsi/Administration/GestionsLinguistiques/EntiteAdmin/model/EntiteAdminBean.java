package ERP.eXpertSoft.wfsi.Administration.GestionsLinguistiques.EntiteAdmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "entite_admin", schema = "administration")
public class EntiteAdminBean    implements Serializable, Cloneable {

	public EntiteAdminBean() {
		super();

	}

	
@EmbeddedId private IdEntiteAdminBean pk_entite_admin = new IdEntiteAdminBean();
	
	@Column private String ent_libelle = "";
	@Column private String ent_abrv = "";
	@Column private String type_lib_id = "";
	@Column private String sousmod_id = "";
	@Column private String mod_id = "";
	@Column private String pack_id = "";
	@Column private String spack_id = "";
	@Column private String table_schem = "";
	@Column private String table_name = "";
	@Column private String column_name = "";
	@Column private String type_name = "";
	@Column private String column_size = "";
	@Transient  private String indx_row = "";
	@Transient  private String indx_row_next = "";
	@Transient  private String to_check = "";
    @Transient  private String chaineColumn = "";
    @Transient  private String id_entite = "";

	public String getSousmod_id() {
		return sousmod_id;
	}

	public void setSousmod_id(String sousmod_id) {
		this.sousmod_id = sousmod_id;
	}

	public String getType_lib_id() {
		return type_lib_id;
	}

	public void setType_lib_id(String type_lib_id) {
		this.type_lib_id = type_lib_id;
	}

	public IdEntiteAdminBean getPk_entite_admin() {
		return pk_entite_admin;
	}

	public void setPk_entite_admin(IdEntiteAdminBean pk_entite_admin) {
		this.pk_entite_admin = pk_entite_admin;
	}

	public String getEnt_libelle() {
		return ent_libelle;
	}

	public void setEnt_libelle(String ent_libelle) {
		this.ent_libelle = ent_libelle;
	}

	public String getEnt_abrv() {
		return ent_abrv;
	}

	public void setEnt_abrv(String ent_abrv) {
		this.ent_abrv = ent_abrv;
	}

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

	public String getChaineColumn() {
		return chaineColumn;
	}

	public void setChaineColumn(String chaineColumn) {
		this.chaineColumn = chaineColumn;
	}

	public String getTable_schem() {
		return table_schem;
	}

	public void setTable_schem(String table_schem) {
		this.table_schem = table_schem;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getColumn_size() {
		return column_size;
	}

	public void setColumn_size(String column_size) {
		this.column_size = column_size;
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

 

	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	 

}
