package by.mprotas.dao;

import by.mprotas.config.TestApplicationContext;
import by.mprotas.dto.Call;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalTime;

@ContextConfiguration(classes = TestApplicationContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CallDaoTest {
    @Autowired
    private ICallDao callDao;

    private Call call;

    public Call createCall(String firstName, String lastName, LocalTime time, String phone) {
        Call call = new Call();
        call.setFirstName(firstName);
        call.setLastName(lastName);
        call.setTime(time);
        call.setPhone(phone);
        return call;
    }

    @Test
    public void testFullyInitializedCall() {
        Call call = createCall("firstName", "lastName", LocalTime.now(), "123456789");
//        File file = new File("./temp/LASTNAME_FIRSTNAME.txt");
//        assertFalse(file.exists());
        callDao.saveCall(call);
//        assertTrue(file.exists());
    }
}
