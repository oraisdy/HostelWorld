package edu.nju.repository;

import edu.nju.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dora on 2017/3/11.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String name);
}
