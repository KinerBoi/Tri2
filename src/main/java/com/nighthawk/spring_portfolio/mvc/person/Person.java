package com.nighthawk.spring_portfolio.mvc.person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.mongodb.core.aggregation.DateOperators.DayOfMonth;
import org.springframework.format.annotation.DateTimeFormat;

import com.vladmihalcea.hibernate.type.json.JsonType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/*
Person is a POJO, Plain Old Java Object.
First set of annotations add functionality to POJO
--- @Setter @Getter @ToString @NoArgsConstructor @RequiredArgsConstructor
The last annotation connect to database
--- @Entity
 */





@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name="json", typeClass = JsonType.class)
public class Person {
    
    // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // email, password, roles are key attributes to login and authentication
    @NotEmpty
    @Size(min=5)
    @Column(unique=true)
    @Email
    private String email;

    @NotEmpty
    private String password;

    // @NonNull, etc placed in params of constructor: "@NonNull @Size(min = 2, max = 30, message = "Name (2 to 30 chars)") String name"
    @NonNull
    @Size(min = 2, max = 30, message = "Name (2 to 30 chars)")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;


    

    /* HashMap is used to store JSON for daily "stats"
    "stats": {
        "2022-11-13": {
            "calories": 2200,
            "steps": 8000
        }
    }
    */
    @Type(type="json")
    @Column(columnDefinition = "jsonb")
    private Map<String,Map<String, Object>> stats = new HashMap<>(); 

    @Column(unique = false)
    private double height;

    @Column(unique = false)
    private double age;

    @Column(unique=false)
    private double weight;

    

    // Constructor used when building object from an API
    public Person(String email, String password, String name, Date dob, double height, double age, double weight) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.id = id;
        this.dob = dob;
        this.height = height;
        this.age = age;
        this.weight = weight;
        
    }

    // A custom getter to return age from dob attribute
    public int getAge() {
        if (this.dob != null) {
            LocalDate birthDay = this.dob.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int age = Period.between(birthDay, LocalDate.now()).getYears();
            if(this.getAge() >= 18) {
                return 1;
            }
            else{
                System.out.println(age + "Is child");
            }

        return -1;
    }
        return 0;
    }

    

    public double bodyMassIndex(double height, double weight) {
        double bmi = (weight/(height * height)) * 703;
        return bmi;
    }

    public double bodyFatPercentage (double bmi, double height, double weight, double age) {
        double bfp = ((1.20 * bodyMassIndex(height, weight)) + (0.23 * age) - 5.4);
        return bmi;
    }


    public double netCalories () {

        Scanner steps = new Scanner(System.in);
        System.out.println("Enter the amount of steps you took today");
        double stepsValue = steps.nextDouble();

        Scanner calorie = new Scanner(System.in);
        System.out.println("Enter the amount of calories you ate today");
        double calorieValue = calorie.nextDouble();

        double caloriesBurned = stepsValue * 0.05;
        double netCaloriePerDay = calorieValue - caloriesBurned;
        return netCaloriePerDay;
    }

    public String toString () {
        return ("{ \"email\": " + this.email + ", " + "\"password\": " + this.password + ", " + "\"name\": " + this.name + ", " + "\"dob\": " + this.dob + ", " + "\"password\": " 
        + this.password);
    }

    public static void main(String args[]) {

        Person mandem = new Person();

        Date dob= new GregorianCalendar(2005,5,28);
        
        Person Kinish = new Person("kinish2005@gmail.com","2334","Kinish Sathish",19, dob,70.5,154,);

    }

    
 
}