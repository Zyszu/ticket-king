package pl.matzysz.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "company_verifications")
public class CompanyVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    Company company;

    @ManyToOne(fetch = FetchType.EAGER)
    User reviewedBy;

    @NotNull
    private boolean verificationComplete;

    private boolean accepted;

    private String comment;



    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public User getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(User reviewedBy) { this.reviewedBy = reviewedBy; }

    public boolean isVerificationComplete() { return verificationComplete; }
    public void setVerificationComplete(boolean verificationComplete) { this.verificationComplete = verificationComplete; }

    public boolean isAccepted() { return accepted; }
    public void setAccepted(boolean accepted) { this.accepted = accepted; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

}
