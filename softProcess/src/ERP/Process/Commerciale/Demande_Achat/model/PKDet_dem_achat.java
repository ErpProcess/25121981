package ERP.Process.Commerciale.Demande_Achat.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;


@SuppressWarnings("serial")
@Embeddable
public class  PKDet_dem_achat implements Serializable,Cloneable  {
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	     @JoinColumn(name = "code_barre", insertable = true, updatable = true,referencedColumnName="code_barre"),
	})
	private Code_barreBean  fkCode_barre = new Code_barreBean();

	
	
	@ManyToOne 
	@JoinColumn(name = "dem_achat_id", insertable = true, updatable = true)
	private Demande_achatBean    dem_achatBean = new Demande_achatBean();
	 
	 

	public Demande_achatBean getDem_achatBean() {
		return dem_achatBean;
	}


	public void setDem_achatBean(Demande_achatBean dem_achatBean) {
		this.dem_achatBean = dem_achatBean;
	}


	public Code_barreBean getFkCode_barre() {
		return fkCode_barre;
	}


	public void setFkCode_barre(Code_barreBean fkCode_barre) {
		this.fkCode_barre = fkCode_barre;
	}

 
	 
	}
