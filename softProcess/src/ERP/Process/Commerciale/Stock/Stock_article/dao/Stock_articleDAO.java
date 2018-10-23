package ERP.Process.Commerciale.Stock.Stock_article.dao;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ERP.Process.Commerciale.Stock.Stock_article.model.IncidentStock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.model.Stock_articleBean;
import ERP.Process.Commerciale.Stock.Stock_article.template.Stock_articleTemplate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.ProcessDate;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
@Repository
public class Stock_articleDAO extends  GenericWeb    {
	
	
	private SessionFactory sessionFactory;

	@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	 
	
	public List<IncidentStock_articleBean> doFindListStock_Incident(
			Stock_articleBean beanSearch) throws Exception {

		Session session = openSessionHibernate(sessionFactory);
		List<IncidentStock_articleBean> lisf = new ArrayList<IncidentStock_articleBean>();
		try {

			String requette = " select  bean   FROM    IncidentStock_articleBean    bean    WHERE     1=1       ";
			requette += "   AND   bean.pk.stock_article_id  ='"+ beanSearch.getStock_article_id()+"'        ";

			lisf = session.createQuery(requette).list();
			commitTransaction(session);
		} catch (Exception e) {
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return lisf;
	}
	 
	
	
	public List<IncidentStock_articleBean> doFindListIncident_Stock_article(  String chain) throws Exception {
		Session session = openSessionHibernate(sessionFactory);
		List<IncidentStock_articleBean> lisf = new ArrayList<IncidentStock_articleBean>();
		try {

			String requette = " select  bean   FROM    IncidentStock_articleBean    bean    WHERE     bean.pk.stock_article_id  in  ("
					+ chain + ")     ";

			lisf = session.createQuery(requette).list();
			commitTransaction(session);
		} catch (Exception e) {
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return lisf;
		}
	
	
	
	public List<Stock_articleBean> doFindListStock_article(
			Stock_articleBean beanSearch) throws Exception {

		Session session = openSessionHibernate(sessionFactory);
		List<Stock_articleBean> lisf = new ArrayList<Stock_articleBean>();
		try {

			BeanSession bs = (BeanSession) getObjectValueModel(BEAN_SESSION);

			String requette = " select  bean   FROM    Stock_articleBean    bean    WHERE     1=1       ";

			if (beanSearch.getPk().getDate_stock() != null)
				requette += "   AND   bean.pk.date_stock >= '"+ProcessDate.getStringFormatDate(beanSearch.getPk().getDate_stock())+"'        ";

			if (beanSearch.getDate_stock2() != null)
				requette += "   AND   bean.pk.date_stock  <= '"+ProcessDate.getStringFormatDate(beanSearch.getDate_stock2()) + "'        ";

			if (!StringUtils.isEmpty(beanSearch.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()))
				requette += "   AND   bean.pk.fkCode_barre.pk.ar_bean.pk_article.ar_id ='"+ beanSearch.getPk().getFkCode_barre().getPk().getAr_bean().getPk_article().getAr_id()+"'        ";

			if (!StringUtils.isEmpty(beanSearch.getPk().getFkCode_barre()
					.getPk().getCode_barre()))
				requette += "   AND   bean.pk.fkCode_barre.pk.code_barre = '"+ beanSearch.getPk().getFkCode_barre().getPk().getCode_barre() + "'        ";

			if ((beanSearch.getPk().getDepot().getDepot_id() != null))
				requette += "   AND   bean.pk.depot.depot_id = "+beanSearch.getPk().getDepot().getDepot_id()+"        ";

		 

			if (!StringUtils.isEmpty(beanSearch.getCondition_max_date_stock()))
				requette += "   " + beanSearch.getCondition_max_date_stock()
						+ "        ";

			if (!StringUtils.isEmpty(beanSearch.getCondition_list_article()))
				requette += "   " + beanSearch.getCondition_list_article()
						+ "        ";

		    if ((beanSearch.getSolde_stock() != null))
				requette += "   AND   bean.solde_stock = '"+ beanSearch.getSolde_stock() + "'        ";

			requette += "   AND   bean.pk.depot.fk_etab_Bean.pk_etab.soc_bean.soc_id='"+ bs.getSoc_id() + "'";

			requette += "   ORDER BY  date(bean.pk.date_stock )   ASC      ";

			lisf = session.createQuery(requette).list();
			commitTransaction(session);
		} catch (Exception e) {
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return lisf;
	}
	 
	
	
	
	
	
	public Boolean doSaveStock_article(Stock_articleBean beanSave) throws Exception {
		boolean result = false;
		Session session = openSessionHibernate(sessionFactory);
		try {
			this.setBeanTrace(beanSave);
			session.save(beanSave);
			this.saveTrace(beanSave);
			result = true;
			commitTransaction(session);
		} catch (Exception e) {
			result = false;
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
	public Boolean doUpdateStock_article(Stock_articleBean beanUpdate)  throws Exception { 
		boolean result = false;
		Session session = openSessionHibernate(sessionFactory);
		try {
			setIdBean((Stock_articleBean) getObjectValueModel(FORM_BEAN), beanUpdate, Stock_articleTemplate.id_entite);
			this.setUpdateValueFieldTraceOject(beanUpdate);
			session.update(beanUpdate);
			this.saveTrace(beanUpdate);
			result = true;
			commitTransaction(session);
		} catch (Exception e) {
			result = false;
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
	
	public Boolean doUpdateStock_Procedure_vente(Stock_articleBean beanUpdate)  throws Exception { 
		boolean result = false;
		Session session = openSessionHibernate(sessionFactory);
		try {
			session.saveOrUpdate(beanUpdate);
			result = true;
			commitTransaction(session);
		} catch (Exception e) {
			result = false;
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
	
	
	public Boolean doDeleteStock_article(Stock_articleBean beanDelete)  throws Exception  {
		boolean result = false;
		Session session = openSessionHibernate(sessionFactory);
		try {
			session.delete(beanDelete);
	        this.saveTrace(beanDelete);
			result=true;
			result = true;
			commitTransaction(session);
		} catch (Exception e) {
			result = false;
			if (sessionIsTrue(session))
				rollbackTransaction(session);
			throw e;
		} finally {
			session.close();
		}
		return result;
	}
}
