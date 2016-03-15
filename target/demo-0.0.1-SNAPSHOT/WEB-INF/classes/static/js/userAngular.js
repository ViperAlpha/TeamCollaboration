/**
 * Created by horvste on 3/5/16.
 */

var messagingApp = angular.module('messagingApp', []);

messagingApp.controller('userController', function ($scope, $http, $interval, $window) {
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

