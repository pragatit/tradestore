package org.example.tradestore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
public class Trade implements Serializable {

    private String tradeId;
    private int version;
    private String counterPartyId;
    private String bookId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
    private Date maturityDate;
    private Date createdDate;
    private boolean expired;

}
