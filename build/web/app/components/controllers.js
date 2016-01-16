var app = angular.module('myAppRename.controllers', []);

  app.controller('AppCtrl', function ($scope, $http, $window,$location,$rootScope) {

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
      $scope.error = {
          message: "Your Token timed out. Please login again"
      };
      $scope.logout();
    });

    $scope.isActive = function (viewLocation) {
      return viewLocation === $location.path();
    };
    $scope.user = {};
    
    $scope.user.useFronter = ($window.localStorage.useFronter === "true") ;

    $scope.title = "Study Points";
    $scope.username = "";
    //TODO --> Fix Authentication
    $scope.isAuthenticated = false;
    $scope.isAdmin = false;
    $scope.isUser = false;
    $scope.message = '';
   // $scope.error = null;
    $scope.showLogonSpinner = false;
    
    $scope.useFronterClicked = function(){
      $window.localStorage.useFronter = $scope.user.useFronter;
    };

    $scope.submit = function () {
      $scope.showLogonSpinner = true;
      $http
        .post('api/login', $scope.user)
        .success(function (data, status, headers, config) {
          $scope.showLogonSpinner = false;
          $window.sessionStorage.token = data.token;
          $scope.isAuthenticated = true;
          var encodedProfile = data.token.split('.')[1];
          var profile = JSON.parse(url_base64_decode(encodedProfile));
          $scope.username = profile.username;
          $scope.isAdmin = profile.role === "Admin";
          $scope.isUser = profile.role === "User";
          $scope.error = null;
          $location.path("#/view1");
        })
        .error(function (data, status, headers, config) {
          // Erase the token if the user fails to log in
          delete $window.sessionStorage.token;
          $scope.isAuthenticated = false;
          $scope.showLogonSpinner = false;
          //$scope.error = $rootScope.error;
          $scope.error = data.error;
          $scope.error.description= null; //No need for more info than what is provided in message
          $scope.logout();  //Clears an eventual error message from timeout on the inner view
        });
    };

    $scope.logout = function () {
      $scope.isAuthenticated = false;
      $scope.isAdmin =false;
      $scope.isUser = false;
      delete $window.sessionStorage.token;
      $location.path("#/view1");
    };

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


app.controller('warningDeleteNameCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
      $modalInstance.close("CANCEL");
    };
    $scope.delete = function () {
      $modalInstance.close("DELETE");
    };
  });




