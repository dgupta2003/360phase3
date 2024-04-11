//package application;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//public class Patient {
//	
//	//private String id;
//    private String firstName;
//    private String lastName;
//    //private String age;
//    private Date birthday;
//    private String username;
//    private String email;
//    private String phoneNumber;
//    private String insuranceId;
//    private String password;
//
//    private ArrayList<String> healthHistory;
//    private ArrayList<Date> visitDates;
//    
//    private boolean checkedIn;
//    
//    public Patient() {}
//	
//    public Patient(String firstName, String lastName, Date birthday) {
//    	this.firstName = firstName;
//    	this.lastName = lastName;
//    	this.birthday = birthday;
//    	this.healthHistory = new ArrayList<>();
//        this.visitDates = new ArrayList<>();
//        this.checkedIn = false;
//        
//	}
//    
//    
//	// Getters and setters for the patient object
//    
//    public void setFirstName(String firstName) {
//      	 this.firstName = firstName;
//    }
//    
//    public void setLastName(String lastName) {
//     	 this.lastName = lastName;
//   }
//    
//    public String getFirstName() {
//   	 return firstName;
//   	}
//    
//    public String getLastName() {
//      	 return lastName;
//    }
//    
//    public void setBirthday(Date birthday) {
//    	this.birthday = birthday;
//    }
//    
//    public Date getBirthday() {
//        return birthday;
//    }
//    
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//    
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    
//    public String getEmail() {
//        return email;
//    }
//    
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//    
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//    
//    public void setInsuranceId(String insuranceId) {
//        this.insuranceId = insuranceId;
//    }
//    
//    public String getInsuranceId() {
//        return insuranceId;
//    }
//    
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    
//    public String getPassword() {
//        return password;
//    }
//    
//    
//    public ArrayList<String> getHealthHistory() {
//        return healthHistory;
//    }
//
//    public void addHealthRecord(String record) {
//        healthHistory.add(record);
//    }
//
//    public ArrayList<Date> getVisitDates() {
//        return visitDates;
//    }
//
//    public void addVisitDate(Date date) {
//        visitDates.add(date);
//        //visitDates.add(removeTimeComponent(date));
//    }
//    
//    private Date removeTimeComponent(Date date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            return sdf.parse(sdf.format(date));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    
//    public String healthHistoryToString() {
//        if(healthHistory == null || healthHistory.isEmpty()) {
//        	return "No health history recorded\n";
//        }
//        else {
//        	StringBuilder stringBuilder = new StringBuilder();
//            for (String record : healthHistory) {
//                stringBuilder.append(record).append("\n");
//            }
//            return stringBuilder.toString();
//        }
//    	
//    }
//
//    public String visitDatesToString() {
//        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////        StringBuilder stringBuilder = new StringBuilder();
////        for (Date date : visitDates) {
////            stringBuilder.append(sdf.format(date)).append("\n");
////        }
////        return stringBuilder.toString();
//    	
//    	 if (visitDates == null || visitDates.isEmpty()) {
//    	        return "No visit dates recorded\n";
//    	    } else {
//    	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//    	        StringBuilder stringBuilder = new StringBuilder();
//    	        for (Date date : visitDates) {
//    	            stringBuilder.append(sdf.format(date)).append("\n");
//    	        }
//    	        return stringBuilder.toString();
//    	    }
//    }
//    
//    
//    public String toString() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("UserName: ").append(this.getUsername()).append("\n")
//                .append("First Name: ").append(firstName).append("\n")
//                .append("Last Name: ").append(lastName).append("\n")
//                .append("Birthday: ").append(sdf.format(birthday)).append("\n")
//                .append("Email: ").append(email).append("\n")
//                .append("Phone Number: ").append(this.getPhoneNumber()).append("\n")
//                .append("Insurance ID: ").append(this.getInsuranceId()).append("\n")
//                .append("Password: ").append(this.getPassword()).append("\n")
//                .append("Health History: ").append(healthHistoryToString())
//                .append("Visit Dates: ").append(visitDatesToString());
//        return stringBuilder.toString();
//    }
//    
//    public static Patient fromString(String patientInfoString) {
//        Patient patient = new Patient();
//        String[] lines = patientInfoString.split("\n");
//        //System.out.println(lines.toString());
//        for (String line : lines) {
//            String[] parts = line.split(": ");
//            String key = parts[0].trim();
//            //System.out.println(key);
//            String value = parts[1].trim();
//            //System.out.println(value);
//            switch (key) {
//                case "UserName":
//                    patient.setUsername(value);
//                    //System.out.println(value);
//                    break;
//                case "First Name":
//                    patient.setFirstName(value);
//                    //System.out.println(value);
//                    break;
//                case "Last Name":
//                    patient.setLastName(value);
//                    //System.out.println(value);
//                    break;
//                case "Birthday":
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    try {
//                        Date birthday = sdf.parse(value);
//                        patient.setBirthday(birthday);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case "Email":
//                    patient.setEmail(value);
//                    break;
//                case "Phone Number":
//                    patient.setPhoneNumber(value);
//                    break;
//                case "Insurance ID":
//                    patient.setInsuranceId(value);
//                    break;
//                case "Password":
//                    patient.setPassword(value);
//                    break;
//                case "Health History":
//                    if (!value.equals("No health history recorded")) {
//                        String[] historyRecords = value.split("\n");
//                        for (String record : historyRecords) {
//                            patient.addHealthRecord(record);
//                        }
//                    }
//                    break;
//                case "Visit Dates":
//                    if (!value.equals("No visit dates recorded")) {
//                        String[] visitDates = value.split("\n");
//                        SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                        for (String dateString : visitDates) {
//                            try {
//                                Date visitDate = visitDateFormat.parse(dateString);
//                                patient.addVisitDate(visitDate);
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                    break;
//                default:
//                    break;
//            }
//        }
//        return patient;
//    }
//    
//
//    // Getter and setter for checkedIn field
//    public boolean isCheckedIn() {
//        return checkedIn;
//    }
//
//    public void setCheckedIn(boolean checkedIn) {
//        this.checkedIn = checkedIn;
//    }
//    
//    
////	public String toString() {
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
////	    return "UserName: " + this.getUsername() + "\n" +
////	            "First Name: " + firstName + "\n" +
////	            "Last Name: " + lastName + "\n" + 
////	            "Birthday: " + sdf.format(birthday) + "\n" +
////	            "Phone Number: " + this.getPhoneNumber() + "\n" +
////	            "Insurance ID: " + this.getInsuranceId() + "\n" + 
////	            "Password: " + this.getPassword() + "\n";
////	    
////	}
//    
//}

package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Patient {
	
	//private String id;
    private String firstName;
    private String lastName;
    //private String age;
    private Date birthday;
    private String username;
    private String email;
    private String phoneNumber;
    private String insuranceId;
    private String password;

    private ArrayList<String> healthHistory;
    private ArrayList<Date> visitDates;
    
    private boolean checkedIn;
    
    public Patient() {}
	
    public Patient(String firstName, String lastName, Date birthday) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.birthday = birthday;
    	this.healthHistory = new ArrayList<>();
        this.visitDates = new ArrayList<>();
        this.checkedIn = false;
        
	}
    
    
	// Getters and setters for the patient object
    
    public void setFirstName(String firstName) {
      	 this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
     	 this.lastName = lastName;
   }
    
    public String getFirstName() {
   	 return firstName;
   	}
    
    public String getLastName() {
      	 return lastName;
    }
    
    public void setBirthday(Date birthday) {
    	this.birthday = birthday;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }
    
    public String getInsuranceId() {
        return insuranceId;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
    
    
    public ArrayList<String> getHealthHistory() {
        return healthHistory;
    }

    public void addHealthRecord(String record) {
        healthHistory.add(record);
    }

    public ArrayList<Date> getVisitDates() {
        return visitDates;
    }

    public void addVisitDate(Date date) {
        visitDates.add(date);
        //visitDates.add(removeTimeComponent(date));
    }
    
    private Date removeTimeComponent(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(date));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public String healthHistoryToString() {
        if(healthHistory == null || healthHistory.isEmpty()) {
        	return "No health history recorded\n";
        }
        else {
        	StringBuilder stringBuilder = new StringBuilder();
            for (String record : healthHistory) {
                stringBuilder.append(record).append("\n");
            }
            return stringBuilder.toString();
        }
    	
    }

    public String visitDatesToString() {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (Date date : visitDates) {
//            stringBuilder.append(sdf.format(date)).append("\n");
//        }
//        return stringBuilder.toString();
    	
    	 if (visitDates == null || visitDates.isEmpty()) {
    	        return "No visit dates recorded\n";
    	    } else {
    	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	        StringBuilder stringBuilder = new StringBuilder();
    	        for (Date date : visitDates) {
    	            stringBuilder.append(sdf.format(date)).append("\n");
    	        }
    	        return stringBuilder.toString();
    	    }
    }
    
    
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UserName: ").append(this.getUsername()).append("\n")
                .append("First Name: ").append(firstName).append("\n")
                .append("Last Name: ").append(lastName).append("\n")
                .append("Birthday: ").append(sdf.format(birthday)).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Phone Number: ").append(this.getPhoneNumber()).append("\n")
                .append("Insurance ID: ").append(this.getInsuranceId()).append("\n")
                .append("Password: ").append(this.getPassword()).append("\n")
                .append("Health History: ").append(healthHistoryToString())
                .append("Visit Dates: ").append(visitDatesToString());
        return stringBuilder.toString();
    }
    
    public static Patient fromString(String patientInfoString) {
        Patient patient = new Patient();
        String[] lines = patientInfoString.split("\n");
        //System.out.println(lines.toString());
        for (String line : lines) {
            String[] parts = line.split(": ");
            String key = parts[0].trim();
            //System.out.println(key);
            String value = parts[1].trim();
            //System.out.println(value);
            switch (key) {
                case "UserName":
                    patient.setUsername(value);
                    //System.out.println(value);
                    break;
                case "First Name":
                    patient.setFirstName(value);
                    //System.out.println(value);
                    break;
                case "Last Name":
                    patient.setLastName(value);
                    //System.out.println(value);
                    break;
                case "Birthday":
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date birthday = sdf.parse(value);
                        patient.setBirthday(birthday);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "Email":
                    patient.setEmail(value);
                    break;
                case "Phone Number":
                    patient.setPhoneNumber(value);
                    break;
                case "Insurance ID":
                    patient.setInsuranceId(value);
                    break;
                case "Password":
                    patient.setPassword(value);
                    break;
                case "Health History":
                    if (!value.equals("No health history recorded")) {
                        String[] historyRecords = value.split("\n");
                        for (String record : historyRecords) {
                            patient.addHealthRecord(record);
                        }
                    }
                    break;
                case "Visit Dates":
                    if (!value.equals("No visit dates recorded")) {
                        String[] visitDates = value.split("\n");
                        SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        for (String dateString : visitDates) {
                            try {
                                Date visitDate = visitDateFormat.parse(dateString);
                                patient.addVisitDate(visitDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return patient;
    }
    

    // Getter and setter for checkedIn field
    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

//	public void setHealthHistory(String value) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void setVisitDates(String value) {
//		// TODO Auto-generated method stub
//		
//	}
    
    
//	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//	    return "UserName: " + this.getUsername() + "\n" +
//	            "First Name: " + firstName + "\n" +
//	            "Last Name: " + lastName + "\n" + 
//	            "Birthday: " + sdf.format(birthday) + "\n" +
//	            "Phone Number: " + this.getPhoneNumber() + "\n" +
//	            "Insurance ID: " + this.getInsuranceId() + "\n" + 
//	            "Password: " + this.getPassword() + "\n";
//	    
//	}
    
}