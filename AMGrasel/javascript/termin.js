/*
 * Janusz Błaszczyk
 * blaszczyk.janusz@gmail.com
 * 01.02.15 13:29
 */

$( init );

function init() {

    $('.wizyta').draggable({
        containment: '#content', /*kontener, fajne :P*/
        /*stack: '#draggable',*/
        cursor: 'move',
        revert: true,
        drag: function (event, ui) {
            ui.helper.draggable('option', 'revert', true);
        }
    }).click(function () {
       dialog.data('dane', $(this).text()).dialog("open");
    });

    $(".termin").droppable({
        accept: '.wizyta',
        hoverClass: 'hovered',
        drop: przesunWizyte,
        activate: function (event, ui) {
            ui.draggable.draggable('option', 'revert', true); //powrót zawsze
        }
    });
    $(".termin-pusty").droppable("option", "disabled", false);
    $(".termin-brak-uslug").droppable("option", "disabled", true);
    $(".termin-zajety").droppable("option", "disabled", true);

}


function przesunWizyte( event, ui ) {
    $(this).droppable( "option", "disabled", true );

    //zmiana flagi - ten termin jest juz zajęty
    $(this).removeClass("termin-pusty").addClass("termin-zajety");
    //dołączamy obiekt
    $(this).append(ui.draggable);
    //ustawiamy pozycję - początek w lewym górnym rogu
    ui.draggable.position( { of: $(this), my: 'left top +10', at: 'left top' } );
    //zmieniamy, aby nas nie cofnęło
    ui.draggable.draggable( 'option', 'revert', false );
    $( ".termin-zajety").not(':has(.wizyta)')
        .removeClass("termin-zajety").addClass("termin-pusty");
    //zbiorczo przestawiamy flagi:
    $(".termin-pusty").droppable( "option", "disabled", false );
    $(".termin-zajety").droppable( "option", "disabled", true );

}
