package com.shopkart.userService.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@Table(name = User.TABLE_NAME)
public class User {

    public static final String TABLE_NAME="USERS";
    public static final String SEQ_GEN_ALIAS="seq_gen_alias";
    public static final String SEQ_GEN_STRATEGY="uuid2";

    @Id
    @Column(name="USER_ID")
    @GeneratedValue(generator = SEQ_GEN_ALIAS)
    @GenericGenerator(name=SEQ_GEN_ALIAS,strategy = SEQ_GEN_STRATEGY)
    private String userId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "PASSWORD")
    private String password;

}
