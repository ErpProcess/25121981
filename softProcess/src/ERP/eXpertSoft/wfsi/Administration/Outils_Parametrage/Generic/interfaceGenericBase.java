package ERP.eXpertSoft.wfsi.Administration.Outils_Parametrage.Generic;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

 

public abstract class interfaceGenericBase implements interfaceGeneric {
	
	  public void process(URL url) throws IOException {
	        URLConnection urlConnection = (URLConnection) url.openConnection();
	        InputStream input = urlConnection.getInputStream();

	        try{
	            processURLData(input);
	        } finally {
	            input.close();
	        }
	    }

	    protected abstract void processURLData(InputStream input)
	        throws IOException;

}


 
