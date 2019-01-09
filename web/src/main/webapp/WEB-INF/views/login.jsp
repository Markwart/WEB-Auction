<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>

<h4 class="center"><mytaglib:i18n key="login.header"></mytaglib:i18n></h4>
<div class="row center-in-window">
	<div class="col s3"></div>
	<div class="col s6">
		<form name='loginForm' action="<c:url value='login' />" method='POST'>
			<div class="row">
				<div class="input-field col s12 center">
					<input type='text' name='username' value=''> <label
						for="username"><mytaglib:i18n key="email"></mytaglib:i18n></label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12 center">
					<input type='password' name='password' /><label for="password"><mytaglib:i18n key="password"></mytaglib:i18n></label>
				</div>
			</div>
			<c:if test="${not empty error}">
				<div class="row">
					<div class="col s12 center">
						<div class="error">${error}</div>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty msg}">
				<div class="row">
					<div class="col s12 center">
						<div class="msg">${msg}</div>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="col s12 center">
					<button class="btn waves-effect waves-light " type="submit"><mytaglib:i18n key="login"></mytaglib:i18n></button>

				</div>
			</div>
			<div class="row">
				<div class="col s12 center">
						<a class="btn waves-effect waves-light " href="${pagesUserAccount}/add"><mytaglib:i18n key="registration"></mytaglib:i18n></a>
				</div>
			</div>
		</form>
	</div>
	<div class="col s3"></div>
</div>