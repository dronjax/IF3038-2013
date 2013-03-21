Rp(function() {
	checkValidity = function() {
		pattern = new RegExp(this.getAttribute('pattern'), 'i');
		match = this.value.match(pattern);

		diff = true;
		if (neqe = this.getAttribute('data-neq')) {
			ne = Rp('#' + neqe);
			if (ne.val() == this.value)
				diff = false;
		}

		if ((match && diff) || !this.value) {
			// bener
			Rp(this).removeClass('invalid');

			inv = Rp('input[pattern].invalid');
			if (this.value) {
				Rp('#edit_submit').prop('disabled', (inv.length() > 0));
			}
		}
		else {
			// ga valid
			console.log('Invalid: ' + this.id);
			Rp(this).addClass('invalid');
			Rp('#edit_submit').prop('disabled', true);
		}
	};

	Rp('input[pattern]').on('keyup', checkValidity);
	Rp('#edit_submit').prop('disabled', true); // disabled by default
	
	window.onload = function() 
	{
		datePicker.init(document.getElementById("calendar"), document.getElementById("edit_form"));
	}
});
