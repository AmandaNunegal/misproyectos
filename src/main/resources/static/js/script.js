let nameStudentForm2 = document.getElementById("nameStudent");
let surnameStudentForm2 = document.getElementById("surnameStudent");
let idActual;

function delStudent(e) {

	const xhttp = new XMLHttpRequest();
	let studentId = this.dataset.id;
	let rowsTStudent = document.getElementsByClassName("rowTableStudent");

	//buttonDel.parentElement.parentElement.parentElement;

	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {
			for (let i = 0; i < rowsTStudent.length; i++) {
				if (rowsTStudent[i].dataset.id == studentId) {
					rowsTStudent[i].remove();
				}
			}
		}
	}

	xhttp.open("POST", "/tablas/deleteSt");
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify({ "id": studentId }));

}

function edStudent() {

	idActual = this.dataset.id;

	let formEd = document.getElementsByClassName("formEdit");

	let nameStudentTable = document.getElementsByClassName("nameStudentTable");
	let surnameStudentTable = document.getElementsByClassName("surnameStudentTable");

	for (let i = 0; i < nameStudentTable.length; i++) {
		if (nameStudentTable[i].dataset.id == idActual) {
			nameStudentForm2.value = nameStudentTable[i].textContent;
			surnameStudentForm2.value = surnameStudentTable[i].textContent;
		}
	}

	for (let i = 0; i < formEd.length; i++) {
		if (formEd[i].dataset.id == "form2") {
		}
	}
}


function saveEdStudent() {

	const xhttp = new XMLHttpRequest();

	let nameStudentT = document.getElementsByClassName("nameStudentTable");
	let surnameStudentT = document.getElementsByClassName("surnameStudentTable");


	xhttp.onload = function() {
		if (xhttp.status >= 200 && xhttp.status < 300) {
			for (let i = 0; i < nameStudentT.length; i++) {
				if (nameStudentForm2.value != "" || surnameStudentForm2.value != "") {
					if (nameStudentT[i].dataset.id == idActual) {
						nameStudentT[i].textContent = nameStudentForm2.value;
						surnameStudentT[i].textContent = surnameStudentForm2.value;						
					}
				}
			}

		}
	}

	xhttp.open("POST", "/tablas/editSt");
	xhttp.setRequestHeader("Content-type", "application/json");
	xhttp.send(JSON.stringify({
		"id": idActual,
		"name": nameStudentForm2.value,
		"surname": surnameStudentForm2.value
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