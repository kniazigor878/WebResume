package by.iharkaratkou.bo;

import java.io.InputStream;
import java.io.Serializable;

public class Country implements Serializable {
	String COUNTRY_ID;
	String COUNTRY_NAME;
	byte[] FLAG;
	
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
}
