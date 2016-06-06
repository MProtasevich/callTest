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

    /**
     * Formats {@link Call phone} field according to {@link IPhoneExtractor} implementation,
     * sets current time to {@link Call time} field and saves the {@link Call} via {@link ICallDao}
     * @param call {@link Call} object to save.
     * @return true if {@link Call} was saved without errors.
     */
    @Override
    public boolean saveCall(Call call) {
        call.setPhone(phoneExtractor.extractPhone(call.getPhone()));
        call.setTime(LocalTime.now());
        return callDao.saveCall(call);
    }
}
