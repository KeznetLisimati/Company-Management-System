<%@include file="../template/header.jspf" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            Privilege List
        </div>
        <div class="panel-body">
            <a href="../index.htm">Option Tables</a> | <a href="privilege.form">New Privilege</a> | <a href="privilege.list">Privilege List</a>
            <hr/>
            <%@include file="../template/message.jspf" %>
            <div class="row">
                <div class="col-lg-10">
                    <table id="tableList" class="display" cellspacing="0">
                        <thead>
                        <th>Name</th>
                        <th>&nbsp</th>
                        </thead>
                        <tfoot>
                        <th>Name</th>
                        <th>&nbsp</th>
                        </tfoot>
                        <tbody>
                            <c:forEach var="privilege" items="${items}" >
                                <tr>
                                    <td><a href="<c:url value="privilege.form?id=${privilege.id}"/>">${privilege.name}</a></td>
                                    <td>
                                        <a href="<c:url value="privilege.form?id=${privilege.id}"/>">Edit | </a>
                                        <a href="<c:url value="privilege.delete?id=${privilege.id}"/>">Delete</a>
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