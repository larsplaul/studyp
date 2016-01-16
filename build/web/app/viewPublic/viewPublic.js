'use strict';

angular.module('myAppRename.viewPublic1', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider

    .when('/view1', {
    templateUrl: 'app/viewPublic/viewPublic.html',
    controller:  "View1Ctrl"
  });
}])

.controller('View1Ctrl', function($scope,$http) {
 $http.get("https://jokes-plaul.rhcloud.com/api/joke").then(function(res){
          var joke =res.data.joke;
          $scope.ref = res.data.reference;
          $scope.joke = joke.replace(/(?:\r\n|\r|\n)/g, '<br />');
        });
});