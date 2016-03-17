/**
 * Created by horvste on 3/5/16.
 */

var messagingApp = angular.module('messagingApp', []);

messagingApp.controller('userController', function ($scope, $http, $interval, $window) {
    $scope.currentUserId = null;
    $scope.currentName = null;

    $http.get("/user/message/individual-message/listUsers/")
        .then(function(response) {
            $scope.users = response.data;
        });

    $scope.displayMessages = function(user){
        $scope.currentUserId = user.userId;
        $scope.currentName = user.firstName + ' ' + user.lastName;
        var url = '/user/message/individual-message/listBySingleUserId?userId=' + $scope.currentUserId;
        $http.get(url)
            .then(function(response) {
                $scope.messages = response.data;
            });
    };

    $scope.displayFirstMessage = function(user){
        if($scope.currentUserId !== null)
            return;
        $scope.displayMessages(user);
    }

});

/**
 * Should eventually migrate over to angular but this was just an easier solution in this case.
 *
 */
$(document).ready(function () {
    var inputId = "#input-userName";
    $(inputId).autocomplete({
        source: function (request, response) {
            $.ajax({
                type: "GET",
                url: "/user/invitation/autocomplete-user/list",
                data: {
                    usernameToAuto: $(inputId).val()
                },
                error: function (xhr, textStatus, errorThrown) {
                    alert('Error: ' + xhr.responseText);
                },
                success: function (data) {
                    data = JSON.parse(data);
                    response($.map(data, function (item) {
                        return {
                            label: item
                        }
                    }));
                }
            });
        }
    });
});

