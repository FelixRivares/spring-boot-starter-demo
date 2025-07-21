package ru.sample.db;

import org.springframework.data.repository.CrudRepository;

public interface TimeRepository extends CrudRepository<TimeEntity, Integer> {
}
