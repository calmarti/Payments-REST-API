package net.calmarti.banking_app.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="account_holder_name")
    private String accountHolderName;
    @Column(name="balance")
    private Double balance;

}
