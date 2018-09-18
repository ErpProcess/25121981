package ERP.Process.Commerciale.Vente.Commandeclient.model;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import ERP.Process.Commerciale.Code_barre.model.Code_barreBean;


@SuppressWarnings("serial")
@Embeddable
public class  PKDetCmdClt implements Serializable,Cloneable  {
	
	@ManyToOne
	@JoinColumns({
		 @JoinColumn(name = "etab_id", insertable = true, updatable = true,referencedColumnName="etab_id"),
	     @JoinColumn(name = "soc_id", insertable = true, updatable = true,referencedColumnName="soc_id"),
	     @JoinColumn(name = "ar_id", insertable = true, updatable = true,referencedColumnName="ar_id"),
	     @JoinColumn(name = "code_barre", insertable = true, updatable = true,referencedColumnName="code_barre"),
	})
	private Code_barreBean  fkcode_barre = new Code_barreBean();
	
	@ManyToOne 
	@JoinColumn(name = "cmd_id", insertable = true, updatable = true)
	private CommandeclientBean     cmd = new CommandeclientBean ();



	 



	public Code_barreBean getFkcode_barre() {
		return fkcode_barre;
	}



	public void setFkcode_barre(Code_barreBean fkcode_barre) {
		this.fkcode_barre = fkcode_barre;
	}



	public CommandeclientBean getCmd() {
		return cmd;
	}



	public void setCmd(CommandeclientBean cmd) {
		this.cmd = cmd;
	}



	 
	}
