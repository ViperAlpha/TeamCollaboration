var messagingApp2 = angular.module('messagingApp');


messagingApp2.controller('wikiController', function ($scope, $http, $interval, $window, $timeout) {
    console.log("enter ");

    $scope.userToShowWiki = null;

    $scope.displayWiki = function(user){
        alert(user.firstName);
        $scope.userToShowWiki = user;
    }

});
