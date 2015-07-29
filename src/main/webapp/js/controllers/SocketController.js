app.controller('SocketController', [ '$scope', function($scope) {
	var mensajes = [];
	var status = "Closed";
	var handler = {
		'onopen' : function() {
			status = "opened";
		},
		'onmessage' : function(msg) {
			mensajes.push(msg.data);
		},
		'onerror' : function(error) {
			status = error.data;
		},
		'onclose' : function() {
			status = "closed";
		}
	};
	function abrirSocket(token) {
		var channel = new goog.appengine.Channel(token);
		var socket = channel.open(handler);
	}

} ]);