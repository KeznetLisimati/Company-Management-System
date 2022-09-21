<b class="titleHeader">Qualifications </b>  | <a href="../qualification/item.form?employeeId=${employee.employeeId}">Add Qualification</a>
<hr/>
<div class="row">
    <div class="col-lg-12">
        <table class="display itemList" cellspacing="0">
            <thead>
                <tr>
                    <th>Qualification</th>
                    <th>Training Institution</th>
                    <th>Awarding Institution</th>
                    <th>Year Acquired</th>
                    <th>Extra</th>
                    <th>&nbsp;</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="qual" items="${qualifications}" varStatus="">
                <tr class="gradeA">
                    <td>${qual.qualification}</td>
                    <td>${qual.trainingInstitution}</td>
                    <td>${qual.awardingInstitution}</td>
                    <td><spring:eval expression="qual.yearAcquired"/></td>
                <td>${qual.extra}</td> 
                <td>
                    <a href="../qualification/item.form?id=${qual.employeeQualificationId}&employeeId=${employee.employeeId}">Edit</a> | 
                    <a id="deletequalification" href="../qualification/item.delete?id=${qual.employeeQualificationId}">Delete</a>
                </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</div>