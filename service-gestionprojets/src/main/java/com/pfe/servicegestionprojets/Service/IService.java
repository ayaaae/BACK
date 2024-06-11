package com.pfe.servicegestionprojets.Service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<T> {
    public List<T> All();
    public void Add(T x);
    public long Addprojet(T x);
    void Update(T x);
    public T Get(long id);
    public void Delete(long id);
}
