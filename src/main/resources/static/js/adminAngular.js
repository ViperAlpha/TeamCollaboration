/**
 * Created by horvste on 4/6/16.
 */

var messagingApp = angular.module('messagingAdminApp', ['ngSanitize']);

messagingApp.controller('adminController', function ($scope, $http, $interval, $window, $timeout) {
    $scope.users = null;

    $scope.refreshUsers = function refreshUsers() {
        $http.get("/admin/users").then(function (response) {
            $scope.users = response.data;
        });
    };

    $scope.refreshUsers();

    $scope.deleteUser = function (user) {
        var deleteUserPostRequest = {
            url: "/admin/users/delete",
            method: 'POST',
            data: {'userId': user.userId}
        };
        $http(deleteUserPostRequest).then(function (response) {
            $scope.refreshUsers();
        });
    };


});
