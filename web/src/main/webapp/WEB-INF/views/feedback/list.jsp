<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h4 class="header"><mytaglib:i18n key="section.feedback"></mytaglib:i18n></h4>

<c:if test="${showSomeElements eq 'false'}">
<div class="row">
<nav class="search-from-db">
	<div class="nav-wrapper">
		<form:form method="POST" action="${pagesFeedback}" modelAttribute="searchFormModel" id="form">
			<div class="input-field">
				<form:input path="userWhomEmail" type="search" />
				<label class="label-icon" for="search">
				<i class="material-icons">search</i></label>
				<button class="button-reset" type="button" onclick="form.reset()"><i class="material-icons" >close</i></button>				
			</div>
		</form:form>
	</div>
</nav>
</div>
</c:if>

<table class="bordered highlight">
    <tbody>
     <c:if test="${showPagingAndSort eq 'false'}">
         <tr>
            <th>item name</th>
            <th>seller</th>
            <th>comment</th>
            <th>created</th>
            <th></th>
         </tr>
      </c:if>
    
        <c:if test="${showPagingAndSort}"><tr class="font-my-set">
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="item_id"><mytaglib:i18n key="table.column.item-name"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="user_whom_id"><mytaglib:i18n key="table.column.seller"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="comment"><mytaglib:i18n key="table.column.comment"></mytaglib:i18n></mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="created"><mytaglib:i18n key="table.column.created"></mytaglib:i18n></mytaglib:sort-link></th>
            
            <sec:authorize access="hasAnyRole('admin', 'moderator')">
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="user_from_id">from</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="updated">updated</mytaglib:sort-link></th>
            <th><mytaglib:sort-link pageUrl="${pagesFeedback}" column="id">ID</mytaglib:sort-link></th>
            </sec:authorize>
            <th></th>
        </tr></c:if>
        
        <c:forEach var="feedback" items="${gridItems}" varStatus="loopCounter">
            <tr class="font-my-set">
                <td><c:out value="${feedback.itemName}" /></td>
                <td><c:out value="${feedback.userWhomEmail}" /></td>
                <td><c:out value="${feedback.comment}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${feedback.created}" /></td>
                
                <sec:authorize access="hasAnyRole('admin', 'moderator')">
                <td><c:out value="${feedback.userFromEmail}" /></td>
                <td class="no-transfer"><fmt:formatDate pattern="yyyy-MM-dd" value="${feedback.updated}" /></td>
                <td><c:out value="${feedback.id}" /></td>
                </sec:authorize>
                
                <td class="right"><a class="btn-floating color-icon" href="${pagesFeedback}/${feedback.id}"><i class="material-icons">pageview</i></a> 
                
                <sec:authorize access="hasRole('admin')">
                <a class="btn-floating color-icon" href="${pagesFeedback}/${feedback.id}/edit"><i class="material-icons">edit</i></a> 
                <a class="btn-floating color-icon-delete" href="${pagesFeedback}/${feedback.id}/delete"><i class="material-icons">delete_forever</i></a>
                </sec:authorize></td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<c:if test="${showPagingAndSort}">
<jspFragments:paging />
</c:if>

<sec:authorize access="hasRole('admin')">
<a class="waves-effect waves-light btn right" href="${pagesFeedback}/add"><i class="material-icons">add</i></a>
</sec:authorize>