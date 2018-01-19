 var map, heatmap;

      function initMap() {
		  
		var data={};
		data.url=proxyUrl+"heat";
		data.method="GET";
		data.contenttype="application/json";
		data.datatype="json"; 
		
		$.ajax({
			type: data.method,
			contentType: data.contenttype,
			url: data.url,
			dataType: data.datatype, 
		    success: function(resp){
					var response = resp.latLong;
					var array = [];
					for(var i=0;i<response.length;i++){
						var latLong = response[i].split(",");
						var mapPoint = new google.maps.LatLng(latLong[0], latLong[1]);
						array[i]=mapPoint;
						mapPoint=null;
						latLong=null;
					}
					var latLongCenter = response[Math.round(response.length/2)];
				    map = new google.maps.Map(document.getElementById('map'), {
						zoom: 13,
						center: {lat: parseFloat(latLongCenter.split(",")[0]), lng: parseFloat(latLongCenter.split(",")[1])},
						mapTypeId: 'satellite'
					});

					heatmap = new google.maps.visualization.HeatmapLayer({
					  data: array,
					  map: map
					});
			
		},
			error: function (e) {
			   
			}
		});    
      }

      function toggleHeatmap() {
        heatmap.setMap(heatmap.getMap() ? null : map);
      }

      function changeGradient() {
        var gradient = [
          'rgba(0, 255, 255, 0)',
          'rgba(0, 255, 255, 1)',
          'rgba(0, 191, 255, 1)',
          'rgba(0, 127, 255, 1)',
          'rgba(0, 63, 255, 1)',
          'rgba(0, 0, 255, 1)',
          'rgba(0, 0, 223, 1)',
          'rgba(0, 0, 191, 1)',
          'rgba(0, 0, 159, 1)',
          'rgba(0, 0, 127, 1)',
          'rgba(63, 0, 91, 1)',
          'rgba(127, 0, 63, 1)',
          'rgba(191, 0, 31, 1)',
          'rgba(255, 0, 0, 1)'
        ]
        heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);
      }

      function changeRadius() {
        heatmap.set('radius', heatmap.get('radius') ? null : 20);
      }

      function changeOpacity() {
        heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);
      }

    