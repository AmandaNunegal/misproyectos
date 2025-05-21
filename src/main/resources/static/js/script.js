function deleteStudent(elemento) {
	
	// let nombre = document.getElementById("prueba").name;
	
	const xhttp = new XMLHttpRequest();	
	const studentId = elemento.dataset.id;
	const trDelete = elemento.parentElement.parentElement;	
	
	
	xhttp.onload = function (){	
		if (xhttp.status >= 200 && xhttp.status < 300) {
			trDelete.remove();					
		}
	}	
	
	xhttp.open("POST", "/tablas/deleteSt");	
	xhttp.setRequestHeader("Content-type", "application/json");	
	xhttp.send(JSON.stringify({ id: studentId }));
			
}

//document.getElementById("newStudent").addEventListener("click", deleteStudent);