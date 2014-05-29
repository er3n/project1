function lockScreen() {
	busyStatusDialog.show();
}

function releaseScreen() {
	busyStatusDialog.hide();
}

$(document).ready(function() {
    PrimeFaces.widget.Dialog.prototype.originalHide = PrimeFaces.widget.Dialog.prototype.hide; // keep a reference to the original hide()
    PrimeFaces.widget.Dialog.prototype.hide = function() {
        var ajaxResponseArgs = arguments.callee.caller.arguments[2]; // accesses oncomplete arguments
        if (ajaxResponseArgs && ajaxResponseArgs.validationFailed) {
            return;  // on validation error, prevent closing
        }
        this.originalHide();
    };
});
