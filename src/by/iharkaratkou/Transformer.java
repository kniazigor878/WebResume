package by.iharkaratkou;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;

import by.iharkaratkou.bo.Certification;
import by.iharkaratkou.bo.Country;
import by.iharkaratkou.bo.Education;
import by.iharkaratkou.bo.Exp_activity;
import by.iharkaratkou.bo.Experience;
import by.iharkaratkou.bo.GeneralData;
import by.iharkaratkou.bo.Label;
import by.iharkaratkou.bo.Qualifications;
import by.iharkaratkou.dto.DBUtils;
import by.iharkaratkou.javaUtils.JavaHelpUtils;

/**
 * This class contains methods which transform select results from database into objects of data model.
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
 */
public class Transformer {
	
	final static Logger logger = Logger.getLogger(Transformer.class);
	
	public GeneralData getGeneralDataFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		GeneralData gd = new GeneralData();
		
		final Integer NAME = 0;
		final Integer SURNAME = 1;
		final Integer CURRENT_POST = 2;
		final Integer CURRENT_COMPANY = 3;
		final Integer CURRENT_LOCATION = 4;
		final Integer CURRENT_BUS_PHONE = 5;
		final Integer CURRENT_BUSINESS_MAIL = 6;
		final Integer SN_LINKEDIN = 7;
		final Integer SN_TWITTER = 8;

		for(ArrayList<String> row : queryResult){
			gd.setNAME(row.get(NAME));
			gd.setSURNAME(row.get(SURNAME));
			gd.setCURRENT_POST(row.get(CURRENT_POST));
			gd.setCURRENT_COMPANY(row.get(CURRENT_COMPANY));
			gd.setCURRENT_LOCATION(row.get(CURRENT_LOCATION));
			gd.setCURRENT_BUS_PHONE(row.get(CURRENT_BUS_PHONE));
			gd.setCURRENT_BUSINESS_MAIL(row.get(CURRENT_BUSINESS_MAIL));
			gd.setSN_LINKEDIN(row.get(SN_LINKEDIN));
			gd.setSN_TWITTER(row.get(SN_TWITTER));
		}
		
		return gd;
	}
	
	public Qualifications getQualificationsFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		Qualifications quals = new Qualifications();
		ArrayList<String> qualifications_temp = new ArrayList<String>();
		
		final Integer QUALIFICATION = 0;

		for(ArrayList<String> row : queryResult){
			qualifications_temp.add(row.get(QUALIFICATION));
		}
		
		quals.setQualifications(qualifications_temp);
		
		return quals;
	}
	
	public ArrayList<Experience> getExperiencesFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Experience> exps = new ArrayList<Experience>();
		Experience exp_temp = new Experience();
		
		final Integer EXP_ID = 0;
		final Integer POSITION = 1;
		final Integer COMPANY = 2;
		final Integer PERIOD = 3;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<String> row : queryResult){
			exp_temp.setID(row.get(EXP_ID));
			exp_temp.setPOSITION(row.get(POSITION));
			exp_temp.setCOMPANY(row.get(COMPANY));
			exp_temp.setPERIOD(row.get(PERIOD));
			exps.add((Experience) jhu.deepClone(exp_temp));
		}
		
		return exps;
	}
	
	public ArrayList<Exp_activity> getExperienceAcrivitiesFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Exp_activity> exp_acts = new ArrayList<Exp_activity>();
		Exp_activity exp_act_id = new Exp_activity();
		ArrayList<String> exp_acts_temp = new ArrayList<String>();
		HashMap<String,ArrayList<String>> hmQueryResult = new HashMap<String,ArrayList<String>>();
		
		final Integer EXP_ID = 0;
		final Integer ACTIVITY = 1;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<String> row : queryResult){
			if(hmQueryResult.containsKey(row.get(EXP_ID))){
				hmQueryResult.get(row.get(EXP_ID)).add(row.get(ACTIVITY));
			}else{
				exp_acts_temp.add(row.get(ACTIVITY));
				hmQueryResult.put(row.get(EXP_ID), (ArrayList<String>) jhu.deepClone(exp_acts_temp));
				exp_acts_temp.clear();
			}
		}
		
		Set<String> kHmSet = hmQueryResult.keySet();
		for(String id: kHmSet){
			exp_act_id.setExp_ID(id);
			exp_act_id.setExp_activities(hmQueryResult.get(id));
			exp_acts.add((Exp_activity) jhu.deepClone(exp_act_id));
		}
		
		return exp_acts;
	}
	
	public ArrayList<Certification> getCertificationsFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Certification> certs = new ArrayList<Certification>();
		Certification cert_temp = new Certification();
		
		final Integer CERT_NAME = 0;
		final Integer CERT_DATE = 1;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<String> row : queryResult){
			cert_temp.setCERT_NAME(row.get(CERT_NAME));
			cert_temp.setCERT_DATE(row.get(CERT_DATE));
			certs.add((Certification) jhu.deepClone(cert_temp));
		}
		
		return certs;
	}

	public ArrayList<Education> getEducationsFromQuery(ArrayList<ArrayList<String>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Education> educs = new ArrayList<Education>();
		Education educ_temp = new Education();
		
		final Integer DIPLOMA = 0;
		final Integer EDUC_CENTER = 1;
		final Integer EDUC_PERIOD = 2;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<String> row : queryResult){
			educ_temp.setDIPLOMA(row.get(DIPLOMA));
			educ_temp.setEDUC_CENTER(row.get(EDUC_CENTER));
			educ_temp.setEDUC_PERIOD(row.get(EDUC_PERIOD));
			educs.add((Education) jhu.deepClone(educ_temp));
		}
		
		return educs;
	}
	
	public ArrayList<Country> getVisCountriesFromQuery(ArrayList<ArrayList<byte[]>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Country> vis_countries = new ArrayList<Country>();
		Country vis_countries_temp = new Country();
		
		final Integer FLAG = 0;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<byte[]> row : queryResult){
			vis_countries_temp.setFLAG(row.get(FLAG));
			vis_countries_temp.setSTRFLAG();

			vis_countries.add((Country) jhu.deepClone(vis_countries_temp));
		}
		
		return vis_countries;
	}
	
	public ArrayList<Label> getLabelsFromQuery(ArrayList<ArrayList<byte[]>> queryResult) throws ClassNotFoundException, SQLException{
		
		logger.debug("queryResult: " + queryResult);
		ArrayList<Label> labels = new ArrayList<Label>();
		Label labels_temp = new Label();
		
		final Integer LABEL = 0;
		
		JavaHelpUtils jhu = new JavaHelpUtils();
		
		for(ArrayList<byte[]> row : queryResult){
			labels_temp.setLABEL(row.get(LABEL));
			labels_temp.setSTRLABEL();

			labels.add((Label) jhu.deepClone(labels_temp));
		}
		
		return labels;
	}	
	
}
