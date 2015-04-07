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
	function Output(msg) {
		var m = document.getElementById("messages");
		m.innerHTML = msg + m.innerHTML;
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
		FileDragHover(e);

		// fetch FileList object
		var files = e.target.files || e.dataTransfer.files;

		// process all File objects
		for (var i = 0, f; f = files[i]; i++) {
			ParseFile(f);
		}

	}


	// output file information
	function ParseFile(file) {

		Output(
			"<p>File information: <strong>" + file.name +
			"</strong> type: <strong>" + file.type +
			"</strong> size: <strong>" + file.size +
			"</strong> bytes</p>"
		);

	}
	
	

	// initialize
	function Init() {
		//alert("inside init()");
		var fileselect = document.getElementById("fileselect");
		var filedrag = document.getElementById("filedrag");
		var	submitbutton = document.getElementById("submitbutton");
		
		//alert(fileselect);
		//alert(filedrag);
		//alert(submitbutton);
		// file select
		fileselect.addEventListener("change", FileSelectHandler, false);
		// is XHR2 available?
		var xhr = new XMLHttpRequest();
		if (xhr.upload) {
			// file drop
			filedrag.addEventListener("dragover", FileDragHover, false);
			filedrag.addEventListener("dragleave", FileDragHover, false);
			filedrag.addEventListener("drop", FileSelectHandler, false);
			filedrag.style.display = "block";
			// remove submit button
			submitbutton.style.display = "none";
		}else{
			alert("not xhr.upload");
		}

	}

	// call initialization file
	if (window.File && window.FileList && window.FileReader) {
		//alert("init()");
		Init();
	}


})();