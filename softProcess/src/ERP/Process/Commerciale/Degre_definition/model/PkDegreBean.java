package ERP.Process.Commerciale.Degre_definition.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Article.model.ArticleBean;
import ERP.Process.Commerciale.Caracteristique.model.CaracteristiqueBean;

 
 
@Embeddable
public class PkDegreBean implements java.io.Serializable {



	private static final long serialVersionUID = -8080356779287842724L;


	private ArticleBean art_Bean= new ArticleBean();


	private CaracteristiqueBean carac_Bean= new CaracteristiqueBean();

	
	@ManyToOne
	public ArticleBean getArt_Bean() {
		return art_Bean;
	}

	public void setArt_Bean(ArticleBean art_Bean) {
		this.art_Bean = art_Bean;
	}

	@ManyToOne
	public CaracteristiqueBean getCarac_Bean() {
		return carac_Bean;
	}

	public void setCarac_Bean(CaracteristiqueBean carac_Bean) {
		this.carac_Bean = carac_Bean;
	}
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PkDegreBean that = (PkDegreBean) o;

        if (art_Bean != null ? !art_Bean.equals(that.art_Bean) : that.art_Bean != null) return false;
        if (carac_Bean != null ? !carac_Bean.equals(that.carac_Bean) : that.carac_Bean != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (art_Bean != null ? art_Bean.hashCode() : 0);
        result = 31 * result + (carac_Bean != null ? carac_Bean.hashCode() : 0);
        return result;
    }

}
