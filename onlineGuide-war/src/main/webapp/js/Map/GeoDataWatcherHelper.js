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
            'MapObjectsQueueService',
            'GeoContextService', 
            '$rootScope', 
            '$http',
            function(MapObjectsQueueService, GeoContextService, $rootScope, $http){
                function GeoDataWatcherHelper(){
                    
                }

                function onUpdate(newValue, oldValue){
                    console.log(JSON.stringify(GeoContextService.coords));

                    function asoundNearestObject() {
                        if(!responsiveVoice.isPlaying()) {
                            $http({
                                method: 'POST',
                                url: '/OnlineGuide/nearest-objects',
                                contentType: 'application/json',
                                data: JSON.stringify(GeoContextService.coords)
                            }).then(function (response) {
                                var mapObjects = response.data;

                                mapObjects.forEach(function(mapObject) {
                                    if(!MapObjectsQueueService.contains(mapObject)) {
                                        MapObjectsQueueService.add(mapObject);
                                    }
                                });

                                var toSpeakObject = MapObjectsQueueService.getNextNotPlayedObject();
                                if(toSpeakObject) {
                                    $http({
                                        url: '/OnlineGuide/map-object-info',
                                        method: 'GET',
                                        params: {name: toSpeakObject.title}
                                    }).then(function (response) {
                                        responsiveVoice.speak(response.data.description, 'UK English Male',{onend: asoundNearestObject});
                                    }, function (reject) {

                                    });
                                }

                            }, function (reject) {

                            });
                        }
                    }

                    asoundNearestObject();
                }


                GeoDataWatcherHelper.prototype.init = function(){
                    $rootScope.$watch(
                        function(){
                            return GeoContextService.coords;
                        },
                        onUpdate
                    );
                };

                return new GeoDataWatcherHelper();
        }]);
