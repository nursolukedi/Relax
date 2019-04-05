package com.relax.birdie.relax;

/**
 * User class that contains user-related information
 */
public class User {
    // Constants regarding calculation of maximum heart rate by gender
    // Maximum heart rates by gender
    private static final int maxHeartRateMale = 200;
    private static final int maxHeartRateFemale = 206;

    // Adjustment factor for heart rate by age, for diff. genders
    private static final double heartRateCoeffMale = 1;
    private static final double heartRateCoeffFemale = 0.88;

    // Default fitness level of users
    private static final int defaultFitnessLevel = 3;

    // Discount factor for heart rate by fitness level
    private static final double fitnessLevelDiscountFactor = 0.1;

    // Stress threshold for heart rate percentile
    private static final double stressThreshold = 0.7;

    // Parameters
    private String name;
    private String surname;
    private String email;
    private int age;
    private int gender;  // 0 = not known, 1 = male, 2 = female, 9 = non-applicable
    private int fitnessLevel; // 0: obese, 5: athlete

    // Constructor with email only
    public User (String email) {
        this.email = email;
        this.name = "";
        this.surname = "";
        this.age = -1;
        this.gender = 0;
        this.fitnessLevel = 3;
    }

    // Full constructor
    public User (String email, String name, String surname, int age, int gender, int fitnessLevel) {
        this.gender = gender;
        this.age = age;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.fitnessLevel = fitnessLevel;
    }

    // Getter and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getFitnessLevel() {
        return fitnessLevel;
    }

    public void setFitnessLevel(int fitnessLevel) {
        this.fitnessLevel = fitnessLevel;
    }

    /**
     * Function to determine whether user is stressed or not.
     * @param heartRate Measured heart rate from user
     * @return Whether the user is currently stressed or not
     */
    public boolean isStressed(int heartRate) {
        int ageTemp; // age to be used for calculation
        int maxHeartRate;
        double adjustmentFactor;

        // Adjust max heart rate and age adjustment coefficient by gender
        if (this.gender == 1) { // user is a male
            maxHeartRate = maxHeartRateMale;
            adjustmentFactor = heartRateCoeffMale;
        }
        else if (this.gender == 2) { // user is a female
            maxHeartRate = maxHeartRateFemale;
            adjustmentFactor = heartRateCoeffFemale;
        }
        else { // gender is not known
            maxHeartRate = (maxHeartRateMale + maxHeartRateFemale)/2;
            adjustmentFactor = (heartRateCoeffFemale + heartRateCoeffMale) / 2;
        }

        // Adjust max heart rate by fitness level
        maxHeartRate = maxHeartRate + (int)(maxHeartRate * (defaultFitnessLevel - this.fitnessLevel) *
                        fitnessLevelDiscountFactor);

        // Check if age is valid, if not assume 20 yr old person
        if (this.age == -1)
            ageTemp = 20;
        else
            ageTemp = this.age;

        // Adjust max heart rate by age
        maxHeartRate = (int)(maxHeartRate - (ageTemp * adjustmentFactor));

        // Calculate heart rate percentile. Return true if percentile is above threshold.
        double heartRatePercentile = (double) heartRate / maxHeartRate;
        return heartRatePercentile > stressThreshold;
    }

}
