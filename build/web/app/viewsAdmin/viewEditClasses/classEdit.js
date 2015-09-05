'use strict';

var app = angular.module('myAppRename.admin.editClass', ['ngRoute'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view_admin_editClass', {
      templateUrl: 'app/viewsAdmin/viewEditClasses/classEdit.html',
      controller: 'classEditCtrl'
    });
  }]);


app.controller('classEditCtrl', function ($scope, $http,restErrorHandler) {


  $http.get("api/admin/classes")
    .success(function (data, status, headers, config) {
      $scope.classes = data;
    })
    .error(function (data, status, headers, config) {
      restErrorHandler.handleErrors(data,status,$scope);
    })

  $scope.getClass = function (id) {
    $scope.selectedClassId = id;
    $http.get("api/admin/classesFullInfo/" + id)
      .success(function (data, status, headers, config) {
        
        $scope.selectedClass = data;
        $scope.selectedClass.students.forEach(function (student) {
          student.delete = false;
        });
      })
      .error(function (data, status, headers, config) {
        restErrorHandler.handleErrors(data,status,$scope);
      });
  };

  $scope.saveEditedClass = function(){
    var selectedClass = $scope.selectedClass;
    $scope.selectedClass = null;
    setTimeout(function() {
      //$http.put("adminApi/class/" + $scope.selectedClassId, selectedClass)
      $http.put("api/removeFromClass/" + $scope.selectedClassId, selectedClass)
        .success(function (data, status, headers, config) {

          $scope.getClass($scope.selectedClassId);
        })
        .error(function (data, status, headers, config) {
          restErrorHandler.handleErrors(data,status,$scope);
        })
    },500);
  }

});

