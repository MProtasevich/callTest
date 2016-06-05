package by.mprotas.controller;

import by.mprotas.dto.Call;
import by.mprotas.service.ICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/call")
public class CallController {

    @Autowired
    private ICallService callService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addCall(@Valid Call call, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        callService.saveCall(call);

        return new ResponseEntity<>(call, HttpStatus.OK);
    }
}