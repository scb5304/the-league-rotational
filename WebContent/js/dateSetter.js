function setDate() {
	var d_names = new Array("Sunday", "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday");

	var m_names = new Array("January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December");

	var d = new Date();
	var curr_day = d.getDay();
	var curr_date = d.getDate();
	var curr_month = d.getMonth();
	var curr_year = d.getFullYear();

	document.getElementById("header-date").innerHTML = d_names[curr_day] + ", " + m_names[curr_month] + " " + curr_date + ", " + curr_year;
}

onload = setDate;