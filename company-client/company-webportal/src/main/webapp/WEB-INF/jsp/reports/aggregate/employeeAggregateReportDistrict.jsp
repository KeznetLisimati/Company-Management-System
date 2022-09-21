<%@include file="../../template/header.jspf" %>
<style type="text/css">
    .sidebar-nav{
        display: none;
    }
    #page-wrapper {
        margin: 0 ! important;
    }
    .make-scroll{
        height: 445px;
        overflow: scroll;
    }
</style>
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
                        <br/>
                        <a href="${page}/reports/index.htm">&DoubleLeftArrow; Back To Reports DashBoard Home</a>
                        <!--Dashboard panels here -->
                        <c:if test="${items.size() >= 1}">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel-footer" style="text-align: right">
                                        Export/ View As
                                        <a href="${page}${excelExport}">
                                            <img src="<c:url value="/resources/images/excel.jpeg"/>"/>
                                        </a><!-- | &nbsp; 
                                        <a href="${page}${wordExport}">
                                            <img src="<c:url value="/resources/images/wrd.jpeg"/>"/>
                                        </a>--> | &nbsp;
                                        <a href="${page}${pdfExport}">
                                            <img src="<c:url value="/resources/images/pdf.png"/>"/>
                                        </a><!-- | &nbsp;
                                        <a href="${page}${emailExport}">
                                            <img src="<c:url value="/resources/images/email.jpeg"/>"/>
                                        </a> -->
                                    </div>
                                </div>
                            </div> 
                        </c:if>
                        <table id="tableList" class="display" cellspacing="0" width="100%">
                            <tbody>
                                <c:set value="0" var="numCount"/>
                                <c:forEach var="top" items="${items}">
                                    <c:choose>
                                        <c:when test="${numCount == 0}">
                                            <tr>
                                                <c:forEach var="topRow" items="${top.row}">
                                                    <th class="report-header">${topRow}</th> 
                                                    </c:forEach>
                                            </tr>
                                            <c:set var="numCount" value="${numCount+1}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:set var="innerCount" value="0"/>
                                            <tr>
                                                <c:forEach var="r" items="${top.row}">
                                                    <c:choose>
                                                        <c:when test="${innerCount == 0 or innerCount +1 == top.row.size()}">
                                                            <td class="report-header">${r}</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>${r}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:set var="innerCount" value="${innerCount+1}"/>
                                                </c:forEach>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tbody>
                        </table>
                        <!--Dashboard panels here -->
                        <c:if test="${items.size() >= 1}">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="panel-footer" style="text-align: right">
                                        Export/ View As
                                        <a href="${page}${excelExport}">
                                            <img src="<c:url value="/resources/images/excel.jpeg"/>"/>
                                        </a><!--| &nbsp; 
                                        <a href="${page}${wordExport}">
                                            <img src="<c:url value="/resources/images/wrd.jpeg"/>"/>
                                        </a>--> | &nbsp;
                                        <a href="${page}${pdfExport}">
                                            <img src="<c:url value="/resources/images/pdf.png"/>"/>
                                        </a> <!-- | &nbsp;
                                        <a href="${page}${emailExport}">
                                            <img src="<c:url value="/resources/images/email.jpeg"/>"/>
                                        </a> -->
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