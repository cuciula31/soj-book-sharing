package com.soj.booksharing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.soj.booksharing.services.RentalService;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class PendingRental {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnoreProperties("pendingBooks")
    private User user;

    @ManyToOne()
    @JoinTable(name = "pending_from",
            joinColumns = @JoinColumn(name = "rental_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    @JsonIgnoreProperties({"users", "pendingFrom", "book", "pendingTo"})
    private User rentedFrom;

    @ManyToOne
    @JsonIgnoreProperties({"users", "rentedFrom", "book", "pendingTo"})
    private Book book;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "was_extended")
    private Boolean wasExtended;

    public PendingRental() {
    }

    public PendingRental(User user, User rentedFrom, Book book, Date startDate, Date endDate, Boolean wasExtended) {
        this.user = user;
        this.rentedFrom = rentedFrom;
        this.book = book;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wasExtended = wasExtended;
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

    public User getRentedFrom() {
        return rentedFrom;
    }

    public void setRentedFrom(User rentedFrom) {
        this.rentedFrom = rentedFrom;
    }

}
