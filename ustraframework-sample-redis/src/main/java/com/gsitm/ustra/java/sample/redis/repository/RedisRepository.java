package com.gsitm.ustra.java.sample.redis.repository;

import com.gsitm.ustra.java.sample.redis.model.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * redis sample repository
 *
 * @author youngran.kwon@gsitm.com
 */
public interface RedisRepository extends CrudRepository<Person, String> {
}
