package com.zhy.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author zhouhongyin
 * @since 2023/5/10 22:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("user")
public class User {

    @Id
    private String id;

    @Field("username")
    private String name;

    private Integer age;

    private Date date;

}
