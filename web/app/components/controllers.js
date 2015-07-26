var app = angular.module('myAppRename.controllers', []);

  app.controller('AppCtrl', function ($scope, $http, $window,$location) {

    function url_base64_decode(str) {
      var output = str.replace('-', '+').replace('_', '/');
      switch (output.length % 4) {
        case 0:
          break;
        case 2:
          output += '==';
          break;
        case 3:
          output += '=';
          break;
        default:
          throw 'Illegal base64url string!';
      }
      return window.atob(output); //polifyll https://github.com/davidchambers/Base64.js
    }

    //Other controller emits the logOutEvent to force a logout
    $scope.$on('logOutEvent', function(event, args) {
      $scope.error= "Your session timed out. Please login again";
      $scope.logout();
    });

    $scope.isActive = function (viewLocation) {
      return viewLocation === $location.path();
    };

    $scope.title = "Study Points";
    $scope.username = "";
    //TODO --> Fix Authentication
    $scope.isAuthenticated = false;
    $scope.isAdmin = false;
    $scope.isUser = false;
    $scope.message = '';
    $scope.error = null;

    $scope.submit = function () {
      $http
        .post('api/login', $scope.user)
        .success(function (data, status, headers, config) {
          $window.sessionStorage.token = data.token;
          $scope.isAuthenticated = true;
          var encodedProfile = data.token.split('.')[1];
          var profile = JSON.parse(url_base64_decode(encodedProfile));
          $scope.username = profile.username;
          $scope.isAdmin = profile.role === "Admin";
          $scope.isUser = !$scope.isAdmin;
          $scope.error = null;
          $location.path("#/view1");
        })
        .error(function (data, status, headers, config) {
          // Erase the token if the user fails to log in
          delete $window.sessionStorage.token;
          $scope.isAuthenticated = false;
          $scope.error = 'You failed to login. Invalid User or Password';
          $scope.logout();  //Clears an eventual error message from timeout on the inner view
        });
    };

    $scope.logout = function () {
      $scope.isAuthenticated = false;
      $scope.isAdmin =false;
      $scope.isUser = false;
      delete $window.sessionStorage.token;
      $location.path("#/view1");
    }

    //This sets the login data from session store if user pressed F5 (You are still logged in)
    var init = function () {

      var token = $window.sessionStorage.token;
      if(token) {
        $scope.isAuthenticated = true;
        var encodedProfile = token.split('.')[1];
        var profile = JSON.parse(url_base64_decode(encodedProfile));
        $scope.username = profile.username;
        $scope.isAdmin = profile.role == "Admin";
        $scope.isUser = !$scope.isAdmin;
        $scope.error = null;
      }
    };
// and fire it after definition
    init();
  });


  //app.controller('StudentStudyPointCtrl', ['$scope', '$http','$routeParams', function ($scope, $http, $routeParams) {
  //
  //  $scope.name = $routeParams.name; //Only set from Admin View
  //
  //  $scope.showPeriod = function(id){
  //    $scope.period = getPeriod($scope.allPeriods, id);
  //    $scope.period = getPeriod($scope.allPeriods, id);
  //  }
  //
  //
  //  function getPeriod(array, periodId) {
  //    for(var i = 0 ; i < array.length; i++) {
  //      if (array[i].id === periodId)
  //        return array[i];
  //    }
  //    return null;
  //  }
  //
  //  $scope.getClass = function (id) {
  //    var urlForStudentsClass = typeof($routeParams.studentId) === "undefined" ? "userApi/class/"+id : "adminApi/class/"+id+"/"+$routeParams.studentId ;
  //    $http.get(urlForStudentsClass)
  //      .success(function (data, status, headers, config) {
  //        $scope.allPeriods = data;
  //        $scope.period = null;
  //      })
  //      .error(function (data, status, headers, config) {
  //        $scope.error = data.toString();
  //      })
  //  }
  //  //When this controller is used by a Teacher we pass studentId with the request since a teacher can get all students
  //  //For a student the studentsId is taken from the token on the server
  //  var urlForStudentsClasses = typeof($routeParams.studentId) === "undefined" ? "userApi/myClasses" : "adminApi/class/"+$routeParams.studentId ;
  //  $http.get(urlForStudentsClasses)
  //    .success(function(data,status,headers,config){
  //      $scope.classes = data;
  //      $scope.allPeriods = null;
  //      $scope.period = null;;
  //      $scope.error = null;
  //    })
  //    .error(function (data, status, headers, config) {
  //      if (status == 401) {
  //        $scope.error = "You are not authenticated to request these data";
  //        return;
  //      }
  //      $scope.error = data;
  //    });
  //}]);

app.controller('warningDeleteNameCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
      $modalInstance.close("CANCEL");
    };
    $scope.delete = function () {
      $modalInstance.close("DELETE");
    };
  });




