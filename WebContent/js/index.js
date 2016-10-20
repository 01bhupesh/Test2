
/*$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});*/

var app = angular.module('myApp', []);
		app.controller('formCtrl', function($scope, $http ,$location) {
			$scope.student ={
					firstName : "",
					lastName : ""
			}
			$http({
				method : "POST",
				url : "ServletController"
			}).then(function(response) {//Success Method
				$scope.user = response.data;
			}, function(response) {
				// optional
				// failed
			});
			$scope.receiveData =function() {
				$http({
					method : "POST",
					url : "ServletController"
				}).then(function(response) {//Success Method
					$scope.user = response.data;
				}, function(response) {
					// optional
					// failed
				});
			}
			$scope.sendData =function() {	
				console.log("Send data called=============>>>");
				$http({
					url : 'ServletControllerSendData',
					method : "POST",
					data : $scope.student
				}).then(function(response) {
					$scope.student ={
							firstName : "",
							lastName : ""
					}
					obj=response.data;
					if(obj!=null){
						if(obj.responseMsg.OK=='OK')					
							$scope.user.push(obj.responseResult.result.data);
						else
							alert(obj.responseMsg.OK);
					}
				}, function(response) { 
					// optional
					// failed
				});
			}
			$scope.setSelected =function(student) {
				$scope.selectedStudent=student;
			}
			
			$scope.deleteData = function() {				
				$http({
					url : 'ServletControllerDeleteData',
					method : "POST",
					data : $scope.selectedStudent
				}).then(function(response) {
					$scope.student ={
							firstName : "",
							lastName : ""
					}
					obj=response.data;
					if(obj.responseMsg.OK=='OK'){
						
						var index = -1;		
						var comArr = eval( $scope.user );
						for( var i = 0; i < comArr.length; i++ ) {
							if( comArr[i].firstName === $scope.selectedStudent.firstName) {
								index = i;
								break;
							}
						}
						
						if( index === -1 ) {
							alert( "Something gone wrong" );
						}
						else
							alert( "Row Deleted..." );
						$scope.user.splice( index, 1 );
					}
					else
						alert(obj.responseMsg.OK);
					//$scope.user = response.data;
				}, function(response) { // optional
					// failed
				});
			}
			$scope.edit = function() {	
				$scope.student =$scope.selectedStudent
				$http({					
					url : "http://localhost:8080/WebAngulerJs/main.htm",
					method : "POST",
					data : $scope.selectedStudent
				}).then(function(response) {
					
					
				}, function(response) { 
					// optional					
				});
			}
			
			$scope.uploadFile = function(){
				 
	               var file = $scope.myFile;	                   
	               var uploadUrl = 'fileUpload';
	              // fileUpload.uploadFileToUrl(file, uploadUrl);
	               console.log("the file is  000000000000000000000 uploadFileToUrl");
	               var fd = new FormData();
	               fd.append('file', file);
	               console.log("helooooooooooooooooooooo");
	               $http.post(uploadUrl, fd, {
	                  transformRequest: angular.identity,
	                  headers: {'Content-Type': 'multipart/form-data'}
	               })	            
	               .success(function(){
	            	   console.log("kkkkkkkkkkkkkk");
	               })
	            
	               .error(function(){
	            	   console.log("hhhhhhhhhhhhhhhhhhhh");
	               });
	            };
			
			
		});
		
		
		
		app.directive('fileModel', ['$parse', function ($parse) {
	            return {
	            	
	               restrict: 'A',
	               link: function(scope, element, attrs) {
	                  var model = $parse(attrs.fileModel);
	                  var modelSetter = model.assign;
	                  
	                  element.bind('change', function(){
	                     scope.$apply(function(){
	                    	 console.log("11111111111the file is   uploadFileToUrl");
	                        modelSetter(scope, element[0].files[0]);
	                     });
	                  });
	               }
	            };
	         }]);
	      
		/*app.service('fileUpload', ['$http', function ($http) {
	            this.uploadFileToUrl = function(file, uploadUrl){	            	
	               var fd = new FormData();
	               fd.append('file', file);
	            
	               $http.post(uploadUrl, fd, {
	                  transformRequest: angular.identity,
	                  headers: {'Content-Type': undefined}
	               })
	            
	               .success(function(){
	               })
	            
	               .error(function(){
	               });
	            }
	         }]);
	      */
		
		
		