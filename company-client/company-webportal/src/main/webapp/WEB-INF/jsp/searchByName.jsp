<%@include file="template/header.jspf" %>
<style type="text/css">
    input[type="text"]{
        height: 4em;
        font-size: 16px;
    }
</style>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                ${pageTitle}
            </div>
            <div class="panel-body">
                <%@include file="template/message.jspf" %>
                <c:if test="${message.exist}">
                    <div class="alert alert-info ${message.messageType}">
                        ${message}
                    </div>
                </c:if>
                <div class="row">
                    <div class="col-lg-12">
                        <form method="post">
                            <div class="form-group">
                                <label>Search Employees</label>
                                <input type="text" name="search" placeholder="Search by ecnumber or first name or last name or both first name and lastname" id="search" class="form-control"/>
                            </div>
                        </form><br/>
                        <table id="employeeListing" class="display hide" cellspacing="0">
                            <thead>
                            <th>Name</th>
                            <th>EC Number</th>
                            <th>Post</th>
                            <th>District</th>
                            <th>Facility</th>
                            <th>&nbsp</th>
                            </thead>
                            <tfoot>
                            <th>Name</th>
                            <th>EC Number</th>
                            <th>Post</th>
                            <th>District</th>
                            <th>Facility</th>
                            <th>&nbsp</th>
                            </tfoot>
                            <tbody>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jspf" %>
<script type="text/javascript">
    var search_url = null;
    var count = 0;
    var current = 0;
    $(":text#search").focus();
    var num_chars = 0;
    $(":text#search").keyup(function () {
        $this = $(this);
        num_chars = $this.val().length;
        if (num_chars >= 4) {
            count++;
            if (count - current >= 1) {
                cancelAjaxRequest(search_url);
            }
            search_url = $.get(path + "/employee/search/search-employees", {search: $this.val()}, function (pat) {
                $("#employeeListing").dataTable().fnClearTable(true);
                $("#employeeListing").removeClass("hide");
                $("#employeeListing_paginate").removeClass("hide");
                current++;
                for (i = 0; i < pat.length; i++) {
                    var part_url = "<a href='"+path+"/emp/dashboard/profile.htm?id=" + pat[i].id + "'>";
                    $("#employeeListing").dataTable().fnAddData([part_url + pat[i].name + "</a>",
                        pat[i].ecNumber,
                        pat[i].post,
                        pat[i].district,
                        pat[i].station,
                        part_url + "Dashboard</a> | <a href='"+path+"/emp/item.form?id=" + pat[i].id + "'>Edit</a>"]);
                }
            });
        } else {
            $("#employeeListing").dataTable().fnClearTable(true);
        }
    });
    $("#employeeListing").dataTable({
        "bFilter": false,
        "bSort": false,
        "bLengthChange": false,
        "bInfo": false});
    $("form").submit(function (evt) {
        return false;
    });
    function cancelAjaxRequest(request) {
        if (request !== null)
            request.abort();
    }
    $("#employeeListing_paginate").addClass("hide");
</script>