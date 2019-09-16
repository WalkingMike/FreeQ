package com.project.freeq.controller;

import com.project.freeq.model.Partner;
import com.project.freeq.service.PartnerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/partner")
public class PartnerController {
    @Autowired
    private final PartnerService prtnService;

    @GetMapping(value = "/getall")
    public @ResponseBody List<Partner> selectAll() {
        return prtnService.getAll();
    }

    @GetMapping(value = "/id/")
    public @ResponseBody Partner getPartnerByID(@RequestParam Long id) {
        return prtnService.getPartnerByID(id);
    }

    @GetMapping(value = "/phone/")
    public @ResponseBody Partner getPartnerByPhone(@RequestParam String phone) {
        return prtnService.getPartnerByPhone(phone);
    }

    @PostMapping(value = "/add")
    public void addPartner(@RequestBody Partner prtn) {

        prtnService.savePartner(prtn);
    }

    @DeleteMapping(value = "/remove")
    public void removePartner(@RequestParam Long id) {
        prtnService.removePartner(id);
    }

    @DeleteMapping(value = "/remove/phone")
    public void removePartnerByPhone(@RequestParam String phone) {
        prtnService.removePartnerByPhone(phone);
    }
}