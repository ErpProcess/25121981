package ERP.Process.Commerciale.Code_barre.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import ERP.Process.Commerciale.Article.model.ArticleBean;



@Embeddable
public class PkCodeBarre implements Serializable,Cloneable {

	private static final long serialVersionUID = 9000823046063816516L;
	
	@Column
	private String code_barre = "";
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	})
	private ArticleBean ar_bean = new ArticleBean();
	

	public String getCode_barre() {
		return code_barre;
	}

	public void setCode_barre(String code_barre) {
		this.code_barre = code_barre;
	}

	public ArticleBean getAr_bean() {
		return ar_bean;
	}

	public void setAr_bean(ArticleBean ar_bean) {
		this.ar_bean = ar_bean;
	}
	
	 
	
	 

	 

	 
	
	 

	 

}
