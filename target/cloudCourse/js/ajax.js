var url = "localhost"
var port = "8080"

function getXmlHttp() {
    var xmlHttp = false;
    if (window.XMLHttpRequest) {
        //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
        xmlHttp = new XMLHttpRequest();
    } else {
        //ie5,ie6
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (e) {
            xmlHttp = false;
        }
    }
    return xmlHttp;
}

function queryTeacherBySchool(school_id) {
    var select = document.getElementById("queryCla");
    if (school_id == "") {
        select.length = 1;
    } else {
        select.length = 1;
        var xmlHttp = getXmlHttp();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status = 200) {
                    var clalist = xmlHttp.responseText //返回的json
                    var obj = eval(clalist);
                    for (var i = 0; i < obj.length; i++) {
                        option = document.createElement("option");
                        option.innerHTML = obj[i].className;
                        option.value = obj[i].classId;
                        select.appendChild(option);
                    }
                }
            }
        }
        xmlHttp.open("GET", "http://localhost:8080/person/ajaxGetClassBySchool.do?personId=" + school_id, true);
        xmlHttp.setRequestHeader("Content-type", "text/plain");
        xmlHttp.send(null);
    }
}

function queryCourseByCategory(category_id) {
    var select = document.getElementById("courseName");
    if (category_id == "") {
        select.length = 1;
    } else {
        select.length = 1;
        var xmlHttp = getXmlHttp();
        xmlHttp.onreadystatechange = function () {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status = 200) {
                    var clalist = xmlHttp.responseText //返回的json
                    var obj = eval(clalist);
                    for (var i = 0; i < obj.length; i++) {
                        option = document.createElement("option");
                        option.innerHTML = obj[i].courseName;
                        option.value = obj[i].courseId;
                        select.appendChild(option);
                    }
                }
            }
        }
        xmlHttp.open("GET", "http://localhost:8080/class/ajaxGetCourseByCategory.do?categoryId=" + category_id, true);
        xmlHttp.setRequestHeader("Content-type", "text/plain");
        xmlHttp.send(null);
    }
}
