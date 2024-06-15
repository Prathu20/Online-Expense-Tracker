<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="Component/all_css.jsp"%>

<style type="text/css">
.card-sh {
	box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.3);
}
</style>

</head>
<body class="bg-light">
	<%@include file="Component/navbar.jsp"%>
	<div class="container p-5">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card card-sh">
					<div class="card-header">
						<p class="text-center text-success fs-4">
							<i class="fa-solid fa-user-plus"></i> Register Page
						</p>
						<c:if test="${not empty msg }">
							<p class="text-center text-success fs-3">${msg }</p>
							<c:remove var="msg" />
							<div class="text-center mt-2">
							Login <a href="login.jsp" class="text-decoration-none">Click here</a>
							</div>
						</c:if>

					</div>
					<div class="card-body">
						<form action="userRegister" method="post">
							<div class="mb-3">
								<label>Enter Full Name</label> <input type="text"
									name="fullname" class="form-control">
							</div>

							<div class="mb-3">
								<label>Email</label> <input type="email" name="email"
									class="form-control">
							</div>

							<div class="mb-3">
								<label>Password</label> <input type="password" name="password"
									class="form-control">
							</div>

							<div class="mb-3">
								<label>About</label> <input type="text" name="about"
									class="form-control">
							</div>
							<button class="btn btn-success col-md-12">Register</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="Component/footer.jsp"%>
</body>
</html>