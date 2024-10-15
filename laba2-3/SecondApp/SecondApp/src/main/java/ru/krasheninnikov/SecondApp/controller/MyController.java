package ru.krasheninnikov.SecondApp.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.krasheninnikov.SecondApp.exception.ValidationFailedException;
import ru.krasheninnikov.SecondApp.model.*;
import ru.krasheninnikov.SecondApp.service.ModifyResponseService;
import ru.krasheninnikov.SecondApp.service.ValidationService;
import ru.krasheninnikov.SecondApp.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Received request: {}", request);

        Response response = buildInitialResponse(request);

        try {
            validationService.isValid(bindingResult);

        } catch (ValidationFailedException e) {
            log.error("Validation failed: {}", bindingResult.getFieldError().toString());
            return createErrorResponse(response, Codes.FAILED, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            log.error("Unexpected error: {}", request, e);
            return createErrorResponse(response, Codes.FAILED, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Response modifiedResponse = modifyResponseService.modify(response);
        log.info("Response: {}", modifiedResponse);
        return new ResponseEntity<>(modifiedResponse, HttpStatus.OK);
    }

    private Response buildInitialResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private ResponseEntity<Response> createErrorResponse(Response response, Codes code, ErrorCodes errorCode, ErrorMessages errorMessage, HttpStatus status) {
        response.setCode(code);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return new ResponseEntity<>(response, status);
    }
}
