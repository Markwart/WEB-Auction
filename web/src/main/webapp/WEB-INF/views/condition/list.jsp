<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">Condition</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>created</th>
            <th>updated</th>
            <th></th>
        </tr>
        <c:forEach var="condition" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${condition.id}" /></td>
                <td><c:out value="${condition.name}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${condition.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${condition.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesCondition}/${condition.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesCondition}/${condition.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesCondition}/${condition.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesCondition}/add"><i class="material-icons">add</i></a>
