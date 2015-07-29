app.controller('ChatController', ['$scope', 'ChatService', function($scope, ChatService) {
	$scope.title = 'Chatting';
	var login = new Token();
	$scope.mensajes = [];
	$scope.status = "Closed";
	$scope.nuevoMensaje = "";
	var channel = undefined;
	var socket = undefined;
	var handler = {
		'onopen' : function() {
			$scope.status = "Opened";
			console.log($scope.status);
		},
		'onmessage' : function(msg) {
			$scope.mensajes.push(msg.data);
			console.log(msg.data);
		},
		'onerror' : function(error) {
			$scope.status = error.data;
		},
		'onclose' : function() {
			$scope.status = "Closed";
		}
	};
	function abrirSocket(token) {
		channel = new goog.appengine.Channel(token);
		socket = channel.open(handler);
	}
	
	$scope.sendMessage = function() {
		var mensaje = new Mensaje(login.token, $scope.nuevoMensaje);
		ChatService.sendMessage(mensaje).then(function(response) {
			console.log("mensaje enviado");
		});
	};
	ChatService.getToken().then(function(response) {
		login.id = response.data.id;
		login.token = response.data.token;
		login.fecha = response.data.fecha;
		abrirSocket(login.token);
	});
	
	$scope.$watch($scope.status);
}]);