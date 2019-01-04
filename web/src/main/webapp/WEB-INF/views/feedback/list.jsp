<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<h4 class="header">Feedback</h4>
<table class="bordered highlight">
    <tbody>
        <tr>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="id">id</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="item_id">item</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="user_from_id">from</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="user_whom_id">whom</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="communication">communication</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="shipping_time">shipping time</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="shipping_charges">shipping charges</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="item_description">item description</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="comment">comment</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="created">created</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="updated">updated</mytaglib:sort-link></th>
            <th></th>
        </tr>
        <c:forEach var="feedback" items="${gridItems}" varStatus="loopCounter">
            <tr class="font">
                <td><c:out value="${feedback.id}" /></td>
                <td><c:out value="${feedback.itemName}" /></td>
                <td><c:out value="${feedback.userFromEmail}" /></td>
                <td><c:out value="${feedback.userWhomEmail}" /></td>
                <td><c:out value="${feedback.communication}" /></td>
                <td><c:out value="${feedback.shippingTime}" /></td>
                <td><c:out value="${feedback.shippingCharges}" /></td>
                <td><c:out value="${feedback.itemDescription}" /></td>
                <td><c:out value="${feedback.comment}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${feedback.created}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${feedback.updated}" /></td>
                <td class="right"><a class="btn-floating" href="${pagesFeedback}/${feedback.id}"><i class="material-icons">info</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating" href="${pagesFeedback}/${feedback.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating red" href="${pagesFeedback}/${feedback.id}/delete"><i class="material-icons">delete</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jspFragments:paging />
<sec:authorize access="hasRole('admin')">
   <a class="waves-effect waves-light btn right" href="${pagesFeedback}/add"><i class="material-icons">add</i></a>
</sec:authorize>