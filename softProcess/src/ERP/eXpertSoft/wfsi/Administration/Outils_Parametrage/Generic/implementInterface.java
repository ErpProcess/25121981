package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.io.IOException;
import java.io.InputStream;

public class implementInterface   extends interfaceGenericBase {
	 @Override
	    protected void processURLData(InputStream input) throws IOException {
	        int data = input.read();
	        while(data != -1){
	            System.out.println((char) data);
	            data = input.read();
	        }
	    }
}
 
