<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Condition</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesCondition}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesCondition}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesCondition}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesCondition}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="condition" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${condition.id}" /></td>
                <td><c:out value="${condition.name}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${condition.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${condition.updated}" /></td>
                <td class="right"><a class="btn-floating color-icon" href="${pagesCondition}/${condition.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesCondition}/${condition.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating color-icon-delete" href="${pagesCondition}/${condition.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesCondition}/add"><i class="material-icons">add</i></a>
</sec:authorize>