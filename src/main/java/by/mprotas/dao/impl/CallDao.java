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
        File file = new File(getFileName("./", call));
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.println(call.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            pw.println(call.getPhone());
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            return false;
        }
        return true;
    }

    private String getFileName(String path, Call call) {
        return path + call.getLastName() + (call.getFirstName() == null ? "" : call.getFirstName());
    }
}
