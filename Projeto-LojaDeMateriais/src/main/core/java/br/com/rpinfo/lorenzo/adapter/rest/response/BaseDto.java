package main.core.java.br.com.rpinfo.lorenzo.adapter.rest.response;

import br.framework.classes.dto.DtoClass;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BaseDto extends DtoClass {
    public static <T, U> List<U> criarDtos(List<T> lista, Function<T, U> mapper) {
        List<U> dtos = new ArrayList<>();
        lista.forEach(dto -> dtos.add(mapper.apply(dto)));
        return dtos;
    }
}
