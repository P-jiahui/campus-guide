package org.donglight.campus.guide.bak;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * 人实体
 *
 * @author donglight
 * @date 2019/6/22
 * @since 1.0.0
 */
@NodeEntity(label = "Person")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;

    @Property(name = "born")
    private int born;

    @Relationship(type = "ACTED_IN")
    @JsonIgnoreProperties({"person"})
    private Role actedIn;
}
