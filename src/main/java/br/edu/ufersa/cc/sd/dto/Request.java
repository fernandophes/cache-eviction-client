package br.edu.ufersa.cc.sd.dto;

import java.io.Serializable;

import br.edu.ufersa.cc.sd.enums.Operation;
import br.edu.ufersa.cc.sd.models.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Request implements Serializable {

    private final Operation operation;
    private final Order order;

}
