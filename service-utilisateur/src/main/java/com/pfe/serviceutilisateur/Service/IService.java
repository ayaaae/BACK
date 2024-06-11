package com.pfe.serviceutilisateur.Service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IService<T> {
    public List<T> All();
    public T Add(T x);
    T Update(T x);
    public T Get(long id);
    public void Delete(long id);
    Optional<T> findByEmail(String email);
}
