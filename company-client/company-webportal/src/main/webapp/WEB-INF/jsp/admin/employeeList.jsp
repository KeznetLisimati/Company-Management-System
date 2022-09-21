<%@include file="../template/header.jspf" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            Employees List
        </div>
        <div class="panel-body">
            <a href="../index.htm">Option Tables</a> | <a href="employee.form">New Employee</a> | <a href="employee.list">Employee List</a>
            <hr/>
            <%@include file="../template/message.jspf" %>
            <div class="row">
                <div class="col-lg-12">
                    <table id="tableList" class="display" cellspacing="0">
                        <thead>
                        <th>Full Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Age</th>
                        <th>Status</th>
                        <th>&nbsp</th>
                        </thead>
                        <tfoot>
                        <th>Full Name</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>Gender</th>
                        <th>Age</th>
                        <th>Status</th>
                        <th>&nbsp</th>
                        </tfoot>
                        <tbody>
                            <c:forEach var="employee" items="${items}" >
                                <tr>
                                    <td><a href="<c:url value="employee.form?id=${employee.id}"/>">${employee.name}</a></td>
                                    <td>${employee.contact}</td>
                                     <td>${employee.address}</td>
                                      <td>${employee.gender}</td>
                                       <td>${employee.age}</td>
                                        <td>${employee.status}</td>
                                    <td>
                                        <a href="<c:url value="employee.form?id=${employee.id}"/>"><span class="btn btn-default btn-sm" style="color:green"> Edit | </span></a>
                                        <a href="<c:url value="employee.delete?id=${employee.id}"/>"><span style="color:red" class="btn btn-default btn-sm"> Delete </span></a>
                                          
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>
        <div class="panel-footer">
            
        </div>
    </div>
</div>

<%@include file="../template/footer.jspf" %>