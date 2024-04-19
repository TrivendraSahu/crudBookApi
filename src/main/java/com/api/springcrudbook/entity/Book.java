package com.api.springcrudbook.entity;

import java.time.LocalDate;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="books_store")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String title;
	private LocalDate publishingDate;
	private String publisherName;
	private int totalCopies;
    private int copiesIssued;
    private int copiesAvailable;
    private String issuedBy;
    @Lob
    private byte[] image;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
	private Author author;
    
    public boolean isAvailable(int quantity) {
        return (totalCopies - copiesIssued) >= quantity;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(LocalDate publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public int getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}

	public int getCopiesIssued() {
		return copiesIssued;
	}

	public void setCopiesIssued(int copiesIssued) {
		this.copiesIssued = copiesIssued;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book(Long id, String title, LocalDate publishingDate, String publisherName, int totalCopies,
			int copiesIssued, int copiesAvailable, byte[] image, Author author) {
		super();
		this.id = id;
		this.title = title;
		this.publishingDate = publishingDate;
		this.publisherName = publisherName;
		this.totalCopies = totalCopies;
		this.copiesIssued = copiesIssued;
		this.copiesAvailable = copiesAvailable;
		this.image = image;
		this.author = author;
	}

	public Book() {
		super();
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", publishingDate=" + publishingDate + ", publisherName="
				+ publisherName + ", totalCopies=" + totalCopies + ", copiesIssued=" + copiesIssued
				+ ", copiesAvailable=" + copiesAvailable + ", image=" + Arrays.toString(image) + ", author=" + author
				+ "]";
	}

	public String getIssuedBy() {
		return issuedBy;
	}

	public void setIssuedBy(String issuedBy) {
		this.issuedBy = issuedBy;
	}
   
    
}
