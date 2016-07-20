function setupTable(){
	document.getElementById("updateButton").onclick = function() { updateTable() };
	document.getElementById("slideLeftButton").onclick = function() { slideLeft() };
	document.getElementById("slideRightButton").onclick = function() { slideRight() };
	updateTable();
}

function slideLeft() {
	startPos = document.getElementById("startPos").value;
	endPos = document.getElementById("endPos").value;
	slideAmount = document.getElementById("slideAmount").value;
	if (slideAmount < 1) {
		slideAmount = 1;
		document.getElementById("slideAmount").value = slideAmount;
	}
	startPos = parseInt(startPos) - parseInt(slideAmount);
	endPos = parseInt(endPos) - parseInt(slideAmount);
	document.getElementById("startPos").value = startPos;
	document.getElementById("endPos").value = endPos;
	updateTable();
}

function slideRight() {
	startPos = document.getElementById("startPos").value;
	endPos = document.getElementById("endPos").value;
	slideAmount = document.getElementById("slideAmount").value;
	if (slideAmount < 1) {
		slideAmount = 1;
		document.getElementById("slideAmount").value = slideAmount;
	}
	startPos = parseInt(startPos) + parseInt(slideAmount);
	endPos = parseInt(endPos) + parseInt(slideAmount);
	document.getElementById("startPos").value = startPos;
	document.getElementById("endPos").value = endPos;
	updateTable();
}

function updateTable() {
	var myTable = document.getElementById("viewerTable");

	myTable.innerHTML = ""; //clear table

	var startPos = document.getElementById("startPos").value;
	var endPos = document.getElementById("endPos").value;
	var numCols = document.getElementById("numColumns").value;
	var slideAmount = document.getElementById("slideAmount").value;
	
	//validation
	seqLength = sequences[0].length;
	if (endPos > seqLength - 1) {
		endPos = seqLength - 1;
		document.getElementById("endPos").value = endPos;
	}
	else if (endPos <= 0) {
		endPos = 0;
		document.getElementById("endPos").value = endPos;
	}
	if (startPos < 0) {
		startPos = 0;
		document.getElementById("startPos").value = startPos;
	}
	else if (startPos > seqLength - 1) {
		startPos = seqLength - 1;
		document.getElementById("startPos").value = startPos;
	}
	if (parseInt(endPos) < parseInt(startPos)) {
		startPos = endPos;
		document.getElementById("startPos").value = startPos;
	}
	if (numCols < 1) {
		numCols = 1;
		document.getElementById("numColumns").value = numColumns;
	}
	
	//display table
	rowIndex = 0;
	numRows = Math.floor((endPos - startPos)/numCols) + 1;
	for (i = 0; i < numRows; i++) {
		for (seqNum = 0; seqNum < sequences.length; seqNum++) {
			row = myTable.insertRow(rowIndex++);
			for (j = 0; j < numCols; j++) {
				if (parseInt(startPos) + parseInt(numCols*i) + parseInt(j) <= endPos) {
					var cell = row.insertCell(j);
					cell.innerHTML = sequences[seqNum][parseInt(startPos) + parseInt(numCols*i) + parseInt(j)];
					cell.className="entry";
				}
		}
		}
		row = myTable.insertRow(rowIndex++);
		for (j = 0; j < numCols; j++) {
			if (parseInt(startPos) + parseInt(numCols*i) + parseInt(j) <= endPos) {
				var cell = row.insertCell(j);
				cell.innerHTML = parseInt(startPos) + parseInt(numCols*i) + parseInt(j);
				cell.className="index";
			}
		}
		row = myTable.insertRow(rowIndex++);
		for (j = 0; j < numCols; j++) {
			if (parseInt(startPos) + parseInt(numCols*i) + parseInt(j) <= endPos) {
				var cell = row.insertCell(j);
				cell.className="blank";
			}
		}
	}
	
	colorSequences();
}