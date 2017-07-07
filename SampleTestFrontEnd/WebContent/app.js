
var app = angular.module('myApp', [ 'ngRoute','ngCookies']);
app.config(function($routeProvider) {
	$routeProvider

	.when('/', {
		templateUrl : 'home/homepage.html'
		
	})
	
	.when('/login',{
		templateUrl : 'home/login.html',
			controller : 'UserController'
		})
		
		.when('/register',{
		templateUrl : 'home/register.html',
			controller : 'UserController'
		})



	.otherwise({
		redirectTo : '/'
	});
})
