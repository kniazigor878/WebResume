<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet"  media="all" href="CSS/test.css">
<script src="JS/test.js"></script>
<script src="JS/filedrag.js"></script>
</head>
<body>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1">Upload new resume</a></li>
			<li><a href="#tabs-2">Modify existing resume</a></li>
		</ul>
		<div id="tabs-1">
			<div class="downl_templ">
				<p class="center">
					Download WebResume template: <a
						href="Template/Template_resume.xlsx">Template_resume.xlsx</a>
				</p>
				<br>

				<p>
					1) Upload resume*: <input type="file" value="Upload resume:"
						name="file" />
				</p>
				<p>
					2) Set password*: <input type="text" name="password" />
				</p>
				<p>
					Confirm password*: <input type="text" name="conf_password" />
				</p>
				<p>3) Upload company labels:</p>
				<div>
					<fieldset>
						<legend>HTML File Upload</legend>

						<input type="hidden" id="MAX_FILE_SIZE" name="MAX_FILE_SIZE"
							value="300000" />

						<div>
							<label for="fileselect">Files to upload:</label> <input
								type="file" id="fileselect" name="fileselect[]"
								multiple="multiple" />
							<div id="filedrag">or drop files here</div>						
						</div>

						<!-- <div id="submitbutton">
							<button type="submit">Upload Files</button>
						</div> -->

					</fieldset>

					<div id="messages">
						<p>Status Messages</p>
					</div>

				</div>
				<p class="center">
					<input type="submit" value="Generate WebResume" /><br> <br>
					Your WebResume ID is:
				</p>
				<p>* - mandatory fields</p>
			</div>
		</div>
		<div id="tabs-2">
			<p>Morbi tincidunt, dui sit amet facilisis feugiat, odio metus
				gravida ante, ut pharetra massa metus id nunc. Duis scelerisque
				molestie turpis. Sed fringilla, massa eget luctus malesuada, metus
				eros molestie lectus, ut tempus eros massa ut dolor. Aenean aliquet
				fringilla sem. Suspendisse sed ligula in ligula suscipit aliquam.
				Praesent in eros vestibulum mi adipiscing adipiscing. Morbi
				facilisis. Curabitur ornare consequat nunc. Aenean vel metus. Ut
				posuere viverra nulla. Aliquam erat volutpat. Pellentesque
				convallis. Maecenas feugiat, tellus pellentesque pretium posuere,
				felis lorem euismod felis, eu ornare leo nisi vel felis. Mauris
				consectetur tortor et purus.</p>
		</div>
	</div>
</body>
</html>