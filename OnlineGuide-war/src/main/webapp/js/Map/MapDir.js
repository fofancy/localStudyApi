/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * Main directive for displaying the map 
 */


angular.module('Map')
        .directive('map', [
            'MAP',
            'MAP_API_IMPL',
            '$window',
            'MapApiFactory',
            function(MAP, MAP_API_IMPL, $window, MapApiFactory) {
            return {
                template: MAP.TEMPLATE,
                link: function(scope, element, attr){
                    
                    element.css({
                        position: 'absolute',
                        width: $window.innerWidth + 'px',
                        height: $window.innerHeight + 'px'
                    });
                    setTimeout(function() {
                        var mapApiImpl = MapApiFactory.createInstance(MAP_API_IMPL.value);
                    }, 201);
                    
                }
            };
        }]);