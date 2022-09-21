<%@include file="../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <a href="../index.htm">Option Tables</a> | <a href="employee.form">New Employee</a> | <a href="employee.list">Employee List</a><br/><br/>
                <%@include file="../template/message.jspf" %>
                <div class="row">
                    <div class="col-lg-10">
                        <form:form commandName="item">
                            <form:hidden path="version"/>
                            <form:hidden path="employeeId"/>
                            <%@include file="../template/formState.jspf" %>
                            <div class="form-group">
                                <label>Full Name</label>
                                <form:input path="name" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="name" class="alert-danger"/>
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Phone Number</label>
                                <form:input path="contact" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="contact"/>
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Address</label>
                                <form:input path="address" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="address"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Gender</label>
                                <form:input path="gender" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="gender"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Age</label>
                                <form:input path="age" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="age"/>
                                </p>
                            </div>

                            <div class="form-group">
                                <label>Status</label><br/>
                                <form:radiobutton path="status" label="Married" value="true"/>
                                <form:radiobutton path="status" label="Single" value="false"/>
                            </div> 
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Save</button>
                                <a href="${itemDelete}"><button class="btn btn-primary" type="button" style="color:red">Cancel</button></a>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../template/footer.jspf" %>