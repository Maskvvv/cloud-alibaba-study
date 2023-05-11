package com.zhy.mongo.collection;

import com.zhy.mongo.SpringBootMongoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2023/5/10 17:18
 */
public class CollectionTest extends SpringBootMongoApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;


    @Test
    public void creatCollection() {

        mongoTemplate.createCollection("product1");

    }

    @Test
    public void document() {


        mongoTemplate.save();

    }

}
