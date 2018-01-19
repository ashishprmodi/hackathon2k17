 $.ajax({
                        type: "GET",
                        url: "/backend/data/ajax1.htm",
                        success: function (data) {
                                $("#retriever").text(data);
                        }
                });