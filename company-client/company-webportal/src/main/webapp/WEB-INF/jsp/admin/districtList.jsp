<%@include file="../template/header.jspf" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            District List
        </div>
        <div class="panel-body">
            <a href="../index.htm">Option Tables</a> | <a href="district.form">New District</a> | <a href="district.list">District List</a><br/><br/>
            <%@include file="../template/message.jspf" %>
            <div class="row">
                <div class="col-lg-10">
                    <table id="tableList" class="display" cellspacing="0">
                        <thead>
                        <th>Name</th>
                        <th>Province</th>
                        <th>&nbsp</th>
                        </thead>
                        <tfoot>
                        <th>Name</th>
                        <th>Province</th>
                        <th>&nbsp</th>
                        </tfoot>
                        <tbody>
                            <c:forEach var="district" items="${items}" >
                                <tr>
                                    <td><a href="<c:url value="district.form?id=${district.id}"/>">${district.name}</a></td>
                                    <td>${district.province.name}</td>
                                    <td>
                                        <a href="<c:url value="district.form?id=${district.id}"/>">Edit | </a>
                                        <a href="<c:url value="district.delete?id=${district.id}"/>">Delete</a>
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