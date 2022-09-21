<%@include file="../template/header.jspf" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            Station List
        </div>
        <div class="panel-body">
            <a href="../index.htm">Option Tables</a> | <a href="station.form">New Station</a> | <a href="station.list">Station List</a>
            <hr/>
            <%@include file="../template/message.jspf" %>
            <div class="row">
                <div class="col-lg-10">
                    <table id="tableList" class="display" cellspacing="0">
                        <thead>
                        <th>Name</th>
                        <th>Station Category</th>
                        <th>District</th>
                        <th>Province</th>
                        <th>&nbsp</th>
                        </thead>
                        <tfoot>
                        <th>Name</th>
                        <th>Station Category</th>
                        <th>District</th>
                        <th>Province</th>
                        <th>&nbsp</th>
                        </tfoot>
                        <tbody>
                            <c:forEach var="station" items="${items}" >
                                <tr>
                                    <td><a href="<c:url value="station.form?id=${station.id}"/>">${station.name}</a></td>
                                    <td>${station.category.name}</td>
                                    <td>${station.district.name}</td>
                                    <td>${station.district.province.name}</td>
                                    <td>
                                        <a href="<c:url value="station.form?id=${station.id}"/>">Edit | </a>
                                        <a href="<c:url value="station.delete?id=${station.id}"/>">Delete</a>
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