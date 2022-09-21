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
                                            <label>Type</label>
                                            <form:select path="citizenship" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${citizenship}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="citizenship" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Nationality</label>
                                            <form:select path="nationality" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${nationality}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="nationality" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>Gender</label>
                                            <form:select path="gender" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${gender}" itemValue="id" />/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="gender" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td> 
                                    <td>
                                        <div class="form-group">
                                            <label>Province</label>
                                            <form:select path="province" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${province}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="province" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label>District</label>
                                            <form:select path="district" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${district}" itemValue="id" itemLabel="name"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="district" class="alert-danger"/>
                                            </p>
                                        </div>
                                    </td>

                                    <td>
                                        <div class="form-group">
                                            <label>Qualification</label>
                                            <form:select path="employeeQualification" class="form-control">
                                                <form:option value="" label="--Select Item"/>
                                                <form:options items="${employeeQualification}" itemValue="employeeQualificationId" itemLabel="qualification"/>/>
                                            </form:select>
                                            <p class="help-block">
                                                <form:errors path="employeeQualification" class="alert-danger"/>
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
                            <th>Citizenship</th>
                            <th>Nationality</th>
                            <th>Gender</th>
                            <th>Province</th>
                            <th>District</th>
                            <th>Qualification</th>
                            </thead>
                            <tfoot>
                            <th>Name</th>
                            <th>Citizenship</th>
                            <th>Nationality</th>
                            <th>Gender</th>
                            <th>Province</th>
                            <th>District</th>
                            <th>Qualification</th>
                            </tfoot>
                            <tbody>
                                <c:forEach var="employee" items="${items}" >
                                    <tr>
                                        <td>${employee.name}</td>
                                        <td>${employee.citizenship.name}</td>
                                        <td>${employee.nationality.name}</td>
                                        <td>${employee.gender}</td>
                                        <td>${employee.station.district.province.name}</td>
                                        <td>${employee.station.district.name}</td>
                                        <td>${employee.employeeQualification}</td>
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
                                <a href="${page}${wordExport}">
                                    <img src="<c:url value="/resources/images/wrd.jpeg"/>" id="wordExport"/>
                                </a> | &nbsp; 
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
    $("#citizenship").change(function () {
    $("form").submit();
    });
            $("#nationality").change(function () {
    $("form").submit();
    });
            $("#gender").change(function () {
    $("form").submit();
    });
            $("#province").change(function () {
    $("#district").val('');
            $("#station").val('');
            $("form").submit();
    });
            $("#district").change(function () {
    $("#station").val('');
            $("form").submit();
    });
            $("#employeeQualification").change(function){
    $("form").submit();
    });


</script>