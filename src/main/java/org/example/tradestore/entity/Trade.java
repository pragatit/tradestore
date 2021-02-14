package org.example.tradestore.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
public class Trade implements Serializable {

    @Id
    private String tradeId;

    @Column(nullable = false)
    private int version;

    @Column(nullable = false)
    private String counterPartyId;

    @Column(nullable = false)
    private String bookId;

    @Column(nullable = false)
    private Date maturityDate;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column
    @UpdateTimestamp
    private Date updatedDate;

    @Column(nullable = false)
    private boolean expired;
}
