package br.edu.ufersa.cc.sd.dto;

import java.util.List;

import br.edu.ufersa.cc.sd.enums.ResponseStatus;
import br.edu.ufersa.cc.sd.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    private final ResponseStatus status;
    private final String message;
    private final List<Order> orders;

}
