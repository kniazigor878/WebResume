<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="../CSS/resume.css" />
</head>
<body>
<div id="General_info">
	<h1><c:out value = "${gd.getNAME()}"/> <c:out value = "${gd.getSURNAME()}"/></h1>
	<p class="title"><c:out value = "${gd.getCURRENT_POST()}"/><c:out value = " at "/><c:out value = "${gd.getCURRENT_COMPANY()}"/></p>
	<p class= "contact_info"><c:out value = "${gd.getCURRENT_LOCATION()}"/><c:out value = " | "/><c:out value = "${gd.getCURRENT_BUS_PHONE()}"/><br>
	<a href="mailto:${gd.getCURRENT_BUSINESS_MAIL()}"><c:out value = "${gd.getCURRENT_BUSINESS_MAIL()}"/></a></p>
</div>
<div id="Qualifications">
	<h2><c:out value = "Summary of Qualifications"/></h2>
	<UL>
	<c:forEach var="qual" items="${quals.getQualifications()}">
		<LI><c:out value = "${qual}"/></LI>
	</c:forEach>
	</UL>
</div>
<div id="Experience">
	<h2><c:out value = "Experience"/></h2>
	<c:forEach var="exp" items="${exps}">
		<h3><c:out value = "${exp.getPOSITION()}"/></h3>
		<h4><c:out value = "${exp.getCOMPANY()}"/></h4>
		<p class="date"><c:out value = "${exp.getPERIOD()}"/></p>
		<c:forEach var="exp_act" items="${exp_acts}">
			<c:if test="${exp.getID() == exp_act.getExp_ID()}">
				<UL class="description">
				<c:forEach var="act" items="${exp_act.getExp_activities()}">
					<LI class="description"><c:out value = "${act}"/></LI>
				</c:forEach>
				</UL>
			</c:if>
		</c:forEach>
	</c:forEach>
</div>
<div id="Certifications">
	<h2><c:out value = "Certification"/></h2>
	<c:forEach var="cert" items="${certs}">
		<h3><c:out value = "${cert.getCERT_NAME()}"/></h3>
		<p class="date"><c:out value = "${cert.getCERT_DATE()}"/></p>
	</c:forEach>
</div>
<div id="Educations">
	<h2><c:out value = "Education"/></h2>
	<c:forEach var="educ" items="${educs}">
		<h3><c:out value = "${educ.getDIPLOMA()}"/></h3>
		<h4><c:out value = "${educ.getEDUC_CENTER()}"/></h4>
		<p class="date"><c:out value = "${educ.getEDUC_PERIOD()}"/></p>
	</c:forEach>	
</div>
<div id="Additional_info">
	<h2><c:out value = "Additional Information"/></h2>
	<div id="Clients">
		<h3><c:out value = "Clients"/></h3>
		<p class="additional_info">Some clients ${gd.getNAME()} had a pleasure working with:</p>
	</div>
	<div id="Countries">
		<h3><c:out value = "Countries"/></h3>
		<p class="additional_info">Countries where ${gd.getNAME()} managed onsite or remote projects:</p>
		<div class="flags">
		<c:forEach var="vis_countrie" items="${vis_countries}">
			<img src="data:image/gif;base64,${vis_countrie.getSTRFLAG() }" width="100" height="70" alt="Australia" title="Australia" class="flag" />
		</c:forEach>
		</div>
	</div>
	<br><br>
	<div id="Links">
		<a href="http://${gd.getSN_LINKEDIN()}" target="_blank"><img src="../labels/linked.png" width="80" height="80" alt="View Profile on LinkedIn" title="View Profile on LinkedIn" class="noprint"/></a>
	    <a href="http://${gd.getSN_TWITTER()}" target="_blank"><img src="../labels/twitter.png" width="80" height="80" alt="Follow on Twitter" title="Follow on Twitter" class="noprint" /></a>
  	  	<a href="#" onClick="window.print()"><img src="../labels/print.png" width="80" height="80" alt="Print This Page" title="Print This Page" class="noprint" /></a>
	</div>
</div>
</body>
</html>