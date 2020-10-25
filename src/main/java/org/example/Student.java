package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Student {
    private static final Logger logger = LogManager.getLogger(Student.class.getName());
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private Long ID;
    private boolean err=false;
    private Date today = new Date();
    String[] genderValues = {"M", "F"};
    List<String> genderList = Arrays.asList(genderValues);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );

    public Student(String firstName, String lastName, String gender, String ID, String dob) {
        try {
            if (firstName == null || firstName.trim().isEmpty()) {
                throw new IllegalArgumentException("First Name is Empty");
            } else {
                this.firstName = firstName;
            }
            if (lastName == null || lastName.trim().isEmpty()) {
                throw new IllegalArgumentException("Last Name is Empty");
            } else {
                this.lastName = lastName;
            }

            if (gender == null || !genderList.contains(gender.toUpperCase())) {
                throw new IllegalArgumentException("error on gender input");
            } else {
                this.gender = gender;
            }

            LocalDate date = LocalDate.parse(dob, formatter);

            if (date.getYear()<= 1900 || date.getYear()>(LocalDate.now().getYear()-18)) {
                throw new IllegalArgumentException("Date of birth not in accepted range");
            }else{
                this.dob = date;
            }

            //if(ID.toString().matches("\b[1-8]\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])(0[1-9]|[1-4]\d|5[0-2]|99)\d{4}\b))
            this.ID = Long.parseLong(ID);
       }catch (IllegalArgumentException | DateTimeParseException ex){
           logger.error(ex);
//        }
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public Long getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return false;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return ID.equals(student.ID);
    }

    @Override
    public int hashCode(){
        return Objects.hash(ID);
    }

}
