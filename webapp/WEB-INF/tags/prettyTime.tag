<%@attribute name="time" required="true" type="java.util.Date"%>
<%
	org.ocpsoft.prettytime.PrettyTime formatter = (org.ocpsoft.prettytime.PrettyTime) request
			.getAttribute("prettyTimeFormatter");
	out.write(formatter.format(time));
%>
