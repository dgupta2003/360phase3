package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {

	private Queue<Patient> patientQueue;
	private Stage primaryStage;
	private Scene mainScene, 
	docLgnScene, 
	nurLgnScene,nurChkIn, 
	oldPLgnScene,
	newPScene1, newPScene2, newPScene3;

	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.patientQueue = new LinkedList<>();
		
		//creating the main scene and other scenes (for respective users)
		createMainScene();
		createDocLgn();
		createNurLgn();
		createOldPLgn();
		createNewPatientP1();
		createNurseP1();

		// Set title and scene for the primary stage
		primaryStage.setTitle("CSE360 Group Project Application (Th34)");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	private void createMainScene() {

		//using a VBox for a vertical layout of buttons on the Main Page
		VBox mainLayout = new VBox(20);
		mainLayout.setAlignment(Pos.CENTER);

		//creating label for the welcome message on top of main page
		Label welcomeLabel = new Label("Welcome to Carter's Family Clinic!");
		welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		//creating the three buttons for taking the user to different views: 
		Button newPatLgnBtn = new Button("New Patient login");
		Button exPatLgnBtn = new Button("Existing Patient Login");
		Button docLgnBtn = new Button("Doctor's Login");
		Button nurseLgnBtn = new Button("Nurse's Login");

		//set the action for each button i.e. each button is supposed to take use to a different page
		newPatLgnBtn.setOnAction(e -> primaryStage.setScene(newPScene1));
		exPatLgnBtn.setOnAction(e -> primaryStage.setScene(oldPLgnScene));
		docLgnBtn.setOnAction(e -> primaryStage.setScene(docLgnScene));
		nurseLgnBtn.setOnAction(e -> primaryStage.setScene(nurLgnScene));

		//adding all children to mainLayout
		mainLayout.getChildren().addAll(welcomeLabel, newPatLgnBtn, exPatLgnBtn, docLgnBtn, nurseLgnBtn);
		mainScene = new Scene(mainLayout, 600, 400);
		primaryStage.setScene(mainScene);
	}	

	private void createOldPLgn() {
		VBox layout = new VBox(20);

		Label title = new Label("Existing Patient Panel");
		title.setFont(Font.font(14));

		Label username = new Label("User Name: ");
		TextField userName = new TextField();
		HBox name = new HBox(20);
		name.getChildren().addAll(username, userName);

		Label password = new Label("Password:   ");
		PasswordField passWord = new PasswordField(); // Using PasswordField for password input
		HBox pass = new HBox(20);
		pass.getChildren().addAll(password, passWord);

		Button login = new Button("Login");
		login.setOnAction(e -> {
			String enteredUsername = userName.getText();
			String enteredPassword = passWord.getText();
			userName.clear();
			passWord.clear();
			
			Patient patient = readPatientFromFile(enteredUsername + "_PatientInfo.txt");

			if (patient != null && patient.getPassword().equals(enteredPassword)) {
				createPatientDashboardScene(patient);
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username or password.");
				alert.showAndWait();
			}
		});

		Button back = new Button("Back");
		back.setOnAction(e -> primaryStage.setScene(mainScene));

		HBox button = new HBox(300);
		button.getChildren().addAll(back, login);
		button.setAlignment(Pos.CENTER);

		layout.getChildren().addAll(title, name, pass, button);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		oldPLgnScene = new Scene(layout, 600, 400);
	}

	private void createPatientDashboardScene(Patient patient) {
		VBox layout = new VBox(20);

		Label titleLabel = new Label("Patient Dashboard");
		titleLabel.setFont(Font.font(14));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Display patient information
		Label usernameLabel = new Label("Username: " + patient.getUsername());
		Label firstNameLabel = new Label("First Name: " + patient.getFirstName());
		Label lastNameLabel = new Label("Last Name: " + patient.getLastName());
		Label birthdayLabel = new Label("Birthday: " + sdf.format(patient.getBirthday()) .toString());
		Label emailLabel = new Label("Email: " + patient.getEmail());
		Label phoneNumberLabel = new Label("Phone Number: " + patient.getPhoneNumber());
		Label insuranceIdLabel = new Label("Insurance ID: " + patient.getInsuranceId());

		// Non-editable text fields for health history and visit dates
		TextArea healthHistoryTextArea = new TextArea(patient.healthHistoryToString());
		healthHistoryTextArea.setEditable(false);
		TextArea visitDatesTextArea = new TextArea(patient.visitDatesToString());
		visitDatesTextArea.setEditable(false);

		Button backButton = new Button("Back");
		backButton.setOnAction(e -> primaryStage.setScene(oldPLgnScene));

		layout.getChildren().addAll(titleLabel, usernameLabel, firstNameLabel, lastNameLabel,
				birthdayLabel, emailLabel, phoneNumberLabel, insuranceIdLabel,
				new Label("Health History:"),
				healthHistoryTextArea,
				new Label("Visit Dates:"),
				visitDatesTextArea,
				backButton);

		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);

		Scene patientDashboardScene = new Scene(layout, 600, 400);
		primaryStage.setScene(patientDashboardScene);
	}

	private void createDocLgn() {
		VBox layout = new VBox(20);


		Label title = new Label("Doctor Panel");
		title.setFont(Font.font(14));


		Label username = new Label("User Name: ");
		TextField userName = new TextField();
		HBox name = new HBox(20);
		name.getChildren().addAll(username, userName);


		Label password = new Label("Password:   ");
		TextField passWord = new TextField();
		//		        VBox layout = new VBox(20);
		HBox pass = new HBox(20);
		pass.getChildren().addAll(password, passWord);


		Button enter = new Button("Login");
		enter.setOnAction(e -> {
	        createDoctorPatientSelectionPage(); // Call the method here
	    });
		Button back = new Button("Back");
		back.setOnAction(e -> primaryStage.setScene(mainScene));
		HBox button = new HBox(300);
		button.getChildren().addAll(back, enter);
		button.setAlignment(Pos.CENTER);


		layout.getChildren().addAll(title, name, pass, button);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		docLgnScene = new Scene(layout, 600, 400);

	}

	private void createNurLgn() {
		VBox layout = new VBox(20);

		Label title = new Label("Nurse Panel");
		title.setFont(Font.font(14));


		Label username = new Label("User Name: ");
		TextField userName = new TextField();
		HBox name = new HBox(20);
		name.getChildren().addAll(username, userName);


		Label password = new Label("Password:   ");
		TextField passWord = new TextField();
		HBox pass = new HBox(20);
		pass.getChildren().addAll(password, passWord);


		Button enter = new Button("Login");
		enter.setOnAction(e-> {
			userName.clear();
			passWord.clear();
			primaryStage.setScene(nurChkIn);
			
		});

		Button back = new Button("Back");
		back.setOnAction(e -> primaryStage.setScene(mainScene));
		HBox button = new HBox(300);
		button.getChildren().addAll(back, enter);
		button.setAlignment(Pos.CENTER);


		layout.getChildren().addAll(title, name, pass, button);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);
		nurLgnScene = new Scene(layout, 600, 400);

	}

	private void createNurseP1() {
		VBox layout = new VBox(20);


		Label title = new Label("Patient Check-in");
		title.setFont(Font.font(14));


		Label userName = new Label("Username: ");
		TextField user = new TextField();


		HBox name1 = new HBox(20);
		name1.getChildren().addAll(userName, user);


		Button enter = new Button("Enter");
		enter.setOnAction(e -> {
		String enteredUsername = user.getText();
		user.clear();
		
		Patient patient = readPatientFromFile(enteredUsername + "_PatientInfo.txt");

		if (patient != null && patient.getUsername().equals(enteredUsername)) {
			createNurseCheckInPage(patient);
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Invalid username or password.");
			alert.showAndWait();
		}
	});

		Button back = new Button("Back");
		back.setOnAction(e -> primaryStage.setScene(nurLgnScene));
		
		Button newPatient = new Button("Add New Patient");
		newPatient.setOnAction(e -> primaryStage.setScene(newPScene1));
		
		Label queueLabel = new Label("Patient Queue:");
	    TextArea queueTextArea = new TextArea();
	    queueTextArea.setEditable(false);
	    for (Patient p : patientQueue) {
	        queueTextArea.appendText(p.getFirstName() + " " + p.getLastName() + "\n");
	    }
		
		HBox buttonbox = new HBox(100);
		buttonbox.getChildren().addAll(back, newPatient, enter);
		buttonbox.setAlignment(Pos.CENTER);


		layout.getChildren().addAll(title, name1, buttonbox, queueLabel, queueTextArea);
		layout.setPadding(new Insets(20, 20, 20, 20));
		layout.setAlignment(Pos.CENTER);

		nurChkIn = new Scene(layout, 600, 400);
		primaryStage.setScene(nurChkIn);


	}
	
	private void createNurseCheckInPage(Patient patient) {
	    VBox layout = new VBox(20);

	    Label titleLabel = new Label("Patient Check-In");
	    titleLabel.setFont(Font.font(14));

	    // Display patient information
	    Label patientInfoLabel = new Label("Patient Information:");
	    TextArea patientInfoTextArea = new TextArea();
	    patientInfoTextArea.setEditable(false);
	    patientInfoTextArea.setWrapText(true);
	    patientInfoTextArea.setText(patient.toString());

	    // Vitals entry fields
	    Label vitalsLabel = new Label("Enter Vitals:");
	    TextField heightField = new TextField();
	    heightField.setPromptText("Height (cm)");
	    TextField weightField = new TextField();
	    weightField.setPromptText("Weight (kg)");
	    TextField bloodPressureField = new TextField();
	    bloodPressureField.setPromptText("Blood Pressure");

	    // Button to check-in
	    Button checkInButton = new Button("Check-In");
	    checkInButton.setOnAction(e -> {
	        // Validate and save vitals and visit date
	        String height = heightField.getText();
	        String weight = weightField.getText();
	        String bloodPressure = bloodPressureField.getText();
	      
	        // Add patient to queue or perform further actions
	        addToPatientQueue(patient);
	        
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Success");
	        alert.setHeaderText(null);
	        alert.setContentText(patient.getFirstName() + " " + patient.getLastName() + " is successfully checked-in!");
	        alert.showAndWait();
	        
	        
	        createNurseP1();
	        // For now, let's just print the patient details
	    });

	    // Back button
	    Button backButton = new Button("Back");
	    backButton.setOnAction(e -> {
	        // Navigate back to the nurse login page
	        primaryStage.setScene(nurLgnScene);
	    });

	    // Add components to layout
	    layout.getChildren().addAll(
	            titleLabel,
	            patientInfoLabel,
	            patientInfoTextArea,
	            vitalsLabel,
	            heightField,
	            weightField,
	            bloodPressureField,
	            checkInButton,
	            backButton
	    );

	    layout.setPadding(new Insets(20));
	    layout.setAlignment(Pos.CENTER);

	    Scene nurseCheckInScene = new Scene(layout, 600, 400);
	    primaryStage.setScene(nurseCheckInScene);
	}
	

	private void createNewPatientP1() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//creating a label for the receptionist page heading
		Label intakeFormLabel = new Label("Patient Intake Form");
		intakeFormLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		//creating text fields for various patient information to be put in
		TextField firstNameField = new TextField();
		TextField lastNameField = new TextField();
		DatePicker birthdayPicker = new DatePicker();

		//creating a button to save all the patient information in a new patient object
		Button submitBtn = new Button("Submit");

		//setting the action on button click
		submitBtn.setOnAction(e -> {

			if(!firstNameField.getText().isBlank() && !lastNameField.getText().isBlank() && birthdayPicker.getValue() != null) {


				//creating a new Patient object with the entered information
				Patient patient = new Patient(firstNameField.getText(),
						lastNameField.getText(),
						java.sql.Date.valueOf(birthdayPicker.getValue()));

				LocalDate selectedDate = birthdayPicker.getValue();

				if (isBelowAge(selectedDate, 12)) {
					//usernameLabel.setText("Age must be at least 12 years.");
					System.out.println("YES");
					//return;
				}

				patient.setUsername(generateUsername(patient));

				//clearing all fields
				firstNameField.clear();
				lastNameField.clear();
				birthdayPicker.setValue(null);

				createNewPatientP2(patient);

			} else {
				// If any field is empty, show an error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all fields.");
				alert.showAndWait();
			}


		});

		//setting the position of each field on the screen in the gridpane
		grid.add(intakeFormLabel, 0, 0, 2, 1);
		grid.add(new Label("First Name:"), 0, 1);
		grid.add(firstNameField, 1, 1);
		grid.add(new Label("Last Name:"), 0, 2);
		grid.add(lastNameField, 1, 2);

		Label birthdayLabel = new Label("Birthday:");
		grid.add(birthdayLabel, 0, 3);

		grid.add(birthdayPicker, 1, 3);

		//adding the save button to the gridpane
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitBtn);
		grid.add(hbBtn, 1, 4);

		//adding the quit button to gridpane in order to quit to the main page
		Button quitBtn = new Button("Quit to Main Page");
		quitBtn.setOnAction(e -> primaryStage.setScene(mainScene));
		grid.add(quitBtn, 0, 4);

		newPScene1 = new Scene(grid, 600, 400);
	}

	private void createNewPatientP2(Patient patient) {

		//using a gridpane for the receptionist scene since we have a lot of text fields to be filled out in order
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//creating a label for the receptionist page heading
		Label intakeFormLabel = new Label("Patient Information");
		intakeFormLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		//creating text fields for various patient information to be put in
		TextField emailField = new TextField();
		TextField phoneField = new TextField();
		TextField insuranceIdField = new TextField();

		//creating a button to save all the patient information in a new patient object
		Button submitBtn = new Button("Submit");

		//setting the action on button click
		submitBtn.setOnAction(e -> {	

			//checking if all fields are filled out or not
			if (!emailField.getText().isBlank() & !phoneField.getText().isBlank() && !insuranceIdField.getText().isBlank()) {
				patient.setEmail(emailField.getText());
				patient.setPhoneNumber(phoneField.getText());
				patient.setInsuranceId(insuranceIdField.getText());

				emailField.clear();
				phoneField.clear();
				insuranceIdField.clear();

				createNewPatientP3(patient);
				//	            	primaryStage.setScene(newPScene3);

			} else {
				// If any field is empty, show an error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all fields.");
				alert.showAndWait();
			}
		});

		//setting the position of each field on the screen in the gridpane
		grid.add(intakeFormLabel, 0, 0, 2, 1);
		grid.add(new Label("Email:"), 0, 1);
		grid.add(emailField, 1, 1);
		grid.add(new Label("Phone Number:"), 0, 2);
		grid.add(phoneField, 1, 2);
		grid.add(new Label("Insurance ID:"), 0, 3);
		grid.add(insuranceIdField, 1, 3);

		//adding the save button to the gridpane
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(submitBtn);
		grid.add(hbBtn, 1, 4);

		//adding the quit button to gridpane in order to quit to the main page
		Button quitBtn = new Button("Quit to Main Page");
		quitBtn.setOnAction(e -> primaryStage.setScene(newPScene1));
		grid.add(quitBtn, 0, 4);

		newPScene2 = new Scene(grid, 600, 400);
		primaryStage.setScene(newPScene2);
	}

	private void createNewPatientP3(Patient patient) {

		//using a gridpane for the receptionist scene since we have a lot of text fields to be filled out in order
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		//creating a label for the receptionist page heading
		Label intakeFormLabel = new Label("Set Password");
		intakeFormLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

		TextField passwordField = new TextField();

		//creating a button to save all the patient information in a new patient object
		Button saveBtn = new Button("Save");

		//setting the action on button click
		saveBtn.setOnAction(e -> {

			//checking if all fields are filled out or not
			if (!passwordField.getText().isBlank()) {

				patient.setPassword(passwordField.getText());

				String userName = savePatientInfo(patient);

				if (userName != null) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Information");
					alert.setHeaderText(null);
					alert.setContentText("Patient information saved successfully. Username: " + userName);
					alert.showAndWait();

					passwordField.clear();
					createMainScene();
					//patient = null;

				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to save patient information.");
					alert.showAndWait();
					passwordField.clear();
				}
				
				
				
			} else {
				// If any field is empty, show an error alert
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Please fill in all fields.");
				alert.showAndWait();
			}
		});

		//setting the position of each field on the screen in the gridpane
		grid.add(intakeFormLabel, 0, 0, 2, 1);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(passwordField, 1, 1);

		//adding the save button to the gridpane
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(saveBtn);
		grid.add(hbBtn, 1, 3);

		//adding the quit button to gridpane in order to quit to the main page
		Button quitBtn = new Button("Quit to Main Page");
		quitBtn.setOnAction(e -> primaryStage.setScene(mainScene));
		grid.add(quitBtn, 0, 3);

		newPScene3 = new Scene(grid, 600, 400);
		primaryStage.setScene(newPScene3);
	}

	public static String generateUsername(Patient patient) {
		// Extracting initials from first and last names
		String initials = (patient.getFirstName().substring(0, 1) + patient.getLastName().substring(0, 1)).toLowerCase();

		// Formatting birthday to get a string representation
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		String birthdayString = sdf.format(patient.getBirthday());

		// Generating username combining initials and birthday
		String username = initials + birthdayString;

		return username;
	}

	public static boolean isBelowAge(LocalDate birthday, int age) {
		LocalDate currentDate = LocalDate.now();
		Period period = Period.between(birthday, currentDate);
		return period.getYears() < age;
	}

	private String savePatientInfo(Patient patient) {
		//String patientId = generatePatientId();					//generating a unique patient ID
		String fileName = patient.getUsername() + "_PatientInfo.txt";		//setting the name of the file as specified in the template

		//writing the contents to the file
		try {
			FileWriter writer = new FileWriter(fileName);		//creating a file writer object
			writer.write(patient.toString());					//writing the patient object to the file
			writer.close();										//closing the file writer
			return patient.getUsername();									//returning the patientID generated
		} catch (IOException e) {
			e.printStackTrace();	
			return null;
		}
	}

	private Patient readPatientFromFile(String fileName) {
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line).append("\n");

			}
			String patientInfoString = stringBuilder.toString();
			// Convert patient information string to Patient object
			return Patient.fromString(patientInfoString);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void addToPatientQueue(Patient patient) {
        patient.setCheckedIn(true);
        patientQueue.offer(patient); // Add patient to the end of the queue
    }
	
	private void createDoctorPatientSelectionPage() {
	    VBox layout = new VBox(20);

	    Label title = new Label("Patient Selection");
	    title.setFont(Font.font(14));

	    Label username = new Label("Enter Patient's Username:");
	    TextField usernameField = new TextField();
	    HBox usernameBox = new HBox(20);
	    usernameBox.getChildren().addAll(username, usernameField);

	    Button enterButton = new Button("Enter");
	    enterButton.setOnAction(e -> {
	        String patientUsername = usernameField.getText();
	        Patient patient = readPatientFromFile(patientUsername + "_PatientInfo.txt");
	        if (patient != null) {
	            createDoctorPatientDashboardScene(patient);
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Error");
	            alert.setHeaderText(null);
	            alert.setContentText("Patient with the given username not found.");
	            alert.showAndWait();
	        }
	    });

	    layout.getChildren().addAll(title, usernameBox, enterButton);
	    layout.setPadding(new Insets(20, 20, 20, 20));
	    layout.setAlignment(Pos.CENTER);

	    Scene doctorPatientSelectionScene = new Scene(layout, 600, 400);
	    primaryStage.setScene(doctorPatientSelectionScene);
	}

	private void createDoctorPatientDashboardScene(Patient patient) {
	    // Create labels for patient information
	    Label firstNameLabel = new Label("First Name:");
	    Label lastNameLabel = new Label("Last Name:");
	    Label ageLabel = new Label("Birthday:");
	    Label phoneNumberLabel = new Label("Phone Number:");
	    //Label nextOfKinLabel = new Label("Next of Kin Phone Number:");

	    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    String formattedBirthday = dateFormat.format(patient.getBirthday());
	    // Create text fields for patient information
	    TextField firstNameField = new TextField(patient.getFirstName());
	    TextField lastNameField = new TextField(patient.getLastName());
	    TextField ageField = new TextField(formattedBirthday);
	    TextField phoneNumberField = new TextField(patient.getPhoneNumber());

	    // Disable text field editing
	    firstNameField.setEditable(false);
	    lastNameField.setEditable(false);
	    ageField.setEditable(false);
	    phoneNumberField.setEditable(false);

	    // Layout for patient information
	    GridPane patientInfoGrid = new GridPane();
	    patientInfoGrid.setHgap(10);
	    patientInfoGrid.setVgap(5);
	    patientInfoGrid.addRow(0, firstNameLabel, firstNameField);
	    patientInfoGrid.addRow(1, lastNameLabel, lastNameField);
	    patientInfoGrid.addRow(2, ageLabel, ageField);
	    patientInfoGrid.addRow(3, phoneNumberLabel, phoneNumberField);

	    // Main layout using VBox
	    VBox mainLayout = new VBox(20);
	    mainLayout.setPadding(new Insets(20));
	    mainLayout.setAlignment(Pos.CENTER);

	    // Add patient information section
	    Label patientInfoTitle = new Label("Patient Information");
	    mainLayout.getChildren().addAll(patientInfoTitle, patientInfoGrid);

	    // Add health issues section
	    Label healthIssuesTitle = new Label("Health Issues");
	    TextArea healthIssuesField = new TextArea();
	    healthIssuesField.setEditable(true);
	    healthIssuesField.setStyle("-fx-font-size: 12px;");
	    healthIssuesField.setPrefSize(400, 200);
	    healthIssuesField.setWrapText(true);

	    // Add immunization records section
	    Label immunizationRecordsTitle = new Label("Immunization Records");
	    TextArea immunizationRecordsField = new TextArea();
	    immunizationRecordsField.setEditable(true);
	    immunizationRecordsField.setStyle("-fx-font-size: 12px;");
	    immunizationRecordsField.setPrefSize(400, 200);
	    immunizationRecordsField.setWrapText(true);
	    
	    Button saveButton = new Button("Save");
	    saveButton.setOnAction(e -> {
	        // Get the current date for the visit
	        LocalDate visitDate = LocalDate.now();
	        String visitDateString = visitDate.toString();

	        // Get the health issues and immunization records from the text fields
	        String healthIssues = healthIssuesField.getText();
	        String immunizationRecords = immunizationRecordsField.getText();

	        // Construct the string to be saved to the patient's file
	        String dataToSave = "Visit Date: " + visitDateString + "\n"
	                          + "Health Issues: " + healthIssues + "\n"
	                          + "Immunization Records: " + immunizationRecords + "\n";

	        // Append the data to the patient's file
	        String fileName = patient.getUsername() + "_PatientInfo.txt";
	        try (FileWriter writer = new FileWriter(fileName, true)) {
	            writer.write(dataToSave);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	        // Show a confirmation message
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Information");
	        alert.setHeaderText(null);
	        alert.setContentText("Patient information saved successfully.");
	        alert.showAndWait();
	    });
	    
	    
	    Button quitButton = new Button("Quit to Main Menu");
	    quitButton.setOnAction(e -> {
	        // Call a method to go back to the main menu
	        createMainScene();
	    });
	    
	    
	    mainLayout.getChildren().addAll(healthIssuesTitle, healthIssuesField, immunizationRecordsTitle, immunizationRecordsField, saveButton, quitButton);

	    // Set the vertical grow for text areas to always occupy the available space
	    VBox.setVgrow(healthIssuesField, Priority.ALWAYS);
	    VBox.setVgrow(immunizationRecordsField, Priority.ALWAYS);

	    Scene scene = new Scene(mainLayout, 800, 600);
	    primaryStage.setScene(scene);

	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}
