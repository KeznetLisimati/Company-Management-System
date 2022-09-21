<%@include file="../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <%@include file="../template/dashboard/companyProfile.jspf" %>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <%@include file="../template/message.jspf" %> 
                       
                        <form:form commandName="item">
                            <form:hidden path="version"/>
                            <form:hidden path="company" value="${company.companyId}"/>
                            <form:hidden path="companyAddressId"/>
                            <c:if test="${not empty item.companyAddressId}">
                                <form:hidden path="dateCreated"/>
                                <%--<form:hidden path="createdBy" value="${item.createdBy.username}"/>--%>
                            </c:if>
                          <div class="form-group">
                                <label>Address Type</label>
                                <form:select path="addressType" class="form-control">
                                    <form:option value="" label="--Select Item"/>
                                    <form:options items="${addressType}" itemValue="addressTypeId"/>
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="addressType" class="alert-danger"/>
                                </p>
                            </div>  
                            <div class="form-group">
                                <label>Address Detail</label>
                                <form:input path="addressDetail" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="addressDetail" class="alert-danger"/>
                                </p>
                            </div> 
                            <div class="form-group">
                                <label>Address Status</label><br/>
                                <form:radiobutton path="status" label="Active" value="true"/>
                                <form:radiobutton path="status" label="In Active" value="false"/>
                            </div> 
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Save</button>
                                <a href="${itemDelete}"><button class="btn btn-primary" type="button">Cancel</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../template/footer.jspf" %>