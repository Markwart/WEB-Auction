<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

<h4 class="header">Edit feedback</h4>
<div class="row">
	<form:form class="col s12" method="POST" action="${pagesFeedback}/add"
		modelAttribute="formModel">

		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:select path="itemId" disabled="${readonly}">
					<form:options items="${itemsChoices}" />
				</form:select>
				<form:errors path="itemId" cssClass="red-text" />
				<label for="itemId">item</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userFromId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userFromId" cssClass="red-text" />
				<label for="userFromId">from</label>
			</div>
		</div>
		
		<div class="row">
			<div class="input-field col s12">
				<form:select path="userWhomId" disabled="${readonly}">
					<form:options items="${userAccountsChoices}" />
				</form:select>
				<form:errors path="userWhomId" cssClass="red-text" />
				<label for="userWhomId">whom</label>
			</div>
		</div>
		
		<span class="feedback-name">Communication:</span>
	  <div class="row">
	  <div class="star-rating">
      <div class="star-rating__wrap">
        <input class="star-rating__input" id="star-rating-5-0" type="radio" name="communication" value="5"<%--  disabled="${readonly} --%>">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5-0" title="5 out of 5 stars"></label>
        
        <input class="star-rating__input" id="star-rating-4-0" type="radio" name="communication" value="4" <%-- disabled="${readonly} --%>">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4-0" title="4 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-3-0" type="radio" name="communication" value="3" <%-- disabled="${readonly} --%>">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3-0" title="3 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-2-0" type="radio" name="communication" value="2" <%-- disabled="${readonly} --%>">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2-0" title="2 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-1-0" type="radio" name="communication" value="1" <%-- disabled="${readonly} --%>">
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1-0" title="1 out of 5 stars"></label>
      </div>
      </div>
	  </div>
	  
		<span class="feedback-name">Shipping Time:</span>
	  <div class="row">
	  <div class="star-rating">
      <div class="star-rating__wrap">
        <input class="star-rating__input" id="star-rating-5-1" type="radio" name="shippingTime" value="5" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5-1" title="5 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-4-1" type="radio" name="shippingTime" value="4" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4-1" title="4 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-3-1" type="radio" name="shippingTime" value="3" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3-1" title="3 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-2-1" type="radio" name="shippingTime" value="2" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2-1" title="2 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-1-1" type="radio" name="shippingTime" value="1" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1-1" title="1 out of 5 stars"></label>
      </div>
      </div>
	  </div>
		
		<span class="feedback-name">Shipping Charges:</span>
	  <div class="row">
	  <div class="star-rating">
      <div class="star-rating__wrap">
        <input class="star-rating__input" id="star-rating-5-2" type="radio" name="shippingCharges" value="5" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5-2" title="5 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-4-2" type="radio" name="shippingCharges" value="4" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4-2" title="4 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-3-2" type="radio" name="shippingCharges" value="3" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3-2" title="3 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-2-2" type="radio" name="shippingCharges" value="2" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2-2" title="2 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-1-2" type="radio" name="shippingCharges" value="1" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1-2" title="1 out of 5 stars"></label>
      </div>
      </div>
	  </div> 
		
		<span class="feedback-name">Item Description:</span>
	  <div class="row">
      <div class="star-rating">
      <div class="star-rating__wrap" >
        <input class="star-rating__input" id="star-rating-5-3" type="radio" name="itemDescription" value="5" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-5-3" title="5 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-4-3" type="radio" name="itemDescription" value="4" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-4-3" title="4 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-3-3" type="radio" name="itemDescription" value="3" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-3-3" title="3 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-2-3" type="radio" name="itemDescription" value="2" <%-- disabled="${readonly} --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-2-3" title="2 out of 5 stars"></label>
        <input class="star-rating__input" id="star-rating-1-3" type="radio" name="itemDescription" value="1" <%-- disabled="${readonly}" --%>>
        <label class="star-rating__ico fa fa-star-o fa-lg" for="star-rating-1-3" title="1 out of 5 stars"></label>
      </div>
      </div>
	  </div>
	
	<%-- <div class="row">
			<div class="input-field col s12">
				<form:input path="communication" type="text" disabled="${readonly}" />
				<form:errors path="communication" cssClass="red-text" />
				<label for="communication">communication</label>
			</div>
		</div> 
	
		<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingTime" type="text" disabled="${readonly}" />
				<form:errors path="shippingTime" cssClass="red-text" />
				<label for="shippingTime">shipping time</label>
			</div>
		</div>
	
	<div class="row">
			<div class="input-field col s12">
				<form:input path="shippingCharges" type="text" disabled="${readonly}" />
				<form:errors path="shippingCharges" cssClass="red-text" />
				<label for="shippingCharges">shipping charges</label>
			</div>
		</div> 
	
	 <div class="row">
			<div class="input-field col s12">
				<form:input path="itemDescription" type="text" disabled="${readonly}" />
				<form:errors path="itemDescription" cssClass="red-text" />
				<label for="itemDescription">item description</label>
			</div>
		</div> --%>
	
		<div class="row">
			<div class="input-field col s12">
				<form:textarea path="comment" type="text" disabled="${readonly}" />
				<form:errors path="comment" cssClass="red-text" />
				<label for="comment">comment</label>
			</div>
		</div>
		
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right color-button" href="${pagesFeedback}">list<i class="material-icons right"></i>
				</a>
			</div>
		</div>
	</form:form>
</div> 




