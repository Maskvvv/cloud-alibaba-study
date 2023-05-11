package com.zhy.mongo.query;

import com.zhy.mongo.SpringBootMongoApplicationTests;
import com.zhy.mongo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author zhouhongyin
 * @since 2023/5/10 22:06
 */
public class QueryTest extends SpringBootMongoApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void sort() {
        Query query = new Query();
        query.with(Sort.by((Sort.Order.desc("age"))));

        List<User> users = mongoTemplate.find(query, User.class);
        System.out.println(users);

    }

    @Test
    public void jsonQuery() {
        BasicQuery basicQuery = new BasicQuery("{name: 'mike'}");

        List<User> users = mongoTemplate.find(basicQuery, User.class);

        System.out.println(users);
    }


}
