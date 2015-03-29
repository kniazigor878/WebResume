package by.iharkaratkou.bo;

import java.io.Serializable;

public class Education implements Serializable {
	String DIPLOMA;
	String EDUC_CENTER;
	String EDUC_PERIOD;
	public String getDIPLOMA() {
		return DIPLOMA;
	}
	public void setDIPLOMA(String dIPLOMA) {
		DIPLOMA = dIPLOMA;
	}
	public String getEDUC_CENTER() {
		return EDUC_CENTER;
	}
	public void setEDUC_CENTER(String eDUC_CENTER) {
		EDUC_CENTER = eDUC_CENTER;
	}
	public String getEDUC_PERIOD() {
		return EDUC_PERIOD;
	}
	public void setEDUC_PERIOD(String eDUC_PERIOD) {
		EDUC_PERIOD = eDUC_PERIOD;
	}
	
	
}
