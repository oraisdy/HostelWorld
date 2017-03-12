package edu.nju.vo;

import edu.nju.entity.Branch;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created by Dora on 2017/2/21.
 */
@Data
@Entity
public class ManagerVO {
    @Id
    @Column(name = "id")
    private int id;

    private String name;
    private String role;
//    private String password;
    @OneToMany(mappedBy = "manager")
    private Collection<Branch> branchesById;

}
