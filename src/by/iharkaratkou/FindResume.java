package by.iharkaratkou;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

/**
 * Servlet implementation class FindResume
 */
@WebServlet("/FindResume/*")
public class FindResume extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(FindResume.class);

	public static boolean isNumeric(String str) {
		try {
			Integer d = Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		String[] urlParts = url.split("/");
		logger.debug("View: " + urlParts[urlParts.length - 1]);
		String personID = urlParts[urlParts.length - 1];
		DBUtils dbu = new DBUtils();
		Transformer trans = new Transformer();
		try {
			if (this.isNumeric(personID)) {
				//System.out.println(dbu.selectGeneralData(Integer.valueOf(personID)));
				GeneralData gd = new GeneralData();
				Qualifications quals = new Qualifications();
				ArrayList<Experience> exps = new ArrayList<Experience>();
				ArrayList<Exp_activity> exp_acts = new ArrayList<Exp_activity>();
				ArrayList<Certification> certs = new ArrayList<Certification>();
				ArrayList<Education> educs = new ArrayList<Education>();
				ArrayList<Country> vis_countries = new ArrayList<Country>();
				ArrayList<Label> labels = new ArrayList<Label>(); 
				
				gd = trans.getGeneralDataFromQuery(dbu.selectGeneralData(Integer.valueOf(personID)));
				logger.debug("gd: " + gd.getNAME() + " " + gd.getSURNAME());
				quals = trans.getQualificationsFromQuery(dbu.selectQualifications(Integer.valueOf(personID)));
				exps = trans.getExperiencesFromQuery(dbu.selectExperiences(Integer.valueOf(personID)));
				exp_acts = trans.getExperienceAcrivitiesFromQuery(dbu.selectExpActivities(Integer.valueOf(personID)));
				certs = trans.getCertificationsFromQuery(dbu.selectCertificatins(Integer.valueOf(personID)));
				educs = trans.getEducationsFromQuery(dbu.selectEducations(Integer.valueOf(personID)));
				vis_countries = trans.getVisCountriesFromQuery(dbu.selectVisCountries(Integer.valueOf(personID)));
				labels = trans.getLabelsFromQuery(dbu.selectLabels(Integer.valueOf(personID)));
				
				request.setAttribute("gd", gd);
				request.setAttribute("quals", quals);
				request.setAttribute("exps", exps);
				request.setAttribute("exp_acts", exp_acts);
				request.setAttribute("certs", certs);
				request.setAttribute("educs", educs);
				request.setAttribute("vis_countries", vis_countries);
				request.setAttribute("labels", labels);
				
				RequestDispatcher rd = request.getRequestDispatcher("/resume.jsp");
				rd.forward(request, response);
				
			} else {
				logger.error("Person ID is not a number");
			}
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			logger.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
