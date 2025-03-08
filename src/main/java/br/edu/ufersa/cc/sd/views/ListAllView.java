package br.edu.ufersa.cc.sd.views;

import br.edu.ufersa.cc.sd.services.OrderService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListAllView implements Runnable {

    private final OrderService service;

    @Override
    public void run() {
        System.out.println("Listando todas as ordens de serviço...");

        service.list().forEach(System.out::println);
    }

}
