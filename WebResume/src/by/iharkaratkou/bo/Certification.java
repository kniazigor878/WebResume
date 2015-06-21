package by.iharkaratkou.bo;

import java.io.Serializable;

public class Certification implements Serializable {
	String CERT_NAME;
	String CERT_DATE;
	
	public String getCERT_NAME() {
		return CERT_NAME;
	}
	public void setCERT_NAME(String cERT_NAME) {
		CERT_NAME = cERT_NAME;
	}
	public String getCERT_DATE() {
		return CERT_DATE;
	}
	public void setCERT_DATE(String cERT_DATE) {
		CERT_DATE = cERT_DATE;
	}
}
