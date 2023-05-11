package com.zhy.mongo.document;

import com.zhy.mongo.SpringBootMongoApplicationTests;
import com.zhy.mongo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhouhongyin
 * @since 2023/5/10 22:06
 */
public class DocumentTest extends SpringBootMongoApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void insert() {
        User user = new User(UUID.randomUUID().toString(), "mike", 10, new Date());
        mongoTemplate.insert(user);

    }

    @Test
    public void save() {
        User user = new User(UUID.randomUUID().toString(), "name", 10, new Date());
        mongoTemplate.insert(user);

    }


}
