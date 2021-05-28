# Getting Started
Spring Boot application with 2 users and roles with two-sided @ManyToMany relationship.

Notes:
- Does not support adding new users. Only users HAMU4 and FRMU can be used. If you need other users, adapt source code.
- Track all changes in H2 database while application is running http://localhost:8080/h2-console
- After restarting application you have to initialize database with http://localhost:8080/setup first
- Does not validate input parameters. Make sure you use expected usernames and rolenames.

Runs with H2 in memory database and supports following calls:
1. http://localhost:8080/setup - Adds 2 Users with Usernames HAMU4 and FRMU1 and 2 roles with rolenames admin and user to database
2. http://localhost:8080/ - Shows available users in database
   After setup response looks like this:
`  [
   {
   "id": 1,
   "username": "HAMU4",
   "createdBy": "setup",
   "roles": []
   },
   {
   "id": 2,
   "username": "FRMU1",
   "createdBy": "setup",
   "roles": []
   }
   ]`
3. http://localhost:8080/addRoleToUser?username=[username]&rolename=[rolename] - Adds given role name to given user name
   After calling http://localhost:8080/addRoleToUser?username=HAMU4&rolename=admin this is what response looks like:
   `[
   {
   "id": 1,
   "username": "HAMU4",
   "createdBy": "addRoleToUser",
   "roles": [
   {
   "id": 1,
   "rolename": "admin",
   "createdBy": "setup"
   }
   ]
   },
   {
   "id": 2,
   "username": "FRMU1",
   "createdBy": "setup",
   "roles": []
   }
   ]`
4. http://localhost:8080/removeRolesFromUser?username=[username] - Removes all roles from user with given username
5. http://localhost:8080/deleteUser?username=[username] - Deletes User with all its roles

