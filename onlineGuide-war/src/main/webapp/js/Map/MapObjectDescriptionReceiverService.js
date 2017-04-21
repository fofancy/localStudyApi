/**
 * Created by shaylin3 on 10.04.2017.
 */


angular.module('Map')
    .factory('MapObjectDescriptionReceiverService', [
        '$http',
        function($http){
            var self;
            function MapObjectDescriptionReceiverService() {

            }

            MapObjectDescriptionReceiverService.prototype.receiveDescription = function(mapObjectId) {
                $http({
                    method: 'GET',
                    url: '/OnlineGuide/map-object-info',
                    contentType: 'application/json',
                    params: {id : mapObjectId}
                }).then(function (response) {
                    return response.data;

                }, function (reject) {
                    console.log(reject);
                });
            };

            return new MapObjectDescriptionReceiverService();
        }]);