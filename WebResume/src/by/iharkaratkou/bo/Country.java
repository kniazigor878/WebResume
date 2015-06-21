package by.iharkaratkou.bo;

import java.io.InputStream;
import java.io.Serializable;

//import com.sun.org.apache.xml.internal.security.utils.Base64
import org.apache.commons.codec.binary.Base64;

public class Country implements Serializable {
	String COUNTRY_ID;
	String COUNTRY_NAME;
	byte[] FLAG;
	String STRFLAG;
	
	public byte[] getFLAG() {
		return FLAG;
	}
	public void setFLAG(byte[] fLAG) {
		FLAG = fLAG;
	}
	public String getCOUNTRY_ID() {
		return COUNTRY_ID;
	}
	public void setCOUNTRY_ID(String cOUNTRY_ID) {
		COUNTRY_ID = cOUNTRY_ID;
	}
	public String getCOUNTRY_NAME() {
		return COUNTRY_NAME;
	}
	public void setCOUNTRY_NAME(String cOUNTRY_NAME) {
		COUNTRY_NAME = cOUNTRY_NAME;
	}
	public void setSTRFLAG()
	{
		STRFLAG = new String(org.apache.commons.codec.binary.Base64.encodeBase64(this.FLAG));
	}
	public String getSTRFLAG()
	{
		return STRFLAG;
	}
}
