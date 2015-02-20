angular.module('hello')
.controller('UsersController', function($http) {
        var vm = this;
        vm.users = [];
        vm.user = null;
        vm.userRoles = null;

        vm.editUser = editUser;
        vm.saveUser = saveUser;
        vm.hasRole = hasRole;
        vm.defineRole = defineRole;

        init();

        function init() {
            loadUsers();
        }

        function loadUsers() {
            $http
                .get('/rest/users')
                .success(function(data) {
                    vm.users = data
                })
                .error(function(error) {
                    console.log('Error : get users',error);
                });
        }

        function editUser(login) {
            $http
                .get('/rest/users/'+login)
                .success(function(data) {
                    vm.user = data;
                    vm.userRoles = {
                        'ADMIN': false,
                        'USER': false
                    };
                    for(var i=0; i<vm.user.roles.length; i++) {
                        var role = vm.user.roles[i];
                        vm.userRoles[role] = true;
                    }
                })
                .error(function(error) {
                    console.log('Error : get user',login,error);
                });
        }

        function saveUser(user, userRoles) {
            user.roles = [];
            for(var role in userRoles) {
                if(userRoles[role] === true) {
                    user.roles.push(role);
                }
            }

            $http
                .put('/rest/users/'+user.login, user)
                .success(function(data) {
                    console.log('Save ok');
                })
                .error(function(error) {
                    console.log('Error : save user',error);
                });
        }

        function indexRole(roles, role) {
            for(var i=0; i<roles.length; i++) {
                if(role === roles[i]) {
                    return i;
                }
            }
            return -1;
        }

        function hasRole(roles, role) {
            return indexRole(roles, role) != -1;
        }

        function defineRole(roles, role) {
            var index = indexRole(roles, role);
            if (index === -1) {
                roles.push(role);
            } else {
                roles.splice(index, 1);
            }
        }

});