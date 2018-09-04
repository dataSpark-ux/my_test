package com.wy.newblog;

import com.wy.newblog.rabbitmq.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewblogApplication.class)
public class NewblogApplicationTests {
    @Autowired
    private Sender sender;
    @Test
    public void send() {
        this.sender.send();
    }

}
