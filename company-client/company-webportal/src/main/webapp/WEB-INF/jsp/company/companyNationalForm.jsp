<%@include file="../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <%@include file="../template/message.jspf" %>
                <div class="row">
                    <div class="col-lg-10">
                        <form:form modelAttribute="company">
                            <form:hidden path="companyId"/>
                            <div class="form-group">
                                <label>Name</label>
                                <form:input path="name" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="name" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Company No</label>
                                <form:input path="companyNo" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="companyNo" class="alert-danger"/>
                                </p>
                            </div>    

                            <div class="form-group">
                                <label>Contact Person</label>
                                <form:input path="contactPerson" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="contactPerson" class="alert-danger"/>
                                </p>
                            </div>
                             <div class="form-group">
                                <label>Company Type</label>
                                <form:input path="companyType" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="companyType" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Industry</label>
                                <form:input path="industry" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="industry" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>District</label>
                                <form:input path="district" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="district" class="alert-danger"/>
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Province</label>
                                <form:input path="province" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="province" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Country</label>
                                <form:input path="country" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="country" class="alert-danger"/>
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Start Date</label>
                                <form:input path="startDate" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="startDate" class="alert-danger"/>
                                </p>
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