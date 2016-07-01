mainCtrl.$inject = ["$rootScope", "$scope", "$routeParams"];
function mainCtrl($rootScope, $scope, $routeParams) {
    $scope.userName = $routeParams.userName;
    $scope.password = $routeParams.password;
}