package ERP.Process.Commerciale.Degre_definition.model;


import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;


 
@SuppressWarnings("serial")
@JsonAutoDetect
@Entity
@Table(name = "degre_definition", schema = "gestioncommerciale")
@AssociationOverrides({
		@AssociationOverride(name = "pkBean.art_Bean", joinColumns = {
				 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
			     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
			     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id")}),
		@AssociationOverride(name = "pkBean.carac_Bean",joinColumns = @JoinColumn(name = "carac_id")) })
public class Degre_definitionBean    implements java.io.Serializable {

	
		@Column private Integer ordre;
		@Column private Date date_cre;
		@Column private Date date_mod;
		@Column private String usr_cre = "";
		@Column private String usr_mod = "";
		
	    private String is_checked = "";
		
		
	   private PkDegreBean pkBean = new PkDegreBean();
    
    
	     @EmbeddedId  
		public PkDegreBean getPkBean() {
			return pkBean;
		}

		public void setPkBean(PkDegreBean pkBean) {
			this.pkBean = pkBean;
		}
		
		
	   
    
	    @Transient
	   public ArticleBean getArt_Bean() {
			return getPkBean().getArt_Bean();
		}

		public void setArt_Bean(ArticleBean art_Bean) {
			getPkBean().setArt_Bean(art_Bean) ;
		}

		 @Transient
		public CaracteristiqueBean getCarac_Bean() {
			return getPkBean().getCarac_Bean();
		}

		public void setCarac_Bean(CaracteristiqueBean carac_Bean) {
			getPkBean().setCarac_Bean(carac_Bean) ;
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

		public String getUsr_cre() {
			return usr_cre;
		}

		public void setUsr_cre(String usr_cre) {
			this.usr_cre = usr_cre;
		}

		public String getUsr_mod() {
			return usr_mod;
		}

		public void setUsr_mod(String usr_mod) {
			this.usr_mod = usr_mod;
		}

		public Integer getOrdre() {
			return ordre;
		}

		public void setOrdre(Integer ordre) {
			this.ordre = ordre;
		}

		

		 

		 
		@Transient
		public String getIs_checked() {
			return is_checked;
		}

		public void setIs_checked(String is_checked) {
			this.is_checked = is_checked;
		} 
 
	
	 
 
}
