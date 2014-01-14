(function ($) {

    $(function () {
        init();
    });

    function init() {
        console.log("init");

        $("#load_file").change(function (){
            var file = $(this).prop('files')[0];
            console.log("file:"+escape(file.name));
        });

        $("#upload").click(function() {
            console.log("uploadFile");

            var form = $("#uploadform")[0];
            var formData = new FormData(form);

            console.log("form:"+form);

            $.ajax("/gaesimple/ajax", {
                method: "POST",
                contentType: false,
                processData: false,
                data: formData,
                dataType: "json",
                error: function() {
                    alert("upload error");
                },
                success: function() {
                    alert("upload success");
                }
            });
        });
    }

})(jQuery)
