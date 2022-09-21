<%@include file="../template/header.jspf" %>
<form:form commandName="item">
    <legend style="color: green">Reports Dash Board</legend> 
    <div class="panel panel-default">
        <div class="panel-heading">
            Employees Reports By Criteria
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <table width="100%" class="table table-bordered">
                    <tbody>
                        <tr>
                            <td>
                                <a href="${page}/reports/all-employees-report.htm">All Employees</a>
                            </td>
                            <td>
                                <a href="${page}/report/leave/employee-leave.htm">Employees Leave</a> 
                            </td>

                            <td>
                                <a href="${page}/report/aggregate/leave-and-location-report.htm">Leave Cross Tabulation</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>                     
</form:form>
<%@include file="../template/footer.jspf" %>
