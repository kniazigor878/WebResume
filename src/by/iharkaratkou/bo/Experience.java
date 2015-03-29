package by.iharkaratkou.bo;

import java.io.Serializable;

public class Experience implements Serializable{
	String ID;
	String POSITION;
	String COMPANY;
	String PERIOD;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPOSITION() {
		return POSITION;
	}
	public void setPOSITION(String pOSITION) {
		POSITION = pOSITION;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getPERIOD() {
		return PERIOD;
	}
	public void setPERIOD(String pERIOD) {
		PERIOD = pERIOD;
	}
}
