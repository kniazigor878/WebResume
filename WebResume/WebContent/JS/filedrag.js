/*
filedrag.js - HTML5 File Drag & Drop demonstration
Featured on SitePoint.com
Developed by Craig Buckler (@craigbuckler) of OptimalWorks.net
*/
(function() {

	// getElementById
	function $id(id) {
		return document.getElementById(id);
	}


	// output information
	function Output(msg,formNameAuto) {
		var m = document.getElementById("messages_" + formNameAuto);
		m.innerHTML = m.innerHTML + msg;
	}


	// file drag hover
	function FileDragHover(e) {
		//alert("FileDragHover");
		e.stopPropagation();
		e.preventDefault();
		e.target.className = (e.type == "dragover" ? "hover" : "");
	}


	// file selection
	function FileSelectHandler(e) {
		//alert("FileSelectHandler(e)");
		// cancel event and hover styling
		var $form = $(this).closest('form');
		//alert($form.attr('name'));
		var formNameAuto = $form.attr('name');
		//alert("inside FileSelectHandler " + formName);
		FileDragHover(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f,formNameAuto);
		}

	}


	// output file information
	function ParseFile(file,formNameAuto) {
		//alert("inside ParseFile " + formNameAuto);
		
		Output(
			"<p>File information: <strong>" + file.name +
			"</strong> type: <strong>" + file.type +
			"</strong></p>", formNameAuto
		);

	}
	
	

	// initialize
	function Init(formName) {
		//alert("inside init()");
		//alert(formName);
		var fileselect = document.getElementById("fileselect" + formName);
		/*var filedrag = document.getElementById("filedrag" + formName);*/
		//alert("1");
		
		//alert(fileselect);
		//alert(filedrag);
		//alert(submitbutton);
		// file select
		fileselect.addEventListener("change", FileSelectHandler, false);
		// is XHR2 available?
		/*var xhr = new XMLHttpRequest();
		if (xhr.upload) {
			// file drop
			filedrag.addEventListener("dragover", FileDragHover, false);
			filedrag.addEventListener("dragleave", FileDragHover, false);
			filedrag.addEventListener("drop", FileSelectHandler, false);
			filedrag.style.display = "block";
		}else{
			alert("not xhr.upload");
		}*/
	}

	// call initialization file
	if (window.File && window.FileList && window.FileReader) {
		//alert("init()");
		Init("_form1");
		Init("_form2");
	}


})();