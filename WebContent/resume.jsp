<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<c:if test="${exps.size() > 0 == true}">
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
</c:if>

<c:if test="${certs.size() > 0 == true}">
<div id="Certifications">
	<h2><c:out value = "Certification"/></h2>
	<c:forEach var="cert" items="${certs}">
		<h3><c:out value = "${cert.getCERT_NAME()}"/></h3>
		<p class="date"><c:out value = "${cert.getCERT_DATE()}"/></p>
	</c:forEach>
</div>
</c:if>
<c:if test="${educs.size() > 0 == true}">
<div id="Educations">
	<h2><c:out value = "Education"/></h2>
	<c:forEach var="educ" items="${educs}">
		<h3><c:out value = "${educ.getDIPLOMA()}"/></h3>
		<h4><c:out value = "${educ.getEDUC_CENTER()}"/></h4>
		<p class="date"><c:out value = "${educ.getEDUC_PERIOD()}"/></p>
	</c:forEach>	
</div>
</c:if>

<c:if test = "${labels.size() > 0 == true || vis_countries.size() > 0 == true}">
<div id="Additional_info">
	<h2><c:out value = "Additional Information"/></h2>
	<c:if test = "${labels.size() > 0 == true}">
	<div id="Clients">
		<h3><c:out value = "Clients"/></h3>
		<p class="additional_info">Some clients ${gd.getNAME_CAMEL_CASE()} had a pleasure working with:</p>
		<div class="Client_labels">
		<c:forEach var="label" items="${labels}">
			<img src="data:image/png;base64,${label.getSTRLABEL() }" width="200" height="140" alt="Company label" title="Company label" class="flag" />
		</c:forEach>
		</div>
	</div>
	</c:if>
	<c:if test = "${vis_countries.size() > 0 == true}">
	<div id="Countries">
		<h3><c:out value = "Countries"/></h3>
		<p class="additional_info">Countries where ${gd.getNAME_CAMEL_CASE()} managed onsite or remote projects:</p>
		<div class="flags">
		<c:forEach var="vis_country" items="${vis_countries}">
			<img src="data:image/gif;base64,${vis_country.getSTRFLAG() }" width="100" height="70" alt="${vis_country.getCOUNTRY_NAME() }" title="${vis_country.getCOUNTRY_NAME() }" class="flag" />
		</c:forEach>
		</div>
	</div>
	</c:if>
	<br><br>
</div>
</c:if>
<div id="Links">
		<a href="http://${gd.getSN_LINKEDIN()}" target="_blank"><img src="../labels/linked.png" width="80" height="80" alt="View Profile on LinkedIn" title="View Profile on LinkedIn" class="flag"/></a>
	    <a href="http://${gd.getSN_TWITTER()}" target="_blank"><img src="../labels/twitter.png" width="80" height="80" alt="Follow on Twitter" title="Follow on Twitter" class="flag" /></a>
  	  	<a href="#" onClick="window.print()"><img src="../labels/print.png" width="80" height="80" alt="Print This Page" title="Print This Page" class="flag" /></a>
</div>
</body>
</html>