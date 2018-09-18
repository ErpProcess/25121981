package ERP.Process.Commerciale.Article.model;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@SuppressWarnings("serial")
@Embeddable
public class  PKPrix_article implements Serializable  {
	
	 
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	})
	private ArticleBean ar_bean = new ArticleBean();
	 
	 
	@Column 
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/YYYY", iso = DateTimeFormat.ISO.DATE, style = "SS")
	private Date date_prix;


	public ArticleBean getAr_bean() {
		return ar_bean;
	}


	public void setAr_bean(ArticleBean ar_bean) {
		this.ar_bean = ar_bean;
	}


	public Date getDate_prix() {
		return date_prix;
	}


	public void setDate_prix(Date date_prix) {
		this.date_prix = date_prix;
	}
	 
	 

	 

	 
	 
 
	
	 
	}
