app.factory('ChatService', ['$http', function($http) {
	return {
		getToken: function() {
			return $http.get('/rest/chat/login');
		},
		sendMessage: function(data) {
			return $http.post('/rest/chat/sendMessage', data);
		},
		getConectado: function() {
			return $http.get('/rest/chat/conectados');
		}
	};
}]);