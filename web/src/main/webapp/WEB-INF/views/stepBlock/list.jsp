<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Step blocks</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="step_1">step 1</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="step_2">step 2</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="step_3">step 3</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="step_4">step 4</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="step_5">step 5</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesStepBlock}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="stepBlock" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${stepBlock.id}" /></td>
                <td><c:out value="${stepBlock.name}" /></td>
                <td><c:out value="${stepBlock.step_1}" /></td>
                <td><c:out value="${stepBlock.step_2}" /></td>
                <td><c:out value="${stepBlock.step_3}" /></td>
                <td><c:out value="${stepBlock.step_4}" /></td>
                <td><c:out value="${stepBlock.step_5}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${stepBlock.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${stepBlock.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesStepBlock}/${stepBlock.id}"><i class="material-icons">info</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating" href="${pagesStepBlock}/${stepBlock.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating red" href="${pagesStepBlock}/${stepBlock.id}/delete"><i class="material-icons">delete</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesStepBlock}/add"><i class="material-icons">add</i></a>
</sec:authorize>