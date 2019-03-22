package com.triador.yourwayserver.service.converter;

public interface Converter<R, T> {
    T convert(R t);
}
