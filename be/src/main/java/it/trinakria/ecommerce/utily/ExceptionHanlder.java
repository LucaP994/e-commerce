package it.trinakria.ecommerce.utily;

public class ExceptionHanlder extends Exception{
    public ExceptionHanlder(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
