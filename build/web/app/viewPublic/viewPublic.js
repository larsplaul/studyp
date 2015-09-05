'use strict';

angular.module('myAppRename.viewPublic1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider

    .when('/view1', {
    templateUrl: 'app/viewPublic/viewPublic.html',
    controller:  "View1Ctrl"
  });
}])

.controller('View1Ctrl', function($scope) {

});