Project Name: restshopping
Author: Puneet Kaur Sethi
Date: 11/21/2017

###################
Project Description
###################
The application restshopping is a rest webservice developed using spring boot. This appplication provides a webserivce to the user to place an order for a product. stores the order information in the in-memory H2 DB. The application does the following:
1) It checks the availability of the product in the order against the inventory. This is done by comparing the product quantity in the order with the product quantity retrieved from the PRODUCT table in the database.
2) If the product is available in the inventory, the order is processed further by validating the credit card information that the user provides in the order.
3) The credit card number length and number format is verified in the code. The card authentication is done using a Payment Gateway interface. For simplicity, the code has an Interface called PaymentGateway.java in the package com.psethi.api.restshopping.thirdparty to perform this validation.
4) After the card information is validated, the order is processed and a new order entry is made in the ORDER_DETAIL table in the database.
5) The quantity of the product is then updated in the PRODUCT table in the database.
6) There is an email sent to the shipping company using SMTP java mail.
7) The processed order details are then sent to the user as a response.
8) It is assumed that the user trying to place the order is already present in the database and the inventory for product is already existing. This is ensured by initializing the in-memory H2 data base with insert script when the application loads.

There are 4 webservies present in the application:
1) To view the User records
   Name: getUserRecords
   Controller Name: ShoppingController
   URL for testing on Postman: /users
   Service Type: GET 
   Input:None
   Response: List of User Objects  
   Behavior: To fetch all user records from USER table.   
   			 
2) To view the product records
   Name: getProductRecords
   Controller Name: ShoppingController
   URL for testing on Postman: /products
   Service Type: GET 
   Input:None
   Response: List of Product Objects 
   Behavior: To fetch all product records from PRODUCT table.
   
3) To view the order records
   Name: getOrderRecords
   Controller Name: ShoppingController
   URL for testing on Postman: /orders
   Service Type: GET 
   Input:None
   Response: List of OrderDetail Objects 
   Behavior: To fetch all orderDetail records from ORDER_DETAIL table.
   
4) To place an order for a particular product
   Name: placeOrder
   Controller Name: ShoppingController
   URL for testing on Postman: /orders
   Service Type: POST 
   Input: OrderDto Object
   Response: OrderDetail Object
   Behavior: To place an order for a product. The credit card information is provided along with the order. The order is processed and a new record is inserted into the ORDER_DETAIL table. An update is made in the quantity in PRODUCT table.
  
###############
Database Table 
###############
This application stores data in the in-memory H2 database. There are 3 entities created:
1) PRODUCT
2) USER
3) ORDER_DETAILS

ORDER_DETAILS has ManyToOne relation with USER and OneToMany with the PRODUCT table.

data.sql script runs and inserts data into the PRODUCT and USER tables when the application loads.

#######################
Maven Build Instruction
#######################
1. In the command prompt go to parent project \restshopping
2. Execute--> "mvn clean install" command
3. The restshopping-0.0.1-SNAPSHOT.war will be generated in the path \restshopping\target 

###################
Application Testing
###################
Application can be tested in 2 ways:
1) By running Junit methods in the code.
2) Running the main method in the file "RestshoppingApplication.java" in the package "com.psethi.api.restshopping". This will inturn start the inbuild tomcat server and deploy the war file. Tools like postman can be used to test the webservices.

###################
Testing using Junit
###################

1. Application can be tested using Junit test cases defined.
2. Junit method "placeOrderTest" present in the ShoppingControllerTest.java file can be run to test the complete flow of the application. This Junit method calls the post method in the rest controller.
3. After the Junit is run the application should return a JSON OrderDetail object containg the details of the processed order and the order number.
   For e.g- {
				"id": 1,
				"user": {
					"id": 1,
					"name": "Puneet Sethi",
					"address": "9509 Key W Ave, Rockville, MD 20850"
				},
				"quantity": 5,
				"product": {
					"id": "1",
					"name": "Samsung Monitor",
					"totalProductQuantity": 45,
					"productUnitAmount": 100.99
				}
			}

		
######################
Testing Using Postman
######################
1. After the main method is run in the file "RestshoppingApplication.java", the appliation can be accessed using the followig URL:      http://localhost:8080/restshopping/
2. Tools like PostMan can be used to test the webservice.
3. The following are the URL for the GET and POST webservices:
	a) GET Method to retrieve all users: http://localhost:8080/restshopping/users
	b) GET Method to retrieve all products: http://localhost:8080/restshopping/products
	c) GET Method to retrieve all orders: http://localhost:8080/restshopping/orders
	d) POST Method to place an order: http://localhost:8080/restshopping/order . For this method, the following JSON input can be provided:
	{
        "productId": "1",
        "userId": "1",
        "quantity": "5",
        "creditCardNumber": "1234567812345678"
    }
	
#######
Logging
#######
1. Loggers are implemented in the code using log4j.
2. Loggers can be viewed on the console.		

############
Assumptions
############
The requirement indicates that a detailed application is not required. The following are the assumptions made while creating the application.
1) The user ordering the product already has an acount in the system. His/Her information is already present in the USER table.
2) The inventory information is already present in the database in the PRODUCT table.
3) The user is placing the order only for a single product.
4) The credit card information is provided along with the order in the webservice request.
5) The application uses the SMTP java mail to send emails. In the Junit, the application.properties file in the test resources contains dummy email and password to avoid sending actual emails.
 




