<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Payment method</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesPaymentMethod}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPaymentMethod}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPaymentMethod}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesPaymentMethod}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="paymentMethod" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${paymentMethod.id}" /></td>
                <td><c:out value="${paymentMethod.name}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${paymentMethod.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${paymentMethod.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesPaymentMethod}/${paymentMethod.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesPaymentMethod}/${paymentMethod.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesPaymentMethod}/${paymentMethod.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesPaymentMethod}/add"><i class="material-icons">add</i></a>
