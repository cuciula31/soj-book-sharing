package com.soj.booksharing.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class RentedBook {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
//    @JoinTable(name = "user_rental",
//            joinColumns = @JoinColumn(name = "rent_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("rentedBooks")
    private User user;

    @OneToOne
    @JsonIgnoreProperties("rentedBook")
    private Book book;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "was_extended")
    private Boolean wasExtended;

    public RentedBook() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getWasExtended() {
        return wasExtended;
    }

    public void setWasExtended(Boolean wasExtended) {
        this.wasExtended = wasExtended;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
