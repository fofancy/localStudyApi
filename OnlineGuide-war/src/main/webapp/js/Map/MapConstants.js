/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


angular.module('Map')
        .constant('MAP', {
            'TEMPLATE' : '<div id=\'map\'></div>'
        })
        .constant('MAP_API_IMPL', {
            'value' : 'OsmApiImpl'
        });