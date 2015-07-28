app.factory('ChatService', ['$http', function($http) {
	return {
		getToken: function() {
			return $http.get('/rest/chat/login');
		}
		
	};
}]);