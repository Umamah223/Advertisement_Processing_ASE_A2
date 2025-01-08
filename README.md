# Advertisement Processing System

## Overview
This project was created for the **Applied Software Engineering** module and implements the use case "Advertisement Processing" for the case study "IT in the Valley". The code provides an overview of different tasks and concepts including class implementation, the Command Pattern, OCL (Object Constraint Language) implementation, and unit testing, all developed in Java using NetBeans IDE.

The primary goal of the system is to simulate the process of managing advertisements submitted by advertisers. 

## Key Components
The entire functionality of the system is contained within a single file, named Advertisement_Processing.java. The code utilizes various Java classes to model the system's functionality, which are explained below:

- **Advertisement**: Represents an advertisement submitted by an advertiser.
- **Advertiser**: A class representing an advertiser who submits advertisements.
- **Employee**: A parent class for all employee-related classes.
- **MarketingDeptEmployee**: A subclass of `Employee`, representing a marketing department employee.
- **Editor**: A subclass of `Employee`, responsible for reviewing, approving, and rejecting advertisements.
- **SystemClass**: A key handler class for processing advertisements, validating them, and updating their status.
- **ProcessingCenter**: Stores approved advertisements.


