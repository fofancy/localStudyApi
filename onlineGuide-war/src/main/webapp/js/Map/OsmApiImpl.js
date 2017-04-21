/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('Map')
        .service('OsmApiImpl', [
            'GeoContextService',
            'GeoDataWatcherHelper',
            function(GeoContextService, GeoDataWatcherHelper){
                var self;
                
                function drawMap() {
                     var coords = GeoContextService.coords;
                        GeoDataWatcherHelper.init();
                        
                        var map = L.map('map').setView([coords.latitude, coords.longitude], 13);

                        L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                            maxZoom: 18,
                            attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
                                '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                                'Imagery © <a href="http://mapbox.com">Mapbox</a>',
                            id: 'mapbox.streets'
                            }).addTo(map);
                            
                        var marker = L.marker([coords.latitude, coords.longitude]).addTo(map);
                }
                
                function OsmApiImpl(){
                    self = this;
                    
                    GeoContextService.init().then(function(){
                        drawMap();
                    },
                    function(errorMessage){
                        alert(errorMessage); 
                    });
                };
                
                OsmApiImpl.prototype.drawMap = drawMap;
            
                return OsmApiImpl();
        }]);