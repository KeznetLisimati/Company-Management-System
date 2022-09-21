<%@include file="../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <a href="../index.htm">Option Tables</a> | <a href="institution.form">New Institution</a> | <a href="institution.list">Institution List</a>
                <hr/>
                <%@include file="../template/message.jspf" %>
                <div class="row">
                    <div class="col-lg-10">
                        <form:form commandName="item">
                            <form:hidden path="version"/>
                             <form:hidden path="institutionId"/>
                            <c:if test="${not empty item.institutionId}">
                                <form:hidden path="dateCreated"/>
                                <form:hidden path="createdBy" value="${item.createdBy.id}"/>
                            </c:if>
                            <div class="form-group">
                                <label>Name</label>
                                <form:input path="name" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="name" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <form:textarea path="description" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="description"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Country:</label>
                                <form:select path="country">
                                    <form:options items="${country}" itemLabel="name" cssClass="txtbox" itemValue="countryId" />
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="country"/>
                                </p>
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