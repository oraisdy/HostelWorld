package edu.nju.repository;

import edu.nju.entity.Manager;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Dora on 2017/2/21.
 */
public interface ManagerRepository extends CrudRepository<Manager,Integer> {
}
