package com.global_solution.gs_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.global_solution.gs_api.models.Cultivos;
import com.global_solution.gs_api.repository.CultivosRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class CultivosServiceImpl implements CultivosService {

    @Autowired // IoD IoC
    CultivosRepository repository;

    private EntityManager entityManager;

    public CultivosServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createJPQL(Cultivos cultivo) {

        try {

            entityManager.getTransaction().begin();

            entityManager.persist(cultivo);

            entityManager.getTransaction().commit();

        } catch (Exception e) {

            entityManager.getTransaction().rollback();

            throw e;

        }

    }

    @Override
    public List<Cultivos> findByLikeJPQL(String cultivos) {
        String jpql = "SELECT d FROM TB_CULTIVOS d WHERE d.grao LIKE :cultivo";
        TypedQuery<Cultivos> query = entityManager.createQuery(jpql, Cultivos.class)
                .setParameter("cultivo", "%" + cultivos + "%")
                .setHint("jakarta.persistence.query.timeout", 60000);
        List<Cultivos> cultivo = query.getResultList();
        return cultivo;
    }

    @Override
    public void updateJPQL(Cultivos cultivo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cultivo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void deleteJPQL(Cultivos cultivo) {
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(cultivo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Cultivos findByIdJPQL(Long id) {
        Cultivos cultivo = entityManager.find(Cultivos.class, id);
        if (cultivo == null) {
            return null;
        }
        return cultivo;
    }

    @Override
    public void deleteByIdJPQL(Long id) {
        entityManager.getTransaction().begin();
        try {
            Cultivos cultivo = entityManager.find(Cultivos.class, id);
            if (cultivo != null) {
                entityManager.remove(cultivo);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}