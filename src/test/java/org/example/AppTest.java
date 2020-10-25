package org.example;

import static org.junit.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static final Logger logger = LogManager.getLogger(Student.class.getName());

    @Test
    public void addStudent()
    {
        Set<Student> studentList = new HashSet<>();
        studentList.add(new Student("Ion", "1", "M", "1900505261698", "05/05/1888"));
        studentList.add(new Student("Sadsa", "aaaaaa", "F", "2760101262698", "01/01/1976"));

        Assert.assertEquals(2, studentList.size(), 0);
    }

    @Test
    public void delete(){

        String ID;
        Set<Student> studentList = new HashSet<>();
        studentList.add(new Student("Ion", "1", "M", "1900505261698", "05/05/1990"));
        studentList.add(new Student("Sadsa", "aaaaaa", "F", "2760101262698", "01/01/1976"));
        ID="2760101262698";

        boolean exists = false;

        try {
            if (ID == null || ID.trim().isEmpty()) {
                throw new IllegalArgumentException("ID empty");
            }else {
                for (Student s : studentList) {
                    if (s.getID() == Long.parseLong(ID)) {
                        exists = true;
                    }
                }
                System.out.println(exists);
                if(!exists){
                    throw new IllegalArgumentException("ID not found");
                }else{
                    studentList.removeIf(i->i.getID()==Long.parseLong(ID));
                }

            }
        }catch(IllegalArgumentException ex){
            logger.error(ex);
        }

        Assert.assertEquals(1, studentList.size(), 0);
    }


    @Test
    public void getStudentsByAge(){
        Set<Student> studentList = new HashSet<>();
        studentList.add(new Student("Ion", "1", "M", "1900505261698", "05/05/1990"));
        studentList.add(new Student("Sadsa", "aaaaaa", "F", "2760101262698", "01/01/1976"));

        String age = "-30";
        try{
            if(Integer.parseInt(age)<0){
                throw new IllegalArgumentException("Age is negative");
            }else {
                for (Student s : studentList) {
                    if (LocalDate.now().getYear() - s.getDob().getYear() == Integer.parseInt(age)) {
                        System.out.println(s.getFirstName() + " " + s.getLastName());
                    }
                }
            }

        } catch (NumberFormatException ex){
            logger.error(ex.getMessage() + " cannot be converted to numeric");
        } catch (IllegalArgumentException ex){
            logger.error(ex.getMessage() );
        }


    }
}
