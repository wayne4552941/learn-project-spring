/**
 * 
 */

document.getElementById("name").addEventListener('blur', function () {

    if (!nameDatacheck()) {
        document.getElementById('spExamName').innerHTML = "姓名不能為空"
    } else {
        document.getElementById('spExamName').innerHTML = ""
    }

})

document.getElementById("examDate").addEventListener('blur', function () {

    if (!dateDatacheck()) {
        document.getElementById('spExamDate').innerHTML = "日期格式 2012-12-13"
    } else {
        document.getElementById('spExamDate').innerHTML = ""
    }

})

function nameDatacheck() {
    var name = document.getElementById("name").value
    if (/^[\s\n\t\r]*$/.test(name)) {
        return false;
    } else {
        return true;
    }
}

function dateDatacheck() {
    var date = document.getElementById("examDate").value

    if (!/^([2]{1}[0]{1}\d{2})-(\d{2})-(\d{2})$/.test(date)) {
        return false;
    } else {
        return true;
    }
}


function check() {
    var name = document.getElementById("name").value
    if (/^[\s\n\t\r]*$/.test(name)) {
        alert("姓名不能為空");
        return false;
    }


    var date = document.getElementById("examDate").value

    if (!/^([2]{1}[0]{1}\d{2})-(\d{2})-(\d{2})$/.test(date)) {
        alert("日期格式 2012-12-13");
        return false;
    }
}