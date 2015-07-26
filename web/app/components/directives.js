'use strict';

/* Directives */

angular.module('myAppRename.directives', [])
  . directive('studentDetails', function () {
    return {
      restrict: 'E',
      replace: 'true',
      templateUrl : "../directiveTemplates/studentStudypoints.html"
    };
  })
.directive('studyPointDetails',function(){
    return {
      restrict: 'E',
      replace: 'true',
      //templateUrl : "../directiveTemplates/detailsTemplate.html"
      templateUrl : "app/directiveTemplates/detailsTemplate.html"
    };
  });
