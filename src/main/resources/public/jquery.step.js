$(function() {
    var step = 0;
    var stepItem = $('.step-progress .step-slider .step-slider-item');

    // Step Next
    $('.step-content .step-content-foot button[name="next"]').on('click', function() {
        var instance = $(this);
        if (stepItem.length - 1 < step) {
            return;
        }
        if (step == (stepItem.length - 2)) {
            instance.addClass('out');
            instance.siblings('button[name="finish"]').removeClass('out');
        }
        $(stepItem[step]).addClass('active');
        $('.step-content-body').addClass('out');
        $('#' + stepItem[step + 1].dataset.id).removeClass('out');
        step++;
    });

    // Step Last
    $('.step-content .step-content-foot button[name="finish"]').on('click', function() {
        if (step == stepItem.length) {
            return;
        }
        $(stepItem[stepItem.length - 1]).addClass('active');
        $('.step-content-body').addClass('out');
        $('#stepLast').removeClass('out');
    });

    // Step Previous
    $('.step-content .step-content-foot button[name="prev"]').on('click', function() {
        var instance = $(this);
        $(stepItem[step]).removeClass('active');
        if (step == (stepItem.length - 1)) {
            instance.siblings('button[name="next"]').removeClass('out');
            instance.siblings('button[name="finish"]').addClass('out');
        }
        $('.step-content-body').addClass('out');
        $('#' + stepItem[step].dataset.id).removeClass('out');
        if (step <= 0) {
            return;
        }
        step--;
        /*window.location.href="/hr/preBtnShowRecomendedPerson(positionID = ${positionNo})";*/
    });
});
