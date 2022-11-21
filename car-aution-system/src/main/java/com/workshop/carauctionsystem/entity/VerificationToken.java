package com.workshop.carauctionsystem.entity;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verificationtoken")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "expiryDate")
    private Date expiryDate;
    @Column(name = "token")
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "userId", foreignKey = @ForeignKey(name = "verificationtoken_userid_foreign"), referencedColumnName = "id")
    private User user;


    public VerificationToken() {
    }

    public VerificationToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
