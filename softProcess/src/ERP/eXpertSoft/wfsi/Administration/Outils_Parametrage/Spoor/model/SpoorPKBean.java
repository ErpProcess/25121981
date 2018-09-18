package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Spoor.model;

 

import java.io.Serializable;
import java.sql.Date;
 

import javax.persistence.Column;
import javax.persistence.Embeddable;

 

@Embeddable
public class SpoorPKBean implements Serializable {
 
	 
	@Column(name = "sp_date")
	private java.sql.Date sp_date ;
	
	 
	@Column(name = "sp_time")
	private java.sql.Time sp_time ;
	
	 
	@Column(name = "sp_id")
	private int sp_id ;
	
	 
	@Column(name = "usr_login")
	private String usr_login = "";
	
	 

	public Date getSp_date() {
		return sp_date;
	}

	public void setSp_date(Date sp_date) {
		this.sp_date = sp_date;
	}

 

	public java.sql.Time getSp_time() {
		return sp_time;
	}

	public void setSp_time(java.sql.Time sp_time) {
		this.sp_time = sp_time;
	}

	public int getSp_id() {
		return sp_id;
	}

	public void setSp_id(int sp_id) {
		this.sp_id = sp_id;
	}

	public String getUsr_login() {
		return usr_login;
	}

	public void setUsr_login(String usr_login) {
		this.usr_login = usr_login;
	}

	public SpoorPKBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	 

	 
	 
	
	 
	 
	
	
	

	
	 

	 


	
 


	 
	 

	 

}
