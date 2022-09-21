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
                        <form:form commandName="item" action="${action}">
                            <form:hidden path="user" value="${item.user.username}"/>
                            <div class="form-group">
                                <label >Username:</label>
                                <label class="form-control">${item.user.username}</label>
                            </div> 
                            <div class="form-group">
                                <label>New Password:</label>
                                <form:password path="newPassword" class="form-control"  />
                                <p class="help-block">
                                    <form:errors path="newPassword" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <label>Confirm Password:</label>
                                <form:password path="confirmPassword" class="form-control"/>
                                <p class="help-block">
                                    <form:errors path="confirmPassword" class="alert-danger"/>
                                </p>
                            </div>
                            <div class="form-group">
                                <button class="btn btn-primary" type="submit">Save</button>
                                <a href="../index.htm"><button class="btn btn-primary" type="button">Cancel</button></a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../template/footer.jspf" %>
