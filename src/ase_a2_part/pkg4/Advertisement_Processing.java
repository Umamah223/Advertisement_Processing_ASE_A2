/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ase_a2_part.pkg4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Advertisement Class
 * Represents an Advertisement submitted by an Advertiser
 * Contains details such as advertisement ID, size, placement preferences, payment terms, status & advertiser information.
 */

class Advertisement {
    private int advertID;
    private int size;
    private String placementPreferences;
    private String paymentTerms;
    private String status;
    private String advertiserInfo;

    private static final Set<Integer> advertIDs = new HashSet<>();  // OCL : Set to store unique advertisement IDs
    private static final Set<String> validStatuses = Set.of("Accepted", "Rejected", "Submitted", "Validated"); // OCL: Valid status values
    
    public static void resetAdvertIDs() {
    advertIDs.clear();
}
    
    public Advertisement(int advertID, int size, String placementPreferences, 
            String paymentTerms, String status, String advertiserInfo) {
        
        if (advertIDs.contains(advertID)) {     // OCL Rule: Each Advertisement should have a unique id
            throw new IllegalArgumentException( "Advertisement creation failed: Provided Advertisement ID (" + advertID + 
        ") is already in use. Each Advertisement ID must be unique");
        }
        
         if (status == null || !validStatuses.contains(status)) {
        throw new IllegalArgumentException("Invalid status. Status must be one of " + validStatuses);
    }
         
        this.advertID = advertID;
        this.size = size;
        this.placementPreferences = placementPreferences;
        this.paymentTerms = paymentTerms;
        this.status = status;
        this.advertiserInfo = advertiserInfo;

        advertIDs.add(advertID);  // Add new ID to the set
    }

    //Getter Methods for accessing advertisement details and to provide read-only access to attributes 
    public int getAdvertID() {
        return advertID;
    }

    public int getSize() {
        return size;
    }

    public String getPlacementPreferences() {
        return placementPreferences;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public String getStatus() {
        return status;
    }

    public String getAdvertiserInfo() {
        return advertiserInfo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

/**
     * Declaring Advertiser class who submits Advertisements to the Marketing Dept
     * This class contains the Advertiser details and allows the submission of multiple Advertisements.
     */
    
class Advertiser {
    private int advertiserID;
    private String advertiserName;
    private String contactInfo;
    
     //Aggregation between Advertiser and Advertisement Class
     //An Advertiser can have many Advertisements (1 to 1..* multiplicity)
    private List<Advertisement> advertisements = new ArrayList<>();  

    // Constructor
    public Advertiser(int advertiserID, String advertiserName, String contactInfo) {
        this.advertiserID = advertiserID;
        this.advertiserName = advertiserName;
        this.contactInfo = contactInfo;
        this.advertisements = new ArrayList<>();
    }
   
    public int getAdvertiserID() {
        return advertiserID;
    }

    public String getAdvertiserName() {
        return advertiserName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // OCL: Advertiser must have at least one advertisement
    public boolean hasAdvertisements() {
        return advertisements.size() >= 1;
    }

  // Submit Advert Method
   public void submitAdvert(Advertisement advert, MarketingDeptEmployee marketingEmployee) {
    if (advert == null || advert.getPlacementPreferences() == null || advert.getPlacementPreferences().trim().isEmpty()
            || advert.getPaymentTerms() == null || advert.getPaymentTerms().trim().isEmpty()  || advert.getAdvertiserInfo() == null ) {
        
        throw new IllegalArgumentException("Error: Advertisement " + advert.getAdvertID() + " details are incomplete.");
    }

    // Enforce the OCL constraint during submission
    if (!hasAdvertisements()) {
        System.out.println("First advertisement submitted by advertiser:  " + advertiserName +" (ID: " + advertiserID + ")");
    }

    // Add the advertisement to the list
    advert.setStatus("Submitted");        
    advertisements.add(advert);

    System.out.println("Advertisement " + advert.getAdvertID() + " has been submitted by Advertiser : " + advertiserName + " (ID:" + advertiserID + " )"); 
}
}


/**
 * Employee Class
 * This is a parent class that represents a generic employee in an organization
 * It serves as a Parent class for other specific employee types (e.g., MarketingDeptEmployee, Editor)
 * The MarketingDept Employee and Editor classes inherit from this class.
 */

class Employee {
     // private methods demonstrating OOP Principle of Encapsulation
    private int employeeID;
    private String name;
    private int deptID;
    
    // Parameterized constructor for initializing the employee fields
    public Employee(int employeeID, String name, int deptID) {
        
        this.employeeID = employeeID;
        this.name = name;
        this.deptID = deptID;
    }
    
    //Getter Methods for accessing employee details and to provide read-only access to attributes
    public int getEmployeeID(){
       return employeeID;
    }
    
    public String getName(){
        return name;
    }
    
    public int getDeptID(){
        return deptID;
    }
}

/**
 * Represents a Marketing Department Employee, which inherits from the Employee class
 * Demonstrates the OOP principle of Inheritance
 * This class has additional functionality to enter Advertisement details into the System.
 */

class MarketingDeptEmployee extends Employee{
      private String contactInfo;     //Additional attribute specific to the MarketingDeptEmployee
 
    // Constructor to initialize the MarketingDeptEmployee
    public MarketingDeptEmployee(int employeeID, String name, int deptID, String contactInfo) {
        
        super(employeeID, name, deptID);   // Initialize inherited attributes
        this.contactInfo = contactInfo;    // Initialize specific attribute
    }
    
    //Getter Method for the contactInfo attribute
    public String getContactInfo(){
        
       return contactInfo;
    }
    
    //Method showing the Marketing Dept Employee entering Advertisement Details into the System
    public void enterAdvertisementDetails(Advertisement advert){
        System.out.println("Advertisement entered into the System: "+advert.getAdvertID());
    }
}

/**
 * Editor Class inherits from the Employee class 
 * Demonstrates the OOP principle of Inheritance
 * It has additional functionalities such as reviewing, approving and rejecting adverts.
 */

//Represents as a receiver for the Command Pattern

class Editor extends Employee{
    private String contactInfo;  // Contact information of the editor
     
    //Constructor to initialize the Editor
    public Editor(int employeeId, String name, int deptID, String contactInfo){
           
           super(employeeId, name, deptID);    // Initialize inherited attributes from the Employee class
           this.contactInfo = contactInfo;  
     }
     
    //Getter for the contactInfo attribute
     public String getContactInfo(){
         return contactInfo;
     }
    
    // Method to review Advertisement
     public void reviewAdvertisement(Advertisement advert, SystemClass system){
         
         if (!advert.getStatus().equals("Validated")) {   //OCL : Advertisement must be validated by the System before forwarded to Editor
            throw new IllegalStateException("Advertisement (ID:" + advert.getAdvertID()  + ") must be validated before review.");
        }
   
         System.out.println("Advertisement ID: " +advert.getAdvertID()+ " is under review by Editor: " + super.getName() + "(Dept: " + super.getDeptID() + ", ID: " + super.getEmployeeID() +")");
        
         if (advert.getStatus().equals("Accepted")) {   // OCL Post condition: Store advertisement in the Processing Center if accepted
    
            ProcessingCenter processingCenter = new ProcessingCenter();
            processingCenter.storeAdvertisement(advert);
            System.out.println("Advertisement (ID: " + advert.getAdvertID() + " ) has been stored in the Processing Center.");
            
         } 
         
         else if (advert.getStatus().equals("Rejected")) {
            // Call the system's rejection notification method when the advertisement is rejected
            system.generateAdRejectionNotification(advert, this); 
             
         } 
     }
     
     // Method to approve Advertisement
     public void approveAdvertisement(Advertisement advert, SystemClass system){
         
         System.out.println("Advertisement ID: " +advert.getAdvertID()+ " has been approved by the Editor");
         system.updateAdStatus(advert,"Accepted");     // The advertisement status is updated to "Accepted"
     }
     
     // Method to reject Advertisement
     public void rejectAdvertisement(Advertisement advert, SystemClass system){
         
         System.out.println("Advertisement "+advert.getAdvertID()+" has been rejected by the Editor");
         system.updateAdStatus(advert,"Rejected"); // The advertisement status is updated to "Rejected"
         
     }
}

/**
 * Represents the System which is a key handler class for processing advertisements
 * Contains several functions such as validating advert details, displaying error messages etc.
 */

class SystemClass {
    //Composition: between the System and the Processing Center class
    private final ProcessingCenter processingCenter;
    
    public SystemClass(){
        this.processingCenter = new ProcessingCenter();  // Instantiation within the System class
    }
    
    //Method to validate the Advertisement Details
   public boolean validateAdvertDetails(Advertisement advert){
       
       if(advert == null){
           
           displayErrorMessage("The Advertisement is empty. Please fill it with details");
            //return false;
           throw new IllegalArgumentException("The Advertisement is empty. Please fill it with details");
       }
       
       if (String.valueOf(advert.getAdvertID()).isEmpty()){
           
           displayErrorMessage("Invalid Advertisement: The Advertisement ID is missing. Please provide a valid ID to proceed.");
           //return false;
           throw new IllegalArgumentException("Invalid Advertisement: The Advertisement ID is missing. Please provide a valid ID to proceed.");
       }
       
       if (advert.getSize() <= 0 ){
             displayErrorMessage("Invalid Advertisement: The Advertisement Size is incorrect. Please provide a valid size to proceed.");
        throw new IllegalArgumentException("Error: Advertisement ID " + advert.getAdvertID() + " has an invalid size. Please provide a valid size to proceed.");
       }
       
      
        if (advert.getPlacementPreferences() == null || advert.getPlacementPreferences().isEmpty()) {
            displayErrorMessage("Missing Placement Preferences.Please Check Again");
            //return false;
            throw new IllegalArgumentException("Missing Placement Preferences.Please Check Again");
        }
        
        if (advert.getPaymentTerms().isEmpty()){
            displayErrorMessage("Invalid Advertisement: The Payment terms are missing. Please provide Payment Terms to proceed.");
            //return true;
            throw new IllegalArgumentException("Invalid Advertisement: The Payment terms are missing. Please provide Payment Terms to proceed.");
        }
       
        if (advert.getAdvertiserInfo() == null || advert.getAdvertiserInfo().isEmpty()) {
            displayErrorMessage("Missing Advertiser Information.Please Check Again");
            //return false;
            throw new IllegalArgumentException("Missing Advertiser Information.Please Check Again");
        }

        // If all validations pass
        System.out.println("Advertisement ID:" + advert.getAdvertID() + " is validated and is ready for review.");
        advert.setStatus("Validated");  // Update status to 'Validated'
        return true;
   }
   
    //Method to display error messages for invalid adverts to the MarketingDept Employee
    public void displayErrorMessage(String message){
       
       System.out.println("Error :" +message);
    }
     
   //Method to forward the validated Advertisement to the Editor
    public void forwardValidatedData(Advertisement advert, Editor editor){
       
       System.out.println("Forwarding advertisement " + advert.getAdvertID() + " to the Editor.");
       editor.reviewAdvertisement(advert,this);    
   }
   
    //Method to update the Advertisement status when the advertisement is rejected or approved
    public void updateAdStatus(Advertisement advert, String status){
       advert.setStatus(status);
       System.out.println("Advertisement ID: "+advert.getAdvertID()+ " status is updated to " +status);   
    }
   
   //Method to generate Advertisement Rejection Notification if the advert is rejected by the Editor
    public void generateAdRejectionNotification(Advertisement advert, Editor editor){
       
       //Checking if the advertisement status is rejected
       if (advert.getStatus().equals("Rejected")){
           
           //Generate the Rejection Notification
           System.out.println("The Advertisement with ID:" +advert.getAdvertID() +" has been rejected by the Editor." +
                " Please review the Advertisement details and resubmit after making the necessary changes.");   
       }
    }
   
   //Method to record the Advertisement details into the Processing Center
    public void recordAdvertDetails(Advertisement advert, ProcessingCenter processingCenter){
       
        // Store the advertisement in the Processing Center
        processingCenter.storeAdvertisement(advert);
        System.out.println("Advertisement " + advert.getAdvertID() + " has been recorded.");
    }
}

/**
 * Represents a Processing Center for storing advertisements
 * Acts as a database in the system.
 */ 

class ProcessingCenter {
     // Attribute to store the approved advertisements
    private List<Advertisement> storeAdvertisement = new ArrayList<>();

    //Method to store Advertisement for further publication
    public void storeAdvertisement(Advertisement advert) {
        storeAdvertisement.add(advert);
        System.out.println("Advertisement(ID:"+ advert.getAdvertID() + ")has been successfully stored for publication.");
    }
    
   //Getter method for the list of stored advertisements
    public List<Advertisement> getStoredAdvertisements() {
        return storeAdvertisement;
    }
}


//--Task 2: Command Pattern Implementation--

// Command Interface
interface Command {
    void execute();
}

// Concrete Command: Review Advertisement
class ReviewAdvertisementCommand implements Command {
    private final Editor editor;    // Receiver
    private final Advertisement advert;  //Argument passed to review the adverts
    private final SystemClass system;   

    public ReviewAdvertisementCommand(Advertisement advert,Editor editor,SystemClass system ) {
        this.advert = advert;
        this.editor = editor;
        this.system = system;
    }
 
    @Override
    public void execute() {
        editor.reviewAdvertisement(advert, system);
    }
}
 
// Concrete Command: Approve Advertisement
class ApproveAdvertisementCommand implements Command {
    private final Editor editor;   //Reciever
    private final Advertisement advert;  //Dependency or argument
    private final SystemClass system;   //Argument passed to change the status

    public ApproveAdvertisementCommand(Advertisement advert, Editor editor,SystemClass system) {
        this.advert = advert;
        this.editor = editor;
        this.system = system;
    }

    @Override
    public void execute() {
        editor.approveAdvertisement(advert,system);
    }
}

// Concrete Command: Reject Advertisement
class RejectAdvertisementCommand implements Command {
    private final Editor editor;   //Receiver
    private final Advertisement advert;
    private final SystemClass system;   //Argument passed to change the status

    
    public RejectAdvertisementCommand(Advertisement advert, Editor editor, SystemClass system) {
        this.advert = advert;
        this.editor = editor;
        this.system = system;
    }

    @Override
    public void execute() {
        editor.rejectAdvertisement(advert,system);
    }
}

// Invoker: CommandInvoker
class CommandInvoker {
    private final List<Command> commandQueue = new ArrayList<>();

    public void addCommand(Command command) {
        commandQueue.add(command);
    }

    public void executeCommands() {
        for (Command command : commandQueue) {
            command.execute();
        }
        commandQueue.clear();
    }
}


public class Advertisement_Processing {
     public static void main(String[] args) {
          
        // Creating sample advertisements with details such as ID, pricing,etc.and some with invalid details to show OCL
        Advertisement advert1 = new Advertisement(101, 20,"Center", "30$ Installment per month","Accepted","techcorp@gmail.com");
        Advertisement advert2= new Advertisement(102, 40,"Center", "141$ Installments in 3 months","Validated","Justin@innovtech.com");  
        Advertisement advert3 = new Advertisement(103, 40,"Center", "Full Upfront","Submitted","Justin@innovtech.com");
        Advertisement advert4 = new Advertisement(104, 0, "Top Right", "  ", "Submitted", "thames@example.com");
      
        //Initializing instances for Advertiser, MarketingEmployee, Editor, ProcessingCenter, System
        Advertiser advertiser = new Advertiser(1, "Justin", "Justin@innovatech.com");
        MarketingDeptEmployee marketingEmployee = new MarketingDeptEmployee(201, "Ben Barnes", 1, "ben@techcorp.com");
        SystemClass system = new SystemClass();  
        Editor editor = new Editor(2, "Thomas Shelby", 2, "thomas@byTech.com");   
        ProcessingCenter processingCenter = new ProcessingCenter(); 
        
       //--Task 1: Class Implementation and Overall Workflow--
       
        System.out.println("Class Implementation and Workflow");
        System.out.println("----Advertisement Processing---");
        System.out.println("");
        
        //Step 1: Advertiser submits advertisement to the Marketing Dept Employee
        advertiser.submitAdvert(advert1, marketingEmployee);
      
        //Step 2: Marketing Dept Employee enters Advertisement Details into the System
        marketingEmployee.enterAdvertisementDetails(advert1);
       
         // Step 3: Validate the Advertisement
        system.validateAdvertDetails(advert1);
        
         // Step 4: Forward the valid advertisement to the Editor 
         system.forwardValidatedData(advert1,editor);
         
         //Step 5: Editor reviews the advertisement
         editor.reviewAdvertisement(advert1, system);
       
         // Step 6: Editor approves or rejects the advert
        editor.approveAdvertisement(advert1, system);
        
        // The Advert status is updated in the system 
        system.updateAdStatus(advert1, "Approved");

        // Step 7: Store the approved advertisement in the Processing Center
        processingCenter.storeAdvertisement(advert1); 
        
        System.out.println("\n---Advertisement Processing Completed---");
     
        
        //--Task 2: Command Pattern Implementation--
  
        System.out.println("\n--Command Pattern Implementation--");
        
        // Creating the Command Invoker
        CommandInvoker commandInvoker = new CommandInvoker();
        
        // Adding commands to the invoker for Advertisement 2
        ReviewAdvertisementCommand reviewCommand = new ReviewAdvertisementCommand(advert2, editor, system);
        ApproveAdvertisementCommand approveCommand = new ApproveAdvertisementCommand(advert2, editor,system);
        
        //Adding and executing commands
        commandInvoker.addCommand(reviewCommand);
        commandInvoker.addCommand(approveCommand);
        System.out.println("Executing Commands:");
        commandInvoker.executeCommands();
        
        
       //--Task 3: OCL Implementation--
       System.out.println(" ");
       System.out.println("----OCL Implementation---");
       
       
       //1st OCL Implementation : Advertisement IDs must be unique
        System.out.println(" ");
        System.out.println("1st OCL Implementation: Advertisement IDs must be unique");
        
        try {
            // Create first advertisement with ID 101
            System.out.println("Advertisement 1 created successfully with ID: " + advert1.getAdvertID());

            // Attempting to create an advertisement with a duplicate ID (101)
            Advertisement duplicateID = new Advertisement(101, 42, "Top Right", "170$ Installments every 4 months", "Accepted", "alina123@gmail.com");
            System.out.println("Advertisement 2 created successfully with ID: " + duplicateID.getAdvertID());
        } 
          catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());    //Catching error if a duplicate ID is attempted
        }

        // Creatinng a valid third advertisement with a unique ID
        try {
            System.out.println("Advertisement 3 created successfully with ID: " + advert3.getAdvertID());   
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
       
           
       
       //2nd OCL Implementation :Advertiser must have at least one advertisement
        System.out.println(" ");
        System.out.println("2nd OCL Implementation: Advertiser must have at least one advertisement");
        
            //An Advertiser submiting their first advertisement
             advertiser.submitAdvert(advert3, marketingEmployee);
            
            //Same Advertiser submitting another advertisement
            advertiser.submitAdvert(advert2, marketingEmployee);
            
            //Checking if an advertiser has many advertisements
            System.out.println("Advertiser has advertisements: " + advertiser.hasAdvertisements());
            
       
       //3rd OCL Implementation : Advertisement should have valid status values
       System.out.println(" ");
       System.out.println("3rd OCL Implementation: Advertisement should have valid status values");
       
       try {
              // Attempting to create an advertisement with an invalid status ("InvalidStatus")
             Advertisement advert5 = new Advertisement(105, 50, "Top Right", "100$ per month", "InvalidStatus", "sirius@gmail.com");
            System.out.println("Advertisement created successfully with ID: " + advert5.getAdvertID());
        } 
           catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }

        //Creating an Advertisement with valid status
        Advertisement advert6= new Advertisement(107, 60, "Top Left", "200$ upfront", "Submitted", "hermione@gmail.com");
        System.out.println("Advertisement 7(ID: " + advert6.getAdvertID()+ ") has status:" + advert6.getStatus());
        
       
       //4th OCL Implementation: Advertiser must provide complete advertisement details
       System.out.println(" ");
       System.out.println("4th OCL Implementation: Advertiser must provide complete advertisement details");
       
       try { 
        // Attempting to submit an advertisement with incomplete details 
        advertiser.submitAdvert(advert4, marketingEmployee); 
       } 
        catch (IllegalArgumentException e) {
          System.err.println(e.getMessage());   
        }  
       
       
      //5th OCL Implementation: System must validate Advertisement before forwarding to Editor
       System.out.println(" ");
       System.out.println("5th OCL Implementation: System must validate Advertisement before forwarding to Editor");
       
       try {
            //Attempting to have the editor review an advertisement that is not validated
            editor.reviewAdvertisement(advert3, system);  // OCL Precondition: Advert must be 'Validated'
        } 
           catch (IllegalStateException e) {
            System.err.println(e.getMessage());  // Error if status is not 'Validated'
        } 
         
        //Testing with Validated Advertisement
        system.validateAdvertDetails(advert2);
        editor.reviewAdvertisement(advert2, system);
}
}
