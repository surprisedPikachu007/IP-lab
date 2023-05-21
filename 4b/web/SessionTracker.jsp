<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Session Count by Times of the Day</title>
</head>
<body>
	<%
		int morningCount = 0;
		int afternoonCount = 0;
		int eveningCount = 0;
		int nightCount = 0;

		if (session.getAttribute("morningCount") != null) {
			morningCount = (Integer) session.getAttribute("morningCount");
		}
		if (session.getAttribute("afternoonCount") != null) {
			afternoonCount = (Integer) session.getAttribute("afternoonCount");
		}
		if (session.getAttribute("eveningCount") != null) {
			eveningCount = (Integer) session.getAttribute("eveningCount");
		}
		if (session.getAttribute("nightCount") != null) {
			nightCount = (Integer) session.getAttribute("nightCount");
		}
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);

		if (hour >= 6 && hour < 12) {
			morningCount++;
		} else if (hour >= 12 && hour < 18) {
			afternoonCount++;
		} else if (hour >= 18 && hour < 24) {
			eveningCount++;
		} else {
			nightCount++;
		}

		session.setAttribute("morningCount", 5);
		session.setAttribute("afternoonCount", 3);
		session.setAttribute("eveningCount", eveningCount);
		session.setAttribute("nightCount", 7);
	%>
	
	<h2>Session Count by Times of the Day</h2>
	<p>Morning (6:00 am - 11:59 am): <%= session.getAttribute("morningCount") %></p>
	<p>Afternoon (12:00 pm - 5:59 pm): <%= session.getAttribute("afternoonCount") %></p>
	<p>Evening (6:00 pm - 11:59 pm): <%= session.getAttribute("eveningCount") %></p>
	<p>Night (12:00 am - 5:59 am): <%= session.getAttribute("nightCount") %></p>
</body>
</html>
