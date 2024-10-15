package com.sc.servicecompanies.infrastructure.repositories.person;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sc.servicecompanies.application.services.PersonService;
import com.sc.servicecompanies.domain.entities.Person;

public class PersonImpl implements PersonService{
    
    @Autowired
    private PersonRepository repository;

    @Override
    public List<Person> findAll() {
        return (List<Person>) repository.findAll();
    }

    @Override
    public Optional<Person> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Person save(Person Person) {
        return repository.save(Person);
    }

    @Override
    public Optional<Person> update(String id, Person Person) {
        // Verificar si el objeto existe
        Optional<Person> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Actualizar el objeto existente
            Person.setDocumentNumber(id); // Asegurarse de que el ID sea el mismo
            return Optional.of(repository.save(Person));
        }
        return Optional.empty(); // Si no existe, retornar vacío
    }

    @Override
    public Optional<Person> delete(String id) {
        Optional<Person> existingResponse = repository.findById(id);
        if (existingResponse.isPresent()) {
            // Eliminar si existe
            repository.deleteById(id);
            return existingResponse; // Retornar el objeto eliminado
        }
        return Optional.empty();
     } // Si no existe, retornar vacío    }
}
