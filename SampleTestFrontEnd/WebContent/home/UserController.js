'use strict';

app.controller('UserController',['$scope','UserService','$location','$rootScope','$cookieStore','$http',
						function($scope, UserService, $location, $rootScope,$cookieStore, $http) {
							console.log("UserController...")
							var self = this;
							this.user = {name : '',pasword :'',userID:'',contact : '',email : '',errorcode:''};
							this.currentUser = {name : '',pasword : '',userID:'',contact : '',email : '',errorcode:''};

						//	self.users=[];
							  $scope.users =[];
							
							
		

							

							self.createUser = function(user) {
								console.log("createUser...")
								UserService.createUser(user).then(
												function(d) {
													alert("Thank you for registration")
													$location.path("/login")
												}
												);
							};

							

							

							

							

							
							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService.authenticate(user).then(

												function(d) {

													self.user = d;
													
													if (self.user.errorcode=="200")
															

													{
														
												console.log("Valid credentials. Navigating to home page")
												
												window.alert('Welcome '
																		+ d.name
																		+ ' ! You are logged in successfully ');
												console.log('Current user : '+ self.user)
												$rootScope.currentUser = self.user;

												$rootScope.islogged= true;
												
												$cookieStore.put('currentUser',self.user);
												$http.defaults.headers.common['Authorization'] = 'Basic '
														+ $rootScope.currentUser;

													$location.path('/home');
													
														
														
													} else { 
														
														window.alert('The Credentials are Wrong ');
															
															
															
															
															$location.path('/login');
															


													}

												}
												);
							}


							// self.fetchAllUsers(); //calling the method

							// better to call fetchAllUsers -> after login ???

							self.login = function() {
								{
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								}
								
							};
							
							self.logout = function() {
								console.log("logout");	
											UserService.logout($rootScope.currentUser.id).then
											(
											function(d){
											self.user=d;			
											$rootScope.currentUser = {};
											$cookieStore.remove('currentUser');
											//$rootScope.isLogged = false;
											
											$location.path('/');
											//$location.reload('/');
											console
											.log('You are logged out successfully in registerController ');
									
									window
											.alert('You are logged out successfully ');
											}
											);
							}

						} ]);
