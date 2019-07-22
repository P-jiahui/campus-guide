package org.donglight.campus.guide.bak;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

/**
 * 角色
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@RelationshipEntity(type = "ACTED_IN")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue
    private Long relationshipId;

    @Property
    private String title;

    @StartNode
    @JsonIgnore
    private Person person;

    @EndNode
    private Movie movie;
}
