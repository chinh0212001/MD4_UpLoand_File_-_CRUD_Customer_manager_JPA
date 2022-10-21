package rikkei.academy.model;

import org.springframework.web.multipart.MultipartFile;

public class CustomerForm {
    private Long id;
    private String firstName;
    private String lastName;
    private MultipartFile avatar;

    public CustomerForm() {
    }

    public CustomerForm(Long id, String firstName, String lastName, MultipartFile avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
