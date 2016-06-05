package by.mprotas.service.impl;

import by.mprotas.dao.ICallDao;
import by.mprotas.dto.Call;
import by.mprotas.extractor.IPhoneExtractor;
import by.mprotas.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class CallService implements ICallService {
    @Autowired
    private ICallDao callDao;

    @Autowired
    private IPhoneExtractor phoneExtractor;

    @Override
    public boolean saveCall(Call call) {
        call.setPhone(phoneExtractor.extractPhone(call.getPhone()));
        call.setTime(LocalTime.now());
        return callDao.saveCall(call);
    }
}
