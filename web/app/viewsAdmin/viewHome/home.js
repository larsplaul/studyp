'use strict';

//Todo: Refactor into a separate factory or service
var users = [];

angular.module('myAppRename.admin.home', ['ngRoute'])

  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/view_admin_home', {
      templateUrl: 'app/viewsAdmin/viewHome/home.html',
      controller: 'AdminHomeCtrl'
    });
  }])
  .filter("showClasses",function(){
    return function(cl){
      if(cl==null)
        return;
      var list = [];
      cl.forEach(function(classInfo){
        list.push(classInfo.id);
      });
      return list.join(",");
    }
  })
  .controller('AdminHomeCtrl', function ($scope, $http, $modal,restErrorHandler) {
    $scope.usersToShow = [];
    $scope.itemsPerPage = 8;
    $scope.currentPage = 1;

    $scope.isAdding = false;


    $scope.multiSelectSettings = {
     enableSearch : true,
      buttonClasses : "btn btn-default btn-sm",
      scrollable: true,
      dynamicTitle: false,
      showCheckAll: false,
      showUncheckAll: false,
      closeOnSelect : true

    };
    $scope.multiSelectText = {
      buttonDefaultText: 'Select Classes'
      //dynamicButtonTextSuffix: "Classes"
    }
    $http.get("adminApi/class")
      .success(function (data, status, headers, config) {
        $scope.allclasses = [];
        if(data == null)
         return;
        data.forEach(function(classItem){
          $scope.allclasses.push({id : classItem._id, label: classItem.nameShort});
        });
      })
      .error(function (data, status, headers, config) {
        restErrorHandler.handleErrors(data,status,$scope);
      })

    $scope.getUsers = function (alwaysFetch) {
      if (users.length > 0 && !alwaysFetch) {
        $scope.users = users;
        return;
      }

      $scope.users = [];

      //$http.get("adminApi/class")
      //  .success(function (data, status, headers, config) {
      //    $scope.classes = data;
      //  })
      //  .error(function (data, status, headers, config) {
      //    scope.error = data.toString();
      //  })

      $http({method: 'GET', url: 'adminApi/user'})
        .success(function (data, status, headers, config) {
          //Refactor into a service
          //In order to use the multi select control classes must be converted to an object with an id with the value as value
          data.forEach(function(user){
            var classes = [];
            user.classes.forEach(function(classId){
              classes.push({id: classId});
            })
            user.classes = classes;
          })



          users = data;
          $scope.users = users;
          $scope.getUsersToShow();
          $scope.error = null;
        })
        .error(function (data, status, headers, config) {
          restErrorHandler.handleErrors(data,status,$scope);
        });
    };

    $scope.getUsersToShow = function () {
      var begin = (($scope.currentPage - 1) * $scope.itemsPerPage);
      var end = begin + $scope.itemsPerPage;
      $scope.usersToShow = $scope.users.slice(begin, end);
    };

    $scope.getUsers(false);
    $scope.getUsersToShow();


    $scope.showAddUser = function () {
      $scope.isAdding = true;
      $scope.newUser = {};
      $scope.addEditHeading = "Add new User";
      $scope.newUser.role = "User";
      $scope.newUser.classes= [];
    }

    $scope.cancel = function () {
      $scope.newUser = {};
      $scope.isAdding = false;
      $scope.isEditing = false;
    }

    $scope.saveUser = function () {
      var method = $scope.newUser._id ? "PUT" : "POST";

      //Change the classes array back into a simple list of ID's
      var list = [];
      $scope.newUser.classes.forEach(function(item){
        list.push(item.id);
      })
      $scope.newUser.classes = list;


      $http({method: method, url: 'adminApi/user', data: $scope.newUser})
        .success(function (data, status, headers, config) {
          $scope.getUsers(true);
          if (method == "POST") {
            $scope.showAddUser();
          }
          else {
            $scope.isEditing = false;
            $scope.newUser = {};
          }
        })
        .error(function (data, status, headers, config) {
          restErrorHandler.handleErrors(data,status,$scope);
        })
    }

    $scope.edit = function (userToEdit) {;
      $scope.newUser = angular.copy(userToEdit);
      $scope.addEditHeading = "Edit " + userToEdit.user;
      $scope.isAdding = true;
      $scope.isEditing = true;
    }

    $scope.delete = function (id) {
      var modalInstance = $modal.open({
        templateUrl: 'warningDelete.html',
        controller: 'warningDeleteNameCtrl',
        backdrop: "static",
        size: "sm"
      });

      modalInstance.result.then(function (selection) {
        if (selection === "DELETE") {
          $http.delete("adminApi/user/" + id)
            .success(function (data, status, headers, config) {
              $scope.getUsers(true);
            })
            .error(function (data, status, headers, config) {
              restErrorHandler.handleErrors(data,status,$scope);
            })
        }
      })
    }
  })
  .controller('warningDeleteNameCtrl', function ($scope, $modalInstance) {
    $scope.cancel = function () {
      $modalInstance.close("CANCEL");
    };
    $scope.delete = function () {
      $modalInstance.close("DELETE");
    };
  });








