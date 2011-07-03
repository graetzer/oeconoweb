/* Author: Simon Grätzer simon@graetzer.org
 * 
 * _i_ is replaced with the row index
 */
(function($) {
	$.fn.addRow = function(template) {
		if (template) {
			$( template.replace(/_i_/g, this.children().length) ).append(
					$('<a href="#">Delete</a>').click(function() {
						$(this).parent().remove();
					})).appendTo(this);
		}
		return this;
	};
})(jQuery);
