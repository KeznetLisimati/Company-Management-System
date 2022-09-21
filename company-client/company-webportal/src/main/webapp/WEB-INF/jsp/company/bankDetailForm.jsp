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
                            <form:hidden path="companyBankDetailId"/>
                            <c:if test="${not empty item.companyBankDetailId}">
                                <form:hidden path="dateCreated"/>
                                <%--<form:hidden path="createdBy" value="${item.createdBy.username}"/>--%>
                            </c:if>
                            <div class="form-group">
                                <label>Bank</label>
                                <form:input path="bank" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="bank" class="alert-danger"/>
                                </p>
                            </div> 
                            <div class="form-group">
                                <label>Branch</label>
                                <form:input path="branch" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="branch" class="alert-danger"/>
                                </p>
                            </div>                                 
                           <div class="form-group">
                                <label>Account Type</label>
                                <form:select path="accountType" class="form-control">
                                    <form:option value="" label="--Select Item"/>
                                    <form:options items="${accountType}" itemValue="accountTypeId"/>
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="accountType" class="alert-danger"/>
                                </p>
                            </div> 
                            <div class="form-group">
                                <label>Account Name</label>
                                <form:input path="accountName" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="accountName" class="alert-danger"/>
                                </p>
                            </div>  
                            <div class="form-group">
                                <label>Account Number</label>
                                <form:input path="accountNumber" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="accountNumber" class="alert-danger"/>
                                </p>
                            </div>       
                            <div class="form-group">
                                <label>Status</label><br/>
                                <form:radiobutton path="status" label="Active" value="true"/>
                                <form:radiobutton path="status" label="In Active" value="false"/>
                            </div> 
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Save</button>
                                <a href="${itemDelete}"><button class="btn btn-primary" type="button"><span style="color:red">Cancel</span></button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../template/footer.jspf" %>