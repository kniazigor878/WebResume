package by.iharkaratkou;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.SQLException;
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

import by.iharkaratkou.dto.DBUtils;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet/*")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	String saveFile = "d:/eclipse_workspace/upload";
	
	public String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String filenameTimestamp = "";

		try {
			boolean ismultipart = ServletFileUpload.isMultipartContent(request);
			if (!ismultipart) {
				System.out.println("ismultipart is false");
			} else {
				System.out.println("ismultipart is true");
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
						if(f.getName().contains(".xlsx")){
							filenameTimestamp = f.getName();
						}
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
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String text = "some text Ihar";
		 String url = request.getRequestURL().toString();
		 System.out.println("url " + url);
		 String[] urlParts = url.split("/");
		 String id = urlParts[5];
		 System.out.println("id " + id);
		 
		 DBUtils dbu = new DBUtils();
		 Integer isIDexist = 0;
		 try {
			 isIDexist = dbu.checkID(Integer.valueOf(id));
			 System.out.println("isIDexist " + isIDexist);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		 System.out.println("isIDexist " + isIDexist);
		    response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
		    response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
		    response.getWriter().write(isIDexist.toString());       // Write response body.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*System.out.println("doPost");
		String filenameTimestamp = processRequest(request, response);
		System.out.println("filenameTimestamp: " + filenameTimestamp);*/
	}

}
