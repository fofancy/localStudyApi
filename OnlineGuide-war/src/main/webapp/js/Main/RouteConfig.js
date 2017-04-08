/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('app')
        .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider){
            $routeProvider
                .otherwise({
                    templateUrl: 'templates/map.html',
                    controller: 'MapCtrl'
                });
                    
            $locationProvider.html5Mode({
                enabled: true,
                requireBase: false
            });
}]);
