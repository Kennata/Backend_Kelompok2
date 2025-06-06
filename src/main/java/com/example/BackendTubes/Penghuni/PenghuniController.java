package com.example.BackendTubes.Penghuni;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PatchMapping(path = "{id}")
    public void updatePenghuni(@PathVariable("id") Long id, @RequestBody Map<String, Object> update){
        penghuniService.updatePenghuni(id,update);
    }

    @PostMapping(path = "/tambah")
    public Map<String, Object> tambahPenghuni(@RequestBody PenghuniDTO penghuniDTO){
        return penghuniService.tambahPenghuni(penghuniDTO);
    }

    @GetMapping(path = "{nama}")
    public Penghuni getPenghuniByNama(@PathVariable("nama") String nama){
        return penghuniService.findPenghuniByNama(nama);
    }
}
