/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Service for working with geolocation of user
 */


angular.module('Map')
        .factory('GeoContextService', [
            'GeoCoordsBuilder', 
            '$q', 
            '$interval', 
            function(GeoCoordsBuilder, $q, $interval){
                var self;
                function GeoContextService(){
                    self = this;
                }
                
                // In order not to duplicate code was created this function
                // It was used:
                // 1) to initially get coords and to trigger the higher code after 
                //  initialization primary data
                // 2) for periodically updating current geoposition
                function getCurrentPositionWrapper(callback){
                    navigator.geolocation.watchPosition(function(position){
                        self.coords = GeoCoordsBuilder.build({
                            latitude : position.coords.latitude,
                            longitude : position.coords.longitude
                        });

                        if(callback && typeof callback === "function")
                            callback();
                    })
                }
                
                // Gets initial geoposition for triggering the code of higher invoking function
                function getInitGeoPosition(){
                    return $q(function(resolve, reject) {
                        if (navigator.geolocation) {
                            getCurrentPositionWrapper(resolve);
                        } else {
                            reject("Geolocation is not supported by this browser.");
                        }
                    });
                }
                
                function initSurroundingObjects(){
                    
                }
            
                // It sets an inital value for all data and starts periodical updating of coords
                // Thus all depends on coords changing
                // In service GeoDataWatcherHelper in case of coords changing all geodata will 
                // be updated and sent to back
                GeoContextService.prototype.init = function(){
                    initSurroundingObjects();
                    $interval(getCurrentPositionWrapper(), 100);
                    return getInitGeoPosition();
                }
            
            return new GeoContextService();
        }]);