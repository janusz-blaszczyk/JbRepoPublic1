/*
 * Janusz Błaszczyk
 * blaszczyk.janusz@gmail.com
 * 01.02.15 20:34
 */

/**
 * Created by Janusz on 2015-02-01.
 */


var dialog, form;
$( initWizytaPopup );



function initWizytaPopup() {
    dialog = $("#dialog-form").dialog({

        autoOpen: false,
        height: 200,
        width: 300,
        modal: true,
        buttons: {
            "Zapisz": saveAction,
            "Anuluj wizytę": saveAction,
            "Cofnij": function () {
                dialog.dialog("close");
            }
        },
        open: function (event, ui) {
            var titleData = $(this).data('dane');

            $(this).dialog({
                title: titleData
            });
        }
        ,
        close: function () {
            form[0].reset();
            //tutaj to co robimy po zamknieciu okna cancel/X
        }
    });

    form = dialog.find("form").on("submit", function (event) {
        event.preventDefault();
        saveAction //Tutaj to co robimy po kliknięciu ok
    });
}
function saveAction() {
    var valid = true;
    if ( valid ) {
        dialog.dialog( "close" );
    }
    return valid;
}