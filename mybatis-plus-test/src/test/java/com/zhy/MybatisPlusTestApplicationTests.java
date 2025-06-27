package com.zhy;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhy.mapper.UserMapper;
import com.zhy.module.GradeEnum;
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

		System.out.println(("----- selectAll method test ------"));

		System.out.println(userMapper.selectById11(1));
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
		Page<User> page = new Page<>(1, 5);
		System.out.println("-----------------------");
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.eq("name", "Judith Allen");
		List<User> list1 = userService.list(page, wrapper);
		list1.forEach(System.out::println);
		System.out.println("-----------------------");
		List<User> list2 = userService.list(new Page<>(1, 5));
		list2.forEach(System.out::println);

	}

	@Test
	public void select1() {
		User user = new User();
		List<User> users = user.selectAll();
		users.forEach(System.out::println);
	}


	@Test
	public void insertEnum() {

		User user = new User();
		user.setAge(11);
		user.setName("zzz");
		user.setType(GradeEnum.HIGH);
		userMapper.insert(user);


		userMapper.selectList(null).forEach(System.out::println);
	}


}
