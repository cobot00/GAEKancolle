(function($){

    var
    ID_GRID_SHIPS = "#grid_ships",
    ID_GRID_EVENT2 = "#grid_event2",
    ID_GRID_EVENT3 = "#grid_event3",
    ID_FILE_TYPE = "#file_type",
    ID_SORT_TYPE_AREA = "#sort_type_area",
    ID_GRID_SHIPS_AREA = "#grid_ships_area",
    ID_GRID_EVENT2_AREA = "#grid_event2_area",
    ID_GRID_EVENT3_AREA = "#grid_event3_area",
    FILE_TYPE_SHIPS = "ships",
    FILE_TYPE_EVENT2 = "event_arp_2",
    FILE_TYPE_EVENT3 = "event_arp_3"
    ;

    $(function() {
        init();
    });

    function init() {

        $("#query").click(function(){
            load();
        });

        $(ID_GRID_SHIPS).flexigrid({
            width: 300,
            url: "/gaesimple/query",
            method: "POST",
            dataType: "json",
            colModel : [
                {display: "No.", name : "ship_no", width : 50, sortable : true, align: "center"},
                {display: "艦種", name : "ship_type", width : 50, sortable : true, align: "center"},
                {display: "艦名", name : "ship_name", width : 70, sortable : true, align: "center"},
                {display: "レア度", name : "rarity", width : 60, sortable : false, align: "center"}],
            autoload: false,
            height: 300
        });

        $(ID_GRID_EVENT2).flexigrid({
            width: 500,
            url: "/gaesimple/query",
            method: "POST",
            dataType: "json",
            colModel : [
                {display: "ID", name : "id", width : 50, sortable : true, align: "center"},
                {display: "海域A", name : "area_a", width : 70, sortable : true, align: "center"},
                {display: "海域B", name : "area_b", width : 70, sortable : true, align: "center"},
                {display: "海域C", name : "area_c", width : 70, sortable : false, align: "center"},
                {display: "海域D", name : "area_d", width : 70, sortable : false, align: "center"},
                {display: "海域G", name : "area_g", width : 70, sortable : false, align: "center"}],
            autoload: false,
            height: 300
        });

        $(ID_GRID_EVENT3).flexigrid({
            width: 590,
            url: "/gaesimple/query",
            method: "POST",
            dataType: "json",
            colModel : [
                {display: "ID", name : "id", width : 50, sortable : true, align: "center"},
                {display: "海域A", name : "area_a", width : 70, sortable : true, align: "center"},
                {display: "海域B", name : "area_b", width : 70, sortable : true, align: "center"},
                {display: "海域C", name : "area_c", width : 70, sortable : false, align: "center"},
                {display: "海域D", name : "area_d", width : 70, sortable : false, align: "center"},
                {display: "海域F", name : "area_f", width : 70, sortable : false, align: "center"},
                {display: "海域H", name : "area_h", width : 70, sortable : false, align: "center"}],
            autoload: false,
            height: 300
        });

        $("div.flexigrid").css("font-size", "100%");

        $(ID_FILE_TYPE).change(function(){
            var value = $(this).val();
            if (value == FILE_TYPE_SHIPS) {
                $(ID_SORT_TYPE_AREA).show();
            } else {
                $(ID_SORT_TYPE_AREA).hide();
            }
            toggleGridView();
        });

        toggleGridView();
    };

    function toggleGridView() {
        var fileType = $("#file_type").val();
        if (fileType == FILE_TYPE_SHIPS) {
            $(ID_GRID_SHIPS_AREA).show();
            $(ID_GRID_EVENT2_AREA).hide();
            $(ID_GRID_EVENT3_AREA).hide();
        } else if (fileType == FILE_TYPE_EVENT2) {
            $(ID_GRID_SHIPS_AREA).hide();
            $(ID_GRID_EVENT2_AREA).show();
            $(ID_GRID_EVENT3_AREA).hide();
        } else {
            $(ID_GRID_SHIPS_AREA).hide();
            $(ID_GRID_EVENT2_AREA).hide();
            $(ID_GRID_EVENT3_AREA).show();
        }
    }

    function load() {
        var fileType = $("#file_type").val();
        var grid;
        if (fileType == FILE_TYPE_SHIPS) {
            grid = $(ID_GRID_SHIPS);
        } else if (fileType == FILE_TYPE_EVENT2) {
            grid = $(ID_GRID_EVENT2);
        } else {
            grid = $(ID_GRID_EVENT3);
        }

        setLoadParams(grid);
        grid.flexReload();
    }

    function setLoadParams(grid) {
        var loadCond = [
            {name: "file_type", value: $("#file_type").val()},
            {name: "sort_type", value: $("#sort_type").val()}
        ];

        grid.flexOptions({
            params: loadCond
        });
        return true;
    }

})(jQuery);
