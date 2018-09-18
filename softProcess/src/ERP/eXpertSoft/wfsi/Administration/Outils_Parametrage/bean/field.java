package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.bean;

public class field {
	  
         String lname;  
          String fname;  
          String mi;  
        field(String lname,String fname,String mi) {  
                this.lname = lname;  
                this.fname = fname;  
                this.mi = mi;  
        }  
        String getLname() { return lname; }  
        String getFname() { return fname; }  
        String getMi() { return mi; }  
        public String toString() {  
                return lname+","+fname+","+mi;  
        
}  
}
