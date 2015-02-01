/*
 * Janusz Błaszczyk
 * blaszczyk.janusz@gmail.com
 * 01.02.15 13:29
 */

$( init );

function init() {

    $( '.wizyta' ).draggable( {
        containment: '#content',	/*kontener, fajne :P*/
        /*stack: '#draggable',*/
        cursor: 'move',
        revert: true,
        drag: function(event, ui){
            ui.helper.draggable( 'option', 'revert', true );
        }
    } ).click(function() {
        alert( "Handler for .click() called." );
    });

    $(".termin").droppable( {
        accept: '.wizyta',
        hoverClass: 'hovered',
        drop: handleCardDrop,
        activate: function( event, ui ) {
            ui.draggable.draggable( 'option', 'revert', true ); //powrót zawsze
        }
    } );
    $(".termin-pusty").droppable( "option", "disabled", false );
    $(".termin-brak-uslug").droppable( "option", "disabled", true );
    $(".termin-zajety").droppable( "option", "disabled", true);
}

function handleCardDrop( event, ui ) {
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

function handleCardDrop2( event, ui ) {
    $(this).html( "Dropped!" );
    // If the card was dropped to the correct slot,
    // change the card colour, position it directly
    // on top of the slot, and prevent it being dragged
    // again
    //ui.draggable.addClass( 'correct' );
    //ui.draggable.draggable( 'disable' );
    $(this).droppable( "option", "disabled", true );
    $(this).removeClass("termin-pusty").addClass("termin-zajety");

    $(this).append(ui.draggable);

   $(this).position( { of: $(this), my: 'left top +10', at: 'left top' } );
    ui.draggable.draggable( 'option', 'revert', false );

    $( ".termin-zajety" ).not( ".wizyta" )
        .removeClass("termin-zajety").addClass("termin-pusty").droppable( {
            accept: '.wizyta',
            hoverClass: 'hovered',
            drop: handleCardDrop,
            activate: function( event, ui ) {
                ui.draggable.draggable( 'option', 'revert', true );


            }
        } );
    $(".termin-pusty").droppable( "option", "disabled", false );






}
