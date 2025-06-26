package application.server.persistence.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
public class VerificationToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="token_id", nullable = false)
    private Long id;

    @Column(name="token", nullable = false)
    private String token;

    @OneToOne(targetEntity = UserInfo.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserInfo user;

    @Column(name="expiry_date", nullable = false)
    private Date expiryDate;

    public VerificationToken() {
        this.expiryDate = calculateExpiryDate();
    }

    public VerificationToken(String token, UserInfo user) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate();
    }

    public VerificationToken(Date expiryDate,String token, UserInfo user,Long id) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate != null ? expiryDate : calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, VerificationToken.EXPIRATION);
        return new Date(cal.getTime().getTime());
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate != null ? expiryDate : calculateExpiryDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        if (this.expiryDate == null) {
            this.expiryDate = calculateExpiryDate();
        }
    }
}