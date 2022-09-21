<%@include file="../template/header.jspf" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            Address Type List
        </div>
        <div class="panel-body">
            <a href="../index.htm">Option Tables</a> | <a href="addressType.form">New Address Type</a> | <a href="addressType.list">Address Type List</a>
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
                            <c:forEach var="addressType" items="${items}" >
                                <tr>
                                    <td><a href="<c:url value="addressType.form?id=${addressType.id}"/>">${addressType.name}</a></td>
                                    <td>
                                        <a href="<c:url value="addressType.form?id=${addressType.id}"/>">Edit | </a>
                                        <a href="<c:url value="addressType.delete?id=${addressType.id}"/>">Delete</a>
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