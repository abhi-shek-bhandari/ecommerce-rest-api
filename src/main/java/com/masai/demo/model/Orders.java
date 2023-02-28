package com.masai.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private String firstName;

    private String lastName;

    private Double orderAmount;

    private String city;

    private String state;

    private String phone;

    private String pincode;

    private String landmark;

    private Boolean orderCancelled;

    private String promoCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime orderTimeCreatedOn;

    @ElementCollection
    @CollectionTable(name = "order_item_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "orderId")})
    @MapKeyColumn(name = "item_name")
    @Column(name = "price")
    private Map<String, Double> products = new HashMap<>();
}
