import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        WestminsterSkinConsultationManager skinConsultationManager = new WestminsterSkinConsultationManager();
        Scanner input = new Scanner(System.in);
        System.out.println("\n+++++++++WESTMINSTER SKIN CONSULTATION MANAGER+++++++++");
        System.out.print("\nDo You Want To Load Previously Saved Program Data (Enter Y for Yes or Any Key To Continue)? ");
        String answer = input.next();
        if (answer.equalsIgnoreCase("Y")){
            skinConsultationManager.loadFromFile();
        }
        skinConsultationManager.initializeTimeSlots();
        System.out.print("""

                    +++++++++MAIN MENU+++++++++
                    100 or AND : Add A New Doctor
                    101 or DAD : Delete A Doctor
                    102 or PLD : View List Of Doctors
                    103 or ACL : Add Consultation
                    104 or CCL : Cancel Consultation
                    105 or DNV : Decrypt Notes
                    106 or VCL : View Consultations
                    107 or SIF : Save In File
                    108 or GUI : Open User Interface
                    999 or EXT : Exit the Program
                    """);
        //noinspection InfiniteLoopStatement
        while(true) {
            System.out.print("\nSelect An Option To Proceed: ");
            String selection = input.next();
            switch(selection.toUpperCase()) {
                case "100", "AND" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Add A Doctor +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    if (skinConsultationManager.checkSize() == 1) {
                        skinConsultationManager.addDoctor();
                    } else if (skinConsultationManager.checkSize() == 2) {
                        System.out.println("\nSorry! Cannot Add More Than 10 Doctors.");
                    }
                }
                case "101", "DAD" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Delete A Doctor +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.deleteDoctor();
                }
                case "102", "PLD" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ List Of Doctors +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.printDoctors();
                }
                case "103", "ACL" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Add Consultation +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.addConsultation();
                }
                case "104", "CCL" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Cancel Consultation +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.cancelConsultation();
                }
                case "105", "DNV" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Decrypt Notes  +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.decryptNotes();
                }
                case "106", "VCL" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ View Consultations +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.viewConsultations();
                }
                case "107", "SIF" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Save To File +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.saveToFile();
                }
                case "108", "GUI" ->{
                    if(skinConsultationManager.getDoctorsList().isEmpty()){
                        System.out.println("\nAdd A Doctor To Open GUI!");
                    }
                    else {
                        new UserInterface();
                    }
                }
                case "999", "EXT" -> {
                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++ Exiting Program +++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    skinConsultationManager.exit();
                }
                default -> System.out.println("\nInvalid Option, Try Again!");
            }
        }
    }
}
