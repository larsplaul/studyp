'use strict';

/* Factories */

var app = angular.module('myAppRename.factories', []);

app.factory('restErrorHandler', function () {
    var handleErrors = function (data,status,$scope){
       $scope.error = data.error;
      if (status == 401) {
        //if (data.error == "jwt expired") {
          $scope.error = data.error;
          $scope.$emit("logOutEvent");
          
        //}
        //$scope.error = "You are not authenticated to request these data";
        return;
      }
      if (status == 403) {
       // if (data.error == "jwt expired") {
          $scope.$emit("logOutEvent");
        
        //}
        //$scope.error = "You are not authenticated to request these data";
        return;
      }
      //$scope.error = data.error;
    }
    return {
      handleErrors: handleErrors
    }
  });

  app.factory('authInterceptor', function ($rootScope, $q, $window) {
    return {
      request: function (config) {
        config.headers = config.headers || {};
        if ($window.sessionStorage.token) {
          config.headers.Authorization = 'Bearer ' + $window.sessionStorage.token;
        }
        return config;
      },
      responseError: function (rejection) {
        if (rejection.status === 401) {
          // handle the case where the user is not authenticated
        }
        return $q.reject(rejection);
      }
    };
  });

;