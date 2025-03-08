package br.edu.ufersa.cc.sd.views;

import br.edu.ufersa.cc.sd.services.OrderService;
import br.edu.ufersa.cc.sd.services.ScreenService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ListAllView implements Runnable {

    private final OrderService service;
    private final ScreenService screen;

    @Override
    public void run() {
        screen.clear();
        screen.println("Listando todas as ordens de serviço...");

        service.list().forEach(screen::println);
        service.list().forEach(screen::println);
        screen.awaitAndBack();
    }

}
