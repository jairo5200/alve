/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jairo
 */
@Stateless
public class AlquilerFacade extends AbstractFacade<Alquiler> {
    @PersistenceContext(unitName = "alveaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlquilerFacade() {
        super(Alquiler.class);
    }
    
}