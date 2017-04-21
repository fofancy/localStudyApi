/*
 * The factory that creates differenet instances for various providers
 */

angular.module('Map')
        .factory('MapApiFactory', [
            '$injector', 
            function($injector){
                var self;
                function MapApiFactory(){
                    self = this;
                };
                
                MapApiFactory.prototype.createInstance = function(mapApiImpl) {
                    return $injector.get(mapApiImpl);
                };
            
                return new MapApiFactory();
        }]);