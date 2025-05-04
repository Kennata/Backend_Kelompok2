package com.example.BackendTubes.Penghuni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/penghuni")
public class PenghuniController {

    private final PenghuniService penghuniService;

    @Autowired
    public PenghuniController(PenghuniService penghuniService) {
        this.penghuniService = penghuniService;
    }

    @GetMapping
    public List<Penghuni> getPenghuni(){
        return penghuniService.getPenghuni();
    }

    @PostMapping
    public void addNewPenghuni(@RequestBody Penghuni penghuni){
        penghuniService.addNewPenghuni(penghuni);
    }

    @PatchMapping(path = "api/penghuni/{id}")
    public void updatePenghuni(@PathVariable Long id, @RequestBody Map<String, Object> update){
        penghuniService.updatePenghuni(id,update);
    }
}
