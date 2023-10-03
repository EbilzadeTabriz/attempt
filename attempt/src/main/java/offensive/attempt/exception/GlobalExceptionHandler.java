package offensive.attempt.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFound exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Employee_Not_Found"

        );
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExist.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExist(EmailAlreadyExist exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDate.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "Employee_Email_Already_Exist"

        );
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleException(Exception exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDate.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "Internal Server Error"
//
//        );
//        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String > errors = new HashMap<>();
      List<ObjectError> errorList = ex.getBindingResult().getAllErrors();

      errorList.forEach((errorObject)->
      {
          String fieldName = ((FieldError) errorObject).getField();
          String message = errorObject.getDefaultMessage();
          errors.put(fieldName,message);

      });
return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
