package by.mprotas.dao.impl;

import by.mprotas.dao.ICallDao;
import by.mprotas.dto.Call;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;

@Component
public class CallDao implements ICallDao {

    @Override
    public boolean saveCall(Call call) {
        File file = new File(getFileName("../temp/", call.getFirstName(), call.getLastName()));
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(call.getPhone());
            pw.println(call.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            return false;
        }
        return true;
    }

    private String getFileName(String path, String firstName, String lastName) {
        return path + lastName.toUpperCase() + (isEmpty(firstName) ? "" : "_" + firstName.toUpperCase()) + ".txt";
    }

    private boolean isEmpty(String string) {
        return string == null || "".equals(string);
    }
}
