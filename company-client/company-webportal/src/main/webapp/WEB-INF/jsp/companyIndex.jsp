<%@include file="template/header.jspf" %>

<form:form commandName="item">
    <legend style="color: green">Company Registration </legend> 

    <div class="col-lg-12">
        <div class="panel panel-default">

            <div class="panel-heading">
                Manage Company
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table width="100%" class="table table-bordered">
                        <tbody>
                            <tr>
                                <td  hight="100%" style="background-color:lightcyan" >
                                    <a href="${page}/companies.htm" title="Manage Company" title="Manage Company">Manage Company</a>
                                </td>

                                <td>
                                    <a href="${page}/emp/item.form" title="New Employee">Company Employee</a>
                                </td>

                            </tr>
                            <tr>
                                <td>
                                    <a href="${page}/reports/index.htm" title= "Reports">System Reports</a>
                                </td>
                                <td>
                                    <a href="${page}/admin/index.htm"   title= "System Administration">  Administration</a>
                                </td>
                            </tr>
                            

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form:form>

<%@include file="template/footer.jspf" %>
