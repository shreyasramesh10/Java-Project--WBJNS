<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WBJNS - Java Project</title>
</head>
<body>
<div class="container-fluid">
<h1 class = "text-center" style = "font-family: Monospace">Web Based Recruitment System</h1>
<div class = "well">
		<form action="JobServlet">	
				<div class = "row">
				<div class="col-md-2"><label>First Name</label><input class = "form-control" type = "text" name="firstName" required/></div>
				<div class="col-md-2"><label>Last Name</label><input class = "form-control" type = "text" name="lastName" required/></div>
				<div class="col-md-2"><label>Email Id</label><input class = "form-control" type = "text" name="emailId" required/></div>
				<div class="col-md-2"><label>Phone Number</label><input class = "form-control" type = "text" name="phoneNumber" /></div>
				</div>
				<div class = "row">
				<div class="col-md-4"><label>Experience</label><input class = "form-control" type = "text" name="experience" /></div>
				<div class="col-md-4"><label>Skills</label><input class = "form-control" type = "text" name="skills" /></div>
				<div class = "col-md-4"><button class="btn btn-primary" type="submit" value = "submit">Submit<i class="fa fa-paper-plane"></i></button></div>
				</div>				
		</form>
		</div>
</div>
</body>
</html>