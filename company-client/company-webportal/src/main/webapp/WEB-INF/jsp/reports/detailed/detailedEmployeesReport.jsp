<%@include file="../../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <%@include file="../../template/message.jspf" %>
                <div class="row">
                    <div class="col-lg-12">
                        <form:form commandName="item" action="${action}">

                            <input type="hidden" id="exportType" name="exportType" />

                            <table class ="table">
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label>Category</label>
                                            <form:select path="employmentCategory" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${employmentCategory}" itemValue="name" itemLabel="name"/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="employmentCategory" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Type</label>
                                            <form:select path="employmentType" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${employmentType}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="employmentType" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Department</label>
                                            <form:select path="department" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${department}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="department" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Station</label>
                                            <form:select path="station" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${station}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="station" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Post</label>
                                            <form:select path="post" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${post}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="post" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Grade</label>
                                            <form:select path="grade" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${grade}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="grade" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>

                                </tr>
                            </table>
                        </form:form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="tableList" class="display" cellspacing="0">
                            <thead>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Type</th>
                            <th>Department</th>
                            <th>Station</th>
                            <th>Post</th>
                            <th>Grade</th>
                            </thead>
                            <tfoot>
                            <th>Name</th>
                            <th>Category</th>
                            <th>Type</th>
                            <th>Department</th>
                            <th>Station</th>
                            <th>Post</th>
                            <th>Grade</th>
                            </tfoot>
                            <tbody>
                                <c:forEach var="employee" items="${items}" >
                                    <tr>
                                        <td>${employee.firstname}</td>
                                        <td>${employee.employmentCategory.name}</td>
                                        <td>${employee.employmentType.name}</td>
                                        <td>${employee.department.name}</td>
                                        <td>${employee.station.name}</td>
                                        <td>${employee.post.name}</td>
                                        <td>${employee.grade.name}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!--Export panels here -->
                <c:if test="${items.size() >= 1}">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel-footer" style="text-align: right">
                                Export/ View As
                                <a href="${page}${excelExport}">
                                    <img src="<c:url value="/resources/images/excel.jpeg"/>" id="excelExport"/>
                                </a>| &nbsp; 
                                <%--    <a href="${page}${wordExport}">
                                    <img src="<c:url value="/resources/images/wrd.jpeg"/>" id="wordExport"/>
                                </a> --%>| &nbsp; 
                                <a href="${page}${pdfExport}">
                                    <img src="<c:url value="/resources/images/pdf.png"/>"  id="pdfExport"/>
                                </a> | &nbsp;
                                <a href="${page}${emailExport}">
                                    <img src="<c:url value="/resources/images/email.jpeg"/>"/>
                                </a> 
                            </div>
                        </div>
                    </div> 
                </c:if>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="../../template/footer.jspf" %>
<script type="text/javascript">
    $("#employmentCategory").change(function () {
        $("form").submit();
    });
    $("#employmentType").change(function () {
        $("form").submit();
    });
    $("#department").change(function () {
        $("form").submit();
    });
    $("#station").change(function () {
        $("form").submit();
    });
    $("#post").change(function (e) {
        $("form").submit();
    });
    $("#grade").change(function () {
        $("form").submit();
    });

</script>