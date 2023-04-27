DROP DATABASE IF EXISTS sports_zone;
CREATE DATABASE IF NOT EXISTS sports_zone;
USE sports_zone;

CREATE TABLE Employee(
                         empId VARCHAR(10),
                         empName VARCHAR(30) NOT NULL ,
                         address TEXT NOT NULL ,
                         dob DATE NOT NULL ,
                         contactNo VARCHAR(15) UNIQUE NOT NULL ,
                         salary DECIMAL(8,2) NOT NULL ,
                         email VARCHAR(50) ,
                         nic VARCHAR(15) UNIQUE NOT NULL ,
                         jobTitle VARCHAR(15) NOT NULL ,
                         CONSTRAINT PRIMARY KEY (empId)
);

INSERT INTO Employee VALUES ('E-001', 'isuru', 'galle', '2021-01-20', '0717236574', 20000, 'isuru@gmail.com', '200134567567', 'Admin');

CREATE TABLE User(
                     userName VARCHAR(20),
                     empId    VARCHAR(10) UNIQUE NOT NULL,
                     password VARCHAR(100)       NOT NULL,
                     email    VARCHAR(50) UNIQUE NOT NULL,
                     jobTitle VARCHAR(10)        NOT NULL,
                     CONSTRAINT PRIMARY KEY (userName),
                     CONSTRAINT FOREIGN KEY (empId) REFERENCES Employee (empId)
                         ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Customer(
                         custId VARCHAR(10),
                         custName VARCHAR(30) NOT NULL ,
                         contactNo VARCHAR(15) NOT NULL ,
                         address TEXT NOT NULL ,
                         email VARCHAR(50) ,
                         CONSTRAINT PRIMARY KEY (custId)
);

/*remove foriegn key*/

CREATE TABLE Repair(
                       repairId VARCHAR(10),
                       custId VARCHAR(10) ,
                       repairItem VARCHAR(30) NOT NULL ,
                       date DATE NOT NULL ,
                       price DECIMAL(7,2) NOT NULL ,
                       CONSTRAINT PRIMARY KEY (repairId),
                       CONSTRAINT FOREIGN KEY (custId) REFERENCES Customer(custId)
                           ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Orders(
                       orderId VARCHAR(10),
                       custId VARCHAR(10),
                       payment DECIMAL(8,2) NOT NULL ,
                       time TIME NOT NULL ,
                       date DATE NOT NULL ,
                       deliveryStatus VARCHAR(10) DEFAULT 'Pending' ,
                       CONSTRAINT PRIMARY KEY (orderId),
                       CONSTRAINT FOREIGN KEY (custId) REFERENCES Customer(custId)
                           ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Item(
                     itemCode VARCHAR(10),
                     itemName VARCHAR(30) NOT NULL ,
                     category VARCHAR(30),
                     brand VARCHAR(30) NOT NULL ,
                     unitPrice DECIMAL(7,2) NOT NULL ,
                     qtyOnHand INT NOT NULL ,
                     CONSTRAINT PRIMARY KEY (itemCode)
);


CREATE TABLE OrderDetail(
                            orderId VARCHAR(10),
                            itemCode VARCHAR(10),
                            qty INT NOT NULL ,
                            CONSTRAINT PRIMARY KEY (orderId,itemCode),
                            CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orders(orderId)
                                ON UPDATE CASCADE ON DELETE CASCADE,
                            CONSTRAINT FOREIGN KEY (itemCode) REFERENCES item(itemCode)
                                ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Supplier(
                         supId VARCHAR(10),
                         supName VARCHAR(30) NOT NULL ,
                         address TEXT ,
                         email VARCHAR(50) UNIQUE NOT NULL ,
                         contactNo VARCHAR(15) UNIQUE NOT NULL ,
                         CONSTRAINT PRIMARY KEY (supId)
);


CREATE TABLE SupplyLoadDetail(
                                 loadId VARCHAR(10),
                                 itemCode VARCHAR(10),
                                 supId VARCHAR(10),
                                 date DATE NOT NULL ,
                                 price DECIMAL(9,2) NOT NULL ,
                                 qty INT NOT NULL ,
                                 CONSTRAINT PRIMARY KEY (loadId,itemCode,supId),
                                 CONSTRAINT FOREIGN KEY (itemCode) REFERENCES Item(itemCode)
                                     ON UPDATE CASCADE ON DELETE CASCADE,
                                 CONSTRAINT FOREIGN KEY (supId) REFERENCES Supplier(supId)
                                     ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Vehicle(
                        vehiId VARCHAR(10),
                        vehiNo VARCHAR(10) UNIQUE NOT NULL ,
                        vehiType VARCHAR(30) NOT NULL ,
                        CONSTRAINT PRIMARY KEY (vehiId)
);


CREATE TABLE Delivery(
                         deliveryId VARCHAR(10),
                         empId VARCHAR(10) ,
                         orderId VARCHAR(10) UNIQUE NOT NULL ,
                         vehiId VARCHAR(10) ,
                         location TEXT,
                         deliveryDate VARCHAR(20) DEFAULT 'pending',
                         dueDate VARCHAR(20) DEFAULT 'not given',
                         deliveryStatus VARCHAR(10) DEFAULT 'pending',
                         CONSTRAINT PRIMARY KEY (deliveryId),
                         CONSTRAINT FOREIGN KEY (empId) REFERENCES Employee(empId)
                             ON UPDATE CASCADE ON DELETE CASCADE ,
                         CONSTRAINT FOREIGN KEY (orderId) REFERENCES Orders(orderId)
                             ON UPDATE CASCADE ON DELETE CASCADE ,
                         CONSTRAINT FOREIGN KEY (vehiId) REFERENCES Vehicle(vehiId)
                             ON UPDATE CASCADE ON DELETE CASCADE

);
