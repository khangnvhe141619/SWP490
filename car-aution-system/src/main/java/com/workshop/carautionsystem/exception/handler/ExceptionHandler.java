package com.workshop.carautionsystem.exception.handler;

import com.workshop.carautionsystem.exception.ConstraintException;
import com.workshop.carautionsystem.exception.NotFoundException;
import com.workshop.carautionsystem.exception.ServiceException;
import com.workshop.carautionsystem.model.ResponseObject;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.workshop.carautionsystem.exception.MessageConstant;
import com.workshop.carautionsystem.exception.NotUserException;


import javax.ws.rs.BadRequestException;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleAllException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, MessageConstant.EXCEPTION_UNEXPECTED), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     * NotFoundException sẽ được xử lý riêng tại đây
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> NotFoundException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, ex.getMessage()), HttpStatus.OK);

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseObject> handleServiceException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
//        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, ex.getMessage()), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseObject> handleBadRequest(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ResponseObject> handleConstraintException(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, ""), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintException.class)
    public ResponseEntity<ResponseObject> handleConstraintExceptionDelete(Exception ex, WebRequest request) {
        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, ex.getMessage()), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NotUserException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ResponseObject> handleNotUserException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
//        ex.printStackTrace();
        return new ResponseEntity(new ResponseObject(null, ResponseObject.STATUS_FAIL, MessageConstant.FORBIDDEN_MESSAGE), HttpStatus.FORBIDDEN);
    }
}
