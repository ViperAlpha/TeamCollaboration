/**
 * Created by horvste on 3/5/16.
 */

var messagingApp = angular.module('messagingApp', ['ngSanitize']);

messagingApp.controller('userController', function ($scope, $http, $interval, $window, $timeout) {
    $scope.currentUserId = null;
    $scope.currentName = null;
    $scope.currentInvite = null;
    $scope.invitedUsername = null;
    $scope.showFileUpload = false;
    $scope.autocompleteData = null;
    $scope.currentTeam = null;

    var INDIVIDUAL = 'Individual';
    var TEAM = 'Team';

    $scope.typemessages = [
        {id: 1, name: INDIVIDUAL}, {id: 2, name: TEAM}
    ];
    $scope.typemsgselected = $scope.typemessages[0].name;

    $scope.switchTypeMessage = function (t) {
        $scope.typemsgselected = t.name;
    };

    $http.get('/user/team/invitation/mine')
        .then(function (response) {
            $scope.teaminvitations = response.data;
        });

    $http.get("/user/invitation/see/accepted")
        .then(function (response) {
            $scope.users = response.data;
        });

    $http.get("/user/team/list")
        .then(function (response) {
            $scope.teams = response.data;
        });

    $http.get('/user/invitation/mine')
        .then(function (response) {
            $scope.invitations = response.data;
        });

    $scope.updateMessageVar = function () {
        if ($scope.currentUserId === null)
            return;

        var url = '/user/message/individual-message/listBySingleUserId?userId=' + $scope.currentUserId;
        $http.get(url)
            .then(function (response) {
                $scope.messages = response.data;
            });
    };

    $scope.getTeamMessages = function (team) {
        $scope.currentTeam = team;
        var url = "/user/team/message/list/all/message?teamId=" + team.teamId;

        $http.get(url)
            .then(function (response) {
                $scope.teammessages = response.data;
            });
    };

    $scope.getNewTeamMessages = function (team) {
        $scope.currentTeam = team;
        var url = "/user/team/message/list/all/message?teamId=" + team.teamId;

        $http.get(url)
            .then(function (response) {
                $scope.newteammessages = response.data;
            });


    };

    $scope.displayMessages = function (user) {
        $scope.currentUserId = user.userId;
        $scope.currentName = user.firstName + ' ' + user.lastName;
        $scope.updateMessageVar();
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

    $scope.setCurrentTeamInvite = function (invite) {
        $scope.currentTeamInvite = invite;
    };

    $scope.nameStartsWith = function (currentName, messageFirstName) {
        return currentName.startsWith(messageFirstName);
    };

    $scope.updateTeamMessageVar = function () {
        if ($scope.currentTeam === null)
            return;

        $scope.getTeamMessages($scope.currentTeam);
    };

    $scope.autocompleteSearchBar = function (text) {
        var data = $.param({
            q: $scope.text
        });

        $http.get('/user/search', data, config)
            .success(function (data, status, headers, config) {
                $scope.autocompleteData = data;
            })
            .error(function (data, status, header, config) {
                alert('error');
            });
    };

    $scope.intervalFunction = function () {
        $timeout(function () {
            if ($scope.typemsgselected === INDIVIDUAL) {
                $scope.updateMessageVar();
            } else {
                $scope.updateTeamMessageVar();
            }
            $scope.intervalFunction();
        }, 1000)
    };

    // Kick off the interval
    $scope.intervalFunction();

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


    $("#sendTeamInvite").click(function (e) {
        var teamInvitedTo = $("#teamToAddTo option:selected").attr('id');
        var invitedUsername = $("#invitedUserName").val();
        var message = $('#message').val();

        var queryAsJson = {
            teamId: teamInvitedTo,
            invitedUserName: invitedUsername,
            message: message
        };

        queryAsJson = JSON.stringify(queryAsJson);

        $.ajax({
            type: 'POST',
            url: '/user/team/invite/send',
            data: queryAsJson,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            error: function (xhr, textStatus, errorThrown) {
                alert('Error: ' + textStatus + " ..... " + errorThrown + " ... " + xhr.responseText);
            },
            success: function (data) {
                alert('Invitation sent');
            }
        });

    });

    $("#acceptTeamInvite").click(function (e) {
        var teamInvitationId = $("#teamInvitationId").val();

        var data = {
            teamInvitationId: teamInvitationId,
        };

        data = JSON.stringify(data);

        $.ajax({
            type: 'POST',
            url: '/user/team/invite/accept',
            data: data,
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


    //send individual message
    //$('#sendind').click(function (e){
    //
    //    e.preventDefault();
    //
    //    var toUserId = $('#itoUserId').val();
    //    var message = $('#imessage').val();
    //
    //    var data = {
    //        toUserId : toUserId,
    //        message : message
    //    }
    //
    //    data = JSON.stringify(data);
    //    alert('sending msg');
    //    console.log(data);
    //
    //    $.ajax({
    //        type: 'POST',
    //        url: '/user/message/individual-message/insert',
    //        data: data,
    //        dataType: 'json',
    //        contentType: 'application/json; charset=utf-8',
    //        error: function (xhr, textStatus, errorThrown) {
    //            alert('Error while sending the message.');
    //            console.log(textStatus + ' ... '  + errorThrown);
    //        },
    //        success: function (data) {
    //            console.log(data);
    //        }
    //    });
    //
    //});


});

