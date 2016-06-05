package by.mprotas.service;


import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import by.mprotas.dao.ICallDao;
import by.mprotas.dto.Call;
import by.mprotas.extractor.IPhoneExtractor;
import by.mprotas.service.impl.CallService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CallServiceTest {
    @Mock
    private ICallDao callDao;
    @Mock
    private IPhoneExtractor phoneExtractor;
    @Mock
    private Call call;

    @InjectMocks
    private ICallService callService = new CallService();

    @Test
    public void testService() {
        callService.saveCall(call);

        verify(phoneExtractor).extractPhone(anyString());
        verify(callDao).saveCall(any(Call.class));
    }
}
