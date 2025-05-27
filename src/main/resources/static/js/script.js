let nameStudentForm= document.getElementById("nameStudent");
let lastnameStudentForm = document.getElementById("lastnameStudent");
let idActual;

function saveStudent() {

	let nameSt = document.getElementById("name");
	let lastnameSt = document.getElementById("lastname");


	if (nameSt.value != "" && lastnameSt.value != "") {
		nameSt.value = "ok";
	} else {
		lastnameSt.value = "no ok";
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
	xhttp.send(JSON.stringify({ "id": studentId }));

}

function edStudent(e) {

	let element = e.target;
	let studentId = element.dataset.id;
	idActual = studentId;

	let nameStudentTable = document.getElementsByClassName("nameStudentTable");
	for (let i = 0; i < nameStudentTable.length; i++) {
		if (nameStudentTable[i].dataset.id == idActual) {
			nameStudentForm.value = nameStudentTable[i].textContent;
		}
	}

	let lastnameStudentTable = document.getElementsByClassName("lastnameStudentTable");
	for (let i = 0; i < lastnameStudentTable.length; i++) {
		if (lastnameStudentTable[i].dataset.id == idActual) {
			lastnameStudentForm.value = lastnameStudentTable[i].textContent;
		}
	}


}


function saveEdStudent() {

	const xhttp = new XMLHttpRequest();
	let nameStudentT = document.getElementsByClassName("nameStudentTable")[idActual];
	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {
			
			alert(nameStudentT.value);
		}
	}
	xhttp.open("POST", "/tablas/editSt");
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify({
		"id": idActual,
		"name": nameStudentForm.value,
		"lastname": lastnameStudentForm.value
	}));
}


//window.location.href = "/tablas/holaAlumnos";


let buttonEditStudent = document.getElementsByClassName("buttonStudentEdit");
for (let i = 0; i < buttonEditStudent.length; i++) {
	/* buttonEditStudent[i].addEventListener("click", function(event) {
		edStudent(event); */
	buttonEditStudent[i].addEventListener("click", edStudent);
}

let buttonDeleteStudent = document.getElementsByClassName("buttonStudentDelete");
for (let i = 0; i < buttonDeleteStudent.length; i++) {
	buttonDeleteStudent[i].addEventListener("click", delStudent);
}

let buttonSaveEditStudent = document.getElementById("editStudent");
buttonSaveEditStudent.addEventListener("click", saveEdStudent);

