package xin.altitude.lock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.lock.service.OrderService;

/**
 * @author explore
 * @date 2020/11/02 13:47
 */
@RestController
public class IndexController {

    @Autowired
    private OrderService service;


    /**
     * 下单
     *  @param id  商品ID
     * @param num 下单数量，默认为1
     * @return
     */
    @PostMapping("/order")
    public Boolean index(@RequestParam(value = "id", defaultValue = "2") Long id,
                         @RequestParam(value = "num", defaultValue = "1", required = false) int num) {
        return service.genOrder(id, num);
    }


    @PostMapping("/orderZk")
    public Boolean indexWithZk(@RequestParam(value = "id", defaultValue = "2") Long id,
                         @RequestParam(value = "num", defaultValue = "1", required = false) int num) {
        System.out.printf("AA:", "开始了");
        return service.genOrderWithZk(id, num);
    }

    @PostMapping("/orderRedis")
    public Boolean indexWithRedis(@RequestParam(value = "id", defaultValue = "2") Long id,
                               @RequestParam(value = "num", defaultValue = "1", required = false) int num) {
        System.out.printf("AA:", "开始了");
        return service.genOrderWithRedis(id, num);
    }
}
