/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Service for manipulating with watcher which watches the geodata of user
 */

angular.module('Map')
        .factory('GeoDataWatcherHelper', [
            'GeoContextService', 
            '$rootScope', 
            '$http',
            function(GeoContextService, $rootScope, $http){
                function GeoDataWatcherHelper(){
                    
                }

                GeoDataWatcherHelper.prototype.init = function(){
                    $rootScope.$watch(
                        function(){
                            return GeoContextService.coords;
                        }, 
                        function(newValue, oldValue){
                            console.log(JSON.stringify(GeoContextService.coords));
                            // Send coords
                            $http({
                                method: 'POST',
                                url: '/OnlineGuide/nearest-objects',
                                contentType: 'application/json',
                                data: JSON.stringify(GeoContextService.coords)
                            }).then(function(response){
                                var mapObjects = response.data;

                                console.log(mapObjects);
                                
                                function readIt(){
                                    responsiveVoice.speak('Response was received','US English Female');
                                }

                                setTimeout(readIt,201);
                            }, function(response){
                                
                            });
                        }
                    );
                }
                return new GeoDataWatcherHelper();
        }]);
