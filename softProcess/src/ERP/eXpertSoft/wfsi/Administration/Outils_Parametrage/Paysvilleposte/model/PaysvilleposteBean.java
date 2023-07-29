package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Paysvilleposte.model;


import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect
@Entity
@Table(name = "country", schema = "administration")
public class PaysvilleposteBean {
 
	 
	@Id
	@Column(name = "paycod", unique = true, nullable = true ,insertable=false)
	private String paycod     ="";
	
	@Column(name = "paylib")
	private String   paylib   ="";
	
	
	@Column(name = "capital")
	private String  capital   ="";
	
	@Column(name = "province")
	private String  province     ="";
	
	
	@Column(name = "area")
	private BigDecimal  area   ;
	
	
	@Column(name = "population")
	private BigDecimal  population   ;


	 

	public String getPaycod() {
		return paycod;
	}


	public void setPaycod(String paycod) {
		this.paycod = paycod;
	}


	public String getPaylib() {
		return paylib;
	}


	public void setPaylib(String paylib) {
		this.paylib = paylib;
	}


	public String getCapital() {
		return capital;
	}


	public void setCapital(String capital) {
		this.capital = capital;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public BigDecimal getArea() {
		return area;
	}


	public void setArea(BigDecimal area) {
		this.area = area;
	}


	public BigDecimal getPopulation() {
		return population;
	}


	public void setPopulation(BigDecimal population) {
		this.population = population;
	}  
	
	 
 
	
	

	 

}
