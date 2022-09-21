<%@include file="template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <%@include file="template/message.jspf" %>
                <c:if test="${message.exist}">
                    <div class="alert alert-info ${message.messageType}">
                        ${message}
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-lg-12">
                        <form action="search_employee" method="post">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search Employee" name="employeeName" id="employeeName">
                                <div class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                                </div> 
                            </div>
                        </form><br/><br/>
                            <%@include file="template/employeeList.jspf" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jspf" %>
