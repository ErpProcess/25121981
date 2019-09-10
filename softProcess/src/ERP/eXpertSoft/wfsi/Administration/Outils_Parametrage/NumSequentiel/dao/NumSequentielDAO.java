package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.dao;

import java.lang.reflect.Field;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic.GenericWeb;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSeqReserve;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.NumSequentielBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.NumSequentiel.model.Num_seqBean;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BDateTime;
import ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean.BeanSession;
 
@Repository
public class NumSequentielDAO extends  GenericWeb implements INumSequentielDAO {
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory (SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	
	 
	@SuppressWarnings("unchecked")
	public List<NumSequentielBean> doFindListNumSequentiel(NumSequentielBean beanSearch) {
		
		String requette="   FROM  NumSequentielBean      WHERE 1=1      ";
		 
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	
	 
	public void  getNumSeqSimple(Object bean,String code_num_attribute ,	Session session ) throws Exception {
		
		String master_num=" select  numbean    FROM  Num_seqBean numbean  WHERE  numbean.code_num='"+code_num_attribute+"'      ";
		
		try {
			 
			List <Num_seqBean> list_num_seq=session.createQuery(master_num).list();
			Num_seqBean  beanMaster=list_num_seq.get(0);
			BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
			int nbr_chiffre= beanMaster.getNbr_chiffre().intValue();
			  
			 String req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			 
			 //if( beanMaster.getType_num().indexOf("etab_id")>-1) 
				    req+=
				    	   "       AND   bean.pknumseqbean.etab_id  ='"+bs.getEtab_id()+"'         ";
			  
			 
			 //if( beanMaster.getType_num().indexOf("soc_id")>-1) 
				    req+=
				    	  "       AND   bean.pknumseqbean.soc_id   ='"+bs.getSoc_id()+"'          ";
			  
			  
			 
				 if( beanMaster.getType_num().indexOf("numero")>-1) {
					    req+="";
				  }
			 
			 
			    if( beanMaster.getType_num().indexOf("annee")>-1) {
					    req+=
					        "       AND   bean.pknumseqbean.annee    ='"+BDateTime.getAnneeString()+"'     ";
				}
			 
				if( beanMaster.getType_num().indexOf("mois")>-1) {
				      req+=
				        "           AND   bean.pknumseqbean.mois     ='"+BDateTime.getMoisString()+"'      ";
				 }
			
			List<NumSequentielBean> list_detail=session.createQuery(req).list();
			
			NumSequentielBean bean_detail = new NumSequentielBean();
			String numSeq_Resultat="";
			
			if(list_detail!=null  &&  list_detail.size()>0){
			    bean_detail=list_detail.get(0);
			    String res=bean_detail.getNumero();
				int numSeq=Integer.parseInt(res);
				int NumSqSuivant=numSeq+1;
				int lengthNumSqSuivant=String.valueOf(NumSqSuivant).length();
				 
				int lesZero=nbr_chiffre-lengthNumSqSuivant;
				String zero_wette="";
				for (int i = 0; i <lesZero; i++) {
					zero_wette=zero_wette+"0";
				}
				numSeq_Resultat=zero_wette+String.valueOf(NumSqSuivant);
				bean_detail.setNumero(numSeq_Resultat);
				bean_detail.setJour(BDateTime.getJourString());
				session.update(bean_detail);
				
			}else{
				
				int les_Zero=nbr_chiffre;
				    les_Zero=les_Zero-1;
				
				String zero_wette="";
				for (int i = 0; i <les_Zero; i++) {
					zero_wette=zero_wette+"0";
				}
				bean_detail.setNumero(zero_wette+"1");
				bean_detail.getPknumseqbean().setCode_num(code_num_attribute);
				bean_detail.getPknumseqbean().setAnnee(BDateTime.getAnneeString());
				bean_detail.getPknumseqbean().setMois(BDateTime.getMoisString());
				bean_detail.setJour(BDateTime.getJourString());
				bean_detail.getPknumseqbean().setSoc_id(bs.getSoc_id());
				bean_detail.getPknumseqbean().setEtab_id(bs.getEtab_id());
				session.save(bean_detail);
			}
			
			
			String prefix=beanMaster.getPrefix()!=null && !beanMaster.getPrefix().equals("")?beanMaster.getPrefix():"";
			
			 if(beanMaster.getType_num().indexOf(";")<0){
				 
				 String paramter=bean_detail.getNumero()!=null?bean_detail.getNumero():"";
				        paramter=prefix+paramter;
				 GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,paramter);
				 
			 }else{
				 
				String[]  num_final= beanMaster.getType_num().split(";");
				String elment_data="";
				for (int i = 0; i < num_final.length; i++) {
					String   ssss=(String) GenericWeb.getValueOject_with_name_field(bean_detail, num_final[i]);
					elment_data+=ssss;
				}
				elment_data+=bean_detail.getNumero();
				elment_data=prefix+elment_data;
			    GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,elment_data);
			    
			 }
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	
	
	public void  getNumSeqSimple(Object bean,String code_num_attribute ,	Session session,String prefix ) throws Exception {
		
		String master_num=" select  numbean    FROM  Num_seqBean numbean  WHERE  numbean.code_num='"+code_num_attribute+"'      ";
		
		try {
			 
			List <Num_seqBean> list_num_seq=session.createQuery(master_num).list();
			Num_seqBean  beanMaster=list_num_seq.get(0);
			BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
			int nbr_chiffre= beanMaster.getNbr_chiffre().intValue();
			  
			 String req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			 
			 //if( beanMaster.getType_num().indexOf("etab_id")>-1) 
				    req+=
				    	   "       AND   bean.pknumseqbean.etab_id  ='"+bs.getEtab_id()+"'         ";
			  
			 
			 //if( beanMaster.getType_num().indexOf("soc_id")>-1) 
				    req+=
				    	  "       AND   bean.pknumseqbean.soc_id   ='"+bs.getSoc_id()+"'          ";
			  
			  
			 
				 if( beanMaster.getType_num().indexOf("numero")>-1) {
					    req+="";
				  }
			 
			 
			    if( beanMaster.getType_num().indexOf("annee")>-1) {
					    req+=
					        "       AND   bean.pknumseqbean.annee    ='"+BDateTime.getAnneeString()+"'     ";
				}
			 
				if( beanMaster.getType_num().indexOf("mois")>-1) {
				      req+=
				        "           AND   bean.pknumseqbean.mois     ='"+BDateTime.getMoisString()+"'      ";
				 }
			
			List<NumSequentielBean> list_detail=session.createQuery(req).list();
			
			NumSequentielBean bean_detail = new NumSequentielBean();
			String numSeq_Resultat="";
			
			if(list_detail!=null  &&  list_detail.size()>0){
			    bean_detail=list_detail.get(0);
			    String res=bean_detail.getNumero();
				int numSeq=Integer.parseInt(res);
				int NumSqSuivant=numSeq+1;
				int lengthNumSqSuivant=String.valueOf(NumSqSuivant).length();
				 
				int lesZero=nbr_chiffre-lengthNumSqSuivant;
				String zero_wette="";
				for (int i = 0; i <lesZero; i++) {
					zero_wette=zero_wette+"0";
				}
				numSeq_Resultat=zero_wette+String.valueOf(NumSqSuivant);
				bean_detail.setNumero(numSeq_Resultat);
				bean_detail.setJour(BDateTime.getJourString());
				session.update(bean_detail);
				
			}else{
				
				int les_Zero=nbr_chiffre;
				    les_Zero=les_Zero-1;
				
				String zero_wette="";
				for (int i = 0; i <les_Zero; i++) {
					zero_wette=zero_wette+"0";
				}
				bean_detail.setNumero(zero_wette+"1");
				bean_detail.getPknumseqbean().setCode_num(code_num_attribute);
				bean_detail.getPknumseqbean().setAnnee(BDateTime.getAnneeString());
				bean_detail.getPknumseqbean().setMois(BDateTime.getMoisString());
				bean_detail.setJour(BDateTime.getJourString());
				bean_detail.getPknumseqbean().setSoc_id(bs.getSoc_id());
				bean_detail.getPknumseqbean().setEtab_id(bs.getEtab_id());
				session.save(bean_detail);
			}
			
			
			  prefix=prefix!=null && !prefix.equals("")?prefix:"";
			
			 if(beanMaster.getType_num().indexOf(";")<0){
				 
				 String paramter=bean_detail.getNumero()!=null?bean_detail.getNumero():"";
				        paramter=prefix+paramter;
				 GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,paramter);
				 
			 }else{
				 
				String[]  num_final= beanMaster.getType_num().split(";");
				String elment_data="";
				for (int i = 0; i < num_final.length; i++) {
					String   ssss=(String) GenericWeb.getValueOject_with_name_field(bean_detail, num_final[i]);
					elment_data+=ssss;
				}
				elment_data+=bean_detail.getNumero();
				elment_data=prefix+elment_data;
			    GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,elment_data);
			    
			 }
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	
	public void  getNumSeqSimple(Object bean,String code_num_attribute  ) throws Exception {
		
		String master_num=" select  numbean    FROM  Num_seqBean numbean  WHERE  numbean.code_num='"+code_num_attribute+"'      ";
		
		try {
			
			List <Num_seqBean> list_num_seq=hibernateTemplate.find(master_num);
			Num_seqBean  beanMaster=list_num_seq.get(0);
			BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
			int nbr_chiffre= beanMaster.getNbr_chiffre().intValue();
			  
			 String req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			  
			 
			 if( beanMaster.getType_num().indexOf("numero")>-1) {
				    req+="";
			  }
			 
			  if( beanMaster.getType_num().indexOf("etab_id")>-1) {
				    req+=
				    	   "       AND   bean.pknumseqbean.etab_id  ='"+bs.getEtab_id()+"'         ";
			  }
			 
			  if( beanMaster.getType_num().indexOf("soc_id")>-1) {
				    req+=
				    	  "       AND   bean.pknumseqbean.soc_id   ='"+bs.getSoc_id()+"'          ";
			   } 
		    if( beanMaster.getType_num().indexOf("annee")>-1) {
				    req+=
				        "       AND   bean.pknumseqbean.annee    ='"+BDateTime.getAnneeString()+"'     ";
			}
			 
			if( beanMaster.getType_num().indexOf("mois")>-1) {
			      req+=
			        "           AND   bean.pknumseqbean.mois     ='"+BDateTime.getMoisString()+"'      ";
			 }
			
			if(code_num_attribute.equals("soc_id") ||  code_num_attribute.equals("pk_etab.etab_id") )
				req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			
			List<NumSequentielBean> list_detail=hibernateTemplate.find(req);
			
			NumSequentielBean bean_detail = new NumSequentielBean();
			String numSeq_Resultat="";
			
			if(list_detail!=null  &&  list_detail.size()>0){
			    bean_detail=list_detail.get(0);
			    String res=bean_detail.getNumero();
				int numSeq=Integer.parseInt(res);
				int NumSqSuivant=numSeq+1;
				int lengthNumSqSuivant=String.valueOf(NumSqSuivant).length();
				 
				int lesZero=nbr_chiffre-lengthNumSqSuivant;
				String zero_wette="";
				for (int i = 0; i <lesZero; i++) {
					zero_wette=zero_wette+"0";
				}
				numSeq_Resultat=zero_wette+String.valueOf(NumSqSuivant);
				bean_detail.setNumero(numSeq_Resultat);
				bean_detail.setJour(BDateTime.getJourString());
				hibernateTemplate.update(bean_detail);
				
			}else{
				
				int les_Zero=nbr_chiffre;
				    les_Zero=les_Zero-1;
				
				String zero_wette="";
				for (int i = 0; i <les_Zero; i++) {
					zero_wette=zero_wette+"0";
				}
				bean_detail.setNumero(zero_wette+"1");
				bean_detail.getPknumseqbean().setCode_num(code_num_attribute);
				bean_detail.getPknumseqbean().setAnnee(BDateTime.getAnneeString());
				bean_detail.getPknumseqbean().setMois(BDateTime.getMoisString());
				bean_detail.setJour(BDateTime.getJourString());
				bean_detail.getPknumseqbean().setSoc_id(bs.getSoc_id());
				bean_detail.getPknumseqbean().setEtab_id(bs.getEtab_id());
				hibernateTemplate.save(bean_detail);
			}
			
			
			String prefix=beanMaster.getPrefix()!=null && !beanMaster.getPrefix().equals("")?beanMaster.getPrefix():"";
			
			 if(beanMaster.getType_num().indexOf(";")<0){
				 
				 String paramter=bean_detail.getNumero()!=null?bean_detail.getNumero():"";
				        paramter=prefix+paramter;
				 GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,paramter);
				 
			 }else{
				 
				String[]  num_final= beanMaster.getType_num().split(";");
				String elment_data="";
				for (int i = 0; i < num_final.length; i++) {
					String   ssss=(String) GenericWeb.getValueOject_with_name_field(bean_detail, num_final[i]);
					elment_data+=ssss;
				}
				elment_data+=bean_detail.getNumero();
				elment_data=prefix+elment_data;
			    GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,elment_data);
			    
			 }
			 
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	
	
	public void  doDecrementeNumSeq( String code_num_attribute  ) throws Exception {
		
		String master_num=" select  numbean    FROM  Num_seqBean numbean  WHERE  numbean.code_num='"+code_num_attribute+"'      ";
		
		try {
			
			List <Num_seqBean> list_num_seq=hibernateTemplate.find(master_num);
			Num_seqBean  beanMaster=list_num_seq.get(0);
			BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
			int nbr_chiffre= beanMaster.getNbr_chiffre().intValue();
			  
			 String req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			  
			 
			 if( beanMaster.getType_num().indexOf("numero")>-1) {
				    req+="";
			  }
			 
			  if( beanMaster.getType_num().indexOf("etab_id")>-1) {
				    req+=
				    	   "       AND   bean.pknumseqbean.etab_id  ='"+bs.getEtab_id()+"'         ";
			  }
			 
			  if( beanMaster.getType_num().indexOf("soc_id")>-1) {
				    req+=
				    	  "       AND   bean.pknumseqbean.soc_id   ='"+bs.getSoc_id()+"'          ";
			   } 
		    if( beanMaster.getType_num().indexOf("annee")>-1) {
				    req+=
				        "       AND   bean.pknumseqbean.annee    ='"+BDateTime.getAnneeString()+"'     ";
			}
			 
			if( beanMaster.getType_num().indexOf("mois")>-1) {
			      req+=
			        "           AND   bean.pknumseqbean.mois     ='"+BDateTime.getMoisString()+"'      ";
			 }
			
			if(code_num_attribute.equals("soc_id") ||  code_num_attribute.equals("pk_etab.etab_id") )
				req=" select  bean    FROM  NumSequentielBean bean     WHERE   bean.pknumseqbean.code_num ='"+code_num_attribute+"'      ";
			
			List<NumSequentielBean> list_detail=hibernateTemplate.find(req);
			
			NumSequentielBean bean_detail = new NumSequentielBean();
			String numSeq_Resultat="";
			
			if(list_detail!=null  &&  list_detail.size()>0){
			    bean_detail=list_detail.get(0);
			    String res=bean_detail.getNumero();
				int numSeq=Integer.parseInt(res);
				int NumSqSuivant=numSeq-1;
				int lengthNumSqSuivant=String.valueOf(NumSqSuivant).length();
				 
				int lesZero=nbr_chiffre-lengthNumSqSuivant;
				String zero_wette="";
				for (int i = 0; i <lesZero; i++) {
					zero_wette=zero_wette+"0";
				}
				numSeq_Resultat=zero_wette+String.valueOf(NumSqSuivant);
				bean_detail.setNumero(numSeq_Resultat);
				bean_detail.setJour(BDateTime.getJourString());
				hibernateTemplate.update(bean_detail);
				
			}
//			else{
//				
//				int les_Zero=nbr_chiffre;
//				    les_Zero=les_Zero-1;
//				
//				String zero_wette="";
//				for (int i = 0; i <les_Zero; i++) {
//					zero_wette=zero_wette+"0";
//				}
//				bean_detail.setNumero(zero_wette+"1");
//				bean_detail.getPknumseqbean().setCode_num(code_num_attribute);
//				bean_detail.getPknumseqbean().setAnnee(BDateTime.getAnneeString());
//				bean_detail.getPknumseqbean().setMois(BDateTime.getMoisString());
//				bean_detail.setJour(BDateTime.getJourString());
//				bean_detail.getPknumseqbean().setSoc_id(bs.getSoc_id());
//				bean_detail.getPknumseqbean().setEtab_id(bs.getEtab_id());
//				hibernateTemplate.save(bean_detail);
//			}
			
			
//			String prefix=beanMaster.getPrefix()!=null && !beanMaster.getPrefix().equals("")?beanMaster.getPrefix():"";
//			
//			 if(beanMaster.getType_num().indexOf(";")<0){
//				 
//				 String paramter=bean_detail.getNumero()!=null?bean_detail.getNumero():"";
//				        paramter=prefix+paramter;
//				 GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,paramter);
//				 
//			 }else{
//				 
//				String[]  num_final= beanMaster.getType_num().split(";");
//				String elment_data="";
//				for (int i = 0; i < num_final.length; i++) {
//					String   ssss=(String) GenericWeb.getValueOject_with_name_field(bean_detail, num_final[i]);
//					elment_data+=ssss;
//				}
//				elment_data+=bean_detail.getNumero();
//				elment_data=prefix+elment_data;
//			    GenericWeb.setValueOject_with_name_field(bean , code_num_attribute ,elment_data);
//			    
//			 }
			 
			
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String  getNumSeqWithPrefixM(Object bean,String code_num_attribute,String  prefix ,int nbr_chiffre ) {
		String req=" select  bean    FROM  NumSequentielBean bean      WHERE  " +
				"    bean.pknumseqbean.code_num='"+code_num_attribute+"'      ";
		try {
			List<NumSequentielBean> list=hibernateTemplate.find(req);
			
			NumSequentielBean beanRes = new NumSequentielBean();
			String numSeqResultat="";
			if(list!=null  &&  list.size()>0){
			    beanRes=list.get(0);
			    String res="";
			    String debuchaine=beanRes.getNumero();
			    for (int i = 0; i < debuchaine.length(); i++) {
			    	
			    	char cc=debuchaine.charAt(i);
			    	if(cc=='0' ||  cc=='1' ||  cc=='2' ||  cc=='3' ||  cc=='4' ||  cc=='5' ||  cc=='6' ||  cc=='7' ||  cc=='8' ||  cc=='9' )
			    		res=res+cc;
					
				}
				int numSeq=Integer.parseInt(res);
				int NumSqSuivant=numSeq+1;
				int lengthNumSqSuivant=String.valueOf(NumSqSuivant).length();
				 
				int lesZero=nbr_chiffre-lengthNumSqSuivant;
				String zerowette="";
				for (int i = 0; i <lesZero; i++) {
					zerowette=zerowette+"0";
				}
				numSeqResultat=zerowette+String.valueOf(NumSqSuivant);
				beanRes.setNumero(prefix+numSeqResultat);
				hibernateTemplate.update(beanRes);
			}else{
				int lesZero=nbr_chiffre;
				String zerowette="";
				for (int i = 0; i <lesZero; i++) {
					zerowette=zerowette+"0";
				}
				BeanSession  bs=(BeanSession) getObjectValueModel(BEAN_SESSION);
				beanRes.setNumero(prefix+zerowette);
				numSeqResultat=zerowette;
				beanRes.getPknumseqbean().setCode_num(code_num_attribute);
				beanRes.getPknumseqbean().setAnnee("2015");
				beanRes.getPknumseqbean().setMois("01");
				beanRes.getPknumseqbean().setSoc_id("SOC-001");
				hibernateTemplate.save(beanRes);
			}
				
			
			
			 Field field = bean.getClass().getDeclaredField(code_num_attribute);
		     field.setAccessible(true);
		     field.set(bean, prefix+numSeqResultat!=null?prefix+numSeqResultat:"");
		     
			return numSeqResultat;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		 
		
	}
	 
	public Boolean saveNumSequentiel(NumSequentielBean beanSave) {

		try {
			//this.setCreValueFieldTraceOject(beanSave);
			this.hibernateTemplate.save(beanSave);
			//this.saveTrace(beanSave,"usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean updateNumSequentiel(NumSequentielBean beanUpdate) {

		try {
			//this.setUpdateValueFieldTraceOject(beanUpdate);
			this.hibernateTemplate.update(beanUpdate);
			//this.saveTrace(beanUpdate, "usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public Boolean dodeleteNumSequentiel(NumSequentielBean beanDelete) {

		try {
			hibernateTemplate.delete(beanDelete);
			//this.saveTrace(beanDelete, "usr_id");
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	public void doInsertNumSequentielReseve(NumSeqReserve numSeqReserve) {

		try {
			hibernateTemplate.save(numSeqReserve);
			//this.saveTrace(beanDelete, "usr_id");
			 
		} catch (Exception ex) {
			ex.printStackTrace();
		 
		}

	}
	
	public List<NumSeqReserve> doFetchNumSequentielReseve(NumSeqReserve numSeqReserve) throws Exception {

		String requette="   FROM  NumSeqReserve  bean    WHERE    bean.code_num='"+numSeqReserve.getCode_num()+"'    ";
		 
		  requette +=this.setSocieteEtabFetch(numSeqReserve,"bean.fk_etab_Bean", false);
		try {
			return   hibernateTemplate.find(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	public  void doDeleteNumSequentielReseve(NumSeqReserve numSeqReserve) throws Exception {

		String requette="  DELETE FROM  NumSeqReserve  bean    WHERE   "
				+ "     bean.code_num='"+numSeqReserve.getCode_num()+"'   and   "
						+ "   bean.numero='"+numSeqReserve.getNumero()+"'   ";
		 
		  requette +=this.setSocieteEtabFetch(numSeqReserve,"bean.fk_etab_Bean", false);
		try {
			  hibernateTemplate.bulkUpdate(requette);
		} catch (Exception ex) {
			ex.printStackTrace();
			 
		}

	}
	 

}
