function colorSequences() {
	var x = document.getElementsByClassName("entry");
	var i;
	for (i = 0; i < x.length; i++) {
		if (x[i].innerHTML == "A") {
			x[i].style.backgroundColor = "red";
		} else if (x[i].innerHTML == "C") {
			x[i].style.backgroundColor = "blue";
		} else if (x[i].innerHTML == "G") {
			x[i].style.backgroundColor = "green";
		} else if (x[i].innerHTML == "T") {
			x[i].style.backgroundColor = "orange";
		} else if (x[i].innerHTML == "-") {
			x[i].style.backgroundColor = "black";
		}
	}
}