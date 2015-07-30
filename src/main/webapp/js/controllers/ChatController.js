app.controller('ChatController', ['$scope', '$rootScope', 'ChatService', function($scope, $rootScope, ChatService) {
	$rootScope.loading = true;
	$scope.login = false;
	$scope.urlLogin = "";
	$scope.title = 'Chatting';
	var login = new Token();
	$scope.mensajes = [];
	$scope.conectados = [];
	$scope.status = "Closed";
	$scope.nuevoMensaje = "";
	var channel = undefined;
	var socket = undefined;
	$scope.tokenSeleccionado = "";
	var handler = {
		'onopen' : function() {
			$scope.status = "Opened";
			console.log($scope.status);
		},
		'onmessage' : function(msg) {
			$scope.mensajes.push(JSON.parse(msg.data));
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
		$rootScope.loading = true;
		console.log($scope.tokenSeleccionado);
		var mensaje = new Mensaje($scope.tokenSeleccionado, $scope.nuevoMensaje);
		ChatService.sendMessage(mensaje).then(function(response) {
			console.log("mensaje enviado");
			$rootScope.loading = false;
		}, function(error) {
			$rootScope.loading = false;;
		});
	};
	ChatService.getToken().then(function(response) {
		login.id = response.data.id;
		login.token = response.data.token;
		login.fecha = response.data.fecha;
		abrirSocket(login.token);
		$scope.login = true;
		$rootScope.loading = false;
	}, function(error) {
		$scope.urlLogin = error.data.mensaje;
		$rootScope.loading = false;
		$scope.login = false;
	});
	
	$scope.setOpcion = function(opcion) {
		$scope.tokenSeleccionado = opcion;
	};
	$scope.loading = true;
	ChatService.getConectado().then(function(response) {
		$scope.conectados = response.data;
		$rootScope.loading = false;
	}, function(error) {
		$rootScope.loading = false;
	});
	
}]);