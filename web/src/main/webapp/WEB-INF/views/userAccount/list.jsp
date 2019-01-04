<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">User account</h4>

<div class="row">
    <div class="col s12 m10">
        <div class="card-panel blue lighten-5">
            <div class="row">
                <form:form method="POST" action="${pagesUserAccount}" modelAttribute="searchFormModel">
                    <div class="input-field col s4">
                        <form:input path="email" type="text" />
                        <label for="name">email</label>
                    </div>
                    <div class="col s4">
                        <button class="btn waves-effect waves-light right" type="submit">search</button>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>  

<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="email">email</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="password">password</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="role">role</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesUserAccount}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
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
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesUserAccount}/add"><i class="material-icons">add</i></a>
