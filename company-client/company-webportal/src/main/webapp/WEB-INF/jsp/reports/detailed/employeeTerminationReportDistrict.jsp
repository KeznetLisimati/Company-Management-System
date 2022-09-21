<%@include file="../../template/header.jspf" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <%@include file="../../template/message.jspf" %>
                <%@include file="../../template/terminationDistrict.jspf" %>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="tableList" class="display" cellspacing="0">
                            <thead>
                            <th>Name</th>
                            <th>Post</th>
                            <th>Station</th>
                            <th>Reason</th>
                            <td>Termination</td>
                            <td>Approved</td>
                            </thead>
                            <tfoot>
                            <th>Name</th>
                            <th>Post</th>
                            <th>Station</th>
                            <th>Reason</th>
                            <td>Termination</td>
                            <td>Approved</td>
                            </tfoot>
                            <tbody>
                                <c:forEach var="tem" items="${items}" >
                                    <tr>
                                        <td>${tem.employee.fullname}</td>
                                        <td>${tem.employee.post.name}</td>
                                        <td>${tem.employee.station.name}</td>
                                        <td>${tem.employeeStatusChangeReason.name}</td>
                                        <td><spring:eval expression="tem.terminationDate"/></td>
                                        <td><spring:eval expression="tem.dateApproved"/></td>
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
                                </a> | &nbsp; --%>
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

</script>