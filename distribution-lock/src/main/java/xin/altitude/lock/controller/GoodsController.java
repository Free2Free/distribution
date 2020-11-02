package xin.altitude.lock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xin.altitude.lock.entity.Goods;
import xin.altitude.lock.service.GoodsService;
import xin.altitude.lock.service.OrderService;

/**
 * @author explore
 * @date 2020/11/02 13:47
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService service;


    @GetMapping("/list/{id}")
    public Goods getGoodsById(@PathVariable("id") Long id){
        return service.getGoodsById(id);
    }
}
