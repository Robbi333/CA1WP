package Business;
import java.util.Date;

public class members {
    private int MemberID;
    private String Username;
    private String Password;
    private String First_Name;
    private String Last_Name;
    private String Email;
    private String Address1;
    private String Address2;
    private String Eircode;
    private String Phone_Number;
    private Date Registration_Date;

    // Constructors, getters, and setters
    public members() {

    }

    public members(int memberID, String username, String password, String first_Name,
                   String last_Name, String email, String address1, String address2, String eircode,
                   String phone_Number, Date registration_Date) {
        MemberID = memberID;
        Username = username;
        Password = password;
        First_Name = first_Name;
        Last_Name = last_Name;
        Email = email;
        Address1 = address1;
        Address2 = address2;
        Eircode = eircode;
        Phone_Number = phone_Number;
        Registration_Date = registration_Date;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String address1) {
        Address1 = address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String address2) {
        Address2 = address2;
    }

    public String getEircode() {
        return Eircode;
    }

    public void setEircode(String eircode) {
        Eircode = eircode;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public Date getRegistration_Date() {
        return Registration_Date;
    }

    public void setRegistration_Date(Date registration_Date) {
        Registration_Date = registration_Date;
    }

    @Override
    public String toString() {
        return "members{" +
                "MemberID=" + MemberID +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", First_Name='" + First_Name + '\'' +
                ", Last_Name='" + Last_Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Address1='" + Address1 + '\'' +
                ", Address2='" + Address2 + '\'' +
                ", Eircode='" + Eircode + '\'' +
                ", Phone_Number='" + Phone_Number + '\'' +
                ", Registration_Date=" + Registration_Date +
                '}';
    }
}
