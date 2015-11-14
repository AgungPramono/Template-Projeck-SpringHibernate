/*
 *  Copyright (c) 2015 Agung Pramono <agungpermadi13@gmail.com || www.github.com/agung pramono>.
 *  All rights reserved.
 * 
 * Silahkan digunakan dengan bebas / dimodifikasi
 * Dengan tetap mencantumkan nama @author dan Referensi / Source
 * Terima Kasih atas Kerjasamanya.
 * 
 *  MateriServiceImpl.java
 * 
 *  Created on Nov 14, 2015, 9:07:51 PM
 */
package com.agung.template.springhibernate.service.impl;

import com.agung.template.springhibernate.entity.Materi;
import com.agung.template.springhibernate.service.MateriService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author agung
 */
@Service("materiService")
@Transactional(readOnly = true)
public class MateriServiceImpl implements MateriService {

    @Autowired private SessionFactory sessionFactory;
    
    @Transactional(readOnly = false)
    public void simpan(Materi m) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(m);
    }

    @Transactional(readOnly = false)
    public void hapus(Materi m) {
        if(m != null && m.getId() != null){
            sessionFactory.getCurrentSession()
                .delete(m);
        }
    }

    public List<Materi> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Materi m order by m.namaMateri")
                .list();
    }

    public Materi findById(Integer id) {
        if (id == null ) return null;
        
        return (Materi) sessionFactory.getCurrentSession()
                .get(Materi.class, id);
    }

    public List<Materi> findByNama(String nama) {
        if(!StringUtils.hasLength(nama))return new ArrayList<Materi>();
        
        return sessionFactory.getCurrentSession()
                .createQuery("from Materi m where lower(m.namaMateri) like :nama order by m.namaMateri")
                .setString("nama", "%"+nama.toLowerCase()+"%")
                .list();
    }

    public Long countAll() {
        return (Long) sessionFactory.getCurrentSession().
                createQuery("select count(m) from Materi m ").uniqueResult();
    }

    public Long countByNama(String nama) {
        if(!StringUtils.hasText(nama)) return 0L;
        
        return (Long) sessionFactory.getCurrentSession()
                .createQuery("select count(m) from Materi m where m.namaMateri like :nama")
                .setString("nama", "%"+nama.toLowerCase()+"%")
                .uniqueResult();
    }
    
}
