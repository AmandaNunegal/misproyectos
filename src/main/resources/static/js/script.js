let nameStudentForm2 = document.getElementById("nameStudent");
let lastnameStudentForm2 = document.getElementById("lastnameStudent");
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
	let lastnameStudentTable = document.getElementsByClassName("lastnameStudentTable");
	for (let i = 0; i < nameStudentTable.length; i++) {
		if (nameStudentTable[i].dataset.id == idActual) {
			nameStudentForm2.value = nameStudentTable[i].textContent;
			lastnameStudentForm2.value = lastnameStudentTable[i].textContent;
		}
	}

}


function saveEdStudent() {

	const xhttp = new XMLHttpRequest();
	
	let nameStudentT = document.getElementsByClassName("nameStudentTable");	
	let lastnameStudentT = document.getElementsByClassName("lastnameStudentTable");
	
	
	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {
			for (let i = 0; i < nameStudentT.length; i++) {
				if (nameStudentT[i].dataset.id == idActual) {
					nameStudentT[i].textContent = nameStudentForm2.value;
					lastnameStudentT[i].textContent = lastnameStudentForm2.value;
				}
			}
			

		}
	}
	xhttp.open("POST", "/tablas/editSt");
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify({
		"id": idActual,
		"name": nameStudentForm2.value,
		"lastname": lastnameStudentForm2.value
	}));
}

let buttonEditStudent = document.getElementsByClassName("buttonStudentEdit");
for (let i = 0; i < buttonEditStudent.length; i++) {	
	buttonEditStudent[i].addEventListener("click", edStudent);
}

let buttonDeleteStudent = document.getElementsByClassName("buttonStudentDelete");
for (let i = 0; i < buttonDeleteStudent.length; i++) {
	buttonDeleteStudent[i].addEventListener("click", delStudent);
}

let buttonSaveEditStudent = document.getElementById("editStudent");
buttonSaveEditStudent.addEventListener("click", saveEdStudent);

// buttonEditStudent[i].addEventListener("click", function(event) { edStudent(event); } 
//window.location.href = "/tablas/holaAlumnos";