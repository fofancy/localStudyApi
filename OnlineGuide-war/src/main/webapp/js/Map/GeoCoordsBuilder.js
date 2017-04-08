/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('Map')
        .factory('GeoCoordsBuilder', [function(){
            function Coords(latitude, longitude){
                this.latitude = latitude;
                this.longitude = longitude;
            };
            
            Coords.build = function(data){
                return new Coords(
                    data.latitude,
                    data.longitude 
                );
            };
            
            return Coords;
        }]);