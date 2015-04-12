package by.iharkaratkou.bo;

import java.io.Serializable;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class Label implements Serializable {
	byte[] LABEL;
	String STRLABEL;
	
	public String getSTRLABEL() {
		return STRLABEL;
	}
	public void setSTRLABEL() {
		STRLABEL = new String(Base64.encode(this.LABEL));
	}
	public byte[] getLABEL() {
		return LABEL;
	}
	public void setLABEL(byte[] fLAG) {
		LABEL = fLAG;
	}
}
