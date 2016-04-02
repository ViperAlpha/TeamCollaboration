/**
 * Created by horvste on 3/5/16.
 */

var messagingApp = angular.module('messagingApp', ['ngSanitize']);

messagingApp.controller('userController', function ($scope, $http, $interval, $window) {
    $scope.currentUserId = null;
    $scope.currentName = null;
    $scope.currentInvite = null;
    $scope.invitedUsername = null;
    $scope.showFileUpload = false;

    $http.get('/team/invitation/mine')
        .then(function (response) {
            $scope.teaminvitations = response.data;
        });

    $http.get("/user/invitation/see/accepted")
        .then(function (response) {
            $scope.users = response.data;
        });

    $http.get('/user/invitation/mine')
        .then(function (response) {
            $scope.invitations = response.data;
        });

    $scope.displayMessages = function (user) {
        $scope.currentUserId = user.userId;
        $scope.currentName = user.firstName + ' ' + user.lastName;
        var url = '/user/message/individual-message/listBySingleUserId?userId=' + $scope.currentUserId;
        $http.get(url)
            .then(function (response) {
                $scope.messages = response.data;
            });
    };

    $scope.getTeamMessages = function (user, team) {
        var url = "/team/message/list/new/message?teamId=" + team.teamId;

        $http.get(url)
            .then(function (response) {
                $scope.teammessages = response.data;
            });
    };

    $scope.getNewTeamMessages = function (user, team) {

        var url = "/team/message/list/all/message?teamId=" + team.teamId;

        $http.get(url)
            .then(function (response) {
                $scope.newteammessages = response.data;
            });


    };

    $scope.displayFirstMessage = function (user) {
        if ($scope.currentUserId !== null)
            return;
        $scope.displayMessages(user);
    };

    $scope.sendInvitation = function () {
        var data = $.param({
            name: $scope.invitedUsername,
            message: $scope.inviteMessage
        });

        $http.put('/user/invitation/invite', data, config)
            .success(function (data, status, headers, config) {
                alert('sent invite');
            })
            .error(function (data, status, header, config) {
                alert('error');
            });
    };

    $scope.setCurrentInvite = function (invite) {
        $scope.currentInvite = invite;
    };

    $scope.setCurrentInvite = function (invite) {
        $scope.currentTeamInvite = invite;
    };

});

/**
 * Should eventually migrate over to angular but this was just an easier solution in this case.
 *
 */
$(document).ready(function () {
    var inputId = "#input-userName";
    var inputIndvidual = "#input-individual";

    function autocompleteWithInput(inputId) {
        return {
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
        };
    }

    $(inputId).autocomplete(autocompleteWithInput(inputId));
    $(inputIndvidual).autocomplete(autocompleteWithInput(inputIndvidual));


    $("#sendIndvidualInvite").click(function (e) {
        var usernameChosen = $("#input-individual").val();
        var message = $("#message").val();

        var queryAsJson = {
            name: usernameChosen,
            message: message
        };
        queryAsJson = JSON.stringify(queryAsJson);

        $.ajax({
            type: 'PUT',
            url: '/user/invitation/invite',
            data: queryAsJson,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            error: function (xhr, textStatus, errorThrown) {
                alert('Error: ' + xhr.responseText);
            },
            success: function (data) {
                alert('Invitation Sent');
            }
        });
    });

    $("#acceptInvitation").click(function (e) {
        var currentInviteFromId = $("#currentInviteFromId").val();
        var currentInviteId = $("#currentInviteId").val();

        var queryAsJson = {
            fromUserId: currentInviteFromId,
            userInvitationId: currentInviteId
        };

        queryAsJson = JSON.stringify(queryAsJson);

        $.ajax({
            type: 'PUT',
            url: '/user/invitation/accept',
            data: queryAsJson,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            error: function (xhr, textStatus, errorThrown) {
                alert('Error: ' + xhr.responseText);
            },
            success: function (data) {
                alert('Invitation Accepted');
            }
        });

    });


});

