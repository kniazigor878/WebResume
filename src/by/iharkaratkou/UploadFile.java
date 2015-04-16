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

@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	String saveFile = "d:/Eclipse_Workspace_luna/upload";
	String webPass = "";
	String webID = "";
	String genOrUpd = "";

	public ArrayList<String> processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
						System.out.println(filename);
						File f = checkExist(filename);
						item.write(f);
						filenameTimestamps.add(f.getName());
					}
				}
			}

		} catch (Exception e) {

		} finally {
			out.close();
		}
		return filenameTimestamps;
	}

	private File checkExist(String fileName) {
		File f = new File(saveFile + "/" + fileName);
		// File f = new File("d:\\eclipse_workspace\\upload\\LICENSE");
		System.out.println("fileName: " + saveFile + "/" + fileName);
		System.out.println("f.exists(): " + f.exists());

		if (f.exists()) {
			StringBuffer sb = new StringBuffer(fileName);
			System.out.println("sb: " + sb);
			sb.insert(sb.lastIndexOf("."), "-" + new Date().getTime());
			f = new File(saveFile + "/" + sb.toString());
			System.out.println("sb.toString(): " + sb.toString());
		}
		return f;
	}

	public static void openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop()
				: null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		ArrayList<String> filenameTimestamps = processRequest(request, response);
		System.out.println(filenameTimestamps);
		String resumeFile = "";
		ArrayList<String> labels = new ArrayList<String>();
		for (String filenameTimestamp: filenameTimestamps){
			if(filenameTimestamp.contains(".xlsx")){
				resumeFile = filenameTimestamp;
			}else{
				labels.add(filenameTimestamp);
			}
		}
		System.out.println(labels);
		System.out.println("webPass: " + webPass);
		System.out.println("webID: " + webID);
		System.out.println("genOrUpd: " + genOrUpd);
		
		boolean newWebResume = true;
		if(genOrUpd.equals("Generate WebResume")){
			newWebResume = true;
		}else if (genOrUpd.equals("Update WebResume")){
			newWebResume = false;
		}else{
			System.out.println("Error in WebResume type");
		}
		
		ExcelParser exlParser = new ExcelParser();
		Integer id_last_temp = exlParser.parseExcelToDatabase(webID,resumeFile,webPass,newWebResume);
		LabelsUploader lu = new LabelsUploader();
		lu.uploadLabels(labels, id_last_temp);
		URL myURL = new URL("http://localhost:8080/WebResume/FindResume/"
				+ id_last_temp);
		try {
			openWebpage(myURL.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public String getServletInfo() {
		return "Short description";
	}

}