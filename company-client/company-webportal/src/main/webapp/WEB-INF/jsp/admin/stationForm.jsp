<%@include file="../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <a href="../index.htm">Option Tables</a> | <a href="station.form">New Station</a> | <a href="station.list">Station List</a>
                <hr/>
                <%@include file="../template/message.jspf" %>
                <div class="row">
                    <div class="col-lg-10">
                        <form:form commandName="item">
                            <form:hidden path="version"/>
                            <form:hidden path="stationId"/>
                            <c:if test="${not empty item.stationId}">
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
                                <label>Station Code</label>
                                <form:input path="stationCode" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="stationCode"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Station Category:</label>
                                <form:select path="category" class="form-control">
                                    <form:options items="${stationCategories}" itemLabel="name" cssClass="txtbox" itemValue="stationCategoryId" />
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="category"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Province:</label>
                                <form:select path="province" class="form-control">
                                    <form:options items="${provinces}" itemLabel="name" cssClass="txtbox" itemValue="provinceId" />
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="province"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>District:</label>
                                <form:select path="district" class="form-control">
                                    <form:options items="${districts}" itemLabel="name" cssClass="txtbox" itemValue="districtId" />
                                </form:select>
                                <p class="help-block">
                                    <form:errors path="district"/>
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