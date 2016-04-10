var messagingApp2 = angular.module('messagingApp', ['ngSanitize']);


messagingApp2.controller('wikiController', function ($scope, $http, $interval, $window, $timeout) {
    $scope.wikiOutput = null;
    $scope.currentlySelectedWikiOutput = null;
    $scope.needsFirstEntry = null;
    $scope.content = {};
    $scope.isFirstLoad = true;
    $scope.editIsEnabled = false;

    function updateWikiOutput() {
        $http.get('/user/content/api/wiki').then(function (response) {
            $scope.wikiOutput = response.data;
            $scope.needsFirstEntry = $scope.wikiOutput.length === 0;
            if (!$scope.needsFirstEntry && $scope.isFirstLoad) {
                $scope.content.wikiText = $scope.wikiOutput[$scope.wikiOutput.length - 1].wiki.content;
                $scope.isFirstLoad = false;
            }
            console.log($scope.wikiOutput);
            console.log($scope.needsFirstEntry);
        });
    }

    updateWikiOutput();

    $scope.sendChanges = function () {
        $http({
            method: "PUT",
            url: "/user/content/api/wiki",
            data: {
                content: $scope.content.wikiText
            }
        })
            .success(function (data, status, headers, config) {
                $scope.editIsEnabled = false;
            })
            .error(function (data, status, header, config) {
                alert('Error Updating Content');
            });
    };

    $scope.setEditIsEnabledToTrue = function () {
        $scope.editIsEnabled = true;
    };

    $scope.displayWikiOutput = function (wiki) {
        $scope.content.wikiText = wiki.wiki.content;
        updateWikiOutput();
    };

    $scope.intervalFunction = function () {
        $timeout(function () {
            updateWikiOutput();
        }, 1000)
    };

    // Kick off the interval
    $scope.intervalFunction();
});

messagingApp2.filter('unsafe', function($sce) {
    return function(val) {
        return $sce.trustAsHtml(val);
    };
});