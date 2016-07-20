window.onload = function() {
	document.getElementById("singleAlignmentInfo").style.display = 'none';
	document.getElementById("multipleAlignmentInfo").style.display = 'none';
	var elems = document.getElementsByClassName("alignment");
	for (i = 0; i < elems.length; i++) {
		elems[i].onchange = function() {
			toggleAlignmentTypes()
		};
	}
	var sequencesToSelect = document.getElementById("sequencesToSelect");
	sequencesToSelect.onchange = function() {
		selectTaxonNames()
	};
	var taxonOneToSelect = document.getElementById("sequence1ID");
	var taxonTwoToSelect = document.getElementById("sequence2ID");
	taxonOneToSelect.onchange = function() {
		selectTaxonOneName()
	};
	taxonTwoToSelect.onchange = function() {
		selectTaxonTwoName()
	};
	selectTaxonOneName();
	selectTaxonTwoName();
	
	sequencesToSelect.selectedIndex = 0;
	selectTaxonNames();
}

function toggleAlignmentTypes() {
	var elems = document.getElementsByClassName("alignment");
	for (i = 0; i < elems.length; i++) {
		if (elems[i].value == 'single') {
			if (elems[i].checked) {
				document.getElementById("singleAlignmentInfo").style.display = 'block';
				document.getElementById("multipleAlignmentInfo").style.display = 'none';
			} else {
				document.getElementById("singleAlignmentInfo").style.display = 'none';
				document.getElementById("multipleAlignmentInfo").style.display = 'block';
			}
		}
	}
}
function selectTaxonOneName() {
	var taxonOneToSelect = document.getElementById("sequence1ID");
	var options = taxonOneToSelect.options;
	var index = taxonOneToSelect.selectedIndex;
	var taxonTable = document.getElementById("taxonNameTableSingle");
	
	taxonTable.rows[1].cells[0].innerHTML = options[index].value;
	str = options[index].innerHTML
	str = str.substring(str.indexOf(":") + 2, str.length);
	taxonTable.rows[1].cells[1].innerHTML = "<input type='text' class='form-control' name='s" + options[index].value
			+ "' value='" + str + "'></input>";
}
function selectTaxonTwoName() {
	var taxonTwoToSelect = document.getElementById("sequence2ID");
	var options = taxonTwoToSelect.options;
	var index = taxonTwoToSelect.selectedIndex;
	var taxonTable = document.getElementById("taxonNameTableSingle");
	
	taxonTable.rows[2].cells[0].innerHTML = options[index].value;
	str = options[index].innerHTML
	str = str.substring(str.indexOf(":") + 2, str.length);
	taxonTable.rows[2].cells[1].innerHTML = "<input type='text' class='form-control' name='s" + options[index].value
			+ "' value='" + str + "'></input>";
}

function selectTaxonNames() {
	var sequencesToSelect = document.getElementById("sequencesToSelect");
	var options = sequencesToSelect.options;
	var taxonTable = document.getElementById("taxonNameTable");
	var index = 1;
	for (i = 0; i < options.length; i++) {
		if (options[i].selected) {
			if (taxonTable.rows.length <= index) {
				var row = taxonTable.insertRow(index);
				var cell = row.insertCell(0);
				cell.innerHTML = options[i].value;
				cell = row.insertCell(1);
				str = options[i].innerHTML
				str = str.substring(str.indexOf(":") + 2, str.length);
				cell.innerHTML = "<input type='text' class='form-control' name='m" + options[i].value
						+ "' value='" + str + "'></input>";
			} else {
				if (taxonTable.rows[index].cells[0].innerHTML == options[i].value) {
				} else {
					var row = taxonTable.insertRow(index);
					var cell = row.insertCell(0);
					cell.innerHTML = options[i].value;
					cell = row.insertCell(1);
					str = options[i].innerHTML
					str = str.substring(str.indexOf(":") + 2, str.length);
					cell.innerHTML = "<input type='text' class='form-control' name='m"
							+ options[i].value + "' value='" + str + "'></input>";
				}
			}
			index++;
		} else if (taxonTable.rows[index]
				&& options[i].value == taxonTable.rows[index].cells[0].innerHTML) {
			taxonTable.deleteRow(index);
		}
	}
}