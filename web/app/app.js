'use strict';

// Declare app level module which depends on views, and components
angular.module('myAppRename', [
  'ngRoute',
  'ui.bootstrap',
  'angularUtils.directives.dirPagination',
  'myAppRename.controllers',
  'myAppRename.directives',
  'myAppRename.factories',
  'myAppRename.filters',
  'myAppRename.viewPublic1',
  'myAppRename.user.home',
  'myAppRename.admin.home',
  'myAppRename.admin.studypoint',
  'myAppRename.admin.studyPointsForStudent',
  'myAppRename.admin.editClass',
  'myAppRename.viewUsersDetails',
  //'myAppRename.admin.gridDemo',
  'angularjs-dropdown-multiselect'
]).
config(['$routeProvider', function($routeProvider) {
    $routeProvider.otherwise({redirectTo: '/view1'});
}])
.config(function ($httpProvider) {
   $httpProvider.interceptors.push('authInterceptor');
  });



