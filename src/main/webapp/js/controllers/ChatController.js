app.controller('ChatController', ['$scope', 'ChatService', function($scope, ChatService) {
	$scope.title = 'Chatting';
	var token = undefined;
	ChatService.getToken().then(function(response) {
		token = response.data.token;
		console.log(token);
	});
}]);