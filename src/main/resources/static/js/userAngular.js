/**
 * Created by horvste on 3/5/16.
 */

var messagingApp = angular.module('messagingApp', []);

messagingApp.controller('userController', function ($scope, $http, $interval, $window) {
    $http.get("/user/message/individual-message/listUsers/")
        .then(function(response) {
            $scope.users = response.data;
        });

    $scope.displayMessages = function(firstUserId){
        var url = '/user/message/individual-message/listBySingleUserId?userId=' + firstUserId;
        $scope.currentUserId = firstUserId;
        $http.get(url)
            .then(function(response) {
                $scope.messages = response.data;
            });
    };

    //currently broken function needs fixing
    $scope.sendMessage = function(){
        var url = '/user/message/individual-message/insert';

        var data = $.param({
            toUserId: $scope.currentUserId,
            message: $scope.currentMessage
        });

        var headers = $.param({ 'Content-Type': 'application/x-www-form-urlencoded' });

        $http.put(url, data)
            .success(function (data, status, headers) {
                $scope.ServerResponse = data;
            })
            .error(function (data, status, header, config) {
                $scope.ServerResponse =  htmlDecode("Data: " + data +
                    "\n\n\n\nstatus: " + status +
                    "\n\n\n\nheaders: " + header +
                    "\n\n\n\nconfig: " + config);
            });
    };

    $scope.hello = function(){
        alert('hello');
    };

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

