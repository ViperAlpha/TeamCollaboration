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
        $scope.modalMessageInvite = null;
        $scope.option = null;
        $scope.currentMessage = null;
        $scope.currentIndMessage = null;
        $scope.message = {};
        $scope.message.ind = null;
        $scope.loggedInUserInfo = null;


        var INDIVIDUAL = 'Individual';
        var TEAM = 'Team';
        var SEARCH_BAR_MODAL_ID = "#searchModal";

        $scope.options = [
            {id: 0, label: INDIVIDUAL},
            {id: 1, label: TEAM}
        ];

        $scope.teamSelect = null;
        $scope.teamSelects = null;

        $scope.showAvatarModal = function () {
            $("#uploadAvatarModal").modal('show');
        };


        $scope.updateTeamSelect = function (option) {
            var url = '/user/team';
            if (option.label !== TEAM) {
                $scope.teamSelect = null;
                $scope.teamSelects = null;
                return;
            }
            $http.get(url)
                .then(function (response) {
                    $scope.teamSelects = response.data;
                    if ($scope.teamSelect.length > 0)
                        $scope.teamSelect = $scope.teamSelects[0];
                });
        };

        $scope.sendSearchInvite = function () {

            $http({
                method: "PUT",
                url: "/user/invitation/searchBarInvite",
                data: {
                    teamOrIndv: $scope.option.label,
                    teamName: $("#teamSelectedId option:selected").text(),
                    message: $scope.modalMessageInvite,
                    name: $scope.searchBarAtTopUsername
                }
            })
                .success(function (data, status, headers, config) {
                    $(SEARCH_BAR_MODAL_ID).modal('hide');
                    alert('sent invite');
                })
                .error(function (data, status, header, config) {
                    $(SEARCH_BAR_MODAL_ID).modal('hide');
                    alert(data.message);
                });
        };

        $scope.openInviteModal = function () {
            $scope.searchBarAtTopUsername = $("#search-bar-at-top").val();

            $http.get('/user/valid?username=' + $scope.searchBarAtTopUsername)
                .success(function (data, status, headers, config) {
                    $(SEARCH_BAR_MODAL_ID).modal('show');
                })
                .error(function (data, status, header, config) {
                    alert('This username was not found.');
                });
        };

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

        $scope.sendIndMessage = function () {
            var FILE_UPLOAD_ID = "#fileUpload";

            console.log("Send Individual Message: " + $scope.message.ind);
            if ($scope.message.ind === null)
                return;
            if ($scope.message.ind === '')
                return;

            var fileObj = $(FILE_UPLOAD_ID)[0].files[0];
            if (fileObj === null || fileObj === undefined) {
                $http({
                    method: "POST",
                    url: "/user/message/individual-message/insert",
                    data: {
                        toUserId: $scope.currentUserId,
                        message: $scope.message.ind
                    }
                })
                    .success(function (data, status, headers, config) {
                        $scope.message.ind = '';
                    })
                    .error(function (data, status, header, config) {
                        alert('Error Sending Individual Message');
                    });
                return;
            }

            var formData = new FormData();
            formData.append("toUserId", $scope.currentUserId);
            formData.append("message", $scope.message.ind);
            formData.append("fileUpload", fileObj);

            function resetFile() {
                $(FILE_UPLOAD_ID).val("");
            }

            $.ajax({
                method: "POST",
                url: "/user/message/individual-message/insertWithFile",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    $(FILE_UPLOAD_ID)[0].files[0] = null;
                    resetFile();
                    $scope.message.ind = '';
                },
                error: function (data) {
                    alert('Error Sending Individual Message');
                    console.log(JSON.stringify(data));
                    resetFile();
                }
            });

        };

        $scope.sendTeamMessageFunc = function () {
            var TEAM_MESSAGE_ID = '#tmessage';
            var currentMessageContent = $(TEAM_MESSAGE_ID).val();

            if (currentMessageContent === '')
                return;

            var toTeamId = $('#ttoTeamId').val();
            var TEAM_FILE_UPLOAD_ID = "#teamFileUpload";
            var fileObj = $(TEAM_FILE_UPLOAD_ID)[0].files[0];
            if (fileObj === null || fileObj === undefined) {
                $http({
                    method: "POST",
                    url: "/user/team/message/insert",
                    data: {
                        toTeamId: toTeamId,
                        message: currentMessageContent
                    }
                })
                    .success(function (data, status, headers, config) {
                        $(TEAM_MESSAGE_ID).val('');
                    })
                    .error(function (data, status, header, config) {
                        alert('Error Sending Team Message');
                    });
                return;
            }

            var formData = new FormData();
            formData.append("teamId", $scope.currentTeam.teamId);
            formData.append("message", currentMessageContent);
            formData.append("fileUpload", fileObj);

            function resetFile() {
                $(TEAM_FILE_UPLOAD_ID).val("");
            }

            $.ajax({
                method: "POST",
                url: "/user/team/message/insertWithFile",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    $(TEAM_MESSAGE_ID).val('');
                    resetFile();
                },
                error: function (data) {
                    alert('Error Sending Team Message');
                    resetFile();
                }
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
                    alert(data.message);
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
                } else if ($scope.typemsgselected === TEAM) {
                    $scope.updateTeamMessageVar();
                } else {
                    return;
                }
                $scope.intervalFunction();
            }, 1000)
        };

        // Kick off the interval
        $scope.intervalFunction();

        // Collect the information of the current logged in user
        $http.get('/user/get/current/user')
            .then(function (response) {
                $scope.loggedInUserInfo = response.data;
            });

    }
);


/**
 * Should eventually migrate over to angular but this was just an easier solution in this case.
 *
 */
$(document).ready(function () {
    var inputId = "#input-userName";
    var inputIndvidual = "#input-individual";
    var inputSearchBar = "#search-bar-at-top";

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


    function autocompleteTopSearchBar(inputId) {
        return {
            source: function (request, response) {
                $.ajax({
                    type: "GET",
                    url: "/user/search",
                    data: {
                        q: $(inputId).val()
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        alert('Error: ' + xhr.responseText);
                    },
                    success: function (data) {
                        data = JSON.parse(data);
                        response($.map(data, function (item) {
                            return {
                                label: item.username
                            }
                        }));
                    }
                });
            }
        };
    }


    $(inputId).autocomplete(autocompleteWithInput(inputId));
    $(inputIndvidual).autocomplete(autocompleteWithInput(inputIndvidual));
    $(inputSearchBar).autocomplete(autocompleteTopSearchBar(inputSearchBar));

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

    // Code for the search panel with dropdown on the left
    $('.search-panel #search-dropdown-menu').find('a').click(function (e) {
        e.preventDefault();
        var param = $(this).attr("href").replace("#", "");
        var concept = $(this).text();
        $('.search-panel span#search_concept').text(concept);
        $('.input-group #search_param').val(param);
    });

    $('[data-toggle="offcanvas"]').click(function () {
        $('.row-offcanvas').toggleClass('active')
    });

    $("#saveAsAvatarPictureButton").click(function (e) {
        var AVATAR_FILE_ID = "#file-avatar-picture";
        var fileObj = $(AVATAR_FILE_ID)[0].files[0];
        if (fileObj === null || fileObj === undefined) {
            alert('No File Present');
        }

        var formData = new FormData();
        formData.append("fileUpload", fileObj);

        function resetFile() {
            $(AVATAR_FILE_ID).val("");
        }

        $.ajax({
            method: "POST",
            url: "/user/settings/avatarUpload",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                $(TEAM_MESSAGE_ID).val('');
                resetFile();
            },
            error: function (data) {
                alert('Error Sending Team Message');
                resetFile();
            }
        });
    });

});

