package by.iharkaratkou.bo;

import java.io.Serializable;
import java.util.ArrayList;

public class Exp_activity implements Serializable {
	ArrayList<String> exp_activities;
	String exp_ID;

	public String getExp_ID() {
		return exp_ID;
	}

	public void setExp_ID(String exp_ID) {
		this.exp_ID = exp_ID;
	}

	public ArrayList<String> getExp_activities() {
		return exp_activities;
	}

	public void setExp_activities(ArrayList<String> exp_activities) {
		this.exp_activities = exp_activities;
	}
}
