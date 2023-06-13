# Challenge123

User below queries to create tables in h2 db
Configuration to connect to h2 console - 
Login	
Saved Settings:	
Generic H2 (Server)
Setting Name:	
Generic H2 (Server)
  
Driver Class:	
org.h2.Driver
JDBC URL:	
jdbc:h2:tcp://localhost/~/test
User Name:	
sa
Password:	
   
   
create table User_Info( 
   id INT NOT NULL, 
   username VARCHAR(50) NOT NULL, 
   password VARCHAR(20) NOT NULL 
);

create a USER_INFO_SEQ as well.

CREATE TABLE IMAGE_MAPPING(
    UserId INT NOT NULL,
    ImageId VARCHAR(50) NOT NULL,
    ImageDeleteHash VARCHAR(50) NOT NULL
)

ALTER TABLE IMAGE_MAPPING
    ADD FOREIGN KEY (USERID) 
REFERENCES USER_INFO(ID);


ALTER TABLE IMAGE_MAPPING ADD PRIMARY KEY(ID)

CREATE SEQUENCE IMAGE_MAPPING_SEQ   
AS INT  
START WITH 1  
INCREMENT BY 1;  


ALTER TABLE USER_INFO ADD UNIQUE (USERNAME);

Link to postman collection
https://elements.getpostman.com/redirect?entityId=27918751-e8a8754d-6c1f-45dd-977d-2c71e3d6b6e0&entityType=collection

Also please note that in real world use case proper exception config should be maintained and proper business validations should be added. This is just the basic prototype.
