package by.iharkaratkou;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import by.iharkaratkou.javaUtils.JavaHelpUtils;

/**
 * This class upload received files in special folder, calls method to parse and write
 * received info in database and open link to render new/updated WebResume
 * 
 * @author Ihar Karatkou
 * @version 1.0
 * @since 2015-04-20
*/
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UploadFile.class);

	String webPass = "";
	String webID = "";
	String genOrUpd = "";
	
	/**
	 * This method search uploadFolder path in properties file and return it.
	 * 
	 * @return uploadFolder
	 */
	private String getUploadPath(){
		Properties prop = new Properties();
		try {
			prop.load(getServletContext().getResourceAsStream("/WEB-INF/variables.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//prop.getProperty("uploadFolder");
		return prop.getProperty("uploadFolder");
	}
	
	/**
	 * This method uploads received from html-form files and returns List of its names. Also it sets
	 * webPass, webID and action (insert/update) from html-form.
	 * @param request
	 * @param response
	 * @param saveFile
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ArrayList<String> processRequest(HttpServletRequest request,
			HttpServletResponse response, String saveFile) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ArrayList<String> filenameTimestamps = new ArrayList<String>();

		try {
			boolean ismultipart = ServletFileUpload.isMultipartContent(request);
			if (!ismultipart) {
				
			} else {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = null;

				try {

					items = upload.parseRequest(request);
				} catch (Exception e) {
				}
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						String fieldname = item.getFieldName();
						if(fieldname.equals("password")){
							webPass = item.getString();
						}
						if(fieldname.equals("web_id")){
							webID = item.getString();
						}
						if(fieldname.equals("show_button")){
							genOrUpd = item.getString();
						}	
					} else {
						String itemname = item.getName();
						if ((itemname == null || itemname.equals(""))) {
							continue;
						}
						String filename = FilenameUtils.getName(itemname);
						File f = checkExist(filename,saveFile);
						item.write(f);
						filenameTimestamps.add(f.getName());
					}
				}
			}

		} catch (Exception e) {
			logger.error(e);
		} finally {
			out.close();
		}
		return filenameTimestamps;
	}
	
	/**
	 * This method check if file with name = fileName exists in folder saveFile and if it exists
	 *  - adds timestamp to fileName.
	 *   
	 * @param fileName
	 * @param saveFile
	 * @return File
	 */
	private File checkExist(String fileName, String saveFile) {
		File f = new File(saveFile + fileName);
		if (f.exists()) {
			StringBuffer sb = new StringBuffer(fileName);
			sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
			f = new File(saveFile + sb.toString());
		}
		return f;
	}
	
	/**
	 * This method open web-page in new tab by requested URI.
	 *  
	 * @param uri
	 */
	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				logger.error(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}
	
	/**
	 * This method handles POST requests, calls methods to write received data in database
	 *  and open link with inserted/updated WebResume.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String saveFile = getUploadPath(); 
		ArrayList<String> filenameTimestamps = processRequest(request, response, saveFile);
		logger.info("Uploaded file name is " + filenameTimestamps);
		String resumeFile = "";
		ArrayList<String> labels = new ArrayList<String>();
		for (String filenameTimestamp: filenameTimestamps){
			if(filenameTimestamp.contains(".xlsx")){
				resumeFile = filenameTimestamp;
			}else{
				labels.add(filenameTimestamp);
			}
		}
		logger.debug("array of labels: " + labels);
		logger.debug("webID: " + webID);
		logger.debug("webPass: " + webPass);
		logger.debug("genOrUpd: " + genOrUpd);
		
		boolean newWebResume = true;
		if(genOrUpd.equals("Generate WebResume")){
			newWebResume = true;
		}else if (genOrUpd.equals("Update WebResume")){
			newWebResume = false;
		}else{
			logger.info("Error in WebResume type");
		}
		
		ExcelParser exlParser = new ExcelParser();
		Integer id_last_temp = exlParser.parseExcelToDatabase(webID,resumeFile,webPass,newWebResume,saveFile);
		LabelsUploader lu = new LabelsUploader();
		lu.uploadLabels(labels, id_last_temp, saveFile);
		URL myURL = new URL(request.getRequestURL().toString().replaceAll("UploadFile", "FindResume/")
				+ id_last_temp + "?alert=yes");
		logger.debug("myURL: " + myURL);
		try {
			openWebpage(myURL.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}

	public String getServletInfo() {
		return "Short description";
	}

}