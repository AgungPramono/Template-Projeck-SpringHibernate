/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MateriService.java
 * 
 *  Created on Nov 14, 2015, 9:07:28 PM
 */
package com.agung.template.springhibernate.service;

import com.agung.template.springhibernate.entity.Materi;
import java.util.List;

/**
 *
 * @author agung
 */
public interface MateriService {
    void simpan(Materi m);
    void hapus(Materi m);
    List<Materi> getAll();
    Materi findById(Integer id);
    List<Materi> findByNama(String nama);
    Long countAll();
    Long countByNama(String nama);
    
}
