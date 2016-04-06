/**
 * Created by horvste on 4/6/16.
 */

var messagingApp = angular.module('messagingAdminApp', ['ngSanitize']);

messagingApp.controller('adminController', function ($scope, $http, $interval, $window, $timeout) {
    $scope.users = null;
    $http.get("/admin/users").then(function (response) {
        $scope.users = response.data;
    });
});
