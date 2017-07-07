'use strict';
 
app.service('UserService', ['$http', '$q','$rootScope', function($http, $q,$rootScope){
	
	console.log("UserService...")
	
	var BASE_URL='http://localhost:8081/SampleTestRestController'
		
    return {     
             
            createUser: function(user){
            	console.log("calling create user")
                    return $http.post(BASE_URL+'/create', user) //1
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating user');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
           
             
              
            logout: function(id){
            	console.log('Calling logout Method')
                return $http.get(BASE_URL+'/logout/'+id)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while logging out the  user');
                                    return $q.reject(errResponse);
                                }
                        );
        },
        
        
            
            authenticate: function(user){
            	   console.log("Calling the method authenticate with the user :"+user)
          		 
                return $http.post(BASE_URL+'/login',user)
                        .then(
                                function(response){
                                    return response.data;   //user json object
                                }, 
                               null
                        );
        }
         
    };
 
}]);