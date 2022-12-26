package com.xcy.community;

import com.xcy.community.dao.DiscussPostMapper;
import com.xcy.community.dao.UserMapper;
import com.xcy.community.entity.DiscussPost;
import com.xcy.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser() {
        User user1 = userMapper.selectById(101);
        System.out.println(user1);
        User user2 = userMapper.selectByName("liubei");
        User user3 = userMapper.selectByEmail("nowcoder101@sina.com");
        User user4 = userMapper.selectById(102);
        System.out.println(user1.equals(user2) && user2.equals(user3));
        System.out.println(user1.equals(user4));
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("!2345245");
        user.setSalt("Ascc");
        user.setEmail("324fsdf235@163.com");
        user.setHeaderUrl("http://www.nowcoder.com/102.png");
        user.setCreateTime(new Date());

        System.out.println(user.getId());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdateStatus() {
        int rows = userMapper.updateStatus(153, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(153, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(153, "hello");
        System.out.println(rows);
    }

    @Test
    public void testUpdateStatusByUser() {
        User user = userMapper.selectById(153);
        int rows = userMapper.updateStatusTo1ByUser(user);
        System.out.println(rows);
    }

    @Test
    public void testDeleteUser() {
        List<User> users = userMapper.getUsersByIdRange(150, 200);
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            int rows = userMapper.deleteUser(it.next().getId());
            System.out.println("deleted " + rows + " user(s) from database");
        }
    }

    @Test
    public void testGetUsersByIdRange() {
        int begin = 21, end = 30;
        List<User> users = userMapper.getUsersByIdRange(begin, end);
        Iterator it = users.iterator();
        System.out.println(users.size());
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(111, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }

        int rows = discussPostMapper.selectDiscussPostRows(111);
        System.out.println(rows);
    }
}
