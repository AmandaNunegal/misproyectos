let editing = false;

function saveStudent() {

	let nameSt = document.getElementById("name");
	let lastnameSt = document.getElementById("lastname");


	if (nameSt.value != "" && lastnameSt.value != "") {
		nameSt.value = "ok";
	} else {
		lastnameSt.value = "no ok"
	}

}

function delStudent(e) {

	const xhttp = new XMLHttpRequest();
	let element = e.target;
	let studentId = element.dataset.id;
	let trDelete = element.parentElement.parentElement;


	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {
			trDelete.remove();
		}
	}

	xhttp.open("POST", "/tablas/deleteSt");
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify({ id: studentId }));

}

function edStudent(e, editing) {
	
	const xhttp = new XMLHttpRequest();
	let element = e.target;
	let studentId = element.dataset.id;
	let tdNameStudents = document.getElementsByClassName("nameStudentTable");
	let inputTxtNameStudent;
	
	if (editing == false) {
		for (i = 0; i < tdNameStudents.length; i++) {
			if (tdNameStudents[i].dataset.id == studentId) {
				inputTxtNameStudent = document.createElement("input");
				inputTxtNameStudent.type = "text";
				inputTxtNameStudent.value = "";
				tdNameStudents[i].appendChild(inputTxtNameStudent);
				editing = true;				
			}
		}
		element.disabled = true;		
	} 
		

	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {

		}

		xhttp.open("POST", "/tablas/editSt");
		xhttp.setRequestHeader("Content-type", "application/json");
		xhttp.send(JSON.stringify({ id: studentId }));

	}	
}
	let buttonEditStudent = document.getElementsByClassName("buttonStudentEdit");
	for (let i = 0; i < buttonEditStudent.length; i++) {
		buttonEditStudent[i].addEventListener("click", function (event) {
			edStudent(event, "false");
		});
	}

	let buttonDeleteStudent = document.getElementsByClassName("buttonStudentDelete");
	for (let i = 0; i < buttonDeleteStudent.length; i++) {
		buttonDeleteStudent[i].addEventListener("click", delStudent);
	}