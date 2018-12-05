<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Shipping method</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="name">name</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="delivery_time">delivery time</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="cost">cost</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesShippingMethod}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="shippingMethod" items="${gridItems}" varStatus="loopCounter">
            <tr>
                <td><c:out value="${shippingMethod.id}" /></td>
                <td><c:out value="${shippingMethod.name}" /></td>
                <td><c:out value="${shippingMethod.deliveryTime}" /></td>
                <td><c:out value="${shippingMethod.cost}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${shippingMethod.created}" /></td>
                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${shippingMethod.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesShippingMethod}/${shippingMethod.id}"><i class="material-icons">info</i></a> <a
                    class="btn-floating" href="${pagesShippingMethod}/${shippingMethod.id}/edit"><i class="material-icons">edit</i></a> <a
                    class="btn-floating red" href="${pagesShippingMethod}/${shippingMethod.id}/delete"><i class="material-icons">delete</i></a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<a class="waves-effect waves-light btn right" href="${pagesShippingMethod}/add"><i class="material-icons">add</i></a>
