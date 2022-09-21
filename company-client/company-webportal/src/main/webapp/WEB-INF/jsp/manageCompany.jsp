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
                        <%@include file="template/companyList.jspf" %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jspf" %>
