package by.iharkaratkou;

import java.awt.Desktop;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
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

	String saveFile = "d:/eclipse_workspace/upload";

	protected String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String filenameTimestamp = "";

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

					} else {
						String itemname = item.getName();
						if ((itemname == null || itemname.equals(""))) {
							continue;
						}
						String filename = FilenameUtils.getName(itemname);
						System.out.println(filename);
						File f = checkExist(filename);
						item.write(f);
						filenameTimestamp = f.getName();
					}
				}
			}

		} catch (Exception e) {

		} finally {
			out.close();
		}
		return filenameTimestamp;
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
		String filenameTimestamp = processRequest(request, response);
		ExcelParser exlParser = new ExcelParser();
		Integer id_last_temp = exlParser.parseExcelToDatabase(filenameTimestamp);
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