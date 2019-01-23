<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">User account</h4>

<div class="row">
<nav class="search-from-db">
	<div class="nav-wrapper">
		<form:form method="POST" action="${pagesUserAccount}" modelAttribute="searchFormModel" id="form">
			<div class="input-field">
				<form:input path="email" type="search" id="search-item"/>
				<label class="label-icon" for="search">
				<i class="material-icons">search</i></label>
				<button class="button-reset" type="button" onclick="form.reset()"><i class="material-icons" >close</i></button>				
			</div>
		</form:form>
	</div>
</nav>
</div>

<table class="bordered highlight">
    <tbody>
         <c:if test="${showPagingAndSort eq 'false'}">
         <tr>
            <th>id</th>
            <th>email</th>
            <th>password</th>
            <th>role</th>
            <th>created</th>
            <th>updated</th>
            <th></th>
         </tr>
         </c:if>
        
        <c:if test="${showPagingAndSort}"><tr>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="email">email</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="password">password</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="role">role</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr></c:if>
        
        <c:forEach var="userAccount" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${userAccount.id}" /></td>
                <td><c:out value="${userAccount.email}" /></td>
                <td><c:out value="${userAccount.password}" /></td>
                <td><c:out value="${userAccount.role}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${userAccount.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${userAccount.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}"><i class="material-icons">info</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating red" href="${pagesUserAccount}/${userAccount.id}/delete"><i class="material-icons">delete</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${showPagingAndSort}">
    <jspFragments:paging />
</c:if>

<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesUserAccount}/add"><i class="material-icons">add</i></a>
</sec:authorize>
