package com.zhy;

import com.zhy.mapper.UserMapper;
import com.zhy.module.User;
import com.zhy.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusTestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);

		userList.forEach(System.out::println);
	}

	@Test
	public void insert() {

		User user = new User();
		user.setAge(11);
		user.setName("zzz");
		userMapper.insert(user);
	}

	@Test
	public void select() {

		List<User> list = userService.list();

		list.forEach(System.out::println);
	}

	@Test
	public void select1() {
		User user = new User();
		List<User> users = user.selectAll();
		users.forEach(System.out::println);
	}

}
