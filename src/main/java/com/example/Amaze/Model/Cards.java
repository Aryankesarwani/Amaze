package com.example.Amaze.Model;

import com.example.Amaze.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cards")
public class Cards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Enumerated(EnumType.STRING)
    CardType cardType;

    Date expiry;

    int cvv;

    long cardNumber;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
