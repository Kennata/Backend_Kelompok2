package com.example.BackendTubes.RiwayatPenghuni;

import java.util.*;
import java.time.LocalDate;
import java.io.IOException;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.example.BackendTubes.Kos.Kos;
import com.example.BackendTubes.Kos.KosRepository;

@RestController
@RequestMapping(path = "/api/riwayat-penghuni")
public class RiwayatPenghuniController {
    private final RiwayatPenghuniService riwayatPenghuniService;

    @Autowired
    public RiwayatPenghuniController(RiwayatPenghuniService riwayatPenghuniService){
        this.riwayatPenghuniService = riwayatPenghuniService;
    }

    @GetMapping
    public List<RiwayatPenghuni> getRiwayatPenghuni(){
        return riwayatPenghuniService.getRiwayatPenghuni();
    }
    
   @PostMapping
    public void addNewRiwayatPenghuni(@RequestBody RiwayatPenghuni riwayatPenghuni) {
        riwayatPenghuniService.addNewRiwayatPenghuni(riwayatPenghuni);
    }


}